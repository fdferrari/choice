/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package choice.lsp;

import choice.util.CaracteristicaValor;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author federico
 */
public class CalculatorLSP {

    private Tabla tabla;
    private List<CaracteristicaValor> listValor;
    private List<PreferenciaElemental> listPrefElem;
    private String codFun;

    public CalculatorLSP(Integer polaridad) {
        this.listPrefElem = new ArrayList<>();
        this.tabla = new Tabla();
        codFun = "A";
        switch (polaridad) {
            case 0: {
                codFun = "C++";
                break;
            }
            case 1: {
                codFun = "CA";
                break;
            }

            case 2: {
                codFun = "A";
                break;
            }
            case 3: {
                codFun = "DA";
                break;
            }
            case 4: {
                codFun = "D++";
                break;
            }

        }
    }

    public List<CaracteristicaValor> getListValor() {
        return listValor;
    }

    public void setListValor(List<CaracteristicaValor> listValor) {
        this.listValor = listValor;
    }

    public String getCodFun() {
        return codFun;
    }

    public void setCodFun(String codFun) {
        this.codFun = codFun;
    }

    public List<PreferenciaElemental> getListPrefElem() {
        return listPrefElem;
    }

    public void setListPrefElem(List<PreferenciaElemental> listPrefElem) {
        this.listPrefElem = listPrefElem;
    }

    public Tabla getTabla() {
        return tabla;
    }

    public void setTabla(Tabla tabla) {
        this.tabla = tabla;
    }

    public Double calcularLSP(List<CaracteristicaValor> listValor) {
        Double resultado;
        try {
            this.setListValor(listValor);
            this.listPrefElem.clear();
            this.instanciar();
            resultado = this.wmp(listPrefElem, codFun).doubleValue();
        } catch (Exception e) {
            resultado = new BigDecimal("0.0").doubleValue();
        }
        return resultado;
    }

    private void instanciar() {
        PreferenciaElemental pe;
        int name = 1;
        //inicializo el modelo
        for (CaracteristicaValor caracteristicaValor : listValor) {
            pe = new PreferenciaElemental("IN " + name,
                    new BigDecimal(caracteristicaValor.normalizar().toString()),
                    new BigDecimal(caracteristicaValor.getPeso().toString()));
            this.listPrefElem.add(pe);
            name++;
        }

    }

    public BigDecimal wmp(List<PreferenciaElemental> listaPreferencia, String abrev)
            throws Exception {
        BigDecimal resultadoFinal;
        int cantEntradas = listaPreferencia.size();
        BigDecimal r = this.tabla.getValR(abrev, cantEntradas);
        BigDecimal oneOverR = new BigDecimal(new Double(1.0 / r.doubleValue()).toString());
        Double acumulador = 0.0;
        for (int i = 0; i < listaPreferencia.size(); i++) {
            Double calcParcial = Math.pow(listaPreferencia.get(i).getValor().doubleValue(),
                    r.doubleValue()) * listaPreferencia.get(i).getPeso().doubleValue();
            acumulador = acumulador + calcParcial;
        }
        Double calcParcial = Math.pow(acumulador, (oneOverR.doubleValue()));
        resultadoFinal = new BigDecimal(calcParcial.toString());
        return resultadoFinal;
    }
}
