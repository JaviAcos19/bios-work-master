package uy.edu.bios.obligatorio.bios_work.especificaciones;

import uy.edu.bios.obligatorio.bios_work.dominio.OfertaTrabajo;

import java.time.LocalDate;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class EspecificacionesOfertaTrabajo {

    public static Specification<OfertaTrabajo> fechaPublicacionMenorAFechaActual() {

        LocalDate fechaActual = LocalDate.now();

        return new Specification<OfertaTrabajo>() {

            @Override
            public Predicate toPredicate(Root<OfertaTrabajo> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.lessThanOrEqualTo(root.get("fecha_publicacion"), fechaActual);
            }           
        };
    }

    public static Specification<OfertaTrabajo> fechaCierreMayorAFechaActual() {

        LocalDate fechaActual = LocalDate.now();

        return new Specification<OfertaTrabajo>() {

            @Override
            public Predicate toPredicate(Root<OfertaTrabajo> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.greaterThanOrEqualTo(root.get("fecha_cierre"), fechaActual);
            }           
        };
    }

    public static Specification<OfertaTrabajo> listarVigentes() {
        return Specification.allOf(fechaPublicacionMenorAFechaActual(), fechaCierreMayorAFechaActual());
    }

}