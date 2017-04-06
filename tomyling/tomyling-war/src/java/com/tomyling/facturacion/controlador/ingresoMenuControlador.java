/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomyling.facturacion.controlador;

import com.tomyling.facturacion.modelo.Menu;
import static com.tomyling.facturacion.modelo.Menu_.idMenu;
import com.tomyling.facturacion.modelo.RolMenu;
import com.tomyling.facturacion.modelo.RolMenuPK;
import com.tomyling.facturacion.modelo.UsuRolPK;
import com.tomyling.facturacion.modelo.Usuario;
import com.tomyling.facturacion.servicio.MenuServicio;
import com.tomyling.facturacion.servicio.RolMenuServicio;
import com.tomyling.facturacion.servicio.UsuarioServicio;
import com.tomyling.facturacion.utilitarios.Utilitarios;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author EDWIN VACA
 * 
 */
@ManagedBean
@ViewScoped
public class ingresoMenuControlador extends Utilitarios implements Serializable
{
    private Integer ID_MENU;
    private String nombreMenu;
    private Boolean estadoMenu;
    private Integer niveMenu;
    private Integer padreMenu;
    private String iconoMenu;
    private String urlMenu;
    
    private InicioControlador inicio;
    private String usuario;
    private String clave;
    
    private RolMenu rolMenu;
    
    private Menu menu;
    private List<Menu> listMenu;
    
    @EJB
    private MenuServicio menuServicio;
    
    @EJB
    private UsuarioServicio usuarioServicio;
    
    @EJB
    private RolMenuServicio usurolpkServicio;
    
    @PostConstruct
    public void inicia()
    {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        this.inicio = (InicioControlador) session.getAttribute("inicioControlador");
        
    //*****************************************************************************************************************
        this.listMenu=new ArrayList<>();
        llenaListaMenu();
        ultimoId();
        usuario = this.inicio.getNombre();
        clave = this.inicio.getClave();
    }        
    
    public void creaMenu()
    {
        Menu menues = new Menu();
        menues.setIdMenu(ID_MENU);
        menues.setNombreMenu(this.nombreMenu);
        menues.setEstadoMenu(true);
        menues.setPadre(this.padreMenu);
        menues.setNivel(this.niveMenu); 
        menues.setIcono(this.iconoMenu);
        menues.setUrl(this.urlMenu);
        //conseguir ultimo id del menu        
        //conseguir id rol de usuario
        Usuario u = usuarioServicio.existeUsuario(usuario, clave);
        Integer idUsuario=u.getIdUsuario();
     //   UsuRolPK usuarioRol =  (UsuRolPK) usurolpkServicio.consultarRolesPorUsuario(u.getIdUsuario());
        UsuRolPK usuarioRol =  (UsuRolPK) usurolpkServicio.obtenerRolMenu(idUsuario); 
      // this.menuServicio.guardaMenu(menues);
     
       RolMenuPK rm = new RolMenuPK(); 
       rm.setIdMenu(ID_MENU);
        rm.setIdRol(usuarioRol.getIdRol());
       //    this.rolmenuServicio.guardarRolMenu(rm);  
    }        
    
    public void llenaListaMenu()
    {       
        listMenu=this.menuServicio.consultaMenu();        
    }
    
    public void ultimoId()
    {
        Menu men=new Menu();
        men=listMenu.get(listMenu.size()-1);
        int ultimo=men.getIdMenu()+1;
        this.ID_MENU=ultimo;
        this.estadoMenu=true;
    }        
    //getters y setters

    public String getNombreMenu() {
        return nombreMenu;
    }

    public void setNombreMenu(String nombreMenu) {
        this.nombreMenu = nombreMenu;
    }

    public Boolean getEstadoMenu() {
        return estadoMenu;
    }

    public void setEstadoMenu(Boolean estadoMenu) {
        this.estadoMenu = estadoMenu;
    }

    public Integer getNiveMenu() {
        return niveMenu;
    }

    public void setNiveMenu(Integer niveMenu) {
        this.niveMenu = niveMenu;
    }

    public Integer getPadreMenu() {
        return padreMenu;
    }

    public void setPadreMenu(Integer padreMenu) {
        this.padreMenu = padreMenu;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public List<Menu> getListMenu() {
        return listMenu;
    }

    public void setListMenu(List<Menu> listMenu) {
        this.listMenu = listMenu;
    }

    public String getIconoMenu() {
        return iconoMenu;
    }

    public void setIconoMenu(String iconoMenu) {
        this.iconoMenu = iconoMenu;
    }

    public String getUrlMenu() {
        return urlMenu;
    }

    public void setUrlMenu(String urlMenu) {
        this.urlMenu = urlMenu;
    }

    public Integer getID_MENU() {
        return ID_MENU;
    }

    public void setID_MENU(Integer ID_MENU) {
        this.ID_MENU = ID_MENU;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    
    
    
}
