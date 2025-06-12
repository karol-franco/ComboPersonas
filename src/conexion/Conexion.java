package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private String nombreBd = "conexionBDjava";
    private String usuario = "root";
    private String password = "1234";
    
    // Agregamos timezone y otras propiedades recomendadas
    private String url = "jdbc:mysql://localhost/" + nombreBd + "?serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";

    private Connection conn = null;

    public Conexion() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(url, usuario, password);

            if (conn == null) {
                System.err.println("Conexion fallida a la BD " + nombreBd);
            }
        } catch (ClassNotFoundException e) {
            System.err.println("Ocurre una ClassNotFoundException: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Ocurre una SQLException: " + e.getMessage());
            throw e; 
        }
    }

    public Connection getConnection() {
        return conn;
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
}
