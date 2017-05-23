/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boardserver;

import com.sun.corba.se.spi.activation.Server;
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
public class Mem {

    private Connection connection;
    private Statement statement;

    public Mem(Connection connection, Statement statement) {
        this.connection = connection;
        this.statement = statement;
    }

    public String isTrue(String name_tb, String username, String password) {
        String name = "";
        String id = "";
        String admin = "";
        username=username.toLowerCase();
        try {
            String sqlCommand = "select \"PassWord\" from " + "\"" + name_tb + "\"" + " where \"UserName\" = '" + username + "'";
            ResultSet rs = statement.executeQuery(sqlCommand);
            String pass = null;
            while (rs.next()) {
                pass = rs.getString("Password");
            }
            if (password.equals(pass)) {
                sqlCommand = "select \"Ten\",\"TV#\",\"Admin\" from " + "\"" + name_tb + "\"" + " where \"UserName\" = '" + username + "'";
                rs = statement.executeQuery(sqlCommand);
                while (rs.next()) {
                    name = rs.getString("Ten");
                    id = rs.getString("TV#");
                    admin = rs.getString("Admin");
                }
                return id + "|" + name + "|" + admin;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String creatNewAccount(String userID, String userName, String Pass, String name) {
        try {
            userName=userName.toLowerCase();
            String sqlCommand = "insert into \"ThanhVien\"(\"TV#\",\"Ten\",\"UserName\",\"PassWord\","
                    + "\"Avatar\", \"Admin\") values(?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sqlCommand);
            ps.setInt(1, Integer.parseInt(userID));
            ps.setString(2, name);
            ps.setString(3, userName);
            ps.setString(4, Pass);
            ps.setString(5, "");
            ps.setString(6, "0");
            if (ps.executeUpdate() != 0) {
                return "SUCC" + "|" + userID;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BoardServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "FAIL";
    }

    public String getNot(String TV) {
        String not = new String(), nd = new String(), tt = new String();
        try {
            String sqlCommand = "select \"TV#\",\"NoiDung\",\"TrangThai\" from \"ThongBao\""
                    + "where \"TV#\"='" + Integer.parseInt(TV) + "'"
                    + "limit 20";
            ResultSet rs = statement.executeQuery(sqlCommand);
            while (rs.next()) {
                nd = rs.getString("NoiDung");
                tt = rs.getString("TrangThai");
                not += nd + "|" + tt + "|";
            }
            return not;
        } catch (SQLException ex) {
            Logger.getLogger(BoardServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String getAvatar(String id) {
        String avatar = "";
        try {
            String sqlCommand = "select \"Avatar\" from  \"ThanhVien\" where \"TV#\" = '" + id + "'";
            ResultSet rs = statement.executeQuery(sqlCommand);
            while (rs.next()) {
                avatar = rs.getString("Avatar");
            }
            if (avatar == null) {
                return "";
            }
            return avatar;
        } catch (SQLException ex) {
            Logger.getLogger(BoardServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    boolean addMemJ(String job, String tv) {
        try {
            if (tv != null && tv.equals("") == false) {
                String sqlCommand = "insert into \"PhuTrach\" values(?,?)";
                PreparedStatement ps = connection.prepareStatement(sqlCommand);
                ps.setInt(1, Integer.valueOf(job));
                ps.setInt(2, Integer.valueOf(tv));
                if (ps.executeUpdate() != 0) {
                    return true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(BoardServer.class.getName()).log(Level.SEVERE, null, ex);
            return true;
        }

        return false;

    }

    public boolean addMemP(String pro, String tv) {
        try {
            if (tv != null && tv.equals("") == false) {
                String sqlCommand = "insert into \"ThamGia\"(\"DA#\", \"TV#\") values(?,?)";
                PreparedStatement ps = connection.prepareStatement(sqlCommand);
                ps.setInt(1, Integer.valueOf(pro));
                ps.setInt(2, Integer.valueOf(tv));
                if (ps.executeUpdate() != 0) {
                    return true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(BoardServer.class.getName()).log(Level.SEVERE, null, ex);
            return true;
        }

        return false;

    }

    public boolean delMemJ(String job, String tv) {
        try {
            if (tv != null && tv.equals("") == false) {
                String sqlCommand = "delete from \"PhuTrach\" where \"TV#\"='" + tv + "' AND \"CV#\"='" + job + "'";
                PreparedStatement ps = connection.prepareStatement(sqlCommand);
                if (ps.executeUpdate() != 0) {
                    return true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(BoardServer.class.getName()).log(Level.SEVERE, null, ex);
            return true;
        }

        return false;

    }

    public boolean delMemP(String pro, String tv) {
        try {
            if (tv != null && tv.equals("") == false) {
                String sqlCommand = "delete from \"ThamGia\" where \"TV#\"='" + tv + "' AND \"DA#\"='" + pro + "'";
                PreparedStatement ps = connection.prepareStatement(sqlCommand);
                if (ps.executeUpdate() != 0) {
                    return true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(BoardServer.class.getName()).log(Level.SEVERE, null, ex);
            return true;
        }

        return false;

    }

    public String getMem(String id) {
        String mem = "";
        String tv = "", ten = "";
        try {
            String sqlCommand = "select \"TV#\",\"Ten\" from  \"ThanhVien\" where \"TV#\" = '" + id + "'";
            ResultSet rs = statement.executeQuery(sqlCommand);
            while (rs.next()) {
                tv = rs.getString("TV#");
                ten = rs.getString("Ten");
                mem += tv + "|" + ten + "|";
            }
            return mem;
        } catch (SQLException ex) {
            Logger.getLogger(BoardServer.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (Exception ex) {
            Logger.getLogger(BoardServer.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public String getMemJ(String id) {
        String mem = "";
        String tv = "", ten = "", user = new String();
        int i = 0;
        try {
            String sqlCommand = "select \"TV#\", \"UserName\", \"Ten\" from  \"ThanhVien\"  NATURAL JOIN \"PhuTrach\" where \"CV#\" = '" + id + "'";
            ResultSet rs = statement.executeQuery(sqlCommand);
            while (rs.next()) {
                tv = rs.getString("TV#");
                user = rs.getString("UserName");
                ten = rs.getString("Ten");

                mem += tv + "|" + user + "|" + ten + "|";
                i++;
            }
            if (mem.equals("") == false) {
                mem = id + "|" + String.valueOf(i) + "|" + mem;
            } else {
                mem = id + "|" + String.valueOf(i) + "|";
            }
            return mem;
        } catch (SQLException ex) {
            Logger.getLogger(BoardServer.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (Exception ex) {
            Logger.getLogger(BoardServer.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public String getMemP(String id) {
        String mem = new String();
        String tv = "", user = "", ten = "";
        int i = 0;
        try {
            String sqlCommand = "select \"TV#\", \"UserName\",\"Ten\" from  \"ThanhVien\"  NATURAL JOIN \"ThamGia\" where \"DA#\" = '" + id + "'";
            ResultSet rs = statement.executeQuery(sqlCommand);
            while (rs.next()) {
                tv = rs.getString("TV#");
                user = rs.getString("UserName");
                ten = rs.getString("Ten");
                mem += tv + "|" + user + "|" + ten + "|";
                i++;
            }
            if (mem.equals("") == false) {
                mem = id + "|" + String.valueOf(i) + "|" + mem;
            } else {
                mem = id + "|" + String.valueOf(i) + "|";
            }
            return mem;
        } catch (SQLException ex) {
            Logger.getLogger(BoardServer.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (Exception ex) {
            Logger.getLogger(BoardServer.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
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

    public boolean addComment(String CV, String TV, String cmt, int type) {
        String id = getMaxId("BinhLuan", "BL#");
        try {
            String sqlCommand = "insert into \"BinhLuan\"(\"BL#\",\"CV#\",\"TV#\",\"LoiBinh\") values(?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sqlCommand);
            ps.setInt(1, Integer.parseInt(id));
            ps.setInt(2, Integer.parseInt(CV));
            ps.setInt(3, Integer.parseInt(TV));
            if (type == 0) {
                ps.setString(4, "TXT|" + cmt);
            } else if (type == 1) {
                ps.setString(4, "IMG|" + cmt + id);
            }
            if (ps.executeUpdate() != 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BoardServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean addAvatar(String TV, String url) {
        try {
            String sqlCommand = "update \"ThanhVien\" set \"Avatar\"='" + url + "' where \"TV#\"='" + TV + "'";
            PreparedStatement ps = connection.prepareStatement(sqlCommand);
            int row = ps.executeUpdate();
            if (row != 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BoardServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }

    public boolean delAvatar(String TV) {
        try {
            String sqlCommand = "update \"ThanhVien\" set \"Avatar\"='" + "' where \"TV#\"='" + TV + "'";
            PreparedStatement ps = connection.prepareStatement(sqlCommand);
            int row = ps.executeUpdate();
            if (row != 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BoardServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public ArrayList<Integer> getListMem(int CV) {
        try {
            ArrayList<Integer> arr = new ArrayList<>();
            String sqlCommand = "select \"TV#\" from \"PhuTrach\" where \"CV#\"='" + String.valueOf(CV) + "'";
            ResultSet rs = statement.executeQuery(sqlCommand);
            while (rs.next()) {
                int id = rs.getInt("TV#");
                arr.add(id);
            }

            return arr;
        } catch (SQLException ex) {
            Logger.getLogger(BoardServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean updateMem(int id, String pass, String ten) {
        try {
            String sqlCommand = "update \"ThanhVien\" set \"PassWord\"='" + pass + "',\"Ten\"='" + ten + "' where \"TV#\"='" + id + "'";
            PreparedStatement ps = connection.prepareStatement(sqlCommand);
            if (ps.executeUpdate() != 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BoardServer.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return false;
    }

    public boolean updateMem(int id, String userName, String ten, String isAdmin) {
        try {
            String sqlCommand = "update \"ThanhVien\" set \"Ten\"='" + ten + "',"
                    + "\"UserName\" = '" + userName + "', \"Admin\" = '" + isAdmin + "' where \"TV#\"='" + id + "'";
            PreparedStatement ps = connection.prepareStatement(sqlCommand);
            if (ps.executeUpdate() != 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BoardServer.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return false;
    }

    public void updateNot(int TV) {
        try {
            String sqlCommand = "update \"ThongBao\" set \"TrangThai\" = 1 where \"TV#\" = " + TV + "";
            PreparedStatement ps = connection.prepareStatement(sqlCommand);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BoardServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
