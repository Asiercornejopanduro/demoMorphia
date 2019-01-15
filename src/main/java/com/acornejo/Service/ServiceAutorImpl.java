package com.acornejo.Service;

import com.acornejo.dao.AutorDao;
import com.acornejo.pojo.Autor;
import com.mongodb.WriteResult;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.UnknownHostException;
import java.util.List;

public class ServiceAutorImpl implements IServiceAutor {

    @Autowired
    private Datastore datastore;

    private static AutorDao autorDao = null;
    private static ServiceAutorImpl INSTANCE = null;

    private ServiceAutorImpl() throws UnknownHostException {
        super();
        autorDao = AutorDao.getInstance();
    }

    public static synchronized ServiceAutorImpl getInstance() throws UnknownHostException {
        if (INSTANCE == null) {
            INSTANCE = new ServiceAutorImpl();
        }
        return INSTANCE;
    }

    @Override
    public Key<Autor> create(Autor autor) {
        return autorDao.crear(autor);
    }

    @Override
    public Autor getById(String id) {
        return autorDao.obtenerPorId(id);
    }

    @Override
    public Key<Autor> update(Autor autor) {
        return autorDao.modificar(autor);
    }


    @Override
    public WriteResult delete(Autor autor) {
        return autorDao.eliminar(autor);
    }

    @Override
    public List<Autor> getAll() {
        return autorDao.listarTodos();
    }

}
