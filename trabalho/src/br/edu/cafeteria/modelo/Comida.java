package br.edu.cafeteria.modelo;

public class Comida extends Produto 
{
	private int tempoPreparo;
	private boolean vegano, contemGluten;
	
	public Comida(int codigo, String nome, double precoBase, int quantidadeEstoque, int tempoPreparo, boolean vegano, boolean contemGluten)
	{
		super(codigo, nome, precoBase, quantidadeEstoque);
		this.tempoPreparo = tempoPreparo;
		this.vegano = vegano;
		this.contemGluten = contemGluten;
	}

	public int getTempoPreparo() {
		return tempoPreparo;
	}

	public boolean isVegano() {
		return vegano;
	}

	public boolean isContemGluten() {
		return contemGluten;
	}

	public String toString() {
		return super.toString() +
				"\nTempo de Preparo: " + tempoPreparo + " min" +
				"\nVegano: " + (vegano ? "Sim" : "Não") +
				"\nContém Glúten: " + (contemGluten ? "Sim" : "Não");
	}
	
}
