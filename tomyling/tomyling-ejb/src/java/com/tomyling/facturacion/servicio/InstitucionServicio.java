/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomyling.facturacion.servicio;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import com.tomyling.facturacion.dao.InstitucionDao;
import com.tomyling.facturacion.modelo.Institucion;
import com.tomyling.facturacion.modelo.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

/**
 *
 * @author new user
 */
@LocalBean
@Stateless
public class InstitucionServicio extends InstitucionDao 
{

    public List<SelectItem> listarInstituciones() {

        List<SelectItem> items = new ArrayList<>();
        List<Institucion> listaInstitucion;
        items.add(new SelectItem(null, "Seleccione una empresa"));
        listaInstitucion = this.listarTodasInstituciones();
        if (listaInstitucion == null || listaInstitucion.isEmpty()) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No existen datos de Instituciones.!", ""));
        }
        else{
            for(Institucion i : listaInstitucion)
            {
                items.add(new SelectItem(i.getIdInstitucion(), i.getNombre()));
            }
        }
        return items;
    }
    
   public void crearInstitucion(Institucion inst) 
    {
        try
        {    
            //Ingresa a InstitucionDao (se extiende hereda)
            this.create(inst);
            
            FacesMessage msjsi = new FacesMessage();
            msjsi.setSeverity(FacesMessage.SEVERITY_INFO);
            msjsi.setSummary("Institución creada..");
            
            FacesContext.getCurrentInstance().addMessage("men", msjsi);
        }
         catch(Exception e)
         {      
            FacesMessage msjno = new FacesMessage();
            msjno.setSeverity(FacesMessage.SEVERITY_INFO);
            msjno.setSummary("Institución creada..");
            
            FacesContext.getCurrentInstance().addMessage("men", msjno);
         } 
    } 
   
   public List<Institucion> cargaInstitucion()
   {
       List<Institucion> listaInst;
       listaInst=this.listaTodasInstitucion();
       if(listaInst == null || listaInst.isEmpty())
       {
           return null;
       }
       else
       {
           return listaInst;
       }
   
   } 
   
   public void eliminaInstitucion(Institucion selectInstitucion)
   {
        try
        { 
           this.remove(selectInstitucion);
           
           FacesMessage msjsi=new FacesMessage();
           msjsi.setSeverity(FacesMessage.SEVERITY_INFO);
           msjsi.setSummary("Eliminada Institución..");
           
           FacesContext.getCurrentInstance().addMessage("men", msjsi);
        }   
        catch(Exception e)   
        { 
           FacesMessage msjno=new FacesMessage();
           msjno.setSeverity(FacesMessage.SEVERITY_INFO);
           msjno.setSummary("No se elimino  Institución..");
           
           FacesContext.getCurrentInstance().addMessage("men", msjno);
            
        
        }   
   }
   
   public void editarInstitucion(Institucion selectInstitucion)
    {
      this.edit(selectInstitucion);
    }
}
