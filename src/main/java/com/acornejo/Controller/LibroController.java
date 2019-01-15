package com.acornejo.Controller;

import com.acornejo.Service.ServiceLibroImpl;
import com.acornejo.pojo.Libro;
import com.mongodb.WriteResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Controller to manage the libros collection
 */
@Controller
@RequestMapping("/libros")
public class LibroController {
    private ServiceLibroImpl libroService;


    public LibroController() {

        super();

        libroService = ServiceLibroImpl.getInstance();

    }

    /**
     * Gets a list of authors storaged on the "libros" collection from "biblioteca" database.
     *
     * @return ResponseEntity object with all the libros documents on the collection JSON formatted, and the http status code,Ok if the operation was succesfully or NOT FOUND if not.
     */
    @RequestMapping(value = "/listado", method = RequestMethod.GET)
    public ResponseEntity<Object> getAll() {
        List<Libro> libros = libroService.getAll();
        ResponseEntity<Object> response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        for (Libro l :
                libros) {

            System.out.println();

        }
        if (libros.size() >= 1) {
            response = new ResponseEntity<>(libros, HttpStatus.OK);
        }

        return response;

    }

    /**
     * Gets a libro specified by the id parameter wich indicates the resource location.
     *
     * @param id String objectId for the libro who want to return.
     * @return ResponseEntity object with Libro object finded on the collection and the http status code,Ok if the operation was succesfully or NOT FOUND if not.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getById(@PathVariable String id) {
        Libro libro;
        libro = libroService.getById(id);
        ResponseEntity<Object> response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        if (libro != null) {
            response = new ResponseEntity<>(libro, HttpStatus.OK);
        }

        return response;

    }

    /**
     * Insert a new libro on the database, specified by the libro JSON formatted object sended on the request.
     *
     * @param libro Libro object JSON formatted wich want to insert on the collection.
     * @return ResponseEntity object the new libro inserted on the collection, and the http status code,CREATED if the operation was succesfully or CONFLICT if not.
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> crear(@RequestBody Libro libro) {
        ResponseEntity<Object> response = new ResponseEntity<>(HttpStatus.CONFLICT);
        Libro lib = libroService.create(libro);
        if (lib != null) {
            response = new ResponseEntity<>(libro, HttpStatus.CREATED);
        }
        return response;

    }

    /**
     * Deletes the Libro specified by the JSON formatted Libro object sended on the request.
     *
     * @param id String ObjectId of the libro wich want to delete.
     * @return ResponseEntity object with the http status code,OK if the operation was succesfully or NOT_FOUND if not.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> borrar(@PathVariable String id) {
        ResponseEntity<Object> response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        WriteResult wr = libroService.delete(id);
        if (wr.getN() == 1) {
            response = new ResponseEntity<>(HttpStatus.OK);
        }


        return response;
    }

    /**
     * Updates the Libro object sprecified by the JSON formatted Libro object sended on the request.
     *
     * @param libro JSON formatted Libro object wich specify the document to update, with the updated values.
     * @return ResponseEntity object with the libro modified on the collection, and the http status code,OK if the operation was succesfully or NOT_FOUND if not.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> update(@RequestBody Libro libro) {

        ResponseEntity<Object> response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        if (libroService.update(libro).getUpdatedExisting()) {
            response = new ResponseEntity<>(libro, HttpStatus.OK);
        }


        return response;
    }

}