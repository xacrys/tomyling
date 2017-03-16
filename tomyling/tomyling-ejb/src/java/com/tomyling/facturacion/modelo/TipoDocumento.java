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
@Table(name = "tipo_documento", schema="tomyling")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoDocumento.findAll", query = "SELECT t FROM TipoDocumento t")
    , @NamedQuery(name = "TipoDocumento.findByCodDocumento", query = "SELECT t FROM TipoDocumento t WHERE t.codDocumento = :codDocumento")
    , @NamedQuery(name = "TipoDocumento.findByDescripcionDoc", query = "SELECT t FROM TipoDocumento t WHERE t.descripcionDoc = :descripcionDoc")
    , @NamedQuery(name = "TipoDocumento.findByEstadoDoc", query = "SELECT t FROM TipoDocumento t WHERE t.estadoDoc = :estadoDoc")})
public class TipoDocumento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "cod_documento")
    private String codDocumento;
    @Size(max = 50)
    @Column(name = "descripcion_doc")
    private String descripcionDoc;
    @Column(name = "estado_doc")
    private Boolean estadoDoc;

    public TipoDocumento() {
    }

    public TipoDocumento(String codDocumento) {
        this.codDocumento = codDocumento;
    }

    public String getCodDocumento() {
        return codDocumento;
    }

    public void setCodDocumento(String codDocumento) {
        this.codDocumento = codDocumento;
    }

    public String getDescripcionDoc() {
        return descripcionDoc;
    }

    public void setDescripcionDoc(String descripcionDoc) {
        this.descripcionDoc = descripcionDoc;
    }

    public Boolean getEstadoDoc() {
        return estadoDoc;
    }

    public void setEstadoDoc(Boolean estadoDoc) {
        this.estadoDoc = estadoDoc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codDocumento != null ? codDocumento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoDocumento)) {
            return false;
        }
        TipoDocumento other = (TipoDocumento) object;
        if ((this.codDocumento == null && other.codDocumento != null) || (this.codDocumento != null && !this.codDocumento.equals(other.codDocumento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tomyling.facturacion.modelo.TipoDocumento[ codDocumento=" + codDocumento + " ]";
    }
    
}
