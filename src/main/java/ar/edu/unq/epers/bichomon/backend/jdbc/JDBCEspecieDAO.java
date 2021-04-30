/*package ar.edu.unq.epers.bichomon.backend.jdbc;

import ar.edu.unq.epers.bichomon.backend.hibernate.dao.EspecieDAO;
import ar.edu.unq.epers.bichomon.backend.model.especie.Especie;
import ar.edu.unq.epers.bichomon.backend.model.especie.TipoBicho;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCEspecieDAO implements EspecieDAO {

    private ConnectionStatements connection = new ConnectionStatements();

    public JDBCEspecieDAO(){

    }

    @Override
    public void guardar(Especie especie) {
        this.connection.executeWithConnection(conn -> {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO especie (id,nombre, altura, peso, tipo, cantidadBichos) VALUES (?,?,?,?,?,?)");
            ps.setInt(1,especie.getId());
            ps.setString(2, especie.getNombre());
            ps.setInt(3, especie.getAltura());
            ps.setInt(4, especie.getPeso());
            ps.setString(5, especie.getTipo().toString());
            ps.setInt(6,especie.getCantidadBichos());
            ps.execute();
            if (ps.getUpdateCount() != 1) {
                throw new RuntimeException("No se inserto la especie" + especie);
            }
            ps.close();

            return null;
        });
    }

    @Override
    public Especie recuperar(Long id) {
        return null;
    }

    @Override
    public Especie recuperarPorNombre(String nombre) {
        return null;
    }

    @Override
    public void actualizar(Especie especie) {
        this.connection.executeWithConnection(conn -> {
            String query = "UPDATE especie set nombre=?,altura=?,peso=?,tipo=?,cantidadBichos=? WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, especie.getNombre());
            ps.setInt(2, especie.getAltura());
            ps.setInt(3, especie.getPeso());
            ps.setString(4, especie.getTipo().toString());
            ps.setInt(5, especie.getCantidadBichos());
            ps.setInt(6, especie.getId());

            int filasActualizadas = ps.executeUpdate();
            if (filasActualizadas == 1) {
                System.out.println("Se actualizo correctamente al usuario");
            }
            if (filasActualizadas != 1) {
                throw new RuntimeException("Se actualizo mas de una especie o ninguna especie");
            }
            ps.close();
            return null;
        });
    }

    @Override
    public List<Especie> populares() {
        return null;
    }

    @Override
    public List<Especie> impopulares() {
        return null;
    }

    @Override
    public Especie recuperar(String nombreEspecie) {
        return this.connection.executeWithConnection(conn -> {

            PreparedStatement ps = conn.prepareStatement("SELECT id,nombre, altura, peso, tipo,cantidadBichos FROM especie WHERE nombre = ?");
            ps.setString(1,nombreEspecie );
            ResultSet resultSet = ps.executeQuery();
            Especie especieCopia = null;
            while (resultSet.next()) {
                if (especieCopia != null) {
                    throw new RuntimeException("Existe mas de un especie con el nombre " + nombreEspecie);
                }

                especieCopia = new Especie();
                especieCopia.setId(resultSet.getInt("id"));
                especieCopia.setNombre(resultSet.getString("nombre"));
                especieCopia.setAltura(resultSet.getInt("altura"));
                especieCopia.setPeso(resultSet.getInt("peso"));
                especieCopia.setCantidadBichos(resultSet.getInt("cantidadBichos"));
                TipoBicho tipo = TipoBicho.valueOf(resultSet.getString("tipo"));
                especieCopia.setTipo(tipo);
            }

            ps.close();
            return especieCopia;
        });
    }

    @Override
    public List<Especie> recuperarTodos() {
        List<Especie> resultado  = new ArrayList<>();
        return this.connection.executeWithConnection(conn -> {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM especie ORDER BY nombre ASC");
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()){
                Especie especieCopia = new Especie();
                especieCopia.setId(resultSet.getInt("id"));
                especieCopia.setNombre(resultSet.getString("nombre"));
                especieCopia.setAltura(resultSet.getInt("altura"));
                especieCopia.setPeso(resultSet.getInt("peso"));
                especieCopia.setCantidadBichos(resultSet.getInt("cantidadBichos"));
                TipoBicho tipo = TipoBicho.valueOf(resultSet.getString("tipo"));
                especieCopia.setTipo(tipo);
                resultado.add(especieCopia);
            }
            ps.close();
            return resultado;
        });
    }


    public void eliminarDatos() {
        this.connection.executeWithConnection(conn -> {
            PreparedStatement ps = conn.prepareStatement("TRUNCATE TABLE especie");
            ps.execute();
            ps.close();

            return null;
        });
    }

}*/