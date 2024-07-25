package br.ufes.descontoservice2.interfaces;

public interface IPedido {
    ICliente getCliente();
    double getValorTotal();
    int getQuantidadeItens();
    double getValorDesconto();
    void setValorDesconto(double valorDesconto);
}
