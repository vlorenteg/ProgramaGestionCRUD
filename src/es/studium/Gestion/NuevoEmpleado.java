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


public class NuevoEmpleado implements WindowListener, ActionListener
{
	Frame NuevoEmpleado = new Frame("Nuevo Empleado");
	Dialog dlgMensaje = new Dialog(NuevoEmpleado, "Mensaje", true);
	Label lblAlta = new Label(" - Nuevo Empleado -");
	Label lblOficina = new Label("Oficina:");
	TextField txtOficina = new TextField(15);
	Choice choOficina = new Choice();
	Label lblNombre = new Label("Nombre:");
	Label lblMovil = new Label("Teléfono:");
	Label lblDireccion = new Label("Dirección:");
	Label lblNacimiento = new Label("Fecha de Nacimiento:");
	Label lblSueldo = new Label("Sueldo:");
	Label lblMensaje = new Label("Alta de Empleado Correcta");
	TextField txtNombre = new TextField(15);
	TextField txtMovil = new TextField(15);
	TextField txtDireccion = new TextField(15);
	TextField txtNacimiento = new TextField(15);
	TextField txtSueldo = new TextField(15);
	Button btnAceptar = new Button("Aceptar");
	Button btnCancelar = new Button("Cancelar");
	Datos c = new Datos();
	
	NuevoEmpleado()
	{
		NuevoEmpleado.setSize(170, 450);
		NuevoEmpleado.setLayout(new FlowLayout());
		NuevoEmpleado.addWindowListener(this);
		NuevoEmpleado.add(lblAlta);
		NuevoEmpleado.add(lblOficina);
		c.rellenarChoiceOficinas(choOficina);
		NuevoEmpleado.add(choOficina);
		NuevoEmpleado.add(lblNombre);
		NuevoEmpleado.add(txtNombre);
		NuevoEmpleado.add(lblMovil);
		NuevoEmpleado.add(txtMovil);
		NuevoEmpleado.add(lblDireccion);
		NuevoEmpleado.add(txtDireccion);
		NuevoEmpleado.add(lblNacimiento);
		NuevoEmpleado.add(txtNacimiento);
		NuevoEmpleado.add(lblSueldo);
		NuevoEmpleado.add(txtSueldo);
		btnAceptar.addActionListener(this);
		btnCancelar.addActionListener(this);
		NuevoEmpleado.add(btnAceptar);
		NuevoEmpleado.add(btnCancelar);
		NuevoEmpleado.setResizable(false);
		NuevoEmpleado.setLocationRelativeTo(null);
		NuevoEmpleado.setVisible(true);
		
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
			else if(!txtSueldo.getText().matches("^\\d{1,5}(\\.\\d{1,2})?$")) 
			{
				lblMensaje.setText("El Sueldo no es válido");
			}
			else if (!txtNacimiento.getText().matches("^\\d{2}/\\d{2}/\\d{4}$")) 
			{
			    lblMensaje.setText("La Fecha de Nacimiento no es válida");
	        }
			
			else
			{
				String fechaNacimientoMysql = c.europeoMysql(txtNacimiento.getText());
				String sentencia = "insert into empleados values (null, '" + txtNombre.getText() + "', '" + txtMovil.getText() + "', '" + txtDireccion.getText() + "', '" + fechaNacimientoMysql + "', '" + txtSueldo.getText() + "', '" + choiceOficinas[0] + "');";

				int respuesta = c.nuevoEmpleado(sentencia);
				if(respuesta!=0)
				{
					lblMensaje.setText("Ha ocurrido un error");
				}
				else
				{
					lblMensaje.setText("Alta de Empleado Correcta");
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
			txtDireccion.setText("");
			txtMovil.setText("");
			txtNacimiento.setText("");
			txtSueldo.setText("");
			txtNombre.requestFocus();
			
			NuevoEmpleado.setVisible(false);
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
			NuevoEmpleado.setVisible(false);
		}
	}

	public void windowClosed(WindowEvent e){}

	public void windowIconified(WindowEvent e){}

	public void windowDeiconified(WindowEvent e){}

	public void windowActivated(WindowEvent e){}

	public void windowDeactivated(WindowEvent e){}
}
