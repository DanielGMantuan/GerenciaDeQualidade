/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exercicioicms;

import br.ufes.exercicioicms.CalculadoraICMS;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author NOTE155
 */
public class TestICMS {
    @Test
    public void Valor0(){
        double entrada = 0;
        CalculadoraICMS calculadora = new CalculadoraICMS();
        double saida = calculadora.CalcularValorTotalComIcms(entrada);
        
        Assertions.assertEquals(-1, saida);
    }
    
    @Test
    public void ValorNegativo(){
        double entrada = -1;
        CalculadoraICMS calculadora = new CalculadoraICMS();
        double saida = calculadora.CalcularValorTotalComIcms(entrada);
        
        Assertions.assertEquals(-1, saida);
    }
    
    @Test
    public void ValorNegativo1(){
        double entrada = -0.1;
        CalculadoraICMS calculadora = new CalculadoraICMS();
        double saida = calculadora.CalcularValorTotalComIcms(entrada);
        
        Assertions.assertEquals(-1, saida);
    }
    
    @Test
    public void ValorPositivo(){
        double entrada = 1.2;
        CalculadoraICMS calculadora = new CalculadoraICMS();
        double saida = calculadora.CalcularValorTotalComIcms(entrada);
        
        Assertions.assertEquals(entrada * 1.18, saida);
    }
    
    @Test
    public void ValorPositivo1(){
        double entrada = 100.36;
        CalculadoraICMS calculadora = new CalculadoraICMS();
        double saida = calculadora.CalcularValorTotalComIcms(entrada);
        
        Assertions.assertEquals(entrada * 1.18, saida);
    }
    
    @Test
    public void ValorPositivo2(){
        double entrada = 149.02;
        CalculadoraICMS calculadora = new CalculadoraICMS();
        double saida = calculadora.CalcularValorTotalComIcms(entrada);
        
        Assertions.assertEquals(entrada * 1.18, saida);
    }
}
