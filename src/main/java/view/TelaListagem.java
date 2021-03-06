/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.IOException;
import java.util.List;

import controller.ServicoContatoImpl;
import controller.ServicoUsuarioImpl;
import model.Contato;

@SuppressWarnings("serial")
public class TelaListagem extends javax.swing.JFrame {

	/**
	 * Creates new form TelaListagem
	 */

	ServicoContatoImpl list = new ServicoContatoImpl("data_u" + ServicoUsuarioImpl.usuario + ".txt");
	List<Contato> contatos = list.listarTodosContatos();

	public TelaListagem() throws IOException {
		initComponents();
		ServicoContatoImpl.nome = "";
		try {
			for (Contato c : contatos) {
				listaContatos.add(c.getNome());
			}
		} catch(NullPointerException e) {
			listaContatos = null;
		}
		

		// this.listaContatos.add("Fabrício; 22222222; prof.fabriciogmc@gmail.com, Rua
		// do Fabrício");
		// this.listaContatos.add("Pedro; 111111; pedro@pedro.com, Rua do Pedro");

	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	
	// <editor-fold defaultstate="collapsed" desc="Generated
	// Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		jLabel1 = new javax.swing.JLabel();
		listaContatos = new java.awt.List();
		editar = new javax.swing.JButton();
		excluir = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

		jLabel1.setText("Contatos existentes na agenda:");

		listaContatos.addItemListener(new java.awt.event.ItemListener() {
			public void itemStateChanged(java.awt.event.ItemEvent evt) {
				listaContatosItemStateChanged(evt);
			}
		});
		listaContatos.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				listaContatosActionPerformed(evt);
			}
		});

		editar.setText("Editar");
		editar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				editarActionPerformed(evt);
			}
		});

		excluir.setText("Excluir");
		excluir.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				excluirActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(33, 33, 33)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(layout.createSequentialGroup().addComponent(editar)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(excluir))
								.addComponent(jLabel1).addComponent(listaContatos,
										javax.swing.GroupLayout.PREFERRED_SIZE, 306,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addContainerGap(41, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap().addComponent(jLabel1).addGap(22, 22, 22)
						.addComponent(listaContatos, javax.swing.GroupLayout.PREFERRED_SIZE, 218,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(23, 23, 23)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(editar).addComponent(excluir))
						.addContainerGap(32, Short.MAX_VALUE)));

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void listaContatosItemStateChanged(java.awt.event.ItemEvent evt) {// GEN-FIRST:event_listaContatosItemStateChanged
		// TODO add your handling code here:
	}// GEN-LAST:event_listaContatosItemStateChanged

	private void editarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_editarActionPerformed
		String item = listaContatos.getSelectedItem();
		String[] separe = item.split(";");
		TelaCadastro edita = new TelaCadastro();
		edita.setLocationRelativeTo(null);
		edita.pack();
		edita.setVisible(true);
		Contato cAnt = new Contato();
		
		cAnt.setNome(separe[0]);
		cAnt.setTelefone(separe[1]);
		cAnt.setEmail(separe[2]);
		cAnt.setEndereco(separe[3]);
		edita.editarContato(cAnt);

		dispose();
	}// GEN-LAST:event_editarActionPerformed

	private void listaContatosActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_listaContatosActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_listaContatosActionPerformed

	private void excluirActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_excluirActionPerformed
		String item = listaContatos.getSelectedItem();
		for (Contato c : contatos) {
			if (c.getNome().equals(item))
				try {
					list.removerContato(c);
				} catch (IOException e) {
					// e.printStackTrace();
				}
		}
	}// GEN-LAST:event_excluirActionPerformed

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton editar;
	private javax.swing.JButton excluir;
	private javax.swing.JLabel jLabel1;
	private java.awt.List listaContatos;
	// End of variables declaration//GEN-END:variables
}
