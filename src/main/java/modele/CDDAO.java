/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modele;

import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CDDAO {

    public List<CD> getAll() {
        List<CD> list = new ArrayList<>();

        String sql = "SELECT * FROM CD ORDER BY id DESC";

        try (Connection cnx = DBConnection.getConnection();
             PreparedStatement ps = cnx.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                CD cd = new CD();
                cd.setId(rs.getInt("id"));
                cd.setAlbum(rs.getString("album"));
                cd.setArtist(rs.getString("artist"));
                cd.setCountry(rs.getString("country"));
                cd.setPrice(rs.getFloat("price"));
                cd.setQuantity(1);

                list.add(cd);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    
    
    public void addCD(CD cd) {
    String sql = "INSERT INTO CD (album, artist, country, price) VALUES (?, ?, ?, ?)";

    try (Connection cnx = DBConnection.getConnection();
         PreparedStatement ps = cnx.prepareStatement(sql)) {

        ps.setString(1, cd.getAlbum());
        ps.setString(2, cd.getArtist());
        ps.setString(3, cd.getCountry());
        ps.setFloat(4, cd.getPrice());

        ps.executeUpdate();

    } catch (Exception e) {
        e.printStackTrace();
    }
}

public void updateCD(CD cd) {
    String sql = "UPDATE CD SET album=?, artist=?, country=?, price=? WHERE id=?";

    try (Connection cnx = DBConnection.getConnection();
         PreparedStatement ps = cnx.prepareStatement(sql)) {

        ps.setString(1, cd.getAlbum());
        ps.setString(2, cd.getArtist());
        ps.setString(3, cd.getCountry());
        ps.setFloat(4, cd.getPrice());
        ps.setInt(5, cd.getId());

        ps.executeUpdate();

    } catch (Exception e) {
        e.printStackTrace();
    }
}

public void deleteCD(int id) {
    String sql = "DELETE FROM CD WHERE id=?";

    try (Connection cnx = DBConnection.getConnection();
         PreparedStatement ps = cnx.prepareStatement(sql)) {

        ps.setInt(1, id);
        ps.executeUpdate();

    } catch (Exception e) {
        e.printStackTrace();
    }
}

public CD getById(int id) {
    String sql = "SELECT * FROM CD WHERE id=?";
    CD cd = null;

    try (Connection cnx = DBConnection.getConnection();
         PreparedStatement ps = cnx.prepareStatement(sql)) {

        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            cd = new CD(
                rs.getString("album"),
                rs.getString("artist"),
                rs.getString("country"),
                rs.getFloat("price"),
                1
            );
            cd.setId(id);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return cd;
}

    
    
    
}
