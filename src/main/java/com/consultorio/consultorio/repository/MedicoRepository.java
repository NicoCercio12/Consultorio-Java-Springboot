package com.consultorio.consultorio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.consultorio.consultorio.model.Medico;
import java.util.Optional;

public interface MedicoRepository extends JpaRepository<Medico, Long> {

    Optional<Medico> findfindByMatricula(String matricula); //Busca por matricula

}
