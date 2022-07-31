
public class Partido {

	int sets;
	String jugador1;
	String jugador2;
	int prob1;
	int prob2;
	String saque;
	String torneo;
	String ganadorGame;
	String ganadorSet;

	public Partido() {};
	
	public int getSets() {
		return sets;
	}
	public void setSets(int sets) {
		this.sets = sets;
	}
	public String getJugador1() {
		return jugador1;
	}
	public void setJugador1(String jugador1) {
		this.jugador1 = jugador1;
	}
	public String getJugador2() {
		return jugador2;
	}
	public void setJugador2(String jugador2) {
		this.jugador2 = jugador2;
	}
	public int getProb1() {
		return prob1;
	}
	public void setProb1(int prob1) {
		this.prob1 = prob1;
	}
	public int getProb2() {
		return prob2;
	}
	public void setProb2(int prob2) {
		this.prob2 = prob2;
	}
	public String getSaque() {
		return saque;
	}
	public void setSaque(String saque) {
		this.saque = saque;
	}
	public String getTorneo() {
		return torneo;
	}
	public void setTorneo(String torneo) {
		this.torneo = torneo;
	}
	public String getGanadorGame() {
		return ganadorGame;
	}
	public void setGanadorGame(String ganadorGame) {
		this.ganadorGame = ganadorGame;
	}
	public String getGanadorSet() {
		return ganadorSet;
	}
	public void setGanadorSet(String ganadorSet) {
		this.ganadorSet = ganadorSet;
	}
	
}
