/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomyling.facturacion.servicio;


import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import com.tomyling.facturacion.dao.ClientesDao;
import com.tomyling.facturacion.dao.UsuarioDao;
import com.tomyling.facturacion.modelo.Clientes;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;


/**
 *
 * @author new user
 */
@LocalBean
@Stateless
public class ClientesServicio extends ClientesDao
{
   public void creaCliente(Clientes cli)
   {
       try
       {
          this.create(cli);
          
          FacesMessage msjsi=new FacesMessage();
          msjsi.setSeverity(FacesMessage.SEVERITY_INFO);
          msjsi.setSummary("Cliente guardado correctamente");
          
          FacesContext.getCurrentInstance().addMessage("men", msjsi);
       } 
       catch(Exception e)
       {
          FacesMessage msjno=new FacesMessage();
          msjno.setSeverity(FacesMessage.SEVERITY_INFO);
          msjno.setSummary("Cliente guardado correctamente");
          
          FacesContext.getCurrentInstance().addMessage("men", msjno);
       }
   } 
   
   public List<Clientes> listaDeClientes()
   { 
       List<Clientes> listaClientes;
       listaClientes=this.listaTodosClientes();
       if(listaClientes == null || listaClientes.isEmpty())
       {  
           return null;
       }
       else
       {
           return listaClientes;
       }    
      
   }

    
    
}
