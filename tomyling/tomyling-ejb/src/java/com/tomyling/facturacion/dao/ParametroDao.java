/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomyling.facturacion.dao;

import com.tomyling.facturacion.generico.Generico;
import com.tomyling.facturacion.modelo.Parametros;
import javax.persistence.Query;

/**
 *
 * @author new user
 */
public class ParametroDao extends Generico<Parametros> {

    public ParametroDao() {
        super(Parametros.class);
    }

    public Parametros actualizarParametro() {
        Parametros parametro;
        Query query;
        String jpql = "SELECT p FROM Parametros p WHERE p.idParametro = :idParametro";
        query = getEntityManager().createQuery(jpql).setParameter("idParametro", 1);
        parametro = (Parametros) query.getSingleResult();
        if (parametro == null) {
            return null;
        } else {
            return parametro;
    }

    }
}
