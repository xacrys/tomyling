/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomyling.facturacion.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author new user
 */
@Entity
@Table(name = "factura")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Factura.findAll", query = "SELECT f FROM Factura f")
    , @NamedQuery(name = "Factura.findByIdFactura", query = "SELECT f FROM Factura f WHERE f.idFactura = :idFactura")
    , @NamedQuery(name = "Factura.findByCedRuc", query = "SELECT f FROM Factura f WHERE f.cedRuc = :cedRuc")
    , @NamedQuery(name = "Factura.findByCodComprador", query = "SELECT f FROM Factura f WHERE f.codComprador = :codComprador")
    , @NamedQuery(name = "Factura.findByCodEmision", query = "SELECT f FROM Factura f WHERE f.codEmision = :codEmision")
    , @NamedQuery(name = "Factura.findByCodDocumento", query = "SELECT f FROM Factura f WHERE f.codDocumento = :codDocumento")
    , @NamedQuery(name = "Factura.findByCodAmbiente", query = "SELECT f FROM Factura f WHERE f.codAmbiente = :codAmbiente")
    , @NamedQuery(name = "Factura.findByRazonSocial", query = "SELECT f FROM Factura f WHERE f.razonSocial = :razonSocial")
    , @NamedQuery(name = "Factura.findByNombreComercial", query = "SELECT f FROM Factura f WHERE f.nombreComercial = :nombreComercial")
    , @NamedQuery(name = "Factura.findByRuc", query = "SELECT f FROM Factura f WHERE f.ruc = :ruc")
    , @NamedQuery(name = "Factura.findByClaveAcceso", query = "SELECT f FROM Factura f WHERE f.claveAcceso = :claveAcceso")
    , @NamedQuery(name = "Factura.findByCodDoc", query = "SELECT f FROM Factura f WHERE f.codDoc = :codDoc")
    , @NamedQuery(name = "Factura.findByEstab", query = "SELECT f FROM Factura f WHERE f.estab = :estab")
    , @NamedQuery(name = "Factura.findBySecuencial", query = "SELECT f FROM Factura f WHERE f.secuencial = :secuencial")
    , @NamedQuery(name = "Factura.findByDirMatriz", query = "SELECT f FROM Factura f WHERE f.dirMatriz = :dirMatriz")
    , @NamedQuery(name = "Factura.findByFechaEmision", query = "SELECT f FROM Factura f WHERE f.fechaEmision = :fechaEmision")
    , @NamedQuery(name = "Factura.findByDirEstablecimiento", query = "SELECT f FROM Factura f WHERE f.dirEstablecimiento = :dirEstablecimiento")
    , @NamedQuery(name = "Factura.findByContibuyenteEspecial", query = "SELECT f FROM Factura f WHERE f.contibuyenteEspecial = :contibuyenteEspecial")
    , @NamedQuery(name = "Factura.findByObligadoContabilidad", query = "SELECT f FROM Factura f WHERE f.obligadoContabilidad = :obligadoContabilidad")
    , @NamedQuery(name = "Factura.findByTipoIdentificacionComprador", query = "SELECT f FROM Factura f WHERE f.tipoIdentificacionComprador = :tipoIdentificacionComprador")
    , @NamedQuery(name = "Factura.findByGuiaRemision", query = "SELECT f FROM Factura f WHERE f.guiaRemision = :guiaRemision")
    , @NamedQuery(name = "Factura.findByRazonSocialComprador", query = "SELECT f FROM Factura f WHERE f.razonSocialComprador = :razonSocialComprador")
    , @NamedQuery(name = "Factura.findByDireccionComprador", query = "SELECT f FROM Factura f WHERE f.direccionComprador = :direccionComprador")
    , @NamedQuery(name = "Factura.findByTotalSinimpuesto", query = "SELECT f FROM Factura f WHERE f.totalSinimpuesto = :totalSinimpuesto")
    , @NamedQuery(name = "Factura.findByTotalDescuento", query = "SELECT f FROM Factura f WHERE f.totalDescuento = :totalDescuento")
    , @NamedQuery(name = "Factura.findByPropina", query = "SELECT f FROM Factura f WHERE f.propina = :propina")
    , @NamedQuery(name = "Factura.findByImporteTotal", query = "SELECT f FROM Factura f WHERE f.importeTotal = :importeTotal")
    , @NamedQuery(name = "Factura.findByMoneda", query = "SELECT f FROM Factura f WHERE f.moneda = :moneda")})
public class Factura implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_factura")
    private Integer idFactura;
    @Size(max = 20)
    @Column(name = "ced_ruc")
    private String cedRuc;
    @Size(max = 2)
    @Column(name = "cod_comprador")
    private String codComprador;
    @Column(name = "cod_emision")
    private Integer codEmision;
    @Size(max = 2)
    @Column(name = "cod_documento")
    private String codDocumento;
    @Column(name = "cod_ambiente")
    private Integer codAmbiente;
    @Size(max = 300)
    @Column(name = "razon_social")
    private String razonSocial;
    @Size(max = 300)
    @Column(name = "nombre_comercial")
    private String nombreComercial;
    @Size(max = 20)
    @Column(name = "ruc")
    private String ruc;
    @Size(max = 49)
    @Column(name = "clave_acceso")
    private String claveAcceso;
    @Size(max = 35)
    @Column(name = "cod_doc")
    private String codDoc;
    @Size(max = 3)
    @Column(name = "estab")
    private String estab;
    @Size(max = 9)
    @Column(name = "secuencial")
    private String secuencial;
    @Size(max = 300)
    @Column(name = "dir_matriz")
    private String dirMatriz;
    @Column(name = "fecha_emision")
    @Temporal(TemporalType.DATE)
    private Date fechaEmision;
    @Size(max = 300)
    @Column(name = "dir_establecimiento")
    private String dirEstablecimiento;
    @Size(max = 13)
    @Column(name = "contibuyente_especial")
    private String contibuyenteEspecial;
    @Column(name = "obligado_contabilidad")
    private Boolean obligadoContabilidad;
    @Size(max = 2)
    @Column(name = "tipo_identificacion_comprador")
    private String tipoIdentificacionComprador;
    @Size(max = 15)
    @Column(name = "guia_remision")
    private String guiaRemision;
    @Size(max = 300)
    @Column(name = "razon_social_comprador")
    private String razonSocialComprador;
    @Size(max = 300)
    @Column(name = "direccion_comprador")
    private String direccionComprador;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "total_sinimpuesto")
    private BigDecimal totalSinimpuesto;
    @Column(name = "total_descuento")
    private BigDecimal totalDescuento;
    @Column(name = "propina")
    private BigDecimal propina;
    @Column(name = "importe_total")
    private BigDecimal importeTotal;
    @Size(max = 20)
    @Column(name = "moneda")
    private String moneda;

    public Factura() {
    }

    public Factura(Integer idFactura) {
        this.idFactura = idFactura;
    }

    public Integer getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(Integer idFactura) {
        this.idFactura = idFactura;
    }

    public String getCedRuc() {
        return cedRuc;
    }

    public void setCedRuc(String cedRuc) {
        this.cedRuc = cedRuc;
    }

    public String getCodComprador() {
        return codComprador;
    }

    public void setCodComprador(String codComprador) {
        this.codComprador = codComprador;
    }

    public Integer getCodEmision() {
        return codEmision;
    }

    public void setCodEmision(Integer codEmision) {
        this.codEmision = codEmision;
    }

    public String getCodDocumento() {
        return codDocumento;
    }

    public void setCodDocumento(String codDocumento) {
        this.codDocumento = codDocumento;
    }

    public Integer getCodAmbiente() {
        return codAmbiente;
    }

    public void setCodAmbiente(Integer codAmbiente) {
        this.codAmbiente = codAmbiente;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getClaveAcceso() {
        return claveAcceso;
    }

    public void setClaveAcceso(String claveAcceso) {
        this.claveAcceso = claveAcceso;
    }

    public String getCodDoc() {
        return codDoc;
    }

    public void setCodDoc(String codDoc) {
        this.codDoc = codDoc;
    }

    public String getEstab() {
        return estab;
    }

    public void setEstab(String estab) {
        this.estab = estab;
    }

    public String getSecuencial() {
        return secuencial;
    }

    public void setSecuencial(String secuencial) {
        this.secuencial = secuencial;
    }

    public String getDirMatriz() {
        return dirMatriz;
    }

    public void setDirMatriz(String dirMatriz) {
        this.dirMatriz = dirMatriz;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public String getDirEstablecimiento() {
        return dirEstablecimiento;
    }

    public void setDirEstablecimiento(String dirEstablecimiento) {
        this.dirEstablecimiento = dirEstablecimiento;
    }

    public String getContibuyenteEspecial() {
        return contibuyenteEspecial;
    }

    public void setContibuyenteEspecial(String contibuyenteEspecial) {
        this.contibuyenteEspecial = contibuyenteEspecial;
    }

    public Boolean getObligadoContabilidad() {
        return obligadoContabilidad;
    }

    public void setObligadoContabilidad(Boolean obligadoContabilidad) {
        this.obligadoContabilidad = obligadoContabilidad;
    }

    public String getTipoIdentificacionComprador() {
        return tipoIdentificacionComprador;
    }

    public void setTipoIdentificacionComprador(String tipoIdentificacionComprador) {
        this.tipoIdentificacionComprador = tipoIdentificacionComprador;
    }

    public String getGuiaRemision() {
        return guiaRemision;
    }

    public void setGuiaRemision(String guiaRemision) {
        this.guiaRemision = guiaRemision;
    }

    public String getRazonSocialComprador() {
        return razonSocialComprador;
    }

    public void setRazonSocialComprador(String razonSocialComprador) {
        this.razonSocialComprador = razonSocialComprador;
    }

    public String getDireccionComprador() {
        return direccionComprador;
    }

    public void setDireccionComprador(String direccionComprador) {
        this.direccionComprador = direccionComprador;
    }

    public BigDecimal getTotalSinimpuesto() {
        return totalSinimpuesto;
    }

    public void setTotalSinimpuesto(BigDecimal totalSinimpuesto) {
        this.totalSinimpuesto = totalSinimpuesto;
    }

    public BigDecimal getTotalDescuento() {
        return totalDescuento;
    }

    public void setTotalDescuento(BigDecimal totalDescuento) {
        this.totalDescuento = totalDescuento;
    }

    public BigDecimal getPropina() {
        return propina;
    }

    public void setPropina(BigDecimal propina) {
        this.propina = propina;
    }

    public BigDecimal getImporteTotal() {
        return importeTotal;
    }

    public void setImporteTotal(BigDecimal importeTotal) {
        this.importeTotal = importeTotal;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFactura != null ? idFactura.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Factura)) {
            return false;
        }
        Factura other = (Factura) object;
        if ((this.idFactura == null && other.idFactura != null) || (this.idFactura != null && !this.idFactura.equals(other.idFactura))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tomyling.facturacion.modelo.Factura[ idFactura=" + idFactura + " ]";
    }
    
}
