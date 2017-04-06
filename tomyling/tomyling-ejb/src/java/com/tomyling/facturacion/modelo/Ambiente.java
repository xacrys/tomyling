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
@Table(name = "ambiente",schema="tomyling")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ambiente.findAll", query = "SELECT a FROM Ambiente a")
    , @NamedQuery(name = "Ambiente.findByCodAmbiente", query = "SELECT a FROM Ambiente a WHERE a.codAmbiente = :codAmbiente")
    , @NamedQuery(name = "Ambiente.findByDescripcion", query = "SELECT a FROM Ambiente a WHERE a.descripcion = :descripcion")
    , @NamedQuery(name = "Ambiente.findByEstado", query = "SELECT a FROM Ambiente a WHERE a.estado = :estado")})
public class Ambiente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "cod_ambiente")
    private Integer codAmbiente;
    @Size(max = 35)
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "estado")
    private Boolean estado;

    public Ambiente() {
    }

    public Ambiente(Integer codAmbiente) {
        this.codAmbiente = codAmbiente;
    }

    public Integer getCodAmbiente() {
        return codAmbiente;
    }

    public void setCodAmbiente(Integer codAmbiente) {
        this.codAmbiente = codAmbiente;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
        hash += (codAmbiente != null ? codAmbiente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ambiente)) {
            return false;
        }
        Ambiente other = (Ambiente) object;
        if ((this.codAmbiente == null && other.codAmbiente != null) || (this.codAmbiente != null && !this.codAmbiente.equals(other.codAmbiente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tomyling.facturacion.modelo.Ambiente[ codAmbiente=" + codAmbiente + " ]";
    }
    
}
