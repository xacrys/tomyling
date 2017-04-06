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
@Table(name = "tipo_emision",schema="tomyling")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoEmision.findAll", query = "SELECT t FROM TipoEmision t")
    , @NamedQuery(name = "TipoEmision.findByCodEmision", query = "SELECT t FROM TipoEmision t WHERE t.codEmision = :codEmision")
    , @NamedQuery(name = "TipoEmision.findByTipoEmision", query = "SELECT t FROM TipoEmision t WHERE t.tipoEmision = :tipoEmision")})
public class TipoEmision implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "cod_emision")
    private Integer codEmision;
    @Size(max = 35)
    @Column(name = "tipo_emision")
    private String tipoEmision;

    public TipoEmision() {
    }

    public TipoEmision(Integer codEmision) {
        this.codEmision = codEmision;
    }

    public Integer getCodEmision() {
        return codEmision;
    }

    public void setCodEmision(Integer codEmision) {
        this.codEmision = codEmision;
    }

    public String getTipoEmision() {
        return tipoEmision;
    }

    public void setTipoEmision(String tipoEmision) {
        this.tipoEmision = tipoEmision;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codEmision != null ? codEmision.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoEmision)) {
            return false;
        }
        TipoEmision other = (TipoEmision) object;
        if ((this.codEmision == null && other.codEmision != null) || (this.codEmision != null && !this.codEmision.equals(other.codEmision))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tomyling.facturacion.modelo.TipoEmision[ codEmision=" + codEmision + " ]";
    }
    
}
