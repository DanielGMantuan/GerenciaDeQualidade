/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.ufes.testesdeintegracaoaplicados;

import br.ufes.desconto.IPedido;

/**
 *
 * @author NOTE155
 */
public interface MetodoPagamento {
    boolean pagar(IPedido pedido);
}
