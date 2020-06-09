/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package choice.util;

/**
 *
 * @author federico
 */
public class Caracteristica {
    private String nombre;
    private Double peso;
    private Integer importancia;

    public Caracteristica() {
    }

    public Caracteristica(String nombre, Double peso, Integer importancia) {
        this.nombre = nombre;
        this.peso = peso;
        this.importancia=importancia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Integer getImportancia() {
        return importancia;
    }

    public void setImportancia(Integer importancia) {
        this.importancia = importancia;
    }
    
    
}
