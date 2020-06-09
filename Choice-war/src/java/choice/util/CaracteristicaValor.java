/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package choice.util;

import java.math.BigDecimal;

/**
 *
 * @author federico
 */
public class CaracteristicaValor extends Caracteristica {
    
    private Integer puntaje;
    private String imageHeartA;
    private String imageHeartB;
    private String imageHeartC;
   
    
    public CaracteristicaValor(Integer puntaje, String nombre, Double peso, Integer importancia) {
        super(nombre, peso, importancia);
        this.puntaje = puntaje;
        this.imageHeartA = "images/heart_blanco.png";
        this.imageHeartB = "images/heart_blanco.png";
        this.imageHeartC = "images/heart_blanco.png";
    }
    
    public Integer getPuntaje() {
        return puntaje;
    }
    
    public void setPuntaje(Integer puntaje) {
        this.puntaje = puntaje;
    }
    
    public String getImageHeartA() {
        return imageHeartA;
    }
    
    public void setImageHeartA(String imageHeartA) {
        this.imageHeartA = imageHeartA;
    }
    
    public String getImageHeartB() {
        return imageHeartB;
    }
    
    public void setImageHeartB(String imageHeartB) {
        this.imageHeartB = imageHeartB;
    }
    
    public String getImageHeartC() {
        return imageHeartC;
    }
    
    public void setImageHeartC(String imageHeartC) {
        this.imageHeartC = imageHeartC;
    }

    
    private Double redondear(Double numero) {
        return Math.rint(numero * 100) / 100;
    }
    
    /*
     * Convierte el puntaje a valores entre 0 y 1
     */
    public Double normalizar() {
        Double valor = Double.valueOf(String.valueOf(puntaje));
        Double min = 0.0;//para que cuando valor sea 1 y no se haga 0.0 se arregla con min = 0.0
        Double max = 5.0;
        Double resultado = Math.abs(Math.rint(((valor - min) / (max - min)) * 100) / 100);
        return this.redondear(resultado);
    }
}
