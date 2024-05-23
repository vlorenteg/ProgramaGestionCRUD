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

public class ModificacionCliente implements WindowListener, ActionListener
{
	Frame frameModificacionCliente = new Frame("Modificar Cliente");
	Dialog dlgEditar = new Dialog(frameModificacionCliente, "Modificación", true);
	Dialog dlgMensaje = new Dialog(frameModificacionCliente, "Mensaje", true);
	Label lblElegir = new Label(" Elige el Cliente a modificar:");
	Choice choClientes = new Choice();
	Button btnMod = new Button("Modificar");
	Datos c = new Datos();
	Label lblMod = new Label(" - Modificación de Cliente -");
	Label lblNombre = new Label("Nombre:");
	Label lblMovil = new Label("Teléfono");
	Label lblDireccion = new Label("Dirección");

	Label lblMensaje = new Label("Modificación de cliente Correcta");
	TextField txtNombre = new TextField(15);
	TextField txtMovil = new TextField(15);
	TextField txtDireccion = new TextField(15);
	Button btnMod2 = new Button("Modificar");
	Button btnCancelar = new Button("Cancelar");
	String idCliente = "";

	ModificacionCliente()
	{
		frameModificacionCliente.setLayout(new FlowLayout());
		frameModificacionCliente.setSize(200,170);
		frameModificacionCliente.addWindowListener(this);
		frameModificacionCliente.add(lblElegir);
		// Rellenar el Choice
		c.rellenarChoiceClientes(choClientes);
		frameModificacionCliente.add(choClientes);
		btnMod.addActionListener(this);
		frameModificacionCliente.add(btnMod);
		frameModificacionCliente.setResizable(false);
		frameModificacionCliente.setLocationRelativeTo(null);
		frameModificacionCliente.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource().equals(btnMod))
		{
			if(choClientes.getSelectedIndex()!=0)
			{
				dlgEditar.setLayout(new FlowLayout());
				dlgEditar.setSize(180,390);
				dlgEditar.addWindowListener(this);
				btnMod2.addActionListener(this);
				dlgEditar.add(lblMod);
				dlgEditar.add(lblNombre);
				dlgEditar.add(txtNombre);
				dlgEditar.add(lblMovil);
				dlgEditar.add(txtMovil);
				dlgEditar.add(lblDireccion);
				dlgEditar.add(txtDireccion);
				btnMod.addActionListener(this);
				btnCancelar.addActionListener(this);
				dlgEditar.add(btnMod2);
				dlgEditar.add(btnCancelar);
				dlgEditar.setResizable(false);
				dlgEditar.setLocationRelativeTo(null);
				String tabla[] = choClientes.getSelectedItem().split("-");
				String resultado = c.getClienteEditar(tabla[0]);
				String datos[] = resultado.split("-");
				idCliente = datos[0];
				txtNombre.setText(datos[1]);
				txtMovil.setText(datos[2]);
				txtDireccion.setText(datos[3]);
				dlgEditar.setVisible(true);
			}
		}
		else if(e.getSource().equals(btnMod2))
		{
			dlgMensaje.setLayout(new FlowLayout());
			dlgMensaje.setSize(230,100);
			dlgMensaje.addWindowListener(this);
			
			if(txtNombre.getText().length()==0||txtMovil.getText().length()==0||txtDireccion.getText().length()==0)
			{
				lblMensaje.setText("Los campos estan vacíos");
			}
			else if(txtMovil.getText().length()!=9)
			{
				lblMensaje.setText("El Teléfono no es válido");
			}
			else
			{
				// Modificar
				String sentencia = "update clientes set nombreCliente='" + txtNombre.getText() + "', telefonoCliente = '" + txtMovil.getText()
				+ "', direccionCliente = '" + txtDireccion.getText() + "' where idCliente = " + idCliente + ";";
				int respuesta = c.modificarEmpleado(sentencia);
				if(respuesta!=0)
				{
					// Mostrar Mensaje Error
					lblMensaje.setText("Error al Modificar");
				}
				else
				{
					lblMensaje.setText("Modificación de Cliente Correcta");
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
			frameModificacionCliente.setVisible(false);
		}
		else if (dlgMensaje.isActive())
		{
			dlgMensaje.setVisible(false);
		}
		else
		{
			frameModificacionCliente.setVisible(false);
		}
	}
	
	public void windowOpened(WindowEvent e){}	

	public void windowClosed(WindowEvent e){}
	
	public void windowIconified(WindowEvent e){}
	
	public void windowDeiconified(WindowEvent e){}
	
	public void windowActivated(WindowEvent e){}
	
	public void windowDeactivated(WindowEvent e){}
}

