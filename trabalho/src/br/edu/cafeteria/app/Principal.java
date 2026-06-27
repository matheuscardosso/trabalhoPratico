package br.edu.cafeteria.app;

import javax.swing.JOptionPane;
import br.edu.cafeteria.modelo.Bebida;
import br.edu.cafeteria.modelo.Comida;
import br.edu.cafeteria.modelo.GerenciarProduto;
import br.edu.cafeteria.modelo.Produto;

public class Principal 
{
    public static void main(String[] args) 
    {

        GerenciarProduto gp = new GerenciarProduto();
        int opcao1, opcao2;
        
        do {
        	String menu1 = " ";
        	menu1 += "========== Menu ==========\n";
        	menu1 += "1 - Gerenciar Produtos\n";
        	menu1 += "0 - Sair\n";
        	menu1 += "==========================";
        	
        	
        	opcao1 = Integer.parseInt(JOptionPane.showInputDialog(menu1));
        	
        	if(opcao1 == 0)
        	{
        		JOptionPane.showMessageDialog(null, "Programa encerrado!");
                break;

        	}
        
        	do {
        		String menu2 = " ";
        		menu2 += "========== Menu ==========\n";
        		menu2 += "1 - Cadastrar Produto\n";
        		menu2 += "2 - Buscar Produto\n";
        		menu2 += "3 - Listar Produtos\n";
        		menu2 += "4 - Atualizar Produto\n";
        		menu2 += "5 - Deletar Produto\n";
        		menu2 += "0 - Voltar\n";
        		menu2 += "==========================";

        		opcao2 = Integer.parseInt(JOptionPane.showInputDialog(menu2));

        		switch (opcao2) 
        		{
                	case 1:
                		cadastrarProduto(gp);
                		break;

                	case 2:
                		buscarProduto(gp);
                		break;

                	case 3:
                		listarProdutos(gp);
                		break;

                	case 4:
                		atualizarProduto(gp);
                		break;

                	case 5:
                		deletarProduto(gp);
                		break;

                	case 0:
                		break;

                	default:
                		JOptionPane.showMessageDialog(null, "Opção inválida!");
        		}

        	} while (opcao2 != 0);
        
        }while (opcao1 != 0);
    }

    //Cadastrar
    public static void cadastrarProduto(GerenciarProduto gp) 
    {

        int tipo = Integer.parseInt(JOptionPane.showInputDialog("1 - Comida\n2 - Bebida"));
        
        int codigo = Integer.parseInt(JOptionPane.showInputDialog("Código: "));
        
        String nome = JOptionPane.showInputDialog("Nome:");

        double preco = Double.parseDouble(JOptionPane.showInputDialog("Preço:"));

        int estoque = Integer.parseInt(JOptionPane.showInputDialog("Quantidade em estoque:"));

        Produto produto = null;

        if (tipo == 1) 
        {
            int tempo = Integer.parseInt(JOptionPane.showInputDialog("Tempo de preparo (min):"));

            boolean vegano = Boolean.parseBoolean(JOptionPane.showInputDialog("É vegano? (true/false)"));

            boolean semGluten = Boolean.parseBoolean(JOptionPane.showInputDialog("Contém glúten? (true/false)"));

            produto = new Comida(codigo, nome, preco, estoque, tempo, vegano, semGluten);

        } 
        
        else if (tipo == 2) 
        {

            String tamanho = JOptionPane.showInputDialog("Tamanho (P/M/G):");

            double cafeina = Double.parseDouble(JOptionPane.showInputDialog("Quantidade de cafeína (mg):"));

            produto = new Bebida(codigo, nome, preco, estoque, tamanho, cafeina);
        }

        if (produto != null && gp.adicionarProduto(produto)) 
        {
        	JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
        }
        
        else 
        {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto.");
        }
    }

    //Buscar
    public static void buscarProduto(GerenciarProduto gp) 
    {

        int codigo = Integer.parseInt(JOptionPane.showInputDialog("Digite o código:"));

        Produto p = gp.buscarProduto(codigo);

        if (p != null) {
            JOptionPane.showMessageDialog(null,
                    "Código: " + p.getCodigo() +
                    "\nNome: " + p.getNome() +
                    "\nPreço: R$ " + p.getPrecoBase() +
                    "\nEstoque: " + p.getQuantidadeEstoque());
        } else {
            JOptionPane.showMessageDialog(null, "Produto não encontrado!");
        }
    }

    //Listar
    public static void listarProdutos(GerenciarProduto gp) 
    {

        String lista = "";

        for (Produto p : gp.getProdutos()) 
        {
            lista += p.toString();
            lista += "\n==============================\n";
        }

        if (lista.isEmpty()) 
        {
            lista = "Nenhum produto cadastrado.";
        }

        JOptionPane.showMessageDialog(null, lista);
    }

    //Atualizar
    public static void atualizarProduto(GerenciarProduto gp) 
    {

        int codigo = Integer.parseInt(JOptionPane.showInputDialog("Código do produto:"));

        Produto antigo = gp.buscarProduto(codigo);

        if (antigo == null) 
        {
            JOptionPane.showMessageDialog(null, "Produto não encontrado!");
            return;
        }

        String nome = JOptionPane.showInputDialog("Novo nome:");

        double preco = Double.parseDouble(JOptionPane.showInputDialog("Novo preço:"));

        int estoque = Integer.parseInt(JOptionPane.showInputDialog("Novo estoque:"));

        Produto novoProduto = null;

        if (antigo instanceof Comida) {

            int tempo = Integer.parseInt(JOptionPane.showInputDialog("Tempo de preparo:"));

            boolean vegano = Boolean.parseBoolean(JOptionPane.showInputDialog("Vegano? (true/false)"));

            boolean semGluten = Boolean.parseBoolean(JOptionPane.showInputDialog("Sem glúten? (true/false)"));

            novoProduto = new Comida(codigo, nome, preco, estoque, tempo, vegano, semGluten);

        } else if (antigo instanceof Bebida) {

            String tamanho = JOptionPane.showInputDialog("Tamanho (P/M/G):");

            double cafeina = Double.parseDouble(JOptionPane.showInputDialog("Quantidade de cafeína:"));

            novoProduto = new Bebida(codigo, nome, preco, estoque, tamanho, cafeina);
        }

        if (gp.atualizarProduto(codigo,novoProduto)) 
        {
            JOptionPane.showMessageDialog(null, "Produto atualizado!");
        }
        
        else 
        {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar.");
        }
    }

    //Deletar
    public static void deletarProduto(GerenciarProduto gp) 
    {

        int codigo = Integer.parseInt(JOptionPane.showInputDialog("Código do produto a remover:"));

        if (gp.deletarProduto(codigo)) 
        {
            JOptionPane.showMessageDialog(null, "Produto removido!");
        } 
        
        else 
        {
            JOptionPane.showMessageDialog(null, "Produto não encontrado!");
        }
    }
}