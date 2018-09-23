package coche;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Ventana extends JFrame {

	private JButton bot_ac, bot_fr, bot_iz, bot_de;
	private JPanel mainPanel;
	
	private Coche coche;
	
	public Ventana() {
		super();
		crearContenido();

		coche = new Coche(mainPanel);
		JLabel imagen = coche.getImg();
		mainPanel.add(imagen);
		imagen.setLocation(coche.getX(),coche.getY());
		imagen.setSize(coche.getWidth(),coche.getHeight());
		
		addListeners();
		
		setSize(800,700);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		Thread t = new Thread() {
			@Override
			public void run() {
				super.run();
				while(true) {
					coche.update();
					imagen.setLocation(coche.getX(), coche.getY());
					try {
					Thread.sleep(40);
					} catch(Exception e) {}
				}
			}
		};
		t.start();
	}
	
	private void addListeners() {
		bot_ac.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				coche.acelerar();
			}
		});
		
		bot_fr.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				coche.frenar();
			}
		});
		
		bot_iz.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				coche.girarIzquierda();
			}
		});
		
		bot_de.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				coche.girarDerecha();
			}
		});
	}
	
	private void crearContenido() {
		Container cp = getContentPane();
		
		bot_ac = new JButton("Acelerar");
		bot_fr = new JButton("Frenar");
		bot_iz = new JButton("Izquierda");
		bot_de = new JButton("Derecha");
		JPanel botonera = new JPanel(new FlowLayout());
		cp.add(botonera, BorderLayout.SOUTH);
		botonera.add(bot_ac);
		botonera.add(bot_fr);
		botonera.add(bot_iz);
		botonera.add(bot_de);
		
		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		cp.add(mainPanel, BorderLayout.CENTER);
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Ventana();
			}
		});	
	}

}
