package DAO;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import model.Usuario;

public class UsuarioDAOImpl implements UsuarioDAO {

	@Override
	public Usuario inserir(Usuario u) {
		try {
			String usuario = "users.txt";
			String path = "../ContactSchedule/src/main/resources/";
			String caminho = new File(path + usuario).getCanonicalPath();
			PrintWriter arquivo = new PrintWriter(new FileWriter(caminho, true));
			
			String salva = u.getNomeUsuario() + " ;" + u.getSenha();
			arquivo.append(salva);
            arquivo.println();
            
            PrintWriter user = new PrintWriter(new FileWriter(path + "data_u" + u.getNomeUsuario() + ".txt-", true));
            
            arquivo.flush();
            arquivo.close();
            user.close();
            
            return u;
			
		} catch (IOException e) {
			//e.printStackTrace();
		}
		
		return null;
		
	}

	@Override
	public Usuario buscarPorNomeUsuario(String nomeUsuario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean removerUsuario(Usuario u) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Usuario atualizarUsuario(Usuario uAnt, Usuario uAtual) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Usuario> listarTodosUsuarios() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
