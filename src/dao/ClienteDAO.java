/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.Cliente;
import main.conexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    public List<Cliente> obtenerTodosLosClientes() {
        List<Cliente> listaClientes = new ArrayList<>();
        Connection conn = conexionBD.getConexion();
        String sql = "SELECT * FROM clientes";

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("id_cliente"));
                cliente.setPrimerNombre(rs.getString("nombre_1"));
                cliente.setSegundoNombre(rs.getString("nombre_2"));
                cliente.setPrimerApellido(rs.getString("apellido_1"));
                cliente.setSegundoApellido(rs.getString("apellido_2"));
                cliente.setContacto(rs.getString("contacto"));
                cliente.setGenero(rs.getString("genero"));
                listaClientes.add(cliente);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaClientes;
    }

    public List<Cliente> buscarClientesPorNombre(String nombre) {
        List<Cliente> listaClientes = new ArrayList<>();
        Connection conn = conexionBD.getConexion();
        String sql = "SELECT * FROM clientes WHERE nombre_1 LIKE ? OR nombre_2 LIKE ? OR apellido_1 LIKE ? OR apellido_2 LIKE ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            String queryParam = "%" + nombre + "%";
            ps.setString(1, queryParam);
            ps.setString(2, queryParam);
            ps.setString(3, queryParam);
            ps.setString(4, queryParam);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("id_cliente"));
                cliente.setPrimerNombre(rs.getString("nombre_1"));
                cliente.setSegundoNombre(rs.getString("nombre_2"));
                cliente.setPrimerApellido(rs.getString("apellido_1"));
                cliente.setSegundoApellido(rs.getString("apellido_2"));
                cliente.setContacto(rs.getString("contacto"));
                cliente.setGenero(rs.getString("genero"));
                listaClientes.add(cliente);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaClientes;
    }

    public void agregarCliente(Cliente cliente) {
        Connection conn = conexionBD.getConexion();
        String sql = "INSERT INTO clientes (nombre_1, nombre_2, apellido_1, apellido_2, contacto, genero) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, cliente.getPrimerNombre());
            ps.setString(2, cliente.getSegundoNombre());
            ps.setString(3, cliente.getPrimerApellido());
            ps.setString(4, cliente.getSegundoApellido());
            ps.setString(5, cliente.getContacto());
            ps.setString(6, cliente.getGenero());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizarCliente(Cliente cliente) {
        Connection conn = conexionBD.getConexion();
        String sql = "UPDATE clientes SET nombre_1 = ?, nombre_2 = ?, apellido_1 = ?, apellido_2 = ?, contacto = ?, genero = ? WHERE id_cliente = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, cliente.getPrimerNombre());
            ps.setString(2, cliente.getSegundoNombre());
            ps.setString(3, cliente.getPrimerApellido());
            ps.setString(4, cliente.getSegundoApellido());
            ps.setString(5, cliente.getContacto());
            ps.setString(6, cliente.getGenero());
            ps.setInt(7, cliente.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Cliente obtenerClientePorId(int id) {
        Cliente cliente = null;
        Connection conn = conexionBD.getConexion();
        String sql = "SELECT * FROM clientes WHERE id_cliente = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                cliente = new Cliente();
                cliente.setId(rs.getInt("id_cliente"));
                cliente.setPrimerNombre(rs.getString("nombre_1"));
                cliente.setSegundoNombre(rs.getString("nombre_2"));
                cliente.setPrimerApellido(rs.getString("apellido_1"));
                cliente.setSegundoApellido(rs.getString("apellido_2"));
                cliente.setContacto(rs.getString("contacto"));
                cliente.setGenero(rs.getString("genero"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cliente;
    }
    
    public int obtenerIdClientePorNombre(String nombre) {
        int idCliente = -1;
        Connection conn = conexionBD.getConexion();
        String sql = "SELECT id_cliente FROM clientes WHERE CONCAT(nombre_1, ' ', nombre_2, ' ', apellido_1, ' ', apellido_2) = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                idCliente = rs.getInt("id_cliente");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return idCliente;
    }

    
    public String obtenerNombreClientePorId(int id) {
        String nombreCliente = "";
        Connection conn = conexionBD.getConexion();
        String sql = "SELECT nombre_1, nombre_2, apellido_1, apellido_2 FROM clientes WHERE id_cliente = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String nombre1 = rs.getString("nombre_1");
                String nombre2 = rs.getString("nombre_2");
                String apellido1 = rs.getString("apellido_1");
                String apellido2 = rs.getString("apellido_2");

                nombreCliente = String.join(" ", nombre1, nombre2 != null ? nombre2 : "", apellido1, apellido2 != null ? apellido2 : "").trim();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return nombreCliente;
    }
}
