package com.consultorio.consultorio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.consultorio.consultorio.service.TurnoService;
import com.consultorio.consultorio.dto.TurnoRequestDTO;
import com.consultorio.consultorio.dto.TurnoResponseDTO;
import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnoController {

    @Autowired
    private TurnoService turnoService;
    
    //Endpoint para crear/agregar turnos
    @PostMapping
    public ResponseEntity<?> agregarTurno(@RequestBody TurnoRequestDTO dtoTurno){
        turnoService.agregarTurno(dtoTurno);

        return ResponseEntity.ok("Turno creado correctamente");
    }

    //Endpoint para buscar turno por id
    @GetMapping("/{id}")
    public TurnoResponseDTO buscarTurnoPorId(@PathVariable Long id){
        return turnoService.buscarTurnoPorId(id);
    }

    //Endpoint para actualizar turno por id
    @PatchMapping("/{id}")
    public ResponseEntity<?> modificarTurnoPorId(@PathVariable Long id, @RequestBody TurnoRequestDTO dtoTurno){
        turnoService.modificarTurnoPorId(id, dtoTurno);

        return ResponseEntity.ok("Turno modificado correctamente");
    }

    //Endpoint para eliminar turno por id

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarTurnoPorId(@PathVariable Long id){
        turnoService.eliminarTurnoPorId(id);

        return ResponseEntity.ok("Turno eliminado correctamente");
    }

    //Endpoint para listar turnos 

    @GetMapping
    public List<TurnoResponseDTO> listarTurnos(){
        return turnoService.listar();
    }




    

}
   