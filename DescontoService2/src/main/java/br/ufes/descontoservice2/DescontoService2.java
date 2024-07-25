package br.ufes.descontoservice2;

import br.ufes.descontoservice2.models.Cliente;
import br.ufes.descontoservice2.models.Pedido;
import br.ufes.descontoservice2.services.CalculadoraDeDescontoService;

public class DescontoService2 {

    public static void main(String[] args) {
        Cliente cliente = new Cliente("João", 3);
        Pedido pedido = new Pedido(cliente, 1200.0, 15);
        
        CalculadoraDeDescontoService calculadora = new CalculadoraDeDescontoService();

        calculadora.processar(pedido);
        
        System.out.println("Desconto total do pedido: " + pedido.getValorDesconto());
    }
}
