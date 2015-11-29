package dao;


import records.Order;
import java.sql.Connection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class OrderDao {
    private Connection connection;

    public OrderDao(Connection connection) {
        this.connection = connection;
    }

    public ArrayList<Order> selectByUserId(int user_id){
        ArrayList<Order> list = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String query = "SELECT * FROM testbd.order WHERE user_id = ?";
            ps = connection.prepareStatement(query);
            ps.setInt(1,user_id);
            rs = ps.executeQuery();
            while (rs.next()){
                list.add(new Order(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getDate(5)) );
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;

    }

    public ArrayList<Order> selectAll(){
        ArrayList<Order> list = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String query = "SELECT * FROM testbd.order";
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()){
                list.add(new Order(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getDate(5)) );
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void update(Order order){
        PreparedStatement ps = null;
        try {
            String query = "UPDATE testbd.order SET user_id = ?, type_of_work = ?, volume = ?, finish_date = ?  WHERE id = ?";
            ps = connection.prepareStatement(query);
            ps.setInt(1,order.getUser_id());
            ps.setString(2,order.getType_of_work());
            ps.setString(3,order.getVolume());
            ps.setDate(4,order.getFinish_date());
            ps.setInt(5,order.getId());

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(Order order){
        PreparedStatement ps = null;
        try {
            String query = "DELETE FROM testbd.order WHERE id = ?";
            ps = connection.prepareStatement(query);
            ps.setInt(1,order.getId());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insert(Order order){
        PreparedStatement ps = null;
        try {
            String query = "INSERT INTO testbd.order (user_id, type_of_work , volume , finish_date) VALUES (?,?,?,?)";
            ps = connection.prepareStatement(query);
            ps.setInt(1,order.getUser_id());
            ps.setString(2,order.getType_of_work());
            ps.setString(3,order.getVolume());
            ps.setDate(4,order.getFinish_date());

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
