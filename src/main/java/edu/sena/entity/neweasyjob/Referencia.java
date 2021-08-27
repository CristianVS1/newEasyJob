/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sena.entity.neweasyjob;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author user
 */
@Entity
@Table(name = "tbl_referencia")
@NamedQueries({
    @NamedQuery(name = "Referencia.findAll", query = "SELECT r FROM Referencia r")})
public class Referencia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidaDisponible")
    private int cantidaDisponible;
    @Basic(optional = false)
    @NotNull
    @Column(name = "precioVenta")
    private int precioVenta;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "referencia", fetch = FetchType.LAZY)
    private Collection<Detallepedido> detallepedidoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "referencia", fetch = FetchType.LAZY)
    private Collection<Detalleventa> detalleventaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "referencia", fetch = FetchType.LAZY)
    private Collection<Inventario> inventarioCollection;
    @JoinColumns({
        @JoinColumn(name = "producto_id", referencedColumnName = "id"),
        @JoinColumn(name = "producto_id", referencedColumnName = "id")})
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Producto producto;

    public Referencia() {
    }

    public Referencia(Integer id) {
        this.id = id;
    }

    public Referencia(Integer id, int cantidaDisponible, int precioVenta) {
        this.id = id;
        this.cantidaDisponible = cantidaDisponible;
        this.precioVenta = precioVenta;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getCantidaDisponible() {
        return cantidaDisponible;
    }

    public void setCantidaDisponible(int cantidaDisponible) {
        this.cantidaDisponible = cantidaDisponible;
    }

    public int getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(int precioVenta) {
        this.precioVenta = precioVenta;
    }

    public Collection<Detallepedido> getDetallepedidoCollection() {
        return detallepedidoCollection;
    }

    public void setDetallepedidoCollection(Collection<Detallepedido> detallepedidoCollection) {
        this.detallepedidoCollection = detallepedidoCollection;
    }

    public Collection<Detalleventa> getDetalleventaCollection() {
        return detalleventaCollection;
    }

    public void setDetalleventaCollection(Collection<Detalleventa> detalleventaCollection) {
        this.detalleventaCollection = detalleventaCollection;
    }

    public Collection<Inventario> getInventarioCollection() {
        return inventarioCollection;
    }

    public void setInventarioCollection(Collection<Inventario> inventarioCollection) {
        this.inventarioCollection = inventarioCollection;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Referencia)) {
            return false;
        }
        Referencia other = (Referencia) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.sena.entityeasyjob.Referencia[ id=" + id + " ]";
    }
    
}
