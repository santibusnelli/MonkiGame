package juego;

import java.awt.Color;
import java.awt.Graphics;

public class Bomberos extends Enemigo{

	public Bomberos(int posicionX, int posicionY, int velocidadX, int velocidadY, int ancho, int largo, Color color) {
		super(posicionX, posicionY, velocidadX, velocidadY, ancho, largo, color);
	}

	@Override
	public void dibujarse(Graphics g) {
		g.setColor(getColor());
		g.fillRect(getPosicionX(), getPosicionY(), getAncho(), getLargo());
	}


}
