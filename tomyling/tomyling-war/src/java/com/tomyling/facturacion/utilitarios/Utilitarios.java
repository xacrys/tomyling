/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomyling.facturacion.utilitarios;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
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

    public void generaXls() {

        // Se crea el libro
        XSSFWorkbook libro = new XSSFWorkbook();
        // Se crea una hoja dentro del libro 
        XSSFSheet hoja = libro.createSheet();
        //XSSFCellStyle styleTitulo = stiloCabecera(libro);
        //XSSFCellStyle styleCuerpo1 = stiloCuerpo1(libro);

        // Se crea la fila1 y la fila 2 vacías dentro de la hoja
        XSSFRow fila1 = hoja.createRow(1);
        hoja.setColumnWidth(1, 6400);
        hoja.addMergedRegion(new CellRangeAddress(1, 1, 1, 10));
        //crearCelda(fila1, 1, "Indicador: ECUADOR - " + indicador, styleTitulo);

        XSSFRow fila2 = hoja.createRow(2);
        XSSFRow fila4 = hoja.createRow(hoja.getLastRowNum() + 2);
        XSSFRow fila5 = hoja.createRow(hoja.getLastRowNum() + 1);

//        styleCuerpo.setFillForegroundColor(XSSFColor.toXSSFColor(new XSSFColor(Color.BLACK)));
        //se carga los datos de las celdas de la primera fila
        //primeraFila.add(0, variableTitulo);
//        Integer i = 2;
//        Integer j = 1;
//        for (String pf : primeraFila) {
//            // Se crean las celdas dentro de la fila
//            if (primeraFila.indexOf(pf) == 0) {
//                crearCelda(fila4, 1, pf, styleTitulo);
//                crearCelda(fila5, 1, "", styleTitulo);
//
//            } else if (tipoIndicador == 4 || tipoIndicador == 7) {
//
//                for (String sf : segundaFila) {
//                    int posicion = primeraFila.indexOf(pf) + j + segundaFila.indexOf(sf);
//                    crearCelda(fila4, posicion, pf, styleTitulo);
//                    crearCelda(fila5, posicion, sf, styleTitulo);
//                }
//                j++;
//                i++;
//            } else {
//                for (String sf : segundaFila) {
//                    int posicion = primeraFila.indexOf(pf) + 1 + segundaFila.indexOf(sf);
//                    crearCelda(fila4, posicion, pf, styleTitulo);
//                    crearCelda(fila5, posicion, sf, styleTitulo);
//                }
//            }
//            hoja.setColumnWidth(i, 6000);
//            i++;
//        }
//        if (primeraFila.contains("Error estándar")) {
//            crearFilasXHojaEstadistico(hoja, arbolDatos, "", libro, "P");
//        } else {
//            crearFilasXHoja(hoja, arbolDatos.getChildren().get(0), hoja.getLastRowNum() + 1, "", libro, tipoIndicador);
//        }
        hoja.addMergedRegion(new CellRangeAddress(4, 5, 1, 1));
        hoja.createRow(hoja.getLastRowNum() + 1);
        XSSFRow filaPie = hoja.createRow(hoja.getLastRowNum() + 1);
        //crearCelda(filaPie, 1, "Elaborado por: " + nombreSistema, styleTitulo);
        hoja.addMergedRegion(new CellRangeAddress(hoja.getLastRowNum(), hoja.getLastRowNum(), 1, 10));

        filaPie = hoja.createRow(hoja.getLastRowNum() + 1);
        //crearCelda(filaPie, 1, "Fuente : " + fuente, styleTitulo);
        hoja.addMergedRegion(new CellRangeAddress(hoja.getLastRowNum(), hoja.getLastRowNum(), 1, 10));

//        filaPie = hoja.createRow(hoja.getLastRowNum() + 2);
//        crearCelda(filaPie, 1, "MINISTERIO COORDINADOR DE DESARROLLO SOCIAL - SUBSECRETARÍA DE GESTIÓN DE INFORMACIÓN", styleTitulo);
//        hoja.addMergedRegion(new CellRangeAddress(hoja.getLastRowNum(), hoja.getLastRowNum(), 1, 10));
//        hoja.addMergedRegion(new CellRangeAddress(hoja.getLastRowNum(), hoja.getLastRowNum(), 1, primeraFila.size() + 1));
        XSSFRow fecha = hoja.createRow(hoja.getLastRowNum() + 1);
        Calendar c = Calendar.getInstance();
        //crearCelda(fecha, 1, "Fecha de descarga: " + Integer.toString(c.get(Calendar.DATE)) + "/" + Integer.toString(c.get(Calendar.MONTH) + 1) + "/" + Integer.toString(c.get(Calendar.YEAR)), styleTitulo);
        hoja.addMergedRegion(new CellRangeAddress(hoja.getLastRowNum(), hoja.getLastRowNum(), 1, 10));

        // Se salva el libro.
        try {
//            FileOutputStream elFichero = new FileOutputStream("holamundo.xls");
//            libro.write(elFichero);
//            elFichero.close();

            String arch = "Indicador.xls";
            FileOutputStream file = new FileOutputStream(arch);

            libro.write(file);
            file.close();
            File fil = new File(arch);
            byte[] archivo = getBytesFromFile(fil);
//            String nombre = indicador.replace("ñ", "n").replace(" ", "").replace("/", "").replace("(", "").replace(")", "").replace(",", "");
//            nombre = nombre.replace("Ã³", "ó").replace("Ã¡", "á").replace("Ã©", "é").replace("Ã­", "í").replace("Â Â Â Â", "").replace("Ãº", "ú");
//            nombre = nombre.replace(" ", "").replace("á", "a").replace("é", "e").replace("í", "i").replace("ó", "o").replace("ú", "u");
//            nombre = nombre.replace("(", "").replace(")", "").replace("/", "");

            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-disposition", "attachment;filename=" + "as" + ".xls");
            response.getOutputStream().write(archivo);
            response.getOutputStream().flush();
            response.getOutputStream().close();
            context.responseComplete();
            fil.delete();

        } catch (Exception e) {
            e.printStackTrace();
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

    public void generarExcel() {
        //Blank workbook
        XSSFWorkbook workbook = new XSSFWorkbook();

        //Create a blank sheet
        XSSFSheet sheet = workbook.createSheet("Employee Data");

        //This data needs to be written (Object[])
        Map<String, Object[]> data = new TreeMap<String, Object[]>();
        data.put("1", new Object[]{"ID", "NAME", "LASTNAME"});
        data.put("2", new Object[]{1, "Amit", "Shukla"});
        data.put("3", new Object[]{2, "Lokesh", "Gupta"});
        data.put("4", new Object[]{3, "John", "Adwards"});
        data.put("5", new Object[]{4, "Brian", "Schultz"});

        //Iterate over data and write to sheet
        Set<String> keyset = data.keySet();
        int rownum = 0;
        for (String key : keyset) {
            Row row = sheet.createRow(rownum++);
            Object[] objArr = data.get(key);
            int cellnum = 0;
            for (Object obj : objArr) {
                Cell cell = row.createCell(cellnum++);
                if (obj instanceof String) {
                    cell.setCellValue((String) obj);
                } else if (obj instanceof Integer) {
                    cell.setCellValue((Integer) obj);
                }
            }
        }
        try {
            //Write the workbook in file system
            FileOutputStream out = new FileOutputStream(new File("howtodoinjava_demo.xlsx"));
            workbook.write(out);
            out.close();
            System.out.println("howtodoinjava_demo.xlsx written successfully on disk.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
