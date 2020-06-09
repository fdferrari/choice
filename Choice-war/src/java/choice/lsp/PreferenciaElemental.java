/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package choice.lsp;

import java.math.BigDecimal;

/**
 *
 * @author federico ferrari - 64710
 */
public class PreferenciaElemental {

    private String descripcion;
    private BigDecimal valor;
    private BigDecimal peso;

    public PreferenciaElemental(String descripcion, BigDecimal valor, BigDecimal peso) {
        this.descripcion = descripcion;
        this.valor = valor;
        this.peso = peso;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getPeso() {
        return peso;
    }

    public void setPeso(BigDecimal peso) {
        this.peso = peso;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}
