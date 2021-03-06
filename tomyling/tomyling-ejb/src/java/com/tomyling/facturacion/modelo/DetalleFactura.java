/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomyling.facturacion.modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author new user
 */
@Entity
@Table(name = "detalle_factura",schema="tomyling")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleFactura.findAll", query = "SELECT d FROM DetalleFactura d")
    , @NamedQuery(name = "DetalleFactura.findByIdDetalle", query = "SELECT d FROM DetalleFactura d WHERE d.idDetalle = :idDetalle")
    , @NamedQuery(name = "DetalleFactura.findByIdFactura", query = "SELECT d FROM DetalleFactura d WHERE d.idFactura = :idFactura")
    , @NamedQuery(name = "DetalleFactura.findByCodigoPrincipal", query = "SELECT d FROM DetalleFactura d WHERE d.codigoPrincipal = :codigoPrincipal")
    , @NamedQuery(name = "DetalleFactura.findByCodigoAuxiliar", query = "SELECT d FROM DetalleFactura d WHERE d.codigoAuxiliar = :codigoAuxiliar")
    , @NamedQuery(name = "DetalleFactura.findByDescripcion", query = "SELECT d FROM DetalleFactura d WHERE d.descripcion = :descripcion")
    , @NamedQuery(name = "DetalleFactura.findByCantidad", query = "SELECT d FROM DetalleFactura d WHERE d.cantidad = :cantidad")
    , @NamedQuery(name = "DetalleFactura.findByPrecioUnitario", query = "SELECT d FROM DetalleFactura d WHERE d.precioUnitario = :precioUnitario")
    , @NamedQuery(name = "DetalleFactura.findByDescuento", query = "SELECT d FROM DetalleFactura d WHERE d.descuento = :descuento")
    , @NamedQuery(name = "DetalleFactura.findByPrecioTotalSinimpuesto", query = "SELECT d FROM DetalleFactura d WHERE d.precioTotalSinimpuesto = :precioTotalSinimpuesto")})
public class DetalleFactura implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_detalle")
    private Integer idDetalle;
    @Column(name = "id_factura")
    private Integer idFactura;
    @Size(max = 25)
    @Column(name = "codigo_principal")
    private String codigoPrincipal;
    @Size(max = 25)
    @Column(name = "codigo_auxiliar")
    private String codigoAuxiliar;
    @Size(max = 300)
    @Column(name = "descripcion")
    private String descripcion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "cantidad")
    private Double cantidad;
    @Column(name = "precio_unitario")
    private Double precioUnitario;
    @Column(name = "descuento")
    private Double descuento;
    @Column(name = "precio_total_sinimpuesto")
    private Double precioTotalSinimpuesto;

    public DetalleFactura() {
    }

    public DetalleFactura(Integer idDetalle) {
        this.idDetalle = idDetalle;
    }

    public Integer getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(Integer idDetalle) {
        this.idDetalle = idDetalle;
    }

    public Integer getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(Integer idFactura) {
        this.idFactura = idFactura;
    }

    public String getCodigoPrincipal() {
        return codigoPrincipal;
    }

    public void setCodigoPrincipal(String codigoPrincipal) {
        this.codigoPrincipal = codigoPrincipal;
    }

    public String getCodigoAuxiliar() {
        return codigoAuxiliar;
    }

    public void setCodigoAuxiliar(String codigoAuxiliar) {
        this.codigoAuxiliar = codigoAuxiliar;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Double getDescuento() {
        return descuento;
    }

    public void setDescuento(Double descuento) {
        this.descuento = descuento;
    }

    public Double getPrecioTotalSinimpuesto() {
        return precioTotalSinimpuesto;
    }

    public void setPrecioTotalSinimpuesto(Double precioTotalSinimpuesto) {
        this.precioTotalSinimpuesto = precioTotalSinimpuesto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDetalle != null ? idDetalle.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleFactura)) {
            return false;
        }
        DetalleFactura other = (DetalleFactura) object;
        if ((this.idDetalle == null && other.idDetalle != null) || (this.idDetalle != null && !this.idDetalle.equals(other.idDetalle))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tomyling.facturacion.modelo.DetalleFactura[ idDetalle=" + idDetalle + " ]";
    }
    
}
