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

@Controller
@RequestMapping("/libros")
public class LibroController {
    private ServiceLibroImpl libroService;


    public LibroController() {

        super();

        libroService = ServiceLibroImpl.getInstance();

    }


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

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> crear(@RequestBody Libro libro) {
        ResponseEntity<Object> response = new ResponseEntity<>(HttpStatus.CONFLICT);
        Libro lib = libroService.create(libro);
        if (lib != null) {
            response = new ResponseEntity<>(libro, HttpStatus.CREATED);
        }
        return response;

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> borrar(@PathVariable String id) {
        ResponseEntity<Object> response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        WriteResult wr = libroService.delete(id);
        if (wr.getN() == 1) {
            response = new ResponseEntity<>(HttpStatus.OK);
        }


        return response;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> update(@RequestBody Libro libro) {

        ResponseEntity<Object> response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        if (libroService.update(libro).getUpdatedExisting()) {
            response = new ResponseEntity<>(libro, HttpStatus.OK);
        }


        return response;
    }

}