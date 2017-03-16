/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomyling.facturacion.validador;

/**
 *
 * @author EDWIN VACA
 */


import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;


/**
 *
 * @author EDWIN VACA
 */
@FacesValidator("cedulaRuc")
public class validaComponentes implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String label;
        HtmlInputText htmlInputText = (HtmlInputText) component;
        label = htmlInputText.getLabel();
        //*******************************************************
        
        Pattern pat = Pattern.compile("[0-9]+");
        Matcher mat = pat.matcher((CharSequence) value);

        if (mat.matches()) {

            String longitudstr = (String) value;
            int longitud = longitudstr.length();
            if (longitud == 10) {
                /*FacesMessage msjlong= new FacesMessage();
                 msjlong.setSeverity(FacesMessage.SEVERITY_ERROR);
                 msjlong.setSummary(label+" Tiene "+longitud+" Digitos");
                
                 FacesContext.getCurrentInstance().addMessage(label, msjlong);*/
                int j = 1;
                String mensajes = " ";
                String vector[] = new String[10];
                for (int i = 0; i < longitud; i++) {
                    vector[i] = longitudstr.substring(i, j);
                    mensajes = mensajes + vector[i];
                    j++;
                }
                /*FacesMessage msj2=new FacesMessage();
                 msj2.setSeverity(FacesMessage.SEVERITY_INFO);
                 msj2.setSummary("cedula es --> "+mensajes);
                   
                 FacesContext.getCurrentInstance().addMessage("mm", msj2); */
                //******************************************************
                //Suma pares
                int sumapares = 0;

                for (int i = 0; i < vector.length; i++) {
                    i = i + 1;
                    if (i != 9) {
                        sumapares = sumapares + (Integer.parseInt(vector[i]));
                    }
                }
                //Suma Impares *****************************************
                int sumaimpares = 0;
                int pordos, resta;
                for (int i = 0; i < vector.length; i++) {
                    pordos = Integer.parseInt(vector[i]) * 2;
                    if (pordos >= 9) {
                        resta = pordos - 9;
                        sumaimpares = sumaimpares + resta;
                    } else {
                        sumaimpares = sumaimpares + pordos;
                    }
                    i = i + 1;
                }
                //Suma sumapares+sumaimpares *******************************
                int sumaparimpar;
                sumaparimpar = sumapares + sumaimpares;

                //Calculo decena Superior **********************************
                String strsumaparimpar = null;
                String numdec = null;
                int decena = 0;
                int decenasuperior = 0;
                int digitoverif = 0;
                String ultimo = null;
                int ultimoverifica = 0;
                //****************************************************
                ultimo = longitudstr.substring(9, 10);
                ultimoverifica = Integer.parseInt(ultimo);

                if (sumaparimpar % 10 == 0) //Divide para 10
                {
                    if (ultimoverifica == digitoverif) {
                        FacesMessage mensajecero = new FacesMessage();
                        mensajecero.setSeverity(FacesMessage.SEVERITY_INFO);
                        mensajecero.setSummary("La " + label + " " + value + " ES VALIDA");

                        FacesContext.getCurrentInstance().addMessage("men", mensajecero);

                    }
                } else {
                    strsumaparimpar = Integer.toString(sumaparimpar);
                    numdec = strsumaparimpar.substring(0, 1);
                    decena = Integer.parseInt(numdec);
                    decenasuperior = (decena * 10) + 10;
                    digitoverif = decenasuperior - sumaparimpar;

                    /* ultimo=longitudstr.substring(9,10);
                     ultimoverifica=Integer.parseInt(ultimo);*/
                    if (ultimoverifica == digitoverif) {
                        FacesMessage mensajesi = new FacesMessage();
                        mensajesi.setSeverity(FacesMessage.SEVERITY_INFO);
                        mensajesi.setSummary("La " + label + " " + value + " Es VALIDA ..");

                        FacesContext.getCurrentInstance().addMessage("men", mensajesi);
                    } else {
                        FacesMessage mensajeno = new FacesMessage();
                        mensajeno.setSeverity(FacesMessage.SEVERITY_INFO);
                        mensajeno.setSummary("La " + label + " " + value + " No es VALIDA..");

                        throw new ValidatorException(mensajeno);
                    }

                }

                /* FacesMessage msj3=new FacesMessage();
                 msj3.setSeverity(FacesMessage.SEVERITY_INFO);
                 msj3.setSummary("La suma par es --> "+sumapares);

                 FacesContext.getCurrentInstance().addMessage("men", msj3);
                    
                    
                 FacesMessage msj4=new FacesMessage();
                 msj4.setSeverity(FacesMessage.SEVERITY_INFO);
                 msj4.setSummary("La suma impar es --> "+sumaimpares);
                    
                 FacesContext.getCurrentInstance().addMessage("men", msj4);
                    
                 FacesMessage msj5=new FacesMessage();
                 msj5.setSeverity(FacesMessage.SEVERITY_INFO);
                 msj5.setSummary("La suma par mas impar es --> "+sumaparimpar);
                    
                    
                    
                 FacesContext.getCurrentInstance().addMessage("men", msj5);
                    
                 FacesMessage msj6=new FacesMessage();
                 msj6.setSeverity(FacesMessage.SEVERITY_INFO);
                 msj6.setSummary("La decena superior es --> "+decenasuperior);
                    
                 FacesContext.getCurrentInstance().addMessage("men", msj6) ;
                    
                 FacesMessage msj7=new FacesMessage();
                 msj7.setSeverity(FacesMessage.SEVERITY_INFO);
                 msj7.setSummary("Digito verificador es --> "+digitoverif);
                  
                 FacesContext.getCurrentInstance().addMessage("men", msj7);
                    
                                       
                 FacesMessage msj8=new FacesMessage();
                 msj8.setSeverity(FacesMessage.SEVERITY_INFO);
                 msj8.setSummary("Digito Ultimo --> "+ultimo);
                  
                 FacesContext.getCurrentInstance().addMessage("men", msj8);
                    
                 FacesMessage msj9=new FacesMessage();
                 msj9.setSeverity(FacesMessage.SEVERITY_INFO);
                 msj9.setSummary("Digito Ultimo debe ser integer--> "+ultimoverifica);
                  
                 FacesContext.getCurrentInstance().addMessage("men", msj9); */
            } else {
                FacesMessage msjlong = new FacesMessage();
                msjlong.setSeverity(FacesMessage.SEVERITY_ERROR);
                msjlong.setSummary(label + " Debe tener 10 digitos no " + longitud + " digitos..");

                throw new ValidatorException(msjlong);
            }

        } else {
            FacesMessage msj = new FacesMessage();
            msj.setSeverity(FacesMessage.SEVERITY_INFO);
            msj.setSummary(label + " Deber ser numerica de 10 digitos...");

            throw new ValidatorException(msj);
        }
    }
}

