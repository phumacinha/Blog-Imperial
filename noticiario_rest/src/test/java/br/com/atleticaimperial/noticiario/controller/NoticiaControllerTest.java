package br.com.atleticaimperial.noticiario.controller;

import br.com.atleticaimperial.noticiario.model.Noticia;
import br.com.atleticaimperial.noticiario.service.NoticiaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

/**
 * @author Pedro Antônio de Souza
 */
@WebMvcTest
public class NoticiaControllerTest {
    
    @Autowired
    private NoticiaController noticiaController;
    
    @MockBean
    private NoticiaService noticiaService;
    
    @Autowired
    ObjectMapper mapper;
    
    Noticia noticia;
    
    @BeforeEach
    public void setup () {
        standaloneSetup(noticiaController);
    }
    
    @Test
    public void deveRetornarSucesso_quandoBuscarTodasNoticias () {
        List<Noticia> noticias = new ArrayList<>();
        noticia = new Noticia(1L, "Título", "Lide", "Corpo", new Date(), new Date());
        noticias.add(noticia);
        
        when(noticiaService.findAll())
                .thenReturn(noticias);
        
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/noticias")
        .then()
            .statusCode(HttpStatus.OK.value());
    }
    
    @Test
    public void deveRetornarSucesso_quandoCadastrarUmaNoticia () throws Exception {
        noticia = new Noticia();
        noticia.setTitulo("Titulo");
        noticia.setLide("Lide");
        noticia.setCorpo("Corpo");
        
        when(noticiaService.save(noticia))
                .thenReturn(noticia);
        
        given()
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .body(mapper.writeValueAsString(noticia))
        .when()
            .post("/noticias")
        .then()
            .statusCode(HttpStatus.OK.value());
    }
    
    @Test
    public void deveRetornarBadRequest_quandoCadastrarUmaNoticia () {
        noticia = new Noticia();
        noticia.setLide("Lide");
        noticia.setCorpo("Corpo");
        
        when(noticiaService.save(noticia))
                .thenReturn(null);
        
        given()
            .accept(ContentType.JSON)
            .contentType(ContentType.JSON)
        .when()
            .post("/noticias")
        .then()
            .statusCode(HttpStatus.BAD_REQUEST.value());
        
        verify(this.noticiaService, never()).save(noticia);
    }
    
    @Test
    public void deveRetornarSucesso_quandoBuscarUmaNoticia () {
        noticia = new Noticia(1L, "Título", "Lide", "Corpo", new Date(), new Date());
        
        when(noticiaService.findById(1L))
                .thenReturn(noticia);
        
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/noticias/{id}", 1L)
        .then()
            .statusCode(HttpStatus.OK.value());
    }
    
    @Test
    void deveRetornarNotFound_quandoBuscarUmaNoticia () {     
        when(noticiaService.findById(1000L))
                .thenReturn(null);
        
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/noticias/{id}", 1000L)
        .then()
            .statusCode(HttpStatus.NOT_FOUND.value());
    }
    
    @Test
    public void deveRetornarSucesso_quandoEditarUmaNoticia () throws Exception {
        noticia = new Noticia(37L, "Título", "Lide", "Corpo", new Date(), new Date());
        
        when(noticiaService.findById(37L))
                .thenReturn(noticia);
        
        when(noticiaService.save(noticia))
                .thenReturn(noticia);
        
        given()
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .body(mapper.writeValueAsString(noticia))
        .when()
            .put("/noticias/{id}", 37L)
        .then()
            .statusCode(HttpStatus.OK.value());
    }
    
    @Test
    public void deveRetornarNotFound_quandoEditarUmaNoticia () throws Exception {
        noticia = new Noticia(404L, "Título", "Lide", "Corpo", new Date(), new Date());
        
        when(noticiaService.findById(404L))
                .thenReturn(null);
        
        when(noticiaService.save(noticia))
                .thenReturn(null);
        
        given()
            .accept(ContentType.JSON)
            .contentType(ContentType.JSON)
            .body(mapper.writeValueAsString(noticia))
        .when()
            .put("/noticias/{id}", 404L)
        .then()
            .statusCode(HttpStatus.NOT_FOUND.value());
        
        verify(noticiaService, never()).save(noticia);
    }
    
    @Test
    public void deveRetornarSucesso_quandoDeletarUmaNoticia () throws Exception {
        noticia = new Noticia(200L, "Título", "Lide", "Corpo", new Date(), new Date());
        
        when(noticiaService.findById(200L))
                .thenReturn(noticia);
        
        when(noticiaService.delete(200L))
                .thenReturn(null);
        
        given()
            .accept(ContentType.JSON)
        .when()
            .delete("/noticias/{id}", 200L)
        .then()
            .statusCode(HttpStatus.OK.value());
    }
    
    @Test
    public void deveRetornarNotFound_quandoDeletarUmaNoticia () throws Exception {
        when(noticiaService.findById(420L))
            .thenReturn(null);
        
        when(noticiaService.delete(420L))
            .thenReturn(null);
        
        given()
            .accept(ContentType.JSON)
        .when()
            .delete("/noticias/{id}", 420L)
        .then()
            .statusCode(HttpStatus.NOT_FOUND.value());
        
        verify(noticiaService, never()).delete(420L);
    }
}
