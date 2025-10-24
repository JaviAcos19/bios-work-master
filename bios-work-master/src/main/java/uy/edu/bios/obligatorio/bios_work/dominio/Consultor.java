package uy.edu.bios.obligatorio.bios_work.dominio;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.PrimaryKeyJoinColumn;


@Entity @Table(name="consultores") @PrimaryKeyJoinColumn(name="nombre_usuario")  // Annotations: JPA
public class Consultor extends Usuario{

    // METODOS CONSTRUCTORES

    // Constructor completo

    public Consultor(String nombreUsuario, String claveAcceso, boolean activo) {
        super(nombreUsuario, claveAcceso, activo);
    }

    // Constructor por defecto

    public Consultor() {
        this(null, null, false);
    }

}