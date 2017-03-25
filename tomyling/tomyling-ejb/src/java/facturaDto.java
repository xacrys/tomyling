
import com.tomyling.facturacion.modelo.DetalleFactura;
import com.tomyling.facturacion.modelo.Factura;
import com.tomyling.facturacion.modelo.Impuesto;
import java.io.Serializable;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author EDWIN VACA
 */
public class facturaDto implements Serializable
{
    private Factura factura;
    private List<Impuesto> listaImpuesto;
    private List<DetalleFactura> listaDetalle;

    public facturaDto() {
    }

    public facturaDto(Factura factura, List<Impuesto> listaImpuesto, List<DetalleFactura> listaDetalle) {
        this.factura = factura;
        this.listaImpuesto = listaImpuesto;
        this.listaDetalle = listaDetalle;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public List<Impuesto> getListaImpuesto() {
        return listaImpuesto;
    }

    public void setListaImpuesto(List<Impuesto> listaImpuesto) {
        this.listaImpuesto = listaImpuesto;
    }

    public List<DetalleFactura> getListaDetalle() {
        return listaDetalle;
    }

    public void setListaDetalle(List<DetalleFactura> listaDetalle) {
        this.listaDetalle = listaDetalle;
    }
    
     
    
}
