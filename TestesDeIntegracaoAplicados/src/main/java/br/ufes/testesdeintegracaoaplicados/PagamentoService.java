/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufes.testesdeintegracaoaplicados;

import br.ufes.desconto.CalculadoraDeDescontoService;
import br.ufes.desconto.IPedido;

/**
 *
 * @author NOTE155
 */
public interface PagamentoService {
    double processarPagamento(IPedido pedido, CalculadoraDeDescontoService calculadora, MetodoPagamento metodoPagamento);
    boolean verificarPagamento(IPedido pedido);
    void confirmarPagamento(IPedido pedido);
}
