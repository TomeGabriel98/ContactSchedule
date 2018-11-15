/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import model.Contato;

public class ServicoContatoImpl implements
             ServicoContato{

    /* O construtor deve receber
    como parâmetro o nome do arquivo de
    dados de contatos (referente ao usuário
    específico carregado)*/
	
	public static int opcao = 0, found = 1;
	ArrayList<String> array = new ArrayList<>();
    String usuario = Login.usuario;
    public boolean disp;
    private boolean validaUser, validaTel;
	
    public ServicoContatoImpl(String nome_arq_dados_c){
        
    }
    @Override
    public Contato inserir(Contato c) {
        /* Colocar aqui a lógica do tipo
           só inserir se o contato não existir, 
           ou seja, utilizar o dao para buscar e depois
           para inserir, caso seja aplicável. */
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Contato inserir(String nome, String tel, String email, String end) {
        
    	disp = false;
        validaUser = nome.matches("[a-zA-Z�-��-����-��-��-��-�0-9 ]+");
        validaTel = tel.matches("[0-9]+");
        String[] separeDominio, separePonto;
        
        //Nome
        ler.verificaContato(nome, usuario);
        if(!ler.exist) return;
        
        if(nome.trim().equals("")){
            JOptionPane.showMessageDialog(null, "O nome n�o pode estar vazio!");
            
            return;
        }
        
        if(!validaUser){
            JOptionPane.showMessageDialog(null, "Nome s� pode conter letras e n�meros!");
            
            return;
        }
        
        //Telefone
        if(!validaTel){
            JOptionPane.showMessageDialog(null, "Telefone deve conter apenas n�meros");
            
            return;
        }
        if(tel.length() < 8 || tel.length() > 20){
            JOptionPane.showMessageDialog(null, "Telefone deve conter entre 8 e 20 n�meros!");
            
            return;
        }
        
        //Email
        if(email.trim().equals("")){
            JOptionPane.showMessageDialog(null, "O email n�o deve ser vazio");
            
            return;
        }
        if(email.length() == 1){
            JOptionPane.showMessageDialog(null, "O email n�o � v�lido!");
            
            return;
        }
        if(!email.contains("@")){
            JOptionPane.showMessageDialog(null, "O email deve conter 1 '@' !");
            
            return;
        }
        separeDominio = email.split("@");
        if(separeDominio.length > 2){
            JOptionPane.showMessageDialog(null, "O email deve conter somente 1 '@' !");
            
            return;
        }
        if(separeDominio[0].isEmpty() || separeDominio[0].matches("[0-9]+") || separeDominio[0].contains(" ")){
            JOptionPane.showMessageDialog(null, "O endere�o do email n�o pode ser vazio nem conter apenas n�meros!");
            
            return;
        }
        if(separeDominio.length == 1){
            JOptionPane.showMessageDialog(null, "O dom�nio do email n�o pode estar vazio!");
            
            return;
        }
        char[] dominio = separeDominio[1].toCharArray();
        if((int)dominio[0] >= 32 && (int)dominio[0] <= 64 || (int)dominio[0] >= 91 && (int)dominio[0] <= 96 || (int)dominio[0] >= 123){
            JOptionPane.showMessageDialog(null, "Primeiro caractere do dom�nio n�o pode ser vazio e "
                    + "deve ser uma letra!");
            
            return;
        }
        if(!separeDominio[1].contains(".")){
            JOptionPane.showMessageDialog(null, "Dom�nio deve conter um ponto('.')!");
            
            return;
        }
        separePonto = separeDominio[1].split("\\.");
        if(separePonto.length == 1){
            JOptionPane.showMessageDialog(null, "Final do dom�nio n�o pode estar vazio!");
            
            return;
        }
        if(separePonto[1].length() == 0 || separePonto[1].contains(" ") || !separePonto[1].matches("[a-zA-Z]+")){
            JOptionPane.showMessageDialog(null, "Final do dom�nio n�o deve conter n�meros nem ser vazio!");
            
            return;
        }
        
        //Endereco
        if(end.length() < 3 || end.length() > 255){
            JOptionPane.showMessageDialog(null, "Endere�o deve conter entre 3 e 255 caracteres!");
            
            return;
        }
        
        end = end.toLowerCase();
        if(end.contains("lugar nenhum") || end.contains("judas perdeu as botas")
                || end.contains("casa da m�e joana") || end.contains("num sei")){
            JOptionPane.showMessageDialog(null, "Este endere�o � inv�lido, sinto muito!");
            
            return;
        }
        
        disp = true;
        
        if(opcao == 2) JOptionPane.showMessageDialog(null, "Contato atualizado com sucesso!");
        else JOptionPane.showMessageDialog(null, "Contato cadastrado com sucesso!");
    }
    
    @Override
    public List<Contato> buscarPorParteNome(String parteNome) {
    	FileReader arquivo = new FileReader("data_u" + usuario + ".txt");
        try (BufferedReader leitor = new BufferedReader(arquivo)) {
            String linha = "";
       
            while (linha != null) {
            	linha = leitor.readLine();
                if(linha != null){
                	String separe[] = linha.split("; ");
                    if(separe[0].trim().startsWith(parteNome)) array.add(linha);
                }
            }
        }
        
        for(String user : array){
        	lista.add(user);
        }
    }

    @Override
    public boolean removerContato(Contato c) {
    	String item = lista.getSelectedItem();
        lista.remove(item);
        opcao = 1;
        ler.salva(item, usuario);
        return true;
        
    }

    @Override
    public Contato atualizarContato(Contato cAnt, Contato cAtual) {
    	String item = lista.getSelectedItem();
        lista.remove(item);
        opcao = 2;
        ler.salva(item, usuario);
    }

    @Override
    public List<Contato> listarTodosContatos(List<Contato> lista) {
    	FileReader arquivo = new FileReader("data_u" + usuario + ".txt");
        try (BufferedReader leitor = new BufferedReader(arquivo)) {
        	String linha = "";
        	while (linha != null) {
        		linha = leitor.readLine();
                if(linha != null) array.add(linha);
            }   
            
        	for(String user : array) lista.add(user);
        	
        	return lista;
        }
    }


    
}
