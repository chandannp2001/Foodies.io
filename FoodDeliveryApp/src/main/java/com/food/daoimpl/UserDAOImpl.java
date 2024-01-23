package com.food.daoimpl;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.food.dao.UserDAO;
import com.food.model.User;

public class UserDAOImpl implements UserDAO {

	
	private static final String INSERT_QUERY = "INSERT INTO `user` (`Username`, `Password`, `Email`, `Address`, `Role`) VALUES (?,?,?,?,?)";
	private static final String UPDATE_QUERY = "UPDATE `user` SET `Username`=?,`Password`=?, `Email`=?, `Address`=?,  `Role`=? WHERE (`UserID`=?)";
	private static final String DELETE_QUERY = "DELETE FROM `user` WHERE (`UserID`=?)";
	private static final String SELECT_QUERY = "SELECT * FROM `user` WHERE (`UserID`=?)";
	private static final String SELECTALL_QUERY =  "SELECT * FROM `user`";


	private static Connection connection;
	private static PreparedStatement prepareStatement;
	private static Statement statement;
	private static ResultSet res ;
	
	
	
	
	
	public UserDAOImpl() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/swiggyy","root", "root");
		} catch (ClassNotFoundException  | SQLException e) {
			e.printStackTrace();
		}
	}

	
	
	@Override
	public void addUser(User user) {
		try {
			prepareStatement = connection.prepareStatement(INSERT_QUERY);
//			prepareStatement.setInt(1, user.getUserId());
			prepareStatement.setString(1, user.getUsername());
			prepareStatement.setString(2, user.getPassword());
			prepareStatement.setString(3, user.getEmail());
			prepareStatement.setString(4, user.getAddress());
			prepareStatement.setString(5, user.getRole());
			
			int i = prepareStatement.executeUpdate();
			System.out.println(i);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public User getUser(int userId) {
		
		try {
			prepareStatement = connection.prepareStatement(SELECT_QUERY);// "SELECT * FROM `user` WHERE (`UserID`=?)";
			prepareStatement.setInt(1, userId);
			res = prepareStatement.executeQuery();
			while(res.next()) {
			return new User(res.getInt("UserID"),res.getString("Username"),res.getString("Password"),res.getString("Email"),res.getString("Address"),res.getString("Role"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void updateUser(User user) {

		try {
			prepareStatement = connection.prepareStatement(UPDATE_QUERY);
			prepareStatement.setString(1, user.getUsername());//"UPDATE `user` SET `Username`=?,`Password`=?, `Email`=?, `Address`=?,  `Role`=? WHERE (`UserID`=?)";
			prepareStatement.setString(2, user.getPassword());
			prepareStatement.setString(3, user.getEmail());
			prepareStatement.setString(4, user.getAddress());
			prepareStatement.setString(5, user.getRole());
			prepareStatement.setInt(6, user.getUserId());
			
			int i = prepareStatement.executeUpdate();
			System.out.println(i);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteUser(int userId) {
		
		try {
			prepareStatement = connection.prepareStatement(DELETE_QUERY);//"DELETE FROM `user` WHERE (`UserID`=?)";
			prepareStatement.setInt(1, userId);
			int i = prepareStatement.executeUpdate();
			System.out.println(i);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<User> getAllUsers() {
		
		List<User> list = new ArrayList<User>();
		
		try {
			statement = connection.createStatement();
			res = statement.executeQuery(SELECTALL_QUERY);
			
			while(res.next()) {
				User u = new User(res.getInt("UserID"),res.getString("Username"),res.getString("Password"),res.getString("Email"),res.getString("Address"),res.getString("Role"));
				list.add(u);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
