package juego;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import javax.imageio.ImageIO;


public class Mono extends ElementoBasico{
	private BufferedImage img;

	public Mono(int posicionX, int posicionY, int velocidadX, int velocidadY, int ancho, int largo, Color color) {
		super(posicionX, posicionY, velocidadX, velocidadY, ancho, largo, color);
		String path = Paths.get(Mono.class.getClassLoader().getResource("imagenes/android.png").getPath())
                .toString();
        try {
            this.img = ImageIO.read(new File(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
	}
	@Override
	public void dibujarse(Graphics g) {
		try {
			g.drawImage(img, getPosicionX(), getPosicionY(), this.getAncho(), this.getLargo(), null);
		} catch (Exception e1) {
	            throw new RuntimeException(e1);
	    }
	}
}
