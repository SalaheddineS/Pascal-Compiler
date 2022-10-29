package net.mips.compiler;

public class Symboles {
	 private Tokens token;
	 private String nom;
	 
	public Symboles(Tokens beginToken, String string) {
		this.token=beginToken;
		this.nom=string;
	}
	public Tokens getToken() {
		return token;
	}
	public void setToken(Tokens token) {
		this.token = token;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	 
}
