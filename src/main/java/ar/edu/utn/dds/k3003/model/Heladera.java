package ar.edu.utn.dds.k3003.model;

import lombok.Data;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Data
public class Heladera {

    private Integer id;
    private final String nombre;
    List<String> viandas;
    int capacidadMax; //en unidad de viandas
    LocalDate fechaInicioFuncionamiento;
    boolean activa;
   // Collection<Integer> temperaturas;
    float longitud;
    float latitud;
    String direccion;
    String ciudad;
    float temperaturaMin;
    float temperaturaMax;
    long cantidadAperturas;

    public Heladera(Integer id,String nombre) {
        this.id = id;
        this.nombre=nombre;
        this.fechaInicioFuncionamiento=LocalDate.now();
        this.activa=true;
        //this.capacidadMax=20;//por ahora
        this.cantidadAperturas=0;
        this.viandas=new ArrayList<String>();
    }

    public void depositarVianda(String qrVianda) {
        this.viandas.add(qrVianda);
        this.cantidadAperturas++;
    }

    public void retirarVianda(String qrVianda) throws Exception {
        this.cantidadAperturas++;
        if(!viandas.contains(qrVianda)) {
            throw new Exception("No existe la vianda en la heladera ");
        }
        this.viandas.remove(qrVianda);

    }
}
