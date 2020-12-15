package br.com.atleticaimperial.noticiario.model;

import com.fasterxml.jackson.annotation.JsonFormat;
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
import javax.validation.constraints.Size;
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
    
    @NotEmpty(message = "O título não pode estar vazio.")
    @Size(min = 10, message = "O tiítulo deve ter no mínimo 10 caracteres.") 
    @NotNull
    private String titulo;
    
    
    @NotEmpty(message = "O corpo não pode estar vazio.")
    @NotNull
    @Lob
    private String lide;
    
    @NotEmpty(message = "O corpo não pode estar vazio.")
    @NotNull
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
