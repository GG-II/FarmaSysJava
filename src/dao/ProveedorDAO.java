package dao;

import model.Proveedor;
import main.conexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProveedorDAO {

    public List<Proveedor> obtenerTodosLosProveedores() {
        List<Proveedor> listaProveedores = new ArrayList<>();
        Connection conn = conexionBD.getConexion();
        String sql = "SELECT * FROM proveedor";

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Proveedor proveedor = new Proveedor();
                proveedor.setId(rs.getInt("id"));
                proveedor.setNombre(rs.getString("nombre"));
                proveedor.setContacto(rs.getString("contacto"));
                listaProveedores.add(proveedor);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaProveedores;
    }

    public List<Proveedor> buscarProveedoresPorNombre(String nombre) {
        List<Proveedor> listaProveedores = new ArrayList<>();
        Connection conn = conexionBD.getConexion();
        String sql = "SELECT * FROM proveedor WHERE nombre LIKE ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            String queryParam = "%" + nombre + "%";
            ps.setString(1, queryParam);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Proveedor proveedor = new Proveedor();
                proveedor.setId(rs.getInt("id"));
                proveedor.setNombre(rs.getString("nombre"));
                proveedor.setContacto(rs.getString("contacto"));
                listaProveedores.add(proveedor);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaProveedores;
    }

    public void agregarProveedor(Proveedor proveedor) {
        Connection conn = conexionBD.getConexion();
        String sql = "INSERT INTO proveedor (nombre, contacto) VALUES (?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, proveedor.getNombre());
            ps.setString(2, proveedor.getContacto());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizarProveedor(Proveedor proveedor) {
        Connection conn = conexionBD.getConexion();
        String sql = "UPDATE proveedor SET nombre = ?, contacto = ? WHERE id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, proveedor.getNombre());
            ps.setString(2, proveedor.getContacto());
            ps.setInt(3, proveedor.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Proveedor obtenerProveedorPorId(int id) {
        Proveedor proveedor = null;
        Connection conn = conexionBD.getConexion();
        String sql = "SELECT * FROM proveedor WHERE id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
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

    public int obtenerIdProveedorPorNombre(String nombre) {
        int idProveedor = -1;
        Connection conn = conexionBD.getConexion();
        String sql = "SELECT id FROM proveedor WHERE nombre = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                idProveedor = rs.getInt("id");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return idProveedor;
    }

    public String obtenerNombreProveedorPorId(int id) {
        String nombreProveedor = "";
        Connection conn = conexionBD.getConexion();
        String sql = "SELECT nombre FROM proveedor WHERE id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                nombreProveedor = rs.getString("nombre");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return nombreProveedor;
    }
}
