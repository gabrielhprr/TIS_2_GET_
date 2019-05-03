package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.Escalacao;
import model.Jogador;
import model.enums.Escala;

public class EscalacaoDAO implements GenericDAO<Escalacao, Integer> {
	static final String ARQUIVO = "escalacao.txt";
	static final String SEQUENCE = "sequence_escalacao.txt";

	@Override
	public Escalacao get(Integer id) {
		Escalacao retorno = null;
		Escalacao j = null;

		try (BufferedReader buffer_entrada = new BufferedReader(new FileReader(ARQUIVO))) {
			String linha;

			while ((linha = buffer_entrada.readLine()) != null) {
				String[] dados = linha.split(";");

				j = new Escalacao();
				j.setId(Integer.parseInt(dados[0]));
				j.setId(dados[1]);

				if (id.equals(j.getId())) {
					retorno = j;
					break;
				}
			}

			return null;
		}
	}

	@SuppressWarnings("resource")
	@Override
	public void add(Escalacao t) {

		try {
			BufferedReader bufferInSequence = new BufferedReader(new FileReader(SEQUENCE));
			BufferedWriter bufferOutEscalacao = new BufferedWriter(new FileWriter(ARQUIVO, true));

			Integer generatedId;
			String linha = bufferInSequence.readLine();
			if (linha != null) {
				generatedId = Integer.parseInt(linha);
				bufferInSequence.close();

				BufferedWriter bufferOutSequence = new BufferedWriter(new FileWriter(SEQUENCE, false));
				bufferOutSequence.write(Integer.toString(generatedId + 1));
				bufferOutSequence.flush();
			} else {
				generatedId = 1;

				BufferedWriter bufferOutSequence = new BufferedWriter(new FileWriter(SEQUENCE, false));
				bufferOutSequence.write(Integer.toString(generatedId + 1));
				bufferOutSequence.flush();
			}

			String separadorDeAtributo = ";";
			bufferOutEscalacao.write(generatedId + separadorDeAtributo);
			bufferOutEscalacao.write(t.getId() + separadorDeAtributo);
			bufferOutEscalacao.write(System.getProperty("line.separator"));
			bufferOutEscalacao.flush();

		} catch (Exception e) {
			System.out.println("Erro ao Adicionar o Jogador");
			e.printStackTrace();
		}
	}

	@Override
	public void update(Escalacao t) throws NumberFormatException, IOException {
		List<Escalacao> escalacao = getAll();
		int index = escalacao.indexOf(t);
		if (index != -1) {
			escalacao.set(index, t);
			saveToFile(escalacao);
		}
	}

	// TODO Auto-generated method stub

	@Override
	public void delete(Escalacao t) throws NumberFormatException, IOException {
		List<Escalacao> escalacao = getAll();
		int index = escalacao.indexOf(t);
		if (index != -1) {
			escalacao.remove(index);
			saveToFile(escalacao);
		}
		// TODO Auto-generated method stub

	}

	@Override
	public List<Escalacao> getAll() throws FileNotFoundException, NumberFormatException, IOException {

		List<Escalacao> escalacao = new ArrayList<Escalacao>();
		Escalacao j = null;
		BufferedReader buffer_entrada = new BufferedReader(new FileReader(ARQUIVO));
		String linha;

		while ((linha = buffer_entrada.readLine()) != null) {
			String[] dados = linha.split(";");

			j = new Escalacao();
			j.setId(Integer.parseInt(dados[0]));
			escalacao.add(j);
		}

		buffer_entrada.close();
		return escalacao;
	}

	public void saveToFile(List<Escalacao> list) throws IOException {
		BufferedWriter buffer_saida = new BufferedWriter(new FileWriter(ARQUIVO, false));
		String separador = ";";
		for (Escalacao j : list) {
			buffer_saida.write(j.getId() + separador);
			buffer_saida.write(System.getProperty("line.separator"));
			buffer_saida.flush();
		}
		buffer_saida.close();
	}

}
