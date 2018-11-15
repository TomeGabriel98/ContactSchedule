/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;

/**
 *
 * @author fabriciogmc
 */
public interface ContatoDAO {
    public Contato inserir(Contato c);
    public Contato buscarPorParteNome(String parteNome);
    public boolean removerContato(Contato c);
    public Contato atualizarContato(Contato cAnt, 
                                    Contato cAtual);
    public List<Contato> listarTodosContatos(); 
}
