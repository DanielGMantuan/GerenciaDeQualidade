package br.ufes.descontoservice2.services;

import br.ufes.descontoservice2.interfaces.IDesconto;
import br.ufes.descontoservice2.interfaces.IPedido;

public class DescontoPorQuantidade implements IDesconto{

    @Override
    public double calcular(IPedido pedido) {
        int quantidadeItens = pedido.getQuantidadeItens();
        if(quantidadeItens > 10 && quantidadeItens <= 20){
            return (pedido.getValorTotal() - pedido.getValorDesconto()) * 0.1;
        }
        if(quantidadeItens > 20){
            return pedido.getValorTotal() * 0.15;
        }
        return (pedido.getValorTotal() - pedido.getValorDesconto()) * 0.5;
    }
}
