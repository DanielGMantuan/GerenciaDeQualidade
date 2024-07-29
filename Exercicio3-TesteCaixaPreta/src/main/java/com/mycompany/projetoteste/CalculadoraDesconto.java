/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.projetoteste;

import br.ufes.desconto.CalculadoraDeDescontoService;
import br.ufes.desconto.Cliente;
import br.ufes.desconto.Pedido;

/**
 *
 * @author Aluno
 */
public class CalculadoraDesconto{
    
    public static void main(String[] args) {
        Cliente cliente = new Cliente("João", 3);
        Pedido pedido = new Pedido(cliente, 1000.0, 15);


        CalculadoraDeDescontoService calculadora = new CalculadoraDeDescontoService();
        calculadora.calcular(pedido);


        System.out.println("Desconto total do pedido: " + pedido.getValorDesconto());
    }

}
