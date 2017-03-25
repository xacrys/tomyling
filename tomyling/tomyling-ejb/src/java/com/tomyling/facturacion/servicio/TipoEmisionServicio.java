/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomyling.facturacion.servicio;


import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import com.tomyling.facturacion.dao.TipoEmisionDao;
import com.tomyling.facturacion.modelo.TipoEmision;


/**
 *
 * @author new user
 */

@LocalBean
@Stateless
public class TipoEmisionServicio extends TipoEmisionDao
{
   public TipoEmision ingresaTipEmi(Integer tipoEmite)
   {
       TipoEmision tipoEmision;
       tipoEmision=this.regresaTipEmi(tipoEmite);
       if(tipoEmision==null)
       {
            return null; 
       }
       else
       {
           return tipoEmision;
       }          
   }
}
