package com.yash.tcvm.daoimpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.yash.tcvm.dao.OrderDAO;
import com.yash.tcvm.enums.Drink;
import com.yash.tcvm.exception.EmptyException;
import com.yash.tcvm.model.Order;

public class OrderDaoImplTest {

	private OrderDAO orderDao;

	@Before
	public void init() {
		orderDao = new OrderDaoImpl();
	}

	@Test(expected = FileNotFoundException.class)
	public void getOrders_ShouldThrowException_WhenJSONFileForOrderNotFoundInProvidedPath()
			throws FileNotFoundException, EmptyException {
		orderDao.getOrders();
	}

	@Test(expected = EmptyException.class)
	public void getOrders_ShouldThrowException_WhenJSONFileForOrderIsEmpty()
			throws FileNotFoundException, EmptyException {
		orderDao.getOrders();

	}

	@Test
	public void insertOrder_ShouldReturnOne_WhenOrderObjectIsGiven() throws EmptyException, FileNotFoundException {
		Order order = new Order(5, Drink.COFFEE, true);
		assertEquals(1, orderDao.insertOrder(order));
	}

	@Test
	public void getOrders_shouldReturnSizeOfOrdersList_WhenListOfOrderObjectAreInJSONFile()
			throws FileNotFoundException, EmptyException {
		assertEquals(2, orderDao.getOrders().size());
	}

	@Test(expected = NullPointerException.class)
	public void insertOrder_shouldThrowException_WhenOrderObjectGivenIsNull()
			throws FileNotFoundException, EmptyException {
		Order order = null;
		assertEquals(1, orderDao.insertOrder(order));
	}

	@Test
	public void getOrdersByDrink_ShouldReturnSizeOfOrderList_WhenDrinkTypeIsGivenAndJSONFileHasOrdersOfGivenDrinkType()
			throws FileNotFoundException {
		assertEquals(1, orderDao.getOrdersByDrink(Drink.TEA).size());
	}

	@Test
	public void getOrdersByDrink_ShouldReturnSizeOfOrderListAsZero_WhenDrinkTypeIsGivenAndJSONFileDoesntHasOrdersOfGivenDrinkType()
			throws FileNotFoundException {
		assertEquals(0, orderDao.getOrdersByDrink(Drink.BLACK_COFFEE).size());
	}
}