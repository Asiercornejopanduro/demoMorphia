package com.acornejo.pojo;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;

import java.util.Date;

//Si no le pasamos el String Morphia toma el nombre de la clase para la colección en la BBDD

@Entity(noClassnameStored = true, value = "libros")
public class Libro {
    @Id
    @JsonSerialize(using = NoObjectIdSerializer.class)
    private ObjectId id;
    private ObjectId autorId;
    private String titulo;
    //Le indicamos a Morphia que este atributo se llame Publicado en la DDBB en lugar de coger el nombre del atributo.
    @Property("publicado")
    private Date fechaPublicacion;

    /**
     * Class constructor.
     */
    public Libro() {
        super();
    }

    /**
     * Class constructor specifying the title and the publishing date.
     *
     * @param titulo           String title for the book.
     * @param fechaPublicacion Date date of publishing of the book.
     */
    public Libro(String titulo, Date fechaPublicacion) {
        this();
        this.titulo = titulo;
        this.fechaPublicacion = fechaPublicacion;

    }

    /**
     * Gets the book objectId from the document
     *
     * @return the book ObjectId
     */
    public ObjectId getId() {
        return id;
    }

    /**
     * Sets the book ObjectId specified by the id parameter.
     *
     * @param id Objectid updated Objectid for the book.
     */
    public void setId(ObjectId id) {
        this.id = id;
    }

    /**
     * Gets the book title.
     *
     * @return String the book title.
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Sete a new title for the book specified by the titulo parameter.
     *
     * @param titulo String updated title for the book.
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Gets the book publishing date.
     *
     * @return Date publishing date of the book.
     */
    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    /**
     * Sets a new publishing date to the book.
     *
     * @param fechaPublicacion Date updated publishing date.
     */
    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    /**
     * Gets the author id who wrotes the book.
     *
     * @return ObjectId writer ObjectId.
     */
    public ObjectId getAutorId() {
        return autorId;
    }

    /**
     * Sets a new author id specified by the autorId parameter.
     *
     * @param autorId ObjectId updated author id.
     */
    public void setAutorId(ObjectId autorId) {
        this.autorId = autorId;
    }

    /**
     * Class constructor specifying the book Objectid,author ObjectId,book title and book publishing date.
     *
     * @param id               ObejctId book ObjectId.
     * @param autorId          objectId author ObjectId.
     * @param titulo           String book title.
     * @param fechaPublicacion Date publishing date.
     */
    public Libro(ObjectId id, ObjectId autorId, String titulo, Date fechaPublicacion) {
        this.id = id;
        this.autorId = autorId;
        this.titulo = titulo;
        this.fechaPublicacion = fechaPublicacion;
    }
}
