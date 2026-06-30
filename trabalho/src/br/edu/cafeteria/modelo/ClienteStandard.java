package br.edu.cafeteria.modelo;

public class ClienteStandard extends Cliente {

    public ClienteStandard(String nome, String sobrenome , String cpf) {
        super(nome, sobrenome, cpf);
    }

    // 1 XP por 1,00
    @Override
    public int calcularXP(double valorGasto){
        return (int) valorGasto;
    }
    @Override
    public String toString(){
        return "STANDARD -" + super.toString();
    }


    
}
