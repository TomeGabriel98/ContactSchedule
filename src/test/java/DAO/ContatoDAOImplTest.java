package DAO;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.*;

import model.Contato;

public class ContatoDAOImplTest {
	
	private ContatoDAOImpl contato;
	public ContatoDAOImplTest() {
		contato = new ContatoDAOImpl("data_ujdeulho.txt");
		
	}
	
	private Contato c;
	@BeforeEach
	public void contatoSetUp() {
		c = new Contato();
	}
	
	@Test
	public void inserirTest() {
		c.setNome("abcde");
		c.setTelefone("12345678");
		c.setEmail("teste@teste.com");
		c.setEndereco("rua tal");
		
		assertEquals(c, contato.inserir(c));
	}
	
	@Test
	public void removerContatoTest() throws IOException {
		c.setNome("abcde");
		c.setTelefone("12345678");
		c.setEmail("teste@teste.com");
		c.setEndereco("rua tal");
		
		assertTrue(contato.removerContato(c));
	}
	
	@Disabled
	public void listarTodosContatosTest() throws IOException {
		
		assertSame(true, contato.listarTodosContatos() instanceof List);
	}
	
}
