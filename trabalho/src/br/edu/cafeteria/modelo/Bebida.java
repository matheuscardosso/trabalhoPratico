package br.edu.cafeteria.modelo;

public class Bebida extends Produto
{
	String tamanho;
	double quantidadeCafeina;
	
	public Bebida(int codigo, String nome, double precoBase, int quantidadeEstoque, String tamanho, double quantidadeCafeina)
	{
		super(codigo, nome, precoBase, quantidadeEstoque);
		this.tamanho = tamanho;
		this.quantidadeCafeina = quantidadeCafeina;
	}

	public String getTamanho() {
		return tamanho;
	}

	public double getQuantidadeCafeina() {
		return quantidadeCafeina;
	}

	public String toString() {
		return super.toString() +
				"\nTamanho: " + tamanho +
				"\nQuantidade de Cafeína: " + quantidadeCafeina + " mg";
	}	
	
}

