/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sena.facade.neweasyjob;

import edu.sena.entity.neweasyjob.VistasEmpleado;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author user
 */
@Local
public interface VistasEmpleadoFacadeLocal {

    void create(VistasEmpleado vistasEmpleado);

    void edit(VistasEmpleado vistasEmpleado);

    void remove(VistasEmpleado vistasEmpleado);

    VistasEmpleado find(Object id);

    List<VistasEmpleado> findAll();

    List<VistasEmpleado> findRange(int[] range);

    int count();
    
}
