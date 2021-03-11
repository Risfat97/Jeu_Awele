package jeu_awele;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Box;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

import tam.fr.observer.Observateur;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

public class Jeu extends JPanel implements Observateur{

	private static final long serialVersionUID = -5934587876518374772L;
	private Case[] plateau = new Case[12];
	private Joueur[] joueurs = new Joueur[2];
	private PlaceJoueur[] places = new PlaceJoueur[2];
	private Joueur indicateur;
	private JLabel[] pts = new JLabel[2];
	private JLabel[] nomJoueurs = new JLabel[2];
	private Color bg = new Color(133, 193, 233);
	private Color col[] = new Color[2];
	Font police = new Font("Tahoma", Font.BOLD, 15);
	
	public Jeu(Joueur j1, Joueur j2) throws IllegalArgumentException{
		super();
		setBackground(bg);
		if(j1.getNumero() == j2.getNumero())
			throw new IllegalArgumentException("les joueurs sont les mêmes.");
		joueurs[0] = j1;
		joueurs[1] = j2;
		for(int i = 0; i < 2; i++)
			joueurs[i].addObservateur(new JoueurObserver());
		col[0] = new Color(245, 176, 65);
		col[1] = new Color(244, 208, 63);
		for(int i = 0; i < 6; i++) {
			plateau[i] = new Case(joueurs[1], i, col[1]);
			plateau[i].addObservateur(this);
			plateau[i+6] = new Case(joueurs[0], i+6, col[0]);
			plateau[i+6].addObservateur(this);
		}
		indicateur = joueurs[0];
		for(int i = 0; i < 2; i++) {
			nomJoueurs[i] = new JLabel(joueurs[i].getNom() + " ");
			nomJoueurs[i].setFont(police);
			pts[i] = new JLabel(" 0 pts ");
			pts[i].setFont(police);
		}
		this.setLayout(new BorderLayout());
		afficherJoueur();
		afficherPlateau();
		Box hsep = Box.createHorizontalBox();
		hsep.setPreferredSize(new Dimension(getWidth(), 5));
		hsep.add(new JLabel(" "));
		this.add(hsep, BorderLayout.SOUTH);
		places[0].prendreLaMain();
	}
	
	public Jeu() throws IllegalArgumentException {
		this(new Joueur(), new Joueur());
	}
	
	public void afficherJoueur() {
		Border[] lineborder = new Border[2];
		for(int i = 0; i < 2; i++)
			lineborder[i] =  BorderFactory.createLineBorder(col[i], 3, true);
		Box header = Box.createHorizontalBox();
		Box colsHeader[] = new Box[5];
		for(int i = 0; i < 5; i++) {
			colsHeader[i] = Box.createVerticalBox();
			if(i < 2) {
				colsHeader[i].setPreferredSize(new Dimension(190, 100));
				colsHeader[i].setBorder(lineborder[i]);
				colsHeader[i].add(nomJoueurs[i]);
				colsHeader[i].add(pts[i]);
			} else if(i > 2)
				colsHeader[i].setPreferredSize(new Dimension(100, 100));
		}
		colsHeader[2].setPreferredSize(new Dimension(35, 100));
		JLabel vs = new JLabel("VS");
		vs.setFont(police);
		colsHeader[2].add(vs);
		colsHeader[3].add(new JLabel(" "));
		colsHeader[4].add(new JLabel(" "));
		header.add(colsHeader[0]);
		header.add(colsHeader[3]);
		header.add(colsHeader[2]);
		header.add(colsHeader[4]);
		header.add(colsHeader[1]);
		this.add(header, BorderLayout.NORTH);
	}
	
	/*public void afficherPlateau() {
		JPanel pan = new JPanel();
		pan.setBackground(Color.magenta);
		Border roundborder = BorderFactory.createLineBorder(Color.black, 2, true);
		pan.setLayout(new BorderLayout());
		Box boxNS[] = new Box[2];
		for(int i = 0; i < 2; i++)
			boxNS[i] = Box.createHorizontalBox();
		boxNS[0].add(new JLabel(" " + joueurs[0].getNom()));
		boxNS[1].add(new JLabel(" " + joueurs[1].getNom()));
		for(int i = 0; i < 2; i++)
			boxNS[i].setBorder(roundborder);
		pan.add(boxNS[1], BorderLayout.NORTH);
		pan.add(boxNS[0], BorderLayout.SOUTH);
		//pan.add(, BorderLayout.CENTER);
		this.add(pan, BorderLayout.CENTER);
	}*/
	
	public void afficherPlateau() {
		JPanel pan = new JPanel();
		pan.setBackground(bg);
		pan.setLayout(new BorderLayout());
		places[0] = new PlaceJoueur(joueurs[0].getNom(), col[0]);
		places[1] = new PlaceJoueur(joueurs[1].getNom(), col[1]);
		pan.add(places[1], BorderLayout.NORTH);
		pan.add(places[0], BorderLayout.SOUTH);
		
		Box bVPlat = Box.createVerticalBox();
		Box[] bHsPlat = new Box[3];
		Box[] separateurH = new Box[2];
		for(int i = 0; i < 2; i++) {
			separateurH[i] = Box.createHorizontalBox();
			separateurH[i].setPreferredSize(new Dimension(getWidth(), 10));
			separateurH[i].setBackground(bg);
			separateurH[i].add(new JLabel(" "));
		}
		for(int i = 0; i < 3; i++)
			bHsPlat[i] = Box.createHorizontalBox();
		bHsPlat[2].setPreferredSize(new Dimension(getWidth(), 27));
		bHsPlat[2].setBackground(bg);
		bHsPlat[2].add(new JLabel(" "));
		pan.setBackground(bg);
		for(int i = 0; i < 6; i++) {
			bHsPlat[0].add(plateau[i]);
			bHsPlat[1].add(plateau[6+i]);
			
		}
		bVPlat.add(separateurH[0]);
		bVPlat.add(bHsPlat[0]);
		bVPlat.add(bHsPlat[2]);
		bVPlat.add(bHsPlat[1]);
		bVPlat.add(separateurH[1]);
		pan.add(bVPlat, BorderLayout.CENTER);
		this.add(pan, BorderLayout.CENTER);
	}
	
	public String toString() {
		StringBuffer buff = new StringBuffer();
		
		buff.append(joueurs[0] + " vs " + joueurs[1] + "\n");
		for(Case c: plateau)
			buff.append(c + "\n");
		return buff.toString();
	}
	
	public void changeJoueur() {
		if(indicateur.getNumero() == joueurs[0].getNumero()) {
			indicateur = joueurs[1];
			places[0].lacherLaMain();
			places[1].prendreLaMain();
		}			
		else {
			indicateur = joueurs[0];
			places[1].lacherLaMain();
			places[0].prendreLaMain();
		}
	}
	
	public int choisirCase(int ind) {
		int ret = -1;
		if(indicateur.getNumero() == joueurs[1].getNumero()) {
			if(0 <= ind && ind <= 5)
				ret = ind;
		} else
			if(6 <= ind && ind <= 11)
				ret = 11 - ind;
		return ret;
	}
	
	public void unCoup(int i) {
		/* Récupération des billes d'une case non vide */
		if(!plateau[i].estVide()) {
			int nbBilles, caseSuiv, casePrec = 0;
			nbBilles = plateau[i].getNombreBilles();
			/* Distribution des billes dans les cases suivantes */
			if(indicateur.getNumero() == joueurs[0].getNumero())
				caseSuiv = 11 - i;
			else
				caseSuiv = i;
			
			plateau[caseSuiv].vider();
			
			for(int j = 0; j < nbBilles; j++) {
				casePrec = caseSuiv;
				if(caseSuiv > 6)
					caseSuiv--;
				else if(caseSuiv < 5)
					caseSuiv++;
				else if(caseSuiv == 6)
					caseSuiv = 0;
				else if(caseSuiv == 5)
					caseSuiv = 11;
				plateau[caseSuiv].incNombreBilles();
			}
			
			/* Vérification du nombre de billes dans la dernière case de la dist */
			if(plateau[caseSuiv].getNombreBilles() == 2) {
				plateau[caseSuiv].vider();
				indicateur.miseAJour();
				if(plateau[casePrec].getNombreBilles() == 2) {
					plateau[casePrec].vider();
					indicateur.miseAJour();
				}
			}
		}
	}
	
	public boolean finDePartie() {
		if(48 - (joueurs[0].getGain() + joueurs[1].getGain()) <= 12) {
			if(joueurs[0].getGain() > joueurs[1].getGain()) {
				places[0].afficherDetails("(félicitation, vous avez gagné)", new Color(130, 22, 170));
				places[1].afficherDetails("(vous avez perdu)", Color.red);
			} else if(joueurs[0].getGain() < joueurs[1].getGain()) {
				places[1].afficherDetails("(félicitation, vous avez gagné)", new Color(130, 22, 170));
				places[0].afficherDetails("(vous avez perdu)", Color.red);
			}else {
				places[0].afficherDetails("Match nul", Color.white);
				places[1].afficherDetails("Match nul", Color.white);
			}
			return true;
		}
		return false;
	}

	@Override
	public void update(int data) {
		// TODO Auto-generated method stub
		int ret;
		if((ret = choisirCase(data)) == -1) {
			if(indicateur.getNumero() == joueurs[0].getNumero())
				places[0].afficherErreur("(ce case vous appartient pas)");
			else
				places[1].afficherErreur("(ce case vous appartient pas)");
		} else {
			unCoup(ret);
			changeJoueur();
			if(finDePartie()) {
				for(int i = 0; i < 12; i++)
					plateau[i].delObservateur();
				for(int i = 0; i < 2; i++)
					joueurs[i].delObservateur();
			}
		}
		System.out.println(ret);
	}
	
	class JoueurObserver implements Observateur{
		@Override
		public void update(int data) {
			// TODO Auto-generated method stub
			if(indicateur.getNumero() == joueurs[0].getNumero())
				pts[0].setText(" " + data + " pts");
			else
				pts[1].setText(" " + data + " pts");
		}
	}
}
