package br.com.atleticaimperial.noticiario.service;

import br.com.atleticaimperial.noticiario.model.Noticia;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.atleticaimperial.noticiario.repository.NoticiaRepository;

/**
 * @author Pedro Antônio de Souza
 */
@Service
public class NoticiaService {
    
    @Autowired
    NoticiaRepository noticiaRepository;
    
    public List<Noticia> findAll() {
        return noticiaRepository.findAllByOrderByDataCriacaoDesc();
    }

    public Noticia findById(Long id) {
        Optional<Noticia> op_noticia = noticiaRepository.findById(id);
        
        if (op_noticia.isPresent())
            return op_noticia.get();
        
        return null;
    }

    public Noticia save(Noticia noticia) {
        return noticiaRepository.save(noticia);
    }
    
    public Boolean delete(Long id) {
        if (findById(id) == null) {
            return false;
        }
        else {
            noticiaRepository.deleteById(id);
            return true;
        }
    }
}
