/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomyling.facturacion.utilitarios;

import com.tomyling.facturacion.dto.FacturaCompletaDto;
//*****************************************************
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.tomyling.facturacion.modelo.DetalleFactura;
//****************************************************
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.List;
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

    public void generaPdf(FacturaCompletaDto facCompleta) throws FileNotFoundException, DocumentException {
        //Se crea el documento
        Document documento = new Document();
        // Se crea el OutputStream para el fichero donde queremos dejar el pdf.
        try {
            // Se crea el OutputStream para el fichero donde queremos dejar el pdf.
            // FileOutputStream ficheroPdf = new FileOutputStream("fichero.pdf");
            FileOutputStream ficheroPdf = new FileOutputStream("fichero.pdf");
            // Se asocia el documento al OutputStream
            PdfWriter.getInstance(documento, ficheroPdf);
            // Se abre el documento.
            documento.open();
            //Anadir párrafos
            Paragraph parrafo = new Paragraph();
            parrafo.add("este es un PDF");
            documento.add(parrafo);
            documento.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
