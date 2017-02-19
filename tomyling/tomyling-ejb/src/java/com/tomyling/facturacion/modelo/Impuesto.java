/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomyling.facturacion.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
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
@Table(name = "impuesto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Impuesto.findAll", query = "SELECT i FROM Impuesto i")
    , @NamedQuery(name = "Impuesto.findByIdImpuesto", query = "SELECT i FROM Impuesto i WHERE i.idImpuesto = :idImpuesto")
    , @NamedQuery(name = "Impuesto.findByCodImpuesto", query = "SELECT i FROM Impuesto i WHERE i.codImpuesto = :codImpuesto")
    , @NamedQuery(name = "Impuesto.findByIdFactura", query = "SELECT i FROM Impuesto i WHERE i.idFactura = :idFactura")
    , @NamedQuery(name = "Impuesto.findByCodigoPorcentaje", query = "SELECT i FROM Impuesto i WHERE i.codigoPorcentaje = :codigoPorcentaje")
    , @NamedQuery(name = "Impuesto.findByDescuentoAdicional", query = "SELECT i FROM Impuesto i WHERE i.descuentoAdicional = :descuentoAdicional")
    , @NamedQuery(name = "Impuesto.findByBaseImponible", query = "SELECT i FROM Impuesto i WHERE i.baseImponible = :baseImponible")
    , @NamedQuery(name = "Impuesto.findByValor", query = "SELECT i FROM Impuesto i WHERE i.valor = :valor")
    , @NamedQuery(name = "Impuesto.findByTarifa", query = "SELECT i FROM Impuesto i WHERE i.tarifa = :tarifa")})
public class Impuesto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_impuesto")
    private Integer idImpuesto;
    @Column(name = "cod_impuesto")
    private Integer codImpuesto;
    @Column(name = "id_factura")
    private BigInteger idFactura;
    @Size(max = 6)
    @Column(name = "codigo_porcentaje")
    private String codigoPorcentaje;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "descuento_adicional")
    private BigDecimal descuentoAdicional;
    @Column(name = "base_imponible")
    private BigDecimal baseImponible;
    @Column(name = "valor")
    private BigDecimal valor;
    @Column(name = "tarifa")
    private Integer tarifa;

    public Impuesto() {
    }

    public Impuesto(Integer idImpuesto) {
        this.idImpuesto = idImpuesto;
    }

    public Integer getIdImpuesto() {
        return idImpuesto;
    }

    public void setIdImpuesto(Integer idImpuesto) {
        this.idImpuesto = idImpuesto;
    }

    public Integer getCodImpuesto() {
        return codImpuesto;
    }

    public void setCodImpuesto(Integer codImpuesto) {
        this.codImpuesto = codImpuesto;
    }

    public BigInteger getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(BigInteger idFactura) {
        this.idFactura = idFactura;
    }

    public String getCodigoPorcentaje() {
        return codigoPorcentaje;
    }

    public void setCodigoPorcentaje(String codigoPorcentaje) {
        this.codigoPorcentaje = codigoPorcentaje;
    }

    public BigDecimal getDescuentoAdicional() {
        return descuentoAdicional;
    }

    public void setDescuentoAdicional(BigDecimal descuentoAdicional) {
        this.descuentoAdicional = descuentoAdicional;
    }

    public BigDecimal getBaseImponible() {
        return baseImponible;
    }

    public void setBaseImponible(BigDecimal baseImponible) {
        this.baseImponible = baseImponible;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Integer getTarifa() {
        return tarifa;
    }

    public void setTarifa(Integer tarifa) {
        this.tarifa = tarifa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idImpuesto != null ? idImpuesto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Impuesto)) {
            return false;
        }
        Impuesto other = (Impuesto) object;
        if ((this.idImpuesto == null && other.idImpuesto != null) || (this.idImpuesto != null && !this.idImpuesto.equals(other.idImpuesto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tomyling.facturacion.modelo.Impuesto[ idImpuesto=" + idImpuesto + " ]";
    }
    
}
