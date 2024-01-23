package com.food.daoimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.food.dao.MenuDAO;
import com.food.model.Menu;

public class MenuDAOImpl implements MenuDAO {
	
	
	private static final String INSERT_QUERY = "INSERT INTO `menu` (`MenuID`, `RestaurantID`, `ItemName`, `Description`, `Price`, `IsAvailable`) VALUES(?,?,?,?,?,?)";
	private static final String UPDATE_QUERY = "UPDATE `menu` SET `RestaurantID`=?, `ItemName`=?, `Description`=?,  `Price`=?,  `IsAvailable`=? WHERE (`MenuID`=?)";
	private static final String DELETE_QUERY = "DELETE FROM `menu` WHERE (`MenuID`=?)";
	private static final String SELECT_QUERY = "SELECT * FROM `menu` WHERE (`MenuID`=?)";
	private static final String SELECTALL_QUERY = "SELECT * FROM `menu` WHERE (`RestaurantID`=?) ";

	private static Connection connection;
	private static PreparedStatement prepareStatement;
	private static ResultSet res;
	
	

	public MenuDAOImpl() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/swiggyy","root", "root");
		} catch (ClassNotFoundException  | SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addMenu(Menu menu) {
		try {
			prepareStatement = connection.prepareStatement(INSERT_QUERY);
			
			prepareStatement.setInt(1, menu.getMenuId());
			prepareStatement.setInt(2, menu.getRestaurantId());
			prepareStatement.setString(3, menu.getItemName());
			prepareStatement.setString(4, menu.getDescription());
			prepareStatement.setDouble(5, menu.getPrice());
			prepareStatement.setBoolean(6, menu.isAvailable());
			
			int i = prepareStatement.executeUpdate();
			System.out.println(i);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Menu getMenu(int menuId) {//(`MenuID`, `RestaurantID`, `ItemName`, `Description`, `Price`, `IsAvailable`)
		try {
			prepareStatement = connection.prepareStatement(SELECT_QUERY);// "SELECT * FROM `menu` WHERE (`MenuID`=?)";
			prepareStatement.setInt(1, menuId);
			res = prepareStatement.executeQuery();
			while (res.next()) {
				return new Menu(res.getInt("MenuID"),res.getInt("RestaurantID"),res.getString("ItemName"),res.getString("Description"),
						res.getDouble("Price"),res.getBoolean("IsAvailable"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
		
	

	@Override
	public void updateMenu(Menu menu) {   //"UPDATE `menu` SET `RestaurantID`=?, `ItemName`=?, `Description`=?,  `Price`=?,  `IsAvailable`=? WHERE (`MenuID`=?)"
		try {
			prepareStatement = connection.prepareStatement( UPDATE_QUERY);
			
			prepareStatement.setInt(1, menu.getRestaurantId());
			prepareStatement.setString(2, menu.getItemName());
			prepareStatement.setString(3, menu.getDescription());
			prepareStatement.setDouble(4, menu.getPrice());
			prepareStatement.setBoolean(5, menu.isAvailable());
			prepareStatement.setInt(6, menu.getMenuId());
			
			int i = prepareStatement.executeUpdate();
			System.out.println(i);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void deleteMenu(int menuId) {

		try {
			prepareStatement = connection.prepareStatement(DELETE_QUERY);// "DELETE FROM `user` WHERE (`UserID`=?)";
			prepareStatement.setInt(1, menuId);
			int i = prepareStatement.executeUpdate();
			System.out.println(i);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Menu> getAllMenuByRestaurant(int restaurantId) {//"SELECT * FROM `menu` WHERE (`RestaurantID`=?) "

		List<Menu> list = new ArrayList<Menu>();

		try {

			prepareStatement = connection.prepareStatement(SELECTALL_QUERY);
			prepareStatement.setInt(1, restaurantId);
			
			res = prepareStatement.executeQuery();
			
			while (res.next()) {
				Menu u = new Menu(res.getInt("MenuID"),res.getInt("RestaurantID"),res.getString("ItemName"),res.getString("Description"),
						res.getDouble("Price"),res.getBoolean("IsAvailable"));
				list.add(u);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
