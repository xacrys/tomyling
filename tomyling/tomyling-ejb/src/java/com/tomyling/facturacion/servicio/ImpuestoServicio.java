/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomyling.facturacion.servicio;


import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import com.tomyling.facturacion.dao.ImpuestoDao;
import com.tomyling.facturacion.modelo.Impuesto;
import java.math.BigInteger;
import java.util.List;



/**
 *
 * @author new user
 */

@LocalBean
@Stateless
public class ImpuestoServicio extends ImpuestoDao{

    
    public void guardarImpuestos(Integer idFactura, List<Impuesto> lista)
    {
        for(Impuesto i:lista)
        {
            i.setIdFactura(BigInteger.valueOf(idFactura));
            try {
                this.create(i);
            } catch (Exception e) {
                System.out.println("Se produjo un error al guardar impuesto: "+e);
            }        
        }
    }
}