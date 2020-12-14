package br.com.atleticaimperial.noticiario.service.serviceImpl;

import br.com.atleticaimperial.noticiario.model.Noticia;
import br.com.atleticaimperial.noticiario.repository.NoticiarioRepository;
import br.com.atleticaimperial.noticiario.service.NoticiarioService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Pedro Antônio de Souza
 */
@Service
public class NoticiarioServiceImpl implements NoticiarioService {

    @Autowired
    NoticiarioRepository noticiarioRepository;
    
    @Override
    public List<Noticia> findAll() {
        return noticiarioRepository.findAllByOrderByDataCriacaoDesc();
    }

    @Override
    public Noticia findById(Long id) {
        Optional<Noticia> op_noticia = noticiarioRepository.findById(id);
        
        if (op_noticia.isPresent())
            return op_noticia.get();
        
        return null;
    }

    @Override
    public Noticia save(Noticia noticia) {
        return noticiarioRepository.save(noticia);
    }
    
    @Override
    public Boolean delete(Long id) {
        if (findById(id) == null) {
            return false;
        }
        else {
            noticiarioRepository.deleteById(id);
            return true;
        }
    }
}
