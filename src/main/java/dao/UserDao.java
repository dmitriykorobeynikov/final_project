package dao;

import org.apache.log4j.Logger;
import records.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    private Connection connection;
    private static final Logger log = Logger.getLogger(UserDao.class);

    public UserDao(Connection connection){
        this.connection = connection;
    }

    public User getByLoginAndPassword(String login, String password){
        User user = null;
        PreparedStatement ps;
        ResultSet rs ;
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
            log.error("Can't perform a query",e);
        }
        return user;
    }

    public boolean insert(User user){
        PreparedStatement ps;
        ResultSet rs;

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
            log.error("Can't perform a query",e);
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
            log.error("Can't perform a query",e);
        }

        return true;
    }
}
