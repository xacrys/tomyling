/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomyling.facturacion.modelo;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author new user
 */
@Entity
@Table(name = "ins_clientes", schema="tomyling")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InsClientes.findAll", query = "SELECT i FROM InsClientes i")
    , @NamedQuery(name = "InsClientes.findByIdInstitucion", query = "SELECT i FROM InsClientes i WHERE i.insClientesPK.idInstitucion = :idInstitucion")
    , @NamedQuery(name = "InsClientes.findByCedRuc", query = "SELECT i FROM InsClientes i WHERE i.insClientesPK.cedRuc = :cedRuc")})
public class InsClientes implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected InsClientesPK insClientesPK;

    public InsClientes() {
    }

    public InsClientes(InsClientesPK insClientesPK) {
        this.insClientesPK = insClientesPK;
    }

    public InsClientes(int idInstitucion, String cedRuc) {
        this.insClientesPK = new InsClientesPK(idInstitucion, cedRuc);
    }

    public InsClientesPK getInsClientesPK() {
        return insClientesPK;
    }

    public void setInsClientesPK(InsClientesPK insClientesPK) {
        this.insClientesPK = insClientesPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (insClientesPK != null ? insClientesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InsClientes)) {
            return false;
        }
        InsClientes other = (InsClientes) object;
        if ((this.insClientesPK == null && other.insClientesPK != null) || (this.insClientesPK != null && !this.insClientesPK.equals(other.insClientesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tomyling.facturacion.modelo.InsClientes[ insClientesPK=" + insClientesPK + " ]";
    }
    
}
