package br.edu.cafeteria.modelo;

import br.edu.cafeteria.excecao.PontosInsuficientesException;

public class ClienteVIP extends Cliente {
    
    public ClienteVIP(String nome, String sobrenome, String cpf) {
        super(nome, sobrenome, cpf);
    }
    
    @Override
    public int calcularXP(double valorGasto) {
        return (int) (valorGasto * 2); // VIP ganha o DOBRO
    }
    
    // ⭐ MÉTODO EXCLUSIVO DO VIP
    public void pagarComXP(double valorTotal) {
        int pontosNecessarios = (int) (valorTotal * TAXA_CONVERSAO);
        
        if (xpAcumulado < pontosNecessarios) {
            throw new PontosInsuficientesException(
                "Saldo insuficiente! Você tem " + xpAcumulado + 
                " XP, mas precisa de " + pontosNecessarios + " XP"
            );
        }
        
        xpAcumulado -= pontosNecessarios;
        System.out.println(" Pagamento TOTAL com XP realizado!");
        System.out.println(" XP restante: " + xpAcumulado);
    }
    
    @Override
    public String toString() {
        return "VIP - " + super.toString();
    }
}