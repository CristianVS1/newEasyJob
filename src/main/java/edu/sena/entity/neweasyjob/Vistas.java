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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author user
 */
@Entity
@Table(name = "tbl_vistas")
@NamedQueries({
    @NamedQuery(name = "Vistas.findAll", query = "SELECT t FROM Vistas t")})
public class Vistas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "vista_id")
    private Integer vistaId;
    @Size(max = 45)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 150)
    @Column(name = "ruta")
    private String ruta;
    @Column(name = "estado")
    private Short estado;
    @JoinColumn(name = "fk_rol_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Rol fkRolId;

    public Vistas() {
    }

    public Vistas(Integer vistaId) {
        this.vistaId = vistaId;
    }

    public Integer getVistaId() {
        return vistaId;
    }

    public void setVistaId(Integer vistaId) {
        this.vistaId = vistaId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public Short getEstado() {
        return estado;
    }

    public void setEstado(Short estado) {
        this.estado = estado;
    }

    public Rol getFkRolId() {
        return fkRolId;
    }

    public void setFkRolId(Rol fkRolId) {
        this.fkRolId = fkRolId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (vistaId != null ? vistaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vistas)) {
            return false;
        }
        Vistas other = (Vistas) object;
        if ((this.vistaId == null && other.vistaId != null) || (this.vistaId != null && !this.vistaId.equals(other.vistaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.sena.entityeasyjob.Vistas[ vistaId=" + vistaId + " ]";
    }
    
}
