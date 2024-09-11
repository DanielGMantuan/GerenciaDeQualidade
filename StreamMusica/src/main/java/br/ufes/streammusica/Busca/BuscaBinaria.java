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
public class BuscaBinaria implements BuscaStrategy {

    @Override
    public Optional<Musica> buscar(List<Musica> musicas, String criterio) {
        int esquerda = 0;
        int direita = musicas.size() - 1;

        while (esquerda <= direita) {
            int meio = (esquerda + direita) / 2;
            Musica musicaMeio = musicas.get(meio);

            int comparacao = musicaMeio.getTitulo().compareToIgnoreCase(criterio);
            if (comparacao == 0) {
                return Optional.of(musicaMeio);
            } else if (comparacao < 0) {
                esquerda = meio + 1;
            } else {
                direita = meio - 1;
            }
        }
        return Optional.empty(); 
    }
}
