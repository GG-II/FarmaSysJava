package dao;

import model.Compra;
import model.Medicina;
import model.Proveedor;
import main.conexionBD;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CompraDAO {

    public List<Compra> obtenerComprasPorPeriodo(String periodo) {
        String sql;
        switch (periodo) {
            case "Hoy":
                sql = "SELECT * FROM compra WHERE fecha = CURDATE()";
                break;
            case "Esta semana":
                sql = "SELECT * FROM compra WHERE YEARWEEK(fecha, 1) = YEARWEEK(CURDATE(), 1)";
                break;
            case "Últimos 30 días":
                sql = "SELECT * FROM compra WHERE fecha >= CURDATE() - INTERVAL 30 DAY";
                break;
            case "Este año":
                sql = "SELECT * FROM compra WHERE YEAR(fecha) = YEAR(CURDATE())";
                break;
            case "Total":
                sql = "SELECT * FROM compra";
                break;
            default:
                sql = "SELECT * FROM compra WHERE fecha >= CURDATE() - INTERVAL 30 DAY";
                break;
        }

        List<Compra> compras = new ArrayList<>();
        Connection conn = conexionBD.getConexion();

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Compra compra = new Compra();
                compra.setId(rs.getInt("id"));
                compra.setFecha(rs.getDate("fecha").toLocalDate());
                compra.setMedicinaId(rs.getInt("medicina_id"));
                compra.setCantidad(rs.getInt("cantidad"));
                compra.setPrecioUnidad(rs.getDouble("precio_unidad"));
                compra.setPrecioTotal(rs.getDouble("precio_total"));
                compra.setProveedorId(rs.getInt("proveedor_id"));
                compras.add(compra);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return compras;
    }

    public Compra obtenerCompraPorId(int id) {
        Compra compra = null;
        Connection conn = conexionBD.getConexion();
        String sql = "SELECT * FROM compra WHERE id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                compra = new Compra();
                compra.setId(rs.getInt("id"));
                compra.setFecha(rs.getDate("fecha").toLocalDate());
                compra.setMedicinaId(rs.getInt("medicina_id"));
                compra.setCantidad(rs.getInt("cantidad"));
                compra.setPrecioUnidad(rs.getDouble("precio_unidad"));
                compra.setPrecioTotal(rs.getDouble("precio_total"));
                compra.setProveedorId(rs.getInt("proveedor_id"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return compra;
    }

    public String obtenerNombreMedicinaPorIdCompra(int idCompra) {
        String nombreMedicina = "";
        Connection conn = conexionBD.getConexion();
        String sql = "SELECT m.nombre FROM compra c JOIN medicina m ON c.medicina_id = m.id WHERE c.id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idCompra);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                nombreMedicina = rs.getString("nombre");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return nombreMedicina;
    }

    public int insertarCompra(Compra compra) {
        int idCompra = -1;
        Connection conn = conexionBD.getConexion();
        String sql = "INSERT INTO compra (fecha, medicina_id, cantidad, precio_unidad, precio_total, proveedor_id) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setDate(1, Date.valueOf(compra.getFecha()));
            ps.setInt(2, compra.getMedicinaId());
            ps.setInt(3, compra.getCantidad());
            ps.setDouble(4, compra.getPrecioUnidad());
            ps.setDouble(5, compra.getPrecioTotal());
            ps.setInt(6, compra.getProveedorId());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                idCompra = rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return idCompra;
    }

    public void actualizarCompra(Compra compra) {
        Connection conn = conexionBD.getConexion();
        String sql = "UPDATE compra SET fecha = ?, medicina_id = ?, cantidad = ?, precio_unidad = ?, precio_total = ?, proveedor_id = ? WHERE id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDate(1, Date.valueOf(compra.getFecha()));
            ps.setInt(2, compra.getMedicinaId());
            ps.setInt(3, compra.getCantidad());
            ps.setDouble(4, compra.getPrecioUnidad());
            ps.setDouble(5, compra.getPrecioTotal());
            ps.setInt(6, compra.getProveedorId());
            ps.setInt(7, compra.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarCompra(int idCompra) {
        Connection conn = conexionBD.getConexion();
        String sql = "DELETE FROM compra WHERE id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idCompra);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Proveedor obtenerProveedorPorIdCompra(int idCompra) {
        Proveedor proveedor = null;
        Connection conn = conexionBD.getConexion();
        String sql = "SELECT p.id, p.nombre, p.contacto FROM compra c JOIN proveedor p ON c.proveedor_id = p.id WHERE c.id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idCompra);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                proveedor = new Proveedor();
                proveedor.setId(rs.getInt("id"));
                proveedor.setNombre(rs.getString("nombre"));
                proveedor.setContacto(rs.getString("contacto"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return proveedor;
    }
}
