/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sena.facade.neweasyjob;

import edu.sena.entity.neweasyjob.Rol;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author user
 */
@Stateless
public class RolFacade extends AbstractFacade<Rol> implements RolFacadeLocal {

    @PersistenceContext(unitName = "up_EasyJob_war")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RolFacade() {
        super(Rol.class);
    }
    
    @Override
    public boolean addRol(int usuarioid, int rolid) {
        try {
            Query q = em.createNativeQuery("INSERT INTO tbl_usuario_and_rol (cedula_id,rol_id) VALUES (?,?)");
            q.setParameter(1, usuarioid);
            q.setParameter(2, rolid);
            q.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean removerRol(int usuarioid, int rolid) {
        try {
            Query q = em.createNativeQuery("DELETE FROM tbl_usuario_and_rol WHERE cedula_id = ? AND rol_id = ?");
            q.setParameter(1, usuarioid);
            q.setParameter(2, rolid);
            q.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    
}
