package DAO;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.*;

import model.Usuario;

public class UsuarioDAOImplTest {
	
	private static UsuarioDAOImpl usuario;
	public UsuarioDAOImplTest() {
		usuario = new UsuarioDAOImpl();
		
	}
	
	private static Usuario u;
	@BeforeEach
	public void usuarioSetUp() {
		u = new Usuario();
	}
	
	@Test
	public void inserirTest() {
		Usuario u2 = new Usuario();
		
		u.setNomeUsuario("defgh");
		u.setSenha("45678");
		u.setContatos(null);
		
		u2.setNomeUsuario("defgh");
		u2.setSenha("45678");
		u2.setContatos(null);
		
		assertEquals(u, usuario.inserir(u2));
	}
	
	@Test
	public void removerUsuarioTest() throws IOException {
		u.setNomeUsuario("defgh");
		u.setSenha("45678");
		
		assertEquals(true, usuario.removerUsuario(u));
	}
	
	/*@Test
	public void listarTodosUsuarioTest() throws IOException {
		List<Usuario> c = new ArrayList<Usuario>();
		u.setNomeUsuario("jdeulho");
		c.add(u);
		
		u.setNomeUsuario("defgh");
		c.add(u);
		
		assertTrue(c.containsAll(usuario.listarTodosUsuarios()));
	}*/
}