/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomyling.facturacion.dto;

import com.tomyling.facturacion.modelo.DetalleFactura;
import com.tomyling.facturacion.modelo.Factura;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author new user
 */
public class facturaCompletaDto implements Serializable{

    private Factura factura;
    private String descripcionAmbiente;
    private String descripcionTipoEmision;
    private List<DetalleFactura> listaDetalle;
    private BigDecimal valor14;
    private BigDecimal valorIce;
    private BigDecimal valorIrbf;

    public facturaCompletaDto(Factura factura, String descripcionAmbiente, String descripcionTipoEmision, List<DetalleFactura> listaDetalle, BigDecimal valor14, BigDecimal valorIce, BigDecimal valorIrbf) {
        this.factura = factura;
        this.descripcionAmbiente = descripcionAmbiente;
        this.descripcionTipoEmision = descripcionTipoEmision;
        this.listaDetalle = listaDetalle;
        this.valor14 = valor14;
        this.valorIce = valorIce;
        this.valorIrbf = valorIrbf;
    }

    public facturaCompletaDto() {
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public String getDescripcionAmbiente() {
        return descripcionAmbiente;
    }

    public void setDescripcionAmbiente(String descripcionAmbiente) {
        this.descripcionAmbiente = descripcionAmbiente;
    }

    public String getDescripcionTipoEmision() {
        return descripcionTipoEmision;
    }

    public void setDescripcionTipoEmision(String descripcionTipoEmision) {
        this.descripcionTipoEmision = descripcionTipoEmision;
    }

    public List<DetalleFactura> getListaDetalle() {
        return listaDetalle;
    }

    public void setListaDetalle(List<DetalleFactura> listaDetalle) {
        this.listaDetalle = listaDetalle;
    }

    public BigDecimal getValor14() {
        return valor14;
    }

    public void setValor14(BigDecimal valor14) {
        this.valor14 = valor14;
    }

    public BigDecimal getValorIce() {
        return valorIce;
    }

    public void setValorIce(BigDecimal valorIce) {
        this.valorIce = valorIce;
    }

    public BigDecimal getValorIrbf() {
        return valorIrbf;
    }

    public void setValorIrbf(BigDecimal valorIrbf) {
        this.valorIrbf = valorIrbf;
    }
    
    

}
