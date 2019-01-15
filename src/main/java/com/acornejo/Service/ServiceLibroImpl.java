package com.acornejo.Service;

import com.acornejo.dao.LibroDao;
import com.acornejo.pojo.Libro;
import com.mongodb.WriteResult;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.UnknownHostException;
import java.util.List;

public class ServiceLibroImpl implements IServiceLibro {

    @Autowired
    private Datastore datastore;

    private static  LibroDao libroDao=null;
    private static ServiceLibroImpl INSTANCE = null;

    private ServiceLibroImpl() {
        super();
        libroDao = LibroDao.getInstance();
    }

    public static synchronized ServiceLibroImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ServiceLibroImpl();
        }
        return INSTANCE;
    }



    @Override
    public Libro create(Libro libro) {

        return libroDao.insert(libro);
    }

    @Override
    public Libro getById(String id) {

        return libroDao.getById(id);
    }

    @Override
    public List<Libro> getAll()  {
        return libroDao.getAll();
    }

    @Override
    public UpdateResults update(Libro libro) {
        return libroDao.update(libro);

    }

    @Override
    public WriteResult delete(String id) {
        return libroDao.delete(id);

    }

    @Override
    public UpdateOperations<Libro> createOperations() {
        return datastore.createUpdateOperations(Libro.class);
    }
}
