/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomyling.facturacion.controlador;

import com.tomyling.facturacion.modelo.Institucion;
import com.tomyling.facturacion.servicio.InstitucionServicio;
import com.tomyling.facturacion.utilitarios.Utilitarios;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author EDWIN VACA
 */
@ManagedBean
@ViewScoped
public class ingresoInstitucionControlador  extends Utilitarios implements Serializable  
{
   private Integer ID;
   private String nombre;
   private String cedRuc;
   private String direccion;
   private String telefono;
   
   private Institucion selectInstitucion;
   private List<Institucion> listaInstitucion;
   
  
   
   @PostConstruct
   private void inicio()
   {
       this.listaInstitucion=new ArrayList<>();
       llenaInstitucion();
   }  
   
   @EJB
   private InstitucionServicio institutServicio;
   
   public void guardarInstitucion()
   {
       //también se instancia
       //Institucion institucion;
       Institucion institucion=new Institucion();
       institucion.setIdInstitucion(ID);
       institucion.setNombre(nombre);
       institucion.setRuc(cedRuc);
       institucion.setDireccion(direccion);
       institucion.setTelefono(telefono);
       
       this.institutServicio.crearInstitucion(institucion);
   } 
   
   public void llenaInstitucion()
   { 
     listaInstitucion=this.institutServicio.cargaInstitucion();
   } 
   
   public void eliminarInstitucion()
   {
      this.institutServicio.eliminaInstitucion(selectInstitucion);    
   }
   
   public void seleccionaFila(SelectEvent ev)
   {
       this.selectInstitucion=(Institucion) ev.getObject();
   }        
   //getters y setters
    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCedRuc() {
        return cedRuc;
    }

    public void setCedRuc(String cedRuc) {
        this.cedRuc = cedRuc;
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

    public Institucion getSelectInstitucion() {
        return selectInstitucion;
    }

    public void setSelectInstitucion(Institucion selectInstitucion) {
        this.selectInstitucion = selectInstitucion;
    }

    public List<Institucion> getListaInstitucion() {
        return listaInstitucion;
    }

    public void setListaInstitucion(List<Institucion> listaInstitucion) {
        this.listaInstitucion = listaInstitucion;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }
   
}
