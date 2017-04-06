/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomyling.facturacion.dao;


import com.tomyling.facturacion.generico.Generico;
import com.tomyling.facturacion.modelo.Impuesto;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author new user
 */

public class ImpuestoDao extends Generico<Impuesto>{

    

    public ImpuestoDao() {
        super(Impuesto.class);
    }
    
    public Impuesto cargaImpuesto(Integer codImp)
    {      
        Query query;
        String jpql="SELECT i FROM Impuesto i WHERE i.idFactura = :paramA";
        query=getEntityManager().createQuery(jpql).setParameter("paramA", codImp);
        if(query==null)
        {
            return null;
        }
        else
        {
            return (Impuesto) query.getSingleResult();
        }      
    }
    
     public List<Impuesto> cargaListaImpuesto(Integer idFactura)
    {   
        List<Impuesto> listImp;
        Query query;
        String jpql="SELECT i FROM Impuesto i WHERE i.idFactura = :paramA";
        query=getEntityManager().createQuery(jpql).setParameter("paramA", idFactura);
        listImp = query.getResultList();
        if(listImp==null || listImp.isEmpty())
        {
            return null;
        }
        else
        {
            return listImp;
        }      
    }
    
    
}
