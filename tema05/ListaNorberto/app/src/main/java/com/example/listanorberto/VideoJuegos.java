package com.example.listanorberto;

public class VideoJuegos {private String titulo;
    private String plataforma;
    private int imagen; // el recurso de la imagen: R.drawable.algo
    public VideoJuegos(String titulo, String anio, int imagen) {
        this.titulo = titulo;
        this.plataforma = anio;
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
