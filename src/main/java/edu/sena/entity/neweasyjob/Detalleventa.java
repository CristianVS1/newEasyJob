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
@Table(name = "tbl_detalleventa")
@NamedQueries({
    @NamedQuery(name = "Detalleventa.findAll", query = "SELECT d FROM Detalleventa d")})
public class Detalleventa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidadVendida")
    private int cantidadVendida;
    @JoinColumns({
        @JoinColumn(name = "cliente_id", referencedColumnName = "id"),
        @JoinColumn(name = "cliente_id", referencedColumnName = "id")})
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Detallecliente detallecliente;
    @JoinColumns({
        @JoinColumn(name = "referencia_id", referencedColumnName = "id"),
        @JoinColumn(name = "referencia_id", referencedColumnName = "id")})
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Referencia referencia;
    @JoinColumns({
        @JoinColumn(name = "venta_id", referencedColumnName = "id"),
        @JoinColumn(name = "venta_id", referencedColumnName = "id")})
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Venta venta;

    public Detalleventa() {
    }

    public Detalleventa(Integer id) {
        this.id = id;
    }

    public Detalleventa(Integer id, int cantidadVendida) {
        this.id = id;
        this.cantidadVendida = cantidadVendida;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getCantidadVendida() {
        return cantidadVendida;
    }

    public void setCantidadVendida(int cantidadVendida) {
        this.cantidadVendida = cantidadVendida;
    }

    public Detallecliente getDetallecliente() {
        return detallecliente;
    }

    public void setDetallecliente(Detallecliente detallecliente) {
        this.detallecliente = detallecliente;
    }

    public Referencia getReferencia() {
        return referencia;
    }

    public void setReferencia(Referencia referencia) {
        this.referencia = referencia;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
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
        if (!(object instanceof Detalleventa)) {
            return false;
        }
        Detalleventa other = (Detalleventa) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.sena.entityeasyjob.Detalleventa[ id=" + id + " ]";
    }
    
}
