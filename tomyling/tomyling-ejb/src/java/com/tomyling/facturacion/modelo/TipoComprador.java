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
@Table(name = "tipo_comprador", schema="tomyling")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoComprador.findAll", query = "SELECT t FROM TipoComprador t")
    , @NamedQuery(name = "TipoComprador.findByCodComprador", query = "SELECT t FROM TipoComprador t WHERE t.codComprador = :codComprador")
    , @NamedQuery(name = "TipoComprador.findByDesComprador", query = "SELECT t FROM TipoComprador t WHERE t.desComprador = :desComprador")
    , @NamedQuery(name = "TipoComprador.findByEstadoComprador", query = "SELECT t FROM TipoComprador t WHERE t.estadoComprador = :estadoComprador")})
public class TipoComprador implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "cod_comprador")
    private String codComprador;
    @Size(max = 50)
    @Column(name = "des_comprador")
    private String desComprador;
    @Column(name = "estado_comprador")
    private Boolean estadoComprador;

    public TipoComprador() {
    }

    public TipoComprador(String codComprador) {
        this.codComprador = codComprador;
    }

    public String getCodComprador() {
        return codComprador;
    }

    public void setCodComprador(String codComprador) {
        this.codComprador = codComprador;
    }

    public String getDesComprador() {
        return desComprador;
    }

    public void setDesComprador(String desComprador) {
        this.desComprador = desComprador;
    }

    public Boolean getEstadoComprador() {
        return estadoComprador;
    }

    public void setEstadoComprador(Boolean estadoComprador) {
        this.estadoComprador = estadoComprador;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codComprador != null ? codComprador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoComprador)) {
            return false;
        }
        TipoComprador other = (TipoComprador) object;
        if ((this.codComprador == null && other.codComprador != null) || (this.codComprador != null && !this.codComprador.equals(other.codComprador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tomyling.facturacion.modelo.TipoComprador[ codComprador=" + codComprador + " ]";
    }
    
}
