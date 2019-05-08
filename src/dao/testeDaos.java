package dao;

import java.io.IOException;
import java.util.List;

import model.Estatistica;

public class testeDaos {

	public static void main(String[] args) {
		Estatistica estatistica = new Estatistica();

		JogadorDAO jog = new JogadorDAO();
		estatistica.setJogador(jog.get(3));
		estatistica.setAssistencias(99);
		estatistica.setGols(777);
		estatistica.setPasseDeBola(888);
		estatistica.setId(null);

		EstatisticaDAO dao = new EstatisticaDAO();
		
		
		try {
//			dao.add(estatistica);
			dao.update(estatistica);//IO necessário para esse tipo método.
//			dao.delete(estatistica);
//			System.out.println(dao.get(2).toJson().toString());
//			List<Estatistica> lista = dao.getAll();
//			for(Estatistica e : lista) {
//				System.out.println(e.toJson().toString());
//			}
		} catch (NumberFormatException | IOException e ) {
			e.printStackTrace();
		}
		
		
		
		
		
		
	
	
	
	
	
	
	
	}

}
