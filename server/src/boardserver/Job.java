/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boardserver;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class Job {

    private Connection connection;
    private Statement statement;

    public Job(Connection connection, Statement statement) {
        this.connection = connection;
        this.statement = statement;
    }

    public boolean addJob(String CV, String Ten, String type, String DA) {
        int test1 = 0, test2 = 0;
        try {
            String sqlCommand = "insert into \"CongViec\"(\"CV#\",\"Ten\",\"TrangThai\") values(?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sqlCommand);
            ps.setInt(1, Integer.parseInt(CV));
            ps.setString(2, Ten);
            ps.setString(3, type);
            test1 = ps.executeUpdate();

            sqlCommand = "insert into \"ViecCuaDuAn\" (\"CV#\",\"DA#\") values(?,?)";
            ps = connection.prepareStatement(sqlCommand);
            ps.setInt(1, Integer.parseInt(CV));
            ps.setInt(2, Integer.parseInt(DA));
            test2 = ps.executeUpdate();
            if (test1 != 0 && test2 != 0) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException ex) {
            Logger.getLogger(BoardServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public String updateNameJob(String id, String name) {
        try {
            String sqlCommand = "update \"CongViec\" set \"Ten\"='" + name + "' where \"CV#\"='" + id + "'";
            PreparedStatement ps = connection.prepareStatement(sqlCommand);
            int row = ps.executeUpdate();
            if (row != 0) {
                return name;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BoardServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean delJob(String id) {
        try {
            String sqlCommand = "delete from \"CongViec\" where \"CV#\"='" + id + "'";
            PreparedStatement ps = connection.prepareStatement(sqlCommand);
            if (ps.executeUpdate() != 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BoardServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean removeJob(String id, String type) {
        try {
            String sqlCommand = "update \"CongViec\" set \"TrangThai\"='" + type + "' where \"CV#\"='" + id + "'";
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

    public String getComment(String CV) {
        String cmt = "", ten = "", lb = "";
        int bl = 0;
        try {
            String sqlCommand = "select \"BL#\",\"Ten\",\"LoiBinh\" from  \"ThanhVien\"  NATURAL JOIN \"BinhLuan\" where \"CV#\" = '" + CV + "'";
            ResultSet rs = statement.executeQuery(sqlCommand);
            while (rs.next()) {
                bl = rs.getInt("BL#");
                ten = rs.getString("Ten");
                lb = rs.getString("LoiBinh");
                cmt += String.valueOf(bl) + "|" + ten + "|" + lb + "|";
            }
            return cmt;
        } catch (SQLException ex) {
            Logger.getLogger(BoardServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean updateDes(String id, String des) {
        try {
            String sqlCommand = "update \"CongViec\" set \"MoTa\"='" + des + "' where \"CV#\"='" + id + "'";
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

    public String getJobFromProject(String id) {
        String job = "";
        try {
            String ten = "";
            String cv = "";
            String tt = "";
            String mota = "";
            String dl = "";
            String sqlCommand = "select \"CV#\",\"Ten\",\"DeadLine\",\"MoTa\",\"TrangThai\" from \"ViecCuaDuAn\" NATURAL JOIN \"CongViec\" where \"DA#\" = '" + id + "'";
            ResultSet rs = statement.executeQuery(sqlCommand);
            while (rs.next()) {
                cv = rs.getString("CV#");
                ten = rs.getString("Ten");
                Date d = rs.getDate("DeadLine");
                if (d == null) {
                    dl = "null";
                } else {
                    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                    dl = df.format(d);
                    //dl = dl.replaceAll("-", "/");
                }
                mota = rs.getString("MoTa");
                if (mota == null) {
                    mota = "null";
                }
                tt = rs.getString("TrangThai");
                job += cv + "|" + ten + "|" + dl + "|" + mota + "|" + tt + "|";
            }
            return job;
        } catch (SQLException ex) {
            Logger.getLogger(BoardServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    public String getInfoJob(String id) {
        String infoJob = "";
        try {
            String mota = "";
            String dl = "";
            String sqlCommand = "select \"DeadLine\",\"MoTa\" from  \"CongViec\" where \"CV#\" = '" + id + "'";
            ResultSet rs = statement.executeQuery(sqlCommand);
            while (rs.next()) {
                Date d = rs.getDate("DeadLine");
                if (d != null) {
                    dl = d.toString();
                } else {
                    dl = null;
                }

                mota = rs.getString("MoTa");
            }
            if (dl == null) {
                dl = "null";
            }
            infoJob += dl + "|";
            if (mota == null) {
                mota = "Không có mô tả";
            }
            infoJob += mota + "|";
            return infoJob;
        } catch (SQLException ex) {
            Logger.getLogger(BoardServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean updateDeadLine(String id, String date) {
        String infoJob = "";
        try {
            Date dl = null;
            String sqlCommand = "update \"CongViec\" set \"DeadLine\"='" + date + "' where \"CV#\" = '" + id + "'";
            PreparedStatement ps = connection.prepareStatement(sqlCommand);

            if (ps.executeUpdate() != 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BoardServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }

    public Date getDeadLine(String id) {
        String infoJob = "";
        try {
            Date dl = null;
            String sqlCommand = "select \"DeadLine\" from \"CongViec\" where \"CV#\" = '" + id + "'";
            ResultSet rs = statement.executeQuery(sqlCommand);
            while (rs.next()) {
                dl = rs.getDate("DeadLine");
            }
            return dl;
        } catch (SQLException ex) {
            Logger.getLogger(BoardServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    public ArrayList<CongViec> getListJob() {
        try {
            String sqlCommand = "select \"CV#\",\"Ten\" from \"CongViec\" ";
            ArrayList<CongViec> arr = new ArrayList<>();
            ResultSet rs = statement.executeQuery(sqlCommand);
            while (rs.next()) {
                CongViec cv = new CongViec();
                cv.CV = rs.getInt("CV#");
                cv.Ten = rs.getString("Ten");
                arr.add(cv);
            }
            return arr;
        } catch (SQLException ex) {
            Logger.getLogger(BoardServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
