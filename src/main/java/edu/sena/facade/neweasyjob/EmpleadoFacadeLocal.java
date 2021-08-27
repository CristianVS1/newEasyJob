/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sena.facade.neweasyjob;

import edu.sena.entity.neweasyjob.Empleado;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author user
 */
@Local
public interface EmpleadoFacadeLocal {

    void create(Empleado empleado);

    void edit(Empleado empleado);

    void remove(Empleado empleado);

    Empleado find(Object id);

    List<Empleado> findAll();

    List<Empleado> findRange(int[] range);

    int count();

    public boolean removerCargo(int usuarioid, int cargoid);

    public boolean addCargo(int usuarioid, int cargoid);
    
}
