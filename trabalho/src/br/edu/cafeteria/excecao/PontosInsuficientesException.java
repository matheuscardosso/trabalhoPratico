package br.edu.cafeteria.excecao;

import br.edu.cafeteria.excecao.CafeteriaException;

public class PontosInsuficientesException extends CafeteriaException {

    public PontosInsuficientesException(String mensagem ){
        super(mensagem);
    }

    public PontosInsuficientesException(String mensagem , Throwable causa){
        super(mensagem, causa);
    }
    
}
