/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sena.facade.neweasyjob;

import edu.sena.entity.neweasyjob.Rol;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author user
 */
@Local
public interface RolFacadeLocal {

    void create(Rol rol);

    void edit(Rol rol);

    void remove(Rol rol);

    Rol find(Object id);

    List<Rol> findAll();

    List<Rol> findRange(int[] range);

    int count();

    public boolean addRol(int usuarioid, int rolid);

    public boolean removerRol(int usuarioid, int rolid);
    
}
