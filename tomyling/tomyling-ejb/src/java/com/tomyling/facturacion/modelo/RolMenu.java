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
@Table(name = "rol_menu", schema="tomyling")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RolMenu.findAll", query = "SELECT r FROM RolMenu r")
    , @NamedQuery(name = "RolMenu.findByIdRol", query = "SELECT r FROM RolMenu r WHERE r.rolMenuPK.idRol = :idRol")
    , @NamedQuery(name = "RolMenu.findByIdMenu", query = "SELECT r FROM RolMenu r WHERE r.rolMenuPK.idMenu = :idMenu")})
public class RolMenu implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RolMenuPK rolMenuPK;

    public RolMenu() {
    }

    public RolMenu(RolMenuPK rolMenuPK) {
        this.rolMenuPK = rolMenuPK;
    }

    public RolMenu(int idRol, int idMenu) {
        this.rolMenuPK = new RolMenuPK(idRol, idMenu);
    }

    public RolMenuPK getRolMenuPK() {
        return rolMenuPK;
    }

    public void setRolMenuPK(RolMenuPK rolMenuPK) {
        this.rolMenuPK = rolMenuPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rolMenuPK != null ? rolMenuPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RolMenu)) {
            return false;
        }
        RolMenu other = (RolMenu) object;
        if ((this.rolMenuPK == null && other.rolMenuPK != null) || (this.rolMenuPK != null && !this.rolMenuPK.equals(other.rolMenuPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tomyling.facturacion.modelo.RolMenu[ rolMenuPK=" + rolMenuPK + " ]";
    }
    
}
