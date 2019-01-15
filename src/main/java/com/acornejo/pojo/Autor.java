package com.acornejo.pojo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.*;

import java.util.ArrayList;
import java.util.List;


//Indicamos a Morphia el campo por el cúal queremos indexar(por defecto sera en orden ascendente).
@Indexes({
        @Index(fields = {@Field("id")})
})
//Si no le pasamos el String Morphia toma el nombre de la clase para la colección en la BBDD

@Entity(value = "autores", noClassnameStored = true)
public class Autor {
    @Id
    @JsonSerialize(using = NoObjectIdSerializer.class)
    private ObjectId id;
    private String nombre;

    //Indicamos a Morphia que este atributo en realidad otra entidad de morphia y este lo almacenara en otra colección diferente.
    //En este caso Morphia almacenará lo que en MongoDB se conoce como DBRef,una colección de valores nombre y clave.
    //Si la entidad a la que se hace referencia no tiene un atributo con @Id, Morphia lanzará excepción.

    @Property("publicaciones")
    private List<Libro> libros;

    private Autor() {
        super();
        this.nombre = "";

    }

    public Autor(String nombre) {
        this();
        this.nombre = nombre;
        this.libros = new ArrayList<>();
    }


    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    @Override
    public String toString() {
        return "Autor{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", libros=" + libros +
                '}';
    }
}
