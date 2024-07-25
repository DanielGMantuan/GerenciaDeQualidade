package br.ufes.descontoservice2.services;

import br.ufes.descontoservice2.interfaces.IDesconto;
import br.ufes.descontoservice2.interfaces.IPedido;
import java.util.ArrayList;
import java.util.List;

public class CalculadoraDeDescontoService {
    private final List<IDesconto> descontos;
    
    public CalculadoraDeDescontoService(){
        this.descontos = new ArrayList<>();
        this.descontos.add(new DescontoPorQuantidade());
        this.descontos.add(new DescontoPorFilidade());
        this.descontos.add(new DescontoPorValorTotal());
    }
    
    public void processar(IPedido pedido){
        descontos.forEach(desconto ->{
            pedido.setValorDesconto(pedido.getValorDesconto() + desconto.calcular(pedido) );
        });
    }
}
