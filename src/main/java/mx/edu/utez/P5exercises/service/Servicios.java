package mx.edu.utez.P5exercises.service;

import mx.edu.utez.P5exercises.controller.dto.RequestCotizador;
import mx.edu.utez.P5exercises.controller.dto.ResponseCotizador;
import mx.edu.utez.P5exercises.exception.custom.CustomBadRequest;
import org.springframework.stereotype.Service;

@Service
public class Servicios {
    public ResponseCotizador CotizarEnvios (RequestCotizador data){
        double costoBase = 80;
        if (
            !data.getTipoEnvio().equalsIgnoreCase("estandar") &&
            !data.getTipoEnvio().equalsIgnoreCase("express") &&
            !data.getTipoEnvio().trim().equalsIgnoreCase("mismodia")
        ) {
            throw new CustomBadRequest("El tipo de envió es invalido o no disponible");
        }
        costoBase += (data.getPesoKg()*12);
        
        if ((data.getAltoCm()*data.getAnchoCm()*data.getLargoCm())>1000000) {
            throw new CustomBadRequest("El volumen dno debe superar el millón de cm cúbicos");
        }

        if ((data.getAltoCm()*data.getAnchoCm()*data.getLargoCm())>50000) {
            costoBase +=100;
        }

        switch (data.getTipoEnvio().trim().toLowerCase()) {
            case "estandar":
                break;
            case "express":
                costoBase += (costoBase*.40);
                break;
            case "mismodia":
                costoBase += (costoBase*.70);
                break;
        }
        if (data.getValorDeclarado()>10000) {
            costoBase += (data.getValorDeclarado()*.02);
        }
        double cuadrados = data.getAltoCm()*data.getAnchoCm()*data.getLargoCm();
        return new ResponseCotizador(costoBase,cuadrados,"Su cotización fue calculado con éxito");
    }
}
