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

public class ModificacionEmpleado implements WindowListener, ActionListener
{
	Frame frameModificacionEmpleado = new Frame("Modificar Empleado");
	Dialog dlgEditar = new Dialog(frameModificacionEmpleado, "Modificar", true);
	Dialog dlgMensaje = new Dialog(frameModificacionEmpleado, "Mensaje", true);
	Label lblElegir = new Label(" Elige el empleado a modificar:");
	Choice choEmpleados = new Choice();
	Button btnMod = new Button("Modificar");
	Datos c = new Datos();
	Label lblMod = new Label(" - Modificación de Empleado -");
	Label lblNombre = new Label("Nombre:");
	Label lblMovil = new Label("Teléfono");
	Label lblDireccion = new Label("Dirección");
	Label lblNacimiento = new Label("Fecha de Nacmiento");
	Label lblSueldo = new Label("Sueldo");
	Label lblMensaje = new Label("Modificación de Empleado Correcta");
	TextField txtNombre = new TextField(15);
	TextField txtMovil = new TextField(15);
	TextField txtDireccion = new TextField(15);
	TextField txtNacimiento = new TextField(15);
	TextField txtSueldo = new TextField(15);
	Button btnMod2 = new Button("Modificar");
	Button btnCancelar = new Button("Cancelar");
	String idEmpleado = "";

	ModificacionEmpleado()
	{
		frameModificacionEmpleado.setLayout(new FlowLayout());
		frameModificacionEmpleado.setSize(400,150);
		frameModificacionEmpleado.addWindowListener(this);
		frameModificacionEmpleado.add(lblElegir);
		// Rellenar el Choice
		c.rellenarChoiceEmpleados(choEmpleados);
		frameModificacionEmpleado.add(choEmpleados);
		btnMod.addActionListener(this);
		frameModificacionEmpleado.add(btnMod);
		frameModificacionEmpleado.setResizable(false);
		frameModificacionEmpleado.setLocationRelativeTo(null);
		frameModificacionEmpleado.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource().equals(btnMod))
		{
			if(choEmpleados.getSelectedIndex()!=0)
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
				dlgEditar.add(lblNacimiento);
				dlgEditar.add(txtNacimiento);
				dlgEditar.add(lblSueldo);
				dlgEditar.add(txtSueldo);
				btnMod.addActionListener(this);
				btnCancelar.addActionListener(this);
				dlgEditar.add(btnMod2);
				dlgEditar.add(btnCancelar);
				dlgEditar.setResizable(false);
				dlgEditar.setLocationRelativeTo(null);
				String tabla[] = choEmpleados.getSelectedItem().split("-");
				String resultado = c.getEmpleadoEditar(tabla[0]);
				String datos[] = resultado.split("-");
				idEmpleado = datos[0];
				txtNombre.setText(datos[1]);
				txtMovil.setText(datos[2]);
				txtDireccion.setText(datos[3]);
				txtNacimiento.setText(datos[4] + "/" + datos[5] + "/" + datos[6]);
				txtSueldo.setText(datos[7]);
				dlgEditar.setVisible(true);
			}
		}
		else if(e.getSource().equals(btnMod2))
		{
			dlgMensaje.setLayout(new FlowLayout());
			dlgMensaje.setSize(230,100);
			dlgMensaje.addWindowListener(this);
			
			if(txtNombre.getText().length()==0||txtMovil.getText().length()==0||txtDireccion.getText().length()==0||txtNacimiento.getText().length()==0||txtSueldo.getText().length()==0)
			{
				lblMensaje.setText("Los campos están vacíos");
			}
			else if(txtMovil.getText().length()!=9)
			{
				lblMensaje.setText("El Teláfono no es válido");
			}
			else
			{
				// Modificar
				String sentencia = "update empleados set nombreEmpleado='" + txtNombre.getText() + "', telefonoEmpleado = '" + txtMovil.getText()
				+ "', direccionEmpleado = '" + txtDireccion.getText() + "', fechaNacimiento = '" + c.europeoMysql(txtNacimiento.getText())
				+ "', sueldoEmpleado = '" + txtSueldo.getText() + "' where idEmpleado = " + idEmpleado + ";";
				int respuesta = c.modificarEmpleado(sentencia);
				if(respuesta!=0)
				{
					// Mostrar Mensaje Error
					lblMensaje.setText("Error al Modificar");
				}
				else
				{
					lblMensaje.setText("Modificación de Empleado Correcta");
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
			frameModificacionEmpleado.setVisible(false);
		}
		else if (dlgMensaje.isActive())
		{
			dlgMensaje.setVisible(false);
		}
		else
		{
			frameModificacionEmpleado.setVisible(false);
		}
	}
	
	public void windowOpened(WindowEvent e){}	

	public void windowClosed(WindowEvent e){}
	
	public void windowIconified(WindowEvent e){}
	
	public void windowDeiconified(WindowEvent e){}
	
	public void windowActivated(WindowEvent e){}
	
	public void windowDeactivated(WindowEvent e){}
	
}
