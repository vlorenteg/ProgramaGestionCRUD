package es.studium.Gestion;

import java.awt.Choice;
import java.awt.TextArea;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Datos
{
	String driver = "com.mysql.cj.jdbc.Driver"; //cadena conexión a BD SQL
	String url = "jdbc:mysql://localhost:3306/programaGestion"; //ubicación
	String login = "gerente"; //usuario
	String password = "Studium2023"; //clave
	
	Connection connection = null; //objeto hacer conexion
	Statement statement = null;// lanzar sentencia
	ResultSet rs = null; // recoger información de la BD
	public int tipoUsuario;
	
	Datos(){
		connection = this.conectar();
	}
	
	public Connection conectar()
    {
        try
        {
            Class.forName(driver); //cargar los controladores para el acceso a la BD
            return(DriverManager.getConnection(url, login, password)); //establece conexión con la BD
        }
        catch (ClassNotFoundException cnfe) //posibles errores
        {
            System.out.println("Error 1-" + cnfe.getMessage());
        }
        catch (SQLException sqle)
        {
            System.out.println("Error 2-" + sqle.getMessage());
        }
        return null;
    }
	
	public int comprobarCredenciales(String u, String c)
	{
		String cadena = "select * from usuarios where nombreUsuario = '" + u + "' and claveUsuario = SHA2('" + c + "',256);";
		try
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY); //crea una sentencia
			rs = statement.executeQuery(cadena); //crea un objeto ResulSet para guardar la información que nos da la BD y ejecuta la sentencia SQL
												//executeQuery solo para consulta
			if(rs.next())
			{
				String entrada = "Accede usuario '" + u + "'";
				apunteLog(u, entrada);
				return rs.getInt("tipoUsuario");
			}
			else
			{
				return -1;
			}
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 3-" + sqle.getMessage());
		}
		return -1;
	}


	public int dameTipoUsuario()
	{
		int tipo = 0;
		if(tipo==1) {
			return 1;
		}
		else {
		return 0;
		}
	}
	
	public int NuevaOficina(String sentencia)
	{
		try
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			statement.executeUpdate(sentencia); //executeUpdate para INSERT, DELETE y UPDATE
			apunteLog(MenuPrincipal.nusuario, sentencia); //guarda la información que ingreso
			return 0;
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 4-" + sqle.getMessage());
			return 1;
		}
	}
	
	public void consultaOficina(TextArea txaConsulta)
	{
		String sentencia = "select idOficina, direccionOficina, telefonoOficina from oficinas order by 1;";
		try
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet resultado = statement.executeQuery(sentencia);
			String entrada = "Consulta Oficinas";
			apunteLog(MenuPrincipal.nusuario, entrada);
			while (resultado.next())
			{
				txaConsulta.append(resultado.getString("idOficina")+ " - ");
				txaConsulta.append(resultado.getString("direccionOficina") + " - ");
				txaConsulta.append(resultado.getString("telefonoOficina") + "\n");
			}
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 5-" + sqle.getMessage());
		} 
	}

	public String getOficinaEditar(String idOficina) //muestra la oficina elegida para modificar
    {
        String resultado = "";
        String sentencia = "select * from oficinas where idOficina = " + idOficina;
        try
        {

            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet resultSet = statement.executeQuery(sentencia);
            resultSet.next();
            resultado =(resultSet.getString("idOficina") + "-" + resultSet.getString("direccionOficina") + "-" + resultSet.getString("telefonoOficina"));
        }
        catch (SQLException sqle)
        {
            System.out.println("Error 16-"+sqle.getMessage());
        }
        return resultado;
    }
	
	public int modificarOficinas(String sentencia) //modifica los datos
    {
        try
        {
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            statement.executeUpdate(sentencia);
            apunteLog(MenuPrincipal.nusuario, sentencia);
            return 0;
        }
        catch (SQLException sqle)
        {
            System.out.println("Error 17-" + sqle.getMessage());
            return 1;
        }
    }
	
	public int rellenarChoiceBajaOficina(Choice choOficinas)
	{
		String idOficina = choOficinas.getSelectedItem();
		String sentencia = "delete from Oficinas where idOficina = '" + idOficina + "'";
		try
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			statement.executeUpdate(sentencia);
			apunteLog(MenuPrincipal.nusuario, sentencia);
			return 0;
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 7-" + sqle.getMessage());
			return 1;
		}
	}

	public int bajaOficina(String idOficina)
	{
		  String sentencia = "DELETE FROM oficinas WHERE idOficina = " + idOficina;
		    try {
		        statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		        statement.executeUpdate(sentencia);
		        apunteLog(MenuPrincipal.nusuario, sentencia);
		        return 0;
		    } catch (SQLException sqle) {
		        System.out.println("Error al eliminar la oficina: " + sqle.getMessage());
		        return 1;
		    }
	}

	public void rellenarChoiceOficinas(Choice choOficinas)
	{
		String sentencia = "select idOficina, direccionOficina, telefonoOficina from oficinas order by 1;";
		try
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet resultado = statement.executeQuery(sentencia);
			choOficinas.add("Elige una oficina...");
			while (resultado.next())
			{
				choOficinas.add(resultado.getString("idOficina") + "-" + resultado.getString("direccionOficina") + "-" + resultado.getString("telefonoOficina"));
			}
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 12-" + sqle.getMessage());
		}
	}

	public String europeoMysql(String fecha)
	{
		String fechaTransformada = "";
		String[] temporal = fecha.split("/");
		fechaTransformada = temporal[2]+"-"+temporal[1]+"-"+temporal[0];
		return (fechaTransformada);
	}
	
	public String mysqlEuropeo(String fecha)
	{
		String fechaTransformada = "";
		String[] temporal = fecha.split("-");
		fechaTransformada = temporal[2]+"/"+temporal[1]+"/"+temporal[0];
		return (fechaTransformada);
	}
	
	public int nuevoEmpleado(String sentencia)
	{
		try
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			statement.executeUpdate(sentencia);
			apunteLog(MenuPrincipal.nusuario, sentencia);
			return 0;
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 8-" + sqle.getMessage());
			return 1;
		}
	}
	
	public void rellenarListadoEmpleado(TextArea txaEmpleados)
	{
		String sentencia = "select e.idEmpleado, e.nombreEmpleado, e.telefonoEmpleado, e.direccionEmpleado, e.fechaNacimientoEmpleado, e.sueldoEmpleado, o.direccionOficina, o.telefonoOficina "
				+ "FROM empleados e " + "JOIN oficinas o ON e.idOficinaFK = o.idOficina;" ;;
		try
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet resultado = statement.executeQuery(sentencia);
			String entrada = "Entra en el listado";
			apunteLog(MenuPrincipal.nusuario, entrada);
			while (resultado.next())
			{
				txaEmpleados.append(resultado.getString("idEmpleado") + " - ");
				txaEmpleados.append(resultado.getString("nombreEmpleado") + " - ");
				txaEmpleados.append(resultado.getString("telefonoEmpleado") + " - ");
				txaEmpleados.append(resultado.getString("direccionEmpleado") + " - ");
				txaEmpleados.append(resultado.getString("fechaNacimientoEmpleado") + " - ");
				txaEmpleados.append(resultado.getString("sueldoEmpleado") + " - ");
				txaEmpleados.append(resultado.getString("direccionOficina") + " - ");
				txaEmpleados.append(resultado.getString("telefonoOficina") + "\n");
			}
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 10-" + sqle.getMessage());
		}
	}
	
	public String getEmpleadoEditar(String idEmpleado)
	{
		String resultado = "";
		String sentencia = "select * from Empleados where idEmpleado = " + idEmpleado;
		try
		{
			// Crear una sentencia
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			// Crear un objeto ResultSet para guardar lo obtenido
			// y ejecutar la sentencia SQL
			ResultSet resultSet = statement.executeQuery(sentencia);
			resultSet.next();
			resultado =(resultSet.getString("idEmpleado") + "-" + resultSet.getString("nombreEmpleado")+ "-" + resultSet.getString("telefonoEmpleado")
			+ "-" + resultSet.getString("direccionEmpleado") + "-" + resultSet.getString("fechaNacimientoEmpleado") + "-" + resultSet.getString("sueldoEmpleado"));
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 18-" + sqle.getMessage());
		}
		return resultado;
	}

	public int modificarEmpleado(String sentencia)
	{
		try
		{
			// Crear una sentencia
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			// Ejecutar la sentencia SQL
			statement.executeUpdate(sentencia);
			apunteLog(MenuPrincipal.nusuario, sentencia);
			return 0;
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 19-" + sqle.getMessage());
			return 1;
		}
	}
	
	public void rellenarChoiceEmpleados(Choice choEmpleados)
	{
		String sentencia = "select idEmpleado, nombreEmpleado, telefonoEmpleado, direccionEmpleado, fechaNacimientoEmpleado, sueldoEmpleado from empleados;";
		try
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet resultado = statement.executeQuery(sentencia);
			choEmpleados.add("Elige un empleado...");
			while (resultado.next())
			{
				choEmpleados.add(resultado.getString("idEmpleado") + "-" + resultado.getString("nombreEmpleado")+ "-" + resultado.getString("telefonoEmpleado")
				+ "-" + resultado.getString("direccionEmpleado") + "-" + resultado.getString("fechaNacimientoEmpleado") + "-" + resultado.getString("sueldoEmpleado"));
			}
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 6-" + sqle.getMessage());
		}
	}
	
	public int bajaEmpleado(String idEmpleado)
	{
		String sentencia = "DELETE FROM Empleados WHERE idEmpleado = '" + idEmpleado + "'";
		try
		{
			// Crear una sentencia
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			// Ejecutar la sentencia SQL
			statement.executeUpdate(sentencia);
			apunteLog(MenuPrincipal.nusuario, sentencia);
			return 0;
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 7-" + sqle.getMessage());
			return 1;
		}
	}
	
	public int nuevoCliente(String sentencia)
	{
		try
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			statement.executeUpdate(sentencia);
			apunteLog(MenuPrincipal.nusuario, sentencia);
			return 0;
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 8-" + sqle.getMessage());
			return 1;
		}
	}

	public void rellenarListadoCliente(TextArea txaClientes)
	{
		String sentencia = "select c.idCliente, c.nombreCliente, c.telefonoCliente, c.direccionCliente, o.direccionOficina, o.telefonoOficina " + 
				"FROM clientes c " + "JOIN oficinas o ON c.idOficinaFK = o.idOficina;" ;
		try
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet resultado = statement.executeQuery(sentencia);
			String entrada = "Entra en el listado";
			apunteLog(MenuPrincipal.nusuario, entrada);
			while (resultado.next())
			{
				txaClientes.append(resultado.getString("idCliente") + " - ");
				txaClientes.append(resultado.getString("nombreCliente") + " - ");
				txaClientes.append(resultado.getString("telefonoCliente") + " - ");
				txaClientes.append(resultado.getString("direccionCliente") + " - ");
				txaClientes.append(resultado.getString("direccionOficina") + " - ");
				txaClientes.append(resultado.getString("telefonoOficina") + "\n");
			}
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 10-" + sqle.getMessage());
		}
	}
	
	public String getClienteEditar(String idCliente)
	{
		String resultado = "";
		String sentencia = "select * from Clientes where idCliente = " + idCliente;
		try
		{
			// Crear una sentencia
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			// Crear un objeto ResultSet para guardar lo obtenido
			// y ejecutar la sentencia SQL
			ResultSet resultSet = statement.executeQuery(sentencia);
			resultSet.next();
			resultado =(resultSet.getString("idCliente") + "-" + resultSet.getString("nombreCliente")+ "-" + resultSet.getString("telefonoCliente") + "-" + resultSet.getString("direccionCliente"));
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 18-" + sqle.getMessage());
		}
		return resultado;
	}

	public int modificarCliente(String sentencia)
	{
		try
		{
			// Crear una sentencia
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			// Ejecutar la sentencia SQL
			statement.executeUpdate(sentencia);
			apunteLog(MenuPrincipal.nusuario, sentencia);
			return 0;
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 19-" + sqle.getMessage());
			return 1;
		}
	}

	public void rellenarChoiceClientes(Choice choClientes)
	{
		String sentencia = "select idCliente, nombreCliente, telefonoCliente, direccionCliente from clientes;";
		try
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet resultado = statement.executeQuery(sentencia);
			choClientes.add("Elige un cliente...");
			while (resultado.next())
			{
				choClientes.add(resultado.getString("idCliente") + "-" + resultado.getString("nombreCliente") + "-" + resultado.getString("direccionCliente"));
			}
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 6-" + sqle.getMessage());
		}
	}
	
	public int bajaCliente(String idCliente)
	{
		String sentencia = "DELETE FROM Clientes WHERE idCliente = '" + idCliente + "'";
		try
		{
			// Crear una sentencia
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			// Ejecutar la sentencia SQL
			statement.executeUpdate(sentencia);
			apunteLog(MenuPrincipal.nusuario, sentencia);
			return 0;
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 7-" + sqle.getMessage());
			return 1;
		}
	}
	
	public void apunteLog(String nusuario, String salida){ 
		//comprobar funcionamiento
		Date fecha = new Date();
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String fechaFormateada = formato.format(fecha);
		try
		{
			// Abrir el fichero
			FileWriter fw = new FileWriter("historico.log", true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter salida1 = new PrintWriter(bw);
			// Gestionar el fichero
			salida1.println("[" + fechaFormateada + "] [" + nusuario + "] " + salida);
			System.out.println("Información Almacenada");
			// Cerrar el fichero
			salida1.close();
			bw.close();
			fw.close();
		}
		catch(IOException ioe)
		{
			System.out.println("Error en Fichero");
		}
	}
}
