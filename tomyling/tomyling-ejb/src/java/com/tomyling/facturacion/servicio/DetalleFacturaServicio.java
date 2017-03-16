/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomyling.facturacion.servicio;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import com.tomyling.facturacion.dao.DetalleFacturaDao;
import com.tomyling.facturacion.modelo.DetalleFactura;
import java.util.List;

/**
 *
 * @author new user
 */
@LocalBean
@Stateless
public class DetalleFacturaServicio extends DetalleFacturaDao{

    public void guardarDetalles(Integer idFactura, List<DetalleFactura> listaDetalle) {
        for (DetalleFactura df : listaDetalle) {
            df.setIdFactura(idFactura);
            this.create(df);

        }
    }
}
