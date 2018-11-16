/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.IOException;

import controller.ServicoContatoImpl;
import controller.ServicoUsuarioImpl;

@SuppressWarnings("serial")
public class TelaPrincipal extends javax.swing.JFrame {

    ServicoUsuarioImpl seguranca = new ServicoUsuarioImpl("users.txt");
	
    public TelaPrincipal() throws IOException {
        initComponents();
        seguranca.listarTodosUsuarios();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buscarContato = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        listarContatos = new javax.swing.JButton();
        criarContatos = new javax.swing.JButton();
        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        buscarContato.setText("Buscar Contato");
        buscarContato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarContatoActionPerformed(evt);
            }
        });

        jLabel1.setText("Op��es b�sicas da agenda de contatos.");

        listarContatos.setText("Listar Contatos");
        listarContatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listarContatosActionPerformed(evt);
            }
        });

        criarContatos.setText("Criar Contatos");
        criarContatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                criarContatosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(114, 114, 114)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(listarContatos, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buscarContato, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(criarContatos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(70, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(58, 58, 58))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(38, 38, 38)
                .addComponent(buscarContato)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(listarContatos)
                .addGap(18, 18, 18)
                .addComponent(criarContatos)
                .addContainerGap(107, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void buscarContatoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarContatoActionPerformed
        TelaBusca busca = new TelaBusca();
        busca.setVisible(true);
    }//GEN-LAST:event_buscarContatoActionPerformed

    private void criarContatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_criarContatosActionPerformed
    	TelaCadastro cadastro = new TelaCadastro();
        cadastro.setVisible(true);
    }//GEN-LAST:event_criarContatosActionPerformed

    private void listarContatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listarContatosActionPerformed
        try {
        	ServicoContatoImpl.opcao = 1;
			TelaListagem listar = new TelaListagem();
			listar.setVisible(true);
		} catch (IOException e) {
			//e.printStackTrace();
		}
        
    }//GEN-LAST:event_listarContatosActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buscarContato;
    private javax.swing.JButton criarContatos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton listarContatos;
    // End of variables declaration//GEN-END:variables
}
