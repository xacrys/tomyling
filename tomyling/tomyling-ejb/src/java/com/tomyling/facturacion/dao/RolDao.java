/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomyling.facturacion.dao;


import com.tomyling.facturacion.generico.Generico;
import com.tomyling.facturacion.modelo.Rol;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author new user
 */
public class RolDao extends Generico<Rol>{

    

    public RolDao() {
        super(Rol.class);
    }
    
    public List<Rol> cargaTodosRol()
    {
        List<Rol> lstRol;
        Query query;
        String jpql="SELECT r FROM Rol r";
        query=getEntityManager().createQuery(jpql);
        lstRol=query.getResultList();
        if(lstRol==null || lstRol.isEmpty())        
        {
            return null;
        }
        else
        {
            return lstRol;
        }
    }        
}
