package juego;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class PantallaGanador extends Pantalla {
	
	private int puntos;

	public PantallaGanador(int ancho, int largo, String resource, int puntos) {
		super(ancho, largo, resource);
		this.puntos = puntos;
		
	}

	public void dibujarse(Graphics g) {
		super.dibujarse(g);
		mostrarMensaje(g);
	}
	
	public void mostrarMensaje(Graphics g) {
		g.setColor(Color.white);
        g.setFont(new Font("Arial black", 50, 65));
        g.drawString("Felicidades, ganaste!!", 10, 100);
        g.setColor(Color.MAGENTA);
        g.setFont(new Font("Arial black", 30, 50));
        if (puntos > 0) {
        	g.drawString("Recolectaste: " + puntos + " bananas", 80, 300); 
        }else {
            g.drawString("No recolectaste bananas :(", 40, 300);
        }
        g.setColor(Color.red);
        g.setFont(new Font("Arial black", 40, 30));
        g.drawString("Presione cualquier tecla para continuar", 80, 500);
    }
	
}
