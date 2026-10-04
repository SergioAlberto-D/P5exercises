package mx.edu.utez.P5exercises.controller.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter 
@Setter 
@AllArgsConstructor 
@NoArgsConstructor 
public class RequestCotizador {
    @NotBlank(message = "El codigo postal es obligatorio")
    private String codigoPostal;

    @NotNull(message = "El peso es obligatorio")
    @DecimalMax (value = "50.0",message = "El paquete no puede pesar mas de 50m kg")
    private double pesoKg;

    @NotNull(message = "El largo es obligatorio")
    @DecimalMax (value = "150.0",message = "Ninguna medida puede superar los 150 cm")
    private double largoCm;

    @NotNull(message = "El ancho es obligatorio")
    @DecimalMax (value = "150.0",message = "Ninguna medida puede superar los 150 cm")
    private double anchoCm;

    @NotNull(message = "El alto es obligatorio")
    @DecimalMax (value = "150.0",message = "Ninguna medida puede superar los 150 cm")
    private double altoCm;

    @NotBlank(message = "El tipo de envio postal es obligatorio")
    private String tipoEnvio;

    @NotNull (message = "El valor declarado es un campo obligatorio")
    @PositiveOrZero (message = "El valor declarado no puede ser negativo")
    private double valorDeclarado;
     
}
