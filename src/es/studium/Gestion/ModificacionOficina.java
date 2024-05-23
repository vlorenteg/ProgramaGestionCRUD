package es.studium.Gestion;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class ModificacionOficina implements WindowListener, ActionListener
{
	Frame Modificar = new Frame("Modificar");
	Dialog dlgEditar = new Dialog(Modificar, "Modificar", true);
	Dialog dlgMensaje = new Dialog(Modificar, "Mensaje", true);
	Label lblElegir = new Label(" Elige una oficina para modificar:");
	Choice choOficinas = new Choice();
	Datos c = new Datos();
	Button btnMod = new Button("Modificar");
	Label lblMod = new Label(" - Modificación de oficina -");
	Label lblDireccion = new Label("Dirección:");
	Label lblTelefono = new Label("Teléfono:");
	Label lblMensaje = new Label("Modificación de Oficina Correcta");
	TextField txtDireccion = new TextField(15);
	TextField txtTelefono = new TextField(15);
	Button btnMod2 = new Button("Modificar");
	Button btnCancelar = new Button("Cancelar");
	String idOficina = "";

	ModificacionOficina(){
		Modificar.setLayout(new FlowLayout());
		Modificar.setSize(260,250);
		Modificar.addWindowListener(this);
		Modificar.add(lblElegir);
		c.rellenarChoiceOficinas(choOficinas);
		Modificar.add(choOficinas);
		btnMod.addActionListener(this);
		Modificar.add(btnMod);
		Modificar.setResizable(false);
		Modificar.setLocationRelativeTo(null);
		Modificar.setVisible(true);
	}
	

	
	public void windowOpened(WindowEvent e){}	

	public void windowClosed(WindowEvent e){}
	
	public void windowIconified(WindowEvent e){}
	
	public void windowDeiconified(WindowEvent e){}
	
	public void windowActivated(WindowEvent e){}
	
	public void windowDeactivated(WindowEvent e){}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource().equals(btnMod))
		{
			if(choOficinas.getSelectedIndex()!=0)
			{
				dlgEditar.setLayout(new FlowLayout());
				dlgEditar.setSize(260,250);
				dlgEditar.addWindowListener(this);
				btnMod2.addActionListener(this);
				dlgEditar.add(lblElegir);
				dlgEditar.add(lblDireccion);
				dlgEditar.add(txtDireccion);
				dlgEditar.add(lblTelefono);
				dlgEditar.add(txtTelefono);
				btnMod.addActionListener(this);
				btnCancelar.addActionListener(this);
				dlgEditar.add(btnMod2);
				dlgEditar.add(btnCancelar);
				dlgEditar.setResizable(false);
				dlgEditar.setLocationRelativeTo(null);
				String tabla[] = choOficinas.getSelectedItem().split("-");
				String resultado = c.getOficinaEditar(tabla[0]);
				String datos[] = resultado.split("-");
				idOficina = datos[0];
				txtDireccion.setText(datos[1]);
				txtTelefono.setText(String.valueOf(datos[2]));
				dlgEditar.setVisible(true);
			}
		}
		else if(e.getSource().equals(btnMod2))
		{
		    dlgMensaje.setLayout(new FlowLayout());
		    dlgMensaje.setSize(260,250);
		    dlgMensaje.addWindowListener(this);

		    if(txtDireccion.getText().length()==0 || txtTelefono.getText().length()==0)
		    {
		        lblMensaje.setText("Los campos están vacíos");
		    }
		    else
		    {
		        String sentencia = "update oficinas set direccionOficina='" + txtDireccion.getText() +
		                            "', telefonoOficina='" + txtTelefono.getText() +
		                            "' where idOficina = " + idOficina + ";";

		        int respuesta = c.modificarOficinas(sentencia);
		        if(respuesta != 0)
		        {
		            lblMensaje.setText("Error al Modificar");
		        }
		        else
		        {
		            lblMensaje.setText("Modificación de Oficina Correcta");
		        }
		    }
		    dlgMensaje.add(lblMensaje);
		    dlgMensaje.setResizable(false);
		    dlgMensaje.setLocationRelativeTo(null);
		    dlgMensaje.setVisible(true);
		}

		else if (e.getSource().equals(btnCancelar))
		{
			dlgEditar.setVisible(false);
		}
	}
	
	public void windowClosing(WindowEvent e)
	{
		if(dlgEditar.isActive())
		{
			dlgEditar.setVisible(false);
			Modificar.setVisible(false);
		}
		else if (dlgMensaje.isActive())
		{
			dlgMensaje.setVisible(false);
		}
		else
		{
			Modificar.setVisible(false);
		}
	}
}
