/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sena.facade.neweasyjob;

import edu.sena.entity.neweasyjob.Inventario;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author user
 */
@Local
public interface InventarioFacadeLocal {

    void create(Inventario inventario);

    void edit(Inventario inventario);

    void remove(Inventario inventario);

    Inventario find(Object id);

    List<Inventario> findAll();

    List<Inventario> findRange(int[] range);

    int count();
    
}
