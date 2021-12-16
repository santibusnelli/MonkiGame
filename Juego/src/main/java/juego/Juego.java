package juego;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
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
	private Mono mono;
	private Arbol arbol;
	private List<Enemigo> enemigos;
	private int enemigosPorLinea;
    private int filasDeEnemigos;
    private Vidas vidas;
	private Pantalla pantallaInicio;
	private Pantalla pantallaGanaste;
	private Pantalla pantallaFondo;
	private Pantalla pantallaPerdiste;
	
	public Juego(int anchoJuego, int largoJuego, int enemigosPorLinea, int filasDeEnemigos ) {
		this.pantallaActual = PANTALLA_INICIO;
		this.anchoJuego = anchoJuego;
		this.largoJuego = largoJuego;		
		this.enemigos = new ArrayList<Enemigo>();
		this.enemigosPorLinea = enemigosPorLinea;
        this.filasDeEnemigos = filasDeEnemigos;
		this.pantallaInicio = new PantallaInicio(anchoJuego, largoJuego, "imagenes/PantallaInicio.jpg");
		this.pantallaGanaste = new PantallaGanador(anchoJuego, largoJuego, "imagenes/PantallaGanaste.jpg");
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
		this.mono = new Mono(400, 600, 0, 0, 40, 40, Color.DARK_GRAY);
		this.arbol = new Arbol(380, 0, 0, 0, 60, 60, Color.DARK_GRAY);
        this.vidas = new Vidas(700, 590, new Font("Arial Black", 10, 20), Color.red, 1);
		agregarEnemigos(enemigosPorLinea, filasDeEnemigos);
	}

	@Override
	public void run() {
		while (true) {
			
			if (pantallaActual == PANTALLA_JUEGO) {
				actualizarJuego();
			}
			
			dibujarJuego();
			esperar(100);
		}
	}
	
	@Override
	protected void paintComponent (Graphics g) {
		//super.paintComponent(g);
		this.limpiarPantalla(g);
		if (pantallaActual == PANTALLA_INICIO) {
			pantallaInicio.dibujarse(g);
        }if (pantallaActual == PANTALLA_JUEGO) {
        	dibujar(g);
        }if(pantallaActual == PANTALLA_PERDEDOR) {
        	if (this.pantallaPerdiste == null) {
        		this.pantallaPerdiste = new PantallaPerdedor(anchoJuego, largoJuego, "imagenes/PantallaPerdiste.jpg");
        	}
        	pantallaPerdiste.dibujarse(g);
        }if(pantallaActual == PANTALLA_GANADOR) {
        	pantallaGanaste.dibujarse(g);
        }
        
	}
	
	public void dibujar(Graphics g) {
		pantallaFondo.dibujarse(g);
		mono.dibujarse(g);
		arbol.dibujarse(g);
		dibujarEnemigos(g);
		vidas.dibujarse(g);
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
	
	private void agregarEnemigos(int enemigosPorLinea2, int filasDeEnemigos2) {
		for (int x = 1; x < enemigosPorLinea; x++) {
            for (int y = 1; y < filasDeEnemigos; y++) {
            	
            	//agregarEnemigo(new Patrulla(500 + x * -200, 120, 2, 0, 60, 30, Color.green));
				agregarEnemigo(new Patrulla(500, 400, -20, 0, 60, 40, Color.black));
				agregarEnemigo(new Ambulancia(500, 200, -15, 0, 100, 40, Color.gray));
				agregarEnemigo(new Bomberos(500, 500, -1, 0, 120, 40, Color.red));		
            }
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
    
    private void limpiarPantalla(Graphics graphics) {
        graphics.setColor(Color.cyan);
        graphics.fillRect(0, 0, anchoJuego, largoJuego);
    }

	
	   private void verificarEstadoAmbiente() {
		   verificarColisionContraVentana();
		   verificarColisionEntreMonoYEnemigos();
		   verificarFinDeJuego();
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

	   private void verificarFinDeJuego() {
	        if (vidas.getVidas() == 0) {
	            pantallaActual = PANTALLA_PERDEDOR;
	        }if(mono.hayColision(arbol)) {
	        	pantallaActual = PANTALLA_GANADOR;
			   }
	            
	   }
	
}
