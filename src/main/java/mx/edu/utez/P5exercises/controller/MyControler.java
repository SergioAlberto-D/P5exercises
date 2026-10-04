package mx.edu.utez.P5exercises.controller;

import mx.edu.utez.P5exercises.controller.dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import mx.edu.utez.P5exercises.service.Servicios;

@RestController 
@CrossOrigin({"*"})
@RequestMapping("/servicios")
public class MyControler {
    private final Servicios services;

    public MyControler(Servicios services) {
        this.services = services;
    }

    @PostMapping("/Cotizar")
    public ResponseEntity<ResponseCotizador> cotizarEnvio(@Valid @RequestBody RequestCotizador payload){
        return ResponseEntity.status(200).body(services.CotizarEnvios(payload));
    }
    @PostMapping("/cotizar-vehiculo")
    public ResponseEntity<ResponseVehiculos> cotizarVehiculos(@Valid @RequestBody RequestVehiculos payload){
        return ResponseEntity.status(200).body(services.CotizarVehiculos(payload));
    }
    @PostMapping("/cotizar-hospedaje")
    public ResponseEntity<ResponseHospedaje> cotizarHospedaje(@Valid @RequestBody RequestHospedaje payload) {
        return ResponseEntity.status(200).body(services.CotizarHospedaje(payload));
    }
    
}
