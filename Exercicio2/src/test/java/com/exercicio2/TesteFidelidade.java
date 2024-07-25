/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exercicio2;

import br.ufes.desconto.CalculadoraDeDescontoService;
import br.ufes.desconto.DescontoPorFidelidade;
import br.ufes.exercicio2.Cliente;
import br.ufes.exercicio2.Pedido;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author NOTE155
 */
public class TesteFidelidade {
    @Test
    public void TestException(){
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () ->{
            Cliente cliente = new Cliente("João", -1);
        });
        Assertions.assertEquals("Falha: O número de anos de fidelidade deve ser não negativo." , exception.getMessage());
    }
    
    @Test
    public void DescontoPorFidelidadeMenor1Ano(){
        Cliente cliente = new Cliente("João", 0);
        Pedido pedido = new Pedido(cliente, 1200.0, 2);
       
        CalculadoraDeDescontoService calculadora = new CalculadoraDeDescontoService();

        calculadora.setEstrategia(new DescontoPorFidelidade());
        calculadora.calcular(pedido);
       
        Assertions.assertEquals( pedido.getValorTotal() * 0, pedido.getValorDesconto());
    }
    
    @Test
    public void DescontoPorFidelidade1Ano(){
        Cliente cliente = new Cliente("João", 1);
        Pedido pedido = new Pedido(cliente, 1200.0, 2);
       
        CalculadoraDeDescontoService calculadora = new CalculadoraDeDescontoService();

        calculadora.setEstrategia(new DescontoPorFidelidade());
        calculadora.calcular(pedido);
       
        Assertions.assertEquals( pedido.getValorTotal() * 0, pedido.getValorDesconto());
    }
    
    @Test
    public void DescontoPorFidelidadeMaior1Ano(){
        Cliente cliente = new Cliente("João", 2);
        Pedido pedido = new Pedido(cliente, 1200.0, 2);
       
        CalculadoraDeDescontoService calculadora = new CalculadoraDeDescontoService();

        calculadora.setEstrategia(new DescontoPorFidelidade());
        calculadora.calcular(pedido);
       
        Assertions.assertEquals( 1200.0 * 0.07, pedido.getValorDesconto());
    }
    
    @Test
    public void DescontoPorFidelidadeIgual5Ano(){
        Cliente cliente = new Cliente("João", 5);
        Pedido pedido = new Pedido(cliente, 1200.0, 2);
       
        CalculadoraDeDescontoService calculadora = new CalculadoraDeDescontoService();

        calculadora.setEstrategia(new DescontoPorFidelidade());
        calculadora.calcular(pedido);
       
        Assertions.assertEquals( 1200.0 * 0.07, pedido.getValorDesconto());
    }
    
    @Test
    public void DescontoPorFidelidadeMaior5Anos(){
        Cliente cliente = new Cliente("João", 6);
        Pedido pedido = new Pedido(cliente, 1200.0, 2);
       
        CalculadoraDeDescontoService calculadora = new CalculadoraDeDescontoService();

        calculadora.setEstrategia(new DescontoPorFidelidade());
        calculadora.calcular(pedido);
       
        Assertions.assertEquals( 1200.0 * 0.12, pedido.getValorDesconto());
    }
}
