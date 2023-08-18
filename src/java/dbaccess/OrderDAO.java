/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dbaccess;

import basicobject.Car;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import mylib.DBUtils;

/**
 *
 * @author Karui
 */
public class OrderDAO {

    // Hàm này sẽ chèn một dòng mới vào bảng order và cart vào orderdetail
    // trả về 1/0
    public static int insertOrder(String userid, HashMap<Car, Integer> cart) throws Exception {
        int result = 0;
        Connection cn = DBUtils.makeConnection();
        
        if (cn != null) {
            cn.setAutoCommit(false);
            // insert a new row into tblOrder
            String sql = "INSERT dbo.Orders\n"
                    + "(    \n"
                    + "    OrderDate,   \n"
                    + "    UserID,\n"
                    + "    Total\n"
                    + ")\n"
                    + "VALUES\n"
                    + "(?, ?, ?)";
            PreparedStatement pst = cn.prepareStatement(sql);
            Date d = new Date(System.currentTimeMillis());
            double sum = 0;
            for (Car c : cart.keySet()) sum = sum + c.getPrice() * cart.get(c);            
            pst.setDate(1, d);
            pst.setString(2, userid);
            pst.setInt(3, (int) sum);
           
            result = pst.executeUpdate();
            if (result > 0) {
                // insert cart into orderdetail
                sql = "SELECT MAX(OrderID) AS 'orderid' FROM dbo.Orders";
                pst = cn.prepareStatement(sql);
                ResultSet rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    int orderid = rs.getInt("orderid");
                    for (Car c : cart.keySet()) {
                        sql = "INSERT dbo.OrderDetail\n"
                                + "(\n"
                                + "    OrderID,\n"
                                + "    CarID,\n"
                                + "   Quantity\n"
                                + ")\n"
                                + "VALUES\n"
                                + "(?,?,?)";
                        pst = cn.prepareStatement(sql);
                        pst.setInt(1, orderid);
                        pst.setString(2, c.getId());
                        pst.setInt(3, cart.get(c));
                        result = pst.executeUpdate();
                    }
                }
            }

            cn.commit();
            cn.setAutoCommit(true);
            cn.close();
        }
        return result;
    }
}
