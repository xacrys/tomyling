/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomyling.facturacion.dao;

import com.tomyling.facturacion.generico.Generico;
import com.tomyling.facturacion.modelo.Institucion;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author new user
 */
public class InstitucionDao extends Generico<Institucion> {

    public InstitucionDao() {
        super(Institucion.class);
    }

    public List<Institucion> listarTodasInstituciones() {
        List<Institucion> listaIns = new ArrayList<>();
        Query query;
        String jpql = "SELECT i FROM Institucion i";
        query = getEntityManager().createQuery(jpql);
        listaIns = query.getResultList();
        if (listaIns == null || listaIns.isEmpty()) {
            return null;
        } else {
            return listaIns;
        }

    }

}
