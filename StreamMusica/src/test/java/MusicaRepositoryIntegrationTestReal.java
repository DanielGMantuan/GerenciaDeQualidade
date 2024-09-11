/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author NOTE155
 */

import br.ufes.streammusica.Ordenacao.BubbleSort;
import br.ufes.streammusica.Ordenacao.InsertionSort;
import br.ufes.streammusica.Musica;
import br.ufes.streammusica.MusicaRepository;
import br.ufes.streammusica.Ordenacao.OrdenacaoStrategy;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class MusicaRepositoryIntegrationTestReal {
    private MusicaRepository musicaRepository;
    private List<Musica> musicas;
    
    @BeforeEach
    public void setUp(){
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
    public void testOrdenacaoComBubbleSort(){
        OrdenacaoStrategy bubbleSort = new BubbleSort();
        musicaRepository = new MusicaRepository(musicas, bubbleSort);
        
        musicaRepository.ordenarMusicas();
        List<Musica> musicasOrdenadas = musicaRepository.getMusicas();
        
        assertEquals("Billie Jean", musicasOrdenadas.get(0).getTitulo());
        assertEquals("Bohemian Rhapsody", musicasOrdenadas.get(1).getTitulo());
        assertEquals("Comfortably Numb", musicasOrdenadas.get(2).getTitulo());
        assertEquals("Hey Jude", musicasOrdenadas.get(3).getTitulo());
        assertEquals("Hotel California", musicasOrdenadas.get(4).getTitulo());
        assertEquals("Imagine", musicasOrdenadas.get(5).getTitulo());
        assertEquals("Like a Rolling Stone", musicasOrdenadas.get(6).getTitulo());
        assertEquals("Smells Like Teen Spirit", musicasOrdenadas.get(7).getTitulo());
        assertEquals("Stairway to Heaven", musicasOrdenadas.get(8).getTitulo());
        assertEquals("Sweet Child O' Mine", musicasOrdenadas.get(9).getTitulo());
    }
    
    @Test
    public void testOrdenacaoComInsertionSort(){
        OrdenacaoStrategy insertionSort = new InsertionSort();
        musicaRepository = new MusicaRepository(musicas, insertionSort);
        
        musicaRepository.ordenarMusicas();
        List<Musica> musicasOrdenadas = musicaRepository.getMusicas();
        
        assertEquals("Billie Jean", musicasOrdenadas.get(0).getTitulo());
        assertEquals("Bohemian Rhapsody", musicasOrdenadas.get(1).getTitulo());
        assertEquals("Comfortably Numb", musicasOrdenadas.get(2).getTitulo());
        assertEquals("Hey Jude", musicasOrdenadas.get(3).getTitulo());
        assertEquals("Hotel California", musicasOrdenadas.get(4).getTitulo());
        assertEquals("Imagine", musicasOrdenadas.get(5).getTitulo());
        assertEquals("Like a Rolling Stone", musicasOrdenadas.get(6).getTitulo());
        assertEquals("Smells Like Teen Spirit", musicasOrdenadas.get(7).getTitulo());
        assertEquals("Stairway to Heaven", musicasOrdenadas.get(8).getTitulo());
        assertEquals("Sweet Child O' Mine", musicasOrdenadas.get(9).getTitulo());
    }
}
