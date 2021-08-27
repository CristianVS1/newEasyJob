/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sena.entity.neweasyjob;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author user
 */
@Entity
@Table(name = "tbl_pedido")
@NamedQueries({
    @NamedQuery(name = "Pedido.findAll", query = "SELECT p FROM Pedido p")})
public class Pedido implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechaPedido")
    @Temporal(TemporalType.DATE)
    private Date fechaPedido;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pedido", fetch = FetchType.LAZY)
    private Collection<Detallepedido> detallepedidoCollection;
    @JoinColumns({
        @JoinColumn(name = "aprobacion_id", referencedColumnName = "id"),
        @JoinColumn(name = "aprobacion_id", referencedColumnName = "id")})
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Aprobacion aprobacion;
    @JoinColumns({
        @JoinColumn(name = "empleado_id", referencedColumnName = "id"),
        @JoinColumn(name = "empleado_id", referencedColumnName = "id")})
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Empleado empleado;
    @JoinColumns({
        @JoinColumn(name = "proveedor_id", referencedColumnName = "id"),
        @JoinColumn(name = "proveedor_id", referencedColumnName = "id")})
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Proveedor proveedor;

    public Pedido() {
    }

    public Pedido(Integer id) {
        this.id = id;
    }

    public Pedido(Integer id, Date fechaPedido) {
        this.id = id;
        this.fechaPedido = fechaPedido;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(Date fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public Collection<Detallepedido> getDetallepedidoCollection() {
        return detallepedidoCollection;
    }

    public void setDetallepedidoCollection(Collection<Detallepedido> detallepedidoCollection) {
        this.detallepedidoCollection = detallepedidoCollection;
    }

    public Aprobacion getAprobacion() {
        return aprobacion;
    }

    public void setAprobacion(Aprobacion aprobacion) {
        this.aprobacion = aprobacion;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
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
        if (!(object instanceof Pedido)) {
            return false;
        }
        Pedido other = (Pedido) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.sena.entityeasyjob.Pedido[ id=" + id + " ]";
    }
    
}
