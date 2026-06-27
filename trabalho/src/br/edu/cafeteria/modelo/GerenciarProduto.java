package br.edu.cafeteria.modelo;

import java.util.ArrayList;
import java.util.List;

public class GerenciarProduto
{
    private List<Produto> produtos;

    public GerenciarProduto() 
    {
        produtos = new ArrayList<>();
    }

    // Adicionar produto
    public boolean adicionarProduto(Produto produto) 
    {
        if (buscarProduto(produto.getCodigo()) != null) 
        {
            return false; // código já existe
        }

        produtos.add(produto);
        return true;
    }

    // Buscar produto pelo código
    public Produto buscarProduto(int codigo) 
    {
        for (Produto p : produtos) 
        {
            if (p.getCodigo() == codigo) 
            {
                return p;
            }
        }
        return null;
    }
  
    // Listar todos os produtos
    public void listarProdutos() 
    {
        if (produtos.isEmpty()) 
        {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }

        for (Produto p : produtos) 
        {
            System.out.println(p);
            System.out.println("---------------------");
        }
    }

    // Deletar produto pelo código
    public boolean deletarProduto(int codigo) {
        Produto produto = buscarProduto(codigo);

        if (produto != null) {
            produtos.remove(produto);
            return true;
        }

        return false;
    }
    
 // Atualizar produto pelo código
    public boolean atualizarProduto(int codigo, Produto novoProduto) 
    {
        for (int i = 0; i < produtos.size(); i++) {
            if (produtos.get(i).getCodigo() == codigo) {

                // Mantém o mesmo código do produto original
                produtos.set(i, novoProduto);
                return true;
            }
        }
        return false;
    }
    
    public List<Produto> getProdutos() {
        return produtos;
    }
}
