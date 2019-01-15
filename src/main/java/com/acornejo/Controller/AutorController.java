package com.acornejo.Controller;

import com.acornejo.Service.ServiceAutorImpl;
import com.acornejo.pojo.Autor;
import com.mongodb.WriteResult;
import org.mongodb.morphia.Key;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.net.UnknownHostException;
import java.util.List;

@Controller
@RequestMapping("/autores")
public class AutorController {
    private ServiceAutorImpl autorService;

    public AutorController() throws UnknownHostException {

        super();

        autorService = ServiceAutorImpl.getInstance();

    }

    @RequestMapping(value = "/listado", method = RequestMethod.GET)
    public ResponseEntity<Object> getAll() {
        List<Autor> autores = autorService.getAll();
        ResponseEntity<Object> response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        for (Autor l :
                autores) {

            System.out.println();

        }
        if (autores.size() >= 1) {
            response = new ResponseEntity<>(autores, HttpStatus.OK);
        }

        return response;

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getById(@PathVariable String id) {
        Autor autor;
        autor = autorService.getById(id);
        ResponseEntity<Object> response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        if (autor != null) {
            response = new ResponseEntity<>(autor, HttpStatus.OK);
        }

        return response;

    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> crear(@RequestBody Autor autor) {
        ResponseEntity<Object> response = new ResponseEntity<>(HttpStatus.CONFLICT);
        Key<Autor> created = autorService.create(autor);
        if (created.getId() != null) {
            response = new ResponseEntity<>(autor, HttpStatus.CREATED);
        }
        return response;

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> borrar(@RequestBody Autor autor) {
        ResponseEntity<Object> response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        WriteResult wr = autorService.delete(autor);
        if (wr.getN() == 1) {
            response = new ResponseEntity<>(HttpStatus.OK);
        }


        return response;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> update(@RequestBody Autor autor) {

        ResponseEntity<Object> response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        Key<Autor> updated = autorService.update(autor);
        if (updated.getId() != null) {
            response = new ResponseEntity<>(autor, HttpStatus.OK);
        }


        return response;
    }

}
