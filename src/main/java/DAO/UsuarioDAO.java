/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.io.IOException;
import java.util.List;

import model.Usuario;

public interface UsuarioDAO {
    public Usuario inserir(Usuario u);
    public Usuario buscarPorNomeUsuario(String nomeUsuario);
    public boolean removerUsuario(Usuario u) throws IOException;
    public Usuario atualizarUsuario(Usuario uAnt, 
                                    Usuario uAtual);
    public List<Usuario> listarTodosUsuarios() throws IOException;    
}
