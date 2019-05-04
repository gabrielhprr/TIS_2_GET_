package service;

import java.io.IOException;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.simpleframework.http.Query;
import org.simpleframework.http.Request;

import dao.EstatisticaDAO;
import model.Estatistica;
import model.Jogador;

public class EstatisticaService {
	private EstatisticaDAO estatisticaDAO = new EstatisticaDAO();

	public String consultarEstatistica(Integer id, Request request) {
		try {
			Estatistica estatistica = estatisticaDAO.get(id);
			return estatistica.toJson().toString();

		} catch (Exception e) {
			e.printStackTrace();
			return "Erro ao consultar Estatistica";
		}
	}
	
	
	public String listarEstatistica(Request request) {
		try {
			return listaEstatisticaJSON().toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "Erro ao consultar Estatistica";
		}
	}
	
	
	public String adicionarEstatistica(Request request) {
		Query query = request.getQuery();

		try {
			Estatistica estatistica = new Estatistica();
			estatistica.setId(query.getInteger("id"));
			estatistica.setPasseDeBola(query.getInteger("passe de bola"));
			estatistica.setGols(query.getInteger("gols"));
			estatistica.setAssistencias(query.getInteger("assistencias"));
			
			estatisticaDAO.add(estatistica);

			return listaEstatisticaJSON().toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "Erro ao adicionar Estatistica";
		}
	}
	
	public String atualizarEstatistica(Request request) {
		Query query = request.getQuery();

		try {
			Estatistica estatistica = new Estatistica();
			estatistica.setId(query.getInteger("id"));
			estatistica.setPasseDeBola(query.getInteger("passe de bola"));
			estatistica.setGols(query.getInteger("gols"));
			estatistica.setAssistencias(query.getInteger("assistencias"));
			estatisticaDAO.update(estatistica);

			return listaEstatisticaJSON().toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "Erro ao atualizar Estatistica";
		}
	}
	
	public String removerEstatistica(Request request) {
		Query query = request.getQuery();
		
		try {
			Estatistica estatistica = new Estatistica();
			estatistica.setId(query.getInteger("id"));
			
			estatisticaDAO.delete(estatistica);
			
			return listaEstatisticaJSON().toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "Erro ao excluir Estatistica.";
		}
	}
	
	private JSONObject listaEstatisticaJSON() throws NumberFormatException, IOException {
		List<Estatistica> listaEstatistica = estatisticaDAO.getAll();

		JSONArray array = new JSONArray();
		for (Estatistica j : listaEstatistica) {
			array.put(j.toJson());
		}
		JSONObject obj = new JSONObject();
		obj.put("listaEstatistica", new JSONArray(listaEstatistica));

		return obj;
	}
}
	
	
	