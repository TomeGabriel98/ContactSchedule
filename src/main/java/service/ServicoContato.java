/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;

import model.Contato;

/**
 *
 * @author fabriciogmc
 */
public interface ServicoContato {
    public Contato inserir(Contato c);
    public Contato inserir(String nome, String tel,
                           String email, String end);
    public List<Contato> buscarPorParteNome(String parteNome);
    public boolean removerContato(Contato c);
    public Contato atualizarContato(Contato cAnt, 
                                    Contato cAtual);
    public List<Contato> listarTodosContatos(); 
}
