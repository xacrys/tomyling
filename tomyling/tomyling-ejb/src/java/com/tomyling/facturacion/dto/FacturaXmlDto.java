/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomyling.facturacion.dto;

import com.tomyling.facturacion.modelo.DetalleFactura;
import com.tomyling.facturacion.modelo.Factura;
import com.tomyling.facturacion.modelo.Impuesto;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author new user
 */
public class FacturaXmlDto implements Serializable{
    
    
private Factura factura;
private List<Impuesto> listaImpuestos;
private List<DetalleFactura> listaDetalles;

    public FacturaXmlDto() {
    }

    public FacturaXmlDto(Factura factura, List<Impuesto> listaImpuestos, List<DetalleFactura> listaDetalles) {
        this.factura = factura;
        this.listaImpuestos = listaImpuestos;
        this.listaDetalles = listaDetalles;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public List<Impuesto> getListaImpuestos() {
        return listaImpuestos;
    }

    public void setListaImpuestos(List<Impuesto> listaImpuestos) {
        this.listaImpuestos = listaImpuestos;
    }

    public List<DetalleFactura> getListaDetalles() {
        return listaDetalles;
    }

    public void setListaDetalles(List<DetalleFactura> listaDetalles) {
        this.listaDetalles = listaDetalles;
    }
    
    
    
    



}
