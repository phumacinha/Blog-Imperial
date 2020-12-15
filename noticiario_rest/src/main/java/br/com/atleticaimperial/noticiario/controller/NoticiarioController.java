package br.com.atleticaimperial.noticiario.controller;

import br.com.atleticaimperial.noticiario.service.NoticiarioService;
import br.com.atleticaimperial.noticiario.model.Noticia;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Pedro Antônio de Souza
 */
//@CrossOrigin(origins = "http://localhost:4545", maxAge = 3600)
@RestController
public class NoticiarioController {
    
    @Autowired
    NoticiarioService noticiarioService;
    
    
    @GetMapping("/noticias")
    public ResponseEntity<List<Noticia>> getNoticias() {
        return new ResponseEntity<>(noticiarioService.findAll(), HttpStatus.OK);
    }
    
    @GetMapping("/noticias/{id}")
    public ResponseEntity<Noticia> getNoticiaById(@PathVariable("id") long id) {
        Noticia noticia = noticiarioService.findById(id);
        
        if (noticia != null) {
            return new ResponseEntity<>(noticia, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(noticia, HttpStatus.NOT_FOUND);
        }
    }
    
    @PostMapping("/noticias")
    public ResponseEntity<Noticia> createNoticia(@RequestBody @Valid Noticia noticia, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(noticia, HttpStatus.BAD_REQUEST);
        }
        
        noticiarioService.save(noticia);
        return new ResponseEntity<>(noticia, HttpStatus.OK);
    }
    
    @DeleteMapping("/noticias/{id}")
    public ResponseEntity<HttpStatus> deleteNoticia(@PathVariable("id") long id) {
        Noticia noticia = noticiarioService.findById(id);
        
        if (noticia != null) {
            noticiarioService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/noticias/{id}")
    public ResponseEntity<Noticia> updateNoticia(@PathVariable("id") long id, @RequestBody @Valid Noticia noticia) {
        Noticia _noticia = noticiarioService.findById(id);
        
        if (_noticia != null) {
            _noticia.setTitulo(noticia.getTitulo());
            _noticia.setLide(noticia.getLide());
            _noticia.setCorpo(noticia.getCorpo());
            
            noticiarioService.save(_noticia);
            return new ResponseEntity<>(_noticia, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
