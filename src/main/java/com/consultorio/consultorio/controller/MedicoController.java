package com.consultorio.consultorio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.consultorio.consultorio.service.MedicoService;
import com.consultorio.consultorio.model.Medico;


@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @PostMapping
    public ResponseEntity<?> crearMedico(@RequestBody Medico medico) {
        medicoService.agregarMedico(medico);
        return ResponseEntity.ok("Médico creado exitosamente");
    }

    
}
