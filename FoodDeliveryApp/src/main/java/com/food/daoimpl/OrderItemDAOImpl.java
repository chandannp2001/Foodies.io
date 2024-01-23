package com.food.daoimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.food.dao.OrderItemDAO;
import com.food.model.OrderItem;

public class OrderItemDAOImpl implements OrderItemDAO{
	
	private static final String INSERT_QUERY = "INSERT INTO `orderitem` (`OrderID`, `MenuID`, `Quantity`, `ItemTotal`) VALUES (?,?,?,?)";
	private static final String UPDATE_QUERY = "UPDATE `orderitem` SET  `OrderID`=?, `MenuID`=?, `Quantity`=?,  `ItemTotal`=? WHERE (`OrderItemID`=?)";
	private static final String DELETE_QUERY = "DELETE FROM `orderitem` WHERE (`OrderItemID`=?)";
	private static final String SELECT_QUERY = "SELECT * FROM `orderitem` WHERE (`OrderItemID`=?)";
	private static final String SELECTALL_QUERY = "SELECT * FROM `orderitem` WHERE (`OrderID`=?)";

	private static Connection connection;
	private static PreparedStatement prepareStatement;
	private static ResultSet res;


	public OrderItemDAOImpl() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/swiggyy","root", "root");
		} catch (ClassNotFoundException  | SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void addOrderItem(OrderItem orderItem) {//int orderItemId, int orderId, int menuId, int quantity, double itemTotal) {

		try {
			prepareStatement = connection.prepareStatement(INSERT_QUERY);
			
//			prepareStatement.setInt(1, orderItem.getOrderItemId());
			prepareStatement.setInt(1, orderItem.getOrderId());
			prepareStatement.setInt(2, orderItem.getMenuId());
			prepareStatement.setInt(3, orderItem.getQuantity());
			prepareStatement.setDouble(4, orderItem.getItemTotal());
			
			int i = prepareStatement.executeUpdate();
			System.out.println(i);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateOrderItem(OrderItem orderItem) {
		try {
			prepareStatement = connection.prepareStatement(UPDATE_QUERY);
			
			prepareStatement.setInt(1, orderItem.getOrderId());
			prepareStatement.setInt(2, orderItem.getMenuId());
			prepareStatement.setInt(3, orderItem.getQuantity());
			prepareStatement.setDouble(4, orderItem.getItemTotal());
			prepareStatement.setInt(5, orderItem.getOrderItemId());
			
			int i = prepareStatement.executeUpdate();
			System.out.println(i);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void deleteOrderItem(int orderItemId) {

		try {
			prepareStatement = connection.prepareStatement(DELETE_QUERY);//DELETE FROM `orderItem` WHERE (`orderItemId`=?)"
			prepareStatement.setInt(1, orderItemId);
			int i = prepareStatement.executeUpdate();
			System.out.println(i);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public OrderItem getOrderItem(int orderItemId) {//`OrderItemID`, `OrderID`, `MenuID`, `Quantity`, `ItemTotal`

		try {
			prepareStatement = connection.prepareStatement(SELECT_QUERY);// "SELECT * FROM `menu` WHERE (`MenuID`=?)";
			prepareStatement.setInt(1, orderItemId);
			res = prepareStatement.executeQuery();
			while (res.next()) {
				return new OrderItem(res.getInt("OrderItemID"),res.getInt("OrderID"),res.getInt("MenuID"),res.getInt("Quantity"),
						res.getDouble("ItemTotal"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<OrderItem> getAllOrderItemsByOrder(int orderId) {
		
		List<OrderItem> list = new ArrayList<OrderItem>();

		try {

			prepareStatement = connection.prepareStatement(SELECTALL_QUERY);
			prepareStatement.setInt(1, orderId);
			
			res = prepareStatement.executeQuery();
			
			while (res.next()) {
				OrderItem u = new OrderItem(res.getInt("OrderItemID"),res.getInt("OrderID"),res.getInt("MenuID"),res.getInt("Quantity"),
						res.getDouble("ItemTotal"));
				list.add(u);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;

	}

}
