/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.sena.entity.neweasyjob;

import java.io.Serializable;
import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author user
 */
@Entity
@Table(name = "tbl_detallepedido")
@NamedQueries({
    @NamedQuery(name = "Detallepedido.findAll", query = "SELECT d FROM Detallepedido d")})
public class Detallepedido implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidadCompra")
    private int cantidadCompra;
    @Basic(optional = false)
    @NotNull
    @Column(name = "precioUcompra")
    private int precioUcompra;
    @JoinColumns({
        @JoinColumn(name = "pedido_id", referencedColumnName = "id"),
        @JoinColumn(name = "pedido_id", referencedColumnName = "id")})
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Pedido pedido;
    @JoinColumns({
        @JoinColumn(name = "referencia_id", referencedColumnName = "id"),
        @JoinColumn(name = "referencia_id", referencedColumnName = "id")})
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Referencia referencia;

    public Detallepedido() {
    }

    public Detallepedido(Integer id) {
        this.id = id;
    }

    public Detallepedido(Integer id, int cantidadCompra, int precioUcompra) {
        this.id = id;
        this.cantidadCompra = cantidadCompra;
        this.precioUcompra = precioUcompra;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getCantidadCompra() {
        return cantidadCompra;
    }

    public void setCantidadCompra(int cantidadCompra) {
        this.cantidadCompra = cantidadCompra;
    }

    public int getPrecioUcompra() {
        return precioUcompra;
    }

    public void setPrecioUcompra(int precioUcompra) {
        this.precioUcompra = precioUcompra;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Referencia getReferencia() {
        return referencia;
    }

    public void setReferencia(Referencia referencia) {
        this.referencia = referencia;
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
        if (!(object instanceof Detallepedido)) {
            return false;
        }
        Detallepedido other = (Detallepedido) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.sena.entityeasyjob.Detallepedido[ id=" + id + " ]";
    }
    
}
