/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sena.controller.neweasyjob;

import edu.sena.entity.neweasyjob.Venta;
import edu.sena.facade.neweasyjob.VentaFacadeLocal;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author user
 */
@Named(value = "ventasSession")
@SessionScoped
public class VentasSession implements Serializable {

    @EJB
    VentaFacadeLocal ventaFacadeLocal;
    
    
    public VentasSession() {
    }
    
    public List<Venta> todasVentas(){
    return ventaFacadeLocal.allVentas();
    }
}
