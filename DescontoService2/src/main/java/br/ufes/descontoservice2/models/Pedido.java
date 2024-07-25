package br.ufes.descontoservice2.models;

import br.ufes.descontoservice2.interfaces.ICliente;
import br.ufes.descontoservice2.interfaces.IPedido;

public class Pedido implements IPedido{
    private final ICliente cliente;
    private final int quantidadeItens;
    private final double valorTotal;
    private double valorDesconto;
    
    public Pedido(ICliente cliente, double valorTotal, int quantidadeItens){
        if(quantidadeItens <= 0){
            throw new IllegalArgumentException("Falha: A quantidade de itens deve ser maior que 0.");
        }
        if(valorTotal < 0){
            throw new IllegalArgumentException("Falha: O valor total do pedido deve ser positivo.");
        }
        this.cliente = cliente;
        this.quantidadeItens = quantidadeItens;
        this.valorTotal = valorTotal;
        this.valorDesconto = 0;
    }
    
    @Override
    public ICliente getCliente() {
        return this.cliente;
    }

    @Override
    public double getValorTotal() {
        return this.valorTotal;
    }

    @Override
    public int getQuantidadeItens() {
        return this.quantidadeItens;
    }

    @Override
    public double getValorDesconto() {
        return this.valorDesconto;
    }

    @Override
    public void setValorDesconto(double valorDesconto) {
        this.valorDesconto = valorDesconto;
    }
}
