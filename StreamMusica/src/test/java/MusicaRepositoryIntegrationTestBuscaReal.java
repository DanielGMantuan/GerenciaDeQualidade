/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author NOTE155
 */

import br.ufes.streammusica.Busca.BuscaBinaria;
import br.ufes.streammusica.Busca.BuscaSequencial;
import br.ufes.streammusica.Busca.BuscaStrategy;
import br.ufes.streammusica.Musica;
import br.ufes.streammusica.MusicaRepository;
import br.ufes.streammusica.Ordenacao.BubbleSort;
import br.ufes.streammusica.Ordenacao.OrdenacaoStrategy;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class MusicaRepositoryIntegrationTestBuscaReal {
    private MusicaRepository musicaRepository;
    private List<Musica> musicas;

    @BeforeEach
    public void setUp() {
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
        
        OrdenacaoStrategy bubbleSort = new BubbleSort();
        musicaRepository = new MusicaRepository(musicas, bubbleSort);
    }
    
    @Test
    public void testBuscaComBuscaSequencial(){
        BuscaStrategy buscaSequencial = new BuscaSequencial();
        
        Optional<Musica> resultado = buscaSequencial.buscar(musicas, "Bohemian Rhapsody");
        
        assertTrue(resultado.isPresent());
        assertEquals("Bohemian Rhapsody", resultado.get().getTitulo());
        assertEquals("Queen", resultado.get().getArtista());
    }
    
    @Test
    public void testBuscaComBuscaBinaria(){
        BuscaStrategy buscaBinaria = new BuscaBinaria();
        musicaRepository.ordenarMusicas();
        
        Optional<Musica> resultado = buscaBinaria.buscar(musicas,  "Bohemian Rhapsody");
        
        assertTrue(resultado.isPresent());
        assertEquals("Bohemian Rhapsody", resultado.get().getTitulo());
        assertEquals("Queen", resultado.get().getArtista());
    }
}
