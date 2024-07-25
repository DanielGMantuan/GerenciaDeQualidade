package br.ufes.descontoservice2.services;

import br.ufes.descontoservice2.interfaces.IDesconto;
import br.ufes.descontoservice2.interfaces.IPedido;

public class DescontoPorFilidade implements IDesconto{

    @Override
    public double calcular(IPedido pedido) {
        int anosFidelidade = pedido.getCliente().getAnosDeFidelidade();
        if( anosFidelidade > 1 && anosFidelidade <= 5){
            return (pedido.getValorTotal()- pedido.getValorDesconto()) * 0.07;
        }
        if(anosFidelidade > 5){
            return (pedido.getValorTotal() - pedido.getValorDesconto()) * 0.12;
        }
        return 0;
    }
}
