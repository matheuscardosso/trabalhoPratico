package br.edu.cafeteria.modelo;

import br.edu.cafeteria.excecao.PontosInsuficientesException;

public abstract class Cliente extends CadastroSistema {
    protected int xpAcumulado;
    protected static final int TAXA_CONVERSAO = 10; // 10 XP = R$ 1,00

//construtor
    public Cliente(String nome, String sobrenome, String cpf) {
        super(nome, sobrenome, cpf);
        this.xpAcumulado = 0;
    }

// metodo abstrato 
    public abstract int calcularXP(double valorGasto);


//metodos compartilhados por todos
//1º
    public void adicionarXP(int pontos) {
        this.xpAcumulado += pontos;
        System.out.println(" +" + pontos + " XP! Total: " + xpAcumulado);
    }
//2º
    public double resgatarXP(int pontos) {
        if (pontos <= 0) {
            throw new IllegalArgumentException("Pontos devem ser maior que zero");
        }
        if (pontos > xpAcumulado) {
            throw new PontosInsuficientesException(
                "Saldo insuficiente! Você tem " + xpAcumulado + " XP, mas quer resgatar " + pontos
            );
        }
        xpAcumulado -= pontos;
        double desconto = (double) pontos / TAXA_CONVERSAO;
        System.out.println(" Resgatou " + pontos + " XP = R$ " + String.format("%.2f", desconto));
        return desconto;
    }
//3º
    public boolean podeResgatar(double valorCompra) {
        int pontosNecessarios = (int) (valorCompra * TAXA_CONVERSAO);
        return xpAcumulado >= pontosNecessarios;
    }

//4º

    public int getXpAcumulado() {
        return xpAcumulado;
    }

    @Override
    public String toString() {
        return super.toString() + ", XP: " + xpAcumulado;
    }
}