package model;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;


import org.json.JSONObject;

public class Partida implements JsonFormatter{
	private Integer id;
	
	List<Estatistica> estatisticasJogador;

	public Partida() {
		this.estatisticasJogador = new ArrayList<Estatistica>();
	}
	
	public Integer getId() {
		return id;
	}

	public List<Estatistica> getEstatisticasJogador() {
		return estatisticasJogador;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setEstatisticasJogador(List<Estatistica> estatisticasJogador) {
		this.estatisticasJogador = estatisticasJogador;
	} 
	
	@Override
	public JSONObject toJson() {
		JSONObject obj = new JSONObject();
		obj.put("id", this.id);
		obj.put("estatisticas", this.estatisticasJogador.toArray());
		return obj;
	}
	
	@Override//exemplo de time
	public JSONArray toJsonArray() {
		JSONArray array = new JSONArray();
		for(Estatistica j : this.estatisticasJogador) {
			array.put(j.toJson());
		}
		return array;
	}
	
	@Override
	public boolean equals(Object obj) {
		return this.id == ((Partida) obj).id;
	}
}
