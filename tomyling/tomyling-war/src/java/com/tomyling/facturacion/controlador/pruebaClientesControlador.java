/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomyling.facturacion.controlador;

import com.tomyling.facturacion.modelo.Clientes;
import com.tomyling.facturacion.servicio.ClientesServicio;
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
public class pruebaClientesControlador extends Utilitarios implements Serializable
{
    private String cedRuc;
    private String nombre;
    private String direccion;
    private String telefono;
    private Boolean estado;
    private String correo;
            
    private Clientes cliente;
    private List<Clientes> listaClientes;
    private Clientes selCliente;
    
    @EJB
    private ClientesServicio clienteServicio;
    
    @PostConstruct
    private void inicio()
    {  this.listaClientes=new ArrayList<>();
       llenaClientes();
    } 
    
    public void llenaClientes()
    {
       this.listaClientes=clienteServicio.listaTodosClientes(); 
    } 
    
    public void leeFila()
    {
       cliente=this.selCliente;
    }  
    
    public void seleccionaFila(SelectEvent evento)
    {
       this.selCliente=(Clientes) evento.getObject();
    }     
    // getters y setters

    public String getCedRuc() {
        return cedRuc;
    }

    public void setCedRuc(String cedRuc) {
        this.cedRuc = cedRuc;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Clientes getCliente() {
        return cliente;
    }

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }

    public List<Clientes> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(List<Clientes> listaClientes) {
        this.listaClientes = listaClientes;
    }

    public Clientes getSelCliente() {
        return selCliente;
    }

    public void setSelCliente(Clientes selCliente) {
        this.selCliente = selCliente;
    }
    
}
