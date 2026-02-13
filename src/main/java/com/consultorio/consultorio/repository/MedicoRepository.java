package com.consultorio.consultorio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.consultorio.consultorio.model.Medico;
import java.util.Optional;
import java.util.List;

public interface MedicoRepository extends JpaRepository<Medico, Long> {

    Optional<Medico> findByMatricula(String matricula); //Busca por matricula
    Optional<Medico> findByDni(Long dni); //Busca por dni
    List<Medico> findByEspecialidad(String especialidad); //Busca por especialida
    

}
