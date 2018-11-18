/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

public class Contato {
    private String nome;
    private String telefone;
    private String endereco;
    private String email;
    
    public Contato(){}
    
    //Criar outros construtores caso deseje

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
    
    
    
    @Override
    public boolean equals(Object o){
    	if(o instanceof Contato) {
    		Contato c = ((Contato)o);
    		
    		if(c.getNome().equals(this.getNome()) && c.getTelefone().equals(this.getTelefone()) && 
    				c.getEmail().equals(this.getEmail()) &&
    				c.getEndereco().equals(this.getEndereco())) return true;
    	}
    	
        return false;
    }

	
}
