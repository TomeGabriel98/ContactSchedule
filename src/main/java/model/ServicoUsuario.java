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
public interface ServicoUsuario {
    public Usuario inserir(Usuario u);
    public Usuario inserir(String nomeUsuario, String senha, 
                           List<Contato> contatos);
    /* Deve retornar um objeto completo, incluindo
    seus contatos.*/
    public Usuario buscarPorNomeUsuario(String nomeUsuario);
    /* A remoção de um usuário deve remover
    os contatos associados, via chamada de serviço
    de contatos.*/
    public boolean removerUsuario(Usuario u);
    /* Uma atualização de usuário pode, por exemplo,
    simplesmente alterar os seus contatos, afetando
    a lista do objeto e os arquivos de dados.*/
    public Usuario atualizarUsuario(Usuario uAnt, 
                                    Usuario uAtual);
    /* monta uma lista de objetos completos
    de usuário, contendo inclusive todos os contatos
    de cada usuário*/
    public List<Usuario> listarTodosUsuarios();       
}
