/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package choice.web;

import choice.lsp.CalculatorLSP;
import choice.util.Alternativa;
import choice.util.Caracteristica;
import choice.util.CaracteristicaValor;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author federico
 */
@Named(value = "choiceManagedBean")
@SessionScoped
public class ChoiceManagedBean implements Serializable {

    private String nombreModelo;
    private String nombreCaract;
    private String nombreAlternativa;
    private String imageStarA;
    private String imageStarB;
    private String imageStarC;
    private String imageStarD;
    private String imageStarE;
    private int importancia;
    private Integer polaridad;
    private List<Caracteristica> listCaracteristica;
    private Caracteristica caractSeleccionada;
    private List<Alternativa> listAlternativa;
    private Alternativa alternativaSeleccionada;
    private List<CaracteristicaValor> listaValor;
    //Popup
    private String mensajePopup;
    private boolean opened;

    /**
     * Creates a new instance of ChoiceManagedBean
     */
    public ChoiceManagedBean() {
        this.limpiar();
    }

    public String getNombreModelo() {
        return nombreModelo;
    }

    public void setNombreModelo(String nombreModelo) {
        this.nombreModelo = nombreModelo;
    }

    public String getNombreCaract() {
        return nombreCaract;
    }

    public void setNombreCaract(String nombreCaract) {
        this.nombreCaract = nombreCaract;
    }

    public String getImageStarA() {
        return imageStarA;
    }

    public void setImageStarA(String imageStarA) {
        this.imageStarA = imageStarA;
    }

    public String getImageStarB() {
        return imageStarB;
    }

    public void setImageStarB(String imageStarB) {
        this.imageStarB = imageStarB;
    }

    public String getImageStarC() {
        return imageStarC;
    }

    public void setImageStarC(String imageStarC) {
        this.imageStarC = imageStarC;
    }

    public String getImageStarD() {
        return imageStarD;
    }

    public void setImageStarD(String imageStarD) {
        this.imageStarD = imageStarD;
    }

    public String getImageStarE() {
        return imageStarE;
    }

    public void setImageStarE(String imageStarE) {
        this.imageStarE = imageStarE;
    }

    public int getImportancia() {
        return importancia;
    }

    public void setImportancia(int importancia) {
        this.importancia = importancia;
    }

    public Integer getPolaridad() {
        return polaridad;
    }

    public void setPolaridad(Integer polaridad) {
        this.polaridad = polaridad;
    }

    public List<Caracteristica> getListCaracteristica() {
        return listCaracteristica;
    }

    public void setListCaracteristica(List<Caracteristica> listCaracteristica) {
        this.listCaracteristica = listCaracteristica;
    }

    public String getMensajePopup() {
        return mensajePopup;
    }

    public void setMensajePopup(String mensajePopup) {
        this.mensajePopup = mensajePopup;
    }

    public boolean isOpened() {
        return opened;
    }

    public void setOpened(boolean opened) {
        this.opened = opened;
    }

    public List<Alternativa> getListAlternativa() {
        return listAlternativa;
    }

    public void setListAlternativa(List<Alternativa> listAlternativa) {
        this.listAlternativa = listAlternativa;
    }

    public List<CaracteristicaValor> getListaValor() {
        return listaValor;
    }

    public void setListaValor(List<CaracteristicaValor> listaValor) {
        this.listaValor = listaValor;
    }

    public String getNombreAlternativa() {
        return nombreAlternativa;
    }

    public void setNombreAlternativa(String nombreAlternativa) {
        this.nombreAlternativa = nombreAlternativa;
    }

    public Alternativa getAlternativaSeleccionada() {
        return alternativaSeleccionada;
    }

    public void setAlternativaSeleccionada(Alternativa alternativaSeleccionada) {
        this.alternativaSeleccionada = alternativaSeleccionada;
    }

    public Caracteristica getCaractSeleccionada() {
        return caractSeleccionada;
    }

    public void setCaractSeleccionada(Caracteristica caractSeleccionada) {
        this.caractSeleccionada = caractSeleccionada;
    }

    public void cerrarPopup(ActionEvent event) {
        this.setOpened(false);
    }

    public String prepararNuevoCaracteristica() {
        String resultado = "#";
        if (this.listCaracteristica.size() == 5) {
            this.mensajePopup = "Supero el límite de características.";
            this.opened = true;
        } else {
            this.mensajePopup = "";
            this.opened = false;
            resultado = "nuevoCaracteristica";
        }
        return resultado;
    }

    public String prepararNuevoAlternativa() {
        String resultado = "#";
        if (this.listAlternativa.size() == 3) {
            this.mensajePopup = "Supero el límite de alternativas.";
            this.opened = true;
        } else {
            if (this.listCaracteristica.isEmpty()) {
                this.mensajePopup = "Es necesario ingresar primero al menos una característica.";
                this.opened = true;
            } else {
                this.mensajePopup = "";
                this.opened = false;
                resultado = "nuevoAlternativa";
                this.listaValor = new ArrayList<>();
                for (Caracteristica caracteristica : listCaracteristica) {
                    CaracteristicaValor caracteristicaValor = new CaracteristicaValor(0,
                            caracteristica.getNombre(), caracteristica.getPeso(), caracteristica.getImportancia());
                    this.listaValor.add(caracteristicaValor);
                }
            }
        }
        return resultado;
    }

    public void prepararEditCaracteristica() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest myRequest = (HttpServletRequest) context.getExternalContext().getRequest();
        String nombre = myRequest.getParameter("name");
        Iterator<Caracteristica> iter = this.listCaracteristica.iterator();
        while (iter.hasNext()) {
            caractSeleccionada = iter.next();
            if (caractSeleccionada.getNombre().equals(nombre)) {
                this.nombreCaract = caractSeleccionada.getNombre();
                this.changeImportancia(caractSeleccionada.getImportancia());
                break;
            }
        }
    }

    public void prepararEditAlternativa() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest myRequest = (HttpServletRequest) context.getExternalContext().getRequest();
        String nombre = myRequest.getParameter("name");
        Iterator<Alternativa> iter = this.listAlternativa.iterator();
        while (iter.hasNext()) {
            alternativaSeleccionada = iter.next();
            if (alternativaSeleccionada.getNombre().equals(nombre)) {
                this.nombreAlternativa = alternativaSeleccionada.getNombre();
                this.listaValor = alternativaSeleccionada.getListaValor();
                for (CaracteristicaValor caracteristicaValor : this.listaValor) {
                    this.changePuntaje(caracteristicaValor.getPuntaje(), caracteristicaValor);
                }
                break;
            }
        }
    }

    public void changeImportancia(int impo) {
        this.importancia = impo;
        switch (impo) {
            case 0: {
                this.imageStarA = "images/star_negro.png";
                this.imageStarB = "images/star_blanco.png";
                this.imageStarC = "images/star_blanco.png";
                this.imageStarD = "images/star_blanco.png";
                this.imageStarE = "images/star_blanco.png";
                break;
            }
            case 1: {
                this.imageStarA = "images/star_negro.png";
                this.imageStarB = "images/star_negro.png";
                this.imageStarC = "images/star_blanco.png";
                this.imageStarD = "images/star_blanco.png";
                this.imageStarE = "images/star_blanco.png";
                break;
            }
            case 2: {
                this.imageStarA = "images/star_negro.png";
                this.imageStarB = "images/star_negro.png";
                this.imageStarC = "images/star_negro.png";
                this.imageStarD = "images/star_blanco.png";
                this.imageStarE = "images/star_blanco.png";
                break;
            }
            case 3: {
                this.imageStarA = "images/star_negro.png";
                this.imageStarB = "images/star_negro.png";
                this.imageStarC = "images/star_negro.png";
                this.imageStarD = "images/star_negro.png";
                this.imageStarE = "images/star_blanco.png";
                break;
            }
            case 4: {
                this.imageStarA = "images/star_negro.png";
                this.imageStarB = "images/star_negro.png";
                this.imageStarC = "images/star_negro.png";
                this.imageStarD = "images/star_negro.png";
                this.imageStarE = "images/star_negro.png";
                break;
            }
        }
    }

    public void changePuntaje(int puntaje, CaracteristicaValor caracteristicaValor) {
        caracteristicaValor.setPuntaje(puntaje);
        switch (caracteristicaValor.getPuntaje()) {
            case 1: {//Regular
                caracteristicaValor.setImageHeartA("images/heart_negro.png");
                caracteristicaValor.setImageHeartB("images/heart_blanco.png");
                caracteristicaValor.setImageHeartC("images/heart_blanco.png");
                break;
            }
            case 3: {//Bueno
                caracteristicaValor.setImageHeartA("images/heart_blanco.png");
                caracteristicaValor.setImageHeartB("images/heart_negro.png");
                caracteristicaValor.setImageHeartC("images/heart_blanco.png");
                break;
            }
            case 5: {//Muy Bueno
                caracteristicaValor.setImageHeartA("images/heart_blanco.png");
                caracteristicaValor.setImageHeartB("images/heart_blanco.png");
                caracteristicaValor.setImageHeartC("images/heart_negro.png");
                break;
            }
        }
    }

    public String nuevoCaracteristica() {
        String pagina = "index";
        try {
            Double peso = this.calcularPesoIndividual();
            Caracteristica c = new Caracteristica(nombreCaract, peso, importancia);
            this.listCaracteristica.add(c);
            this.calcularPesoGlobal();
        } catch (Exception e) {
        }
        return pagina;
    }

    public String editCaracteristica() {
        String pagina = "index";
        try {
            Double peso = this.calcularPesoIndividual();
            caractSeleccionada.setNombre(nombreCaract);
            caractSeleccionada.setPeso(peso);
            caractSeleccionada.setImportancia(importancia);
            this.calcularPesoGlobal();
        } catch (Exception e) {
        }
        return pagina;
    }

    public void borrarCaracteristica() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest myRequest = (HttpServletRequest) context.getExternalContext().getRequest();
        String nombre = myRequest.getParameter("name");
        Iterator<Caracteristica> iter = this.listCaracteristica.iterator();
        Caracteristica caract;
        while (iter.hasNext()) {
            caract = iter.next();
            if (caract.getNombre().equals(nombre)) {
                this.listCaracteristica.remove(caract);
                break;
            }
        }
        this.calcularPesoGlobal();
    }

    public String nuevoAlternativa() {
        String pagina = "index";
        try {
            Alternativa a = new Alternativa(nombreAlternativa);
            a.setListaValor(listaValor);
            this.listAlternativa.add(a);
        } catch (Exception e) {
        }
        return pagina;
    }

    public String editAlternativa() {
        String pagina = "index";
        try {
            alternativaSeleccionada.setNombre(nombreAlternativa);
            alternativaSeleccionada.setListaValor(listaValor);
        } catch (Exception e) {
        }
        return pagina;
    }

    public void borrarAlternativa() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest myRequest = (HttpServletRequest) context.getExternalContext().getRequest();
        String nombre = myRequest.getParameter("name");
        Iterator<Alternativa> iter = this.listAlternativa.iterator();
        Alternativa alter;
        while (iter.hasNext()) {
            alter = iter.next();
            if (alter.getNombre().equals(nombre)) {
                this.listAlternativa.remove(alter);
                break;
            }
        }       
    }

    private Double calcularPesoIndividual() {
        Double peso;
        if (importancia == 1) {
            peso = 0.05;
        } else {
            if (importancia == 2) {
                peso = 0.1;
            } else {
                if (importancia == 3) {
                    peso = 0.15;
                } else {
                    if (importancia == 4) {
                        peso = 0.2;
                    } else {
                        peso = 0.01;
                    }
                }
            }
        }
        return peso;
    }

    public void calcularPesoGlobal() {
        Double pesoTotal = 0.0;
        int cantSinImpo = 0;
        int cantPocoImpo = 0;
        int cantImpo = 0;
        int cantMuyImpo = 0;
        int cantDemImpo = 0;
        for (Caracteristica caracteristica : listCaracteristica) {
            switch (caracteristica.getImportancia()) {
                case 0: {
                    cantSinImpo++;
                    pesoTotal = pesoTotal + 0.01;
                    break;
                }
                case 1: {
                    cantPocoImpo++;
                    pesoTotal = pesoTotal + 0.05;
                    break;
                }
                case 2: {
                    cantImpo++;
                    pesoTotal = pesoTotal + 0.1;
                    break;
                }
                case 3: {
                    cantMuyImpo++;
                    pesoTotal = pesoTotal + 0.15;
                    break;
                }
                case 4: {
                    cantDemImpo++;
                    pesoTotal = pesoTotal + 0.2;
                    break;
                }
            }
        }
        Double pesoDif = 1 - pesoTotal;
        Double pesoSinImpo = 0.0;
        Double pesoPocoImpo = 0.0;
        Double pesoImpo = 0.0;
        Double pesoMuyImpo = 0.0;
        Double pesoDemImpo = 0.0;
        if ((cantSinImpo != 0) && (cantPocoImpo != 0) && (cantImpo != 0) && (cantMuyImpo != 0) && (cantDemImpo != 0)) {
            pesoSinImpo = (pesoDif * 0.1) / cantSinImpo;
            pesoPocoImpo = (pesoDif * 0.15) / cantPocoImpo;
            pesoImpo = (pesoDif * 0.2) / cantImpo;
            pesoMuyImpo = (pesoDif * 0.25) / cantMuyImpo;
            pesoDemImpo = (pesoDif * 0.3) / cantDemImpo;
        } else {
            if (cantDemImpo == 0) {
                if ((cantSinImpo != 0) && (cantPocoImpo != 0) && (cantImpo != 0) && (cantMuyImpo != 0)) {
                    pesoSinImpo = (pesoDif * 0.1) / cantSinImpo;
                    pesoPocoImpo = (pesoDif * 0.2) / cantPocoImpo;
                    pesoImpo = (pesoDif * 0.3) / cantImpo;
                    pesoMuyImpo = (pesoDif * 0.4) / cantMuyImpo;
                }
            }
            if (cantMuyImpo == 0) {
                if ((cantSinImpo != 0) && (cantPocoImpo != 0) && (cantImpo != 0) && (cantDemImpo != 0)) {
                    pesoSinImpo = (pesoDif * 0.1) / cantSinImpo;
                    pesoPocoImpo = (pesoDif * 0.2) / cantPocoImpo;
                    pesoImpo = (pesoDif * 0.3) / cantImpo;
                    pesoDemImpo = (pesoDif * 0.4) / cantDemImpo;
                }
            }
            if (cantImpo == 0) {
                if ((cantSinImpo != 0) && (cantPocoImpo != 0) && (cantMuyImpo != 0) && (cantDemImpo != 0)) {
                    pesoSinImpo = (pesoDif * 0.1) / cantSinImpo;
                    pesoPocoImpo = (pesoDif * 0.2) / cantPocoImpo;
                    pesoMuyImpo = (pesoDif * 0.3) / cantMuyImpo;
                    pesoDemImpo = (pesoDif * 0.4) / cantDemImpo;
                }
            }
            if (cantPocoImpo == 0) {
                if ((cantSinImpo != 0) && (cantImpo != 0) && (cantMuyImpo != 0) && (cantDemImpo != 0)) {
                    pesoSinImpo = (pesoDif * 0.1) / cantSinImpo;
                    pesoImpo = (pesoDif * 0.2) / cantImpo;
                    pesoMuyImpo = (pesoDif * 0.3) / cantMuyImpo;
                    pesoDemImpo = (pesoDif * 0.4) / cantDemImpo;
                }
            }
            if (cantSinImpo == 0) {
                if ((cantPocoImpo != 0) && (cantImpo != 0) && (cantMuyImpo != 0) && (cantDemImpo != 0)) {
                    pesoPocoImpo = (pesoDif * 0.1) / cantPocoImpo;
                    pesoImpo = (pesoDif * 0.2) / cantImpo;
                    pesoMuyImpo = (pesoDif * 0.3) / cantMuyImpo;
                    pesoDemImpo = (pesoDif * 0.4) / cantDemImpo;
                }
            }
            if ((cantDemImpo == 0) && (cantMuyImpo == 0)) {
                if ((cantSinImpo != 0) && (cantPocoImpo != 0) && (cantImpo != 0)) {
                    pesoSinImpo = (pesoDif * 0.2) / cantSinImpo;
                    pesoPocoImpo = (pesoDif * 0.3) / cantPocoImpo;
                    pesoImpo = (pesoDif * 0.5) / cantImpo;
                }
            }
            if ((cantDemImpo == 0) && (cantImpo == 0)) {
                if ((cantSinImpo != 0) && (cantPocoImpo != 0) && (cantMuyImpo != 0)) {
                    pesoSinImpo = (pesoDif * 0.2) / cantSinImpo;
                    pesoPocoImpo = (pesoDif * 0.3) / cantPocoImpo;
                    pesoMuyImpo = (pesoDif * 0.5) / cantMuyImpo;
                }
            }
            if ((cantDemImpo == 0) && (cantPocoImpo == 0)) {
                if ((cantSinImpo != 0) && (cantImpo != 0) && (cantMuyImpo != 0)) {
                    pesoSinImpo = (pesoDif * 0.2) / cantSinImpo;
                    pesoImpo = (pesoDif * 0.3) / cantImpo;
                    pesoMuyImpo = (pesoDif * 0.5) / cantMuyImpo;
                }
            }
            if ((cantDemImpo == 0) && (cantSinImpo == 0)) {
                if ((cantPocoImpo != 0) && (cantImpo != 0) && (cantMuyImpo != 0)) {
                    pesoPocoImpo = (pesoDif * 0.2) / cantPocoImpo;
                    pesoImpo = (pesoDif * 0.3) / cantImpo;
                    pesoMuyImpo = (pesoDif * 0.5) / cantMuyImpo;
                }
            }
            if ((cantMuyImpo == 0) && (cantImpo == 0)) {
                if ((cantSinImpo != 0) && (cantPocoImpo != 0) && (cantDemImpo != 0)) {
                    pesoSinImpo = (pesoDif * 0.2) / cantSinImpo;
                    pesoPocoImpo = (pesoDif * 0.3) / cantPocoImpo;
                    pesoDemImpo = (pesoDif * 0.5) / cantDemImpo;
                }
            }
            if ((cantMuyImpo == 0) && (cantPocoImpo == 0)) {
                if ((cantSinImpo != 0) && (cantImpo != 0) && (cantDemImpo != 0)) {
                    pesoSinImpo = (pesoDif * 0.2) / cantSinImpo;
                    pesoImpo = (pesoDif * 0.3) / cantImpo;
                    pesoDemImpo = (pesoDif * 0.5) / cantDemImpo;
                }
            }
            if ((cantMuyImpo == 0) && (cantSinImpo == 0)) {
                if ((cantPocoImpo != 0) && (cantImpo != 0) && (cantDemImpo != 0)) {
                    pesoPocoImpo = (pesoDif * 0.2) / cantPocoImpo;
                    pesoImpo = (pesoDif * 0.3) / cantImpo;
                    pesoDemImpo = (pesoDif * 0.5) / cantDemImpo;
                }
            }
            if ((cantImpo == 0) && (cantPocoImpo == 0)) {
                if ((cantSinImpo != 0) && (cantMuyImpo != 0) && (cantDemImpo != 0)) {
                    pesoSinImpo = (pesoDif * 0.2) / cantSinImpo;
                    pesoMuyImpo = (pesoDif * 0.3) / cantMuyImpo;
                    pesoDemImpo = (pesoDif * 0.5) / cantDemImpo;
                }
            }

            if ((cantImpo == 0) && (cantSinImpo == 0)) {
                if ((cantPocoImpo != 0) && (cantMuyImpo != 0) && (cantDemImpo != 0)) {
                    pesoPocoImpo = (pesoDif * 0.2) / cantPocoImpo;
                    pesoMuyImpo = (pesoDif * 0.3) / cantMuyImpo;
                    pesoDemImpo = (pesoDif * 0.5) / cantDemImpo;
                }
            }

            if ((cantPocoImpo == 0) && (cantSinImpo == 0)) {
                if ((cantImpo != 0) && (cantMuyImpo != 0) && (cantDemImpo != 0)) {
                    pesoImpo = (pesoDif * 0.2) / cantImpo;
                    pesoMuyImpo = (pesoDif * 0.3) / cantMuyImpo;
                    pesoDemImpo = (pesoDif * 0.5) / cantDemImpo;
                }
            }
            if ((cantDemImpo == 0) && (cantMuyImpo == 0) && (cantImpo == 0)) {
                if ((cantSinImpo != 0) && (cantPocoImpo != 0)) {
                    pesoSinImpo = (pesoDif * 0.4) / cantSinImpo;
                    pesoPocoImpo = (pesoDif * 0.6) / cantPocoImpo;
                }
            }
            if ((cantDemImpo == 0) && (cantMuyImpo == 0) && (cantPocoImpo == 0)) {
                if ((cantSinImpo != 0) && (cantImpo != 0)) {
                    pesoSinImpo = (pesoDif * 0.4) / cantSinImpo;
                    pesoImpo = (pesoDif * 0.6) / cantImpo;
                }
            }
            if ((cantDemImpo == 0) && (cantMuyImpo == 0) && (cantSinImpo == 0)) {
                if ((cantPocoImpo != 0) && (cantImpo != 0)) {
                    pesoPocoImpo = (pesoDif * 0.4) / cantPocoImpo;
                    pesoImpo = (pesoDif * 0.6) / cantImpo;
                }
            }

            if ((cantDemImpo == 0) && (cantImpo == 0) && (cantPocoImpo == 0)) {
                if ((cantSinImpo != 0) && (cantMuyImpo != 0)) {
                    pesoSinImpo = (pesoDif * 0.4) / cantSinImpo;
                    pesoMuyImpo = (pesoDif * 0.6) / cantMuyImpo;
                }
            }

            if ((cantDemImpo == 0) && (cantImpo == 0) && (cantSinImpo == 0)) {
                if ((cantPocoImpo != 0) && (cantMuyImpo != 0)) {
                    pesoPocoImpo = (pesoDif * 0.4) / cantPocoImpo;
                    pesoMuyImpo = (pesoDif * 0.6) / cantMuyImpo;
                }
            }

            if ((cantDemImpo == 0) && (cantPocoImpo == 0) && (cantSinImpo == 0)) {
                if ((cantImpo != 0) && (cantMuyImpo != 0)) {
                    pesoImpo = (pesoDif * 0.4) / cantImpo;
                    pesoMuyImpo = (pesoDif * 0.6) / cantMuyImpo;
                }
            }

            if ((cantMuyImpo == 0) && (cantImpo == 0) && (cantPocoImpo == 0)) {
                if ((cantSinImpo != 0) && (cantDemImpo != 0)) {
                    pesoSinImpo = (pesoDif * 0.4) / cantSinImpo;
                    pesoDemImpo = (pesoDif * 0.6) / cantDemImpo;
                }
            }

            if ((cantMuyImpo == 0) && (cantImpo == 0) && (cantSinImpo == 0)) {
                if ((cantPocoImpo != 0) && (cantDemImpo != 0)) {
                    pesoPocoImpo = (pesoDif * 0.4) / cantPocoImpo;
                    pesoDemImpo = (pesoDif * 0.6) / cantDemImpo;
                }
            }
            if ((cantMuyImpo == 0) && (cantPocoImpo == 0) && (cantSinImpo == 0)) {
                if ((cantImpo != 0) && (cantDemImpo != 0)) {
                    pesoImpo = (pesoDif * 0.4) / cantImpo;
                    pesoDemImpo = (pesoDif * 0.6) / cantDemImpo;
                }
            }

            if ((cantImpo == 0) && (cantPocoImpo == 0) && (cantSinImpo == 0)) {
                if ((cantMuyImpo != 0) && (cantDemImpo != 0)) {
                    pesoMuyImpo = (pesoDif * 0.4) / cantMuyImpo;
                    pesoDemImpo = (pesoDif * 0.6) / cantDemImpo;
                }
            }
            if ((cantSinImpo != 0) && (cantPocoImpo == 0) && (cantImpo == 0) && (cantMuyImpo == 0) && (cantDemImpo == 0)) {
                pesoSinImpo = (pesoDif * 1) / cantSinImpo;
            } else {
                if ((cantSinImpo == 0) && (cantPocoImpo != 0) && (cantImpo == 0) && (cantMuyImpo == 0) && (cantDemImpo == 0)) {
                    pesoPocoImpo = (pesoDif * 1) / cantPocoImpo;
                } else {
                    if ((cantSinImpo == 0) && (cantPocoImpo == 0) && (cantImpo != 0) && (cantMuyImpo == 0) && (cantDemImpo == 0)) {
                        pesoImpo = (pesoDif * 1) / cantImpo;
                    } else {
                        if ((cantSinImpo == 0) && (cantPocoImpo == 0) && (cantImpo == 0) && (cantMuyImpo != 0) && (cantDemImpo == 0)) {
                            pesoMuyImpo = (pesoDif * 1) / cantMuyImpo;
                        } else {
                            if ((cantSinImpo == 0) && (cantPocoImpo == 0) && (cantImpo == 0) && (cantMuyImpo == 0) && (cantDemImpo != 0)) {
                                pesoDemImpo = (pesoDif * 1) / cantDemImpo;
                            }
                        }
                    }
                }
            }
        }
        Iterator<Caracteristica> iter = this.listCaracteristica.iterator();
        Caracteristica caracteristica;

        while (iter.hasNext()) {
            caracteristica = iter.next();
            switch (caracteristica.getImportancia()) {
                case 0: {
                    caracteristica.setPeso(0.01 + pesoSinImpo);
                    break;
                }
                case 1: {
                    caracteristica.setPeso(0.05 + pesoPocoImpo);
                    break;
                }
                case 2: {
                    caracteristica.setPeso(0.1 + pesoImpo);
                    break;
                }
                case 3: {
                    caracteristica.setPeso(0.15 + pesoMuyImpo);
                    break;
                }
                case 4: {
                    caracteristica.setPeso(0.2 + pesoDemImpo);
                    break;
                }
            }
        }
    }

    public void limpiar() {
        polaridad = 2;
        this.imageStarA = "images/star_blanco.png";
        this.imageStarB = "images/star_blanco.png";
        this.imageStarC = "images/star_blanco.png";
        this.imageStarD = "images/star_blanco.png";
        this.imageStarE = "images/star_blanco.png";
        this.importancia = 0;
        this.nombreModelo = "";
        this.nombreCaract = "";
        this.nombreAlternativa = "";
        this.mensajePopup = "";
        this.opened = false;
        this.listCaracteristica = new ArrayList<>();
        this.caractSeleccionada = null;
        this.listAlternativa = new ArrayList<>();
        this.alternativaSeleccionada = null;
        this.listaValor = new ArrayList<>();
    }

    public void calcular() {
        if (this.listAlternativa.size() < 2) {
            this.mensajePopup = "Es necesario ingresar primero al menos dos alternativas.";
            this.opened = true;
        } else {
            CalculatorLSP calculatorLSP = new CalculatorLSP(polaridad);
            for (Alternativa alternativa : listAlternativa) {
                alternativa.setIG(calculatorLSP.calcularLSP(alternativa.getListaValor()));
                System.out.println(alternativa.getNombre() + " RESULTADO " + alternativa.getIG());
            }
            this.mensajePopup = "";
            this.opened = false;
        }
    }
}
