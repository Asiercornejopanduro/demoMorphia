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

/**
 * Controller to manage the autores collection
 */
@Controller
@RequestMapping("/autores")
public class AutorController {
    private ServiceAutorImpl autorService;


    public AutorController() throws UnknownHostException {

        super();

        autorService = ServiceAutorImpl.getInstance();

    }

    /**
     * Gets a list of autores storaged on the "autores" collection from "biblioteca" database.
     *
     * @return ResponseEntity object with all the authors documents on the collection, and the http status code,Ok if the operation was succesfully or NOT FOUND if not.
     */
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

    /**
     * Gets a author specified by the id parameter wich indicates the resource location.
     *
     * @param id String objectId for the author who want to return.
     * @return ResponseEntity object with Author object finded on the collection and the http status code,Ok if the operation was succesfully or NOT FOUND if not.
     */
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

    /**
     * Insert a new Autor on the database, specified by the author JSON formatted object sended on the request.
     *
     * @param autor Autor object JSON formatted wich want to insert on the collection.
     * @return ResponseEntity object the new autor inserted on the collection, and the http status code,CREATED if the operation was succesfully or CONFLICT if not.
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> crear(@RequestBody Autor autor) {
        ResponseEntity<Object> response = new ResponseEntity<>(HttpStatus.CONFLICT);
        Key<Autor> created = autorService.create(autor);
        if (created.getId() != null) {
            response = new ResponseEntity<>(autor, HttpStatus.CREATED);
        }
        return response;

    }

    /**
     * Deletes the Autor specified by the JSON formatted Author object sended on the request.
     *
     * @param autor JSON formatted Autor object wich want to delete from the collection.
     * @return ResponseEntity object with the http status code,OK if the operation was succesfully or NOT_FOUND if not.
     */
    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<Object> borrar(@RequestBody Autor autor) {
        ResponseEntity<Object> response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        WriteResult wr = autorService.delete(autor);
        if (wr.getN() == 1) {
            response = new ResponseEntity<>(HttpStatus.OK);

        }

        return response;
    }

    /**
     * Updates the Autor object sprecified by the JSON formatted Autor object sended on the request.
     *
     * @param autor JSON formatted Autor object wich specify the document to update.
     * @return ResponseEntity object the autor modified on the collection, and the http status code,OK if the operation was succesfully or NOT_FOUND if not.
     */
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
