package mx.edu.utez.P5exercises.controller.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestVehiculos {
    @NotBlank(message = "El nombre es obligatorio")
    private String nombreCliente;
    @NotNull(message = "La edad es un campo onbligatorio")
    @Min(value = 18,message = "La edad del conductor debe de ser minimo 18")
    private int edadConductor;
    @NotBlank(message = "El tipo de vehiculo es obligatorio")
    private String tipoVehiculo;
    @NotNull(message = "Los dias rentados deben de ser especificados")
    @Max(value = 30,message = "La renta del vehiculo no puede superar los 30 dias")
    private int diasRenta;
    @NotNull(message = "La estimacion debe de ser especificada")
    @Max(value = 5000,message = "La estimacion de los kilometros no puede superar los 5K")
    private int kilometrosEstimados;
    @NotNull(message = "Si la renta es asegurada o no debe de ser especificada")
    private boolean seguroCompleto;

}
