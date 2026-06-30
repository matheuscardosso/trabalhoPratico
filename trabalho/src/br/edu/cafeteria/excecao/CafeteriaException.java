package br.edu.cafeteria.excecao;

public class CafeteriaException extends RuntimeException{

    public CafeteriaException(String mensagem){
        super(mensagem);
    }

    public CafeteriaException(String mensagem , Throwable causa){
        super(mensagem, causa);
    }
    
}
