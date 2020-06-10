package com.example.aplicacionmercadoa;

public class Vendedor {
    public String nombre;
    public String apellidos;
    public String correo;
    public String celular;
    public String contra;
    public String dni;
    public String nombretienda;
    public String mercado;
    public String ubicacion;
    public String rubro;


    public Vendedor(){

    }
    public Vendedor(String nombre,String apellidos, String correo, String celular, String contra, String dni, String nombretienda, String mercado, String ubicacion, String rubro ){
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.correo = correo;
        this.ubicacion = ubicacion;
        this.dni = dni;
        this.nombretienda = nombretienda;
        this.mercado = mercado;
        this.rubro = rubro;
        this.celular = celular;
        this.contra = contra;
    }
}
