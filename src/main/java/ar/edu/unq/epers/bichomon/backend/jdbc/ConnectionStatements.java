package ar.edu.unq.epers.bichomon.backend.jdbc;

import ar.edu.unq.epers.bichomon.backend.jdbc.ConnectionBlock;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionStatements {

    public ConnectionStatements(){}

    public <T> T executeWithConnection(ConnectionBlock<T> bloque) {
        Connection connection = this.openConnection();
        try {
            return bloque.executeWith(connection);
        } catch (SQLException e) {
            throw new RuntimeException("Error no esperado", e);
        } finally {
            this.closeConnection(connection);
        }
    }
    private Connection openConnection() {
        try {
            //La url de conexion no deberia estar harcodeada aca
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/bichomon?user=root&password=root&useSSL=false&&serverTimezone=UTC");
        } catch (SQLException e) {
            throw new RuntimeException("No se puede establecer una conexion", e);
        }
    }
    private void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException("Error al cerrar la conexion", e);
        }
    }
}
