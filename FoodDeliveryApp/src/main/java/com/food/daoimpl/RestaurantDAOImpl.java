package com.food.daoimpl;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.food.dao.RestaurantDAO;
import com.food.model.Restaurant;

public class RestaurantDAOImpl implements RestaurantDAO {

	private static final String INSERT_QUERY = "INSERT INTO `restaurant` (`RestaurantID`, `Name`, `CuisineType`, `DeliveryTime`, `Address`, `AdminUserID`, `Rating`, `IsActive`, `ImagePath`) VALUES (?,?,?,?,?,?,?,?,?)";
	private static final String UPDATE_QUERY = "UPDATE `restaurant` SET  `Name`=?, `CuisineType`=?, `DeliveryTime`=?,  `Address`=?, `AdminUserID`=?,  `Rating`=?,  `IsActive`=?,  `ImagePath`=? WHERE (`RestaurantID`=?)";
	private static final String DELETE_QUERY = "DELETE FROM `restaurant` WHERE (`RestaurantID`=?)";
	private static final String SELECT_QUERY = "SELECT * FROM `restaurant` WHERE (`RestaurantID`=?)";
	private static final String SELECTALL_QUERY = "SELECT * FROM `restaurant`";

	private static Connection connection;
	private static PreparedStatement prepareStatement;
	private static Statement statement;
	private static ResultSet res;

	public RestaurantDAOImpl() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/swiggyy","root", "root");
		} catch (ClassNotFoundException  | SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addRestaurant(Restaurant restaurant) {
		
		try {//(`RestaurantID`, `Name`, `CuisineType`, `DeliveryTime`, `Address`, `AdminUserID`, `Rating`, `IsActive`, `ImagePath`)
			prepareStatement = connection.prepareStatement(INSERT_QUERY);
			prepareStatement.setInt(1, restaurant.getRestaurantId());
			prepareStatement.setString(2, restaurant.getName());
			prepareStatement.setString(3, restaurant.getCuisineType());
			prepareStatement.setInt(4, restaurant.getDeliveryTime());
			prepareStatement.setString(5, restaurant.getAddress());
			prepareStatement.setInt(6, restaurant.getAdminUserId());
			prepareStatement.setDouble(7, restaurant.getRating());
			prepareStatement.setBoolean(8, restaurant.isActive());
			prepareStatement.setString(9, restaurant.getImagePath());

			int i = prepareStatement.executeUpdate();
			System.out.println(i);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Restaurant getRestaurant(int restaurantId) {

		try {
			prepareStatement = connection.prepareStatement(SELECT_QUERY);// "SELECT * FROM `user` WHERE (`UserID`=?)";
			prepareStatement.setInt(1, restaurantId);
			res = prepareStatement.executeQuery();
			while (res.next()) {
				return new Restaurant(res.getInt("RestaurantId"),res.getString("Name"),res.getString("CuisineType"),res.getInt("DeliveryTime")
						,res.getString("Address"),res.getInt("AdminUserID"),res.getDouble("Rating"),res.getBoolean("IsActive"),res.getString("ImagePath"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void updateRestaurant(Restaurant restaurant) {

		try {
			prepareStatement = connection.prepareStatement(UPDATE_QUERY);
			
			prepareStatement.setString(1, restaurant.getName());
			prepareStatement.setString(2, restaurant.getCuisineType());
			prepareStatement.setInt(3, restaurant.getDeliveryTime());
			prepareStatement.setString(4, restaurant.getAddress());
			prepareStatement.setInt(5, restaurant.getAdminUserId());
			prepareStatement.setDouble(6, restaurant.getRating());
			prepareStatement.setBoolean(7, restaurant.isActive());
			prepareStatement.setString(8, restaurant.getImagePath());
			prepareStatement.setInt(9, restaurant.getRestaurantId());
			
			int i = prepareStatement.executeUpdate();
			System.out.println(i);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteRestaurant(int restaurantId) {

		try {
			prepareStatement = connection.prepareStatement(DELETE_QUERY);// "DELETE FROM `user` WHERE (`UserID`=?)";
			prepareStatement.setInt(1, restaurantId);
			int i = prepareStatement.executeUpdate();
			System.out.println(i);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Restaurant> getAllRestaurants() {

		List<Restaurant> list = new ArrayList<Restaurant>();

		try {
			statement = connection.createStatement();
			res = statement.executeQuery(SELECTALL_QUERY);

			while (res.next()) {
				Restaurant u =new Restaurant(res.getInt("RestaurantId"),res.getString("Name"),res.getString("CuisineType"),res.getInt("DeliveryTime")
						,res.getString("Address"),res.getInt("AdminUserID"),res.getDouble("Rating"),res.getBoolean("IsActive"),res.getString("ImagePath"));
				list.add(u);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
