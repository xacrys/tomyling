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
import com.sun.javafx.scene.control.skin.VirtualFlow;
import com.tomyling.facturacion.modelo.DetalleFactura;
import com.tomyling.facturacion.modelo.Factura;
import com.tomyling.facturacion.modelo.Impuesto;
import com.tomyling.facturacion.modelo.Parametros;
import com.tomyling.facturacion.servicio.FacturaServicio;
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
    private Integer flagCodigo;
    private Integer flagCodigoPorcentaje;
    private Integer flagBaseImponible;
    private Integer flagValor;
    private Parametros parametro;
    private List<Impuesto> listaImpuesto;
    private List<DetalleFactura> listaDetalles;

    @EJB
    private FacturaServicio facturaServicio;

    @EJB
    private ParametroServicio parametroServicio;

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

    public void leerFactura() throws JDOMException, TransformerConfigurationException, ParserConfigurationException, SAXException, TransformerException  {
        
        for(UploadedFile uf : archivoSubido)
        {
            try {
                copiarArchivo(uf.getFileName(), uf.getInputstream());
                String ruta = destino + uf.getFileName();
                this.archivo = new File(ruta);
                String fuenteXml = validarXml(archivo);
                SAXBuilder saxBuilder = new SAXBuilder();
                InputStream stream = new ByteArrayInputStream(fuenteXml.getBytes("UTF-8"));
                Document documento = saxBuilder.build(stream);
                Element nodoRaiz = documento.getRootElement();
                String nombreRaiz = nodoRaiz.getName();

                factura = new Factura();
                flagCodigo = 0;
                flagCodigoPorcentaje = 0;
                flagBaseImponible = 0;
                flagValor = 0;
                recorrerHijos(nodoRaiz);
//            Boolean flagExiste = facturaServicio.facturaExiste(factura.getClaveAcceso());
//            if (!flagExiste) {
//                facturaServicio.guardarFactura(factura);
//            } else {
//                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Alerta!", "La Factura ya ha sido cargada."));
//
//            }

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
                    factura.setCodEmision(Integer.parseInt(nodo.getText()));
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
                case "importeTotal":
                    factura.setImporteTotal(BigDecimal.valueOf(Double.parseDouble(nodo.getText())));
                case "moneda":
                    factura.setMoneda(nodo.getText());

            }
        } else {
            for (int i = 0; i < listaNodos.size(); i++) {
                Element nodoSecundario = (Element) listaNodos.get(i);
                recorrerHijos(nodoSecundario);
            }

        }
    }

    public Integer getFlagCodigo() {
        return flagCodigo;
    }

    public void setFlagCodigo(Integer flagCodigo) {
        this.flagCodigo = flagCodigo;
    }

    public Integer getFlagCodigoPorcentaje() {
        return flagCodigoPorcentaje;
    }

    public void setFlagCodigoPorcentaje(Integer flagCodigoPorcentaje) {
        this.flagCodigoPorcentaje = flagCodigoPorcentaje;
    }

    public Integer getFlagBaseImponible() {
        return flagBaseImponible;
    }

    public void setFlagBaseImponible(Integer flagBaseImponible) {
        this.flagBaseImponible = flagBaseImponible;
    }

    public Integer getFlagValor() {
        return flagValor;
    }

    public void setFlagValor(Integer flagValor) {
        this.flagValor = flagValor;
    }

    public List<Impuesto> getListaImpuesto() {
        return listaImpuesto;
    }

    public void setListaImpuesto(List<Impuesto> listaImpuesto) {
        this.listaImpuesto = listaImpuesto;
    }

    public List<DetalleFactura> getListaDetalles() {
        return listaDetalles;
    }

    public void setListaDetalles(List<DetalleFactura> listaDetalles) {
        this.listaDetalles = listaDetalles;
    }

    public List<UploadedFile> getArchivoSubido() {
        return archivoSubido;
    }

    public void setArchivoSubido(List<UploadedFile> archivoSubido) {
        this.archivoSubido = archivoSubido;
    }

}
