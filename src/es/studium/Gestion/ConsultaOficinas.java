package es.studium.Gestion;


import java.awt.Button;
import java.awt.Desktop;
import java.awt.FlowLayout;

import java.awt.Frame;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.UnitValue;



public class ConsultaOficinas implements WindowListener, ActionListener{
	Frame ConsultaOficinas = new Frame(" Oficinas ");
	TextArea txaConsulta = new TextArea(6, 35);
	Button btnPdf = new Button("Exportar a PDF");
	Datos c = new Datos();
		
	ConsultaOficinas()
	{
		ConsultaOficinas.setSize(300, 200);
		ConsultaOficinas.setLayout(new FlowLayout());
		ConsultaOficinas.addWindowListener(this);
		c.consultaOficina(txaConsulta);
		ConsultaOficinas.add(txaConsulta);
		txaConsulta.setEditable(false);
		ConsultaOficinas.add(btnPdf);
		btnPdf.addActionListener(this);
		ConsultaOficinas.setResizable(false);
		ConsultaOficinas.setLocationRelativeTo(null);
		ConsultaOficinas.setVisible(true);
	}

		@Override
		public void windowOpened(WindowEvent e) {}
		@Override
		public void windowClosing(WindowEvent e) {
			ConsultaOficinas.setVisible(false);
		}
		@Override
		public void windowClosed(WindowEvent e) {}
		@Override
		public void windowIconified(WindowEvent e) {}
		@Override
		public void windowDeiconified(WindowEvent e) {}
		@Override
		public void windowActivated(WindowEvent e) {}	
		@Override
		public void windowDeactivated(WindowEvent e) {}
		@Override
		public void actionPerformed(ActionEvent e){
			if(e.getSource().equals(btnPdf))
			{
				String dest = "ListadoOficinas.pdf";

				try
				{
					// Inicializar PDF writer
					PdfWriter writer = new PdfWriter(dest);

					// Inicializar PDF document
					PdfDocument pdf = new PdfDocument(writer);

					// Inicializar documento
					Document document = new Document(pdf);

					// Crear fuente para el encabezado
					PdfFont fontHeader = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);

					// Crear fuente para los datos
					PdfFont fontDatos = PdfFontFactory.createFont(StandardFonts.HELVETICA);

					// Crear tabla y configurar anchos de columna
					Table tabla = new Table(UnitValue.createPercentArray(new float[]
					{ 1, 1, 1 }));
					tabla.setWidth(UnitValue.createPercentValue(100));

					// Agregar encabezados a la tabla
					tabla.addHeaderCell(new Cell().add(new Paragraph("ID").setFont(fontHeader).setBold()));
					tabla.addHeaderCell(new Cell().add(new Paragraph("Direccion Oficina").setFont(fontHeader).setBold()));
					tabla.addHeaderCell(new Cell().add(new Paragraph("Telefono Cliente").setFont(fontHeader).setBold()));

					// Agregar datos a la tabla desde el TextArea
					String[] lineas = txaConsulta.getText().split("\n");
					for (String linea : lineas)
					{
						String[] datos = linea.split(" - ");
						for (String dato : datos)
						{
							tabla.addCell(new Cell().add(new Paragraph(dato).setFont(fontDatos)));
						}
					}

					// Agregar la tabla al documento
					document.add(tabla);

					// Cerrar documento
					document.close();

					// Abrir el PDF generado
					Desktop.getDesktop().open(new File(dest));
				} catch (IOException ioe)
				{
					ioe.printStackTrace();
				}
			}
		}
}
	
		
