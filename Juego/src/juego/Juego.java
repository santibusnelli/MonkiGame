package juego;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

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
	//private Pantalla pantallaInicio;
	
	public Juego(int anchoJuego, int largoJuego) {
		this.pantallaActual = PANTALLA_INICIO;
		this.anchoJuego = anchoJuego;
		this.largoJuego = largoJuego;
		this.mono = new Mono(30, 30, 0, 0, 30, 30, Color.DARK_GRAY);
		//this.pantallaInicio = new Pantalla(anchoJuego, largoJuego, "imagenes/monoInicio.jpeg" );
		inicializarJuego();
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension (anchoJuego, largoJuego);
	}
	
	private void inicializarJuego(){
		
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
		
		super.paintComponent(g);
		if (pantallaActual == PANTALLA_INICIO) {
            //dibujarInicioJuego(g);
        }if (pantallaActual == PANTALLA_JUEGO) {
        
        	dibujar(g);
        }
	}
	
	public void dibujar(Graphics g) {
		mono.dibujarse(g);
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
				mono.setPosicionX(mono.getPosicionX() + 40);
			}
			if (arg0.getKeyCode() == KeyEvent.VK_LEFT) {
				mono.setPosicionX(mono.getPosicionX() - 40);
			}
			if (arg0.getKeyCode() == KeyEvent.VK_UP) {
				mono.setPosicionY(mono.getPosicionY() - 40);
			}
			if (arg0.getKeyCode() == KeyEvent.VK_DOWN) {
				mono.setPosicionY(mono.getPosicionY() + 40);
			}
        }
	}
	
	private void actualizarJuego() {
		mono.moverse();
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
	/*
	private void dibujarInicioJuego(Graphics g) {
		pantallaInicio.dibujarse(g);
	}
	*/
	
}
