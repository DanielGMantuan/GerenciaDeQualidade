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
public interface OrdenacaoStrategy {
    void ordenar (List<Musica> musicas);
}
