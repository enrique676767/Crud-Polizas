package polizas.Marcas;

import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import polizas.General.Conexion;
import polizas.General.DAO;

public class MarcasModelo extends Conexion implements DAO {
    MarcasModelo() {
        super();
    }

    @Override
    public List read() {
        List<Marca> lista = new ArrayList<>();
        try {
            this.conectar();
            ps = cnx.prepareStatement("SELECT * FROM marcas");
            rs = ps.executeQuery();
            lista.clear();
            while (rs.next()) {
                lista.add(new Marca(rs.getInt("id"), rs.getString("nombre")));
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        } finally {
            try {
                if (cnx != null) cnx.close();
            } catch (SQLException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }
        return lista;
    }

    @Override
    public int create(Object obj) {
        try {
            this.conectar();
            Marca marca = (Marca) obj;
            ps = cnx.prepareStatement("INSERT INTO marcas (nombre) VALUES (?)");
            ps.setString(1, marca.getNombre());
            return ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        } finally {
            try {
                if (cnx != null) cnx.close();
            } catch (SQLException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }
        return 0;
    }

    @Override
    public int delete(Object obj) {
        return 0;
    }

    @Override
    public int update(Object lista) {
        return 0;
    }
}