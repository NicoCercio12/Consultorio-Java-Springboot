package com.consultorio.consultorio.controller;

import java.util.*;
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
import com.consultorio.consultorio.service.MedicoService;
import com.consultorio.consultorio.dto.MedicoRequestDTO;
import com.consultorio.consultorio.dto.MedicoResponseDTO;


//Endpoints

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    //Endpoint para crear/agregar medicos

    @PostMapping
    public ResponseEntity<?> agregarMedico(@RequestBody MedicoRequestDTO dtoMedico){
        medicoService.agregarMedico(dtoMedico);
        return ResponseEntity.ok("Medico agregado correctamente");
    }

    //Endpoint para buscar medico por id

    @GetMapping("/{id}")
    public MedicoResponseDTO buscarMedicoPorId(@PathVariable Long id){
        return medicoService.buscarMedicoPorId(id);
    }

    //Endpoint para modificar medico por id

    @PutMapping("/{id}")
    public ResponseEntity<?> modificarMedicoPorId(@PathVariable Long id, @RequestBody MedicoRequestDTO dtoMedico){
        medicoService.modificarMedicoPorId(dtoMedico, id);

        return ResponseEntity.ok("Medico modificado correctamente");
    }

    //Endpoint para eliminar medico por id

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarMedicoPorId(@PathVariable Long id){
        medicoService.eliminarMedicoPorId(id);

        return ResponseEntity.ok("Medico eliminado correctamente");
    }
    //Endpoint para listar medicos

    @GetMapping
    public List<MedicoResponseDTO> listarMedicos(){
        return medicoService.listar();
    }






    



    
}
