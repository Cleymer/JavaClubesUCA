/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author Administrador
 */
public class Persona {
    String nombre;
    String apellidos;
    Date fechanac;
    String email;
    String telefono;
    String sexo;
    private String pw;

    public Persona() {
    }

    public Persona(String nombre, String apellidos, Date fechanac, String email, String telefono, String sexo, String pw) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechanac = fechanac;
        this.email = email;
        this.telefono = telefono;
        this.sexo = sexo;
        this.pw = pw;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Date getFechanac() {
        return fechanac;
    }

    public void setFechanac(Date fechanac) {
        this.fechanac = fechanac;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }
    
}
