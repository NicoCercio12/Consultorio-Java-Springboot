package com.consultorio.consultorio.controller;
import com.consultorio.consultorio.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.consultorio.consultorio.dto.PacienteRequestDTO;
import com.consultorio.consultorio.dto.PacienteResponseDTO;
import com.consultorio.consultorio.dto.PacienteUpdateDTO;
import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;
    
    //Endpoint para crear/agregar paciente

    @PostMapping
    public ResponseEntity<?> agregarPaciente(@RequestBody PacienteRequestDTO dtoPaciente){
        pacienteService.agregarPaciente(dtoPaciente);

        return ResponseEntity.ok("Paciente agregado correctamente");
    }

    //Endpoint para buscar paciente por id

    @GetMapping("/{id}")
    public PacienteResponseDTO buscarPacientePorId(@PathVariable Long id){
        return pacienteService.buscarPacientePorId(id);
    }

    //Endpoint para actualizar paciente por id
    @PutMapping("/{id}")
    public ResponseEntity<?> modificarPacientePorId(@PathVariable Long id, @RequestBody PacienteUpdateDTO dtoPaciente){
        
        pacienteService.modificarPacientePorId(id, dtoPaciente);
       
        return ResponseEntity.ok("Paciente modificado correctamente");

    }

    //Endpoint para eliminar paciente por id
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarPacientePorId(@PathVariable Long id){
        pacienteService.eliminarPacientePorId(id);

        return ResponseEntity.ok("Paciente eliminado correctamente");
    }

    //Listar Pacientes

    public List<PacienteResponseDTO> listarPacientes(){
        return pacienteService.listar();
    }

}
