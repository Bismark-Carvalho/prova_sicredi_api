package provasicrediapi;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsStringIgnoringCase;
import static org.hamcrest.CoreMatchers.is;

import org.junit.Ignore;
import org.junit.Test;

import io.restassured.path.xml.XmlPath;
import io.restassured.path.xml.XmlPath.CompatibilityMode;


public class DesafioTest {
	
	private String html;

	@Test
	public void deveConsultarCepValido() {
		given()
		.log().all()
		.contentType("application/json")
	.when()
		.get("https://viacep.com.br/ws/91060900/json/")
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
		.get("https://viacep.com.br/ws/99999999/json/")
	.then()
		.log().all()
		.statusCode(200)
		.body("erro", is(true));
	}
	
	@Test
	@Ignore
	public void deveConsultarCepFormatoInvalido() {		
		
		XmlPath xmlPath = new XmlPath(CompatibilityMode.HTML, html);
//		System.out.println(xmlPath.get("html.body.h1"));
		xmlPath.get("html.body.h1");
		
		given()
		.log().all()
		.contentType("application/json")
	.when()
		.get("https://viacep.com.br/ws/999/json/")
	.then()
		.log().all()
		.statusCode(400)
		.body("html/body/h1", containsStringIgnoringCase("Error 400"))
		
		;
	}
	
	@Test
	public void deveConsultarGravataiBarroso() {
		given()
		.log().all()
		.contentType("application/json")
	.when()
		.get("https://viacep.com.br/ws/RS/Gravatai/Barroso/json/")
	.then()
		.log().all()
		.statusCode(200)
		
		
		;
	}
	
	
	
}
