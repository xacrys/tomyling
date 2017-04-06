/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomyling.facturacion.servicio;


import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import com.tomyling.facturacion.dao.TipoImpuestoDao;
import com.tomyling.facturacion.modelo.TipoImpuesto;


/**
 *
 * @author new user
 */

@LocalBean
@Stateless
public class TipoImpuestoServicio extends TipoImpuestoDao
{
    public TipoImpuesto traeCodImpuesto(Integer codigoImpuesto) 
    {
        TipoImpuesto tipoCodImp;
        tipoCodImp=this.recogeTipoImpuesto(codigoImpuesto);  
        if(tipoCodImp==null)
        {
            return null;
        }
        else
        {
            return tipoCodImp;
        }         
    }

   
}