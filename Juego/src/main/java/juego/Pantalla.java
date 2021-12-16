package juego;


import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import javax.imageio.ImageIO;

public abstract class Pantalla implements Dibujable {
	
	protected BufferedImage img;
	protected int ancho;
	protected int largo;
	 
	 public Pantalla( int ancho, int largo, String resource) {
			this.ancho = ancho;
            this.largo = largo;
		try {
			String path = Paths.get(Pantalla.class.getClassLoader().getResource(resource).toURI()).toString();
			this.img = ImageIO.read(new File(path));
			} catch (IOException e) {
				throw new RuntimeException(e);
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
		}
	 
	    public void dibujarse(Graphics g) {
	        try {
	            g.drawImage(img, 0, 0, ancho, largo, null);
	        } catch (Exception e1) {
	            throw new RuntimeException(e1);
	        }
	    }
	    
	    
}
