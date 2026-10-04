package mx.edu.utez.P5exercises.controller.dto;

import lombok.*;

@Getter 
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseCotizador {
    private double costoTotal;
    private double volumenCm3;
    private String mensaje;
}
