package juego;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class PantallaInicio extends Pantalla {

	public PantallaInicio(int ancho, int largo, String resource) {
		super(ancho, largo, resource);
		
	}

	public void dibujarse(Graphics g) {
		super.dibujarse(g);
		mostrarMensaje(g);
	}
	
	public void mostrarMensaje(Graphics g) {
		g.setColor(Color.black);
        g.setFont(new Font("Arial black", 30, 50));
        g.drawString("Monki Game", 10, 50);
        g.setColor(Color.red);
        g.setFont(new Font("Arial black", 40, 30));
        g.drawString("Presione cualquier tecla para iniciar", 100, 500);
    }

	
}
	
