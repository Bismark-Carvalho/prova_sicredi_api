package provasicrediapi;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.http.ContentType;

public class DesafioTest {
	
	@BeforeClass
	public static void setup() {
		baseURI = "https://viacep.com.br/ws";
	}
	

	@Test
	public void deveConsultarCepValido() {
		given()
		.log().all()
		.contentType("application/json")
	.when()
		.get("/91060900/json/")
	.then()
		.log().all()
		.statusCode(200)
		.body("cep", is("91060-900"))
		.body("logradouro", is("Avenida Assis Brasil 3940"))
		.body("complemento", is(""))
		.body("bairro", is("São Sebastião"))
		.body("localidade", is("Porto Alegre"))
		.body("uf", is("RS"))
		.body("ibge", is("4314902"));
	}
	
	@Test
	public void deveConsultarCepInvalido() {
		given()
		.log().all()
		.contentType("application/json")
	.when()
		.get("/99999999/json/")
	.then()
		.log().all()
		.statusCode(200)
		.body("erro", is(true));
	}
	
	@Test
	public void deveConsultarCepFormatoInvalido() {				
		given()
		.log().all()
		.contentType("text/html")
	.when()
		.get("/999/json/")
	.then()
		.log().all()
		.statusCode(400)
		.contentType(ContentType.HTML)
		.body("html.body.h1", is("Erro 400"))
		.body("html.body.h2", is("Ops!"))
		.body("html.body.h3", is("Verifique a sua URL (Bad Request)"))
		;
	}
	
	@Test
	public void deveConsultarGravataiBarroso() {
		given()
		.log().all()
		.contentType("application/json")
	.when()
		.get("/RS/Gravatai/Barroso/json/")
	.then()
		.log().all()
		.statusCode(200)
		.body("$", hasSize(2))
		
		.body("cep[0]", is("94085-170"))
		.body("logradouro[0]", is("Rua Ari Barroso"))
		.body("complemento[0]", is(""))
		.body("bairro[0]", is("Morada do Vale I"))
		.body("localidade[0]", is("Gravataí"))
		.body("uf[0]", is("RS"))
		.body("ibge[0]", is("4309209"))
		.body("gia[0]", is(""))
		.body("ddd[0]", is("51"))
		.body("siafi[0]", is("8683"))
		
		.body("cep[1]", is("94175-000"))
		.body("logradouro[1]", is("Rua Almirante Barroso"))
		.body("complemento[1]", is(""))
		.body("bairro[1]", is("Recanto Corcunda"))
		.body("localidade[1]", is("Gravataí"))
		.body("uf[1]", is("RS"))
		.body("ibge[1]", is("4309209"))
		.body("gia[1]", is(""))
		.body("ddd[1]", is("51"))
		.body("siafi[1]", is("8683"));
	}
	
	
	
}
