package controller;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.*;

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
				() -> assertNull(usuario.inserir("gabriel", "12345", "12345", null)),
				() -> assertNull(usuario.inserir("defgh", "12345", "123456", null)));
		
	}
	
	@Test
	public void buscarPorNomeUsuarioTest() throws IOException {
		u.setNomeUsuario("gabriel");
		u.setSenha("");
		u.setContatos(null);
		
		assertEquals(u, usuario.buscarPorNomeUsuario("gabriel"));
		
		assertNull(usuario.buscarPorNomeUsuario("ana"));
	}
	
	@Test
	public void removerUsuarioTest() throws IOException {
		u.setNomeUsuario("abcde");
		u.setSenha("12345");
		
		assertTrue(usuario.removerUsuario(u));
		
		u.setNomeUsuario("abcde");
		u.setSenha(" ");
		
		assertFalse(usuario.removerUsuario(u));
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
			() -> assertTrue(usuario.validaUser("defgh", true)),
			() -> assertFalse( usuario.validaUser("defg", true)),
			() -> assertFalse(usuario.validaUser("defgh", false)));
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
		assertFalse(usuario.exist);
		
		usuario.seguranca("jdeulho");
		assertTrue(usuario.exist);
	}
}