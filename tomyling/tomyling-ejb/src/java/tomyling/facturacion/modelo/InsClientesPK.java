/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tomyling.facturacion.modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author new user
 */
@Embeddable
public class InsClientesPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "id_institucion")
    private int idInstitucion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "ced_ruc")
    private String cedRuc;

    public InsClientesPK() {
    }

    public InsClientesPK(int idInstitucion, String cedRuc) {
        this.idInstitucion = idInstitucion;
        this.cedRuc = cedRuc;
    }

    public int getIdInstitucion() {
        return idInstitucion;
    }

    public void setIdInstitucion(int idInstitucion) {
        this.idInstitucion = idInstitucion;
    }

    public String getCedRuc() {
        return cedRuc;
    }

    public void setCedRuc(String cedRuc) {
        this.cedRuc = cedRuc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idInstitucion;
        hash += (cedRuc != null ? cedRuc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InsClientesPK)) {
            return false;
        }
        InsClientesPK other = (InsClientesPK) object;
        if (this.idInstitucion != other.idInstitucion) {
            return false;
        }
        if ((this.cedRuc == null && other.cedRuc != null) || (this.cedRuc != null && !this.cedRuc.equals(other.cedRuc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tomyling.facturacion.modelo.InsClientesPK[ idInstitucion=" + idInstitucion + ", cedRuc=" + cedRuc + " ]";
    }
    
}
