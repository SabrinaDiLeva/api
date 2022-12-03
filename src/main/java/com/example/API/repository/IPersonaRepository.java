package com.example.API.repository;
import com.example.API.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
public interface IPersonaRepository extends JpaRepository<Persona,Long>{

}