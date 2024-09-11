/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufes.streammusica.Ordenacao;

import br.ufes.streammusica.Musica;
import java.util.List;

/**
 *
 * @author NOTE155
 */
public class BubbleSort implements OrdenacaoStrategy {

    @Override
    public void ordenar(List<Musica> musicas) {
       int n = musicas.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (musicas.get(j).getTitulo().compareTo(musicas.get(j + 1).getTitulo()) > 0) {
                    Musica temp = musicas.get(j);
                    musicas.set(j, musicas.get(j + 1));
                    musicas.set(j + 1, temp);
                }
            }
        }
    }
}
