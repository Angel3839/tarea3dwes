package com.Angelvf3839.tarea3dwesangel.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Angelvf3839.tarea3dwesangel.modelo.Ejemplar;
import com.Angelvf3839.tarea3dwesangel.modelo.Persona;

@Repository
public interface EjemplarRepository extends JpaRepository <Ejemplar, Long>{

}