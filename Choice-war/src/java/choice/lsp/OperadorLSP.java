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
public class OperadorLSP {

    private String nombre;
    private String abrev;
    private BigDecimal r2;
    private BigDecimal r3;
    private BigDecimal r4;
    private BigDecimal r5;

    public OperadorLSP(String nombre, String abrev, BigDecimal r2, BigDecimal r3,
            BigDecimal r4, BigDecimal r5) {
        this.nombre = nombre;
        this.abrev = abrev;
        this.r2 = r2;
        this.r3 = r3;
        this.r4 = r4;
        this.r5 = r5;
    }

    public String getAbrev() {
        return abrev;
    }

    public String getNombre() {
        return nombre;
    }

    public BigDecimal getR2() {
        return r2;
    }

    public BigDecimal getR3() {
        return r3;
    }

    public BigDecimal getR4() {
        return r4;
    }

    public BigDecimal getR5() {
        return r5;
    }

    public BigDecimal getValR(int entradas) throws Exception {
        if (this.abrev.equals("A")) {
            return this.r2;
        }
        switch (entradas) {
            case 2: {
                return this.r2;
            }
            case 3: {
                return this.r3;
            }
            case 4: {
                return this.r4;
            }
            case 5: {
                return this.r5;
            }
            default: {
                throw new Exception("Valor no valido para las entradas requeridas");
            }
        }
    }
}
