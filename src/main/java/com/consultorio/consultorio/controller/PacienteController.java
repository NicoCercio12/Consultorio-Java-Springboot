package com.consultorio.consultorio.controller;
import com.consultorio.consultorio.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.consultorio.consultorio.model.Paciente;
import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;
    
    @PostMapping
    public ResponseEntity<?> crearPaciente(@RequestBody Paciente paciente) {
        pacienteService.agregarPaciente(paciente);
        return ResponseEntity.ok("Paciente creado correctamente");
    }

    @GetMapping
    public List<Paciente> listarPacientes(){
        return pacienteService.listar();

    }




    
}
