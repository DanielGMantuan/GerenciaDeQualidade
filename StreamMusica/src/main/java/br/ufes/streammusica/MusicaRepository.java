/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufes.streammusica;

import br.ufes.streammusica.Ordenacao.OrdenacaoStrategy;
import java.util.List;

/**
 *
 * @author NOTE155
 */
public class MusicaRepository {
    private List<Musica> musicas;
    private OrdenacaoStrategy ordenacaoStrategy;
    
    public MusicaRepository(List<Musica> musicas, OrdenacaoStrategy ordenacao){
        this.musicas = musicas;
        this.ordenacaoStrategy = ordenacao;
    }
    
    public void setOrdenacaoStrategy(OrdenacaoStrategy ordenacao){
        this.ordenacaoStrategy = ordenacao;
    }
    
    public void ordenarMusicas(){
        ordenacaoStrategy.ordenar(musicas);
    }
    
    public List<Musica> getMusicas(){
        return musicas;
    }
}
