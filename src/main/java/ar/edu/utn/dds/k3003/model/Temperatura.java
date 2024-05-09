package ar.edu.utn.dds.k3003.model;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Temperatura {
    private Integer id;
    private final Integer temperatura;
    private final Integer heladeraId;
    private final LocalDateTime fechaMedicion;

    public Temperatura(Integer temperatura, Integer heladeraId, LocalDateTime fechaMedicion) {
        this.temperatura = temperatura;
        this.heladeraId = heladeraId;
        this.fechaMedicion = fechaMedicion;
    }


}
