/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sena.facade.neweasyjob;

import edu.sena.entity.neweasyjob.Estadosolicitud;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author user
 */
@Local
public interface EstadosolicitudFacadeLocal {

    void create(Estadosolicitud estadosolicitud);

    void edit(Estadosolicitud estadosolicitud);

    void remove(Estadosolicitud estadosolicitud);

    Estadosolicitud find(Object id);

    List<Estadosolicitud> findAll();

    List<Estadosolicitud> findRange(int[] range);

    int count();
    
}
