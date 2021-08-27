/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sena.controller.neweasyjob;

import edu.sena.entity.neweasyjob.Pedido;
import edu.sena.entity.neweasyjob.Proveedor;
import edu.sena.facade.neweasyjob.PedidoFacadeLocal;
import edu.sena.facade.neweasyjob.ProveedorFacadeLocal;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author user
 */
@Named(value = "pedidosYProveedorSession")
@SessionScoped
public class PedidosYProveedorSession implements Serializable {

    @EJB
    ProveedorFacadeLocal proveedorFacadeLocal;
    
    @EJB
    PedidoFacadeLocal pedidoFacadeLocal;
    
    
    
    
    public PedidosYProveedorSession() {
    }
    
    public List<Proveedor> todosProveedores(){
        return proveedorFacadeLocal.allProveedores();
    }
    
    public  List<Pedido> todosPedidos(){
        return pedidoFacadeLocal.allPedidos();
    }
    
}
