package br.com.atleticaimperial.noticiario.controller;

import br.com.atleticaimperial.noticiario.model.Noticia;
import br.com.atleticaimperial.noticiario.service.NoticiarioService;
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
import org.mockito.Mockito;
import org.springframework.web.bind.MethodArgumentNotValidException;

/**
 * @author Pedro Antônio de Souza
 */
@WebMvcTest
public class NoticiarioControllerTest {
    
    @Autowired
    private NoticiarioController noticiarioController;
    
    @MockBean
    private NoticiarioService noticiarioService;
    
    @Autowired
    ObjectMapper mapper;
    
    Noticia noticia;
    
    @BeforeEach
    public void setup () {
        standaloneSetup(noticiarioController);
    }
    
    @Test
    public void deveRetornarSucesso_quandoBuscarTodasNoticias () {
        List<Noticia> noticias = new ArrayList<>();
        noticia = new Noticia(1L, "Título", "Lide", "Corpo", new Date(), new Date());
        noticias.add(noticia);
        
        Mockito.when(noticiarioService.findAll())
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
        
        Mockito.when(noticiarioService.save(noticia))
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
        
        Mockito.when(noticiarioService.save(noticia))
                .thenReturn(null);
        
        given()
            .accept(ContentType.JSON)
            .contentType(ContentType.JSON)
        .when()
            .post("/noticias")
        .then()
            .statusCode(HttpStatus.BAD_REQUEST.value());
    }
    
    @Test
    public void deveRetornarSucesso_quandoBuscarUmaNoticia () {
        noticia = new Noticia(1L, "Título", "Lide", "Corpo", new Date(), new Date());
        
        Mockito.when(noticiarioService.findById(1L))
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
        Mockito.when(noticiarioService.findById(1000L))
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
        
        Mockito.when(noticiarioService.findById(37L))
                .thenReturn(noticia);
        
        Mockito.when(noticiarioService.save(noticia))
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
        
        Mockito.when(noticiarioService.findById(404L))
                .thenReturn(null);
        
        Mockito.when(noticiarioService.save(noticia))
                .thenReturn(null);
        
        given()
            .accept(ContentType.JSON)
            .contentType(ContentType.JSON)
            .body(mapper.writeValueAsString(noticia))
        .when()
            .put("/noticias/{id}", 404L)
        .then()
            .statusCode(HttpStatus.NOT_FOUND.value());
    }
    
    @Test
    public void deveRetornarSucesso_quandoDeletarUmaNoticia () throws Exception {
        noticia = new Noticia(200L, "Título", "Lide", "Corpo", new Date(), new Date());
        
        Mockito.when(noticiarioService.findById(200L))
                .thenReturn(noticia);
        
        Mockito.when(noticiarioService.delete(200L))
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
        Mockito.when(noticiarioService.findById(404L))
                .thenReturn(null);
        
        Mockito.when(noticiarioService.delete(404L))
                .thenReturn(null);
        
        given()
            .accept(ContentType.JSON)
        .when()
            .delete("/noticias/{id}", 404L)
        .then()
            .statusCode(HttpStatus.NOT_FOUND.value());
    }
}
