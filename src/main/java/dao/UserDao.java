package dao;

import records.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    private Connection connection;

    public UserDao(Connection connection){
        this.connection = connection;
    }

    public User getByLoginAndPassword(String login, String password) throws SQLException {
        User user = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "SELECT * FROM user WHERE login = ? AND password = ?";
            ps = connection.prepareStatement(query);
            ps.setString(1,login);
            ps.setString(2,password);
            rs = ps.executeQuery();

            if (rs.next()){
                user = new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public boolean insert(User user){
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String query = "SELECT id FROM user WHERE login = ?";
            ps = connection.prepareStatement(query);
            ps.setString(1,user.getLogin());
            rs = ps.executeQuery();
            if (rs.next()) {
                ps.close();
                rs.close();
                return false;
            }
            ps.close();
            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            String query = "INSERT INTO user (login,password,role,address) VALUES (?,?,?,?)";
            ps = connection.prepareStatement(query);
            ps.setString(1,user.getLogin());
            ps.setString(2,user.getPassword());
            ps.setString(3,user.getRole());
            ps.setString(4,user.getAddress());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }
}
