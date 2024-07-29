import br.ufes.desconto.CalculadoraDeDescontoService;
import br.ufes.desconto.Cliente;
import br.ufes.desconto.Pedido;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class CalculadoraTest {
    @ParameterizedTest(name = "{0}")
    @CsvFileSource(resources = "/CT.csv", delimiterString = ";",numLinesToSkip = 1)
    public void CalculadoraTest(String nomeTeste, String descricao, String nomeCliente,int fidelidade, double valorTotal, int qtdItens,  double resultadoEsperado, String error) {
        if(error == null){
            Cliente cliente = new Cliente(nomeCliente, fidelidade);
            Pedido pedido = new Pedido(cliente, valorTotal, qtdItens);

            CalculadoraDeDescontoService calculadora = new CalculadoraDeDescontoService();
            calculadora.calcular(pedido);
            
            Assertions.assertEquals(resultadoEsperado, pedido.getValorDesconto(), 0.001);
        }
        else{
            IllegalArgumentException excecaoNegativa = Assertions.assertThrows(IllegalArgumentException.class, () -> {
                Cliente cliente = new Cliente(nomeCliente, fidelidade);
                Pedido pedido = new Pedido(cliente, valorTotal, qtdItens);

                CalculadoraDeDescontoService calculadora = new CalculadoraDeDescontoService();
                calculadora.calcular(pedido);
            });
            
            Assertions.assertEquals(error, excecaoNegativa.getMessage());
        }
    }
}
