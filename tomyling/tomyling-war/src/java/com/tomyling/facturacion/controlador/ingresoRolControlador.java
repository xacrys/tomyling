package com.tomyling.facturacion.controlador;



import com.tomyling.facturacion.modelo.Rol;
import com.tomyling.facturacion.servicio.RolServicio;
import com.tomyling.facturacion.utilitarios.Utilitarios;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author EDWIN VACA
 */
@ManagedBean
@ViewScoped
public class ingresoRolControlador  extends Utilitarios implements Serializable    
{
    private Integer id_rol;
    private String nombre_rol;
    private Boolean estado_rol;
    private Date fecha_rol;
    
    private List<Rol> listaRol;
  //  private Rol selectRol;
    private Rol selectRol; 
  
    @EJB
    private RolServicio rolServ;
    @PostConstruct
    public void inicio()
    {   
        this.selectRol=new Rol();
        this.listaRol=new ArrayList<>();
        llenaRol();    
    }
    
    public void llenaRol()
    {
      listaRol=this.rolServ.cargaRol();
    }  
    
    public void guardaRol()
    {
        try
        {    
            Rol rol = new Rol();
            rol.setIdRol(id_rol);
            rol.setNombreRol(nombre_rol);
            rol.setEstadoRol(estado_rol);
            rol.setFechaCreacion(fecha_rol);

            this.rolServ.creaRol(rol);
            
            FacesMessage msj=new FacesMessage();
            msj.setSeverity(FacesMessage.SEVERITY_INFO);
            msj.setSummary("Rol creado con éxito..");
            
            FacesContext.getCurrentInstance().addMessage("men", msj);
        }
         catch(Exception e) 
         {
            FacesMessage msjs=new FacesMessage();
            msjs.setSeverity(FacesMessage.SEVERITY_INFO);
            msjs.setSummary("No se creo el Rol ..");
            
            FacesContext.getCurrentInstance().addMessage("men", msjs);
         }     
            
    } 
        
    public void verFila()
    {
        Rol rol2=new Rol();
        rol2=this.selectRol;
    } 
    //Eliminar Rol
    public void borraUnRol()
    {
        this.rolServ.eliminaRol(selectRol);
        
    } 
    
    //Editar Rol
    public void editaUnRol()
    {
        this.rolServ.editaRol(selectRol);
    }
    //getters y setters

    public Integer getId_rol() {
        return id_rol;
    }

    public void setId_rol(Integer id_rol) {
        this.id_rol = id_rol;
    }

    public String getNombre_rol() {
        return nombre_rol;
    }

    public void setNombre_rol(String nombre_rol) {
        this.nombre_rol = nombre_rol;
    }

    public Boolean getEstado_rol() {
        return estado_rol;
    }

    public void setEstado_rol(Boolean estado_rol) {
        this.estado_rol = estado_rol;
    }

    public Date getFecha_rol() {
        return fecha_rol;
    }

    public void setFecha_rol(Date fecha_rol) {
        this.fecha_rol = fecha_rol;
    }

    public List<Rol> getListaRol() {
        return listaRol;
    }

    public void setListaRol(List<Rol> listaRol) {
        this.listaRol = listaRol;
    }

    public Rol getSelectRol() {
        return selectRol;
    }

    public void setSelectRol(Rol selectRol) {
        this.selectRol = selectRol;
    }

}  
   
    