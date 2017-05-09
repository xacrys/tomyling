/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomyling.facturacion.utilitarios;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.tomyling.facturacion.dto.FacturaCompletaDto;
//*****************************************************
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.tomyling.facturacion.modelo.DetalleFactura;
import com.tomyling.facturacion.modelo.Factura;
//****************************************************
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author new user
 */
public class Utilitarios implements Serializable {
    
    private String descripAmbiente;
    private String descripTipoEmite;
    private BigDecimal val14;
    private BigDecimal valICE;
    private BigDecimal valIRB;
    private Factura selectFactura;
    private List<DetalleFactura> lisDetalleFactura;

    protected FacesContext getContext() {
        return FacesContext.getCurrentInstance();
    }

    protected ExternalContext getExternalContext() {
        return getContext().getExternalContext();
    }

    protected HttpServletRequest getRequest() {
        return (HttpServletRequest) getExternalContext().getRequest();
    }

    protected void redirect(String url) throws IOException {
        getExternalContext().redirect(getRequest().getContextPath() + url);
    }

    public void generaXls(FacturaCompletaDto facCompleta) throws FileNotFoundException, IOException {

        XSSFWorkbook libro = new XSSFWorkbook();
        XSSFSheet hoja = libro.createSheet();
        XSSFRow fila1 = hoja.createRow(0);
        XSSFCellStyle titulo = titulo(libro, "CENTER");
        XSSFCellStyle titulo2 = titulo(libro, "LEFT");
        XSSFCellStyle styleCuerpo = stiloCuerpo1(libro);
        hoja.addMergedRegion(new CellRangeAddress(0, 0, 0, 7));
        crearCelda(fila1, 0, "Sistema de generacion de Facturas", styleCuerpo);
        int pictureIdx;
        try (InputStream inputStream = Utilitarios.class.getResourceAsStream("facturaLogo.png")) {
            byte[] bytes = IOUtils.toByteArray(inputStream);
            pictureIdx = libro.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
        }
        CreationHelper helper = libro.getCreationHelper();
        Drawing drawing = hoja.createDrawingPatriarch();
        ClientAnchor anchor = helper.createClientAnchor();
        hoja.addMergedRegion(new CellRangeAddress(2, 6, 0, 3));
        //create an anchor with upper left cell _and_ bottom right cell
        anchor.setCol1(0); //Column B
        anchor.setRow1(2); //Row 3
        anchor.setCol2(4); //Column C
        anchor.setRow2(6); //Row 4
        drawing.createPicture(anchor, pictureIdx);
        XSSFRow fila2 = hoja.createRow(1);
        hoja.addMergedRegion(new CellRangeAddress(1, 1, 0, 7));
        XSSFRow fila3 = hoja.createRow(2);
        hoja.addMergedRegion(new CellRangeAddress(2, 2, 4, 5));
        crearCelda(fila3, 4, "RUC", titulo2);
        hoja.addMergedRegion(new CellRangeAddress(2, 2, 6, 7));
        crearCelda(fila3, 6, facCompleta.getFactura().getCedRuc(), styleCuerpo);
        XSSFRow fila4 = hoja.createRow(3);
        hoja.addMergedRegion(new CellRangeAddress(3, 3, 4, 7));
        crearCelda(fila4, 4, "Factura", titulo2);
        XSSFRow fila5 = hoja.createRow(4);
        hoja.addMergedRegion(new CellRangeAddress(4, 4, 4, 7));
        crearCelda(fila5, 4, facCompleta.getFactura().getClaveAcceso(), styleCuerpo);
        XSSFRow fila6 = hoja.createRow(5);
        hoja.addMergedRegion(new CellRangeAddress(5, 5, 4, 7));
        crearCelda(fila6, 4, "Clave de Acceso", titulo2);
        XSSFRow fila7 = hoja.createRow(6);
        hoja.addMergedRegion(new CellRangeAddress(6, 6, 4, 7));
        crearCelda(fila7, 4, facCompleta.getFactura().getClaveAcceso(), styleCuerpo);
        XSSFRow fila8 = hoja.createRow(7);
        hoja.addMergedRegion(new CellRangeAddress(7, 7, 0, 1));
        crearCelda(fila8, 0, "Direccion Matriz", titulo2);
        hoja.addMergedRegion(new CellRangeAddress(7, 7, 2, 3));
        crearCelda(fila8, 2, facCompleta.getFactura().getDirMatriz(), styleCuerpo);
        hoja.addMergedRegion(new CellRangeAddress(7, 7, 4, 5));
        crearCelda(fila8, 4, "Fecha de Emision", titulo2);
        hoja.addMergedRegion(new CellRangeAddress(7, 7, 6, 7));
        crearCelda(fila8, 6, facCompleta.getFactura().getFechaEmision().toString(), styleCuerpo);
        XSSFRow fila9 = hoja.createRow(8);
        hoja.addMergedRegion(new CellRangeAddress(8, 8, 0, 1));
        crearCelda(fila9, 0, "Direccion Sucursal", titulo2);
        hoja.addMergedRegion(new CellRangeAddress(8, 8, 2, 3));
        crearCelda(fila9, 2, facCompleta.getFactura().getDirEstablecimiento(), styleCuerpo);
        hoja.addMergedRegion(new CellRangeAddress(8, 8, 4, 5));
        crearCelda(fila9, 4, "Ambiente", titulo2);
        hoja.addMergedRegion(new CellRangeAddress(8, 8, 6, 7));
        crearCelda(fila9, 6, facCompleta.getDescripcionAmbiente(), styleCuerpo);
        XSSFRow fila10 = hoja.createRow(9);
        hoja.addMergedRegion(new CellRangeAddress(9, 9, 0, 1));
        crearCelda(fila10, 0, "Contribuyente Especial No.", titulo2);
        hoja.addMergedRegion(new CellRangeAddress(9, 9, 2, 3));
        crearCelda(fila10, 2, facCompleta.getFactura().getContibuyenteEspecial(), styleCuerpo);
        hoja.addMergedRegion(new CellRangeAddress(9, 9, 4, 5));
        crearCelda(fila10, 4, "Emision", titulo2);
        hoja.addMergedRegion(new CellRangeAddress(9, 9, 6, 7));
        crearCelda(fila10, 6, facCompleta.getDescripcionTipoEmision(), styleCuerpo);
        XSSFRow fila11 = hoja.createRow(10);
        hoja.addMergedRegion(new CellRangeAddress(10, 10, 0, 3));
        crearCelda(fila11, 0, "Obligado a llevar Contabilidad", titulo2);
        hoja.addMergedRegion(new CellRangeAddress(10, 10, 4, 7));
        crearCelda(fila11, 4, "Clave de Acceso", titulo2);
        XSSFRow fila12 = hoja.createRow(11);
        hoja.addMergedRegion(new CellRangeAddress(11, 11, 0, 3));
        crearCelda(fila12, 0, facCompleta.getFactura().getObligadoContabilidad() ? "SI" : "NO", styleCuerpo);
        hoja.addMergedRegion(new CellRangeAddress(11, 11, 4, 7));
        crearCelda(fila12, 4, facCompleta.getFactura().getClaveAcceso(), styleCuerpo);
        XSSFRow fila13 = hoja.createRow(12);
        hoja.addMergedRegion(new CellRangeAddress(12, 12, 0, 1));
        crearCelda(fila13, 0, "Cliente", titulo2);
        hoja.addMergedRegion(new CellRangeAddress(12, 12, 2, 3));
        crearCelda(fila13, 2, facCompleta.getFactura().getRazonSocial(), styleCuerpo);
        hoja.addMergedRegion(new CellRangeAddress(12, 12, 4, 5));
        crearCelda(fila13, 4, "Identificacion", titulo2);
        hoja.addMergedRegion(new CellRangeAddress(12, 12, 6, 7));
        crearCelda(fila13, 6, facCompleta.getFactura().getRuc(), styleCuerpo);
        XSSFRow fila14 = hoja.createRow(13);
        hoja.addMergedRegion(new CellRangeAddress(13, 13, 0, 7));

        XSSFRow fila15 = hoja.createRow(14);
        hoja.addMergedRegion(new CellRangeAddress(14, 14, 0, 1));
        crearCelda(fila15, 0, "Cliente", titulo2);
        hoja.addMergedRegion(new CellRangeAddress(14, 14, 2, 3));
        crearCelda(fila15, 2, facCompleta.getFactura().getRazonSocial(), styleCuerpo);
        hoja.addMergedRegion(new CellRangeAddress(14, 14, 4, 5));
        crearCelda(fila15, 4, "Identificacion", titulo2);
        hoja.addMergedRegion(new CellRangeAddress(14, 14, 6, 7));
        crearCelda(fila15, 6, facCompleta.getFactura().getRuc(), styleCuerpo);

        XSSFRow fila16 = hoja.createRow(15);
        hoja.addMergedRegion(new CellRangeAddress(15, 15, 0, 1));
        crearCelda(fila16, 0, "Fecha de Emision", titulo2);
        hoja.addMergedRegion(new CellRangeAddress(15, 15, 2, 3));
        crearCelda(fila16, 2, facCompleta.getFactura().getFechaEmision().toString(), styleCuerpo);
        hoja.addMergedRegion(new CellRangeAddress(15, 15, 4, 5));
        crearCelda(fila16, 4, "Guia de Remision", titulo2);
        hoja.addMergedRegion(new CellRangeAddress(15, 15, 6, 7));
        crearCelda(fila16, 6, facCompleta.getFactura().getGuiaRemision() == null ? "" : facCompleta.getFactura().getGuiaRemision(), styleCuerpo);
        XSSFRow fila17 = hoja.createRow(16);
        hoja.addMergedRegion(new CellRangeAddress(16, 16, 0, 7));
        agregarTabla(hoja, facCompleta.getListaDetalle(), styleCuerpo, titulo2);
        Integer ultimafila = hoja.getLastRowNum() + 1;
        XSSFRow filaNueva = hoja.createRow(ultimafila);
        hoja.addMergedRegion(new CellRangeAddress(ultimafila, ultimafila, 0, 7));

        //SECCION SUBTOTAL Y TOTAL
        ultimafila = hoja.getLastRowNum() + 1;
        filaNueva = hoja.createRow(ultimafila);
        hoja.addMergedRegion(new CellRangeAddress(ultimafila, ultimafila, 5, 6));
        crearCelda(filaNueva, 5, "Subtotal 14%", titulo2);
        crearCelda(filaNueva, 7, facCompleta.getFactura().getTotalSinimpuesto().toString(), styleCuerpo);
        //SUBTOTAL
        ultimafila = hoja.getLastRowNum() + 1;
        filaNueva = hoja.createRow(ultimafila);
        hoja.addMergedRegion(new CellRangeAddress(ultimafila, ultimafila, 5, 6));
        crearCelda(filaNueva, 5, "Subtotal 0%", titulo2);
        crearCelda(filaNueva, 7, "0.00", styleCuerpo);
        //SUBTOTAL NO OBJETO DE IVA
        ultimafila = hoja.getLastRowNum() + 1;
        filaNueva = hoja.createRow(ultimafila);
        hoja.addMergedRegion(new CellRangeAddress(ultimafila, ultimafila, 5, 6));
        crearCelda(filaNueva, 5, "Subtotal No Objeto de IVA", titulo2);
        crearCelda(filaNueva, 7, "0.00", styleCuerpo);
        //SUBTOTAL NO EXCENTO DE IVA
        ultimafila = hoja.getLastRowNum() + 1;
        filaNueva = hoja.createRow(ultimafila);
        hoja.addMergedRegion(new CellRangeAddress(ultimafila, ultimafila, 5, 6));
        hoja.addMergedRegion(new CellRangeAddress(ultimafila, ultimafila, 0, 1));
        hoja.addMergedRegion(new CellRangeAddress(ultimafila, ultimafila, 2, 3));
        crearCelda(filaNueva, 0, "Direccion", titulo2);
        crearCelda(filaNueva, 2, facCompleta.getFactura().getDireccionAdicional() == null ? "" : facCompleta.getFactura().getDireccionAdicional(), styleCuerpo);
        crearCelda(filaNueva, 5, "Subtotal Excento de IVA", titulo2);
        crearCelda(filaNueva, 7, "0.00", styleCuerpo);
        //SUBTOTAL SIN IMPUESTOS
        ultimafila = hoja.getLastRowNum() + 1;
        filaNueva = hoja.createRow(ultimafila);
        hoja.addMergedRegion(new CellRangeAddress(ultimafila, ultimafila, 5, 6));
        hoja.addMergedRegion(new CellRangeAddress(ultimafila, ultimafila, 0, 1));
        hoja.addMergedRegion(new CellRangeAddress(ultimafila, ultimafila, 2, 3));
        crearCelda(filaNueva, 0, "Telefono", titulo2);
        crearCelda(filaNueva, 2, facCompleta.getFactura().getTelefonoAdicional() == null ? "" : facCompleta.getFactura().getTelefonoAdicional(), styleCuerpo);
        crearCelda(filaNueva, 5, "Subtotal Sin Impuestos", titulo2);
        crearCelda(filaNueva, 7, facCompleta.getFactura().getTotalSinimpuesto().toString(), styleCuerpo);
        //Total Descuento
        ultimafila = hoja.getLastRowNum() + 1;
        filaNueva = hoja.createRow(ultimafila);
        hoja.addMergedRegion(new CellRangeAddress(ultimafila, ultimafila, 5, 6));
        hoja.addMergedRegion(new CellRangeAddress(ultimafila, ultimafila, 0, 1));
        hoja.addMergedRegion(new CellRangeAddress(ultimafila, ultimafila, 2, 3));
        crearCelda(filaNueva, 0, "Vendedor", titulo2);
        crearCelda(filaNueva, 2, facCompleta.getFactura().getVendedorAdicional() == null ? "" : facCompleta.getFactura().getVendedorAdicional(), styleCuerpo);
        crearCelda(filaNueva, 5, "Total Descuento", titulo2);
        crearCelda(filaNueva, 7, facCompleta.getFactura().getTotalDescuento().toString(), styleCuerpo);
        //ICE
        ultimafila = hoja.getLastRowNum() + 1;
        filaNueva = hoja.createRow(ultimafila);
        hoja.addMergedRegion(new CellRangeAddress(ultimafila, ultimafila, 5, 6));
        hoja.addMergedRegion(new CellRangeAddress(ultimafila, ultimafila, 0, 1));
        hoja.addMergedRegion(new CellRangeAddress(ultimafila, ultimafila, 2, 3));
        crearCelda(filaNueva, 0, "Cuota", titulo2);
        crearCelda(filaNueva, 2, facCompleta.getFactura().getCuotaAdicional() == null ? "" : facCompleta.getFactura().getCuotaAdicional(), styleCuerpo);
        crearCelda(filaNueva, 5, "ICE", titulo2);
        crearCelda(filaNueva, 7, facCompleta.getValorIce().toString(), styleCuerpo);
        //IVA
        ultimafila = hoja.getLastRowNum() + 1;
        filaNueva = hoja.createRow(ultimafila);
        hoja.addMergedRegion(new CellRangeAddress(ultimafila, ultimafila, 5, 6));
        hoja.addMergedRegion(new CellRangeAddress(ultimafila, ultimafila, 0, 1));
        hoja.addMergedRegion(new CellRangeAddress(ultimafila, ultimafila, 2, 3));
        crearCelda(filaNueva, 0, "Forma de Pago", titulo2);
        crearCelda(filaNueva, 2, facCompleta.getFactura().getFormaPagoAdicional() == null ? "" : facCompleta.getFactura().getFormaPagoAdicional(), styleCuerpo);
        crearCelda(filaNueva, 5, "IVA 14%", titulo2);
        crearCelda(filaNueva, 7, facCompleta.getValor14().toString(), styleCuerpo);
        //IRBPNR
        ultimafila = hoja.getLastRowNum() + 1;
        filaNueva = hoja.createRow(ultimafila);
        hoja.addMergedRegion(new CellRangeAddress(ultimafila, ultimafila, 5, 6));
        crearCelda(filaNueva, 5, "IRBPNR", titulo2);
        crearCelda(filaNueva, 7, facCompleta.getValorIrbf().toString(), styleCuerpo);
        //Propinas
        ultimafila = hoja.getLastRowNum() + 1;
        filaNueva = hoja.createRow(ultimafila);
        hoja.addMergedRegion(new CellRangeAddress(ultimafila, ultimafila, 5, 6));
        crearCelda(filaNueva, 5, "Propina", titulo2);
        crearCelda(filaNueva, 7, facCompleta.getFactura().getPropina().toString(), styleCuerpo);
        //Valor Total
        ultimafila = hoja.getLastRowNum() + 1;
        filaNueva = hoja.createRow(ultimafila);
        hoja.addMergedRegion(new CellRangeAddress(ultimafila, ultimafila, 5, 6));
        crearCelda(filaNueva, 5, "Subtotal Sin Impuestos", titulo2);
        crearCelda(filaNueva, 7, facCompleta.getFactura().getImporteTotal().toString(), styleCuerpo);

        String nombreExcel = "Factura.xlsx";
        try (FileOutputStream archivoSalida = new FileOutputStream(nombreExcel)) {
            libro.write(archivoSalida);
            File nuevoArchivo = new File(nombreExcel);
            byte[] archivo = getBytesFromFile(nuevoArchivo);
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment; filename=Factura.xlsx");
            response.getOutputStream().write(archivo);
            response.getOutputStream().flush();
            response.getOutputStream().close();
            context.responseComplete();
            nuevoArchivo.delete();
        } catch (IOException e) {
            System.out.println(e);
        }

    }

    public byte[] getBytesFromFile(File file) throws IOException {
        long length = file.length();

        if (length > Integer.MAX_VALUE) {
            throw new IOException("Archivo es muy largo!");
        }
        byte[] bytes = new byte[(int) length];
        int offset = 0;
        int numRead = 0;
        InputStream is = new FileInputStream(file);
        try {
            while (offset < bytes.length
                    && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
                offset += numRead;
            }
        } finally {
            is.close();
        }
        if (offset < bytes.length) {
            throw new IOException("No se pudo leer completamente el archivo " + file.getName());
        }
        return bytes;
    }

    public XSSFCell crearCelda(XSSFRow fila, int posicion, String texto, XSSFCellStyle style) {
        if (texto.equals("-1")) {
            texto = "";
        }
        if (texto.equals("null")) {
            texto = "";
        }
        XSSFCell celda = fila.createCell((short) posicion);
        XSSFRichTextString valor = new XSSFRichTextString(texto);
        celda.setCellValue(valor);
        celda.setCellStyle(style);
        return celda;
    }

    public XSSFCellStyle titulo(XSSFWorkbook libro, String alineamiento) {
// Se crea el estilo para el cuerpo
        XSSFCellStyle styleCuerpo = libro.createCellStyle();
        if (alineamiento.equals("LEFT")) {
            styleCuerpo.setAlignment(HorizontalAlignment.LEFT);
        } else {
            styleCuerpo.setAlignment(HorizontalAlignment.CENTER);
        }

        styleCuerpo.setVerticalAlignment(VerticalAlignment.CENTER);
        XSSFFont font = libro.createFont();
        font.setFontName("Verdana");
        font.setBold(true);
        font.setItalic(true);
        styleCuerpo.setFont(font);
        styleCuerpo.setWrapText(true);
        return styleCuerpo;
    }

    public XSSFCellStyle stiloCuerpo1(XSSFWorkbook libro) {
// Se crea el estilo para el cuerpo
        XSSFCellStyle styleCuerpo = libro.createCellStyle();
        styleCuerpo.setAlignment(HorizontalAlignment.LEFT);
        styleCuerpo.setVerticalAlignment(VerticalAlignment.CENTER);
        XSSFFont font = libro.createFont();
        font.setFontName("Verdana");
        font.setBold(false);
        font.setItalic(false);
        font.setFamily(10);
        styleCuerpo.setFont(font);
        styleCuerpo.setWrapText(true);
        return styleCuerpo;
    }

    public void agregarTabla(XSSFSheet hoja, List<DetalleFactura> lista, XSSFCellStyle estilo, XSSFCellStyle estilo2) {
        XSSFRow fila = hoja.createRow(hoja.getLastRowNum() + 1);
        crearCelda(fila, 0, "Item", estilo2);
        crearCelda(fila, 1, "Codigo Principal", estilo2);
        crearCelda(fila, 2, "Codigo Auxiliar", estilo2);
        crearCelda(fila, 3, "Descripcion", estilo2);
        crearCelda(fila, 4, "Cantidad", estilo2);
        crearCelda(fila, 5, "Precio", estilo2);
        crearCelda(fila, 6, "Descuento", estilo2);
        crearCelda(fila, 7, "Precio Total", estilo2);
        for (DetalleFactura df : lista) {
            XSSFRow filaNueva = hoja.createRow(hoja.getLastRowNum() + 1);
            crearCelda(filaNueva, 0, df.getIdDetalle().toString(), estilo);
            crearCelda(filaNueva, 1, df.getCodigoPrincipal(), estilo);
            crearCelda(filaNueva, 2, df.getCodigoAuxiliar() == null ? "" : df.getCodigoAuxiliar(), estilo);
            crearCelda(filaNueva, 3, df.getDescripcion(), estilo);
            crearCelda(filaNueva, 4, df.getCantidad().toString(), estilo);
            crearCelda(filaNueva, 5, df.getPrecioUnitario().toString(), estilo);
            crearCelda(filaNueva, 6, df.getDescuento().toString(), estilo);
            crearCelda(filaNueva, 7, df.getPrecioTotalSinimpuesto().toString(), estilo);

        }

    }

   public void generaPdf(FacturaCompletaDto facCompleta) throws FileNotFoundException, DocumentException
    {      
        descripAmbiente=facCompleta.getDescripcionAmbiente();
        descripTipoEmite=facCompleta.getDescripcionTipoEmision();
        val14=facCompleta.getValor14();
        valICE=facCompleta.getValorIce();
        valIRB=facCompleta.getValorIrbf();
        selectFactura=facCompleta.getFactura();
        lisDetalleFactura=facCompleta.getListaDetalle();
        
        String valICE1=String.valueOf(valICE);
        String valIRB1=String.valueOf(valIRB);
        String val141=String.valueOf(val14);
       
        //********************************************************       
        String cedulaRuc=selectFactura.getCedRuc();
        String claAcceso=selectFactura.getClaveAcceso();
        Date fecha=selectFactura.getFechaEmision();       
        String ambiente=descripAmbiente;
        String emision=descripTipoEmite;
        String direccion=selectFactura.getDirEstablecimiento();
        String contrEspecial=selectFactura.getContibuyenteEspecial();
        Boolean contaSiNo=selectFactura.getObligadoContabilidad();
        String razonSocialCompra=selectFactura.getRazonSocialComprador();
        String direcMatriz=selectFactura.getDirMatriz();
        String direcSucursal=selectFactura.getDirEstablecimiento();
        String contribuEspecial=selectFactura.getContibuyenteEspecial();
        String sino;
        //**Información adicional
        String direcAdicional=selectFactura.getDireccionAdicional();
        if(direcAdicional== null || direcAdicional.isEmpty() )
        {
            direcAdicional=" ";
        }    
        String telAdicional=selectFactura.getTelefonoAdicional();
        if(telAdicional==null || telAdicional.isEmpty())
        {
           telAdicional=" " ;
        }
        String venAdicional=selectFactura.getVendedorAdicional();
        if(venAdicional==null || venAdicional.isEmpty())
        {
            venAdicional=" ";
        }
        String cuoAdicional=selectFactura.getCuotaAdicional();
        if(cuoAdicional==null || cuoAdicional.isEmpty()) 
        {
            cuoAdicional="  ";
        }
        String forPagAdicional=selectFactura.getFormaPagoAdicional();
        if(forPagAdicional==null || forPagAdicional.isEmpty())
        {
            forPagAdicional="  ";
        }
        BigDecimal impAdicional=selectFactura.getImporteTotal();
        String impAdicional1=String.valueOf(impAdicional);
        if(impAdicional1==null || impAdicional1.isEmpty() )
        {
            impAdicional1=" ";
        }
        //**Subtotales***************************************************
        BigDecimal totSinImp=selectFactura.getTotalSinimpuesto();
        String totalSinImpuesto=String.valueOf(totSinImp);
        BigDecimal totDes=selectFactura.getTotalDescuento();
        String totalDescuento=String.valueOf(totDes);
        BigDecimal propina=selectFactura.getPropina();
        String propinas=String.valueOf(propina);
        BigDecimal impTot=selectFactura.getImporteTotal();
        String importeTotal=String.valueOf(impTot);
        String subTotCer="0,00";
        String subNoIva="0,00";
        String subTotExtIva="0,00";
        //**************************************************************
        if(contaSiNo == true)
        {
            sino="Si";
        }
        else
        {
           sino="No";
        }    
      //  String nombre=selectFactura.
       
        DateFormat fechaHora = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String fechaConvert = fechaHora.format(fecha);
       
       try 
       {       
             // Se crea el OutputStream para el fichero donde queremos dejar el pdf.
            FileOutputStream ficheroPdf=new FileOutputStream("e://pdf//fichero.pdf"); 
            //Se crea el Documento que tendra el contenido de sus elementos
            Document document = new Document(PageSize.A4);      
         //  PdfWriter.getInstance(document, ficheroPdf).setInitialLeading(20);      
            PdfWriter escribir=PdfWriter.getInstance(document, ficheroPdf);
            document.open(); 
            //Construccion de rectangulo 
            float llx = 33;   
            float lly = 5;   
            float urx = 570;
            float ury = 820;
            PdfContentByte cb = escribir.getDirectContent(); 
            Rectangle rect = new Rectangle(llx, lly, urx, ury);  
           // rect.setBackgroundColor(BaseColor.LIGHT_GRAY);
            rect.setBorder(Rectangle.BOX);  
            rect.setBorderWidth(1);
            cb.rectangle(rect);
             // draw the rect

            //**********************************************************************************8
            Font fontContenido = FontFactory.getFont(FontFactory.TIMES_ROMAN, 9, Font.NORMAL,
              BaseColor.RED);
             Font fontContenido1 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 9, Font.NORMAL, 
              BaseColor.BLUE);
             Font fontContenido2 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Font.NORMAL, 
              BaseColor.BLUE);
             Font fontContenido3 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 9, Font.NORMAL,
              BaseColor.BLUE);
            Font fontContenido4 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Font.NORMAL,
              BaseColor.RED);
            Font fontContenido5 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Font.NORMAL,
              BaseColor.GREEN);
            Font fontContenido6 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.NORMAL,
              BaseColor.DARK_GRAY);
            Font fontContenido7 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.NORMAL,
              BaseColor.BLUE);
             Font fontContenido8 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.NORMAL,
              BaseColor.RED);
             //*******Crea la Tabla General****************************************************
             PdfPTable table = new PdfPTable(3);   
             table.setWidthPercentage(100f); 
          //   table.setTotalWidth(540f);
             table.setWidths(new float[]{3f,8f,11f});         
     
            //************************************************************
            // Obtenemos una instancia de un objeto Image 
            // pasandole por parametro la imagen.
            PdfPTable tabla1=new PdfPTable(1); 
            Image imagen = Image.getInstance("E:/imgenes/facturaLogo70x80.jpg");            
        //    PdfPCell cell2 = new PdfPCell(new Paragraph("Cell 1"));
            PdfPCell cell1 = new PdfPCell(imagen, false);  
            imagen.scalePercent(10f);
//            imagen.scaleAbsoluteWidth(3f);
//            imagen.scaleAbsoluteHeight(10f); 
            imagen.setBorder(PdfPCell.NO_BORDER);
            tabla1.addCell(cell1);
//     
           //**********Titulos ******************************************************* 
           PdfPTable tabla2=new PdfPTable(1);
           PdfPCell celda1=new PdfPCell(new Phrase("R.U.C.  ",fontContenido));      
           PdfPCell celda2=new PdfPCell(new Phrase("FACTURA",fontContenido)); 
           PdfPCell celda3=new PdfPCell(new Phrase("NÚMERO DE AUTORIZACIÓN",fontContenido));
           PdfPCell celda4=new PdfPCell(new Phrase("FECHA Y HORA DE AUTORIZACIÓN",fontContenido));
           PdfPCell celda5=new PdfPCell(new Phrase("AMBIENTE",fontContenido));
           PdfPCell celda6=new PdfPCell(new Phrase("EMISIÓN",fontContenido));
            
            
           tabla2.addCell(celda1);    
           tabla2.addCell(celda2);
           tabla2.addCell(celda3);
           tabla2.addCell(celda4);
           tabla2.addCell(celda5);
           tabla2.addCell(celda6);
           // ***********Valores *************************************************************          
           PdfPTable tabla3=new PdfPTable(1);
           PdfPCell celda13=new PdfPCell(new Phrase(cedulaRuc,fontContenido1)); 
           PdfPCell celda23=new PdfPCell(new Phrase(" ")); 
           PdfPCell celda33=new PdfPCell(new Phrase(claAcceso,fontContenido1));
           PdfPCell celda43=new PdfPCell(new Phrase(fechaConvert,fontContenido1));
           PdfPCell celda53=new PdfPCell(new Phrase(ambiente,fontContenido1));
           PdfPCell celda63=new PdfPCell(new Phrase(emision,fontContenido1)); 
           
           tabla3.addCell(celda13);
           tabla3.addCell(celda23);
           tabla3.addCell(celda33);
           tabla3.addCell(celda43);
           tabla3.addCell(celda53);
           tabla3.addCell(celda63);
           //*************Graba la Tabla********************************************************
           PdfPTable tablaFinal=new PdfPTable(1);
           PdfPCell celdas=new PdfPCell(new Paragraph("Hola 2"));
           celdas.setColspan(3);
           tablaFinal.addCell(celdas); 
           table.addCell(tabla1);
           table.addCell(tabla2);
           table.addCell(tabla3);  
           
           table.addCell(tablaFinal);   
           document.add(table); 
           //***********Datos Empresa *************************************************
           Phrase fraseEmp=new Phrase(45);
           fraseEmp.add(Chunk.NEWLINE);
           fraseEmp.add(new Chunk("SANSUR IMPORTACIONES Y COMPANÍA",fontContenido5)); 
           fraseEmp.add(Chunk.NEWLINE);
           fraseEmp.add(new Chunk("Dirección Matríz  ",fontContenido4));
           fraseEmp.add(new Chunk(direcMatriz,fontContenido3));
           fraseEmp.add(Chunk.NEWLINE);
           fraseEmp.add(new Chunk("Dirección Sucursal ",fontContenido4));
           fraseEmp.add(new Chunk(direcSucursal,fontContenido3));
           fraseEmp.add(Chunk.NEWLINE);
           fraseEmp.add(new Chunk("Contribuyente Especial Nro  ",fontContenido4));
           fraseEmp.add(new Chunk(contribuEspecial,fontContenido3));
           fraseEmp.add(Chunk.NEWLINE);
           fraseEmp.add(new Chunk("OBLIGADO A LLEVAR CONTABILIDAD  ",fontContenido4)); 
           fraseEmp.add(new Chunk(sino,fontContenido3));
           document.add(fraseEmp);
           
            float llx2 =33;   
            float lly2 =690;  
            float urx2 = 360;
            float ury2 = 480; 
            PdfContentByte cb2 = escribir.getDirectContent();  
            Rectangle rect2 = new Rectangle(llx2, lly2, urx2, ury2);  
           // rect.setBackgroundColor(BaseColor.LIGHT_GRAY);
            rect2.setBorder(Rectangle.BOX);
            rect2.setBorderWidth(1);
            cb2.rectangle(rect2);
//           //***********Datos Cliente ***************************************************
            String espacio="               ";
            Paragraph parrafo = new Paragraph();  
            parrafo.setSpacingBefore(350); 
            Phrase frase=new Phrase(45);   
            frase.add(Chunk.NEWLINE);
            frase.add(new Chunk("CLIENTE    ",fontContenido));         
            frase.add(new Chunk(razonSocialCompra,fontContenido3));  
            frase.add(new Chunk(espacio));
            frase.add(new Chunk("Identificación    ",fontContenido));            
            frase.add(new Chunk(cedulaRuc,fontContenido3)); 
            frase.add(Chunk.NEWLINE);
            frase.add(new Chunk("Fecha Emisión    ",fontContenido));             
            frase.add(new Chunk(fechaConvert,fontContenido3)); 
            frase.add(new Chunk("                                        "));
            frase.add(new Chunk("Guía    ",fontContenido));           
            frase.add(new Chunk(" ",fontContenido3));   

            document.add(frase);
            float llx1 =33; //posicion x 
            float lly1 =470; //posicion y 
            float urx1 = 545; //ancho
            float ury1 = 385;//largo
            PdfContentByte cb1 = escribir.getDirectContent(); 
            Rectangle rect1 = new Rectangle(llx1, lly1, urx1, ury1);  
           // rect.setBackgroundColor(BaseColor.LIGHT_GRAY);
            rect1.setBorder(Rectangle.BOX);
            rect1.setBorderWidth(1);
            cb1.rectangle(rect1);
 
           PdfPTable tabla=new PdfPTable(7); 
      //     tabla.setWidthPercentage(100);
           tabla.setHorizontalAlignment(Element.ALIGN_CENTER);
           //float[] medidaCeldas={3.30f,3.30f,3.50f,4.50f,3.30f,3.30f,3.30f}; 
           tabla.setTotalWidth(500);
           tabla.setLockedWidth(true); 
           tabla.setWidths(new float[]{5,5,14,5,5,6,5});   
         
          // tabla.addCell(new Paragraph("Item",fontContenido)); 
           
           tabla.addCell(new Paragraph("Código Principal",fontContenido));
           tabla.addCell(new Paragraph("Código Auxiliar",fontContenido));
           tabla.addCell(new Paragraph("Descripión de Item o Producto",fontContenido));
           tabla.addCell(new Paragraph("Cantidad",fontContenido));
           tabla.addCell(new Paragraph("Precio Unitario",fontContenido));
           tabla.addCell(new Paragraph("Descuento",fontContenido));
           tabla.addCell(new Paragraph("Precio Total",fontContenido));
      
            for (DetalleFactura df:lisDetalleFactura) 
            {
                String coda=df.getCodigoAuxiliar();
                String codp=df.getCodigoPrincipal();
                String desc=df.getDescripcion();
                Double cant=df.getCantidad();
                Double preu=df.getPrecioUnitario();
                Double descuento=df.getDescuento();
                Double pret=df.getPrecioTotalSinimpuesto();
             //   String descuento=df.getDescuento().toString();
               
                String cant1=String.valueOf(cant);
                String preu1=String.valueOf(preu);
                String pret1=String.valueOf(pret);
                String descuento1=String.valueOf(descuento); 
                
                tabla.addCell(new Paragraph(codp,fontContenido3)); 
                tabla.addCell(new Paragraph(coda,fontContenido3));
                tabla.addCell(new Paragraph(desc,fontContenido3));
                tabla.addCell(new Paragraph(cant1,fontContenido3));
                tabla.addCell(new Paragraph(preu1,fontContenido3));
                tabla.addCell(new Paragraph(descuento1,fontContenido3)); 
                tabla.addCell(new Paragraph(pret1,fontContenido3));
        
            }
               
             document.add(tabla);
             //*******Detalles y Subtotales ***************************************
             Paragraph parrafoInf=new Paragraph();
             parrafoInf.add(new Phrase("Información y Detalles Adicionales  ",fontContenido6));           
             document.add (parrafoInf);
             PdfPTable tabDetSub=new PdfPTable(4); 
          //   tabDetSub.setTotalWidth(900);
             tabDetSub.setWidthPercentage(100f);
             float[] medidasCeldas={8f,20f,17f,6f}; 
             tabDetSub.setWidths(medidasCeldas);
           
              
           //** Tabla 1***
             PdfPTable tab1=new PdfPTable(1);
             PdfPCell celdas1=new PdfPCell(new Phrase("Dirección   ",fontContenido8));      
             PdfPCell celdas2=new PdfPCell(new Phrase("Teléfono   ",fontContenido8)); 
             PdfPCell celdas3=new PdfPCell(new Phrase("Vendedor   ",fontContenido8));
             PdfPCell celdas4=new PdfPCell(new Phrase("Cuota 1  ",fontContenido8));
             PdfPCell celdas5=new PdfPCell(new Phrase("Formza de pago  ",fontContenido8));
             PdfPCell celdas6=new PdfPCell(new Phrase("Valor Pago  ",fontContenido8));
             
             tab1.addCell(celdas1); 
             tab1.addCell(celdas2);
             tab1.addCell(celdas3);
             tab1.addCell(celdas4);
             tab1.addCell(celdas5); 
             tab1.addCell(celdas6); 
             //**Tabla2
             PdfPTable tab2=new PdfPTable(1);
             PdfPCell celdas11=new PdfPCell(new Phrase(direcAdicional,fontContenido7));      
             PdfPCell celdas21=new PdfPCell(new Phrase(telAdicional,fontContenido7)); 
             PdfPCell celdas31=new PdfPCell(new Phrase(venAdicional,fontContenido7));
             PdfPCell celdas41=new PdfPCell(new Phrase(cuoAdicional,fontContenido7));
             PdfPCell celdas51=new PdfPCell(new Phrase(forPagAdicional,fontContenido7));
             PdfPCell celdas61=new PdfPCell(new Phrase(impAdicional1,fontContenido7));
             
             tab2.addCell(celdas11);  
             tab2.addCell(celdas21);
             tab2.addCell(celdas31);
             tab2.addCell(celdas41);
             tab2.addCell(celdas51);  
             tab2.addCell(celdas61);
             //**Tabla3
             PdfPTable tab3=new PdfPTable(1);
             PdfPCell celdass1=new PdfPCell(new Phrase("SUBTOTAL 14%   ",fontContenido8));            
             PdfPCell celdass2=new PdfPCell(new Phrase("Subtotal 0%   ",fontContenido8));             
             PdfPCell celdass3=new PdfPCell(new Phrase("Subtotal No objeto IVA",fontContenido8));           
             PdfPCell celdass4=new PdfPCell(new Phrase("Subtotal Exento de IVA",fontContenido8));
             PdfPCell celdass5=new PdfPCell(new Phrase("Subtotal Sin Impuestos   ",fontContenido8));
             PdfPCell celdass6=new PdfPCell(new Phrase("Total Descuento   ",fontContenido8));
             PdfPCell celdass7=new PdfPCell(new Phrase("ICE   ",fontContenido8));
             PdfPCell celdass8=new PdfPCell(new Phrase("IVA 14%  ",fontContenido8));
             PdfPCell celdass9=new PdfPCell(new Phrase("IRBPNR   ",fontContenido8)); 
             PdfPCell celdass10=new PdfPCell(new Phrase("PROPINAS 14%  ",fontContenido8)); 
             PdfPCell celdass11=new PdfPCell(new Phrase("VALOR TOTAL   ",fontContenido8));
             
             tab3.addCell(celdass1); 
             tab3.addCell(celdass2);
             tab3.addCell(celdass3);
             tab3.addCell(celdass4);
             tab3.addCell(celdass5);
             tab3.addCell(celdass6);
             tab3.addCell(celdass7);
             tab3.addCell(celdass8); 
             tab3.addCell(celdass9); 
             tab3.addCell(celdass10);
             tab3.addCell(celdass11);
   
             //**Tabla 4
             PdfPTable tab4=new PdfPTable(1);
             PdfPCell cel1=new PdfPCell(new Phrase(totalSinImpuesto,fontContenido7));
             PdfPCell cel2=new PdfPCell(new Phrase(subTotCer,fontContenido7));
             PdfPCell cel3=new PdfPCell(new Phrase(subNoIva,fontContenido7));
             PdfPCell cel4=new PdfPCell(new Phrase(subTotExtIva,fontContenido2));
             PdfPCell cel5=new PdfPCell(new Phrase(totalSinImpuesto,fontContenido2));
             PdfPCell cel6=new PdfPCell(new Phrase(totalDescuento,fontContenido7));
             PdfPCell cel7=new PdfPCell(new Phrase(valICE1,fontContenido7));
             PdfPCell cel8=new PdfPCell(new Phrase(val141,fontContenido7));
             PdfPCell cel9=new PdfPCell(new Phrase(valIRB1,fontContenido7));
             PdfPCell cel10=new PdfPCell(new Phrase(propinas,fontContenido7));
              PdfPCell cel11=new PdfPCell(new Phrase(importeTotal,fontContenido7)); 
             
             tab4.addCell(cel1); 
             tab4.addCell(cel2);
             tab4.addCell(cel3);
             tab4.addCell(cel4);
             tab4.addCell(cel5);
             tab4.addCell(cel6);
             tab4.addCell(cel7);
             tab4.addCell(cel8);
             tab4.addCell(cel9);
              tab4.addCell(cel10);
             tab4.addCell(cel11);
             
             //Tabla Final
            PdfPTable tabFin=new PdfPTable(1);
        //    PdfPTable tablaFinal=new PdfPTable(1);
            PdfPCell celda=new PdfPCell(new Paragraph("Hola 2"));
            celda.setColspan(4); 
            tabFin.addCell(celda); 
            tabDetSub.addCell(tab1);
            tabDetSub.addCell(tab2);
            tabDetSub.addCell(tab3);  
            tabDetSub.addCell(tab4); 
           
            tabDetSub.addCell(tabFin);      
            document.add(tabDetSub);
          
            document.close();
            
            //Mensaje
            FacesMessage pdfsi=new FacesMessage();
            pdfsi.setSeverity(FacesMessage.SEVERITY_INFO); 
            pdfsi.setSummary("PDF Generado..");
            
            FacesContext.getCurrentInstance().addMessage("men", pdfsi);
          
       }
       catch(DocumentException | IOException ex)
       {
          //System.out.println("No escribió..");
          FacesMessage pdfno=new FacesMessage();
          pdfno.setSeverity(FacesMessage.SEVERITY_ERROR);
          pdfno.setSummary("NO Genera PDF");
          
          FacesContext.getCurrentInstance().addMessage("msj", pdfno);
       }    
    } 
}
