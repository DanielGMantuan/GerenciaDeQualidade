package br.ufes.descontoservice2.services;

import br.ufes.descontoservice2.interfaces.IDesconto;
import br.ufes.descontoservice2.interfaces.IPedido;

public class DescontoPorValorTotal implements IDesconto{

    @Override
    public double calcular(IPedido pedido) {
        double valorTotal = pedido.getValorTotal();
        if(valorTotal > 500 && valorTotal <= 1000){
            return (valorTotal - pedido.getValorDesconto()) * 0.05;
        }
        if(valorTotal > 1000){
            return (valorTotal - pedido.getValorDesconto()) * 0.1;
        }
        return 0;
    }
}
