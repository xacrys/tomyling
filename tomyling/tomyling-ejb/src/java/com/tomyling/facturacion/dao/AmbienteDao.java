/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomyling.facturacion.dao;


import com.tomyling.facturacion.generico.Generico;
import com.tomyling.facturacion.modelo.Ambiente;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author new user
 */

public class AmbienteDao extends Generico<Ambiente>
{

    public AmbienteDao() {
        super(Ambiente.class);
    }
    //  ||
    public List<Ambiente> DescripAmbiente(Integer codigo)
    {
        List<Ambiente> lstAmbiente;
        Query query;
        String jpql="SELECT a FROM Ambiente a WHERE a.codAmbiente=:paramA";
        query=getEntityManager().createQuery(jpql).setParameter("paramA",codigo);
        lstAmbiente=query.getResultList();
        if(lstAmbiente==null || lstAmbiente.isEmpty())
        {
            return null;
        }
        else
        {
            return lstAmbiente;
        }        
    }  
    
    public Ambiente devuelveAmbiente(Integer codigo) 
    {      
        Query query;
        String jpql="SELECT a FROM Ambiente a WHERE a.codAmbiente=:paramA";
        query=getEntityManager().createQuery(jpql).setParameter("paramA",codigo);
        if(query==null)
        {
            return null;
        }
        else
        {
            return (Ambiente) query.getSingleResult();
        }      
    }
}
