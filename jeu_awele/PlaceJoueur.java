package jeu_awele;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class PlaceJoueur extends JPanel{

	private static final long serialVersionUID = 1350469316642125075L;
	private String nom;
	private String desc;
	private Color col;
	private Color temp;
	
	public PlaceJoueur(String p_nom, Color p_col) {
		super();
		nom = desc = p_nom;
		temp = col = p_col;
		setPreferredSize(new Dimension(getWidth(), 60));
	}
	
	public void paintComponent(Graphics g) {
		g.setColor(temp);
		g.fillRoundRect(0, 0, getWidth(), getHeight(), 60, 60);
		g.setColor(Color.black);
		g.drawString(desc, (getWidth()/2) - (2*desc.length()), getHeight()/2);
	}
	
	public void prendreLaMain() {
		temp = Color.cyan;
		desc = nom + " (à vous de jouer)";
		repaint();
	}
	
	public void lacherLaMain() {
		temp = col;
		desc = nom;
		repaint();
	}
	
	public void afficherErreur(String msg) {
		temp = Color.red;
		desc = nom + " " + msg;
		repaint();
	}
	
	public void afficherDetails(String msg, Color col) {
		temp = col;
		desc = nom + " " + msg;
		repaint();
	}
}
