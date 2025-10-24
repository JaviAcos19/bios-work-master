package uy.edu.bios.obligatorio.bios_work.dominio;

import java.util.Date;
import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Past;
import org.springframework.format.annotation.DateTimeFormat;


@Entity @Table(name="postulantes") @PrimaryKeyJoinColumn(name="nombre_usuario") // Annotations: JPA
public class Postulante extends Usuario {

    // ATRIBUTOS

    // Cedula de identidad: Numero entero (valores posibles: desde 1000000 hasta 99999999)

    @Column(name="cedula_identidad",nullable=false,unique=true) // Annotations: JPA
    @NotNull @Min(value=1000000) @Max(value=99999999) // Annotations: Bean Validation
    private Integer cedulaIdentidad;
    
    // Nombre completo: Cadena de texto de ingreso libre (maximo: 50 caracteres)
    
    @Column(name="nombre_completo",nullable=false,length=50) // Annotations: JPA
    @NotBlank @Size(max=50) // Annotations: Bean Validation
    private String nombreCompleto;

    // Fecha de nacimiento: Fecha menor a la actual

    @Column(name="fecha_nacimiento",nullable=false) // Annotations: JPA
    @NotNull @Past // Annotations: Bean Validation
    @DateTimeFormat(pattern="yyyy-MM-dd") // Annotations: Format
    private Date fechaNacimiento;

    // Departamento de residencia: Cadena de texto perteneciente a... (maximo: 15 caracteres)
    
    @Column(name="departamento_residencia",nullable=false,length=15) // Annotations: JPA
    @NotBlank @Size(max=15) // Annotations: Bean Validation
    private String departamentoResidencia;

    // Fotografia: Valor de tipo lógico

    @Column(name="fotografia_disponible",nullable=false) // Annotations: JPA
    private Boolean fotografiaDisponible;

    // Curriculum vitae: Valor de tipo lógico

    @Column(name="cv_disponible",nullable=false) // Annotations: JPA
    private Boolean cvDisponible;

    // Postulaciones: Lista de objetos perteneciente a la clase Postulacion (existentes en el sistema)

    @OneToMany(mappedBy="postulante",cascade =CascadeType.ALL,orphanRemoval = true)
    private List<Postulacion> postulaciones;

    // PROPIEDADES

    // Cedula de identidad

    public Integer getCedulaIdentidad() {
        return cedulaIdentidad;
    }

    public void setCedulaIdentidad(Integer cedulaIdentidad) {
        this.cedulaIdentidad = cedulaIdentidad;
    }

    // Nombre completo

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    // Fecha de nacimiento

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    // Departamento de residencia

    public String getDepartamentoResidencia() {
        return departamentoResidencia;
    }

    public void setDepartamentoResidencia(String departamentoResidencia) {
        this.departamentoResidencia = departamentoResidencia;
    }

    // Fotografia

    public Boolean getFotografiaDisponible() {
        return fotografiaDisponible;
    }

    public void setFotografiaDisponible(Boolean fotografiaDisponible) {
        this.fotografiaDisponible = fotografiaDisponible;
    }

    // Curriculum vitae

    public Boolean getCvDisponible() {
        return cvDisponible;
    }

    public void setCvDisponible(Boolean cvDisponible) {
        this.cvDisponible = cvDisponible;
    }

    // Postulaciones

    public List<Postulacion> getPostulaciones() {
        return postulaciones;
    }

    public void setPostulaciones(List<Postulacion> postulaciones) {
        this.postulaciones = postulaciones;
    }

    // METODOS CONSTRUCTORES

    // Constructor completo

    public Postulante(String nombreUsuario, String claveAcceso, Integer cedulaIdentidad, String nombreCompleto, Date fechaNacimiento, String departamentoResidencia, Boolean fotografiaDisponible, Boolean cvDisponible,boolean activo, List<Postulacion> postulaciones) {
        super(nombreUsuario, claveAcceso, activo);
        this.cedulaIdentidad = cedulaIdentidad;
        this.nombreCompleto = nombreCompleto;
        this.fechaNacimiento = fechaNacimiento;
        this.departamentoResidencia = departamentoResidencia;
        this.fotografiaDisponible = fotografiaDisponible;
        this.cvDisponible = cvDisponible;
        this.postulaciones = postulaciones;
    }

    // Constructor comun

        public Postulante(String nombreUsuario, String claveAcceso, Integer cedulaIdentidad, String nombreCompleto, Date fechaNacimiento, String departamentoResidencia, Boolean fotografiaDisponible, Boolean cvDisponible, boolean activo) {
        super(nombreUsuario, claveAcceso, activo);
        this.cedulaIdentidad = cedulaIdentidad;
        this.nombreCompleto = nombreCompleto;
        this.fechaNacimiento = fechaNacimiento;
        this.departamentoResidencia = departamentoResidencia;
        this.fotografiaDisponible = fotografiaDisponible;
        this.cvDisponible = cvDisponible;
    }

    // Constructor por defecto
    
    public Postulante() {
        this(null, null, null, null, null, null, null, null, false, null);
    }

}