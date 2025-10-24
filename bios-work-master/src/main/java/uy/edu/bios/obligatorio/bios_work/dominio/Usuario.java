package uy.edu.bios.obligatorio.bios_work.dominio;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Id;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashSet;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


@Entity @Table(name="usuarios") @Inheritance(strategy = InheritanceType.JOINED) // Annotations: JPA
public abstract class Usuario {

    // ATRIBUTOS

    // Nombre de usuario: Cadena de texto de ingreso libre (minimo: 4 caracteres, maximo: 30 caracteres)

    @Id @Column(name="nombre_usuario",nullable=false,unique=true,length=30) // Annotations: JPA
    @NotBlank @Size(min=4,max=30) // Annotations: Bean Validation
    private String nombreUsuario;

    // Clave de acceso: Cadena de texto de ingreso libre (minimo: 8 caracteres, maximo: 12 caracteres)

    @Column(name="clave_acceso",nullable=false,length=255) // Annotations: JPA
    @NotBlank @Size(min=5,max=255) // Annotations: Bean Validation
    private String claveAcceso;

    @ManyToMany
    @JoinTable(joinColumns = { @JoinColumn(name = "usuario_nombre_usuario") }, inverseJoinColumns = { @JoinColumn(name = "rol_nombre_rol") })
    private Set<Rol> roles;

    @Transient
    @JsonIgnore
    private String repetirContrasena;

    @Column(nullable = false, columnDefinition = "BIT DEFAULT 1")
     private boolean activo=true;

    // PROPIEDADES

    // Nombre de usuario

    public String getNombreUsuario() {
        return nombreUsuario;
    }
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    // Clave de acceso

    public String getClaveAcceso() {
        return claveAcceso;
    }

    public void setClaveAcceso(String claveAcceso) {
        this.claveAcceso = claveAcceso;
    }

    public Set<Rol> getRoles() {
        return this.roles;
    }


     public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    // METODOS CONSTRUCTORES

    // Constructor completo

    public Usuario(String nombreUsuario, String claveAcceso, boolean activo) {
        this.nombreUsuario = nombreUsuario;
        this.claveAcceso = claveAcceso;
        this.activo=activo;

        roles = new HashSet<>();
    }

    // Constructor por defecto

    public Usuario() {
        this(null, null, false);
    }

}