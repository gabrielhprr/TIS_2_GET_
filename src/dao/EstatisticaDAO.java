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
import model.enums.Escala;

public class EstatisticaDAO implements GenericDAO<Jogador, Integer> {
	static final String ARQUIVO = "estatistica.txt";
	static final String SEQUENCE = "sequence_estatistica.txt";

	@Override
	public Jogador get(Integer id) {
		Estatistica retorno = null;
		Estatistica j = null;

		try (BufferedReader buffer_entrada = new BufferedReader(new FileReader(ARQUIVO))) {
			String linha;

			while ((linha = buffer_entrada.readLine()) != null) {
				String[] dados = linha.split(";");

				j = new Estatistica();
				j.setId(Integer.parseInt(dados[0]));
				j.setNome(dados[1]);

				if (id.equals(j.getId())) {
					retorno = j;
					break;
				}
			}
		} catch (Exception e) {
			System.out.println("ERRO ao ler a estatistica '" + j.getId() + "' do disco r�gido!");
			e.printStackTrace();
		}
		return retorno;
	}

	@SuppressWarnings("resource")
	@Override
	public void add(Estatistica t) {
		try {
			BufferedReader bufferInSequence = new BufferedReader(new FileReader(SEQUENCE));
			BufferedWriter bufferOutEstatistica = new BufferedWriter(new FileWriter(ARQUIVO, true));

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
			bufferOutEstatistica.write(generatedId + separadorDeAtributo);
			bufferOutEstatistica.write(t.getNome() + separadorDeAtributo);
			bufferOutEstatistica.write(System.getProperty("line.separator"));
			bufferOutEstatistica.flush();

		} catch (Exception e) {
			System.out.println("ERRO ao gravar o Estatistica no disco!");
			e.printStackTrace();
		}
	}

	@Override
	public void update(Jogador t) throws NumberFormatException, IOException {
		List<Estatistica> estatistica = getAll();
		int index = estatistica.indexOf(t);
		if (index != -1) {
			estatistica.set(index, t);
			saveToFile(estatistica);
		}

	}

	@Override
	public void delete(Jogador t) throws NumberFormatException, IOException {

		List<Estatistica> estatistica = getAll();
		int index = estatistica.indexOf(t);
		if (index != -1) {
			estatistica.remove(index);
			saveToFile(estatistica);
		}

	}

	@Override
	public List<Jogador> getAll() throws FileNotFoundException, NumberFormatException, IOException {
		List<Estatistica> estatistica = new ArrayList<Estatistica>();
		Estatistica j = null;
		BufferedReader buffer_entrada = new BufferedReader(new FileReader(ARQUIVO));
		String linha;

		while ((linha = buffer_entrada.readLine()) != null) {
			String[] dados = linha.split(";");

			j = new Estatistica();
			j.setId(Integer.parseInt(dados[0]));
			j.setNome(dados[1]);

			estatistica.add(j);
		}
		buffer_entrada.close();
		return estatistica;

	}

	public void saveToFile(List<Estatistica> list) throws IOException {
		BufferedWriter buffer_saida = new BufferedWriter(new FileWriter(ARQUIVO, false));
		String separador = ";";
		for (Estatistica j : list) {
			buffer_saida.write(j.getId() + separador);
			buffer_saida.write(j.getNome() + separador);
			buffer_saida.write(System.getProperty("line.separator"));
			buffer_saida.flush();
		}
		buffer_saida.close();
	}

}
