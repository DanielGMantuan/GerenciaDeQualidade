package br.ufes.descontoservice2.models;

import br.ufes.descontoservice2.interfaces.ICliente;

public class Cliente implements ICliente{
    private final String nome;
    private final int anosDeFidelidade;
    
    public Cliente(String nome, int anosFidelidade){
        if(anosFidelidade < 0){
            throw new IllegalArgumentException("Falha: O número de anos de fidelidade deve ser não negativo.");
        }
        this.nome = nome;
        this.anosDeFidelidade = anosFidelidade;
    }
    
    public String getNome(){
        return this.nome;
    }
    
    @Override
    public int getAnosDeFidelidade() {
        return this.anosDeFidelidade;
    }
}
