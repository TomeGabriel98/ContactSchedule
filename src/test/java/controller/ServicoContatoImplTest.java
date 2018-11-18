package controller;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.*;

import model.Contato;

public class ServicoContatoImplTest {
	
	private ServicoContatoImpl contato;
	public ServicoContatoImplTest() {
		contato = new ServicoContatoImpl("data_ujdeulho.txt");
	}
	
	private Contato c;
	@BeforeEach
	public void contatoSetUp() {
		c = new Contato();
	}
	
	@Test
	public void inserirTest() throws IOException {
		c.setNome("abcde");
		c.setTelefone("12345678");
		c.setEmail("teste@teste.com");
		c.setEndereco("rua tal");
		
		assertAll("Teste de inserção de Contato: ",
				() -> assertEquals(c, contato.inserir("abcde", "12345678", "teste@teste.com", "rua tal")),
				() -> assertEquals(null, contato.inserir("gabriel", "12345678", "teste@teste.com", "rua tal")),
				() -> assertEquals(null, contato.inserir(" ", "12345678", "teste@teste.com", "rua tal")),
				() -> assertEquals(null, contato.inserir("´´´´", "12345678", "teste@teste.com", "rua tal")),
				() -> assertEquals(null, contato.inserir("gabriel", "tome", "teste@teste.com", "rua tal")),
				() -> assertEquals(null, contato.inserir("gabriel", "12345", "teste@teste.com", "rua tal")),
				() -> assertEquals(null, contato.inserir("gabriel", "12345678", "  ", "rua tal")),
				() -> assertEquals(null, contato.inserir("gabriel", "12345678", "a", "rua tal")),
				() -> assertEquals(null, contato.inserir("gabriel", "12345678", "teste", "rua tal")),
				() -> assertEquals(null, contato.inserir("gabriel", "12345678", "teste@tes@te", "rua tal")),
				() -> assertEquals(null, contato.inserir("gabriel", "12345678", "123@teste.com", "rua tal")),
				() -> assertEquals(null, contato.inserir("gabriel", "12345678", "teste@", "rua tal")),
				() -> assertEquals(null, contato.inserir("gabriel", "12345678", "teste@.com", "rua tal")),
				() -> assertEquals(null, contato.inserir("gabriel", "12345678", "teste@teste", "rua tal")),
				() -> assertEquals(null, contato.inserir("gabriel", "12345678", "teste@teste.", "rua tal")),
				() -> assertEquals(null, contato.inserir("gabriel", "12345678", "teste@teste.12", "rua tal")),
				() -> assertEquals(null, contato.inserir("gabriel", "12345678", "teste@teste.com", "ab")),
				() -> assertEquals(null, contato.inserir("gabriel", "12345678", "teste@teste.com", "num sei")));
	}
	
	/*@Test
	public void buscarPorParteNomeTest() throws IOException {
		
		assertEquals("g", contato.buscarPorParteNome("g"));
	}
	
	/*@Test
	public void removerContatoTest() throws IOException {
		c.setNome("gabriel");
		c.setTelefone("12345678");
		c.setEmail("teste@teste.com");
		c.setEndereco("rua tal");
		
		assertEquals(true, contato.removerContato(c));
		
		
		c.setNome("ana");
		c.setTelefone("12345678");
		c.setEmail("ssds@sdsd.sdsd");
		c.setEndereco("sdfdfd");
		
		assertEquals(false, contato.removerContato(c));
	}*/
	
	@Test
	public void listarTodosContatosTest() {
		
	}
	
	
}
