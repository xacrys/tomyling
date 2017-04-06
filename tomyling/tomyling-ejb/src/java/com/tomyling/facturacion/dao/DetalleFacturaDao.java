/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomyling.facturacion.dao;


import com.tomyling.facturacion.generico.Generico;
import com.tomyling.facturacion.modelo.DetalleFactura;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author new user
 */

public class DetalleFacturaDao extends Generico<DetalleFactura>{

    public DetalleFacturaDao() {
        super(DetalleFactura.class);
    }
    //  ||
    public List<DetalleFactura> consultaPorID(Integer detFac)
    {
        List<DetalleFactura> lstDetFac;
        Query query;
        String jpql="SELECT df FROM DetalleFactura df WHERE df.idFactura = :paramA";
        query=getEntityManager().createQuery(jpql).setParameter("paramA",detFac);
        lstDetFac=query.getResultList();
        
        if(lstDetFac==null || lstDetFac.isEmpty())
        {
            return null;
        }
        else
        {
            return lstDetFac; 
        }    
    }    
}
