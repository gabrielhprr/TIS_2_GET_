package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.Jogador;
import model.Partida;
import model.enums.Escala;

public class PartidaDAO implements GenericDAO<Partida, Integer> {
	static final String ARQUIVO = "partida.txt";
	static final String SEQUENCE = "sequence_partida.txt";
	private EstatisticaDAO estatisticaDAO = new EstatisticaDAO();

	@Override
	public Partida get(Integer id) {

		Partida retorno = null;
		Partida j = null;

		try (BufferedReader buffer_entrada = new BufferedReader(new FileReader(ARQUIVO))) {
			String linha;

			while ((linha = buffer_entrada.readLine()) != null) {
				String[] dados = linha.split(";");

				j = new Partida();
				j.setId(Integer.parseInt(dados[0]));
				/*
				 * j.setNome(dados[1]); j.setPosicao(dados[2]);
				 */

				if (id.equals(j.getId())) {
					retorno = j;
					break;
				}
			}
		} catch (Exception e) {
			System.out.println("ERRO ao ler a `Partida '" + j.getId() + "' do disco rígido!");
			e.printStackTrace();
		}
		return retorno;
	}

	@SuppressWarnings("resource")
	@Override
	public void add(Partida t) {
		try {
			BufferedReader bufferInSequence = new BufferedReader(new FileReader(SEQUENCE));
			BufferedWriter bufferOutPartida = new BufferedWriter(new FileWriter(ARQUIVO, true));

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
			bufferOutPartida.write(generatedId + separadorDeAtributo);
			bufferOutPartida.write(t.getId() + separadorDeAtributo);
			bufferOutPartida.write(System.getProperty("line.separator"));
			bufferOutPartida.flush();

		} catch (Exception e) {
			System.out.println("ERRO ao gravar a Partida no disco!");
			e.printStackTrace();
		}
	}

	@Override
	public void update(Partida t) throws NumberFormatException, IOException {
		List<Partida> Partida = getAll();
		int index = Partida.indexOf(t);
		if (index != -1) {
			Partida.set(index, t);
			saveToFile(Partida);
		}
	}

	@Override
	public void delete(Partida t) throws NumberFormatException, IOException {
		List<Partida> partida = getAll();
		int index = partida.indexOf(t);
		if (index != -1) {
			partida.remove(index);
			saveToFile(partida);
		}
	}

	@Override
	public List<Partida> getAll() throws FileNotFoundException, NumberFormatException, IOException {
		List<Partida> partida = new ArrayList<Partida>();
		Partida j = null;
		BufferedReader buffer_entrada = new BufferedReader(new FileReader(ARQUIVO));
		String linha;

		while ((linha = buffer_entrada.readLine()) != null) {
			String[] dados = linha.split(";");

			j = new Partida();
			j.setId(Integer.parseInt(dados[0]));
			//j.setId(dados[1]);
			partida.add(j);
		}

		buffer_entrada.close();
		return partida;
	}



	public void saveToFile(List<Partida> list) throws IOException {
		BufferedWriter buffer_saida = new BufferedWriter(new FileWriter(ARQUIVO, false));
		String separador = ";";
		for (Partida j : list) {
			buffer_saida.write(j.getId() + separador);
			buffer_saida.write(j.getEstatisticasJogador() + separador);
			buffer_saida.write(System.getProperty("line.separator"));
			buffer_saida.flush();
		}
		buffer_saida.close();
	}
}
