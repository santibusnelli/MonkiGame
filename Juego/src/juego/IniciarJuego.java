package juego;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class IniciarJuego {

	public static void main(String[] args) {
		int anchoVentana = 800;
		int largoVentana = 600;
		int enemigosPorLinea = 10;
        int filasDeEnemigos = 6;
		
		System.setProperty("sun.java2d.opengl", "true");
		
		
		JFrame ventana = new JFrame("Monki game");
		ventana.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		ventana.setLocationRelativeTo(null);
		ventana.setVisible(true);
		Juego juego = new Juego(anchoVentana, largoVentana, enemigosPorLinea, filasDeEnemigos);
		ventana.add(juego);
		ventana.addKeyListener(juego);
		ventana.pack();
		
		Thread thread = new Thread(juego);
	    thread.start();
	    
	}

}
