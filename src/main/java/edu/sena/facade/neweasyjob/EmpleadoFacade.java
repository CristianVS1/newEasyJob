/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sena.facade.neweasyjob;

import edu.sena.entity.neweasyjob.Empleado;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author user
 */
@Stateless
public class EmpleadoFacade extends AbstractFacade<Empleado> implements EmpleadoFacadeLocal {

    @PersistenceContext(unitName = "up_EasyJob_war")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EmpleadoFacade() {
        super(Empleado.class);
    }
    
    
    @Override
    public boolean addCargo(int usuarioid, int cargoid) {
        try {
            Query q = em.createNativeQuery("INSERT INTO tbl_empleado (cargo_id, cedula) VALUES (?,?)");
            q.setParameter(1, cargoid);
            q.setParameter(2, usuarioid);
            q.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean removerCargo(int usuarioid, int cargoid) {
        try {
            Query q = em.createNativeQuery("DELETE FROM tbl_empleado WHERE cargo_id = ? AND cedula = ?");
            q.setParameter(1, cargoid);
            q.setParameter(2, usuarioid);
            q.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
