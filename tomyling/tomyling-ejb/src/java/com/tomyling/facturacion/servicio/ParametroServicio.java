/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomyling.facturacion.servicio;

import com.tomyling.facturacion.dao.ParametroDao;
import com.tomyling.facturacion.modelo.Parametros;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author new user
 */
@LocalBean
@Stateless
public class ParametroServicio extends ParametroDao {

    public Parametros actualizarParametros() {
        Parametros parametro;
        parametro = this.actualizarParametro();
        if (parametro == null) {
            return null;
        } else {
            return parametro;
        }
    }
}


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
