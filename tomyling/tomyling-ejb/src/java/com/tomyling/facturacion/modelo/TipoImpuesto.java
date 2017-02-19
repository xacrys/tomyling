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
@Table(name = "tipo_impuesto", schema="tomyling")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoImpuesto.findAll", query = "SELECT t FROM TipoImpuesto t")
    , @NamedQuery(name = "TipoImpuesto.findByCodImpuesto", query = "SELECT t FROM TipoImpuesto t WHERE t.codImpuesto = :codImpuesto")
    , @NamedQuery(name = "TipoImpuesto.findByDescImpuesto", query = "SELECT t FROM TipoImpuesto t WHERE t.descImpuesto = :descImpuesto")
    , @NamedQuery(name = "TipoImpuesto.findByEstado", query = "SELECT t FROM TipoImpuesto t WHERE t.estado = :estado")})
public class TipoImpuesto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "cod_impuesto")
    private Integer codImpuesto;
    @Size(max = 20)
    @Column(name = "desc_impuesto")
    private String descImpuesto;
    @Column(name = "estado")
    private Boolean estado;

    public TipoImpuesto() {
    }

    public TipoImpuesto(Integer codImpuesto) {
        this.codImpuesto = codImpuesto;
    }

    public Integer getCodImpuesto() {
        return codImpuesto;
    }

    public void setCodImpuesto(Integer codImpuesto) {
        this.codImpuesto = codImpuesto;
    }

    public String getDescImpuesto() {
        return descImpuesto;
    }

    public void setDescImpuesto(String descImpuesto) {
        this.descImpuesto = descImpuesto;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codImpuesto != null ? codImpuesto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoImpuesto)) {
            return false;
        }
        TipoImpuesto other = (TipoImpuesto) object;
        if ((this.codImpuesto == null && other.codImpuesto != null) || (this.codImpuesto != null && !this.codImpuesto.equals(other.codImpuesto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tomyling.facturacion.modelo.TipoImpuesto[ codImpuesto=" + codImpuesto + " ]";
    }
    
}
