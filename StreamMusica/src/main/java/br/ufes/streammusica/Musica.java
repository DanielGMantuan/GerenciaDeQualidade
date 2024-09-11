/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.ufes.streammusica;

/**
 *
 * @author NOTE155
 */
public class Musica {
    private String titulo;
    private String artista;
    
    public Musica(String titulo, String artista){
        this.titulo = titulo;
        this.artista = artista;
    }
    
    public String getTitulo(){
        return this.titulo;
    }
    
    public String getArtista(){
        return this.artista;
    }
}
