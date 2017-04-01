/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomyling.facturacion.dao;


import com.tomyling.facturacion.generico.Generico;
import com.tomyling.facturacion.modelo.TipoImpuesto;
import javax.persistence.Query;

/**
 *
 * @author new user
 */

public class TipoImpuestoDao extends Generico<TipoImpuesto> {

   

    public TipoImpuestoDao() {
        super(TipoImpuesto.class);
    }
    
    public TipoImpuesto recogeTipoImpuesto(Integer tipImp)  
    {
        Query query;
        String jpql="SELECT ti FROM TipoImpuesto ti WHERE ti.codImpuesto = :paramA";
        query=getEntityManager().createQuery(jpql).setParameter("paramA", tipImp); 
        if(query==null)
        {
            return null;
        }
        else
        {
            return (TipoImpuesto) query.getSingleResult();
        }     
    }
}
