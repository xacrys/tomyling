/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomyling.facturacion.controlador;

import com.tomyling.facturacion.modelo.Ambiente;
import com.tomyling.facturacion.modelo.DetalleFactura;
import com.tomyling.facturacion.modelo.Factura;
import com.tomyling.facturacion.modelo.TipoEmision;
import com.tomyling.facturacion.servicio.AmbienteServicio;
import com.tomyling.facturacion.servicio.DetalleFacturaServicio;
import com.tomyling.facturacion.servicio.FacturaServicio;
import com.tomyling.facturacion.servicio.TipoEmisionServicio;
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
   private String descripAmbiente; 
   private String descripTipoEmision;
   private String obligaContabilidad;
   //*******************************************
   private Boolean flagRide;
  
   
   private List<Factura> listaFactura;
   private Factura seleccionaFactura;
   private List<Ambiente> lstDesAmb;
   private List<DetalleFactura> listaDetalleFactura;
   private DetalleFactura seleccionaDetalleFactura;
   
   @EJB
   private AmbienteServicio ambienteServicio;
   @EJB
   private TipoEmisionServicio tipoEmisionServicio;
   @EJB
   private DetalleFacturaServicio detalleFacturaServicio;
   
   @PostConstruct
   private void inicio()
   {           
       this.listaFactura=new ArrayList<>();
       this.listaDetalleFactura=new ArrayList<>();
       flagRide=false;
       cargaFacturas(); 
       this.lstDesAmb=new ArrayList<>();
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
   
   // Llenar lista listaDetalleFactura
         
   public void verRide(Factura fac)  
   {
     seleccionaFactura=new Factura();
       flagRide=true;
       seleccionaFactura=fac;
       
       //Recupera descripcion de tabla Ambiente
//       Integer cod=this.seleccionaFactura.getCodAmbiente(); 
//     //  lstDesAmb=this.ambienteServicio.listaDescripAmbiente(cod);
//        Ambiente amb=(Ambiente) this.ambienteServicio.retornaCodigo(cod); 
//      //  String descripcion=amb.getDescripcion();
//        descripAmbiente=amb.getDescripcion();
//        // recupera descripcion de tabla TipoEmision
//        Integer codEmi=this.seleccionaFactura.getCodEmision();
//        TipoEmision tipEmis;
//        tipEmis=this.tipoEmisionServicio.ingresaTipEmi(codEmi);
//        descripTipoEmision=tipEmis.getTipoEmision();
//        //Obligado llevar contabilidad
//        Boolean sino=this.seleccionaFactura.getObligadoContabilidad();      
//        if(sino==true)
//        {
//            obligaContabilidad="Si";
//        }
//        else
//        {
//           obligaContabilidad="No"; 
//        }
//        Integer idDetFac=this.seleccionaFactura.getIdFactura();
//        listaDetalleFactura=this.detalleFacturaServicio.cargaDetalleFactura(idDetFac);      
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

    public String getDescripAmbiente() {
        return descripAmbiente;
    }

    public void setDescripAmbiente(String descripAmbiente) {
        this.descripAmbiente = descripAmbiente;
    }

    public String getDescripTipoEmision() {
        return descripTipoEmision;
    }

    public void setDescripTipoEmision(String descripTipoEmision) {
        this.descripTipoEmision = descripTipoEmision; 
    }

    public String getObligaContabilidad() {
        return obligaContabilidad;
    }

    public void setObligaContabilidad(String obligaContabilidad) {
        this.obligaContabilidad = obligaContabilidad;
    }

    public DetalleFactura getSeleccionaDetalleFactura() {
        return seleccionaDetalleFactura;
    }

    public void setSeleccionaDetalleFactura(DetalleFactura seleccionaDetalleFactura) {
        this.seleccionaDetalleFactura = seleccionaDetalleFactura;
    }

    public List<DetalleFactura> getListaDetalleFactura() {
        return listaDetalleFactura;
    }

    public void setListaDetalleFactura(List<DetalleFactura> listaDetalleFactura) {
        this.listaDetalleFactura = listaDetalleFactura;
    }
  
     
     
}
