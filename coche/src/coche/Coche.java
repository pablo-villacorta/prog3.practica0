package coche;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Coche {
	
	private double x, y;
	private int width, height;
	private double vel;
	private double angulo; //en grados
	private JLabelCoche img;
	private JPanel panel;
	
	public Coche(JPanel panel) {
		this.x = 100;
		this.y = 100;
		this.width = 150;
		this.height = 75;
		this.vel = 0;
		this.angulo = 90; 
		this.panel = panel;
		Image image = getScalatedImage();
		ImageIcon icon = new ImageIcon(image);
		//ImageIcon icon = new ImageIcon("coche.png");
		img = new JLabelCoche(icon, this);
	}
	
	public void update() {
		double ax, ay;
		ax = vel*Math.sin(Math.PI*angulo/180);
		ay = vel*Math.cos(Math.PI*angulo/180);
		this.x += ax;
		this.y += ay;
		img.setBounds((int) x,(int) y, width, width);
		rebotar();
	}
	
	private void rebotar() {
		int width, height;
		width = panel.getWidth();
		height = panel.getHeight();
		if(this.x < 0) {
			if(this.angulo <= 270) {
				this.angulo -= 2*(this.angulo-180);
			} else {
				this.angulo += 2*(360-this.angulo);
			}
		} else if(this.x + this.width*Math.sin(Math.PI*this.angulo/180) > width) {
			if(this.angulo <= 90) {
				this.angulo -= 2*(this.angulo);
			} else {
				this.angulo += 2*(180-this.angulo);
			}
		}
		simplificarAngulo();
		
		if(this.y < 0) {
			if(this.angulo <= 180) {
				this.angulo -= 2*(this.angulo-90);
			} else {
				this.angulo += 2*(270-this.angulo);
			}
		} else if(this.y + this.height*(Math.cos(Math.toRadians(angulo)))> height) {
			if(this.angulo < 90) {
				this.angulo += 2*(90-this.angulo);
			} else {
				this.angulo -= 2*(this.angulo-270);
			}
		}
		simplificarAngulo();
	}
	
	public void acelerar() {
		this.vel += 1;
	}
	
	public void frenar() {
		if(vel >= 1) {
			this.vel -= 1;
		}
	}
	
	public void girarIzquierda() {
		this.angulo += 5;
		simplificarAngulo();
	}
	
	public void girarDerecha() {
		this.angulo -= 5;
		simplificarAngulo();
	}
	private void simplificarAngulo() {
		if(this.angulo >= 360) {
			this.angulo -= 360;
		}
		if(this.angulo < 0) {
			this.angulo += 360;
		}
	}
	
	
	public int getX() {
		return (int) this.x;
	}
	
	public int getY() {
		return (int) this.y;
	}
	
	public double getVel() {
		return this.vel;
	}
	
	public double getAngulo() {
		return this.angulo;
	}
	
	public JLabel getImg() {
		return this.img;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	private Image getScalatedImage() {
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("coche.png"));
		} catch(Exception e) {
			e.printStackTrace();
		}
		Image dimg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		return dimg;
	}
	
}
