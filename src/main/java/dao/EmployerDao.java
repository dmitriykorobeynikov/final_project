package dao;


import org.apache.log4j.Logger;
import records.Employer;
import records.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployerDao {
    private Connection connection;
    private static final Logger log = Logger.getLogger(EmployerDao.class);

    public EmployerDao(Connection connection) {
        this.connection = connection;
    }

    public ArrayList<Employer> selectInvolvedEmployers(int order_id){
        ArrayList<Employer> list = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;

        try {
            String query = "SELECT * FROM employer WHERE id IN (SELECT employer_id FROM order_employer WHERE order_id = ?)";
            ps = connection.prepareStatement(query);
            ps.setInt(1,order_id);
            rs = ps.executeQuery();
            while (rs.next()){
                list.add(new Employer(rs.getInt(1),rs.getString(2),rs.getString(3)));
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            log.error("Can't perform a query",e);
        }
        return list;
    }

    public ArrayList<Employer> selectUnInvolvedEmployers(int order_id){
        ArrayList<Employer> list = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;

        try {
            String query = "SELECT * FROM employer WHERE id NOT IN (SELECT employer_id FROM order_employer WHERE order_id = ?)";
            ps = connection.prepareStatement(query);
            ps.setInt(1,order_id);
            rs = ps.executeQuery();
            while (rs.next()){
                list.add(new Employer(rs.getInt(1),rs.getString(2),rs.getString(3)));
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            log.error("Can't perform a query",e);
        }
        return list;
    }


    public void insertOrderEmployer(int order_id, int employer_id){
        PreparedStatement ps;
        try {
            String query = "INSERT INTO order_employer (order_id, employer_id) VALUES (?,?)";
            ps = connection.prepareStatement(query);
            ps.setInt(1,order_id);
            ps.setInt(2,employer_id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            log.error("Can't perform a query",e);
        }
    }

    public void deleteOrderEmployer(int order_id, int employer_id){

        PreparedStatement ps;
        try {
            String query = "DELETE FROM order_employer WHERE order_id = ? AND employer_id = ?";
            ps = connection.prepareStatement(query);
            ps.setInt(1,order_id);
            ps.setInt(2,employer_id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            log.error("Can't perform a query",e);
        }
    }
}
