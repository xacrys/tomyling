/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomyling.facturacion.dao;

import com.tomyling.facturacion.generico.Generico;

import com.tomyling.facturacion.modelo.Factura;
import java.util.Date;
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
        fac = (Factura) query.getSingleResult();
        if (fac == null) {
            return null;
        } else {
            return fac;
        }
    }

    public List<Factura> consultaTodasFacturas() {
        List<Factura> listafac;
        Query query;
        String jsql = "SELECT f FROM Factura f";
        query = getEntityManager().createQuery(jsql);
        listafac = query.getResultList();
        if (listafac.size() != 0 && listafac != null) {
            return listafac;
        } else {
            return null;
        }
    }

    public List<Factura> consultaFacturasPorDatos(String cedRuc, String razonSocial, Date fechaEmision) {
        List<Factura> listafac;
        Query query;
        Boolean flagAnd=true;
        String jsql = "SELECT f FROM Factura f WHERE";
        if (!cedRuc.isEmpty()) {
            jsql += " f.cedRuc = :cedRuc AND";
        }
        if (!razonSocial.isEmpty()) {
            jsql += " f.razonSocial like :razonSocial AND";
        }
        if (fechaEmision!=null) {
            jsql += " f.fechaEmision = :fechaEmision ";
            flagAnd=false;
        }
        if(flagAnd)
        {
            jsql = jsql.substring(0,jsql.length()-3);
        }
        query = getEntityManager().createQuery(jsql);
        if (!cedRuc.isEmpty()) {
            query.setParameter("cedRuc", cedRuc);
        }
        if (!razonSocial.isEmpty()) {
            query.setParameter("razonSocial", "%"+razonSocial.toUpperCase()+"%");
        }
        if (fechaEmision!=null) {
            query.setParameter("fechaEmision", fechaEmision);
        }
        listafac = query.getResultList();
        if (!listafac.isEmpty() && listafac != null) {
            return listafac;
        } else {
            return null;
        }
    }
    
    public Factura consultaFcaturaxId(Integer facturar)
    {
        Query query;
        String jpql="SELECT f FROM Factura f WHERE f.idFactura = :paramA";
        query=getEntityManager().createQuery(jpql).setParameter("paramA", facturar);
        if(query == null)
        {
             return null;
        }    
        else
        {
            return (Factura) query.getSingleResult();
        }
        
    }       
}
