package model;

import java.util.List;

public class Escalacao {

	private boolean finalizado;
	List<Jogador> listaJogador;
	private Integer id;

	public Escalacao(boolean finalizado, List<Jogador> listaJogador) {
		this.setFinalizado(true);
		this.listaJogador = listaJogador;
	}

	public void incluirJogador(Jogador jogador) {
		if (listaJogador < 11) {
			this.listaJogador = jogador;
			this.listaJogador++;
		} else {
			System.out.println("Escalação Completa");
		}
	}

	public void finalizarEscalacao() {
		if (listaJogador == 11)
			setFinalizado(true);
	}

	public boolean isFinalizado() {
		return finalizado;
	}

	public List<Jogador> getListaJogador() {
		return listaJogador;
	}

	public void setFinalizado(boolean finalizado) {
		this.finalizado = finalizado;
	}

	public void setListaJogador(List<Jogador> listaJogador) {
		this.listaJogador = listaJogador;
	}

	public Integer getId() {
		return id;
	}

	public void setId( Integer id) {
		this.id = id;
	}
}
