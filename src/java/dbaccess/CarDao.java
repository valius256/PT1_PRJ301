/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dbaccess;

import basicobject.Car;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import mylib.DBUtils;

/**
 *
 * @author Karui
 */
public class CarDao {

    public static ArrayList<Car> getAllCar() throws Exception {
        Connection cn = DBUtils.makeConnection();
        ArrayList<Car> list = new ArrayList<>();
        if (cn != null) {
            String sql = "SELECT * FROM dbo.tblCars";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs != null) {
                while (rs.next()) {
                    String id = rs.getString("id");
                    String name = rs.getString("name");
                    String description = rs.getString("description");
                    double price = rs.getDouble("price");
                    int speed = rs.getInt("speed");
                    String status = rs.getString("status");
                    Car c = new Car(id, name, description, speed, speed, true);
                    list.add(c);
                }
            }
            cn.close();
        }
        return list;
    }
    public static int insertCar(String id, String name, String description, double price, int speed, String status) throws Exception {
        int rs = 0;
        Connection cn = DBUtils.makeConnection();
        double minPrice = 0;
        double maxPrice = 1000000;
        if (id.length() != 5) {
            throw new IllegalArgumentException("ID must be exactly 5 characters long");
        }
        if (price < minPrice || price > maxPrice) {
            throw new IllegalArgumentException("The price must be between " + minPrice + " and " + maxPrice);
        }
        if (speed < 0) {
            throw new IllegalArgumentException("The speed must be a positive number.");
        }
        if (cn != null) {
            String sql = "INSERT dbo.tblCars(id, name, description, price, speed, status)\n"
                    + "VALUES(?,?,?,?,?,?)";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, name);
            pst.setString(3, description);
            pst.setDouble(4, price);
            pst.setInt(5, speed);
            pst.setString(6, status);
            rs = pst.executeUpdate();
            cn.close();
        }
        return rs;
    }

    public static Car getCar(String id) throws Exception {
        Car rs = null;
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "SELECT [id],[name],[description],[price],[speed],[status]\n"
                    + "FROM [dbo].[cars] where id=?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, id);
            ResultSet tmp = pst.executeQuery();
            if (tmp != null && tmp.next()) {
                String carId = tmp.getString("id");
                String name = tmp.getString("name");
                String description = tmp.getString("description");
                double price = tmp.getDouble("price");
                int speed = tmp.getInt("speed");
                String status = tmp.getString("status");
                rs = new Car(id, name, description, speed, speed, true);
            }
        }
        return rs;
    }

    public static void updateCar(String id, String name, String description, double price, int speed, String status) throws Exception {
        double minPrice = 0;
        double maxPrice = 1000000;
        int rs = 0;
        Connection cn = DBUtils.makeConnection();
        if (id.length() != 5) {
            throw new IllegalArgumentException("The ID must be exactly 5 characters long.");
        }

        if (price < minPrice || price > maxPrice) {
            throw new IllegalArgumentException("The price must be between " + minPrice + " and " + maxPrice + ".");
        }

        if (speed < 0) {
            throw new IllegalArgumentException("The speed must be a positive number.");
        }
        if (cn != null) {
            String sql = "UPDATE dbo.tblCars SET name = ?, description = ?, price = ?, speed = ?, status = ? WHERE id = ?;";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, name);
            pst.setString(3, description);
            pst.setDouble(4, price);
            pst.setInt(5, speed);
            pst.setString(6, status);
            rs = pst.executeUpdate();
            cn.close();
        }
    }

    public static ArrayList<Car> getCarsByName(String key) throws Exception {
        ArrayList<Car> list = new ArrayList();
        //Make connection
        Connection cn = new DBUtils().makeConnection();
        if (cn != null) {
            //Lenh querry
            String sql = "SELECT [id],[name],[description],[price],[speed],[status]\n"
                    + "FROM [dbo].[tblCars] WHERE name like ? and status = ?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, "%" + key + "%");
            ps.setBoolean(2, true);
            ResultSet rs = ps.executeQuery();

            if (rs != null) {
                try {
                    while (rs.next()) {
                        String id = rs.getString("id");
                        String name = rs.getString("name");
                        String description = rs.getString("description");
                        double price = Double.parseDouble(rs.getString("price"));
                        double speed = Double.parseDouble(rs.getString("speed"));
                        boolean status = rs.getString("status").equals("1");

                        Car car = new Car(id, name, description, price, speed, status);
                        list.add(car);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
       
        cn.close();
        return list;
    }

    public static Car getCarsById(String id) throws Exception {
        Car c = null;
        Connection cn = new DBUtils().makeConnection();
        if (cn != null) {
            //Lenh querry
            String sql = "SELECT [id],[name],[description],[price],[speed],[status]\n"
                    + "FROM [dbo].[tblCars] WHERE id = ? and status = ?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, id);
            ps.setBoolean(2, true);
            ResultSet rs = ps.executeQuery();

            if (rs != null) {

                while (rs.next()) {
//                        String id = rs.getString("id");
                    String name = rs.getString("name");
                    String description = rs.getString("description");
                    double price = Double.parseDouble(rs.getString("price"));
                    double speed = Double.parseDouble(rs.getString("speed"));
                    boolean status = rs.getString("status").equals("1");
                    c = new Car(id, name, description, price, speed, status);
                }
            }
        }
        cn.close();
        return c;
    }
}
