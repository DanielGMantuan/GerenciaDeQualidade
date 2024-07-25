/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exercicio2;

import br.ufes.desconto.CalculadoraDeDescontoService;
import br.ufes.desconto.DescontoPorQuantidade;
import br.ufes.exercicio2.Cliente;
import br.ufes.exercicio2.Pedido;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author NOTE155
 */
/*
Desconto por Quantidade:
    Se a quantidade de itens for maior que 20, o desconto será de 15% do valor total do pedido.
    Se a quantidade de itens for maior que 10, o desconto será de 10% do valor total do pedido.
    Se a quantidade de itens for maior que 0, o desconto será de 5% do valor total do pedido.
Desconto por Fidelidade:
    Se o cliente tiver mais de 5 anos de fidelidade, o desconto será de 12% do valor total do pedido.
    Se o cliente tiver mais de 1 ano de fidelidade, o desconto será de 7% do valor total do pedido.
Desconto por Valor Total:
    Se o valor total do pedido for maior que R$ 1000,00, o desconto será de 10%.
    Se o valor total do pedido for maior que R$ 500,00, o desconto será de 5%.
*/

public class TesteQuantidade {
    @Test
    public void ExceptionQuantideNegativa(){
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            Cliente cliente = new Cliente("Jao", 1);
            Pedido pedido = new Pedido(cliente, 100, -1);
        });
        Assertions.assertEquals( "Falha: A quantidade de itens deve ser maior que 0.", exception.getMessage());
    }
    
    @Test
    public void ExceptionQuantide0(){
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            Cliente cliente = new Cliente("Jao", 1);
            Pedido pedido = new Pedido(cliente, 100, 0);
        });
        Assertions.assertEquals( "Falha: A quantidade de itens deve ser maior que 0.", exception.getMessage());
    }
    
    @Test
    public void DescontoPorQuantidadeMaior0(){
        Cliente cliente = new Cliente("João", 1);
        Pedido pedido = new Pedido(cliente, 1200.0, 1);
       
        CalculadoraDeDescontoService calculadora = new CalculadoraDeDescontoService();

        calculadora.setEstrategia(new DescontoPorQuantidade());
        calculadora.calcular(pedido);
       
        Assertions.assertEquals( pedido.getValorTotal() * 0.05, pedido.getValorDesconto());
    }
    
    @Test
    public void DescontoPorQuantidade10(){
        Cliente cliente = new Cliente("João", 3);
        Pedido pedido = new Pedido(cliente, 1200.0, 10);
       
        CalculadoraDeDescontoService calculadora = new CalculadoraDeDescontoService();

        calculadora.setEstrategia(new DescontoPorQuantidade());
        calculadora.calcular(pedido);
       
        Assertions.assertEquals( pedido.getValorTotal()* 0.05, pedido.getValorDesconto());
    }
    
    @Test
    public void DescontoPorQuantidadeMaior10(){
        Cliente cliente = new Cliente("João", 3);
        Pedido pedido = new Pedido(cliente, 1200.0, 11);
       
        CalculadoraDeDescontoService calculadora = new CalculadoraDeDescontoService();

        calculadora.setEstrategia(new DescontoPorQuantidade());
        calculadora.calcular(pedido);
       
        Assertions.assertEquals( pedido.getValorTotal() * 0.1, pedido.getValorDesconto());
    }
    
    @Test
    public void DescontoPorQuantidade20(){
        Cliente cliente = new Cliente("João", 3);
        Pedido pedido = new Pedido(cliente, 1200.0, 20);
       
        CalculadoraDeDescontoService calculadora = new CalculadoraDeDescontoService();

        calculadora.setEstrategia(new DescontoPorQuantidade());
        calculadora.calcular(pedido);
       
        Assertions.assertEquals( pedido.getValorTotal() * 0.1, pedido.getValorDesconto());
    }
    
    @Test
    public void DescontoPorQuantidadeMaior20(){
        Cliente cliente = new Cliente("João", 3);
        Pedido pedido = new Pedido(cliente, 1200.0, 21);
       
        CalculadoraDeDescontoService calculadora = new CalculadoraDeDescontoService();

        calculadora.setEstrategia(new DescontoPorQuantidade());
        calculadora.calcular(pedido);
       
        Assertions.assertEquals(pedido.getValorTotal() * 0.15, pedido.getValorDesconto());
    }
}

