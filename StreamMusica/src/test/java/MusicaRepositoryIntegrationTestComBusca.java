/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author NOTE155
 */

import br.ufes.streammusica.Busca.BuscaStrategy;
import br.ufes.streammusica.Musica;
import br.ufes.streammusica.MusicaRepository;
import br.ufes.streammusica.Ordenacao.BubbleSort;
import br.ufes.streammusica.Ordenacao.OrdenacaoStrategy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class MusicaRepositoryIntegrationTestComBusca {
    private MusicaRepository musicaRepository;
    private List<Musica> musicas;
    
    @Mock
    private BuscaStrategy mockBusca;
    
    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        musicas = Arrays.asList(
           new Musica("Smells Like Teen Spirit", "Nirvana"),
           new Musica("Bohemian Rhapsody", "Queen"),
           new Musica("Comfortably Numb", "Pink Floyd"),
           new Musica("Stairway to Heaven", "Led Zeppelin"),
           new Musica("Hotel California", "Eagles"),
           new Musica("Imagine", "John Lennon"),
           new Musica("Like a Rolling Stone", "Bob Dylan"),
           new Musica("Sweet Child O' Mine", "Guns N' Roses"),
           new Musica("Billie Jean", "Michael Jackson"),
           new Musica("Hey Jude", "The Beatles")
        );
    }
    
    @Test
    public void testBuscarComMock(){
        // define o comportamento simulado para a estrategia de busca
        when(mockBusca.buscar(anyList(), eq("Bohemian Rhapsody")))  // eq(...) faz com que so funcione para essa entrada?
            .thenReturn(Optional.of(new Musica("Bohemian Rhapsody", "Queen")));
        
        // Invoca a estrategia de busca simulada
        Optional<Musica> resultado = mockBusca.buscar(musicas, "Bohemian Rhapsody");
        
        // Verifica se o resultado e o esperado
        assertTrue(resultado.isPresent());
        assertEquals("Bohemian Rhapsody", resultado.get().getTitulo());
        assertEquals("Queen", resultado.get().getArtista());
    }
}
