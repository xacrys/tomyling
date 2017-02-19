/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tomyling.facturacion.dao;

import java.util.List;
import javax.persistence.Query;
import tomyling.facturacion.generico.Generico;
import tomyling.facturacion.modelo.Usuario;

/**
 *
 * @author new user
 */
public class UsuarioDao extends Generico<Usuario> {

    public UsuarioDao() {
        super(Usuario.class);
    }

    public Usuario validarExitenciaUsuario(String nombre, String clave) {
        Query query;
        List<Usuario> usuario;
        String jpql = "SELECT u FROM Usuario u WHERE u.usuario = :usuario and u.contrasenia = :clave ";
        query = getEntityManager().createQuery(jpql).setParameter("usuario", nombre).setParameter("clave", clave);
        usuario = query.getResultList();
        if (usuario == null || usuario.isEmpty()) {
            return null;
        } else {
            return usuario.get(0);
        }

    }

}
