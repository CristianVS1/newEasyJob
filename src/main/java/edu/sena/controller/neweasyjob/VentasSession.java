/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sena.controller.neweasyjob;

import edu.sena.entity.neweasyjob.Venta;
import edu.sena.facade.neweasyjob.DetalleventaFacade;
import edu.sena.facade.neweasyjob.DetalleventaFacadeLocal;
import edu.sena.facade.neweasyjob.VentaFacadeLocal;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import org.primefaces.PrimeFaces;

/**
 *
 * @author user
 */
@Named(value = "ventasSession")
@SessionScoped
public class VentasSession implements Serializable {

    private Venta ventaTemp = new Venta();
    
    @EJB
    VentaFacadeLocal ventaFacadeLocal;
    @EJB
    DetalleventaFacadeLocal detalleventaFacadeLocal;
    
    public VentasSession() {
    }
    
    public List<Venta> todasVentas(){
    return ventaFacadeLocal.allVentas();
    }
    
    public void detalleVenta(Venta ventaT){
        try {
            detalleventaFacadeLocal.detalleId(ventaT.getId());
        } catch (Exception e) {
            PrimeFaces.current().executeScript("Swal.fire({\n"
                            + "  title: 'Error',\n"
                            + "  text: 'actualmente no se puede realizar la petición, intente mas tarde',\n"
                            + "  icon: 'warning',\n"
                            + "  confirmButtonText: 'Aceptar'\n"
                            + "})");
        }
    }

    public Venta getVentaTemp() {
        return ventaTemp;
    }

    public void setVentaTemp(Venta ventaTemp) {
        this.ventaTemp = ventaTemp;
    }
}
