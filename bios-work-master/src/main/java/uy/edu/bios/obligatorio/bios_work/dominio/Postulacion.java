package uy.edu.bios.obligatorio.bios_work.dominio;

import java.util.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;


@Entity @Table(name="postulaciones") // Annotations: JPA
public class Postulacion {

    // ATRIBUTOS

    // ID: Numero entero

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) @Column(name="id",nullable=false,unique=true)  // Annotations: JPA
    @NotNull // Annotations: Bean Validation
    private Long id;
    
    // Postulante: Objeto perteneciente a la clase Postulante (existente en el sistema)

    @NotNull // Annotations: Bean Validation
    @ManyToOne(optional=false) @JoinColumn(name="postulante",nullable=false) // Annotations: JPA
    private Postulante postulante;

    // Oferta de trabajo: Objeto perteneciente a la clase OfertaTrabajo (existente en el sistema)
    
    @NotNull // Annotations: Bean Validation
    @ManyToOne(optional=false) @JoinColumn(name="oferta_trabajo",nullable=false) // Annotations: JPA
    private OfertaTrabajo ofertaTrabajo;

    // Fecha de postulacion: fecha actual

    @NotNull // Annotations: Bean Validation
    @Column(name="fecha_postulacion",nullable=false) // Annotations: JPA
    @DateTimeFormat(pattern="yyyy-MM-dd") // Annotations: Format
    private Date fechaPostulacion;

    // PROPIEDADES

    // ID

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Postulante

    public Postulante getPostulante() {
        return postulante;
    }

    public void setPostulante(Postulante postulante) {
        this.postulante = postulante;
    }

    // Oferta de trabajo

    public OfertaTrabajo getOfertaTrabajo() {
        return ofertaTrabajo;
    }

    public void setOfertaTrabajo(OfertaTrabajo ofertaTrabajo) {
        this.ofertaTrabajo = ofertaTrabajo;
    }

    // Fecha de postulacion
    
    public Date getFechaPostulacion() {
        return fechaPostulacion;
    }

    public void setFechaPostulacion(Date fechaPostulacion) {
        this.fechaPostulacion = fechaPostulacion;
    }

    // METODOS CONSTRUCTORES

    // Constructor completo

    public Postulacion(Long id, Postulante postulante, OfertaTrabajo ofertaTrabajo, Date fechaPostulacion) {
        this.id = id;
        this.postulante = postulante;
        this.ofertaTrabajo = ofertaTrabajo;
        this.fechaPostulacion = fechaPostulacion;
    }
    
    // Constructor por defecto

    public Postulacion() {
        this(null,null, null, null);
    }

}