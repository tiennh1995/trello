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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class Support {

    private Connection connection;
    private Statement statement;
    ArrayList<Integer> arrayI = new ArrayList<>();
    Job job;

    public Support(Connection connection, Statement statement) {
        this.connection = connection;
        this.statement = statement;
        job = new Job(connection, statement);
        arrayI.add(31);
        arrayI.add(28);
        arrayI.add(31);
        arrayI.add(30);
        arrayI.add(31);
        arrayI.add(30);
        arrayI.add(31);
        arrayI.add(31);
        arrayI.add(30);
        arrayI.add(31);
        arrayI.add(30);
        arrayI.add(31);
    }

    public String Search(String title) {
        try {
            String da = new String();
            String ten = new String();
            String result = new String();
            String key = new String();
            String tt = new String();
            switch (title) {
                case "DuAn":
                    key = "DA#";
                    break;
                case "ThanhVien":
                    key = "TV#";
                    break;
                case "CongViec":
                    key = "CV#";
                    break;
            }
            if (key.equals("TV#")) {
                String sqlCommand = "select \"TV#\",\"Ten\",\"UserName\",\"Admin\" FROM \"" + title + "\"";
                ResultSet rs = statement.executeQuery(sqlCommand);
                while (rs.next()) {
                    da = rs.getString(key);
                    ten = rs.getString("Ten");
                    String username = rs.getString("UserName");
                    String admin = rs.getString("Admin");
                    result += da + "|" + ten + "|" + username + "|" + admin + "|";
                }
                return result;
            } else if (key.equals("DA#")) {
                String sqlCommand = "select \"" + key + "\",\"Ten\",\"TrangThai\" FROM \"" + title + "\"";
                ResultSet rs = statement.executeQuery(sqlCommand);
                while (rs.next()) {
                    da = rs.getString(key);
                    ten = rs.getString("Ten");
                    tt = rs.getString("TrangThai");
                    result += da + "|" + ten + "|" + tt + "|";
                }
                return result;
            } else {
                String sqlCommand = "select \"" + key + "\",\"Ten\" FROM \"" + title + "\"";
                ResultSet rs = statement.executeQuery(sqlCommand);
                while (rs.next()) {
                    da = rs.getString(key);
                    ten = rs.getString("Ten");
                    result += da + "|" + ten + "|";
                }
                return result;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BoardServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String getMaxId(String name_tb, String columnName) {
        int id = 0, max = 0;
        try {
            String sqlCommand = "select\"" + columnName + "\"from \"" + name_tb + "\"";
            ResultSet rs = statement.executeQuery(sqlCommand);
            while (rs.next()) {
                id = rs.getInt(columnName);
                if (id > max) {
                    max = id;
                }
            }
            return String.valueOf(max + 1);
        } catch (SQLException ex) {
            Logger.getLogger(BoardServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    public int soSanh(java.util.Date date1, java.util.Date date2) {
        int d1, m1, y1;
        if (date1 == null) {
            return 9999;
        }
        d1 = date1.getDate();
        m1 = date1.getMonth() + 1;
        y1 = date1.getYear() + 1900;
        int d2, m2, y2;
        d2 = date2.getDate();
        m2 = date2.getMonth() + 1;
        y2 = date2.getYear() + 1900;
        int da1 = d1, da2 = d2;

        for (int i = 0; i < m1 - 1; i++) {
            da1 += arrayI.get(i);
        }
        //da1 += y1 / 4;
        da1 += (y1 - 1) * 365;
        if ((y1 % 4 == 0) && (m1 <= 2)) {
            da1 -= 1;
        }

        for (int i = 0; i < m2 - 1; i++) {
            da2 += arrayI.get(i);
        }
        //da2 += y2 / 4;
        da2 += (y2 - 1) * 365;
        if ((y2 % 4 == 0) && (m2 <= 2)) {
            da2 -= 1;
        }
        return da2 - da1;

    }

    public void insertThongBao(int TV, String nd) {
        try {
            String sqlCommand = "insert into \"ThongBao\" values(?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sqlCommand);
            ps.setInt(1, TV);
            ps.setString(2, nd);
            ps.setString(3, "0");
            ps.executeUpdate();
        } catch (SQLException ex) {
            try {
                String sqlCommand = "update \"ThongBao\" set \"TrangThai\" = '0' where \"TV#\"='" + TV + "' AND \"NoiDung\"='" + nd + "'";
                PreparedStatement ps = connection.prepareStatement(sqlCommand);
                ps.executeUpdate();
            } catch (SQLException ex1) {
                Logger.getLogger(BoardServer.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }

}
