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
    //Atributes
    @Id
    @JsonSerialize(using = NoObjectIdSerializer.class)
    private ObjectId id;
    private String nombre;

    //Indicamos a Morphia que este atributo en realidad otra entidad de morphia y este lo almacenara en otra colección diferente.
    //En este caso Morphia almacenará lo que en MongoDB se conoce como DBRef,una colección de valores nombre y clave.
    //Si la entidad a la que se hace referencia no tiene un atributo con @Id, Morphia lanzará excepción.

    @Property("publicaciones")
    private List<Libro> libros;

    //Cosntructors

    /**
     * Class Constructor
     */
    private Autor() {
        super();
        this.nombre = "";

    }

    /**
     * Class constructor specifying the name and his books.
     *
     * @param nombre String name of the author.
     * @param libros ArrayList list of books writen by the author
     */
    public Autor(String nombre, ArrayList<Libro> libros) {
        this();
        this.nombre = nombre;
        this.libros = libros;
    }

    //Methods

    /**
     * Gets the author objectId from the document
     *
     * @return the author ObjectId
     */
    public ObjectId getId() {
        return id;
    }

    /**
     * Sets the author ObjectId specified by the id parameter.
     *
     * @param id ObjectId updated Objectid for the author.
     */
    public void setId(ObjectId id) {
        this.id = id;
    }

    /**
     * Gets author name.
     *
     * @return String Author name.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Sets the author name specified by the nombre parameter.
     *
     * @param nombre String Updated author name.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Gets a list of books writen by the author.
     *
     * @return Arraylist of books writen by the author.
     */
    public List<Libro> getLibros() {
        return libros;
    }

    /**
     * Sets the list of books wirten by the author.
     *
     * @param libros List of books wich want to add to the writen books list by the author.
     */
    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    /**
     * Shows a entire description for the author.
     *
     * @return
     */
    @Override
    public String toString() {
        return "Autor{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", libros=" + libros +
                '}';
    }
}
