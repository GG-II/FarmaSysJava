/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.Venta;
import model.DetalleVenta;
import model.Medicina;
import main.conexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class VentaDAO {

    public List<Venta> obtenerVentasPorPeriodo(String periodo) {
        String sql;
        switch (periodo) {
            case "Hoy":
                sql = "SELECT v.id_venta, v.fecha, v.precio, c.nombre_1, c.nombre_2, c.apellido_1, c.apellido_2 " +
                      "FROM ventas v JOIN clientes c ON v.cliente = c.id_cliente " +
                      "WHERE v.fecha = CURDATE()";
                break;
            case "Esta semana":
                sql = "SELECT v.id_venta, v.fecha, v.precio, c.nombre_1, c.nombre_2, c.apellido_1, c.apellido_2 " +
                      "FROM ventas v JOIN clientes c ON v.cliente = c.id_cliente " +
                      "WHERE YEARWEEK(v.fecha, 1) = YEARWEEK(CURDATE(), 1)";
                break;
            case "Últimos 30 días":
                sql = "SELECT v.id_venta, v.fecha, v.precio, c.nombre_1, c.nombre_2, c.apellido_1, c.apellido_2 " +
                      "FROM ventas v JOIN clientes c ON v.cliente = c.id_cliente " +
                      "WHERE v.fecha >= CURDATE() - INTERVAL 30 DAY";
                break;
            case "Este año":
                sql = "SELECT v.id_venta, v.fecha, v.precio, c.nombre_1, c.nombre_2, c.apellido_1, c.apellido_2 " +
                      "FROM ventas v JOIN clientes c ON v.cliente = c.id_cliente " +
                      "WHERE YEAR(v.fecha) = YEAR(CURDATE())";
                break;
            case "Total":
                sql = "SELECT v.id_venta, v.fecha, v.precio, c.nombre_1, c.nombre_2, c.apellido_1, c.apellido_2 " +
                      "FROM ventas v JOIN clientes c ON v.cliente = c.id_cliente";
                break;
            default:
                sql = "SELECT v.id_venta, v.fecha, v.precio, c.nombre_1, c.nombre_2, c.apellido_1, c.apellido_2 " +
                      "FROM ventas v JOIN clientes c ON v.cliente = c.id_cliente " +
                      "WHERE v.fecha >= CURDATE() - INTERVAL 30 DAY";
                break;
        }

        List<Venta> ventas = new ArrayList<>();
        Connection conn = conexionBD.getConexion();

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Venta venta = new Venta();
                venta.setId(rs.getInt("id_venta"));
                venta.setFecha(rs.getDate("fecha").toLocalDate());
                venta.setTotal(rs.getDouble("precio"));
                venta.setNombreCliente(rs.getString("nombre_1") + " " +
                                       rs.getString("nombre_2") + " " +
                                       rs.getString("apellido_1") + " " +
                                       rs.getString("apellido_2"));
                ventas.add(venta);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ventas;
    }

    public Venta obtenerVentaPorId(int id) {
        Venta venta = null;
        Connection conn = conexionBD.getConexion();
        String sql = "SELECT v.id_venta, v.fecha, v.precio, c.nombre_1, c.nombre_2, c.apellido_1, c.apellido_2 " +
                     "FROM ventas v JOIN clientes c ON v.cliente = c.id_cliente " +
                     "WHERE v.id_venta = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                venta = new Venta();
                venta.setId(rs.getInt("id_venta"));
                venta.setFecha(rs.getDate("fecha").toLocalDate());
                venta.setTotal(rs.getDouble("precio"));
                venta.setNombreCliente(rs.getString("nombre_1") + " " +
                                       rs.getString("nombre_2") + " " +
                                       rs.getString("apellido_1") + " " +
                                       rs.getString("apellido_2"));

                // Obtener los detalles de la venta
                venta.setDetalles(obtenerDetallesPorIdVenta(venta.getId()));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return venta;
    }

    private List<DetalleVenta> obtenerDetallesPorIdVenta(int idVenta) {
        List<DetalleVenta> detalles = new ArrayList<>();
        Connection conn = conexionBD.getConexion();
        String sql = "SELECT d.id_detalle, d.id_venta, d.id_medicina, d.cantidad, d.precio, m.nombre " +
                     "FROM detalle_ventas d JOIN medicina m ON d.id_medicina = m.id " +
                     "WHERE d.id_venta = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idVenta);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                DetalleVenta detalle = new DetalleVenta();
                detalle.setId(rs.getInt("id_detalle"));
                detalle.setIdVenta(rs.getInt("id_venta"));
                detalle.setIdMedicina(rs.getInt("id_medicina"));
                detalle.setCantidad(rs.getInt("cantidad"));
                detalle.setPrecio(rs.getDouble("precio"));

                Medicina medicina = new Medicina();
                medicina.setNombre(rs.getString("nombre"));
                detalle.setMedicina(medicina);

                detalles.add(detalle);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return detalles;
    }

    public int insertarVenta(Venta venta) {
        int idVenta = -1;
        Connection conn = conexionBD.getConexion();
        String sql = "INSERT INTO ventas (fecha, precio, cliente) VALUES (?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setDate(1, java.sql.Date.valueOf(venta.getFecha()));
            ps.setDouble(2, venta.getTotal());
            ps.setInt(3, venta.getIdCliente());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                idVenta = rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return idVenta;
    }

    public void insertarDetalleVenta(DetalleVenta detalle) {
        Connection conn = conexionBD.getConexion();
        String sql = "INSERT INTO detalle_ventas (id_venta, id_medicina, cantidad, precio) VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, detalle.getIdVenta());
            ps.setInt(2, detalle.getIdMedicina());
            ps.setInt(3, detalle.getCantidad());
            ps.setDouble(4, detalle.getPrecio());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizarVenta(Venta venta) {
        Connection conn = conexionBD.getConexion();
        String sql = "UPDATE ventas SET fecha = ?, precio = ?, cliente = ? WHERE id_venta = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDate(1, java.sql.Date.valueOf(venta.getFecha()));
            ps.setDouble(2, venta.getTotal());
            ps.setInt(3, venta.getIdCliente());
            ps.setInt(4, venta.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizarDetalleVenta(DetalleVenta detalle) {
        Connection conn = conexionBD.getConexion();
        String sql = "UPDATE detalle_ventas SET id_venta = ?, id_medicina = ?, cantidad = ?, precio = ? WHERE id_detalle = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, detalle.getIdVenta());
            ps.setInt(2, detalle.getIdMedicina());
            ps.setInt(3, detalle.getCantidad());
            ps.setDouble(4, detalle.getPrecio());
            ps.setInt(5, detalle.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarVenta(int idVenta) {
        Connection conn = conexionBD.getConexion();
        String sql = "DELETE FROM ventas WHERE id_venta = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idVenta);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarDetallesPorIdVenta(int idVenta) {
        Connection conn = conexionBD.getConexion();
        String sql = "DELETE FROM detalle_ventas WHERE id_venta = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idVenta);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public List<DetalleVenta> obtenerDetallesPorVenta(int idVenta) {
        List<DetalleVenta> detalles = new ArrayList<>();
        Connection conn = conexionBD.getConexion();
        String sql = "SELECT d.id_detalle, d.id_venta, d.id_medicina, d.cantidad, d.precio, m.nombre " +
                     "FROM detalle_ventas d JOIN medicina m ON d.id_medicina = m.id " +
                     "WHERE d.id_venta = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idVenta);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                DetalleVenta detalle = new DetalleVenta();
                detalle.setId(rs.getInt("id_detalle"));
                detalle.setIdVenta(rs.getInt("id_venta"));
                detalle.setIdMedicina(rs.getInt("id_medicina"));
                detalle.setCantidad(rs.getInt("cantidad"));
                detalle.setPrecio(rs.getDouble("precio"));

                Medicina medicina = new Medicina();
                medicina.setNombre(rs.getString("nombre"));
                detalle.setMedicina(medicina);

                detalles.add(detalle);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return detalles;
    }

}
