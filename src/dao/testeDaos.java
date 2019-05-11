package dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.Estatistica;
import model.Jogador;
import model.Partida;
import model.Time;
import dao.PartidaDAO;


public class testeDaos {

	public static void main(String[] args) {
		
		//TESTE DE ESTATISTICA
//		Estatistica estatistica = new Estatistica();
//
//		JogadorDAO jog = new JogadorDAO();
//		estatistica.setJogador(jog.get(3));
//		estatistica.setAssistencias(99);
//		estatistica.setGols(1111111);
//		estatistica.setPasseDeBola(888);
//		estatistica.setId(7);
//
		EstatisticaDAO dao = new EstatisticaDAO();
//		
//		
//		try {
////			dao.add(estatistica);
////			dao.update(estatistica);//IO necessário para esse tipo método.
////			dao.delete(estatistica);
////			System.out.println(dao.get(11).toJson().toString());
//			List<Estatistica> lista = dao.getAll();
////			for(Estatistica e : lista) {
////				System.out.println(e.toJson().toString());
////			}
//		} catch (NumberFormatException | IOException e ) {
//			e.printStackTrace();
//		}
		
		
		//TESTE DE PARTIDA
		
		//no GETALL não está puxando pois está com erro no Json
		//no DELETE está deixando o ID.
		
		
		
		Partida partida = new Partida();
		PartidaDAO daopartida = new PartidaDAO();
		
		partida.setId(1);
		partida.getEstatisticasJogador().add(dao.get(8));
		partida.getEstatisticasJogador().add(dao.get(6));
		partida.getEstatisticasJogador().add(dao.get(5));
		
		daopartida.add(partida);
		
		
		try {
			System.out.println(daopartida.getAll().toString());
//			daopartida.delete(partida);
//			daopartida.update(partida);
		
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		
		//System.out.println(daopartida.get(1).toJson().toString());
		
		
		
		
		
	
	
	
	
	
	
	
	}

}
