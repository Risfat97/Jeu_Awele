package jeu_awele;

import java.util.ArrayList;

import tam.fr.observer.Observable;
import tam.fr.observer.Observateur;

public class Joueur implements Observable{
	private String nom;
	private int numero;
	private int gain;
	private ArrayList<Observateur> listObs = new ArrayList<Observateur>();
	
	public Joueur(String p_nom, int num){
		nom = p_nom;
		numero = num;
		gain = 0;
	}
	
	public Joueur(String p_nom) {
		this(p_nom, (int)(Math.random() * 100));
	}
	
	public Joueur(int num) {
		this("Joueur"+num, num);
	}
	
	public Joueur() {
		this("Joueur", (int)(Math.random() * 100));
		nom = nom + (int)(Math.random() * 100);
	}
	
	public String toString() {
		return nom + "_" + numero + ": " + gain + " pts";
	}
	
	public String getNom() {
		return nom;
	}
	
	public int getNumero() {
		return numero;
	}
	
	public int getGain() {
		return gain;
	}
	
	public void miseAJour() {
		gain += 2;
		updateObservateur(gain);
	}
	
	@Override
	public void addObservateur(Observateur obs) {
		// TODO Auto-generated method stub
		listObs.add(obs);
	}

	@Override
	public void updateObservateur(int data) {
		// TODO Auto-generated method stub
		for(Observateur obs: listObs)
			obs.update(data);
	}

	@Override
	public void delObservateur() {
		// TODO Auto-generated method stub
		listObs.clear();
	}
}
