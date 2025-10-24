package uy.edu.bios.obligatorio.bios_work.dominio;

import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;

import org.hibernate.validator.constraints.URL;


@Entity @Table(name="clientes") @PrimaryKeyJoinColumn(name="nombre_usuario") // Annotations: JPA
public class Cliente extends Usuario{

    // ATRIBUTOS

    // RUT: Cadena de texto compuesta por 12 digitos
 
    @Column(name="rut",nullable=false,unique=true,length=12) // Annotations: JPA
    @NotBlank @Size(min=12,max=12) @Pattern(regexp="^\\d+$") // Annotations: Bean Validation
    private String rut;
    
    // Nombre: Cadena de texto de ingreso libre (maximo: 50 caracteres)
    
    @Column(name="nombre",nullable=false,unique=true,length=50) // Annotations: JPA
    @NotBlank @Size(max=50) // Annotations: Bean Validation
    private String nombre;

    // Sitio Web: Cadena de texto con formato de URL (de caracter opcional)
    
    @Column(name="sitio_web",nullable=true,length=100) // Annotations: JPA
    @URL // Annotations: Bean Validation
    private String sitioWeb;

    // Disponible: Valor de tipo lógico
    
    @Column(name="disponible",nullable=false) // Annotations: JPA
    private Boolean disponible;

    // Ofertas de Trabajo: Lista de objetos perteneciente a la clase OfertaTrabajo (existentes en el sistema)

    @OneToMany(mappedBy="cliente",cascade=CascadeType.ALL,orphanRemoval=true) // Annotations: JPA
    private List<OfertaTrabajo> ofertasTrabajo;

    // PROPIEDADES

    // RUT

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    // Nombre

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Sitio Web

    public String getSitioWeb() {
        return sitioWeb;
    }

    public void setSitioWeb(String sitioWeb) {
        this.sitioWeb = sitioWeb;
    }

    // Disponible

    public Boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }

    // Ofertas de Trabajo
    
    public List<OfertaTrabajo> getOfertasTrabajo() {
        return ofertasTrabajo;
    }

    public void setOfertasTrabajo(List<OfertaTrabajo> ofertasTrabajo) {
        this.ofertasTrabajo = ofertasTrabajo;
    }

    // METODOS CONSTRUCTORES

    // Constructor completo

    public Cliente(String nombreUsuario, String claveAcceso, String rut, String nombre, String sitioWeb, Boolean disponible,boolean activo, List<OfertaTrabajo> ofertasTrabajo) {
        super(nombreUsuario, claveAcceso, activo);
        this.rut = rut;
        this.nombre = nombre;
        this.sitioWeb = sitioWeb;
        this.disponible = disponible;
        this.ofertasTrabajo = ofertasTrabajo;
    }

    // Constructores comunes

    public Cliente(String nombreUsuario, String claveAcceso, String rut, String nombre, Boolean disponible, boolean activo) {
        super(nombreUsuario, claveAcceso, activo);
        this.rut = rut;
        this.nombre = nombre;
        this.disponible = disponible;
    }

    public Cliente(String nombreUsuario, String claveAcceso, String rut, String nombre, String sitioWeb, Boolean disponible, boolean activo) {
        super(nombreUsuario, claveAcceso, activo);
        this.rut = rut;
        this.nombre = nombre;
        this.sitioWeb = sitioWeb;
        this.disponible = disponible;
    }
    
    public Cliente(String nombreUsuario, String claveAcceso, String rut, String nombre, Boolean disponible,boolean activo, List<OfertaTrabajo> ofertasTrabajo) {
        super(nombreUsuario, claveAcceso, activo);
        this.rut = rut;
        this.nombre = nombre;
        this.disponible = disponible;
        this.ofertasTrabajo = ofertasTrabajo;
    }

    // Constructor por defecto

    public Cliente() {
        this(null,null,null,null,null,null,false, null);
    }

}