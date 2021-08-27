/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sena.facade.neweasyjob;

import edu.sena.entity.neweasyjob.DatosPersonales;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author user
 */
@Local
public interface DatosPersonalesFacadeLocal {

    void create(DatosPersonales datosPersonales);

    void edit(DatosPersonales datosPersonales);

    void remove(DatosPersonales datosPersonales);

    DatosPersonales find(Object id);

    List<DatosPersonales> findAll();

    List<DatosPersonales> findRange(int[] range);

    int count();
    
}
