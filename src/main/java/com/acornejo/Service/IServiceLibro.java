package com.acornejo.Service;

import com.mongodb.WriteResult;
import org.mongodb.morphia.query.UpdateOperations;
import com.acornejo.pojo.Libro;
import org.mongodb.morphia.query.UpdateResults;

import java.net.UnknownHostException;
import java.util.List;

public interface IServiceLibro {
     Libro  create(Libro libro) throws UnknownHostException;

     Libro getById(String id) throws UnknownHostException;

     List<Libro> getAll() throws UnknownHostException;

     UpdateResults update(Libro libro) throws UnknownHostException;

     WriteResult delete(String id) throws UnknownHostException;

     UpdateOperations<Libro> createOperations();

}
