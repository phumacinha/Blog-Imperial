package br.com.atleticaimperial.noticiario.service;

import br.com.atleticaimperial.noticiario.model.Noticia;
import java.util.List;

/**
 * @author Pedro Antônio de Souza
 */
public interface NoticiarioService {
    List<Noticia> findAll();
    Noticia findById(Long id);
    Noticia save(Noticia noticia);
    Boolean delete(Long id);
}
