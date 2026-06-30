package br.edu.cafeteria.app;

import javax.swing.JOptionPane;
import br.edu.cafeteria.modelo.*;
import br.edu.cafeteria.excecao.*;
import br.edu.cafeteria.servico.*;

public class Principal 
{
    public static void main(String[] args) 
    {

        GerenciarProduto gp = new GerenciarProduto();
        GerenciarCliente gc = new GerenciarCliente();
        int opcao1, opcao2;
        
        do {
            String menu1 = " ";
            menu1 += "========== Menu ==========\n";
            menu1 += "1 - Gerenciar Produtos\n";
            menu1 += "2 - Gerenciar Clientes\n";
            menu1 += "0 - Sair\n";
            menu1 += "==========================";
            
            
            opcao1 = Integer.parseInt(JOptionPane.showInputDialog(menu1));
            
            if(opcao1 == 0)
            {
                JOptionPane.showMessageDialog(null, "Programa encerrado!");
                break;

            }
            
            if (opcao1 == 2) {
                menuClientes(gc);
                continue;
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
                        JOptionPane.showMessageDialog(null, "Opcao invalida!");
                }

            } while (opcao2 != 0);
        
        }while (opcao1 != 0);
    }

    //Cadastrar
    public static void cadastrarProduto(GerenciarProduto gp) 
    {

        int tipo = Integer.parseInt(JOptionPane.showInputDialog("1 - Comida\n2 - Bebida"));
        
        int codigo = Integer.parseInt(JOptionPane.showInputDialog("Codigo: "));
        
        String nome = JOptionPane.showInputDialog("Nome:");

        double preco = Double.parseDouble(JOptionPane.showInputDialog("Preco:"));

        int estoque = Integer.parseInt(JOptionPane.showInputDialog("Quantidade em estoque:"));

        Produto produto = null;

        if (tipo == 1) 
        {
            int tempo = Integer.parseInt(JOptionPane.showInputDialog("Tempo de preparo (min):"));

            boolean vegano = Boolean.parseBoolean(JOptionPane.showInputDialog("Eh vegano? (true/false)"));

            boolean semGluten = Boolean.parseBoolean(JOptionPane.showInputDialog("Contem gluten? (true/false)"));

            produto = new Comida(codigo, nome, preco, estoque, tempo, vegano, semGluten);

        } 
        
        else if (tipo == 2) 
        {

            String tamanho = JOptionPane.showInputDialog("Tamanho (P/M/G):");

            double cafeina = Double.parseDouble(JOptionPane.showInputDialog("Quantidade de cafeina (mg):"));

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

        int codigo = Integer.parseInt(JOptionPane.showInputDialog("Digite o codigo:"));

        Produto p = gp.buscarProduto(codigo);

        if (p != null) {
            JOptionPane.showMessageDialog(null,
                    "Codigo: " + p.getCodigo() +
                    "\nNome: " + p.getNome() +
                    "\nPreco: R$ " + p.getPrecoBase() +
                    "\nEstoque: " + p.getQuantidadeEstoque());
        } else {
            JOptionPane.showMessageDialog(null, "Produto nao encontrado!");
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

        int codigo = Integer.parseInt(JOptionPane.showInputDialog("Codigo do produto:"));

        Produto antigo = gp.buscarProduto(codigo);

        if (antigo == null) 
        {
            JOptionPane.showMessageDialog(null, "Produto nao encontrado!");
            return;
        }

        String nome = JOptionPane.showInputDialog("Novo nome:");

        double preco = Double.parseDouble(JOptionPane.showInputDialog("Novo preco:"));

        int estoque = Integer.parseInt(JOptionPane.showInputDialog("Novo estoque:"));

        Produto novoProduto = null;

        if (antigo instanceof Comida) {

            int tempo = Integer.parseInt(JOptionPane.showInputDialog("Tempo de preparo:"));

            boolean vegano = Boolean.parseBoolean(JOptionPane.showInputDialog("Vegano? (true/false)"));

            boolean semGluten = Boolean.parseBoolean(JOptionPane.showInputDialog("Sem gluten? (true/false)"));

            novoProduto = new Comida(codigo, nome, preco, estoque, tempo, vegano, semGluten);

        } else if (antigo instanceof Bebida) {

            String tamanho = JOptionPane.showInputDialog("Tamanho (P/M/G):");

            double cafeina = Double.parseDouble(JOptionPane.showInputDialog("Quantidade de cafeina:"));

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

        int codigo = Integer.parseInt(JOptionPane.showInputDialog("Codigo do produto a remover:"));

        if (gp.deletarProduto(codigo)) 
        {
            JOptionPane.showMessageDialog(null, "Produto removido!");
        } 
        
        else 
        {
            JOptionPane.showMessageDialog(null, "Produto nao encontrado!");
        }
    }

    // ============================================================
    // MENU CLIENTES
    // ============================================================
    public static void menuClientes(GerenciarCliente gc) 
    {
        int opcao;
        
        do {
            String menu = " ";
            menu += "========== GERENCIAR CLIENTES ==========\n";
            menu += "1 - Cadastrar Cliente\n";
            menu += "2 - Buscar Cliente\n";
            menu += "3 - Listar Clientes\n";
            menu += "4 - Atualizar Cliente\n";
            menu += "5 - Deletar Cliente\n";
            menu += "6 - Adicionar XP\n";
            menu += "7 - Resgatar XP\n";
            menu += "8 - VIP Pagar com XP\n";
            menu += "0 - Voltar\n";
            menu += "========================================";

            opcao = Integer.parseInt(JOptionPane.showInputDialog(menu));

            switch (opcao) 
            {
                case 1:
                    cadastrarCliente(gc);
                    break;

                case 2:
                    buscarCliente(gc);
                    break;

                case 3:
                    listarClientes(gc);
                    break;

                case 4:
                    atualizarCliente(gc);
                    break;

                case 5:
                    deletarCliente(gc);
                    break;

                case 6:
                    adicionarXP(gc);
                    break;

                case 7:
                    resgatarXP(gc);
                    break;

                case 8:
                    vipPagarComXP(gc);
                    break;

                case 0:
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Opcao invalida!");
            }

        } while (opcao != 0);
    }

    // ============================================================
    // OPERACOES CLIENTES
    // ============================================================

    public static void cadastrarCliente(GerenciarCliente gc) 
    {
        try {
            int tipo = Integer.parseInt(JOptionPane.showInputDialog("1 - Standard\n2 - VIP"));
            
            String nome = JOptionPane.showInputDialog("Nome:");
            String sobrenome = JOptionPane.showInputDialog("Sobrenome:");
            String cpf = JOptionPane.showInputDialog("CPF:");
            
            Cliente cliente = (tipo == 2) ? 
                new ClienteVIP(nome, sobrenome, cpf) : 
                new ClienteStandard(nome, sobrenome, cpf);
            
            if (gc.adicionarCliente(cliente)) {
                JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "CPF ja cadastrado!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
        }
    }

    public static void buscarCliente(GerenciarCliente gc) 
    {
        try {
            String cpf = JOptionPane.showInputDialog("Digite o CPF:");
            Cliente c = gc.buscarCliente(cpf);
            
            if (c != null) {
                JOptionPane.showMessageDialog(null, c.toString());
            } else {
                JOptionPane.showMessageDialog(null, "Cliente nao encontrado!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
        }
    }

    public static void listarClientes(GerenciarCliente gc) 
    {
        String lista = "";
        for (Cliente c : gc.getClientes()) {
            lista += c.toString() + "\n==============================\n";
        }
        if (lista.isEmpty()) {
            lista = "Nenhum cliente cadastrado.";
        }
        JOptionPane.showMessageDialog(null, lista);
    }

    public static void atualizarCliente(GerenciarCliente gc) 
    {
        try {
            String cpf = JOptionPane.showInputDialog("CPF do cliente:");
            Cliente antigo = gc.buscarCliente(cpf);
            
            if (antigo == null) {
                JOptionPane.showMessageDialog(null, "Cliente nao encontrado!");
                return;
            }
            
            int tipo = Integer.parseInt(JOptionPane.showInputDialog("Novo tipo:\n1 - Standard\n2 - VIP"));
            String nome = JOptionPane.showInputDialog("Novo nome:");
            String sobrenome = JOptionPane.showInputDialog("Novo sobrenome:");
            
            Cliente novoCliente = (tipo == 2) ? 
                new ClienteVIP(nome, sobrenome, cpf) : 
                new ClienteStandard(nome, sobrenome, cpf);
            
            novoCliente.adicionarXP(antigo.getXpAcumulado());
            
            if (gc.atualizarCliente(cpf, novoCliente)) {
                JOptionPane.showMessageDialog(null, "Cliente atualizado!");
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao atualizar.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
        }
    }

    public static void deletarCliente(GerenciarCliente gc) 
    {
        try {
            String cpf = JOptionPane.showInputDialog("CPF do cliente a remover:");
            if (gc.deletarCliente(cpf)) {
                JOptionPane.showMessageDialog(null, "Cliente removido!");
            } else {
                JOptionPane.showMessageDialog(null, "Cliente nao encontrado!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
        }
    }

    public static void adicionarXP(GerenciarCliente gc) 
    {
        try {
            String cpf = JOptionPane.showInputDialog("CPF do cliente:");
            int pontos = Integer.parseInt(JOptionPane.showInputDialog("Quantos XP adicionar?"));
            
            if (gc.adicionarXP(cpf, pontos)) {
                Cliente c = gc.buscarCliente(cpf);
                JOptionPane.showMessageDialog(null, "XP adicionado!\nTotal: " + c.getXpAcumulado());
            } else {
                JOptionPane.showMessageDialog(null, "Cliente nao encontrado!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
        }
    }



    public static void resgatarXP(GerenciarCliente gc) {
        try {
            String cpf = JOptionPane.showInputDialog("CPF do cliente:");
            if (cpf == null || cpf.trim().isEmpty()) {
                return;
            }
            
            String pontosStr = JOptionPane.showInputDialog("Quantos XP resgatar?");
            if (pontosStr == null || pontosStr.trim().isEmpty()) {
                return;
            }
            
            int pontos = Integer.parseInt(pontosStr);
            
            // ⭐ CHAMA O RESGATE
            double desconto = gc.resgatarXP(cpf, pontos);
            
            // Se chegou aqui, deu certo!
            Cliente c = gc.buscarCliente(cpf);
            JOptionPane.showMessageDialog(null, 
                "✅ Desconto: R$ " + String.format("%.2f", desconto) +
                "\nXP restante: " + c.getXpAcumulado());
                
        } catch (PontosInsuficientesException e) {
            // ⭐ MOSTRA A MENSAGEM REAL DA EXCEÇÃO!
            JOptionPane.showMessageDialog(null, " " + e.getMessage());
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, " Digite um numero valido!");
            
        } catch (Exception e) {
            // ⭐ SÓ CAI AQUI SE FOR OUTRO ERRO!
            JOptionPane.showMessageDialog(null, " Erro: " + e.getMessage());
        }
    }



    public static void vipPagarComXP(GerenciarCliente gc) 
    {
        try {
            String cpf = JOptionPane.showInputDialog("CPF do cliente VIP:");
            double valor = Double.parseDouble(JOptionPane.showInputDialog("Valor da compra: R$"));
            
            if (gc.pagarComXP(cpf, valor)) {
                Cliente c = gc.buscarCliente(cpf);
                JOptionPane.showMessageDialog(null, 
                    "Pagamento com XP realizado!\n" +
                    "XP restante: " + c.getXpAcumulado());
            } else {
                JOptionPane.showMessageDialog(null, 
                    "Erro! Verifique:\n" +
                    "- Cliente existe?\n" +
                    "- Eh VIP?\n" +
                    "- Saldo suficiente?");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
        }
    }
}