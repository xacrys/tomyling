/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomyling.facturacion.controlador;

import com.tomyling.facturacion.modelo.Factura;
import com.tomyling.facturacion.servicio.FacturaServicio;
import com.tomyling.facturacion.utilitarios.Utilitarios;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author EDWIN VACA
 */
@ManagedBean
@ViewScoped
public class verFacturaControlador extends Utilitarios implements Serializable  
{
   private Integer idFactura;
   private String cedRuc;
   private String razonSocial;
   private Date fechaEmision;
   private float totalSinImpuestos;
   private float totalDescuentos;
   
   private List<Factura> listaFactura;
   private Factura seleccionaFactura;
   
   
   @PostConstruct
   private void inicio()
   {
       // Intanciamos a selectInstitucion para que no sea null
       this.seleccionaFactura=new Factura();
       this.listaFactura=new ArrayList<>(); 
       cargaFacturas();     
   }
   @EJB
   private FacturaServicio facturaServicio;
   
   public void cargaFacturas()
   {         
      // listaFactura=this.facturaServicio.verTodasFacturas();
   }
   
   //getters y setters
   public Integer getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(Integer idFactura) {
        this.idFactura = idFactura;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public float getTotalSinImpuestos() {
        return totalSinImpuestos;
    }

    public void setTotalSinImpuestos(float totalSinImpuestos) {
        this.totalSinImpuestos = totalSinImpuestos;
    }

    public float getTotalDescuentos() {
        return totalDescuentos;
    }

    public void setTotalDescuentos(float totalDescuentos) {
        this.totalDescuentos = totalDescuentos;
    }

    public Factura getSeleccionaFactura() {
        return seleccionaFactura;
    }

    public void setSeleccionaFactura(Factura seleccionaFactura) {
        this.seleccionaFactura = seleccionaFactura;
    }

    public String getCedRuc() {
        return cedRuc;
    }

    public void setCedRuc(String cedRuc) {
        this.cedRuc = cedRuc;
    }

    public List<Factura> getListaFactura() {
        return listaFactura;
    }

    public void setListaFactura(List<Factura> listaFactura) {
        this.listaFactura = listaFactura;
    }
  
     
}
