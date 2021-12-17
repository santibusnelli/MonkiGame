package juego;


public interface Elemento extends Dibujable {

	public int getAncho();

	public int getLargo();
	
	public int getPosicionX();

	public int getPosicionY();

	public int getVelocidadX();

	public int getVelocidadY();

	public void moverse();

	public boolean hayColision(Elemento elemento);
}
