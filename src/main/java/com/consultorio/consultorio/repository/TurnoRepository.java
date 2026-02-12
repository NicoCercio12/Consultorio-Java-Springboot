package com.consultorio.consultorio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.consultorio.consultorio.model.Turno;
import java.util.List;
import java.time.LocalDateTime;

import com.consultorio.consultorio.model.Medico;

public interface TurnoRepository extends JpaRepository<Turno, Long> {

    List<Turno> findByFecha(LocalDateTime fechaHora);

    boolean existsByMedicoAndFechaAndHora(Medico medico, LocalDateTime fechaHora);

}
