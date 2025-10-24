package uy.edu.bios.obligatorio.bios_work.dominio;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


@Entity @Table(name="areas") // Annotations: JPA
public class Area {

    // ATRIBUTOS

    // ID: Numero entero

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name="id",nullable=false,unique=true) // Annotations: JPA
    @NotNull // Annotations: Bean Validation
    private Long id;

    // Nombre: Cadena de texto de ingreso libre (maximo: 30 caracteres)

    @Column(name="nombre",nullable=false,unique=true,length=30) // Annotations: JPA
    @NotBlank @Size(max=30) // Annotations: Bean Validation
    private String nombre;

   
    // PROPIEDADES

    // ID

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    

    // Nombre
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
   
    // CONSTRUCTORES

    // Constructor completo

    public Area(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;        
    }
    
    // Constructor común

    public Area(String nombre) {
        this.nombre = nombre;        
    }

    // Constructor por defecto

    public Area() {
        this(null,null);
    }

}