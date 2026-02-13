package com.consultorio.consultorio.controller;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.consultorio.consultorio.service.MedicoService;
import com.consultorio.consultorio.model.Medico;

//Endpoints

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

    @GetMapping("/{dni}")
    public Medico buscarMedicoPorDni(@PathVariable Long dni){
        return medicoService.buscarMedicoPorDni(dni);
    }

    @GetMapping
    public List<Medico> listarMedicos(){
        return medicoService.listar();
    }

    @GetMapping("/especialidad/{especialidad}") //Lista por especialidad
    public List<Medico> listarPorEspecialidad(@PathVariable String especialidad){
        return medicoService.listarPorEspecialidad(especialidad);
    }
    
    @PutMapping("/medicos/{matricula}") //Actualizar por matricula
    public Medico actualizarMedicoPorMatricula(@PathVariable String matricula, Medico medico){
        return medicoService.actualizarMedicoPorMatricula(matricula, medico);
    }

    @PutMapping("/medicos/{dni}") //Actualizar por dni
    public Medico actualizarMedicoPorDni(@PathVariable Long dni, Medico medico){
        return medicoService.actualizarMedicoPorDni(dni, medico);
    }

    @PutMapping("/medicos/{id}") //Actualizar por id
    public Medico actualizarMedicoPorId(@PathVariable Long id, Medico medico){
        return medicoService.actualizarMedicoPorId(id, medico);
    }




    
}
