package model;

import java.util.ArrayList;

import dao.JogadorDAO;
import model.enums.Escala;


public class Principal {
	public static void main(String[] args) {
		
		Jogador jog = new Jogador();
		Jogador jog1 = new Jogador();
		JogadorDAO jogDAO = new JogadorDAO();
		
		jog.setDrible(Escala.QUATRO);
		jog.setFolego(Escala.UM);
		jog.setId(5);
		jog.setNome("Luiz");
		jog.setPosicao("Puto");
		jog.setVelocidade(Escala.TRES);
		jogDAO.add(jog);
		
		
		jog1.setDrible(Escala.QUATRO);
		jog1.setFolego(Escala.UM);
		jog1.setId(5);
		jog1.setNome("Luiz");
		jog1.setPosicao("Puto");
		jog1.setVelocidade(Escala.TRES);
		
		jog = jogDAO.get(5);

		
		
		
		System.out.println(jogDAO.get(5).toJson().toString());
		
	
		
		
		
	}
}
