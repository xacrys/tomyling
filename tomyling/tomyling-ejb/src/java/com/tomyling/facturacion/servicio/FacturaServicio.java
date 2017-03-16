/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomyling.facturacion.servicio;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import com.tomyling.facturacion.dao.FacturaDao;
import com.tomyling.facturacion.modelo.Factura;

/**
 *
 * @author new user
 */
@LocalBean
@Stateless
public class FacturaServicio extends FacturaDao {

    public Boolean facturaExiste(String clave) {
        Boolean flag = validarFacturaExiste(clave);
        return flag;
    }
    
    public void guardarFactura(Factura factura){
        try {
            this.create(factura);
        } catch (Exception e) {
            System.out.println("Error al guardar factura en facturaServicio");
        }
    }
    
    public Factura consultarFactura(String clave)
    {
        Factura fac = consultarFacturaPorClave(clave);
        return fac;
    
    }

}
