/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomyling.facturacion.validador;

import java.util.ArrayList;
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
@FacesValidator("cedulaRuc1")
public class pruebaCedRuc implements Validator
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
            //el value es de tipo Objeto lo casting a String
            String ingresaValue=(String) value;
            //la longitud del String ingresaValue
            int longitud=ingresaValue.length();
           // int long1=longitud-1;
            //Creamos el vector de tipo integer coun una longitud=longitud
            Integer vector[]=new Integer[longitud];
            //Llenamos el vector con ingresaValue       
            int k=1;        
            for(int j=0;j < longitud;j++)
            {           
               vector[j]=Integer.parseInt(ingresaValue.substring(j,k)); 
               k++;
            }
            int cont=vector.length;
            if(ingresaValue==null)
            {
                FacesMessage msj=new FacesMessage();
                msj.setSeverity(FacesMessage.SEVERITY_ERROR);
                msj.setSummary(label + "Es nula");

                throw new ValidatorException(msj);
            } 
            else
            {
                //si valor ingresado no s 10 o 13 digitos
                if(ingresaValue.length()!=10 && ingresaValue.length()!=13 )
                {
                   FacesMessage msjlong=new FacesMessage();
                   msjlong.setSeverity(FacesMessage.SEVERITY_ERROR);
                   msjlong.setSummary(label + " No es de 10/13 digitos");

                  throw new ValidatorException(msjlong); 
                }
                else
                {
                        int prov = Integer.parseInt(ingresaValue.substring(0,2)); 

                        if(prov>=1 && prov<24)
                        {                       
                            if(ingresaValue.length()==10)
                            {
                                //suma pares siempre el coeficiente es 1
                                int sumaimpar=0;
                                int h;
                                
                                for(h=0;h<vector.length-1 ;h++)  
                                {
                                  h=h+1;
                                  if(h!=9)
                                  {                               
//                                      if(vector[h] != 9)
//                                      {
                                          sumaimpar=sumaimpar+vector[h];
//                                      }

                                  } 
                                }
                                int sumapar=0;
                                int pordos=0;
                                int resta=0;
                                for(int l=0;l<vector.length-1;l++)
                                {
                                    pordos=vector[l]* 2;
                                    if(pordos >= 9)
                                    {
                                        resta=pordos-9;
                                        sumapar=sumapar+resta;
                                    }
                                    else
                                    {
                                        sumapar=sumapar+pordos;  
                                    }
                                    l=l+1;
                                } 
                                int sumaparimpar=sumapar+sumaimpar;
                                int digVerif=Integer.parseInt(ingresaValue.substring(9,10));
                                int resulDecena;
                                int decena=10;
                                int residuo=sumaparimpar % 10;

                                if(residuo > 0)
                                {
                                    resulDecena=decena-residuo;
                                    if(resulDecena == digVerif)
                                    {
                                        FacesMessage verifsi=new FacesMessage();
                                        verifsi.setSeverity(FacesMessage.SEVERITY_INFO);
                                        verifsi.setSummary(label + " ES CORRECTA.."  );

                                        FacesContext.getCurrentInstance().addMessage("men", verifsi);
                                      //   throw new ValidatorException(verifsi);
                                    }
                                    else
                                    {
                                        FacesMessage verifno=new FacesMessage();
                                        verifno.setSeverity(FacesMessage.SEVERITY_ERROR);
                                        verifno.setSummary("Cédula/Ruc ES INCORRECTO..");

                                       throw new ValidatorException(verifno); 
                                    }
                                } 
                                else
                                {
                                    resulDecena=0;
                                }                         
                            }
                            else
                            {
                                FacesMessage msj13=new FacesMessage();
                                msj13.setSeverity(FacesMessage.SEVERITY_INFO);
                                msj13.setSummary(label + " digitos 13");

                            //    throw new ValidatorException(msj13);

                            }
                        }
                        else
                        {
                            FacesMessage msjnopro = new FacesMessage();
                            msjnopro.setSeverity(FacesMessage.SEVERITY_ERROR);
                            msjnopro.setSummary("No existe código de PROVINCIA "+prov);

                            throw new ValidatorException(msjnopro);
                        }

                }    
            }    
        }
        else
        {
            FacesMessage msjnonum = new FacesMessage();
            msjnonum.setSeverity(FacesMessage.SEVERITY_ERROR);
            msjnonum.setSummary(label +" "+"NO es Numérica.." );
            
            throw new ValidatorException(msjnonum);
        }
            
    } 
    
}
