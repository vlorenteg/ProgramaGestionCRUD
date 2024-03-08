package es.studium.Gestion;


import java.awt.FlowLayout;

import java.awt.Frame;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;



public class ConsultaOficinas implements WindowListener, ActionListener{
	Frame ConsultaOficinas = new Frame(" Oficinas ");
	TextArea txaConsulta = new TextArea(10, 25);
	Datos c = new Datos();
		
	ConsultaOficinas()
	{
		ConsultaOficinas.setSize(260, 250);
		ConsultaOficinas.setLayout(new FlowLayout());
		ConsultaOficinas.addWindowListener(this);
		c.consultaOficina(txaConsulta);
		ConsultaOficinas.add(txaConsulta);
		txaConsulta.setEditable(false);
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
		public void actionPerformed(ActionEvent e){}
}
	
		
