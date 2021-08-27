/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sena.facade.neweasyjob;

import edu.sena.entity.neweasyjob.Referencia;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author user
 */
@Local
public interface ReferenciaFacadeLocal {

    void create(Referencia referencia);

    void edit(Referencia referencia);

    void remove(Referencia referencia);

    Referencia find(Object id);

    List<Referencia> findAll();

    List<Referencia> findRange(int[] range);

    int count();

    public List<Referencia> todasReferencias();
    
}
