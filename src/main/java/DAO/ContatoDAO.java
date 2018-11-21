/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.io.IOException;
import java.util.List;

import model.Contato;

public interface ContatoDAO {
    public Contato inserir(Contato c);
    public List<Contato> buscarPorParteNome(String parteNome) throws IOException;
    public boolean removerContato(Contato c) throws IOException;
    public Contato atualizarContato(Contato cAnt, 
                                    Contato cAtual) throws IOException;
    public List<Contato> listarTodosContatos(); 
}
