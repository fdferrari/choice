/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package choice.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author federico
 */
public class Alternativa {

    private String nombre;
    private Double IG;
    private List<CaracteristicaValor> listaValor;

    public Alternativa(String nombre) {
        this.nombre = nombre;
        this.listaValor = new ArrayList<>();
    }

    public List<CaracteristicaValor> getListaValor() {
        return listaValor;
    }

    public void setListaValor(List<CaracteristicaValor> listaValor) {
        this.listaValor = listaValor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getIG() {
        return IG;
    }

    public void setIG(Double IG) {
        this.IG = IG;
    }
}
