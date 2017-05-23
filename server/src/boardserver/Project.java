/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boardserver;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class Project {

    private Connection connection;
    private Statement statement;

    public Project(Connection connection, Statement statement) {
        this.connection = connection;
        this.statement = statement;
    }

    public boolean updateProject(String id, String name, String type) {
        try {
            String sqlCommand = "update \"DuAn\" set \"Ten\"='" + name + "', \"TrangThai\"='" + type + "' where \"DA#\" = '" + id + "'";
            PreparedStatement ps = connection.prepareStatement(sqlCommand);
            if (ps.executeUpdate() != 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BoardServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }

    public String getInfoProject(String idUser) {
        String project = new String();
        try {
            String ten = "";
            String da = "";
            String tt = "";
            String sqlCommand = "select \"DA#\",\"Ten\",\"TrangThai\" from \"ThamGia\" NATURAL JOIN \"DuAn\" where \"TV#\" = '" + idUser + "'";
            ResultSet rs = statement.executeQuery(sqlCommand);
            while (rs.next()) {
                ten = rs.getString("Ten");
                da = rs.getString("DA#");
                tt = rs.getString("TrangThai");
                project += da + "|" + ten + "|" + tt + "|";
            }
            return project;
        } catch (SQLException ex) {
            Logger.getLogger(BoardServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean addProject(String DA, String Ten, String userID) {
        try {
            String sqlCommand = "insert into \"DuAn\"(\"DA#\",\"Ten\",\"TrangThai\") values(?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sqlCommand);
            ps.setInt(1, Integer.parseInt(DA));
            ps.setString(2, Ten);
            ps.setString(3, "1");
            if (ps.executeUpdate() != 0) {
                sqlCommand = "insert into \"ThamGia\"(\"DA#\",\"TV#\") values(?,?)";
                ps = connection.prepareStatement(sqlCommand);
                ps.setInt(1, Integer.parseInt(DA));
                ps.setInt(2, Integer.parseInt(userID));
                if (ps.executeUpdate() != 0) {
                    return true;

                } else {
                    return false;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(BoardServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }


}
