/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.util.List;

import model.Contato;

public interface ServicoContato {
    public Contato inserir(Contato c) throws IOException;
    public Contato inserir(String nome, String tel,
                           String email, String end) throws IOException;
    public List<Contato> buscarPorParteNome(String parteNome) throws IOException;
    public boolean removerContato(Contato c) throws IOException;
    public Contato atualizarContato(Contato cAnt, 
                                    Contato cAtual) throws IOException;
    public List<Contato> listarTodosContatos() throws IOException;
}
