/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomyling.facturacion.controlador;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.tomyling.facturacion.dto.FacturaXmlDto;
import com.tomyling.facturacion.modelo.DetalleFactura;
import com.tomyling.facturacion.modelo.Factura;
import com.tomyling.facturacion.modelo.Impuesto;
import com.tomyling.facturacion.modelo.Parametros;
import com.tomyling.facturacion.servicio.DetalleFacturaServicio;
import com.tomyling.facturacion.servicio.FacturaServicio;
import com.tomyling.facturacion.servicio.ImpuestoServicio;
import com.tomyling.facturacion.servicio.ParametroServicio;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.primefaces.model.UploadedFile;
import org.primefaces.event.FileUploadEvent;
import org.xml.sax.SAXException;

/**
 *
 * @author EDWIN VACA
 */
@ManagedBean
@ViewScoped
public class CargarFacturaControlador implements Serializable {

    private List<UploadedFile> archivoSubido;
    private File archivo;
    private String destino;
    private Factura factura;
    private Boolean flagImpuesto;
    private Parametros parametro;
    private List<Impuesto> listaImpuesto;
    private List<DetalleFactura> listaDetalles;
    private Impuesto impuesto;
    private DetalleFactura detalleFactura;
    private FacturaXmlDto facturaXml;

    @EJB
    private FacturaServicio facturaServicio;

    @EJB
    private ParametroServicio parametroServicio;

    @EJB
    private ImpuestoServicio impuestoServicio;

    @EJB
    private DetalleFacturaServicio detalleFacServicio;

    @PostConstruct
    private void inicio() {
        this.parametro = parametroServicio.actualizarParametros();
        this.destino = this.parametro.getRutaTemporal().endsWith("\\") ? this.parametro.getRutaTemporal() : this.parametro.getRutaTemporal() + "\\";
        this.destino = this.destino.replace("\\", "\\\\");
        this.archivoSubido = new ArrayList<>();
    }

    public void subir(FileUploadEvent evento) {
        this.archivoSubido.add(evento.getFile());
    }

    public void leerFactura() throws JDOMException, TransformerConfigurationException, ParserConfigurationException, SAXException, TransformerException {

        for (UploadedFile uf : archivoSubido) {
            try {
                copiarArchivo(uf.getFileName(), uf.getInputstream());
                String ruta = destino + uf.getFileName();
                this.archivo = new File(ruta);
                String fuenteXml = validarXml(archivo);
                SAXBuilder saxBuilder = new SAXBuilder();
                InputStream stream = new ByteArrayInputStream(fuenteXml.getBytes("UTF-8"));
                Document documento = saxBuilder.build(stream);
                Element nodoRaiz = documento.getRootElement();
                //String nombreRaiz = nodoRaiz.getName();

                factura = new Factura();
                listaImpuesto = new ArrayList<>();
                listaDetalles = new ArrayList<>();
                flagImpuesto = false;
                facturaXml = new FacturaXmlDto();
//                flagDetalle = false;
                recorrerHijos(nodoRaiz);
                facturaXml.setFactura(factura);
                facturaXml.setListaImpuestos(listaImpuesto);
                facturaXml.setListaDetalles(listaDetalles);
                Boolean flagExiste = facturaServicio.facturaExiste(factura.getClaveAcceso());
                if (!flagExiste) {
                    facturaServicio.guardarFactura(factura);
                    Factura nuevaFactura = facturaServicio.consultarFactura(factura.getClaveAcceso());
                    impuestoServicio.guardarImpuestos(nuevaFactura.getIdFactura(), listaImpuesto);
                    detalleFacServicio.guardarDetalles(nuevaFactura.getIdFactura(), listaDetalles);

                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Alerta!", "La Factura ya ha sido cargada."));

                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        this.archivoSubido = new ArrayList<>();

    }

    public void copiarArchivo(String fileName, InputStream in) {
        try {
            OutputStream out = new FileOutputStream(new File(destino + fileName));
            int read = 0;
            byte[] bytes = new byte[1024];
            while ((read = in.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            in.close();
            out.flush();
            out.close();

            System.out.println("Nuevo Archivo Creado!");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public String validarXml(File archivo) throws ParserConfigurationException, TransformerConfigurationException, SAXException, IOException, TransformerException {

        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        //initialize StreamResult with File object to save to file
        StreamResult result = new StreamResult(new StringWriter());
        DOMSource source = new DOMSource(docBuilder.parse(archivo));
        transformer.transform(source, result);
        String xmlString = result.getWriter().toString();
        if (xmlString.indexOf("<![CDATA") != -1) {
            System.out.println(xmlString);
            String salida;
            String aux1 = xmlString.substring(0, xmlString.indexOf("<![CDATA"));
            String aux2 = xmlString.substring(xmlString.indexOf("<factura"), xmlString.indexOf("]]"));
            String aux3 = xmlString.substring(xmlString.indexOf("]]") + 2, xmlString.length());
            salida = aux1 + aux2 + aux3;
            System.out.println(salida);
            return salida;
        } else {
            return xmlString;
        }
    }

    public void recorrerHijos(Element nodo) {

        List listaNodos = nodo.getChildren();

        if (listaNodos.isEmpty()) {
            System.out.println(nodo.getName() + "--------" + nodo.getText());

            switch (nodo.getName()) {

                case "ambiente":
                    factura.setCodAmbiente(Integer.parseInt(nodo.getText()));
                    break;
                case "tipoEmision":
                    factura.setCodEmision(Integer.parseInt(nodo.getText()));
                    break;
                case "razonSocial":
                    factura.setRazonSocial(nodo.getText());
                    break;
                case "nombreComercial":
                    factura.setNombreComercial(nodo.getText());
                    break;
                case "ruc":
                    factura.setRuc(nodo.getText());
                    break;
                case "claveAcceso":
                    try {
                        factura.setClaveAcceso(nodo.getText());
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    break;
                case "codDoc":
                    factura.setCodDoc(nodo.getText());
                    break;
                case "estab":
                    factura.setEstab(nodo.getText());
                    break;
                case "ptoEmi":
                    factura.setPtoEmi(nodo.getText());
                    break;
                case "secuencial":
                    factura.setSecuencial(nodo.getText());
                    break;
                case "dirMatriz":
                    factura.setDirMatriz(nodo.getText());
                    break;
                case "fechaEmision":
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                    String dateInString = nodo.getText();
                    try {
                        Date date = formatter.parse(dateInString);
                        factura.setFechaEmision(date);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    break;
                case "dirEstablecimiento":
                    factura.setDirEstablecimiento(nodo.getText());
                    break;
                case "contribuyenteEspecial":
                    factura.setContibuyenteEspecial(nodo.getText());
                    break;
                case "obligadoContabilidad":
                    Boolean flagContribuyente = "SI".equals(nodo.getText());
                    factura.setObligadoContabilidad(flagContribuyente);
                    break;
                case "tipoIdentificacionComprador":
                    factura.setTipoIdentificacionComprador(nodo.getText());
                    break;
                case "guiaRemision":
                    factura.setGuiaRemision(nodo.getText());
                    break;
                case "razonSocialComprador":
                    factura.setRazonSocialComprador(nodo.getText());
                    break;
                case "identificacionComprador":
                    factura.setCedRuc(nodo.getText());
                    break;
                case "direccionComprador":
                    factura.setDireccionComprador(nodo.getText());
                    break;
                case "totalSinImpuestos":
                    factura.setTotalSinimpuesto(BigDecimal.valueOf(Double.parseDouble(nodo.getText())));
                    break;
                case "totalDescuento":
                    factura.setTotalDescuento(BigDecimal.valueOf(Double.parseDouble(nodo.getText())));
                    break;
                case "propina":
                    factura.setPropina(BigDecimal.valueOf(Double.parseDouble(nodo.getText())));
                    break;
                case "importeTotal":
                    factura.setImporteTotal(BigDecimal.valueOf(Double.parseDouble(nodo.getText())));
                    break;
                case "moneda":
                    factura.setMoneda(nodo.getText());
                    break;
                case "codigo":
                    if (flagImpuesto) {
                        impuesto.setCodImpuesto(Integer.parseInt(nodo.getText()));
                    }
                    break;
                case "codigoPorcentaje":
                    if (flagImpuesto) {
                        impuesto.setCodigoPorcentaje(nodo.getText());
                    }
                    break;
                case "baseImponible":
                    if (flagImpuesto) {
                        impuesto.setBaseImponible(BigDecimal.valueOf(Double.parseDouble(nodo.getText())));
                    }
                    break;
                case "valor":
                    if (flagImpuesto) {
                        impuesto.setValor(BigDecimal.valueOf(Double.parseDouble(nodo.getText())));
                        listaImpuesto.add(impuesto);
                    }
                    break;
                case "codigoPrincipal":
                    detalleFactura.setCodigoPrincipal(nodo.getText());
                    break;
                case "codigoAuxiliar":
                    detalleFactura.setCodigoAuxiliar(nodo.getText());
                    break;
                case "descripcion":
                    detalleFactura.setDescripcion(nodo.getText());
                    break;
                case "cantidad":
                    detalleFactura.setCantidad(Double.parseDouble(nodo.getText()));
                    break;
                case "precioUnitario":
                    detalleFactura.setPrecioUnitario(Double.parseDouble(nodo.getText()));
                    break;
                case "descuento":
                    detalleFactura.setDescuento(Double.parseDouble(nodo.getText()));
                    break;
                case "precioTotalSinImpuesto":
                    detalleFactura.setPrecioTotalSinimpuesto(Double.parseDouble(nodo.getText()));
                    listaDetalles.add(detalleFactura);
                    break;
                case "campoAdicional":
                    switch(nodo.getAttributes().get(0).getValue())
                    {
                        case "Direccion":
                        factura.setDireccionAdicional(nodo.getContent().get(0).getValue());
                        break;
                        case "Telefono":
                        factura.setTelefonoAdicional(nodo.getContent().get(0).getValue());
                        break;
                        case "Vendedor":
                        factura.setVendedorAdicional(nodo.getContent().get(0).getValue());
                        break;
                        case "Forma de pago":
                        factura.setFormaPagoAdicional(nodo.getContent().get(0).getValue());
                        break;
                        case "Cuota 1":
                        factura.setCuotaAdicional(nodo.getContent().get(0).getValue());
                        break;
                    }
                    break;

            }
        } else {
            for (int i = 0; i < listaNodos.size(); i++) {
                Element nodoSecundario = (Element) listaNodos.get(i);
                if (nodoSecundario.getName().equals("totalImpuesto")) {
                    impuesto = new Impuesto();
                    flagImpuesto = true;
                }
                if (nodoSecundario.getName().equals("detalle")) {
                    detalleFactura = new DetalleFactura();
                }
                if (nodoSecundario.getName().equals("impuesto")) {
                    flagImpuesto = false;
                }
                recorrerHijos(nodoSecundario);
            }

        }
    }

    public List<UploadedFile> getArchivoSubido() {
        return archivoSubido;
    }

    public void setArchivoSubido(List<UploadedFile> archivoSubido) {
        this.archivoSubido = archivoSubido;
    }

}
