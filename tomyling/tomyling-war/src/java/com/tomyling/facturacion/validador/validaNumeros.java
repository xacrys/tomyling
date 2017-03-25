/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomyling.facturacion.validador;

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
@FacesValidator("numeros")
public class validaNumeros implements Validator
{

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException
    {
        String label;
        HtmlInputText htmlInputText = (HtmlInputText) component;
        label = htmlInputText.getLabel();
        Pattern pat = Pattern.compile("[0-9]+");
        Matcher mat = pat.matcher((CharSequence) value);        
        //||
        
        if (mat.matches())
        {
            int numero=Integer.parseInt((String) value);
            if(numero>=1 && numero<=9)
            {
               
            } 	
            else
            {
                FacesMessage msjno=new FacesMessage();
                msjno.setSeverity(FacesMessage.SEVERITY_ERROR);
                msjno.setSummary(label +"Rango de 1-9");
                
                throw new ValidatorException(msjno);
            }    
        }  
        else
        {
            FacesMessage msjnonum=new FacesMessage();
             msjnonum.setSeverity(FacesMessage.SEVERITY_ERROR);
             msjnonum.setSummary(label + " "+"No es numérico" );
            throw new ValidatorException(msjnonum); 
             
        }               
    }
}
