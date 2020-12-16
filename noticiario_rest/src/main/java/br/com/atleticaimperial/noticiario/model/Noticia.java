package br.com.atleticaimperial.noticiario.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * @author Pedro Antônio de Souza
 */
@Entity
@Table(name="noticia")
@Getter
@Setter
public class Noticia {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @NotBlank(message = "O título é obrigatório.")
    private String titulo;
    
    
    @NotBlank(message = "O lide é obrigatório.")
    @Lob
    private String lide;
    
    
    @NotBlank(message = "O corpo é obrigatório.")
    @Lob
    private String corpo;
    
    @Column(name = "data_criacao")
    @JsonProperty("data_criacao")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCriacao;
    
    @Column(name = "data_modificacao")
    @JsonProperty("data_modificacao")
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataModificacao;
}
