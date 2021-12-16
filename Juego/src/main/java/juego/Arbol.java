package juego;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import javax.imageio.ImageIO;


public class Arbol extends ElementoBasico{
	private BufferedImage img;

	public Arbol(int posicionX, int posicionY, int velocidadX, int velocidadY, int ancho, int largo, Color color) {
		super(posicionX, posicionY, velocidadX, velocidadY, ancho, largo, color);
		try {
			String path = Paths.get(Arbol.class.getClassLoader().getResource("imagenes/Arbol.png").toURI()).toString();
			this.img = ImageIO.read(new File(path));
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void dibujarse(Graphics g) {
		try {
			g.drawImage(img, getPosicionX(), getPosicionY(), this.getAncho(), this.getLargo(), null);
			g.drawRect(getPosicionX(), getPosicionY(), this.getAncho(), this.getLargo());
		} catch (Exception e1) {
	            throw new RuntimeException(e1);
	    }
	}
}
