package coche;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class JLabelCoche extends JLabel {
	
	private Coche coche;
	
	public JLabelCoche(ImageIcon m, Coche coche) {
		super(m);
		this.coche = coche;
	}
	
	@Override
	public void paintComponent(Graphics gr) {
		Graphics2D g = (Graphics2D) gr;
		g.rotate(-Math.PI*(coche.getAngulo()-90)/180, this.getWidth()/2, this.getHeight()/2);
		super.paintComponent(gr);
	}
	
}
