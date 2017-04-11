/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomyling.facturacion.controlador;

import com.tomyling.facturacion.modelo.Ambiente;
import com.tomyling.facturacion.modelo.DetalleFactura;
import com.tomyling.facturacion.modelo.Factura;
import com.tomyling.facturacion.modelo.Impuesto;
import com.tomyling.facturacion.modelo.TipoEmision;
import com.tomyling.facturacion.modelo.TipoImpuesto;
import com.tomyling.facturacion.servicio.AmbienteServicio;
import com.tomyling.facturacion.servicio.DetalleFacturaServicio;
import com.tomyling.facturacion.servicio.FacturaServicio;
import com.tomyling.facturacion.servicio.ImpuestoServicio;
import com.tomyling.facturacion.servicio.TipoEmisionServicio;
import com.tomyling.facturacion.servicio.TipoImpuestoServicio;
import com.tomyling.facturacion.utilitarios.Utilitarios;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
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
public class verFacturaControlador extends Utilitarios implements Serializable {

    private Integer idFactura;
    private String cedRuc;
    private String razonSocial;
    private Date fechaEmision;
    private String descripAmbiente;
    private String descripTipoEmision;
    private String obligaContabilidad;
    private String descripTipoImpuesto;
    //FACTURA
    private BigDecimal subtotal;
    private BigDecimal totalDescuento;
    private BigDecimal valorTotal;
    private BigDecimal propina;
    //IMPUESTO
    private BigDecimal valor14;
    private BigDecimal valorICE;
    private BigDecimal valorIRB;
    //*******************************************;;;;;;;;;
    private Boolean flagRide;

    private List<Factura> listaFactura;
    private Factura seleccionaFactura;
    private List<Ambiente> lstDesAmb;
    private List<DetalleFactura> listaDetalleFactura;
    private DetalleFactura seleccionaDetalleFactura;
    private List<Impuesto> listaImpuesto;

    @EJB
    private AmbienteServicio ambienteServicio;
    @EJB
    private TipoEmisionServicio tipoEmisionServicio;
    @EJB
    private DetalleFacturaServicio detalleFacturaServicio;
    @EJB
    private ImpuestoServicio impuestoServicio;
    @EJB
    private TipoImpuestoServicio tipImpSer;

    @PostConstruct
    private void inicio() {
        this.listaFactura = new ArrayList<>();
        this.listaDetalleFactura = new ArrayList<>();
        this.listaImpuesto = new ArrayList<>();
        flagRide = false;
        cargaFacturas();
        this.lstDesAmb = new ArrayList<>();
        
    }
    @EJB
    private FacturaServicio facturaServicio;

    public void cargaFacturas() {
        listaFactura = this.facturaServicio.verTodasFacturas();
        if (listaFactura.isEmpty() || listaFactura == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "No se ha podido encontrar facturas."));
        }
    }

    public void buscarFactura() {

        listaFactura = new ArrayList<>();
        listaFactura = this.facturaServicio.buscarFacturas(cedRuc, razonSocial, fechaEmision);
        if (listaFactura == null || listaFactura.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning! No se ha podido encontrar facturas", "No se ha podido encontrar facturas."));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se han encontrado " + listaFactura.size() + " facturas", "exito"));
        }

    }

    // Llenar lista listaDetalleFactura
    public void verRide(Factura fac) {
        seleccionaFactura = new Factura();
        flagRide = true;
        seleccionaFactura = fac;

        //Recupera descripcion de tabla Ambiente
        Integer cod = this.seleccionaFactura.getCodAmbiente();
        //  lstDesAmb=this.ambienteServicio.listaDescripAmbiente(cod);
        Ambiente amb = (Ambiente) this.ambienteServicio.retornaCodigo(cod);
        //  String descripcion=amb.getDescripcion();
        descripAmbiente = amb.getDescripcion();
        // recupera descripcion de tabla TipoEmision
        Integer codEmi = this.seleccionaFactura.getCodEmision();
        TipoEmision tipEmis;
        tipEmis = this.tipoEmisionServicio.ingresaTipEmi(codEmi);
        if (tipEmis != null) {
            descripTipoEmision = tipEmis.getTipoEmision();
        }
        //Obligado llevar contabilidad
        Boolean sino = this.seleccionaFactura.getObligadoContabilidad();
        if (sino == true) {
            obligaContabilidad = "Si";
        } else {
            obligaContabilidad = "No";
        }
        //Dstalle Factura
        Integer idDetFac = this.seleccionaFactura.getIdFactura();
        listaDetalleFactura = this.detalleFacturaServicio.cargaDetalleFactura(idDetFac);
        //Factura para Ride
        Factura factura;
        factura = null;
        factura = this.facturaServicio.recogeFactura(idDetFac);
        subtotal = factura.getTotalSinimpuesto();
        totalDescuento = factura.getTotalDescuento();
        valorTotal = factura.getImporteTotal();
        propina = factura.getPropina();
        // Impuestos
        listaImpuesto = this.impuestoServicio.lstImpuesto(this.seleccionaFactura.getIdFactura());
        this.valor14 = new BigDecimal(0d);
        this.valor14 = valor14.setScale(2, RoundingMode.CEILING);
        this.valorICE = new BigDecimal(0d);
        this.valorICE = valorICE.setScale(2, RoundingMode.CEILING);
        this.valorIRB = new BigDecimal(0d);
        this.valorIRB = valorIRB.setScale(2, RoundingMode.CEILING);
        for(Impuesto i: listaImpuesto)
        {
            if(i.getCodImpuesto()==2)
            {
                valor14 = i.getValor();
            }
            if(i.getCodImpuesto()==3)
            {
                valorICE = i.getValor();
            }
            if(i.getCodImpuesto()==5)
            {
                valorIRB = i.getValor();
            }
        }
        //Dstalle Factura
       
        factura=null;
        factura=this.facturaServicio.recogeFactura(idDetFac);
        subtotal=factura.getTotalSinimpuesto();
        totalDescuento=factura.getTotalDescuento();
        valorTotal=factura.getImporteTotal();
        propina=factura.getPropina();
        // Impuestos
        Integer codImpuesto=this.seleccionaFactura.getIdFactura();
        Impuesto impuesto;
        impuesto=this.impuestoServicio.recogeImpuesto(codImpuesto);
        Integer codigoImpuesto=impuesto.getCodImpuesto(); 
        valor14=impuesto.getValor();
        //Trae tipoImpuesto
        TipoImpuesto tipoImp;
        tipoImp=null;
        tipoImp=this.tipImpSer.traeCodImpuesto(codigoImpuesto);
        descripTipoImpuesto=tipoImp.getDescImpuesto();
        
   }
   public void regresar()
   {
       flagRide=false;
   }
   
   public void descargarXls(){
   generaXls();
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

    public String getDescripTipoImpuesto() {
        return descripTipoImpuesto;
    }

    public void setDescripTipoImpuesto(String descripTipoImpuesto) {
        this.descripTipoImpuesto = descripTipoImpuesto;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getTotalDescuento() {
        return totalDescuento;
    }

    public void setTotalDescuento(BigDecimal totalDescuento) {
        this.totalDescuento = totalDescuento;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public BigDecimal getValor14() {
        return valor14;
    }

    public void setValor14(BigDecimal valor14) {
        this.valor14 = valor14;
    }

    public BigDecimal getPropina() {
        return propina;
    }

    public void setPropina(BigDecimal propina) {
        this.propina = propina;
    }

    public BigDecimal getValorICE() {
        return valorICE;
    }

    public void setValorICE(BigDecimal valorICE) {
        this.valorICE = valorICE;
    }

    public BigDecimal getValorIRB() {
        return valorIRB;
    }

    public void setValorIRB(BigDecimal valorIRB) {
        this.valorIRB = valorIRB;
    }

    public ImpuestoServicio getImpuestoServicio() {
        return impuestoServicio;
    }

    public void setImpuestoServicio(ImpuestoServicio impuestoServicio) {
        this.impuestoServicio = impuestoServicio;
    }

}
