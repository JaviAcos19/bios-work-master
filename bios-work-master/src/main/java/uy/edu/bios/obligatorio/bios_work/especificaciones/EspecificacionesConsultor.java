package uy.edu.bios.obligatorio.bios_work.especificaciones;

import uy.edu.bios.obligatorio.bios_work.dominio.Consultor;

import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;


public class EspecificacionesConsultor {

    public static Specification<Consultor> nombreUsuarioIgualA(String nombreUsuario) {
        
        if (nombreUsuario == null) return null;

        return new Specification<Consultor>() {

            @Override
            public Predicate toPredicate(Root<Consultor> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("nombreUsuario"), nombreUsuario);
            }

        };
    }

    public static Specification<Consultor> nombreUsuarioContiene(String nombreUsuario) {
        
        if (nombreUsuario == null) return null;

        return new Specification<Consultor>() {

            @Override
            public Predicate toPredicate(Root<Consultor> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.get("nombreUsuario"), "%" + nombreUsuario +  "%");
            }

        };
    }

    public static Specification<Consultor> buscar(String criterio) {
        
        if (criterio == null || criterio.isBlank()) return null;

        return Specification.anyOf(nombreUsuarioIgualA(criterio), nombreUsuarioContiene(criterio));
    }

}