package com.consultorio.consultorio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.consultorio.consultorio.model.Paciente;
import java.util.Optional;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    Optional<Paciente> findfindByDNI(long dni); //Es opcional, pero me sirve para buscar por dni

}
