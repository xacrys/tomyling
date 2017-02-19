/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomyling.facturacion.utilitarios;

import java.io.IOException;
import java.io.Serializable;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author new user
 */
public class Utilitarios implements Serializable {

    protected FacesContext getContext(){
    return FacesContext.getCurrentInstance();
    }
    protected ExternalContext getExternalContext(){
        return getContext().getExternalContext();
    }
    protected HttpServletRequest getRequest(){
        return (HttpServletRequest) getExternalContext().getRequest();
    }
    protected void redirect(String url) throws IOException {
    getExternalContext().redirect(getRequest().getContextPath()+url);
    }
}
