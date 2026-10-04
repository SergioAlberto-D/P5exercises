package mx.edu.utez.P5exercises.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseVehiculos {
    private String cliente;
    private double costoRentaBase;
    private double cargoKmAdicionales;
    private double cargoPorEdad;
    private double costoSeguro;
    private double descuento;
    private double totalPagar;
    private String mensaje;
}
