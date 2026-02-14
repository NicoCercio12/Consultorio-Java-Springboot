package com.consultorio.consultorio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.consultorio.consultorio.service.PacienteService;
import com.consultorio.consultorio.service.TurnoService;
import com.consultorio.consultorio.service.MedicoService;
import com.consultorio.consultorio.model.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnoController {

    @Autowired
    private TurnoService turnoService;

    @Autowired
    private PacienteService pacienteService;

    @Autowired 
    private MedicoService medicoService;

}
   