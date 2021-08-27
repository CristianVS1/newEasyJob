/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sena.facade.neweasyjob;

import edu.sena.entity.neweasyjob.Usuario;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author user
 */
@Local
public interface UsuarioFacadeLocal {

    void create(Usuario usuario);

    void edit(Usuario usuario);

    void remove(Usuario usuario);

    Usuario find(Object id);

    List<Usuario> findAll();

    List<Usuario> findRange(int[] range);

    int count();

    public Usuario claveNueva(String usuario);

    public Usuario usuLogin1(String usuario, String contrasena);

    public List<Usuario> ListaUsuarios();

    public Usuario buscarUsuarioId(int usuarioId);
    
}
