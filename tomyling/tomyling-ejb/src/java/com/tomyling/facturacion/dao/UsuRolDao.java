/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomyling.facturacion.dao;

import com.tomyling.facturacion.generico.Generico;
import com.tomyling.facturacion.modelo.UsuRol;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author new user
 */
public class UsuRolDao extends Generico<UsuRol> {

    public UsuRolDao() {
        super(UsuRol.class);
    }

    public UsuRol obtenerUsuPorRol(Integer idUsuario) {
        List<UsuRol> listaUsuRol;
        Query query;
        String jqpl = "SELECT u FROM UsuRol u WHERE u.usuRolPK.idUsuario = :idUsuario ";
        query = getEntityManager().createQuery(jqpl).setParameter("idUsuario", idUsuario);
        listaUsuRol = query.getResultList();
        if (listaUsuRol == null || listaUsuRol.isEmpty()) {
            return null;
        } else {
            return listaUsuRol.get(0);
        }

    }
}
