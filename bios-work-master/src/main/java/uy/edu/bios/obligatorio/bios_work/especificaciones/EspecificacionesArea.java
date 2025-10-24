package uy.edu.bios.obligatorio.bios_work.especificaciones;

import org.springframework.data.jpa.domain.Specification;

import uy.edu.bios.obligatorio.bios_work.dominio.Area;

public class EspecificacionesArea {
     public static Specification<Area> nombreIgualA(String nombre) {
        if (nombre == null) return null;

        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("nombre"), nombre);
    }


    public static Specification<Area> nombreContiene(String nombre) {
        if (nombre == null) return null;

        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("nombre")),
                    "%" + nombre.toLowerCase() + "%"
                );
    }


    public static Specification<Area> buscar(String criterio) {
        if (criterio == null || criterio.isBlank()) return null;

        return Specification.anyOf(
                nombreIgualA(criterio),
                nombreContiene(criterio)
        );
    }

}