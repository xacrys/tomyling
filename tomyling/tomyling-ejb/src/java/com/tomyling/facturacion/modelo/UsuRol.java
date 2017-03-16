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
@Table(name = "usu_rol", schema="tomyling")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsuRol.findAll", query = "SELECT u FROM UsuRol u")
    , @NamedQuery(name = "UsuRol.findByIdUsuario", query = "SELECT u FROM UsuRol u WHERE u.usuRolPK.idUsuario = :idUsuario")
    , @NamedQuery(name = "UsuRol.findByIdRol", query = "SELECT u FROM UsuRol u WHERE u.usuRolPK.idRol = :idRol")})
public class UsuRol implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UsuRolPK usuRolPK;

    public UsuRol() {
    }

    public UsuRol(UsuRolPK usuRolPK) {
        this.usuRolPK = usuRolPK;
    }

    public UsuRol(int idUsuario, int idRol) {
        this.usuRolPK = new UsuRolPK(idUsuario, idRol);
    }

    public UsuRolPK getUsuRolPK() {
        return usuRolPK;
    }

    public void setUsuRolPK(UsuRolPK usuRolPK) {
        this.usuRolPK = usuRolPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuRolPK != null ? usuRolPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuRol)) {
            return false;
        }
        UsuRol other = (UsuRol) object;
        if ((this.usuRolPK == null && other.usuRolPK != null) || (this.usuRolPK != null && !this.usuRolPK.equals(other.usuRolPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tomyling.facturacion.modelo.UsuRol[ usuRolPK=" + usuRolPK + " ]";
    }
    
}
