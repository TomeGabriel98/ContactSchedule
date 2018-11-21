/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import DAO.UsuarioDAOImpl;
import model.Contato;
import model.Cripto;
import model.Usuario;
import view.TelaLogin;
import view.TelaPrincipal;

public class ServicoUsuarioImpl implements
             ServicoUsuario, Cripto{
	
	public String nomeCrip, senhaCrip, arq_dados_u;
	public static String usuario, user;
	public ArrayList<String> arrayBuscar = new ArrayList<>();
    public boolean disp;
    private boolean validaUser, validaPass;
	public boolean exist;
	UsuarioDAOImpl usuarios = new UsuarioDAOImpl();
	List<Usuario> lista = new ArrayList<>();

    public ServicoUsuarioImpl(String nome_arq_dados_u){
        arq_dados_u = nome_arq_dados_u;
    	
    }
    
    @Override
    public Usuario inserir(Usuario u) {
    	return usuarios.inserir(u);
    }
    
    @Override
    public Usuario inserir(String nomeUsuario, String senha, String confirmaSenha, 
    		List<Contato> contatos) throws IOException {
    	disp = false;
        nomeCrip = "";
        senhaCrip = "";
        nomeCrip = criptografa(nomeUsuario, nomeCrip);
        senhaCrip = criptografa(senha, senhaCrip);
        validaUser = nomeUsuario.matches("[a-zA-Z0-9]+");
        validaPass = senha.matches("[a-zA-Z0-9]+");
        
        if(!validaUser(nomeCrip, validaUser)) return null;
        
        try {
            exist = true;
            String path = "../ContactSchedule/src/main/resources/";
			String caminho = new File(path + arq_dados_u).getCanonicalPath();
			FileReader arquivo = new FileReader(caminho);
            @SuppressWarnings("resource")
			BufferedReader leitor = new BufferedReader(arquivo);
            String linha = leitor.readLine();
            String[] separe;

            while (linha != null) {
                separe = linha.split("; ");
                linha = leitor.readLine();
                if (separe[0].trim().equalsIgnoreCase(nomeCrip)) {
                    //JOptionPane.showMessageDialog(null, "Este nome de usuário já existe, tente outro!");
                    exist = false;

                    return null;
				}
            }

            leitor.close();
		} catch (FileNotFoundException e) {
			exist = true;
			//e.printStackTrace();
		}
        if(!exist) return null;
        
        if(!validaUser(senhaCrip, validaPass)) return null;
        if(!confirmaSenha.equals(senha)){
            //JOptionPane.showMessageDialog(null, "As senhas não coincidem, confirme a senha corretamente!");

            return null;
        }
        
        //JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso");
        
        disp = true;
        TelaLogin login = new TelaLogin();
        login.setLocationRelativeTo(null);
        login.pack();
        login.setVisible(true);
        
        Usuario u = new Usuario();
        u.setNomeUsuario(nomeCrip);
        u.setSenha(senhaCrip);
        
        return inserir(u);
    }

    @Override
    public Usuario buscarPorNomeUsuario(String nomeUsuario) throws IOException {
    	String path = "../ContactSchedule/src/main/resources/";
		String caminho = new File(path + arq_dados_u).getCanonicalPath();
		FileReader arquivo = new FileReader(caminho);
		try (BufferedReader leitor = new BufferedReader(arquivo)) {
			String linha = "";
 
			while (linha != null) {
				linha = leitor.readLine();
				if (linha != null) {
					String separe[] = linha.split("; ");
					String descrip = descriptografa(separe[0]);
					
					if (descrip.trim().equals(nomeUsuario))
						return usuarios.buscarPorNomeUsuario(nomeUsuario);
				}
			}
		}
		//JOptionPane.showMessageDialog(null, "Nenhum usuário foi encontrado!");

		return null;
    }

    @Override
    public boolean removerUsuario(Usuario u) throws IOException {
    	nomeCrip = ""; 
        senhaCrip = "";
		String path = "../ContactSchedule/src/main/resources/";
		String caminho = new File(path + arq_dados_u).getCanonicalPath();
        try (BufferedReader leitor = new BufferedReader(new FileReader(caminho))) {
            String linha = leitor.readLine();
            String[] separe;
            
            nomeCrip = criptografa(u.getNomeUsuario(), nomeCrip);
            senhaCrip = criptografa(u.getSenha(), senhaCrip);
            
            while(linha != null){
                separe = linha.split("; ");
                linha = leitor.readLine();
                
                if(separe[0].trim().equals(nomeCrip) && separe[1].trim().equals(senhaCrip)){
                    
                    usuarios.removerUsuario(u);
                	
                	return true;
                }
            }
        }
        
        return false;
    }

    @Override
    public Usuario atualizarUsuario(Usuario uAnt, Usuario uAtual) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Usuario> listarTodosUsuarios() {
    	try {
			String path = "../ContactSchedule/src/main/resources/";
			String nome = new File(path).getCanonicalPath();
			File direct = new File(nome);
			
			String[] procura = direct.list();
	        for(int i = 0; i < procura.length; i++){
	            String name = procura[i];
	            
	            if(name.startsWith("data")){
	                String[] separe = name.split("_u");
	                String[] usuario = separe[1].split("\\.");
	                user = usuario[0];
	                
	                seguranca(user);
	                if(!exist) usuarios.listarTodosUsuarios();
	            }
	        }
	        
	        String caminho = new File(path + arq_dados_u).getCanonicalPath();
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
			
		} catch (IOException e) {
			//e.printStackTrace();
		}
    	
    	return lista;
    }
    
    @Override
    public String criptografa(String original, String cripto){
        for(int i = 0; i < original.length(); i++){
            char c = original.charAt(i);
            int a = (int)c;
            
            a += 3;
            c = (char)a;            
            cripto += c;
        }
        
        return cripto;
    }
    
    @Override
    public String descriptografa(String nome){
        String descrip = "";
        
        for(int i = 0; i < nome.length(); i++){
            char c = nome.charAt(i);
            int a = (int)c;
            
            a -= 3;
            c = (char)a;            
            descrip += c;
        }
        
        return descrip;
    }
    
    @Override
    public boolean validaUser(String val, boolean match){
    		if(val.length() < 5 || val.length() > 15){
                //JOptionPane.showMessageDialog(null, "O nome de usuário e senha devem conter entre 5 e 15"
                //        + " caracteres!");
                
                return false;
            } else if(!match){
                //JOptionPane.showMessageDialog(null, "O nome de usuário e senha devem conter somente"
                //        + " letras[a-z] e números[0-9]!");
                
                return false;
            }
    	
    	return true;
        
    }
    
    @Override
    public void valida(String nome, String senha) throws IOException{
    	disp = false;
        nomeCrip = ""; 
        senhaCrip = "";
		String path = "../ContactSchedule/src/main/resources/";
		String caminho = new File(path + arq_dados_u).getCanonicalPath();
        try (BufferedReader leitor = new BufferedReader(new FileReader(caminho))) {
            String linha = leitor.readLine();
            String[] separe;
            
            nomeCrip = criptografa(nome, nomeCrip);
            senhaCrip = criptografa(senha, senhaCrip);
            
            while(linha != null){
                separe = linha.split("; ");
                linha = leitor.readLine();
                
                if(separe[0].trim().equals(nomeCrip) && separe[1].trim().equals(senhaCrip)){
                    disp = true;
                    usuario = nomeCrip;
                    TelaPrincipal principal = new TelaPrincipal();
                    principal.setLocationRelativeTo(null);
                    principal.pack();
                    principal.setVisible(true);
                    
                    return;
                }
            }
        }
        
       // JOptionPane.showMessageDialog(null, "Nome de usuário e/ou senha incorretos, tente novamente!");
        
        
    }
    
    @Override
    public void seguranca(String nome) throws IOException {
    	exist = true;
    	//String descrip = "";
		String path = "../ContactSchedule/src/main/resources/";
		String caminho = new File(path + arq_dados_u).getCanonicalPath();
		@SuppressWarnings("resource")
		BufferedReader leitor = new BufferedReader(new FileReader(caminho));
		String linha = leitor.readLine();
		String[] separe;
        
        while (linha != null) {
            separe = linha.split("; ");
            linha = leitor.readLine();
            /*descrip = descriptografa(separe[0]);
            lista.add(descrip);*/
            if (separe[0].trim().equals(nome)) {
            	return;
            }
        }
        
        leitor.close();
        exist = false;
    	
    }

}