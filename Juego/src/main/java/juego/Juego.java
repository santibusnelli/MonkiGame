package juego;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import java.util.Iterator;


public class Juego extends JPanel implements KeyListener, Runnable {
	private final static int PANTALLA_INICIO = 1;
	private final static int PANTALLA_JUEGO = 2;
	private final static int PANTALLA_PERDEDOR = 3;
	private final static int PANTALLA_GANADOR = 4;
	private static final long serialVersionUID = 1L;
	private int pantallaActual;
	private int anchoJuego;
	private int largoJuego;
	private Banana banana;
	private Mono mono;
	private Arbol arbol;
	private List<Enemigo> enemigos;
	private int enemigosPorLinea;
    private Vidas vidas;
    private Puntaje puntaje;
	private Pantalla pantallaInicio;
	private Pantalla pantallaGanaste;
	private Pantalla pantallaFondo;
	private Pantalla pantallaPerdiste;
	
	public Juego(int anchoJuego, int largoJuego, int enemigosPorLinea) {
		this.pantallaActual = PANTALLA_INICIO;
		this.anchoJuego = anchoJuego;
		this.largoJuego = largoJuego;		
		this.enemigos = new ArrayList<Enemigo>();
		this.enemigosPorLinea = enemigosPorLinea;
		this.pantallaInicio = new PantallaInicio(anchoJuego, largoJuego, "imagenes/PantallaInicio.jpg");
		this.pantallaFondo = new PantallaJuego(anchoJuego, largoJuego, "imagenes/PantallaFondo.jpg");
		inicializarJuego();
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension (anchoJuego, largoJuego);
	}
	
	private void inicializarJuego(){
		this.enemigos.clear();
		this.pantallaPerdiste = null;
		this.pantallaGanaste = null;
		this.mono = new Mono(400, 600, 0, 0, 40, 40);
		this.arbol = new Arbol(380, 0, 0, 0, 60, 60);
        this.vidas = new Vidas(700, 590, new Font("Arial Black", 10, 20), Color.red, 3);
        this.banana = new Banana(new Random().nextInt(750),new Random().nextInt(550), 0, 0, 20, 10);
        this.puntaje = new Puntaje(0, 590, new Font("Arial black", 10, 20), Color.blue);
		agregarEnemigos(enemigosPorLinea);
	}

	@Override
	public void run() {
		while (true) {
			
			if (pantallaActual == PANTALLA_JUEGO) {
				actualizarJuego();
			}
			
			dibujarJuego();
			esperar(10);
		}
	}
	
	@Override
	protected void paintComponent (Graphics g) {
		super.paintComponent(g);
		if (pantallaActual == PANTALLA_INICIO) {
			pantallaInicio.dibujarse(g);
        }if (pantallaActual == PANTALLA_JUEGO) {
        	dibujar(g);
        }if(pantallaActual == PANTALLA_PERDEDOR) {
        	if (this.pantallaPerdiste == null) {
        		this.pantallaPerdiste = new PantallaPerdedor(anchoJuego, largoJuego, "imagenes/PantallaPerdiste.jpg", this.puntaje.getPuntaje());
        	}
        	pantallaPerdiste.dibujarse(g);
        }if(pantallaActual == PANTALLA_GANADOR) {
        	if(this.pantallaGanaste == null) {
        		this.pantallaGanaste = new PantallaGanador(anchoJuego, largoJuego, "imagenes/PantallaGanaste.jpg", this.puntaje.getPuntaje());
        	}
        	pantallaGanaste.dibujarse(g);
        }
        
	}
	
	public void dibujar(Graphics g) {
		pantallaFondo.dibujarse(g);
		mono.dibujarse(g);
		arbol.dibujarse(g);
		dibujarEnemigos(g);
		vidas.dibujarse(g);
		puntaje.dibujarse(g);
		banana.dibujarse(g);
	}
	
	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {

        if (pantallaActual == PANTALLA_INICIO) {
            inicializarJuego();
            pantallaActual = PANTALLA_JUEGO;
        }
        if (pantallaActual == PANTALLA_PERDEDOR || pantallaActual == PANTALLA_GANADOR) {
            pantallaActual = PANTALLA_INICIO;
        }
        
        if (pantallaActual == PANTALLA_JUEGO) {

			if (arg0.getKeyCode() == KeyEvent.VK_RIGHT) {
				mono.setPosicionX(mono.getPosicionX() + 20);
			}
			if (arg0.getKeyCode() == KeyEvent.VK_LEFT) {
				mono.setPosicionX(mono.getPosicionX() - 20);
			}
			if (arg0.getKeyCode() == KeyEvent.VK_UP) {
				mono.setPosicionY(mono.getPosicionY() - 20);
			}
			if (arg0.getKeyCode() == KeyEvent.VK_DOWN) {
				mono.setPosicionY(mono.getPosicionY() + 20);
			}
        }
	}
	
	private void actualizarJuego() {
		verificarEstadoAmbiente();
		mono.moverse();
		moverEnemigos();
	}
	
	private void dibujarJuego() {
		this.repaint();
	}
	
	private void esperar(int milisegundos) {
        try {
            Thread.sleep(milisegundos);
        } catch (Exception e1) {
            throw new RuntimeException(e1);
        }
    }
	
	public void agregarEnemigo(Enemigo enemigo) {
        this.enemigos.add(enemigo);
    }
	
	private void agregarEnemigos(int enemigosPorLinea) {
		for (int x = 0; x < enemigosPorLinea; x++) {
            	agregarEnemigo(new Moto(0, 100, 10, 0, 40, 30));
				agregarEnemigo(new Patrulla(600, 150, -5, 0, 60, 40));
            	agregarEnemigo(new Bulldozer(0 + x * 200, 200, 1, 0, 80, 40));
				agregarEnemigo(new Ambulancia(600, 250, -6, 0, 80, 40));

				agregarEnemigo(new Bomberos(600, 350, -4, 0, 120, 40));
				agregarEnemigo(new Auto(0 + x * 400, 400, 3, 0, 60, 40));
				agregarEnemigo(new Patrulla(600 + x * -100, 450, -5, 0, 60, 40));
				agregarEnemigo(new Bulldozer(0, 500, 1, 0, 80, 40)); 
        }
		
	}
	
	private void moverEnemigos() {
        for (Enemigo enemigo : enemigos) {
            enemigo.moverse();
        }
    }

    private void dibujarEnemigos(Graphics g) {
        for (Enemigo enemigo : enemigos) {
            enemigo.dibujarse(g);
        }
    }
	
	   private void verificarEstadoAmbiente() {
		   verificarColisionContraVentana();
		   verificarColisionEntreMonoYEnemigos();
		   verificarColisionEntreMonoYBanana();
		   verificarColisionEnemigosContraVentana();
		   verificarFinDeJuego();
	   }
	   
	   private void verificarColisionEnemigosContraVentana() {
			for (Enemigo enemigo : enemigos) {
				if (enemigo.getPosicionX() >= anchoJuego) {
					enemigo.setPosicionX(1);
				}if (enemigo.getPosicionX() <= 0 - enemigo.getAncho()) {
					enemigo.setPosicionX(anchoJuego - 1);
				}
			}
		}
	   private void verificarColisionContraVentana(){
		   if(mono.getPosicionX()>=760) {
				mono.setPosicionX(760);
			}else if (mono.getPosicionX() <= 0) {
				mono.setPosicionX(0);
			}
		   if(mono.getPosicionY()>=560) {
				mono.setPosicionY(560);
			} else if (mono.getPosicionY()<=0) {
				mono.setPosicionY(0);
			}
	   }
	   
	   private void verificarColisionEntreMonoYEnemigos	() {
			Iterator<Enemigo> iterador = enemigos.iterator();
			while (iterador.hasNext()) {
				Enemigo enemigo = iterador.next();
				if (enemigo.hayColision(mono)) {
					mono.morir();
					vidas.perderVida();
				}
			}
		}
	   
	   private void verificarColisionEntreMonoYBanana() {
		   if (mono.hayColision(banana)) {
			   this.banana = new Banana(new Random().nextInt(750),new Random().nextInt(550), 0, 0, 20, 10);
			   puntaje.sumarPunto();
		   }
	   }

	   private void verificarFinDeJuego() {
	        if (vidas.getVidas() == 0) {
	            pantallaActual = PANTALLA_PERDEDOR;
	        }if(mono.hayColision(arbol)) {
	        	pantallaActual = PANTALLA_GANADOR;
			   }
	            
	   }
	
}
