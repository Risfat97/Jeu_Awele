package tam.fr.observer;

public interface Observable {
	public void addObservateur(Observateur obs);
	public void updateObservateur(int data);
	public void delObservateur();
}
