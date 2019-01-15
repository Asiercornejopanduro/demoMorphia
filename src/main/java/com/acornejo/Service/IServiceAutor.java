package com.acornejo.Service;

import com.acornejo.pojo.Autor;
import com.mongodb.WriteResult;
import org.mongodb.morphia.Key;

import java.util.List;

public interface IServiceAutor {

    Key<Autor> create(Autor autor);

    Autor getById(String id);

    Key<Autor> update(Autor autor);

    WriteResult delete(Autor autor);

    List<Autor> getAll();


}
