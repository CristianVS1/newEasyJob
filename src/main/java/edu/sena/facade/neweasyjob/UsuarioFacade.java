/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sena.facade.neweasyjob;

import edu.sena.entity.neweasyjob.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author user
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> implements UsuarioFacadeLocal {

    @PersistenceContext(unitName = "up_EasyJob_war")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    
    @Override
    public Usuario usuLogin1 (String usuario, String contrasena){
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            Query q = em.createQuery("SELECT u FROM Usuario u WHERE u.contrasena = :contrasena AND u.nomUser = :nomUser");
            q.setParameter("contrasena", contrasena);
            q.setParameter("nomUser", usuario);
            return (Usuario) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    
    @Override
    public Usuario claveNueva (String usuario){
        try {
            Query q = em.createQuery("SELECT u FROM Usuario u WHERE u.nomUser = :nomUser");
            q.setParameter("nomUser", usuario);
            return (Usuario) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    
    @Override
    public List<Usuario> ListaUsuarios (){
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            Query q = em.createQuery("SELECT u FROM Usuario u");
            return q.getResultList();
        } catch (Exception e) {
            return null;
        }
    
    }
    
    @Override
    public Usuario buscarUsuarioId (int usuarioId){
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            Query q = em.createQuery("SELECT u FROM Usuario u WHERE u.cedula = :usuarioId");
            q.setParameter("usuarioId", usuarioId);
            return (Usuario) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    
    }
}
