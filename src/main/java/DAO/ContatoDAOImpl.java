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

import controller.ServicoUsuarioImpl;
import model.Contato;

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
	public Contato buscarPorParteNome(String parteNome) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean removerContato(Contato c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Contato atualizarContato(Contato cAnt, Contato cAtual) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Contato> listarTodosContatos() {
		// TODO Auto-generated method stub
		return null;
	}

}
