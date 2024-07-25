/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exercicio2;

import br.ufes.desconto.CalculadoraDeDescontoService;
import br.ufes.desconto.DescontoPorValorTotal;
import br.ufes.exercicio2.Cliente;
import br.ufes.exercicio2.Pedido;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author NOTE155
 */
public class TesteValorTotal {
    @Test
    public void TestException(){
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            Cliente cliente = new Cliente("Jao", 0);
            Pedido pedido = new Pedido(cliente, -0.1, 2);
        });
        Assertions.assertEquals( "Falha: O valor total do pedido deve ser positivo.", exception.getMessage());
    }
    
    @Test
    public void DescontoPorValorMenor500(){
        Cliente cliente = new Cliente("João", 3);
        Pedido pedido = new Pedido(cliente, 499.9, 2);
       
        CalculadoraDeDescontoService calculadora = new CalculadoraDeDescontoService();

        calculadora.setEstrategia(new DescontoPorValorTotal());
        calculadora.calcular(pedido);
       
        Assertions.assertEquals( pedido.getValorTotal() * 0, pedido.getValorDesconto());
    }
    
    @Test
    public void DescontoPorValor500(){
        Cliente cliente = new Cliente("João", 3);
        Pedido pedido = new Pedido(cliente, 500.0, 2);
       
        CalculadoraDeDescontoService calculadora = new CalculadoraDeDescontoService();

        calculadora.setEstrategia(new DescontoPorValorTotal());
        calculadora.calcular(pedido);
       
        Assertions.assertEquals( pedido.getValorTotal() * 0, pedido.getValorDesconto());
    }
    
    @Test
    public void DescontoPorValorMaior500(){
        Cliente cliente = new Cliente("João", 3);
        Pedido pedido = new Pedido(cliente, 500.1, 2);
       
        CalculadoraDeDescontoService calculadora = new CalculadoraDeDescontoService();

        calculadora.setEstrategia(new DescontoPorValorTotal());
        calculadora.calcular(pedido);
       
        Assertions.assertEquals( pedido.getValorTotal() * 0.05, pedido.getValorDesconto());
    }
    
    @Test
    public void DescontoPorValor1000(){
        Cliente cliente = new Cliente("João", 3);
        Pedido pedido = new Pedido(cliente, 1000.0, 2);
       
        CalculadoraDeDescontoService calculadora = new CalculadoraDeDescontoService();

        calculadora.setEstrategia(new DescontoPorValorTotal());
        calculadora.calcular(pedido);
       
        Assertions.assertEquals( pedido.getValorTotal() * 0.05, pedido.getValorDesconto());
    }
    
    @Test
    public void DescontoPorValorMaior1000(){
        Cliente cliente = new Cliente("João", 3);
        Pedido pedido = new Pedido(cliente, 1000.1, 2);
       
        CalculadoraDeDescontoService calculadora = new CalculadoraDeDescontoService();

        calculadora.setEstrategia(new DescontoPorValorTotal());
        calculadora.calcular(pedido);
       
        Assertions.assertEquals( pedido.getValorTotal() * 0.1, pedido.getValorDesconto());
    }
}
