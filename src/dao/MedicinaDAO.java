/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author Admin
 */
import model.Medicina;
import main.conexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicinaDAO {
    
    public List<Medicina> obtenerTodasLasMedicinas() {
        List<Medicina> listaMedicinas = new ArrayList<>();
        Connection conn = conexionBD.getConexion();
        String sql = "SELECT m.id, m.nombre, m.dosis, m.precio, m.cantidad, m.fecha_caducidad, t.tipo_medicina FROM medicina m LEFT JOIN tipo_medicina t ON m.tipo = t.id_tipo";
        
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                Medicina medicina = new Medicina();
                medicina.setId(rs.getInt("id"));
                medicina.setNombre(rs.getString("nombre"));
                medicina.setDosis(rs.getString("dosis"));
                medicina.setPrecio(rs.getDouble("precio"));
                medicina.setCantidad(rs.getInt("cantidad"));
                medicina.setFechaCaducidad(rs.getDate("fecha_caducidad"));
                medicina.setTipo(rs.getString("tipo_medicina"));
                listaMedicinas.add(medicina);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return listaMedicinas;
    }
    
    public List<Medicina> buscarMedicinasPorNombre(String nombre) {
        List<Medicina> listaMedicinas = new ArrayList<>();
        Connection conn = conexionBD.getConexion();
        String sql = "SELECT m.id, m.nombre, m.dosis, m.precio, m.cantidad, m.fecha_caducidad, t.tipo_medicina FROM medicina m LEFT JOIN tipo_medicina t ON m.tipo = t.id_tipo WHERE m.nombre LIKE ?";
        
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + nombre + "%");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Medicina medicina = new Medicina();
                medicina.setId(rs.getInt("id"));
                medicina.setNombre(rs.getString("nombre"));
                medicina.setDosis(rs.getString("dosis"));
                medicina.setPrecio(rs.getDouble("precio"));
                medicina.setCantidad(rs.getInt("cantidad"));
                medicina.setFechaCaducidad(rs.getDate("fecha_caducidad"));
                medicina.setTipo(rs.getString("tipo_medicina"));
                listaMedicinas.add(medicina);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return listaMedicinas;
    }
    
        // Método para obtener una medicina por su ID
    public Medicina obtenerMedicinaPorId(int id) {
        Medicina medicina = null;
        Connection conn = conexionBD.getConexion();
        String sql = "SELECT m.id, m.nombre, m.dosis, m.precio, m.cantidad, m.fecha_caducidad, m.tipo, t.tipo_medicina FROM medicina m LEFT JOIN tipo_medicina t ON m.tipo = t.id_tipo WHERE m.id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                medicina = new Medicina();
                medicina.setId(rs.getInt("id"));
                medicina.setNombre(rs.getString("nombre"));
                medicina.setDosis(rs.getString("dosis"));
                medicina.setPrecio(rs.getDouble("precio"));
                medicina.setCantidad(rs.getInt("cantidad"));
                medicina.setFechaCaducidad(rs.getDate("fecha_caducidad"));
                medicina.setTipo(rs.getString("tipo_medicina"));
                medicina.setTipoID(rs.getInt("tipo"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return medicina;
    }

    
       // Método para obtener todos los tipos de medicina
    public List<String> obtenerTiposDeMedicina() {
        List<String> tiposDeMedicina = new ArrayList<>();
        Connection conn = conexionBD.getConexion();
        String sql = "SELECT tipo_medicina FROM tipo_medicina";

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                tiposDeMedicina.add(rs.getString("tipo_medicina"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tiposDeMedicina;
    }

    // Método para actualizar una medicina existente
    public void actualizarMedicina(Medicina medicina) {
        Connection conn = conexionBD.getConexion();
        String sql = "UPDATE medicina SET nombre = ?, dosis = ?, precio = ?, cantidad = ?, fecha_caducidad = ?, tipo = ?, descripcion = ? WHERE id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, medicina.getNombre());
            ps.setString(2, medicina.getDosis());
            ps.setDouble(3, medicina.getPrecio());
            ps.setInt(4, medicina.getCantidad());
            ps.setDate(5, new java.sql.Date(medicina.getFechaCaducidad().getTime()));
            ps.setInt(6, medicina.getTipoID());
            ps.setString(7, medicina.getDescripcion());
            ps.setInt(8, medicina.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    } 
    
    public void agregarMedicina(Medicina medicina) {
    Connection conn = conexionBD.getConexion();
    String sql = "INSERT INTO medicina (nombre, dosis, precio, cantidad, fecha_caducidad, tipo, descripcion) VALUES (?, ?, ?, ?, ?, ?, ?)";

    try (PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, medicina.getNombre());
        ps.setString(2, medicina.getDosis());
        ps.setDouble(3, medicina.getPrecio());
        ps.setInt(4, medicina.getCantidad());
        ps.setDate(5, new java.sql.Date(medicina.getFechaCaducidad().getTime()));
        ps.setInt(6, medicina.getTipoID());
        ps.setString(7, medicina.getDescripcion());
        ps.executeUpdate();

    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    
    public int obtenerIdMedicinaPorNombre(String nombre) {
        int idMedicina = -1;
        Connection conn = conexionBD.getConexion();
        String sql = "SELECT id FROM medicina WHERE nombre = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                idMedicina = rs.getInt("id");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return idMedicina;
    }
       
    public List<Medicina> obtenerMedicinasDisponibles() {
        List<Medicina> listaMedicinas = new ArrayList<>();
        Connection conn = conexionBD.getConexion();
        String sql = "SELECT id, nombre, dosis, precio, cantidad, fecha_caducidad, tipo FROM medicina WHERE cantidad > 0";

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Medicina medicina = new Medicina();
                medicina.setId(rs.getInt("id"));
                medicina.setNombre(rs.getString("nombre"));
                medicina.setDosis(rs.getString("dosis"));
                medicina.setPrecio(rs.getDouble("precio"));
                medicina.setCantidad(rs.getInt("cantidad"));
                medicina.setFechaCaducidad(rs.getDate("fecha_caducidad"));
                medicina.setTipo(rs.getString("tipo"));
                listaMedicinas.add(medicina);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaMedicinas;
    }
    
    public Medicina obtenerMedicinaPorNombre(String nombre) {
        Connection conn = conexionBD.getConexion();
        String sql = "SELECT * FROM medicina WHERE nombre = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nombre);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Medicina medicina = new Medicina();
                    medicina.setId(rs.getInt("id"));
                    medicina.setNombre(rs.getString("nombre"));
                    medicina.setDosis(rs.getString("dosis"));
                    medicina.setPrecio(rs.getDouble("precio"));
                    medicina.setCantidad(rs.getInt("cantidad"));
                    medicina.setFechaCaducidad(rs.getDate("fecha_caducidad"));
                    medicina.setTipo(rs.getString("tipo"));
                    return medicina;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
     public List<Medicina> obtenerMedicinasPorVencer() {
        List<Medicina> listaMedicinas = new ArrayList<>();
        Connection conn = conexionBD.getConexion();
        String sql = "SELECT id, nombre, fecha_caducidad FROM medicina ORDER BY fecha_caducidad ASC LIMIT 25";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Medicina medicina = new Medicina();
                medicina.setId(rs.getInt("id"));
                medicina.setNombre(rs.getString("nombre"));
                medicina.setFechaCaducidad(rs.getDate("fecha_caducidad"));
                listaMedicinas.add(medicina);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaMedicinas;
    }

    public List<Medicina> obtenerMedicinasConNulaExistencia() {
        List<Medicina> listaMedicinas = new ArrayList<>();
        Connection conn = conexionBD.getConexion();
        String sql = "SELECT id, nombre, cantidad FROM medicina WHERE cantidad = 0";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Medicina medicina = new Medicina();
                medicina.setId(rs.getInt("id"));
                medicina.setNombre(rs.getString("nombre"));
                medicina.setCantidad(rs.getInt("cantidad"));
                listaMedicinas.add(medicina);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaMedicinas;
    }

    public List<Medicina> obtenerMedicinasConPocasExistencias() {
        List<Medicina> listaMedicinas = new ArrayList<>();
        Connection conn = conexionBD.getConexion();
        String sql = "SELECT id, nombre, cantidad FROM medicina WHERE cantidad > 0 ORDER BY cantidad ASC LIMIT 25";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Medicina medicina = new Medicina();
                medicina.setId(rs.getInt("id"));
                medicina.setNombre(rs.getString("nombre"));
                medicina.setCantidad(rs.getInt("cantidad"));
                listaMedicinas.add(medicina);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaMedicinas;
    }

    public List<Medicina> obtenerMedicinasConMuchaExistencia() {
        List<Medicina> listaMedicinas = new ArrayList<>();
        Connection conn = conexionBD.getConexion();
        String sql = "SELECT id, nombre, cantidad FROM medicina ORDER BY cantidad DESC LIMIT 25";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Medicina medicina = new Medicina();
                medicina.setId(rs.getInt("id"));
                medicina.setNombre(rs.getString("nombre"));
                medicina.setCantidad(rs.getInt("cantidad"));
                listaMedicinas.add(medicina);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaMedicinas;
    }

    public List<Medicina> obtenerMedicinasConPocasVentas() {
        List<Medicina> listaMedicinas = new ArrayList<>();
        Connection conn = conexionBD.getConexion();
        String sql = "SELECT m.id, m.nombre, SUM(d.cantidad) AS cantidad_vendida FROM medicina m LEFT JOIN detalle_ventas d ON m.id = d.id_medicina GROUP BY m.id ORDER BY cantidad_vendida ASC LIMIT 35";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Medicina medicina = new Medicina();
                medicina.setId(rs.getInt("id"));
                medicina.setNombre(rs.getString("nombre"));
                medicina.setCantidadVendida(rs.getInt("cantidad_vendida"));
                listaMedicinas.add(medicina);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaMedicinas;
    }

    public List<Medicina> obtenerMedicinasConMuchasVentas() {
        List<Medicina> listaMedicinas = new ArrayList<>();
        Connection conn = conexionBD.getConexion();
        String sql = "SELECT m.id, m.nombre, SUM(d.cantidad) AS cantidad_vendida FROM medicina m LEFT JOIN detalle_ventas d ON m.id = d.id_medicina GROUP BY m.id ORDER BY cantidad_vendida DESC LIMIT 35";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Medicina medicina = new Medicina();
                medicina.setId(rs.getInt("id"));
                medicina.setNombre(rs.getString("nombre"));
                medicina.setCantidadVendida(rs.getInt("cantidad_vendida"));
                listaMedicinas.add(medicina);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaMedicinas;
    }
}
