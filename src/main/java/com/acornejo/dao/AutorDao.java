package com.acornejo.dao;

import com.acornejo.pojo.Autor;
import com.mongodb.WriteResult;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.UnknownHostException;
import java.util.List;

public class AutorDao {
    private static AutorDao INSTANCE = null;

    @Autowired
    private Datastore datastore;

    private AutorDao() {
        super();
        datastore = MongoConnection.getConnection();
    }

    public static synchronized AutorDao getInstance() throws UnknownHostException {

        if (INSTANCE == null) {

            INSTANCE = new AutorDao();

        }

        return INSTANCE;
    }


    public List<Autor> listarTodos() {

        return datastore.createQuery(Autor.class).asList();

    }


    public Autor obtenerPorId(String id) {
        ObjectId oid = new ObjectId(id);


        return datastore.find(Autor.class).field("_id").equal(oid).get();
    }

    public WriteResult eliminar(Autor f) {

        return datastore.delete(f);

    }

    public Key<Autor> crear(Autor autor) {

        return datastore.save(autor);

    }

    public Key<Autor> modificar(Autor autor) {

        Key<Autor> autorUpdate = null;

        autorUpdate = datastore.merge(autor);


        return autorUpdate;

    }

}
