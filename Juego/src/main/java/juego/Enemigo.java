package juego;

	import java.awt.Color;

	public abstract class Enemigo extends ElementoBasico {

	    public Enemigo(int posicionX, int posicionY, int velocidadX, int velocidadY, int ancho, int largo, Color color) {
	        super(posicionX, posicionY, velocidadX, velocidadY, ancho, largo, color);
	    }
	}
