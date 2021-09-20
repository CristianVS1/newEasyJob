/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sena.facade.neweasyjob;

import edu.sena.entity.neweasyjob.Detalleventa;
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
public class DetalleventaFacade extends AbstractFacade<Detalleventa> implements DetalleventaFacadeLocal {

    @PersistenceContext(unitName = "up_EasyJob_war")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DetalleventaFacade() {
        super(Detalleventa.class);
    }

    @Override
    public List<Detalleventa> detalleId(int idVenta){
            try {
            em.getEntityManagerFactory().getCache().evictAll();
            Query q = em.createQuery("SELECT d FROM Detalleventa v WHERE v.venta = :idVenta");
            q.setParameter("idVenta", idVenta);
            return q.getResultList();
        } catch (Exception e) {
            return null;
        }
    

    }
}
