/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufes.testesdeintegracaoaplicados.Models;

import br.ufes.desconto.ICliente;
import br.ufes.desconto.IPedido;

/**
 *
 * @author NOTE155
 */
public class Pedido implements IPedido {

    private Cliente cliente;
    private double valorTotal;
    private int quantidadeItens;
    private double valorDesconto;
    
    public Pedido(Cliente cliente, double valorTotal, int quantidadedeItens){
        this.cliente = cliente;
        this.valorTotal = valorTotal;
        this.quantidadeItens = quantidadedeItens;
        this.valorDesconto = 0;
    }
    
    @Override
    public ICliente getCliente() {
        return this.cliente;
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
    public double getValorTotal() {
        return this.valorTotal;
    }

    @Override
    public void setValorDesconto(double d) {
        this.valorDesconto = d;
    }
}
