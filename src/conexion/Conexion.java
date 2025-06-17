package conexion;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Conexion {

   // private String nombreBd = "conexionBDjava";
   // private String usuario = "root";
 //   private String password = "1234";
    private static Conexion instancia;
    
    
    // Agregamos timezone y otras propiedades recomendadas
    private String url ;//"jdbc:mysql://localhost/" + nombreBd + "?serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";
    private String usuario;
	private String password;
    private Connection conn = null;
    private static Properties properties = new Properties();

    public Conexion() throws SQLException {
        try {
        	InputStream input = Conexion.class.getClassLoader().getSystemResourceAsStream("properties/config.properties");
           // Class.forName("com.mysql.cj.jdbc.Driver");

            //conn = DriverManager.getConnection(url, usuario, password);

            if (input == null) {
                System.err.println("Archivo de config.properties no encontrado");
            }
            properties.load(input);
			
			String nombreBD = properties.getProperty("db.name");
            usuario = properties.getProperty("db.user");
            password = properties.getProperty("db.password");
            String host = properties.getProperty("db.host");
            String port = properties.getProperty("db.port");
            
            url = "jdbc:mysql://" + host + ":" + port + "/" + nombreBD +
                    "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
			
            Class.forName("com.mysql.cj.jdbc.Driver");
            
			
			}catch (IOException e) {
                System.err.println("Error al leer config.properties: " + e.getMessage());
			} catch (ClassNotFoundException e) {
                System.err.println("Error al cargar el driver: " + e.getMessage());
			}
			
//			return conn;
		}
            

    public Connection getConnection() {
    	try {
            return DriverManager.getConnection(url, usuario, password);
        } catch (SQLException e) {
            System.err.println("❌ Error al obtener conexión: " + e.getMessage());
            return null;
        }
    }

    public void desconectar() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            System.err.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }

    public static Conexion getInstancia() throws SQLException {
		if(instancia == null) {
			instancia= new Conexion();
		}
		return instancia;
	}
	
}
