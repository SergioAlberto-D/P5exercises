package mx.edu.utez.P5exercises.service;

import mx.edu.utez.P5exercises.controller.dto.*;
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
    public ResponseVehiculos CotizarVehiculos(RequestVehiculos data){
        if (
                data.getTipoVehiculo().equalsIgnoreCase("camioneta") &&
                data.getEdadConductor()<25
        ) {
            throw new CustomBadRequest("No se puede rentar una camioneta a un indivio con esa edad");
        }
        if (
                !data.getTipoVehiculo().equalsIgnoreCase("compacto") &&
                !data.getTipoVehiculo().equalsIgnoreCase("sedan") &&
                        !data.getTipoVehiculo().equalsIgnoreCase("suv") &&
                        !data.getTipoVehiculo().equalsIgnoreCase("camioneta")
        ){
            throw new CustomBadRequest("El tipo de vehiculo es invalido");
        }
        double costoPorTipo = 0;
        switch (data.getTipoVehiculo().trim().toLowerCase()) {
            case "compacto":
                costoPorTipo = 550;
                break;
            case "sedan":
                costoPorTipo = 700;
                break;
            case "suv":
                costoPorTipo = 950;
                break;
            case "camioneta":
                costoPorTipo = 1200;
                break;
        }
        return calculacionTotal(data,costoPorTipo);
    }
    public ResponseVehiculos calculacionTotal(RequestVehiculos data,double costoDia) {


        double costoRenta = data.getDiasRenta() * costoDia;
        double incluidos = data.getDiasRenta() * 100;
        double extra = 0;
        if (data.getKilometrosEstimados() > incluidos) {
            extra = (data.getKilometrosEstimados()-incluidos)*4;
        }
        double cargoExtra = 0;
        if (data.getEdadConductor()>17 && data.getEdadConductor()<25) {
            cargoExtra = (costoRenta+extra)*0.15;
        }
        double costoSeguro = 0;
        if (data.isSeguroCompleto()) {
            costoSeguro = (data.getDiasRenta())*180;
        }
        double descuento = 0;
        if (data.getDiasRenta()>6) {
            descuento = costoRenta*.10;
        }
        double total = (costoRenta-descuento)+cargoExtra+extra+costoSeguro;
        return new ResponseVehiculos(
                data.getNombreCliente(),
                costoRenta,
                extra,
                cargoExtra,
                costoSeguro,
                descuento,
                total,
                "Resumen de la cotizacion"
        );
    }
    public ResponseHospedaje CotizarHospedaje(RequestHospedaje data){
        if (
                !data.getTipoHabitacion().equalsIgnoreCase("individual") &&
                        !data.getTipoHabitacion().equalsIgnoreCase("doble") &&
                        !data.getTipoHabitacion().equalsIgnoreCase("suite")
        ) {
            throw new CustomBadRequest("El tipo de habitacion es invalido");
        }
        if(
                !data.getTemporada().equalsIgnoreCase("baja") &&
                        !data.getTemporada().equalsIgnoreCase("regular") &&
                        !data.getTemporada().equalsIgnoreCase("alta")
        ){
            throw new CustomBadRequest("La temporada es invalida");
        }
        int cantidadSegunHabitacion = switch (data.getTipoHabitacion().toLowerCase()){
            case "individual" -> 1;
            case "doble" -> 2;
            case "suite" -> 4;
            default -> throw new IllegalStateException("Unexpected value: " + data.getTipoHabitacion().toLowerCase());
        };
        if (data.getNumeroHuespedes()>cantidadSegunHabitacion){
            throw new CustomBadRequest("El numero de huespedes supera a la capacidad de la habitacion");
        }
        double costoHabitacion = switch (data.getTipoHabitacion().toLowerCase()){
            case "individual" -> 700;
            case "doble" -> 1100;
            case "suite" -> 1800;
            default -> throw new IllegalStateException("Unexpected value: " + data.getTipoHabitacion().toLowerCase());
        };
        double costoHospedaje = costoHabitacion *data.getNumeroNoches();

        double porTemporada = switch (data.getTemporada().toLowerCase()){
          case "baja" -> -(costoHospedaje*.10);
          case "alta" -> (costoHospedaje*.25);
          default -> 0;
        };
        double costoDesayuno = 0;
        if (data.isIncluyeDesayuno()){
             costoDesayuno = data.getNumeroHuespedes()*data.getNumeroNoches()*150;
        }
        double costoEstacionamiento = 0;
        if (data.isIncluyeEstacionamiento()){
            costoEstacionamiento = data.getNumeroNoches()*100;
        }
        double descuento =0;
        if(data.getNumeroNoches()>6){
            descuento = costoHospedaje*.08;
        }
        double subtotal = (costoHospedaje+porTemporada-descuento)+costoDesayuno+costoEstacionamiento;
        double impuestoHospedaje = subtotal *.04;
        double totalPagar = subtotal + impuestoHospedaje;

        return new ResponseHospedaje(
                data.getNombreHuesped(),
                costoHospedaje,
                porTemporada,
                costoDesayuno,
                costoEstacionamiento,
                descuento,
                subtotal,
                impuestoHospedaje,
                totalPagar,
                "Resumen de la cotizacion"
        );
    }
}
