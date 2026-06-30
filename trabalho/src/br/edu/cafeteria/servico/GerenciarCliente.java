package br.edu.cafeteria.servico;

import br.edu.cafeteria.modelo.Cliente;
import br.edu.cafeteria.modelo.ClienteVIP;
import excecao.PontosInsuficientesException;

import java.util.ArrayList;
import java.util.List;

public class GerenciarCliente {

    private List<Cliente> clientes;
    public GerenciarCliente(){
        clientes = new ArrayList<>();
    }

    // adicionar cliente
    public boolean adicionarCliente(Cliente cliente){
        if (buscarCliente(cliente.getCpf()) != null){ 
            return false; //cpf ja existe 
        }

        clientes.add(cliente);
        return true;
    }

    // buscar cliente pelo cpf
    public Cliente buscarCliente(String cpf)
    {
        for (Cliente c : clientes)
        {
            if (c.getCpf().equals(cpf))
            {
                return c;
            }
        }

        return null;
    }

    //listar todos os clientes 
    public void listarClientes()
    {
        if (clientes.isEmpty())
        {
            System.out.println("nenhum cliente cadastrado");
            return;
        }

        for (Cliente c: clientes)
        {
            System.out.println(c);
            System.out.println(("--------------------"));
        }
    }

    //deletar cliente 
    public boolean deletarCliente(String cpf)
    {
        Cliente cliente = buscarCliente(cpf);

        if (cliente != null)
        {
            clientes.remove(cliente);
            return true;
        }
        return false;
    }

    // atualizar cliente pelo cpf
    public boolean atualizarCliente(String cpf, Cliente novoCliente)
    {
        for (int i = 0; i < clientes.size(); i++)
        {
            if (clientes.get(i).getCpf().equals(cpf))
            {
                //mantem o msm cpf
                clientes.set(i, novoCliente);
                return true;
            }
        }

        return false;
    }

    // adicionar xp
    public boolean adicionarXP(String cpf, int pontos)
    {
        Cliente cliente = buscarCliente(cpf);
        if(cliente == null)
        {

            return false;
        }
        cliente.adicionarXP(pontos);
        return true;
    }

    // resgatar xp

    public double resgatarXP(String cpf, int pontos) throws PontosInsuficientesException {
        Cliente cliente = buscarCliente(cpf);
        if (cliente == null) {
            throw new RuntimeException("Cliente nao encontrado!");  // Lança exceção também!
        }
        return cliente.resgatarXP(pontos);
    }





    // vip pagar com xp

    public boolean pagarComXP(String cpf, double valor)
    {
        Cliente cliente = buscarCliente(cpf);
        if (cliente==null)
        {
            return false;
        }
        if (!(cliente instanceof ClienteVIP))
        {
            return false;
        }

        try
        {
            ((ClienteVIP) cliente).pagarComXP(valor);
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public List<Cliente> getClientes()
    {
        return clientes;
    }
    
}
