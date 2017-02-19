/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomyling.facturacion.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author new user
 */
@Entity
@Table(name = "retencion", schema="tomyling")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Retencion.findAll", query = "SELECT r FROM Retencion r")
    , @NamedQuery(name = "Retencion.findByIdRetencion", query = "SELECT r FROM Retencion r WHERE r.idRetencion = :idRetencion")
    , @NamedQuery(name = "Retencion.findByTipoImpuesto", query = "SELECT r FROM Retencion r WHERE r.tipoImpuesto = :tipoImpuesto")
    , @NamedQuery(name = "Retencion.findByCodigoPorcentaje", query = "SELECT r FROM Retencion r WHERE r.codigoPorcentaje = :codigoPorcentaje")
    , @NamedQuery(name = "Retencion.findByTarifa", query = "SELECT r FROM Retencion r WHERE r.tarifa = :tarifa")
    , @NamedQuery(name = "Retencion.findByValor", query = "SELECT r FROM Retencion r WHERE r.valor = :valor")})
public class Retencion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_retencion")
    private Integer idRetencion;
    @Size(max = 6)
    @Column(name = "tipo_impuesto")
    private String tipoImpuesto;
    @Column(name = "codigo_porcentaje")
    private Integer codigoPorcentaje;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "tarifa")
    private BigDecimal tarifa;
    @Column(name = "valor")
    private BigDecimal valor;

    public Retencion() {
    }

    public Retencion(Integer idRetencion) {
        this.idRetencion = idRetencion;
    }

    public Integer getIdRetencion() {
        return idRetencion;
    }

    public void setIdRetencion(Integer idRetencion) {
        this.idRetencion = idRetencion;
    }

    public String getTipoImpuesto() {
        return tipoImpuesto;
    }

    public void setTipoImpuesto(String tipoImpuesto) {
        this.tipoImpuesto = tipoImpuesto;
    }

    public Integer getCodigoPorcentaje() {
        return codigoPorcentaje;
    }

    public void setCodigoPorcentaje(Integer codigoPorcentaje) {
        this.codigoPorcentaje = codigoPorcentaje;
    }

    public BigDecimal getTarifa() {
        return tarifa;
    }

    public void setTarifa(BigDecimal tarifa) {
        this.tarifa = tarifa;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRetencion != null ? idRetencion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Retencion)) {
            return false;
        }
        Retencion other = (Retencion) object;
        if ((this.idRetencion == null && other.idRetencion != null) || (this.idRetencion != null && !this.idRetencion.equals(other.idRetencion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tomyling.facturacion.modelo.Retencion[ idRetencion=" + idRetencion + " ]";
    }
    
}
