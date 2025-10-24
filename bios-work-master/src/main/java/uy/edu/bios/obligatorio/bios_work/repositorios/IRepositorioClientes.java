package uy.edu.bios.obligatorio.bios_work.repositorios;

import uy.edu.bios.obligatorio.bios_work.dominio.Cliente;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IRepositorioClientes extends JpaRepository<Cliente,String> {
    @Override
    @EntityGraph(type = EntityGraphType.LOAD, attributePaths = {"roles"})
    List<Cliente> findAll();

    @Override
    @EntityGraph(type = EntityGraphType.LOAD, attributePaths = {"roles"})
    Optional<Cliente> findById(String id);
}