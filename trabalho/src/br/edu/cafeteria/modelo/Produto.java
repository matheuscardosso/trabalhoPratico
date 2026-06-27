package br.edu.cafeteria.modelo;

public class Produto 
{	
	
	private int codigo, quantidadeEstoque;
	private String nome;
	private double precoBase;

	
	public Produto(int codigo, String nome, double precoBase, int quantidadeEstoque)
	{
		this.codigo = codigo;
		this.nome = nome;
		this.precoBase = precoBase;
		this.quantidadeEstoque = quantidadeEstoque;
	}


	public int getCodigo() {
		return codigo;
	}


	public int getQuantidadeEstoque() {
		return quantidadeEstoque;
	}


	public String getNome() {
		return nome;
	}


	public double getPrecoBase() {
		return precoBase;
	}

	public String toString() {
		return "Código: " + codigo +
				"\nNome: " + nome +
				"\nPreço: R$" + precoBase +
				"\nQuantidade em Estoque: " + quantidadeEstoque;
	}
	
}
