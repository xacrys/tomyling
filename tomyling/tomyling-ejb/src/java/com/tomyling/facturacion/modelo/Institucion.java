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
@Table(name = "institucion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Institucion.findAll", query = "SELECT i FROM Institucion i")
    , @NamedQuery(name = "Institucion.findByIdInstitucion", query = "SELECT i FROM Institucion i WHERE i.idInstitucion = :idInstitucion")
    , @NamedQuery(name = "Institucion.findByNombre", query = "SELECT i FROM Institucion i WHERE i.nombre = :nombre")
    , @NamedQuery(name = "Institucion.findByRuc", query = "SELECT i FROM Institucion i WHERE i.ruc = :ruc")
    , @NamedQuery(name = "Institucion.findByDireccion", query = "SELECT i FROM Institucion i WHERE i.direccion = :direccion")
    , @NamedQuery(name = "Institucion.findByTelefono", query = "SELECT i FROM Institucion i WHERE i.telefono = :telefono")})
public class Institucion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_institucion")
    private Integer idInstitucion;
    @Size(max = 100)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 20)
    @Column(name = "ruc")
    private String ruc;
    @Size(max = 300)
    @Column(name = "direccion")
    private String direccion;
    @Size(max = 100)
    @Column(name = "telefono")
    private String telefono;

    public Institucion() {
    }

    public Institucion(Integer idInstitucion) {
        this.idInstitucion = idInstitucion;
    }

    public Integer getIdInstitucion() {
        return idInstitucion;
    }

    public void setIdInstitucion(Integer idInstitucion) {
        this.idInstitucion = idInstitucion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idInstitucion != null ? idInstitucion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Institucion)) {
            return false;
        }
        Institucion other = (Institucion) object;
        if ((this.idInstitucion == null && other.idInstitucion != null) || (this.idInstitucion != null && !this.idInstitucion.equals(other.idInstitucion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tomyling.facturacion.modelo.Institucion[ idInstitucion=" + idInstitucion + " ]";
    }
    
}
