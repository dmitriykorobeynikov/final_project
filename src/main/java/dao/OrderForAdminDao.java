package dao;

import org.apache.log4j.Logger;
import records.Order;
import records.OrderForAdmin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderForAdminDao {
    private Connection connection;
    private static final Logger log = Logger.getLogger(OrderForAdminDao.class);

    public OrderForAdminDao(Connection connection) {
        this.connection = connection;
    }

    public ArrayList<OrderForAdmin> selectAll(){
        ArrayList<OrderForAdmin> list = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;

        try {
            String query = "SELECT `order`.id,user_id,type_of_work,volume,finish_date," +
                    "address FROM testbd.`order`,testbd.user WHERE user.id = `order`.user_id";
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()){
                list.add(new OrderForAdmin(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getDate(5),rs.getString(6)) );
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            log.error("Can't perform a query",e);
        }
        return list;
    }

    public OrderForAdmin selectByOrderId(int id){
        OrderForAdmin orderForAdmin = null;
        PreparedStatement ps;
        ResultSet rs;
        try {
            String query = "SELECT `order`.id,user_id,type_of_work,volume,finish_date," +
                    "address FROM testbd.`order`,testbd.user WHERE user.id = `order`.user_id AND `order`.id = ?";
            ps = connection.prepareStatement(query);
            ps.setInt(1,id);
            rs = ps.executeQuery();
            if (rs.next()){
                orderForAdmin = new OrderForAdmin(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getDate(5),rs.getString(6));
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            log.error("Can't perform a query",e);
        }
        return orderForAdmin;
    }
}
