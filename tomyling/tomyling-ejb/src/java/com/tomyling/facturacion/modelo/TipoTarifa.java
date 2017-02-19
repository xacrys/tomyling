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
@Table(name = "tipo_tarifa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoTarifa.findAll", query = "SELECT t FROM TipoTarifa t")
    , @NamedQuery(name = "TipoTarifa.findByIdTarifa", query = "SELECT t FROM TipoTarifa t WHERE t.idTarifa = :idTarifa")
    , @NamedQuery(name = "TipoTarifa.findByCodTarifa", query = "SELECT t FROM TipoTarifa t WHERE t.codTarifa = :codTarifa")
    , @NamedQuery(name = "TipoTarifa.findByDescTarifa", query = "SELECT t FROM TipoTarifa t WHERE t.descTarifa = :descTarifa")
    , @NamedQuery(name = "TipoTarifa.findByImpuesto", query = "SELECT t FROM TipoTarifa t WHERE t.impuesto = :impuesto")})
public class TipoTarifa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tarifa")
    private Integer idTarifa;
    @Size(max = 5)
    @Column(name = "cod_tarifa")
    private String codTarifa;
    @Size(max = 25)
    @Column(name = "desc_tarifa")
    private String descTarifa;
    @Size(max = 20)
    @Column(name = "impuesto")
    private String impuesto;

    public TipoTarifa() {
    }

    public TipoTarifa(Integer idTarifa) {
        this.idTarifa = idTarifa;
    }

    public Integer getIdTarifa() {
        return idTarifa;
    }

    public void setIdTarifa(Integer idTarifa) {
        this.idTarifa = idTarifa;
    }

    public String getCodTarifa() {
        return codTarifa;
    }

    public void setCodTarifa(String codTarifa) {
        this.codTarifa = codTarifa;
    }

    public String getDescTarifa() {
        return descTarifa;
    }

    public void setDescTarifa(String descTarifa) {
        this.descTarifa = descTarifa;
    }

    public String getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(String impuesto) {
        this.impuesto = impuesto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTarifa != null ? idTarifa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoTarifa)) {
            return false;
        }
        TipoTarifa other = (TipoTarifa) object;
        if ((this.idTarifa == null && other.idTarifa != null) || (this.idTarifa != null && !this.idTarifa.equals(other.idTarifa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tomyling.facturacion.modelo.TipoTarifa[ idTarifa=" + idTarifa + " ]";
    }
    
}
