package DAO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import controller.ServicoUsuarioImpl;
import model.Usuario;
import view.TelaListagem;

public class UsuarioDAOImpl implements UsuarioDAO {

	@Override
	public Usuario inserir(Usuario u) {
		try {
			String usuario = "users.txt";
			String path = "../ContactSchedule/src/main/resources/";
			String caminho = new File(path + usuario).getCanonicalPath();
			PrintWriter arquivo = new PrintWriter(new FileWriter(caminho, true));
			
			String salva = u.getNomeUsuario() + "; " + u.getSenha();
			arquivo.append(salva);
            arquivo.println();
            
            PrintWriter user = new PrintWriter(new FileWriter(path + "data_u" + u.getNomeUsuario() + ".txt", true));
            
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
	public Usuario buscarPorNomeUsuario(String nomeUsuario) throws IOException {
		
		TelaListagem listagem = new TelaListagem();
		listagem.setVisible(true);
		
		Usuario u = new Usuario();

		u.setNomeUsuario(nomeUsuario);
		u.setSenha("");
		u.setContatos(null);

		return u;
	}

	@Override
	public boolean removerUsuario(Usuario u) throws IOException {
		String path = "../ContactSchedule/src/main/resources/";
		String caminho = new File(path + "data_u" + u.getNomeUsuario() + ".txt").getCanonicalPath();
        File contato = new File(caminho);
        
        contato.delete();
        
        ArrayList<String> array = new ArrayList<>();
		caminho = new File(path + "users.txt").getCanonicalPath();
		FileReader arquivo = new FileReader(caminho);
		try (BufferedReader leitor = new BufferedReader(arquivo)) {
			String linha = "";

			while (linha != null) {
				linha = leitor.readLine();
				if (linha != null) {
					if (linha.equals(u.getNomeUsuario())) {
						continue;
					} else
						array.add(linha);
				}

			}
		}

		PrintWriter apaga = new PrintWriter(new FileWriter(caminho));
		for (String adc : array) {
			if (adc != null) {
				apaga.printf(adc);
				apaga.println();
				
				apaga.close();
				
				return true;
			}
		}

		apaga.close();
		
		return false;
	}

	@Override
	public Usuario atualizarUsuario(Usuario uAnt, Usuario uAtual) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Usuario> listarTodosUsuarios() throws IOException {
		List<Usuario> lista = new ArrayList<>();
		String path = "../ContactSchedule/src/main/resources/";
		String caminho = new File(path + "data_u" + ServicoUsuarioImpl.user + ".txt").getCanonicalPath();
        File contato = new File(caminho);
        
        contato.delete();
        
        caminho = new File(path + "users.txt").getCanonicalPath();
			FileReader arquivo = new FileReader(caminho);
         @SuppressWarnings("resource")
			BufferedReader leitor = new BufferedReader(arquivo);
         String linha = leitor.readLine();
         String[] separe;
         Usuario u = new Usuario();

         while (linha != null) {
             separe = linha.split("; ");
             linha = leitor.readLine();
             u.setNomeUsuario(separe[0]);
             
         }
         
        if(!lista.contains(u)) lista.add(u);
        
        return lista;
	}
	
}
