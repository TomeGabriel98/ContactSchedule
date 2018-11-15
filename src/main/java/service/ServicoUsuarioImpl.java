/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
	
	private String nomeCrip, senhaCrip;
	public static String usuario;
    public boolean disp;
    private boolean validaUser, validaPass;
	public boolean exist;
	private Usuario user;
	UsuarioDAOImpl usuarios = new UsuarioDAOImpl();

    public ServicoUsuarioImpl(String nome_arq_dados_u){
        //implementar. passar referÃªncia ao instanciar o DAO
    	
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
            FileReader arquivo = new FileReader("users.txt");
            @SuppressWarnings("resource")
            BufferedReader leitor = new BufferedReader(arquivo);
            String linha = leitor.readLine();
            String[] separe;

            while (linha != null) {
                separe = linha.split("; ");
                linha = leitor.readLine();
                if (separe[0].trim().equalsIgnoreCase(nomeCrip)) {
                    JOptionPane.showMessageDialog(null, "Este nome de usuário já existe, tente outro!");
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
            JOptionPane.showMessageDialog(null, "As senhas não coincidem, confirme a senha corretamente!");

            return null;
        }
        
        JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso");
        
        disp = true;
        TelaLogin login = new TelaLogin();
        login.setLocationRelativeTo(null);
        login.pack();
        login.setVisible(true);
        
        Usuario u = new Usuario();
        u.setNomeUsuario(nomeUsuario);
        u.setSenha(confirmaSenha);
        
        return inserir(u);
    }

    @Override
    public Usuario buscarPorNomeUsuario(String nomeUsuario) {
    	try {
            exist = true;
            FileReader arquivo = new FileReader("users.txt");
            @SuppressWarnings("resource")
            BufferedReader leitor = new BufferedReader(arquivo);
            String linha = leitor.readLine();
            String[] separe;

            while (linha != null) {
                separe = linha.split("; ");
                linha = leitor.readLine();
                if (separe[0].trim().equalsIgnoreCase(nomeUsuario)) {
                    JOptionPane.showMessageDialog(null, "Este nome de usuário já existe, tente outro!");
                    exist = false;

                    return null;
                }
            }

            leitor.close();
		} catch (FileNotFoundException e) {
	            exist = true;
	            // e.printStackTrace();
		} catch (IOException e) {
			//e.printStackTrace();
		}
    }

    @Override
    public boolean removerUsuario(Usuario u) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Usuario atualizarUsuario(Usuario uAnt, Usuario uAtual) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Usuario> listarTodosUsuarios() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
                JOptionPane.showMessageDialog(null, "O nome de usuário e senha devem conter entre 5 e 15"
                        + " caracteres!");
                
                return false;
            } else if(!match){
                JOptionPane.showMessageDialog(null, "O nome de usuário e senha devem conter somente"
                        + " letras[a-z] e números[0-9]!");
                
                return false;
            }
    	
    	return true;
        
    }
    
    @Override
    public void valida(String nome, String senha){
    	disp = false;
        String nomeCrip = "", senhaCrip = "";
        File arquivo = new File("users.txt");
        try (BufferedReader leitor = new BufferedReader(new FileReader(arquivo))) {
            String linha = leitor.readLine();
            String[] separe;
            
            nomeCrip = criptografa(nome, nomeCrip);
            senhaCrip = criptografa(senha, senhaCrip);
            
            while(linha != null){
                separe = linha.split("; ");
                linha = leitor.readLine();
                
                if(separe[0].trim().equals(nomeCrip) && separe[1].trim().equals(senhaCrip)){
                    disp = true;
                    usuario = nome;
                    TelaPrincipal principal = new TelaPrincipal();
                    principal.setLocationRelativeTo(null);
                    principal.pack();
                    principal.setVisible(true);
                    
                    return;
                }
            }
        } catch (IOException e) {
			//e.printStackTrace();
		}
        
        JOptionPane.showMessageDialog(null, "Nome de usuário e/ou senha incorretos, tente novamente!");
        
        
    } 


}
