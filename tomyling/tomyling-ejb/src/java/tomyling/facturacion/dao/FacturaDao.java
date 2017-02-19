/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tomyling.facturacion.dao;


import tomyling.facturacion.generico.Generico;

import tomyling.facturacion.modelo.Factura;

/**
 *
 * @author new user
 */

public class FacturaDao extends Generico<Factura> {


    public FacturaDao() {
        super(Factura.class);
    }
    
}
