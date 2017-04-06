/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomyling.facturacion.modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author new user
 */
@Embeddable
public class RolMenuPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "id_rol")
    private int idRol;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_menu")
    private int idMenu;

    public RolMenuPK() {
    }

    public RolMenuPK(int idRol, int idMenu) {
        this.idRol = idRol;
        this.idMenu = idMenu;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public int getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(int idMenu) {
        this.idMenu = idMenu;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idRol;
        hash += (int) idMenu;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RolMenuPK)) {
            return false;
        }
        RolMenuPK other = (RolMenuPK) object;
        if (this.idRol != other.idRol) {
            return false;
        }
        if (this.idMenu != other.idMenu) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tomyling.facturacion.modelo.RolMenuPK[ idRol=" + idRol + ", idMenu=" + idMenu + " ]";
    }
    
}
