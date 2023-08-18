/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dbaccess;

import basicobject.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import mylib.DBUtils;

/**
 *
 * @author LENOVO
 */
public class UserDao {

    // ham nayu de thuc thien ket noi SQLserver 
    //lay tat ca cac dong trong bang User
    // tra ve Arraylist<User>
    public static ArrayList<User> getAllUser() throws Exception {
        //tao cau noi giua app va SQL server
        Connection cn = DBUtils.makeConnection();
        ArrayList<User> list = new ArrayList<>();
        if (cn != null) {
            //viet SQL va excute
            String sql = "SELECT [userID],[fullName],[roleID],[password]\n"
                    + "FROM [dbo].[tblUsers]";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs != null) {
                while (rs.next()) {
                    String id = rs.getString("userID");
                    String name = rs.getString("fullname");
                    String role = rs.getString("roleID");
                    String password = rs.getString("password");
                    User us = new User(id, name, role, password);
                    list.add(us);
                }
            }
            cn.close();
        }

        return list;
    }

    public static int removeUser(String userid) throws Exception {
        int rs = 0;
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "delete from dbo.tblUsers where userID=?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, userid);
            rs = pst.executeUpdate();
            cn.close();
        }
        return rs;
    }

    // ham nay de sau password khi biet userid
    public static int resetPassword(String userid) throws Exception {
        int rs = 0;
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "update tblUsers set password=? where userID=?";
            PreparedStatement pst = cn.prepareStatement(sql);
            int newpassword = (int) (Math.random() * 100000) % 1000 + 10000;
            pst.setString(1, "" + newpassword);
            pst.setString(2, userid);
            rs = pst.executeUpdate();
            cn.close();
        }
        return rs;
    }

    // ham nay de insert 1 dong vao bang tblUsers
    // tra ve 1/0
    public static int insertUser(String userid, String fullname) throws Exception {
        int rs = 0;
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "INSERT dbo.tblUsers(userID, fullName, roleID, password)\n"
                    + "VALUES(?,?,?,?)";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, userid);
            pst.setString(2, fullname);
            pst.setString(3, "US");
            int password = (int) (Math.random() * 100000) % 1000 + 10000;
            pst.setString(4, "" + password);
            rs = pst.executeUpdate();
            cn.close();
        }
        return rs;
    }

    // ham nay de lay User trong bang tblUsers khi biet userid
    // tra ve user tim thay/null
    public static User getUser(String userid) throws Exception {
        User rs = null;
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "SELECT [userID],[fullName],[roleID],[password]\n"
                    + "FROM [dbo].[tblUsers] where userID=?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, userid);
            ResultSet tmp = pst.executeQuery();
            if (tmp != null && tmp.next()) {
                String id = tmp.getString("userID");
                String name = tmp.getString("fullname");
                String role = tmp.getString("roleID");
                String password = tmp.getString("password");
                rs = new User(userid, name, role, password);
            }
        }
        return rs;
    }
}
