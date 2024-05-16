package ar.edu.utn.dds.k3003.app;

import ar.edu.utn.dds.k3003.clientes.ViandasProxy;
import ar.edu.utn.dds.k3003.controller.HeladeraController;
import ar.edu.utn.dds.k3003.controller.TemperaturaController;
import ar.edu.utn.dds.k3003.facades.dtos.Constants;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.javalin.Javalin;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;

public class WebApp {
    public static void main(String[] args){

        var URL_VIANDAS = System.getenv().get("URL_VIANDAS");
        var URL_LOGISTICA = System.getenv().get("URL_LOGISTICA");
        var URL_HELADERAS = System.getenv().get("URL_HELADERAS");
        var URL_COLABORADORES = System.getenv().get("URL_COLABORADORES");

        var port = System.getenv().getOrDefault("PORT", "8080");

        var fachada=new Fachada();
        var objectMapper = createObjectMapper();
        fachada.setViandasProxy(new ViandasProxy(objectMapper));//pruebas locales
        var heladeraController=new HeladeraController(fachada);
        var temperaturaController=new TemperaturaController(fachada);

        Javalin app = Javalin.create().start(Integer.parseInt(port));

        app.post("/heladeras",heladeraController::agregar);
        app.get("/heladeras/{id}",heladeraController::obtener);
        app.post("/temperaturas",temperaturaController::agregar);
        app.get("/heladeras/{id}/temperaturas",temperaturaController::obtener);
        app.post("/depositos",heladeraController::depositar);
        app.post("/retiros",heladeraController::retirar);
    }

    public static ObjectMapper createObjectMapper() {
        var objectMapper = new ObjectMapper();
        configureObjectMapper(objectMapper);
        return objectMapper;
    }

    public static void configureObjectMapper(ObjectMapper objectMapper) {
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        var sdf = new SimpleDateFormat(Constants.DEFAULT_SERIALIZATION_FORMAT, Locale.getDefault());
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        objectMapper.setDateFormat(sdf);
    }
}
