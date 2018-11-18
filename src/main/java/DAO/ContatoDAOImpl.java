package DAO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JOptionPane;

import controller.ServicoContatoImpl;
import controller.ServicoUsuarioImpl;
import model.Contato;
import view.TelaListagem;

public class ContatoDAOImpl implements ContatoDAO {
	
	ArrayList<String> array = new ArrayList<>();

	@Override
	public Contato inserir(Contato c) {
		try {
			String usuario = "data_u" + ServicoUsuarioImpl.usuario + ".txt";
			String path = "../ContactSchedule/src/main/resources/";
			String caminho = new File(path + usuario).getCanonicalPath();
			PrintWriter escreve = new PrintWriter(new FileWriter(caminho, true));       
	        
	        String salva = c.getNome() + "; " + c.getTelefone() + "; " + c.getEmail()
			+ "; " + c.getEndereco();
	        escreve.append(salva);
	        escreve.println();
	        
	        escreve.flush();
	        escreve.close();
	        
	        FileReader le = new FileReader(caminho);
	        @SuppressWarnings("resource")
			BufferedReader leitor = new BufferedReader(le);
	        String linha = "";
	        
	        while (linha != null) {
	            linha = leitor.readLine();
	            if(linha != null)
	                array.add(linha);
	        }
	        
	        Collections.sort(array);   
			
	        @SuppressWarnings("resource")
			PrintWriter arquivo = new PrintWriter(new FileWriter(caminho));
	        for (String adc : array) {
	        	arquivo.append(adc);
	            arquivo.println();
	        }
	            
	        arquivo.flush();  
            
            return c;
			
		} catch (IOException e) {
			//e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<Contato> buscarPorParteNome(String parteNome) throws IOException {
		List<Contato> lista = new ArrayList<>();
		
		TelaListagem listagem = new TelaListagem();
		listagem.setVisible(true);
		
		for (String user : array) {
			Contato c = new Contato();

			c.setNome(user);
			lista.add(c);
		}

		return lista;

	}

	@Override
	public boolean removerContato(Contato c) throws IOException {
		ArrayList<String> array = new ArrayList<>();
		String usuario = "data_u" + ServicoUsuarioImpl.usuario + ".txt";
		String path = "../ContactSchedule/src/main/resources/";
		String caminho = new File(path + usuario).getCanonicalPath();
		FileReader arquivo = new FileReader(caminho);
		try (BufferedReader leitor = new BufferedReader(arquivo)) {
			String linha = "";

			while (linha != null) {
				linha = leitor.readLine();
				if (linha != null) {
					if (!linha.equals(c.getNome())) {
						
						array.add(linha);
					}
				}

			}
			
			PrintWriter apaga = new PrintWriter(new FileWriter(caminho));
			for (String adc : array) {
				if (adc != null) {
					apaga.printf(adc);
					apaga.println();
				}
			}

			apaga.close();

			JOptionPane.showMessageDialog(null, "Contato excluído com sucesso");

			return true;
		}
	}

	@Override
	public Contato atualizarContato(Contato cAnt, Contato cAtual) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Contato> listarTodosContatos() {
		List<Contato> lista = new ArrayList<>();
		//ArrayList<String> array = ServicoContatoImpl.arrayListar;
		/*for (String user : array) {
			Contato c = new Contato();

			c.setNome(user);
			lista.add(c);
		}*/

		return lista;
	}

}
