package br.edu.cafeteria.modelo;

public abstract class CadastroSistema {
    protected String nome;
    protected String sobrenome;
    protected String cpf;

    public CadastroSistema(String nome, String sobrenome, String cpf) {
        this.nome = nome;
        this.sobrenome =  sobrenome;
        this.cpf = cpf;
    }

    public String getNome() {return nome; }
    public String getSobrenome() { return sobrenome; }
    public String getCpf() { return cpf; }
    public String getNomeCompleto() { return nome + " " + sobrenome;}

    @Override 
    public String toString() {
        return "Nome: " + getNomeCompleto() + ", CPF: " + cpf;
    }
    
}
