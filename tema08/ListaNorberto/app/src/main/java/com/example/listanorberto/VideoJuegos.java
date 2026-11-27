package com.example.listanorberto;

public class VideoJuegos {private String titulo;
    private String plataforma;
    private int imagen;
    public VideoJuegos(String titulo, String plataforma, int imagen) {
        this.titulo = titulo;
        this.plataforma = plataforma;
        this.imagen = imagen;
    }
    public String getTitulo() {
        return titulo;
    }
    public String getPlataforma() {
        return plataforma;
    }
    public int getImagen() {
        return imagen;
    }

}
