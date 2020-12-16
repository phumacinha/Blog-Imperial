package br.com.atleticaimperial.noticiario.repository;

import br.com.atleticaimperial.noticiario.model.Noticia;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Pedro Antônio de Souza
 */
public interface NoticiaRepository extends JpaRepository<Noticia, Long> {
    public List<Noticia> findAllByOrderByDataCriacaoDesc();
}
