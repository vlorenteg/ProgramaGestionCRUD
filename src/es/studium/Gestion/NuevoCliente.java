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

public class NuevoCliente implements WindowListener, ActionListener
{
	Frame NuevoCliente = new Frame("Nuevo Cliente");
	Dialog dlgMensaje = new Dialog(NuevoCliente, "Mensaje", true);
	Label lblAlta = new Label(" - Nuevo Cliente -");
	Label lblOficina = new Label("Oficina:");
	TextField txtOficina = new TextField(15);
	Choice choOficina = new Choice();
	Label lblNombre = new Label("Nombre:");
	Label lblMovil = new Label("Teléfono:");
	Label lblDireccion = new Label("Dirección:");
	Label lblMensaje = new Label("Alta de Empleado Correcta");
	TextField txtNombre = new TextField(15);
	TextField txtMovil = new TextField(15);
	TextField txtDireccion = new TextField(15);
	Button btnAceptar = new Button("Aceptar");
	Button btnCancelar = new Button("Cancelar");
	Datos c = new Datos();
	
	NuevoCliente()
	{
		NuevoCliente.setSize(170, 370);
		NuevoCliente.setLayout(new FlowLayout());
		NuevoCliente.addWindowListener(this);
		NuevoCliente.add(lblAlta);
		NuevoCliente.add(lblOficina);
		c.rellenarChoiceOficinas(choOficina);
		NuevoCliente.add(choOficina);
		NuevoCliente.add(lblNombre);
		NuevoCliente.add(txtNombre);
		NuevoCliente.add(lblMovil);
		NuevoCliente.add(txtMovil);
		NuevoCliente.add(lblDireccion);
		NuevoCliente.add(txtDireccion);
		btnAceptar.addActionListener(this);
		btnCancelar.addActionListener(this);
		NuevoCliente.add(btnAceptar);
		NuevoCliente.add(btnCancelar);
		NuevoCliente.setResizable(false);
		NuevoCliente.setLocationRelativeTo(null);
		NuevoCliente.setVisible(true);
		
	}

	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource().equals(btnAceptar))
		{
			dlgMensaje.setSize(225, 100);
			dlgMensaje.setLayout(new FlowLayout());
			dlgMensaje.addWindowListener(this);
			
			String[] choiceOficinas = null;
			String selectedItem = choOficina.getSelectedItem();
			if (selectedItem != null) 
			{
			    choiceOficinas = selectedItem.split("-");
			}
			if(txtNombre.getText().length()==0||txtDireccion.getText().length()==0)
			{
				lblMensaje.setText("Algún campo no ha sido rellenado");
			}
			else if(txtMovil.getText().length()!=9)
			{
				lblMensaje.setText("El Teléfono no es válido");
			}
			
			else
			{
				String sentencia = "insert into clientes values (null, '" + txtNombre.getText() + "', '" + txtMovil.getText() + "', '" + txtDireccion.getText() + "', '" + choiceOficinas[0] + "')";
				int respuesta = c.nuevoCliente(sentencia);
				if(respuesta!=0)
				{
					lblMensaje.setText("Ha ocurrido un error");
				}
				else
				{
					lblMensaje.setText("Alta de Cliente Correcta");
				}
			}
			dlgMensaje.add(lblMensaje);
			dlgMensaje.setResizable(false);
			dlgMensaje.setLocationRelativeTo(null);
			dlgMensaje.setVisible(true);
		}
		else if(e.getSource().equals(btnCancelar))
		{
			txtNombre.setText("");
			txtMovil.setText("");
			txtDireccion.setText("");
			txtNombre.requestFocus();
			
			NuevoCliente.setVisible(false);
		}
	}

	public void windowOpened(WindowEvent e){}

	public void windowClosing(WindowEvent e)
	{
		if(dlgMensaje.isActive())
		{
			dlgMensaje.setVisible(false);
		}
		else
		{
			NuevoCliente.setVisible(false);
		}
	}

	public void windowClosed(WindowEvent e){}

	public void windowIconified(WindowEvent e){}

	public void windowDeiconified(WindowEvent e){}

	public void windowActivated(WindowEvent e){}

	public void windowDeactivated(WindowEvent e){}
}
