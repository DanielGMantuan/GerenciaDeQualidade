/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author NOTE155
 */

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import br.ufes.desconto.CalculadoraDeDescontoService;
import br.ufes.testesdeintegracaoaplicados.MetodoPagamento;
import br.ufes.testesdeintegracaoaplicados.Models.Cliente;
import br.ufes.testesdeintegracaoaplicados.Models.Pedido;
import br.ufes.testesdeintegracaoaplicados.PagamentoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class PagamentoServiceIntegrationTest {
    private CalculadoraDeDescontoService calculadora;
    
    @Mock
    private PagamentoService mockPagamentoService;
    
    @Mock
    private MetodoPagamento mockMetodoPagamentoCartao;
    
    @Mock
    private MetodoPagamento mockMetodoPagamentoPix;
    
    @Mock
    private MetodoPagamento mockMetodoPagamentoDebito;
    
    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        calculadora = new CalculadoraDeDescontoService();
    }
    
    @Test
    public void testProcessamentoPedidoComDescontoPorCartao(){
        
        Pedido pedido = new Pedido(new Cliente("Jao silva", 3), 1000.00, 15);
        
        //Simula um pagamento pelo cartao bem sucedido
        when(mockMetodoPagamentoCartao.pagar(pedido)).thenReturn(Boolean.TRUE);
        
        // When: processa o pagamento com desconto
        doReturn(900.00).when(mockPagamentoService).processarPagamento(pedido, calculadora, mockMetodoPagamentoCartao);
        
        double valorFinal = mockPagamentoService.processarPagamento(pedido, calculadora, mockMetodoPagamentoCartao);
        
        // Then: verifica se o metodo foi chamado exatamento uma vez
        verify(mockPagamentoService, Mockito.times(1)).processarPagamento(pedido, calculadora, mockMetodoPagamentoCartao);
        
        assertEquals(900.00, valorFinal, "O valor final do pagamento com desconto aplicado usando cartão está incorreto.");
    }
    
    @Test
    public void testProcessamentoPedidoComValoresInvalidos(){
        Pedido pedidoInvalido = new Pedido(new Cliente("Jao", 2), 750.00, -1);
        
        doThrow(new IllegalArgumentException("Falha: A quantidade de itens deve ser maior que 0.")).when(mockPagamentoService).processarPagamento(pedidoInvalido, calculadora, mockMetodoPagamentoCartao);
    
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,() ->{
            mockPagamentoService.processarPagamento(pedidoInvalido, calculadora, mockMetodoPagamentoCartao);
        });
        
        assertEquals("Falha: A quantidade de itens deve ser maior que 0.", exception.getMessage());
        
        verify(mockPagamentoService).processarPagamento(pedidoInvalido, calculadora, mockMetodoPagamentoCartao);
    }
    
    @Test
    public void testAplicacaoDeMultiplosDescontos(){
        Pedido pedido = new Pedido(new Cliente("Jao", 10), 2000.00, 25);
        
        doReturn(1500.00).when(mockPagamentoService).processarPagamento(pedido, calculadora, mockMetodoPagamentoCartao);
        double valorFinal = mockPagamentoService.processarPagamento(pedido, calculadora, mockMetodoPagamentoCartao);
        
        verify(mockPagamentoService).processarPagamento(pedido, calculadora, mockMetodoPagamentoCartao);
        assertEquals(1500.00, valorFinal, "O valor final do pagamento com múltiplos descontos aplicados está incorreto.");
    }
    
    @Test
    public void testFalhaAoCalcularDesconto(){
        Pedido pedido = new Pedido(new Cliente("Ana", 5), 1000.00, 15);
        
        doThrow(new RuntimeException("Erro ao calcular desconto")).when(mockPagamentoService).processarPagamento(pedido, calculadora, mockMetodoPagamentoCartao);
        
        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
           mockPagamentoService.processarPagamento(pedido, calculadora, mockMetodoPagamentoCartao);
        });
        
        assertEquals("Erro ao calcular desconto", ex.getMessage());
        
        verify(mockPagamentoService).processarPagamento(pedido, calculadora, mockMetodoPagamentoCartao);
    }
    
    @Test
    public void testPedidoComDiferentesMetodosPagamento(){
        Pedido pedido = new Pedido(new Cliente("Jao", 10), 2000.00, 25);
        
        // when(...).thenReturn(...) aplicado em metodos estaticos, void ou finais
        when(mockMetodoPagamentoPix.pagar(pedido)).thenReturn(Boolean.TRUE);
        when(mockMetodoPagamentoDebito.pagar(pedido)).thenReturn(Boolean.TRUE);
        
        // doReturn(...).when(...) aplicado em metodos que tem retorno
        doReturn(1800.00).when(mockPagamentoService).processarPagamento(pedido, calculadora, mockMetodoPagamentoPix);
        doReturn(1800.00).when(mockPagamentoService).processarPagamento(pedido, calculadora, mockMetodoPagamentoDebito);
    
        // Then: entao o metodo deve ser o mesmo independente do metodo utilizado
        assertEquals(1800.00, mockPagamentoService.processarPagamento(pedido, calculadora, mockMetodoPagamentoPix));
        assertEquals(1800.00, mockPagamentoService.processarPagamento(pedido, calculadora, mockMetodoPagamentoDebito));
    }
    
    @Test
    public void testConfirmarPagamento(){
        Pedido pedido = new Pedido(new Cliente("Ana", 5), 1000.00, 15);
        
        // When: doNothing() pq nao precisa retornar
        doNothing().when(mockPagamentoService).confirmarPagamento(pedido);
        mockPagamentoService.confirmarPagamento(pedido);
        
        // Then: verifica se o metodo foi chamado corretamente
        verify(mockPagamentoService).confirmarPagamento(pedido);
    }
    
    @Test
    public void testVerificarPagamento(){
        Pedido pedido = new Pedido(new Cliente("Ana", 5), 1000.00, 15);
        
        doReturn(true).when(mockPagamentoService).verificarPagamento(pedido);
        
        assertTrue(mockPagamentoService.verificarPagamento(pedido), "O pagamento do pedido deveria estar confirmado.");
    }
    
    @Test
    public void testeFalhaNoPagamentoAposCalculoDeDesconto(){
        Pedido pedido = new Pedido(new Cliente("Ana", 4), 1000.00, 20);
    
        when(mockMetodoPagamentoCartao.pagar(pedido)).thenReturn(Boolean.FALSE);
        
        // Define o comportamento esperado para o metodo processarPagamento
        doAnswer(invocation -> {
            // pega o terceiro argumento passado para o metodo processarPagamento
            MetodoPagamento metodoPagamento = invocation.getArgument(2);
            metodoPagamento.pagar(pedido);
            throw new RuntimeException("Falha ao efetuar o pagamento.");
        }).when(mockPagamentoService).processarPagamento(pedido, calculadora, mockMetodoPagamentoCartao);
        
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            mockPagamentoService.processarPagamento(pedido, calculadora, mockMetodoPagamentoCartao);
        });
        
        verify(mockMetodoPagamentoCartao).pagar(pedido);
        assertEquals("Falha ao efetuar o pagamento.", exception.getMessage());
    }

}
