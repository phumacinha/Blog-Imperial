package br.com.atleticaimperial.noticiario.utils;

import br.com.atleticaimperial.noticiario.repository.NoticiarioRepository;
import br.com.atleticaimperial.noticiario.model.Noticia;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Pedro Antônio de Souza
 */

@Component
public class DummyData {
    
    @Autowired
    NoticiarioRepository noticiarioRepository;
    
    // @PostConstruct
    public void saveNoticias () {
        List<Noticia> noticiaList = new ArrayList<>();
        
        Noticia noticia1 = new Noticia();
        noticia1.setTitulo("Pesquisadores que assessoraram Ministério da Saúde dizem não ter sido consultados antes de envio de plano de vacinação ao STF");
        noticia1.setLide("Cientistas reiteram a recomendação para inserção de todas as populações vulneráveis, incluindo a carcerária, nos grupos prioritários, além da ampliação para todos os profissionais de educação e de áreas essenciais. Segundo ministério, plano será detalhado na próxima quinta.");
        noticia1.setCorpo("<p>Um grupo de pesquisadores divulgou neste sábado (12) uma nota conjunta em que diz não ter sido consultado sobre o plano de vacinação contra a Covid-19 que foi encaminhado pelo governo ao Supremo Tribunal Federal (STF).</p><p>Na nota assinada por cientistas que são listados, no plano, como colaboradores, os pesquisadores dizem que o material não foi apresentado previamente e não obteve a anuência dos integrantes do grupo (leia detalhes do plano mais abaixo e veja a íntegra da nota dos cientistas ao final desta reportagem).</p>");
    
        Noticia noticia2 = new Noticia();
        noticia2.setTitulo("Camada de gelo torna impossível andar sem cair em rua de Kiev");
        noticia2.setLide("Câmera de segurança registrou imagens de pessoas tentando descer ladeira, totalmente incapazes de se manter em pé. Segundo a polícia, mais de 100 acidentes de carro foram registrados na quinta-feira (10).");
        noticia2.setCorpo("<p>Uma fina camada de gelo formada nas ruas paralisou Kiev, a capital da Ucrânia, na quinta-feira (10), causando mais de 100 acidentes de carro, de acordo com a polícia local.</p><p>Um vídeo de segurança divulgado por uma empresa cujo escritório está localizado em Andriyivsky mostrou pessoas escorregando, totalmente incapazes de parar em pé, lutando para caminhar ladeira abaixo (assista vídeo acima).</p>");
   
        noticiaList.add(noticia1);
        noticiaList.add(noticia2);
        
        for (Noticia noticia : noticiaList) {
            Noticia noticiaSalva = noticiarioRepository.save(noticia);
            System.out.println("Noticia #" + noticiaSalva.getId() + " salva.");
        }
    }
}
