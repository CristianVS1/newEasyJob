/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sena.controller.neweasyjob;

import edu.sena.entity.neweasyjob.Cargo;
import edu.sena.entity.neweasyjob.Ciudad;
import edu.sena.entity.neweasyjob.Contacto;
import edu.sena.entity.neweasyjob.DatosPersonales;
import edu.sena.entity.neweasyjob.Departamento;
import edu.sena.entity.neweasyjob.Direccion;
import edu.sena.entity.neweasyjob.Empleado;
import edu.sena.entity.neweasyjob.Rol;
import edu.sena.entity.neweasyjob.Usuario;
import edu.sena.facade.neweasyjob.CargoFacadeLocal;
import edu.sena.facade.neweasyjob.CiudadFacadeLocal;
import edu.sena.facade.neweasyjob.ContactoFacadeLocal;
import edu.sena.facade.neweasyjob.DatosPersonalesFacadeLocal;
import edu.sena.facade.neweasyjob.DepartamentoFacadeLocal;
import edu.sena.facade.neweasyjob.DireccionFacadeLocal;
import edu.sena.facade.neweasyjob.EmpleadoFacadeLocal;
import edu.sena.facade.neweasyjob.RolFacadeLocal;
import edu.sena.facade.neweasyjob.UsuarioFacadeLocal;
import edu.sena.utility.neweasyjob.Mail;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;
import org.primefaces.PrimeFaces;
import org.primefaces.shaded.commons.io.FilenameUtils;

/**
 *
 * @author user
 */
@Named(value = "usuarioSession")
@SessionScoped
public class UsuarioSession implements Serializable {

    @EJB
    UsuarioFacadeLocal usuarioFacadeLocal;

    @EJB
    CiudadFacadeLocal ciudadFacadeLocal;

    @EJB
    DepartamentoFacadeLocal departamentoFacadeLocal;

    @EJB
    DireccionFacadeLocal direccionFacadeLocal;

    @EJB
    ContactoFacadeLocal contactoFacadeLocal;

    @EJB
    DatosPersonalesFacadeLocal datosPersonalesFacadeLocal;

    @EJB
    RolFacadeLocal rolFacadeLocal;

    @EJB
    CargoFacadeLocal cargoFacadeLocal;
    
    @EJB
    EmpleadoFacadeLocal empleadoFacadeLocal;
    
    private Usuario usuLogin = new Usuario();
    private String usuario = "";
    private String contrasena = "";
    private Usuario usuReg = new Usuario();
    private Usuario usuTemporal = new Usuario();
    private int deptIn = 0;
    private Ciudad ciudadIn = new Ciudad();
    private Direccion direccionIn = new Direccion();
    private Contacto contactoIn = new Contacto();
    private int rolIn = 0;
    private DatosPersonales datosIn = new DatosPersonales();
    private Part fotoPerfil;
    private Rol rolTest = new Rol();
    private Empleado empleadotest = new Empleado();
    private Cargo cargoTest = new Cargo();
    private List<Rol> todosRoles = new ArrayList<>();
    private List<Rol> rolesSinAsignar = new ArrayList<>();
    private List<Cargo> todosCargos = new ArrayList<>();
    private List<Cargo> cargosSinAsignar = new ArrayList<>();

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");

    public UsuarioSession() {
    }
    
    @PostConstruct
    public void cargaInicial() {
        todosRoles.addAll(rolFacadeLocal.findAll());
        todosCargos.addAll(cargoFacadeLocal.findAll());
    }
    
    

    public List<Departamento> allDepartamentos() {
        return departamentoFacadeLocal.findAll();
    }

    public List<Rol> allRol() {
        return rolFacadeLocal.findAll();
    }

    public void iniciarSesion() {
        try {
            usuLogin = usuarioFacadeLocal.usuLogin1(usuario, contrasena);
            if (usuLogin != null) {
                if (usuLogin.getEstado() == 1) {
                    FacesContext fc = FacesContext.getCurrentInstance();
                    fc.getExternalContext().redirect("Usuario/index.xhtml");
                } else {
                    PrimeFaces.current().executeScript("Swal.fire({\n"
                            + "  title: 'Acceso Denegado!',\n"
                            + "  text: 'usuario inactivo, comuniquese con el administrador',\n"
                            + "  icon: 'warning',\n"
                            + "  confirmButtonText: 'Aceptar'\n"
                            + "})");
                }

            } else {
                PrimeFaces.current().executeScript("Swal.fire({\n"
                        + "  title: 'Error!',\n"
                        + "  text: 'usuario no encontrado',\n"
                        + "  icon: 'error',\n"
                        + "  confirmButtonText: 'Intente de nuevo'\n"
                        + "})");
            }
        } catch (Exception e) {
        }
    }

    public void cerrarSesion() throws IOException {
        usuLogin = null;
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.getExternalContext().invalidateSession();
        fc.getExternalContext().redirect("../index.xhtml");

    }

    public void validarUsuarioActivo() throws IOException {
        if (usuLogin == null || usuLogin.getNomUser() == null) {
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.getExternalContext().invalidateSession();
            fc.getExternalContext().redirect("../index.xhtml");
        }
    }

    public boolean newUser() {
        try {
            Departamento dep = departamentoFacadeLocal.find(deptIn);
            ciudadIn.setDepartamentoId(dep);
            ciudadFacadeLocal.create(ciudadIn);
            direccionIn.setCiudadId(ciudadIn);
            direccionFacadeLocal.create(direccionIn);
            contactoIn.setDireccionId(direccionIn);
            contactoFacadeLocal.create(contactoIn);
            datosPersonalesFacadeLocal.create(datosIn);
            usuReg.setContactoId(contactoIn);
            usuReg.setDatosId(datosIn);
            usuReg.setEstado(Short.parseShort("1"));
            usuarioFacadeLocal.create(usuReg);
            if (usuReg != null) {
                usuReg = new Usuario();
                ciudadIn = new Ciudad();
                contactoIn = new Contacto();
                direccionIn = new Direccion();
                datosIn = new DatosPersonales();
                PrimeFaces.current().executeScript("Swal.fire({\n"
                        + "  title: 'Registrado!',\n"
                        + "  text: 'usuario registrado con exito',\n"
                        + "  icon: 'success',\n"
                        + "  confirmButtonText: 'Aceptar'\n"
                        + "})");
            }
            return true;
        } catch (Exception e) {
            PrimeFaces.current().executeScript("Swal.fire({\n"
                    + "  title: 'Error!',\n"
                    + "  text: 'usuario no registrado',\n"
                    + "  icon: 'error',\n"
                    + "  confirmButtonText: 'Verifique la informacion'\n"
                    + "})");
            return false;
        }
        
    }

    public void recuperarClave() {
        usuReg = null;
        usuReg = usuarioFacadeLocal.claveNueva(usuario);

        double aleatorio = 100000 * Math.random();
        usuReg.setContrasena("$$"+usuReg.getNomUser() + (int) aleatorio + "$$");
        usuarioFacadeLocal.edit(usuReg);

        if (usuReg != null) {
            try {

                Mail.recuperarClave(usuario, usuReg.getContactoId().getCorreoElectronico(), usuReg.getContrasena());

                PrimeFaces.current().executeScript("Swal.fire({\n"
                        + "  title: 'Correo Enviado!',\n"
                        + "  text: 'Por favor verifique su bandeja de entrada',\n"
                        + "  icon: 'success',\n"
                        + "  confirmButtonText: 'Ok'\n"
                        + "})");
            } catch (Exception e) {
                PrimeFaces.current().executeScript("Swal.fire({\n"
                        + "  title: 'Error!',\n"
                        + "  text: 'Lo sentimos, en el momento no se puede realizar la operacion',\n"
                        + "  icon: 'error',\n"
                        + "  confirmButtonText: 'Intente mas tarde'\n"
                        + "})");
            }

        } else {
            PrimeFaces.current().executeScript("Swal.fire({\n"
                    + "  title: 'Error!',\n"
                    + "  text: 'usuario no encontrado',\n"
                    + "  icon: 'error',\n"
                    + "  confirmButtonText: 'Valide el usuario registrado'\n"
                    + "})");
        }
    }

    public List<Usuario> todosUsuarios() {
        return usuarioFacadeLocal.ListaUsuarios();
    }
    
    public void actualizarDatosTemporal(){
        try {
            Ciudad ciudadT = usuTemporal.getContactoId().getDireccionId().getCiudadId();
            ciudadT.setCiudad(ciudadIn.getCiudad());
            ciudadFacadeLocal.edit(ciudadT);
            Direccion direccionT = usuTemporal.getContactoId().getDireccionId();
            direccionT.setDireccion(direccionIn.getDireccion());
            direccionFacadeLocal.edit(direccionT);
            Contacto contacT = usuTemporal.getContactoId();
            contacT.setCorreoElectronico(contactoIn.getCorreoElectronico());
            contacT.setTelefono(contactoIn.getTelefono());
            contactoFacadeLocal.edit(contacT);
            DatosPersonales DatosT = usuTemporal.getDatosId();
            DatosT.setPrimerNombre(datosIn.getPrimerNombre());
            DatosT.setSegundoNombre(datosIn.getSegundoNombre());
            DatosT.setPrimerApellido(datosIn.getPrimerApellido());
            DatosT.setSegundoApellido(datosIn.getSegundoApellido());
            datosPersonalesFacadeLocal.edit(DatosT);
            usuarioFacadeLocal.edit(usuTemporal);
            PrimeFaces.current().executeScript("Swal.fire({\n"
                    + "  title: 'Usuario Actualizado!',\n"
                    + "  text: 'La información ah sido actualizada con exito',\n"
                    + "  icon: 'success',\n"
                    + "  confirmButtonText: 'Aceptar'\n"
                    + "})");
        } catch (Exception e) {
            PrimeFaces.current().executeScript("Swal.fire({\n"
                    + "  title: 'Error!',\n"
                    + "  text: 'Lo sentimos, en el momento no se puede realizar la operacion',\n"
                    + "  icon: 'error',\n"
                    + "  confirmButtonText: 'Intente mas tarde'\n"
                    + "})");
        }
    }

    public void actualizarDatosBasicos() {
        
        try {
            DatosPersonales DatosA = usuLogin.getDatosId();
            DatosA.setPrimerNombre(datosIn.getPrimerNombre());
            DatosA.setSegundoNombre(datosIn.getSegundoNombre());
            DatosA.setPrimerApellido(datosIn.getPrimerApellido());
            DatosA.setSegundoApellido(datosIn.getSegundoApellido());
            datosPersonalesFacadeLocal.edit(DatosA);
            usuarioFacadeLocal.edit(usuLogin);
            PrimeFaces.current().executeScript("Swal.fire({\n"
                    + "  title: 'Usuario Actualizado!',\n"
                    + "  text: 'La información ah sido actualizada con exito',\n"
                    + "  icon: 'success',\n"
                    + "  confirmButtonText: 'Aceptar'\n"
                    + "})");
        } catch (Exception e) {
            PrimeFaces.current().executeScript("Swal.fire({\n"
                    + "  title: 'Error!',\n"
                    + "  text: 'Lo sentimos, en el momento no se puede realizar la operacion',\n"
                    + "  icon: 'error',\n"
                    + "  confirmButtonText: 'Intente mas tarde'\n"
                    + "})");
        }

    }
    public void actualizarDatosContacto() {
        try {
            Contacto contacA = usuLogin.getContactoId();
            Direccion direccionA = usuLogin.getContactoId().getDireccionId();
            direccionA.setDireccion(direccionIn.getDireccion());
            contacA.setCorreoElectronico(contactoIn.getCorreoElectronico());
            contacA.setTelefono(contactoIn.getTelefono());
            direccionFacadeLocal.edit(direccionA);
            contactoFacadeLocal.edit(contacA);
            PrimeFaces.current().executeScript("Swal.fire({\n"
                    + "  title: 'Usuario Actualizado!',\n"
                    + "  text: 'La información ah sido actualizada con exito',\n"
                    + "  icon: 'success',\n"
                    + "  confirmButtonText: 'Aceptar'\n"
                    + "})");
        } catch (Exception e) {
            PrimeFaces.current().executeScript("Swal.fire({\n"
                    + "  title: 'Error!',\n"
                    + "  text: 'Lo sentimos, en el momento no se puede realizar la operacion',\n"
                    + "  icon: 'error',\n"
                    + "  confirmButtonText: 'Intente mas tarde'\n"
                    + "})");
        }

    }

    public void actualizarMifoto() {
        try {

            if (fotoPerfil != null) {
                if (fotoPerfil.getSize() > 900000) {
                    PrimeFaces.current().executeScript("Swal.fire({"
                            + "  title: 'Error!',"
                            + "  text: 'No se puede cargar este archivo, pór su tamaño',"
                            + "  icon: 'error',"
                            + "  confirmButtonText: 'Por favor intente mas tarde'"
                            + "})");
                } else if (fotoPerfil.getContentType().equalsIgnoreCase("image/jpeg") || fotoPerfil.getContentType().equalsIgnoreCase("image/png")) {

                    File carpeta = new File("C:/Users/user/Documents/NetBeansProjects/ImgnewEasyJob/Fotos/Usuarios");
                    if (!carpeta.exists()) {
                        carpeta.mkdirs();
                    }
                    try (InputStream is = fotoPerfil.getInputStream()) {
                        Calendar hoy = Calendar.getInstance();
                        String renombrar = sdf.format(hoy.getTime()) + ".";
                        renombrar += FilenameUtils.getExtension(fotoPerfil.getSubmittedFileName());
                        Files.copy(is, (new File(carpeta, renombrar)).toPath(), StandardCopyOption.REPLACE_EXISTING);
                        usuLogin.setFoto(renombrar);
                        usuarioFacadeLocal.edit(usuLogin);
                        PrimeFaces.current().executeScript("Swal.fire({"
                                + "  title: 'Imagen de perfil Actualizada !',"
                                + "  text: 'Con Exito !!!',"
                                + "  icon: 'success',"
                                + "  confirmButtonText: 'Ok'"
                                + "})");
                        PrimeFaces.current().executeScript("document.getElementById('formReset').click()");

                    } catch (Exception e) {
                        PrimeFaces.current().executeScript("Swal.fire({"
                                + "  title: 'Error!',"
                                + "  text: 'No se puede realizar esta peticion',"
                                + "  icon: 'error',"
                                + "  confirmButtonText: 'Por favor intente mas tarde'"
                                + "})");

                    }

                } else {
                    PrimeFaces.current().executeScript("Swal.fire({"
                            + "  title: 'Error!',"
                            + "  text: 'Tipo de archivo no permitido, recuerde la extencion es "
                            + ".jpeg o .png',"
                            + "  icon: 'error',"
                            + "  confirmButtonText: 'Por favor intente mas tarde'"
                            + "})");
                }

            } else {
                PrimeFaces.current().executeScript("Swal.fire({"
                        + "  title: 'Error!',"
                        + "  text: 'No se puede realizar esta peticion',"
                        + "  icon: 'error',"
                        + "  confirmButtonText: 'Por favor intente mas tarde'"
                        + "})");

            }

        } catch (Exception e) {
            PrimeFaces.current().executeScript("Swal.fire({"
                    + "  title: 'Error!',"
                    + "  text: 'No se puede realizar esta peticion',"
                    + "  icon: 'error',"
                    + "  confirmButtonText: 'Por favor intente mas tarde'"
                    + "})");
        }

    }

    public void actualizaRoles() {
        rolesSinAsignar.clear();
        for (Rol rolPrincial : todosRoles) {
            boolean flag = true;
            for (Rol rolUsu : this.usuTemporal.getRolCollection()) {
                if (Objects.equals(rolPrincial.getRol(), rolUsu.getRol())) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                rolesSinAsignar.add(rolPrincial);
            }
        }
    }
    
    public void actualizarCargoEmpleado() {
        cargosSinAsignar.clear();
        for (Cargo cargoPrincipal : todosCargos) {
            boolean flag = true;
            for (Empleado usuEmpleado: this.usuTemporal.getEmpleadoCollection()) {
                if (Objects.equals(cargoPrincipal, usuEmpleado.getCargo())) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                cargosSinAsignar.add(cargoPrincipal);
            }
        }
    }
    
    

    public void cargaTemporal(Usuario usuIn) {
        this.usuTemporal = usuIn;
        actualizaRoles();

    }
    
    public void cargaTemporalEmpleado (Usuario usuIn){
        this.usuTemporal = usuIn;
        actualizarCargoEmpleado();
    }

    public void removerRol(int rolid) {
        try {
            rolFacadeLocal.removerRol(this.usuTemporal.getCedula(), rolid);
            this.usuTemporal = usuarioFacadeLocal.buscarUsuarioId(this.usuTemporal.getCedula());
            actualizaRoles();
        } catch (Exception e) {
            PrimeFaces.current().executeScript("Swal.fire({"
                    + "  title: 'Error!',"
                    + "  text: 'No se puede realizar esta peticion',"
                    + "  icon: 'error',"
                    + "  confirmButtonText: 'Por favor intente mas tarde'"
                    + "})");
        }
    }

    public void removerCargo(int cargoid) {
        try {
            empleadoFacadeLocal.removerCargo(this.usuTemporal.getCedula(), cargoid);
            this.usuTemporal = usuarioFacadeLocal.buscarUsuarioId(this.usuTemporal.getCedula());
            actualizarCargoEmpleado();
        } catch (Exception e) {
            PrimeFaces.current().executeScript("Swal.fire({"
                    + "  title: 'Error!',"
                    + "  text: 'No se puede realizar esta peticion',"
                    + "  icon: 'error',"
                    + "  confirmButtonText: 'Por favor intente mas tarde'"
                    + "})");
        }
    }
    
    public void addRol(int rolid) {
        try {
            rolFacadeLocal.addRol(this.usuTemporal.getCedula(), rolid);
            this.usuTemporal = usuarioFacadeLocal.buscarUsuarioId(this.usuTemporal.getCedula());
            actualizaRoles();
        } catch (Exception e) {
            PrimeFaces.current().executeScript("Swal.fire({"
                    + "  title: 'Error!',"
                    + "  text: 'No se puede realizar esta peticion',"
                    + "  icon: 'error',"
                    + "  confirmButtonText: 'Por favor intente mas tarde'"
                    + "})");
        }

    }
    
    public void addCargo(int cargoid) {
        try {
            empleadoFacadeLocal.addCargo(this.usuTemporal.getCedula(), cargoid);
            this.usuTemporal = usuarioFacadeLocal.buscarUsuarioId(this.usuTemporal.getCedula());
            actualizarCargoEmpleado();
        } catch (Exception e) {
            PrimeFaces.current().executeScript("Swal.fire({"
                    + "  title: 'Error!',"
                    + "  text: 'No se puede realizar esta peticion',"
                    + "  icon: 'error',"
                    + "  confirmButtonText: 'Por favor intente mas tarde'"
                    + "})");
        }

    }

    public void cambiarEstado(Usuario usuIn) {
        try {
            if (usuIn.getEstado().equals(Short.parseShort("1"))) {
                usuIn.setEstado(Short.parseShort("0"));
            } else {
                usuIn.setEstado(Short.parseShort("1"));
            }
            usuarioFacadeLocal.edit(usuIn);

        } catch (Exception e) {
            PrimeFaces.current().executeScript("Swal.fire({"
                    + "  title: 'Error!',"
                    + "  text: 'No se puede realizar esta peticion',"
                    + "  icon: 'error',"
                    + "  confirmButtonText: 'Por favor intente mas tarde'"
                    + "})");
        }
    }
    
    public Usuario getUsuLogin() {
        return usuLogin;
    }

    public void setUsuLogin(Usuario usuLogin) {
        this.usuLogin = usuLogin;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Usuario getUsuReg() {
        return usuReg;
    }

    public void setUsuReg(Usuario usuReg) {
        this.usuReg = usuReg;
    }

    public int getDeptIn() {
        return deptIn;
    }

    public void setDeptIn(int deptIn) {
        this.deptIn = deptIn;
    }

    public Ciudad getCiudadIn() {
        return ciudadIn;
    }

    public void setCiudadIn(Ciudad ciudadIn) {
        this.ciudadIn = ciudadIn;
    }

    public Direccion getDireccionIn() {
        return direccionIn;
    }

    public void setDireccionIn(Direccion direccionIn) {
        this.direccionIn = direccionIn;
    }

    public Contacto getContactoIn() {
        return contactoIn;
    }

    public void setContactoIn(Contacto contactoIn) {
        this.contactoIn = contactoIn;
    }

    public int getRolIn() {
        return rolIn;
    }

    public void setRolIn(int rolIn) {
        this.rolIn = rolIn;
    }

    public DatosPersonales getDatosIn() {
        return datosIn;
    }

    public void setDatosIn(DatosPersonales datosIn) {
        this.datosIn = datosIn;
    }

    public Part getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(Part fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

    public Rol getRolTest() {
        return rolTest;
    }

    public void setRolTest(Rol rolTest) {
        this.rolTest = rolTest;
    }

    public Empleado getEmpleadotest() {
        return empleadotest;
    }

    public void setEmpleadotest(Empleado empleadotest) {
        this.empleadotest = empleadotest;
    }

    public Cargo getCargoTest() {
        return cargoTest;
    }

    public void setCargoTest(Cargo cargoTest) {
        this.cargoTest = cargoTest;
    }

    public Usuario getUsuTemporal() {
        return usuTemporal;
    }

    public void setUsuTemporal(Usuario usuTemporal) {
        this.usuTemporal = usuTemporal;
    }

    public List<Rol> getTodosRoles() {
        return todosRoles;
    }

    public void setTodosRoles(List<Rol> todosRoles) {
        this.todosRoles = todosRoles;
    }

    public List<Rol> getRolesSinAsignar() {
        return rolesSinAsignar;
    }

    public void setRolesSinAsignar(List<Rol> rolesSinAsignar) {
        this.rolesSinAsignar = rolesSinAsignar;
    }

    public List<Cargo> getTodosCargos() {
        return todosCargos;
    }

    public void setTodosCargos(List<Cargo> todosCargos) {
        this.todosCargos = todosCargos;
    }

    public List<Cargo> getCargosSinAsignar() {
        return cargosSinAsignar;
    }

    public void setCargosSinAsignar(List<Cargo> cargosSinAsignar) {
        this.cargosSinAsignar = cargosSinAsignar;
    }

}
