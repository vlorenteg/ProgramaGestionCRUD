package es.studium.Gestion;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;


public class MenuPrincipal implements WindowListener, ActionListener{
	Datos c = new Datos();
	static String nusuario;
	int tipoUsuario;
	Frame MenuPrincipal = new Frame("MenuPrincipal");
	MenuBar menuBar = new MenuBar();
	
	Label lblNombre = new Label ("Oficinas");
	Button btnAlta = new Button("Alta");
	Button btnConsulta = new Button("Consulta");
	Button btnModificacion = new Button("Modificación");
	Button btnBaja = new Button("Baja");
    
    Label lblNombreEmpleados = new Label ("Empleados");
    Button btnAltaEmpleado = new Button("Alta");
	Button btnConsultaEmpleado = new Button("Consulta");
	Button btnModificacionEmpleado = new Button("Modificacion");
	Button btnBajaEmpleado = new Button("Baja");
    
    Label lblNombreClientes = new Label ("Clientes");
    Button btnAltaCliente = new Button("Alta");
	Button btnConsultaCliente = new Button("Consulta");
	Button btnModificacionCliente = new Button("Modificación");
	Button btnBajaCliente = new Button("Baja");
	
	Menu mnuAyuda = new Menu("Ayuda");
	MenuItem mniAyuda = new MenuItem("Ayuda");
	
	MenuPrincipal(String usuario, int t){
		nusuario = usuario;
		tipoUsuario = t;
		MenuPrincipal.setLayout(new FlowLayout());
		MenuPrincipal.setSize(350,200);
		MenuPrincipal.add(lblNombre);
		btnAlta.addActionListener(this);
		btnConsulta.addActionListener(this);
		btnModificacion.addActionListener(this);
		btnBaja.addActionListener(this);
	       if (tipoUsuario == 0) {
	    	   MenuPrincipal.add(btnAlta);
	        } else if (tipoUsuario == 1) {
	        	MenuPrincipal.add(btnAlta);
	        	MenuPrincipal.add(btnConsulta);
	        	MenuPrincipal.add(btnModificacion);
	        	MenuPrincipal.add(btnBaja);
	        }
	       MenuPrincipal.add(lblNombreEmpleados);
	       btnAltaEmpleado.addActionListener(this);
		   btnConsultaEmpleado.addActionListener(this);
		   btnModificacionEmpleado.addActionListener(this);
		   btnBajaEmpleado.addActionListener(this);
		    if (tipoUsuario == 0) {
		    	MenuPrincipal.add(btnAltaEmpleado);
		     } else if (tipoUsuario == 1) {
		    	 MenuPrincipal.add(btnAltaEmpleado);
		    	 MenuPrincipal.add(btnConsultaEmpleado);
		    	 MenuPrincipal.add(btnModificacionEmpleado);
		    	 MenuPrincipal.add(btnBajaEmpleado);
		     }
		    MenuPrincipal.add(lblNombreClientes);
		    btnAltaCliente.addActionListener(this);
			btnConsultaCliente.addActionListener(this);
			btnModificacionCliente.addActionListener(this);
			btnBajaCliente.addActionListener(this);
			if (tipoUsuario == 0) {
				MenuPrincipal.add(btnAltaCliente);
			 } else if (tipoUsuario == 1) {
				 MenuPrincipal.add(btnAltaCliente);
				 MenuPrincipal.add(btnConsultaCliente);
				 MenuPrincipal.add(btnModificacionCliente);
				 MenuPrincipal.add(btnBajaCliente);
			 }
		MenuPrincipal.setResizable(false);		
		MenuPrincipal.setMenuBar(menuBar);
		MenuPrincipal.setLocationRelativeTo(null);
		mnuAyuda.add(mniAyuda);
		menuBar.add(mnuAyuda);
		mniAyuda.addActionListener(this);
		MenuPrincipal.addWindowListener((WindowListener) this);
		MenuPrincipal.setVisible(true);	
	}


	@Override
	public void windowOpened(WindowEvent e){}
	@Override
	public void windowClosing(WindowEvent e)
	{
		String salida = "Sale usuario '" + nusuario + "'";
		c.apunteLog(nusuario, salida);
		System.exit(0);
	}
	@Override
	public void windowClosed(WindowEvent e){}
	@Override
	public void windowIconified(WindowEvent e){}
	@Override
	public void windowDeiconified(WindowEvent e){}
	@Override
	public void windowActivated(WindowEvent e) {}
	@Override
	public void windowDeactivated(WindowEvent e){}
	@Override
	public void actionPerformed(ActionEvent e){
		 if(e.getSource().equals(btnAlta)) {
			 new NuevaOficina();
		 }
		 else if(e.getSource().equals(btnConsulta)) {
			 new ConsultaOficinas();
		 }
		 else if(e.getSource().equals(btnModificacion)) {
			 new ModificacionOficina();
		 }
		 else if(e.getSource().equals(btnBaja)) {
			 new BajaOficina();
		 }
		 else if(e.getSource().equals(btnAltaEmpleado)) {
			 new NuevoEmpleado();
		 }
		 else if(e.getSource().equals(btnConsultaEmpleado)) {
			 new ConsultaEmpleado();
		 }
		 else if(e.getSource().equals(btnModificacionEmpleado)) {
			 new ModificacionEmpleado();
		 }
		 else if(e.getSource().equals(btnBajaEmpleado)) {
			 new BajaEmpleado();
		 }
		 else if(e.getSource().equals(btnAltaCliente)) {
			 new NuevoCliente();
		 }
		 else if(e.getSource().equals(btnConsultaCliente)) {
			 new ConsultaCliente();
		 }
		 else if(e.getSource().equals(btnModificacionCliente)) {
			 new ModificacionCliente();
		 }
		 else if(e.getSource().equals(btnBajaCliente)) {
			 new BajaCliente();
	            }
		 else if(e.getSource().equals(mniAyuda)){
				try
				{
					Runtime.getRuntime().exec("hh.exe AYUDA GESTION.chm");
					String sentencia = "Entra en ayuda '" + nusuario + "'";
					c.apunteLog(nusuario, sentencia);
				}
				catch (IOException er)
				{
					er.printStackTrace();
				}

			}
	}}
    
