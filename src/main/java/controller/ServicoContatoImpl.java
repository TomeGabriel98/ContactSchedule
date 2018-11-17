/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import DAO.ContatoDAOImpl;
import model.Contato;
import view.TelaListagem;

public class ServicoContatoImpl implements ServicoContato {

	/*
	 * O construtor deve receber como parÃ¢metro o nome do arquivo de dados de
	 * contatos (referente ao usuÃ¡rio especÃ­fico carregado)
	 */

	public static int opcao;
	public static String nome = "";
	private String arq_dados_c;
	ArrayList<String> array = new ArrayList<>();
	public boolean disp, exist;
	private boolean validaUser, validaTel;
	private ContatoDAOImpl contatos = new ContatoDAOImpl();

	public ServicoContatoImpl(String nome_arq_dados_c) {
		arq_dados_c = nome_arq_dados_c;
	}

	@Override
	public Contato inserir(Contato c) throws IOException {
		try {
			exist = true;
			String path = "../ContactSchedule/src/main/resources/";
			String caminho = new File(path + arq_dados_c).getCanonicalPath();
			FileReader contato = new FileReader(caminho);
			@SuppressWarnings("resource")
			BufferedReader leitor = new BufferedReader(contato);
			String linha = leitor.readLine();
			String[] separe;

			while (linha != null) {
				separe = linha.split("; ");
				linha = leitor.readLine();
				if (separe[0].trim().equalsIgnoreCase(c.getNome())) {
					JOptionPane.showMessageDialog(null, "Este contato já existe!");
					exist = false;

					return null;
				}
			}

			leitor.close();
		} catch (FileNotFoundException e) {
			exist = true;
			// e.printStackTrace();
		}

		disp = true;

		if (opcao == 2)
			JOptionPane.showMessageDialog(null, "Contato atualizado com sucesso!");
		else
			JOptionPane.showMessageDialog(null, "Contato cadastrado com sucesso!");

		return contatos.inserir(c);
	}

	@Override
	public Contato inserir(String nome, String tel, String email, String end) throws IOException {

		disp = false;
		validaUser = nome.matches("[a-zA-Zá-úÁ-ÚãÃâ-ûÂ-Ûà-ùÀ-Ù0-9 ]+");
		validaTel = tel.matches("[0-9]+");
		String[] separeDominio, separePonto;

		// Nome
		if (nome.trim().equals("")) {
			JOptionPane.showMessageDialog(null, "O nome não pode estar vazio!");

			return null;
		}

		if (!validaUser) {
			JOptionPane.showMessageDialog(null, "Nome só pode conter letras e números!");

			return null;
		}

		// Telefone
		if (!validaTel) {
			JOptionPane.showMessageDialog(null, "Telefone deve conter apenas números");

			return null;
		}
		if (tel.length() < 8 || tel.length() > 20) {
			JOptionPane.showMessageDialog(null, "Telefone deve conter entre 8 e 20 números!");

			return null;
		}

		// Email
		if (email.trim().equals("")) {
			JOptionPane.showMessageDialog(null, "O email não deve ser vazio");

			return null;
		}
		if (email.length() == 1) {
			JOptionPane.showMessageDialog(null, "O email não é válido!");

			return null;
		}
		if (!email.contains("@")) {
			JOptionPane.showMessageDialog(null, "O email deve conter 1 '@' !");

			return null;
		}
		separeDominio = email.split("@");
		if (separeDominio.length > 2) {
			JOptionPane.showMessageDialog(null, "O email deve conter somente 1 '@' !");

			return null;
		}
		if (separeDominio[0].isEmpty() || separeDominio[0].matches("[0-9]+") || separeDominio[0].contains(" ")) {
			JOptionPane.showMessageDialog(null, "O endereço do email não pode ser vazio nem conter apenas números!");

			return null;
		}
		if (separeDominio.length == 1) {
			JOptionPane.showMessageDialog(null, "O domínio do email não pode estar vazio!");

			return null;
		}
		char[] dominio = separeDominio[1].toCharArray();
		if ((int) dominio[0] >= 32 && (int) dominio[0] <= 64 || (int) dominio[0] >= 91 && (int) dominio[0] <= 96
				|| (int) dominio[0] >= 123) {
			JOptionPane.showMessageDialog(null,
					"Primeiro caractere do domínio não pode ser vazio e " + "deve ser uma letra!");

			return null;
		}
		if (!separeDominio[1].contains(".")) {
			JOptionPane.showMessageDialog(null, "Domínio deve conter um ponto('.')!");

			return null;
		}
		separePonto = separeDominio[1].split("\\.");
		if (separePonto.length == 1) {
			JOptionPane.showMessageDialog(null, "Final do domínio não pode estar vazio!");

			return null;
		}
		if (separePonto[1].length() == 0 || separePonto[1].contains(" ") || !separePonto[1].matches("[a-zA-Z]+")) {
			JOptionPane.showMessageDialog(null, "Final do domínio não deve conter números nem ser vazio!");

			return null;
		}

		// Endereco
		if (end.length() < 3 || end.length() > 255) {
			JOptionPane.showMessageDialog(null, "Endereço deve conter entre 3 e 255 caracteres!");

			return null;
		}

		end = end.toLowerCase();
		if (end.contains("lugar nenhum") || end.contains("judas perdeu as botas") || end.contains("casa da mãe joana")
				|| end.contains("num sei")) {
			JOptionPane.showMessageDialog(null, "Este endereço é inválido, sinto muito!");

			return null;
		}

		Contato c = new Contato();
		c.setNome(nome);
		c.setTelefone(tel);
		c.setEmail(email);
		c.setEndereco(end);

		return inserir(c);

	}

	@Override
	public List<Contato> buscarPorParteNome(String parteNome) throws IOException {
		List<Contato> lista = new ArrayList<>();
		String path = "../ContactSchedule/src/main/resources/";
		String caminho = new File(path + arq_dados_c).getCanonicalPath();
		FileReader arquivo = new FileReader(caminho);
		try (BufferedReader leitor = new BufferedReader(arquivo)) {
			String linha = "";

			while (linha != null) {
				linha = leitor.readLine();
				if (linha != null) {
					String separe[] = linha.split("; ");
					if (separe[0].trim().startsWith(parteNome))
						array.add(linha);
				}
			}
		}

		if (array.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Nenhum contato foi encontrado!");

			return null;
		} else {
			nome = parteNome;
			TelaListagem listagem = new TelaListagem();
			listagem.setVisible(true);
		}

		for (String user : array) {
			Contato c = new Contato();

			c.setNome(user);
			lista.add(c);
		}

		return lista;
	}

	@Override
	public boolean removerContato(Contato c) throws IOException {
		ArrayList<String> array = new ArrayList<>();
		String path = "../ContactSchedule/src/main/resources/";
		String caminho = new File(path + arq_dados_c).getCanonicalPath();
		FileReader arquivo = new FileReader(caminho);
		try (BufferedReader leitor = new BufferedReader(arquivo)) {
			String linha = "";

			while (linha != null) {
				linha = leitor.readLine();
				if (linha != null) {
					if (linha.equals(c.getNome())) {
						//contatos.removerContato(c);
						continue;
						//return true;
					} else
						//continue;
					
						array.add(linha);
				}

			}
		}
		
		//return false;

		PrintWriter apaga = new PrintWriter(new FileWriter(caminho));
		for (String adc : array) {
			if (adc != null) {
				apaga.printf(adc);
				apaga.println();
			}
		}

		apaga.close();

		JOptionPane.showMessageDialog(null, "Contato excluído com sucesso");

		return true;
	}

	@Override
	public Contato atualizarContato(Contato cAnt, Contato cAtual) {
		return null;
	}

	@Override
	public List<Contato> listarTodosContatos() throws IOException {
		List<Contato> lista = new ArrayList<>();
		String path = "../ContactSchedule/src/main/resources/";
		String caminho = new File(path + arq_dados_c).getCanonicalPath();
		FileReader arquivo = new FileReader(caminho);
		try (BufferedReader leitor = new BufferedReader(arquivo)) {
			String linha = "";

			if (nome.equals("")) {
				while (linha != null) {
					linha = leitor.readLine();
					if (linha != null)
						array.add(linha);
				}
			} else {
				while (linha != null) {
					linha = leitor.readLine();
					if (linha != null) {
						String separe[] = linha.split("; ");
						if (separe[0].trim().contains(nome))
							array.add(linha);
					}
				}
			}

			for (String user : array) {
				Contato c = new Contato();

				c.setNome(user);
				lista.add(c);
			}

			return lista;
		}
	}

}
