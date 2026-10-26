package com.example.listanorberto;

public class VideoJuegos {private String titulo;
    private String anio;
    private int imagen; // el recurso de la imagen: R.drawable.algo
    public VideoJuegos(String titulo, String anio, int imagen) {
        this.titulo = titulo;
        this.anio = anio;
        this.imagen = imagen;
    }
    public String getTitulo() {
        return titulo;
    }
    public String getAnio() {
        return anio;
    }
    public int getImagen() {
        return imagen;
    }

}
