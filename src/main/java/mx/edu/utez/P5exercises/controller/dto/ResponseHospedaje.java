package mx.edu.utez.P5exercises.controller.dto;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseHospedaje {
    private String huesped;
    private double costoHospedajeBase;
    private double ajusteTemporada;
    private double costoDesayuno;
    private double costoEstacionamiento;
    private double descuentoLargaEstancia;
    private double subtotal;
    private double impuestoHospedaje;
    private double totalPagar;
    private String mensaje;
}