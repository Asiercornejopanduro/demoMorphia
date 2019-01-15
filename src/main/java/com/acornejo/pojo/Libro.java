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

    public Libro() {
        super();
    }

    public Libro(String titulo, Date fechaPublicacion) {
        this();
        this.titulo = titulo;
        this.fechaPublicacion = fechaPublicacion;

    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public ObjectId getAutorId() {
        return autorId;
    }

    public void setAutorId(ObjectId autorId) {
        this.autorId = autorId;
    }

    public Libro(ObjectId id, ObjectId autorId, String titulo, Date fechaPublicacion) {
        this.id = id;
        this.autorId = autorId;
        this.titulo = titulo;
        this.fechaPublicacion = fechaPublicacion;
    }
}
