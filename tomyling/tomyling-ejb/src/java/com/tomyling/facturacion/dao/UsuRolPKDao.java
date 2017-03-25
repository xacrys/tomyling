/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomyling.facturacion.dao;

import com.tomyling.facturacion.generico.Generico;
import com.tomyling.facturacion.modelo.UsuRolPK;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author EDWIN VACA
 */
public class UsuRolPKDao extends Generico<UsuRolPK>
{

    public UsuRolPKDao(Class<UsuRolPK> entityClass) {
        super(entityClass);
    }
    
    public List<UsuRolPK> listRolPKId(Integer IdUsuario)
    {
        List<UsuRolPK> lstUsu=new ArrayList<>();
        Query query;
        String jpql="SELECT ur FROM UsuRolPK  rm WHERE rm.idUsuario = :paramA";
        query=getEntityManager().createQuery(jpql);
        query.setParameter("paramA", IdUsuario);
        lstUsu=query.getResultList();
        if(lstUsu==null || lstUsu.isEmpty() )
        {
           return null;
        }
        else
        {
            return lstUsu;
        }
            
    }        
}
