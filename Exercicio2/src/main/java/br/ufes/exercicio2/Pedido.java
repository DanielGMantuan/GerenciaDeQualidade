/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufes.exercicio2;

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


    public Pedido(Cliente cliente, double valorTotal, int quantidadeItens) {
        if (valorTotal <= 0) {
            throw new IllegalArgumentException("Falha: O valor total do pedido deve ser positivo.");
        }
        if (quantidadeItens <= 0) {
            throw new IllegalArgumentException("Falha: A quantidade de itens deve ser maior que 0.");
        }
        this.cliente = cliente;
        this.valorTotal = valorTotal;
        this.quantidadeItens = quantidadeItens;
        this.valorDesconto = 0;
    }


    @Override
    public Cliente getCliente() {
        return cliente;
    }


    @Override
    public double getValorTotal() {
        return valorTotal;
    }


    @Override
    public int getQuantidadeItens() {
        return quantidadeItens;
    }


    @Override
    public double getValorDesconto() {
        return valorDesconto;
    }


    @Override
    public void setValorDesconto(double valorDesconto) {
        this.valorDesconto = valorDesconto;
    }
}

