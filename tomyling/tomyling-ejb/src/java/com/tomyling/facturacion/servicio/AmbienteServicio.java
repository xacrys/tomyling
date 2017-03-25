/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomyling.facturacion.servicio;



import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import com.tomyling.facturacion.dao.AmbienteDao;
import com.tomyling.facturacion.modelo.Ambiente;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author new user
 */
@LocalBean
@Stateless
public class AmbienteServicio extends AmbienteDao
{
    // ||
    
     public List<Ambiente> listaDescripAmbiente(Integer cod)
    {
         List<Ambiente> lstAmb=new ArrayList<>();
         lstAmb=this.DescripAmbiente(cod);
        if(lstAmb==null || lstAmb.isEmpty())
        {
             return null;
        } 
        else
        {
            return lstAmb;
        }    
    }

     public Ambiente retornaCodigo(Integer cod) 
     {
         Ambiente codAmb=this.devuelveAmbiente(cod);
         if(codAmb==null)
         {
             return null;
         }
         else
         {
              return codAmb;
         }        
     }  
}
