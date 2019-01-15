package com.acornejo.dao;

import com.acornejo.pojo.Libro;
import com.mongodb.WriteResult;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.query.UpdateResults;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class LibroDao {
    private static LibroDao INSTANCE = null;
    private List<Libro> libros = null;
    private static LibroDao libroDao = null;
    @Autowired
    private Datastore datastore;
    private Key<Libro> libroId = null;


    private LibroDao() {
        super();
        datastore = MongoConnection.getConnection();
    }

    public static synchronized LibroDao getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LibroDao();
        }
        return INSTANCE;
    }

    public List<Libro> getAll() {


        return datastore.createQuery(Libro.class).asList();

    }

    public Libro getById(String id) {
        ObjectId oid = new ObjectId(id);


        return datastore.find(Libro.class).field("_id").equal(oid).get();
    }

    public Libro insert(Libro libro) {
        Libro result = new Libro();

        libroId = datastore.save(libro);

        if (libroId.getId() != null)
            result = libro;

        return result;
    }


    public UpdateResults update(Libro libro) {

        return datastore.update(
                datastore.find(Libro.class).filter("_id", libro.getId()),
                datastore.createUpdateOperations(Libro.class)
                        .set("titulo", libro.getTitulo()));
    }

    public WriteResult delete(String id) {
        datastore = MongoConnection.getConnection();
        Libro libro = getById(id);
        return datastore.delete(libro);
    }


}
