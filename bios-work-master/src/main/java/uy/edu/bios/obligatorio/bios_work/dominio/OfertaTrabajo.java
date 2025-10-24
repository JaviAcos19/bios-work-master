package uy.edu.bios.obligatorio.bios_work.dominio;

import java.util.Date;
import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.JoinColumn;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;


@Entity @Table(name="ofertas_trabajo") // Annotations: JPA
public class OfertaTrabajo {

    // ATRIBUTOS

    // ID: Numero entero

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) @Column(name="id",nullable=false,unique=true)  // Annotations: JPA
    @NotNull // Annotations: Bean Validation
    private Long id;

    // Fecha de publicacion: fecha actual

    @Column(name="fecha_publicacion",nullable=false) // Annotations: JPA
    @NotNull // Annotations: Bean Validation
    @DateTimeFormat(pattern="yyyy-MM-dd") // Annotations: Format
    private Date fechaPublicacion;

    // Fecha de cierre: fecha posterior a la actual

    @Column(name="fecha_cierre",nullable=false) // Annotations: JPA
    @NotNull @Future // Annotations: Bean Validation
    @DateTimeFormat(pattern="yyyy-MM-dd") // Annotations: Format
    private Date fechaCierre;

    // Titulo: Cadena de texto de ingreso libre (maximo: 100 caracteres)
    
    @Column(name="titulo",nullable=false,length=100) // Annotations: JPA    
    @NotBlank @Size(max=100) // Annotations: Bean Validation
    private String titulo;
    
    // Area: Objeto perteneciente a la clase Area (existente en el sistema)

    @ManyToOne(optional=false) @JoinColumn(name="area",nullable=false) // Annotations: JPA
    @NotNull // Annotations: Bean Validation
    private Area area;

    // Descripcion detallada: Cadena de texto de ingreso libre (maximo: 500 caracteres)
    
    @Column(name="descripcion_detallada",nullable=false,length=500) // Annotations: JPA  
    @NotBlank @Size(max=500) // Annotations: Bean Validation    
    private String descripcionDetallada;

    // Puestos vacantes: Numero entero (valores posibles: desde 1 hasta 100)

    @Column(name="puestos_vacantes",nullable=false) // Annotations: JPA
    @NotNull @Min(value=1) @Max(value=100) // Annotations: Bean Validation
    private Integer puestosVacantes;

    // Cliente: Objeto perteneciente a la clase Cliente (existente en el sistema)

    @ManyToOne(optional=false) @JoinColumn(name="cliente",nullable=false) // Annotations: JPA
    @NotNull // Annotations: Bean Validation
    private Cliente cliente;

    // Postulaciones: Lista de objetos pertenecientes a la clase Postulacion (existente en el sistema)

    @OneToMany(mappedBy="ofertaTrabajo",cascade=CascadeType.ALL,orphanRemoval=true)
    private List<Postulacion> postulaciones;

    // PROPIEDADES

    // ID

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Fecha de publicacion

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    // Fecha de cierre

    public Date getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(Date fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    // Titulo

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    // Area

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    // Descripcion detallada

    public String getDescripcionDetallada() {
        return descripcionDetallada;
    }

    public void setDescripcionDetallada(String descripcionDetallada) {
        this.descripcionDetallada = descripcionDetallada;
    }

    // Puestos vacantes
    
    public Integer getPuestosVacantes() {
        return puestosVacantes;
    }
    public void setPuestosVacantes(Integer puestosVacantes) {
        this.puestosVacantes = puestosVacantes;
    }

    // Cliente
    
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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

    public OfertaTrabajo(Long id, Date fechaPublicacion, Date fechaCierre, String titulo, Area area, String descripcionDetallada, Integer puestosVacantes, Cliente cliente, List<Postulacion> postulaciones) {
        this.id = id;
        this.fechaPublicacion = fechaPublicacion;
        this.fechaCierre = fechaCierre;
        this.titulo = titulo;
        this.area = area;
        this.descripcionDetallada = descripcionDetallada;
        this.puestosVacantes = puestosVacantes;
        this.cliente = cliente;
        this.postulaciones = postulaciones;
    }

    // Constructor comun

    public OfertaTrabajo(Long id, Date fechaPublicacion, Date fechaCierre, String titulo, Area area, String descripcionDetallada, Integer puestosVacantes, Cliente cliente) {
        this.id = id;
        this.fechaPublicacion = fechaPublicacion;
        this.fechaCierre = fechaCierre;
        this.titulo = titulo;
        this.area = area;
        this.descripcionDetallada = descripcionDetallada;
        this.puestosVacantes = puestosVacantes;
        this.cliente = cliente;
    }

    // Constructor por defecto

    public OfertaTrabajo() {
        this(null, null, null, null, null, null, null, null,null);
    }

}