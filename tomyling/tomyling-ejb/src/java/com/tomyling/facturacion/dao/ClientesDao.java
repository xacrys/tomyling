/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomyling.facturacion.dao;

import javax.ejb.Stateless;
import com.tomyling.facturacion.generico.Generico;
import com.tomyling.facturacion.modelo.Clientes;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author new user
 */

public class ClientesDao extends Generico<Clientes> {

   

    public ClientesDao() {
        super(Clientes.class);
    }
    
    public List<Clientes> listaTodosClientes()
    {
        List<Clientes> lstCli=new ArrayList<>();
        Query query;
        String jpql="SELECT c FROM Clientes c";
        query= getEntityManager().createQuery(jpql);
        lstCli=query.getResultList();
        if(lstCli == null || lstCli.isEmpty())
        {
            return null;  
        } 
        else
        {
            return lstCli;
        }    
        
    }      
    
}
