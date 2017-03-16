/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomyling.facturacion.dao;

import com.tomyling.facturacion.generico.Generico;

import com.tomyling.facturacion.modelo.Factura;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author new user
 */
public class FacturaDao extends Generico<Factura> {

    public FacturaDao() {
        super(Factura.class);
    }

    public Boolean validarFacturaExiste(String claveAcceso) {

        List<Factura> fac;
        Query query;
        String jsql = "SELECT f FROM Factura f WHERE f.claveAcceso = :claveAcceso";
        query = getEntityManager().createQuery(jsql).setParameter("claveAcceso", claveAcceso);
        fac = query.getResultList();
        if (fac == null || fac.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
    
     public Factura consultarFacturaPorClave(String claveAcceso) {

        Factura fac;
        Query query;
        String jsql = "SELECT f FROM Factura f WHERE f.claveAcceso = :claveAcceso";
        query = getEntityManager().createQuery(jsql).setParameter("claveAcceso", claveAcceso);
        fac = (Factura)query.getSingleResult();
        if (fac == null ) {
            return null;
        } else {
            return fac;
        }
    }
}
