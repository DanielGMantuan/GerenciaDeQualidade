/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufes.testesdeintegracaoaplicados.Models;

import br.ufes.desconto.ICliente;

/**
 *
 * @author NOTE155
 */
public class Cliente implements ICliente {

    private String nome;
    private int anoFidelidade;
    
    public Cliente(String nome, int anosFidelidade){
        if(anosFidelidade < 0){
            throw new IllegalArgumentException("Falha: O número de anos de fidelidade deve ser não negativo.");
        }
        
        this.nome = nome;
        this.anoFidelidade = anosFidelidade;
    }
    
    @Override
    public int getAnosDeFidelidade() {
        return this.anoFidelidade;
    }
}
