/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sena.facade.neweasyjob;

import edu.sena.entity.neweasyjob.Detallecliente;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author user
 */
@Local
public interface DetalleclienteFacadeLocal {

    void create(Detallecliente detallecliente);

    void edit(Detallecliente detallecliente);

    void remove(Detallecliente detallecliente);

    Detallecliente find(Object id);

    List<Detallecliente> findAll();

    List<Detallecliente> findRange(int[] range);

    int count();
    
}
