/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package br.ufes.exercicioicms;

/**
 *
 * @author NOTE155
 */
public class CalculadoraICMS {

    public double CalcularValorTotalComIcms(double valor) {
        if(valor <= 0){
            return -1;
        }
        
        return valor * 1.18;
    }
}
