package jeu_awele;

import javax.swing.JFrame;

public class Awele extends JFrame{

	private static final long serialVersionUID = -8065703162316666051L;
	private Jeu jeu;
	
	public Awele() {
		super("Awélé");
		setSize(600, 600);
		setLocationRelativeTo(null);
		try {
			jeu = new Jeu();
		} catch(IllegalArgumentException e) {
			e.printStackTrace();
			jeu = new Jeu(new Joueur(0), new Joueur(1));
		}
		setContentPane(jeu);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public void demarrer() {
		/*while(jeu.jouer()) {
			System.out.println("\n" + jeu + "\n");
		}*/
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Awele aw = new Awele();
	}

}
