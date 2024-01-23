package com.food.daoimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.food.dao.OrderHistoryDAO;
import com.food.model.OrderHistory;

public class OrderHistoryDAOImpl implements OrderHistoryDAO{

	
	private static final String INSERT_QUERY = "INSERT INTO `orderhistory` (`UserID`, `OrderID`, `TotalAmount`, `Status`) VALUES(?,?,?,?)";
	private static final String UPDATE_QUERY = "UPDATE `orderhistory` SET  `UserID`=?, `OrderID`=?, `OrderDate`=?,  `TotalAmount`=?, `Status`=?, WHERE (`OrderHistoryID`=?)";
	private static final String DELETE_QUERY = "DELETE FROM `orderhistory` WHERE (`OrderHistoryID`=?)";
	private static final String SELECT_QUERY = "SELECT * FROM `orderhistory` WHERE (`OrderHistoryID`=?)";
	private static final String SELECTALL_QUERY = "SELECT * FROM `orderhistory` WHERE (`UserID`=?)";

	private static Connection connection;
	private static PreparedStatement prepareStatement;
	private static ResultSet res;

	
	public OrderHistoryDAOImpl() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/swiggyy","root", "root");
		} catch (ClassNotFoundException  | SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addOrderHistory(OrderHistory orderHistory) {
		try {//int orderHistoryId, int userId, int orderId, Date orderDate, double totalAmount,String status)
			prepareStatement = connection.prepareStatement(INSERT_QUERY);
//			prepareStatement.setInt(1, orderHistory.getOrderHistroyId());
			prepareStatement.setInt(1, orderHistory.getUserId());
			prepareStatement.setInt(2, orderHistory.getOrderId());
//			prepareStatement.setDate(3, (java.sql.Date) orderHistory.getOrderDate());
			prepareStatement.setDouble(3, orderHistory.getTotalAmount());
			prepareStatement.setString(4, orderHistory.getStatus());

			int i = prepareStatement.executeUpdate();
			System.out.println(i);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateOrderHistory(OrderHistory orderHistory) {
		try {//int orderHistoryId, int userId, int orderId, Date orderDate, double totalAmount,String status)
			prepareStatement = connection.prepareStatement(UPDATE_QUERY);
			prepareStatement.setInt(1, orderHistory.getUserId());
			prepareStatement.setInt(2, orderHistory.getOrderId());
			prepareStatement.setDate(3, (java.sql.Date) orderHistory.getOrderDate());
			prepareStatement.setDouble(4, orderHistory.getTotalAmount());
			prepareStatement.setString(5, orderHistory.getStatus());
			prepareStatement.setInt(6, orderHistory.getOrderHistroyId());

			int i = prepareStatement.executeUpdate();
			System.out.println(i);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void deleteOrderHistory(int orderHistoryId) {

		try {
			prepareStatement = connection.prepareStatement(DELETE_QUERY);// "DELETE FROM `orderHistory` WHERE (`orderHistoryId`=?)";
			prepareStatement.setInt(1, orderHistoryId);
			int i = prepareStatement.executeUpdate();
			System.out.println(i);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public OrderHistory getOrderHistory(int orderHistoryId) {//OrderHistoryID`, `UserID`, `OrderID`, `OrderDate`, `TotalAmount`, `Status

		try {
			prepareStatement = connection.prepareStatement(SELECT_QUERY);// "SELECT * FROM `user` WHERE (`UserID`=?)";
			prepareStatement.setInt(1, orderHistoryId);
			res = prepareStatement.executeQuery();
			while (res.next()) {
				return new OrderHistory(res.getInt("OrderHistoryID"),res.getInt("UserID"),res.getInt("OrderID"),res.getDate("OrderDate")
						,res.getDouble("TotalAmount"),res.getString("Status"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<OrderHistory> getAllOrderHistorysByUser(int userId) {

		List<OrderHistory> list = new ArrayList<OrderHistory>();
		

		try {
			prepareStatement = connection.prepareStatement(SELECTALL_QUERY);
			prepareStatement.setInt(1, userId);
			
			res = prepareStatement.executeQuery();
			
			while (res.next()) {
				OrderHistory u =new OrderHistory(res.getInt("OrderHistoryID"),res.getInt("UserID"),res.getInt("OrderID"),res.getDate("OrderDate")
						,res.getDouble("TotalAmount"),res.getString("Status"));
				list.add(u);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
