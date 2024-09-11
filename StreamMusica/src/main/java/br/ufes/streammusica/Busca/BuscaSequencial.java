/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufes.streammusica.Busca;

import br.ufes.streammusica.Musica;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author NOTE155
 */
public class BuscaSequencial implements BuscaStrategy {

    @Override
    public Optional<Musica> buscar(List<Musica> musicas, String criterio) {
        for (Musica musica : musicas) {
            if (musica.getTitulo().equalsIgnoreCase(criterio) 
                || musica.getArtista().equalsIgnoreCase(criterio)) {
                return Optional.of(musica);
            }
        }
        return Optional.empty();  
    }
}
