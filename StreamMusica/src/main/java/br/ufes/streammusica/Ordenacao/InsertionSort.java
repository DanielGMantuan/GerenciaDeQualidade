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
public class InsertionSort implements OrdenacaoStrategy {

    @Override
    public void ordenar(List<Musica> musicas) {
        int n = musicas.size();
        for (int i = 1; i < n; ++i) {
            Musica key = musicas.get(i);
            int j = i - 1;

            while (j >= 0 && musicas.get(j).getTitulo().compareTo(key.getTitulo()) > 0) {
                musicas.set(j + 1, musicas.get(j));
                j = j - 1;
            }
            musicas.set(j + 1, key);
        }
    }
}
