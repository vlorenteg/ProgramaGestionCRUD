package es.studium.Gestion;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Baja implements WindowListener, ActionListener
{
	Frame BajaOficina = new Frame("Baja Oficina");
	Dialog dlgSeguro = new Dialog(BajaOficina, "¿Está seguro?", true);
	Dialog dlgMensaje = new Dialog(BajaOficina, "Mensaje", true);
	Label lblElegir = new Label("  -  Seleccione la Oficina que desea eliminar:  - ");
	Label lblSeguro = new Label("¿Está seguro de eliminar?");
	Label lblMensaje = new Label("Usuario Eliminado");
	Choice choOficinas = new Choice();
	Button btnEliminar = new Button("Eliminar");
	Button btnSi = new Button("Sí");
	Button btnNo = new Button("No");
	Datos c = new Datos();
	
	Baja(){
        c.rellenarModificacionOficinas(choOficinas);
		BajaOficina.setSize(260, 250);
		BajaOficina.setLayout(new FlowLayout());
		BajaOficina.addWindowListener(this);
		BajaOficina.add(lblElegir);
		c.bajaOficina(choOficinas);
		BajaOficina.add(choOficinas);
		btnEliminar.addActionListener(this);
		BajaOficina.add(btnEliminar);
		BajaOficina.setResizable(false);
		BajaOficina.setLocationRelativeTo(null);
		BajaOficina.setVisible(true);
		}
	

	public void actionPerformed(ActionEvent e)
	{
		String tabla[] = choOficinas.getSelectedItem().split("-");

		if (e.getSource().equals(btnEliminar))
		{
			String tabla1[] = choOficinas.getSelectedItem().split("-");
			if (choOficinas.getSelectedIndex() != 0)
			{
				int respuesta = c.bajaOficina(tabla1[0]);
				dlgSeguro.setLayout(new FlowLayout());
				dlgSeguro.setSize(340,100);
				dlgSeguro.addWindowListener(this);
				lblSeguro.setText("¿Está seguro de eliminar la Oficina: " + tabla1[1] + "?");
				dlgSeguro.add(lblSeguro);
				btnSi.addActionListener(this);
				dlgSeguro.add(btnSi);
				btnNo.addActionListener(this);
				dlgSeguro.add(btnNo);
				dlgSeguro.setResizable(false);
				dlgSeguro.setLocationRelativeTo(null);
				dlgSeguro.setVisible(true);
			}
		}
		else if (e.getSource().equals(btnNo))
		{
			dlgSeguro.setVisible(false);
		}
		else if (e.getSource().equals(btnSi))
		{
			int respuesta = c.bajaOficina(tabla[0]);
			dlgMensaje.setLayout(new FlowLayout());
			dlgMensaje.setSize(170,100);
			dlgMensaje.addWindowListener(this);
			if(respuesta==0)
			{
				lblMensaje.setText("Oficina Eliminada");
			}
			else
			{
				lblMensaje.setText("Error al eliminar");
			}
			dlgMensaje.add(lblMensaje);
			dlgMensaje.setResizable(false);
			dlgMensaje.setLocationRelativeTo(null);
			dlgMensaje.setVisible(true);
		}
		
	}
	
	public void windowClosing(WindowEvent e)
	{
		if(dlgSeguro.isActive())
		{
			dlgSeguro.setVisible(false);
		}
		else if(dlgMensaje.isActive())
		{
			dlgMensaje.setVisible(false);
			dlgSeguro.setVisible(false);
			BajaOficina.setVisible(false);
		}
		else
		{
			BajaOficina.setVisible(false);
		}
	}

	public void windowOpened(WindowEvent e){}

	public void windowClosed(WindowEvent e){}

	public void windowIconified(WindowEvent e){}

	public void windowDeiconified(WindowEvent e){}

	public void windowActivated(WindowEvent e){}

	public void windowDeactivated(WindowEvent e){}
}
