package juego;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Juego extends JPanel {
	private static final long serialVersionUID = 1L;
	private int anchoJuego;
	private int largoJuego;
	
	public Juego(int anchoJuego, int largoJuego) {
		this.anchoJuego = anchoJuego;
		this.largoJuego = largoJuego;
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension (anchoJuego, largoJuego);
	}
	
	@Override
	protected void paintComponent (Graphics g) {
		super.paintComponent(g);
		dibujar(g);
	}
	
	public void dibujar(Graphics g) {
		dibujarMono(g);
	}
	
	public void dibujarMono(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(30, 30, 40, 40);
	}
}
