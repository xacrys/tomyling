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
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

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
   private Boolean flagRide;
  
   
   private List<Factura> listaFactura;
   private Factura seleccionaFactura;
   
   
   @PostConstruct
   private void inicio()
   {
       // Intanciamos a selectInstitucion para que no sea null
       
       this.listaFactura=new ArrayList<>();
       flagRide=false;
       cargaFacturas();     
   }
   @EJB
   private FacturaServicio facturaServicio;
   
   public void cargaFacturas()
   {         
      listaFactura=this.facturaServicio.verTodasFacturas();
      if(listaFactura.isEmpty() || listaFactura==null)
      {
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "No se ha podido encontrar facturas."));
      }
   }
   
   public void buscarFactura(){
   
       listaFactura= new ArrayList<>();
       listaFactura = this.facturaServicio.buscarFacturas(cedRuc, razonSocial, fechaEmision);
       if(listaFactura==null || listaFactura.isEmpty() )
      {
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning! No se ha podido encontrar facturas" , "No se ha podido encontrar facturas."));
      }
       else{
       FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se han encontrado "+listaFactura.size()+" facturas" , "exito"));
       }
       
   }
   
   public void verRide(Factura fac)
   {
       seleccionaFactura=new Factura();
       flagRide=true;
       seleccionaFactura=fac;
   }
   public void regresar()
   {
       flagRide=false;
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

    public Boolean getFlagRide() {
        return flagRide;
    }

    public void setFlagRide(Boolean flagRide) {
        this.flagRide = flagRide;
    }
  
     
}
