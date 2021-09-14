/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sena.controller.neweasyjob;

import edu.sena.entity.neweasyjob.Producto;
import edu.sena.entity.neweasyjob.Referencia;
import edu.sena.facade.neweasyjob.ProductoFacadeLocal;
import edu.sena.facade.neweasyjob.ReferenciaFacadeLocal;
import java.io.File;
import java.io.InputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.http.Part;
import org.primefaces.PrimeFaces;
import org.primefaces.shaded.commons.io.FilenameUtils;

/**
 *
 * @author user
 */
@Named(value = "inventarioSession")
@SessionScoped
public class InventarioSession implements Serializable {

    @EJB
    ProductoFacadeLocal productoFacadeLocal;

    @EJB
    ReferenciaFacadeLocal referenciaFacadeLocal;

    private Part fotoProducto;
    private Producto productoIn = new Producto();
    private Producto productoT = new Producto();
    private Referencia refTemporal = new Referencia();
    private Referencia refNew = new Referencia();
    private Producto prodTest = new Producto();

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");

    public InventarioSession() {
    }

    public void actualizarImgProducto(Producto productoIt) {
        try {

            if (fotoProducto != null) {
                if (fotoProducto.getSize() > 900000) {
                    PrimeFaces.current().executeScript("Swal.fire({"
                            + "  title: 'Error!',"
                            + "  text: 'No se puede cargar este archivo, pór su tamaño',"
                            + "  icon: 'error',"
                            + "  confirmButtonText: 'Por favor intente mas tarde'"
                            + "})");
                } else if (fotoProducto.getContentType().equalsIgnoreCase("image/jpeg") || fotoProducto.getContentType().equalsIgnoreCase("image/png")) {

                    File carpeta = new File("C:/Users/user/Documents/NetBeansProjects/ImgnewEasyJob/Fotos/Productos");
                    if (!carpeta.exists()) {
                        carpeta.mkdirs();
                    }
                    try (InputStream is = fotoProducto.getInputStream()) {
                        Calendar hoy = Calendar.getInstance();
                        String renombrar = sdf.format(hoy.getTime()) + ".";
                        renombrar += FilenameUtils.getExtension(fotoProducto.getSubmittedFileName());
                        Files.copy(is, (new File(carpeta, renombrar)).toPath(), StandardCopyOption.REPLACE_EXISTING);
                        productoIt.setFotoProducto(renombrar);
                        productoFacadeLocal.edit(productoIt);
                        PrimeFaces.current().executeScript("Swal.fire({"
                                + "  title: 'Imagen de producto Actualizada !',"
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

    public List<Referencia> todosProductos() {
        return referenciaFacadeLocal.todasReferencias();
    }

    public void referenciaTemporal(Referencia ref) {
        this.refTemporal = ref;
    }

    public void actualizarCantidadref() {
        try {
            refTemporal.setCantidaDisponible(refTemporal.getCantidaDisponible()+refNew.getCantidaDisponible());
            referenciaFacadeLocal.edit(refTemporal);
            PrimeFaces.current().executeScript("Swal.fire({\n"
                    + "  title: 'Producto Actualizado!',\n"
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

    public boolean newProducto() {
        try {
            productoFacadeLocal.create(productoIn);
            refNew.setProducto(productoIn);
            referenciaFacadeLocal.create(refNew);
            if (refNew != null) {
                refNew = new Referencia();
                PrimeFaces.current().executeScript("Swal.fire({\n"
                        + "  title: 'Registrado!',\n"
                        + "  text: 'Producto registrado con exito',\n"
                        + "  icon: 'success',\n"
                        + "  confirmButtonText: 'Aceptar'\n"
                        + "})");
            }
            return true;
        } catch (Exception e) {
            PrimeFaces.current().executeScript("Swal.fire({\n"
                    + "  title: 'Error!',\n"
                    + "  text: 'Error al registrar el producto',\n"
                    + "  icon: 'error',\n"
                    + "  confirmButtonText: 'Aceptar'\n"
                    + "})");
            return false;
        }

    }
     
    public Part getFotoProducto() {
        return fotoProducto;
    }
    
    public void setFotoProducto(Part fotoProducto) {
        this.fotoProducto = fotoProducto;
    }

    public Producto getProductoIn() {
        return productoIn;
    }

    public void setProductoIn(Producto productoIn) {
        this.productoIn = productoIn;
    }

    public Referencia getRefNew() {
        return refNew;
    }

    public void setRefNew(Referencia refNew) {
        this.refNew = refNew;
    }

    public Producto getProdTest() {
        return prodTest;
    }

    public void setProdTest(Producto prodTest) {
        this.prodTest = prodTest;
    }

    public Producto getProductoT() {
        return productoT;
    }

    public void setProductoT(Producto productoT) {
        this.productoT = productoT;
    }

    public Referencia getRefTemporal() {
        return refTemporal;
    }

    public void setRefTemporal(Referencia refTemporal) {
        this.refTemporal = refTemporal;
    }

}
