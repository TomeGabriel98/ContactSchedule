package controller;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.*;

import DAO.UsuarioDAOImpl;
import model.Usuario;

public class ServicoUsuarioImplTest {
	
	private static ServicoUsuarioImpl usuario;
	public ServicoUsuarioImplTest() {
		usuario = new ServicoUsuarioImpl("users.txt");
		
	}
	
	private static Usuario u;
	@BeforeEach
	public void usuarioSetUp() {
		u = new Usuario();
	}
	
	@Test
	public void inserirTest() throws IOException {	
		u.setNomeUsuario("defgh");
		u.setSenha("45678");
		u.setContatos(null);
		
		assertAll("Teste de inserção de usuário: ",
				() -> assertEquals(u, usuario.inserir("abcde", "12345", "12345", null)),
				() -> assertEquals(null, usuario.inserir("gabriel", "12345", "12345", null)),
				() -> assertEquals(null, usuario.inserir("defgh", "12345", "123456", null)));
		
	}
	
	@Test
	public void removerUsuarioTest() throws IOException {
		u.setNomeUsuario("abcde");
		u.setSenha("12345");
		
		assertEquals(true, usuario.removerUsuario(u));
		
		u.setNomeUsuario("abcde");
		u.setSenha(" ");
		
		assertEquals(false, usuario.removerUsuario(u));
	}
	
	/*@Test
	public void listarTodosUsuariosTest() {
		List<Usuario> c = new ArrayList<Usuario>();
		u.setNomeUsuario("jdeulho");
		c.add(u);
		
		u.setNomeUsuario("defgh");
		c.add(u);
		
		assertTrue(c.containsAll(usuario.listarTodosUsuarios()));
	}*/
	
	@Test
	public void criptografaTest() {
		assertEquals("defg", usuario.criptografa("abcd", ""));
	}
	
	@Test
	public void descriptografaTest() {
		assertEquals("abcd", usuario.descriptografa("defg"));
	}
	
	@Test
	public void validaUserTest() {
		assertAll("Teste de validação de usuário:", 
			() -> assertEquals(true, usuario.validaUser("defgh", true)),
			() -> assertEquals(false, usuario.validaUser("defg", true)),
			() -> assertEquals(false, usuario.validaUser("defgh", false)));
	}
	
	@Test
	public void validaTest() throws IOException {
		usuario.valida("adcdef", "12345");
		assertNull(ServicoUsuarioImpl.usuario);
		
		usuario.valida("gabriel", "12345");
		assertNotNull(ServicoUsuarioImpl.usuario);
	}
	
	@Test
	public void segurancaTest() throws IOException {
		usuario.seguranca("abcdef");
		assertEquals(false, usuario.exist);
		
		usuario.seguranca("jdeulho");
		assertEquals(true, usuario.exist);
	}
}
