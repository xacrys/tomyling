


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomyling.facturacion.controlador;

import com.tomyling.facturacion.modelo.Usuario;
import com.tomyling.facturacion.servicio.InstitucionServicio;
import com.tomyling.facturacion.servicio.UsuarioServicio;
import com.tomyling.facturacion.utilitarios.Utilitarios;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

/**
 *
 * @author EDWIN VACA
 */
@ManagedBean
@ViewScoped
public class UsuarioControlador extends Utilitarios implements Serializable {

    private String nombre;
    private String apellido;
    private String nombreUsuario;
    private String contrasenia;
    private String correo;
    private Integer idInstitucion;
    private List<SelectItem> listaInstituciones; 
    
    private Usuario usuario;
    private Usuario selecUsuario;
    private List<Usuario> listaUsuario;

    @EJB
    private UsuarioServicio usuarioServicio;
    
    @EJB
    private InstitucionServicio institucionServicio;

    public UsuarioControlador()
    { 
      this.listaUsuario =new ArrayList<>();
    }        
    @PostConstruct
    private void inicio(){
    this.listaInstituciones = new ArrayList<>();  
    listaInstituciones = institucionServicio.listarInstituciones();
    llenarUsuarios();
    }
    
    
    public void crearUsuario() {
        Usuario usuario = new Usuario();
        if (usuario.getIdUsuario() == null) {
            usuario.setIdInstitucion(idInstitucion);
            usuario.setNombre(nombre);
            usuario.setApellido(apellido);
            usuario.setUsuario(nombreUsuario);
            usuario.setContrasenia(contrasenia);
            usuario.setCorreo(correo);
            usuario.setEstado(Boolean.TRUE);
            this.usuarioServicio.crearUsuario(usuario);
        }

    }
    
     private void llenarUsuarios()
    {   
        try
        {    
            this.listaUsuario = usuarioServicio.listarUsuarios();
            FacesMessage msjsi=new FacesMessage();
            msjsi.setSeverity(FacesMessage.SEVERITY_INFO);
            msjsi.setSummary("lleno la lista");
            
            FacesContext.getCurrentInstance().addMessage("men", msjsi);
        }
        catch(Exception e)
        {
            FacesMessage msjno=new FacesMessage();
            msjno.setSeverity(FacesMessage.SEVERITY_ERROR);
            msjno.setSummary("NO lleno la lista");
            
            FacesContext.getCurrentInstance().addMessage("men1", msjno);
            //Mensaje
        }
    } 
    
    public void leer(Usuario  selecUsuario)
    {
        usuario=selecUsuario;   
    }        
     
    public void Editar(Usuario usuario)
    { 
        this.usuarioServicio.Modificar(usuario);
    }
            
    public Integer getIdInstitucion() {
        return idInstitucion;
    }

    public void setIdInstitucion(Integer idInstitucion) {
        this.idInstitucion = idInstitucion;
    }

    public List<SelectItem> getListaInstituciones() {
        return listaInstituciones;
    }

    public void setListaInstituciones(List<SelectItem> listaInstituciones) {
        this.listaInstituciones = listaInstituciones;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public InstitucionServicio getInstitucionServicio() {
        return institucionServicio;
    }

    public void setInstitucionServicio(InstitucionServicio institucionServicio) {
        this.institucionServicio = institucionServicio;
    }

    public Usuario getSelecUsuario() {
        return selecUsuario;
    }

    public void setSelecUsuario(Usuario selecUsuario) {
        this.selecUsuario = selecUsuario;
    }

    public List<Usuario> getListaUsuario() {
        return listaUsuario;
    }

    public void setListaUsuario(List<Usuario> listaUsuario) {
        this.listaUsuario = listaUsuario;
    }

    public UsuarioServicio getUsuarioServicio() {
        return usuarioServicio;
    }

    public void setUsuarioServicio(UsuarioServicio usuarioServicio) {
        this.usuarioServicio = usuarioServicio;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
      

}
