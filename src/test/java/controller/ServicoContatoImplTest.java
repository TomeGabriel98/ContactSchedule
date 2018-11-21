package controller;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.List;

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
		assertAll("Teste de inserção de Contato: ",
				() -> assertNotNull(contato.inserir("abcde", "12345678", "teste@teste.com", "rua tal")),
				() -> assertNull(contato.inserir(" ", "12345678", "teste@teste.com", "rua tal")),
				() -> assertNull(contato.inserir("´´´´", "12345678", "teste@teste.com", "rua tal")),
				() -> assertNull(contato.inserir("gabriel", "tome", "teste@teste.com", "rua tal")),
				() -> assertNull(contato.inserir("gabriel", "12345", "teste@teste.com", "rua tal")),
				() -> assertNull(contato.inserir("gabriel", "12345678", "  ", "rua tal")),
				() -> assertNull(contato.inserir("gabriel", "12345678", "a", "rua tal")),
				() -> assertNull(contato.inserir("gabriel", "12345678", "teste", "rua tal")),
				() -> assertNull(contato.inserir("gabriel", "12345678", "teste@tes@te", "rua tal")),
				() -> assertNull(contato.inserir("gabriel", "12345678", "123@teste.com", "rua tal")),
				() -> assertNull(contato.inserir("gabriel", "12345678", "teste@", "rua tal")),
				() -> assertNull(contato.inserir("gabriel", "12345678", "teste@.com", "rua tal")),
				() -> assertNull(contato.inserir("gabriel", "12345678", "teste@teste", "rua tal")),
				() -> assertNull(contato.inserir("gabriel", "12345678", "teste@teste.", "rua tal")),
				() -> assertNull(contato.inserir("gabriel", "12345678", "teste@teste.12", "rua tal")),
				() -> assertNull(contato.inserir("gabriel", "12345678", "teste@teste.com", "ab")),
				() -> assertNull(contato.inserir("gabriel", "12345678", "teste@teste.com", "num sei")));
	}
	
	@Test
	public void removerContatoTest() throws IOException {
		c.setNome("abcde");
		c.setTelefone("12345678");
		c.setEmail("teste@teste.com");
		c.setEndereco("rua tal");
		
		assertTrue(contato.removerContato(c));
		
		
		c.setNome("ana");
		c.setTelefone("12345678");
		c.setEmail("ssds@sdsd.sdsd");
		c.setEndereco("sdfdfd");
		
		assertFalse(contato.removerContato(c));
	}
	
	@Disabled
	public void listarTodosContatosTest() throws IOException {
		
		assertSame(true, contato.listarTodosContatos() instanceof List);
	}
	
	
}
