/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomyling.facturacion.dao;


import com.tomyling.facturacion.generico.Generico;
import com.tomyling.facturacion.modelo.TipoEmision;
import java.util.List;
import javax.persistence.Query;


/**
 *
 * @author new user
 */

public class TipoEmisionDao extends Generico<TipoEmision> {

   
    public TipoEmisionDao() {
        super(TipoEmision.class);
    }
    
    public TipoEmision regresaTipEmi(Integer tipEmi)
    {
        List<TipoEmision> lte;
        Query query;
        String jpql="SELECT te FROM TipoEmision te WHERE te.codEmision = :paramA";
        query=getEntityManager().createQuery(jpql).setParameter("paramA",tipEmi ); 
        lte=query.getResultList();
        if(lte==null || lte.isEmpty())
        {
             return null;
        }    
        else
        {
            return lte.get(0);
        }    
        
    }      
}
