package mx.edu.utez.P5exercises.controller.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestHospedaje {
    @NotBlank(message = "El nombre del huésped es obligatorio")
    private String nombreHuesped;

    @NotBlank(message = "El tipo de habitación es obligatorio")
    private String tipoHabitacion;

    @NotNull(message = "El número de noches es obligatorio")
    @Max(value = 30, message = "No se acepta la reservación: el número de noches no puede superar 30")
    private int numeroNoches;

    @NotNull(message = "El número de huéspedes es obligatorio")
    private int numeroHuespedes;

    @NotBlank(message = "La temporada es obligatoria")
    private String temporada;

    @NotNull(message = "Debe especificar si incluye desayuno")
    private boolean incluyeDesayuno;

    @NotNull(message = "Debe especificar si incluye estacionamiento")
    private boolean incluyeEstacionamiento;
}
