package jeu_awele;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

import tam.fr.observer.Observable;
import tam.fr.observer.Observateur;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Dimension;

public class Case extends JPanel implements MouseListener, Observable{
	private static final long serialVersionUID = -8383533930279872174L;
	private int nbBilles;
	private int numero;
	private Joueur prop;
	private Color couleur;
	private Color tmp;
	private ArrayList<Observateur> listObs = new ArrayList<Observateur>();
	
	public Case(Joueur prop, int num, Color col) {
		super();
		setSize(new Dimension(50, 50));
		Border lineborder = BorderFactory.createLineBorder(Color.black, 2, true);
		super.setBorder(lineborder);
		this.prop = prop;
		numero = num;
		nbBilles = 4;
		couleur = tmp = col;
		this.addMouseListener(this);
	}
	public Case(int num) {
		this(new Joueur(), num, Color.black);
	}
	
	
	public void paintComponent(Graphics g) {
		g.setColor(tmp);
		g.fillRoundRect(0, 0, this.getWidth(), this.getHeight(), 2, 2);
		g.setColor(Color.white);
		int x = 2, y = 2;
		for(int i = 0; i < nbBilles; i++) {
			g.fillOval(x, y, 25, 25);
			x += 28;
			if(this.getWidth() - x < 25) {
				x = 2;
				y += 27;
			}
		}
	}
	
	public String toString() {
		return "propriétaire: " + prop + " : " + nbBilles + " billes";
	}
	
	public boolean estVide() {
		return nbBilles == 0;
	}
	
	public int getNumero() {
		return numero;
	}
	
	public int getNombreBilles() {
		return nbBilles;
	}
	
	public void incNombreBilles() {
		nbBilles++;
		repaint();
	}
	
	public void decNombreBilles() {
		nbBilles--;
		repaint();
	}
	
	public void vider() {
		nbBilles = 0;
		repaint();
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println(arg0.getComponent());
		updateObservateur(numero);
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		tmp = Color.cyan;
		repaint();
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		tmp = couleur;
		repaint();
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void addObservateur(Observateur obs) {
		// TODO Auto-generated method stub
		listObs.add(obs);
	}

	@Override
	public void updateObservateur(int ind) {
		// TODO Auto-generated method stub
		for(Observateur obs: listObs)
			obs.update(ind);
	}

	@Override
	public void delObservateur() {
		// TODO Auto-generated method stub
		listObs.clear();
	}
}
