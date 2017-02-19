/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tomyling.facturacion.dao;

import javax.ejb.Stateless;
import tomyling.facturacion.generico.Generico;
import tomyling.facturacion.modelo.Clientes;

/**
 *
 * @author new user
 */

public class ClientesDao extends Generico<Clientes> {

   

    public ClientesDao() {
        super(Clientes.class);
    }
    
}
