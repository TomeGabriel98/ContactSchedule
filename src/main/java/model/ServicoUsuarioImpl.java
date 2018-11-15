/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import javax.swing.JOptionPane;

import view.TelaLogin;

/**
 *
 * @author fabriciogmc
 */
public class ServicoUsuarioImpl implements
             ServicoUsuario{
	
	private String nomeCrip, senhaCrip;
    public boolean disp;
    private boolean validaUser, validaPass;
	public boolean exist;

    public ServicoUsuarioImpl(String nome_arq_dados_u){
        //implementar. passar referÃªncia ao instanciar o DAO
    }
    
    @Override
    public Usuario inserir(Usuario u) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public Usuario inserir(String nomeUsuario, String senha, List<Contato> contatos) {
    	disp = false;
        nomeCrip = "";
        senhaCrip = "";
        nomeCrip = criptografa(nomeUsuario, nomeCrip);
        senhaCrip = criptografa(senha, senhaCrip);
        validaUser = nomeUsuario.matches("[a-zA-Z0-9]+");
        validaPass = senha.matches("[a-zA-Z0-9]+");
        
        if(!valida(nomeCrip, validaUser)) return;
        
        buscarPorNomeUsuario(nomeCrip);
        if(exist) return;
        
        if(!valida(senha, validaPass)) return;
        if(!confirmaSenha.equals(senha)){
            JOptionPane.showMessageDialog(null, "As senhas não coincidem, confirme a senha corretamente!");

            return;
        }
        
        nomeUsuario = nomeUsuario.toLowerCase();
        JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso");
        
        disp = true;
        TelaLogin login = new TelaLogin();
        login.setLocationRelativeTo(null);
        login.pack();
        login.setVisible(true);
        
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

                    return;
                }
            }

            leitor.close();
		} catch (FileNotFoundException e) {
	            exist = true;
	            // e.printStackTrace();
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
    
    public boolean valida(String val, boolean match){
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

}
