package com.yash.tcvm.main;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.yash.tcvm.builder.BlackCoffeeBuilder;
import com.yash.tcvm.builder.BlackTeaBuilder;
import com.yash.tcvm.builder.CoffeeBuilder;
import com.yash.tcvm.builder.DrinkBuilder;
import com.yash.tcvm.builder.TeaBuilder;
import com.yash.tcvm.enums.Drink;
import com.yash.tcvm.enums.IngredientsWithMaxCapacity;
import com.yash.tcvm.exception.ContainerOverflowException;
import com.yash.tcvm.exception.ContainerUnderflowException;
import com.yash.tcvm.exception.EmptyException;
import com.yash.tcvm.model.Container;
import com.yash.tcvm.model.Order;
import com.yash.tcvm.service.ContainerService;
import com.yash.tcvm.service.OrderService;
import com.yash.tcvm.serviceimpl.ContainerServiceImpl;
import com.yash.tcvm.serviceimpl.OrderServiceImpl;
import com.yash.tcvm.util.FileUtil;

public class VendingMachine {
	private Logger logger = Logger.getLogger(VendingMachine.class);
	private Scanner scanOperatorInput = new Scanner(System.in);
	private ContainerService containerService;
	private OrderService orderService;
	private DrinkBuilder builder;

	public VendingMachine() {

		containerService = new ContainerServiceImpl();
		orderService = new OrderServiceImpl();
	}

	public static void main(String args[]) {

		VendingMachine machine = new VendingMachine();
		machine.takingInput();
	}

	public void displayMenu() {
		FileUtil fileUtil = new FileUtil();
		do {
			fileUtil.displayFile("src/main/resources/menu.txt");
			takingInput();
		} while (true);

	}

	private void takingInput() {
		int option = scanOperatorInput.nextInt();

		switch (option) {
		case 1:
			makeTeaOption();
			break;
		case 2:
			makeCoffeeOption();
			break;
		case 3:
			makeBlackTeaOption();
			break;
		case 4:
			makeBlackCoffeeOption();
			break;
		case 5:
			refillContainerOption();
			break;
		case 6:
			checkTotalSaleOption();
			break;
		case 7:
			containerStatusOption();
			break;
		case 8:
			resetContainerOption();
			break;
		case 9:
			System.exit(0);
			break;

		default:
			logger.error("Invalid Choice. Select a valid option please");
		}
	}

	private void resetContainerOption() {
		System.out.println("Do you want to Reset Containers : (Y to continue)");
		String choice = scanOperatorInput.next();
		if (choice.equalsIgnoreCase("y")) {
			containerService.resetAllContainers();
			System.out.println("Reset Successful");
			System.out.println();
		}

	}

	private void containerStatusOption() {
		List<Container> containers = containerService.getAllContainers();
		System.out.println("Ingredient\tMax Qantity\tAvailable Quantity");
		for (Container container : containers) {
			System.out.println(container.getIngredient()+"\t\t"+container.getMaxCapacity()+"\t\t"+container.getAvailableCapacity());
		}
		System.out.println();
	}

	private void checkTotalSaleOption() {
		double totalAmount=0.0;
		for (Drink drink : Drink.values()) {
			try {
				System.out.println("-------------------------------------------------");
				System.out.println("Total Sale For " + drink.toString() + " : ");
				System.out.println("Order Quantity\tOrder Cost");
				List<Order> orders = orderService.getOrdersByDrink(drink);
				double total = 0.0;
				for (Order order : orders) {
					System.out.println(order.getQuantity()+"\t\t"+order.getQuantity()*drink.getPrice());
					total = total + (order.getQuantity() * drink.getPrice());
				}
				totalAmount=totalAmount+total;
				System.out.println(" Total Tea Collection  "+total);
				System.out.println("------------------------------------------------");
			} catch (FileNotFoundException | EmptyException e) {
				System.out.println(e.getMessage());
				System.out.println("------------------------------------------------");
			}
		}
		System.out.println();
		System.out.println("Total Earning "+totalAmount);
	}

	private void refillContainerOption() {
		Container container = new Container();
		String conti = null;
		do {
			System.out.println(
					"1. Tea Container \n2. Coffee Continer \n3. Milk Container \n4. Sugar Container \n5. Watar Container\n");
			System.out.println("Select container option :");
			int option = scanOperatorInput.nextInt();
			switch (option) {
			case 1:
				refillTeaOption(container);
				break;
			case 2:
				refillCoffeeOption(container);
				break;
			case 3:
				refillMilkOption(container);
				break;
			case 4:
				refillSugarOption(container);
				break;
			case 5:
				refillWaterOption(container);
				break;
			default:
				logger.error("Invalid choice. Enter a Valid Option.");
			}
			System.out.println("Refill More Container (Y)");
			conti = scanOperatorInput.next();
		} while (conti.equalsIgnoreCase("Y"));
	}

	private void refillWaterOption(Container container) {
		System.out.println("Enter Quantity to refill Max" + IngredientsWithMaxCapacity.WATER.getMaxCapacity());
		double capacity = scanOperatorInput.nextDouble();
		container.setIngredient(IngredientsWithMaxCapacity.WATER);
		try {
			containerService.refillContainer(container, capacity);
		} catch (ContainerOverflowException | IllegalArgumentException e) {
			logger.error(e.getMessage());
		}
	}

	private void refillSugarOption(Container container) {
		System.out.println("Enter Quantity to refill Max" + IngredientsWithMaxCapacity.SUGAR.getMaxCapacity());
		double capacity = scanOperatorInput.nextDouble();
		container.setIngredient(IngredientsWithMaxCapacity.SUGAR);
		try {
			containerService.refillContainer(container, capacity);
		} catch (ContainerOverflowException | IllegalArgumentException e) {
			logger.error(e.getMessage());
		}
	}

	private void refillMilkOption(Container container) {
		System.out.println("Enter Quantity to refill Max" + IngredientsWithMaxCapacity.MILK.getMaxCapacity());
		double capacity = scanOperatorInput.nextDouble();
		container.setIngredient(IngredientsWithMaxCapacity.MILK);
		try {
			containerService.refillContainer(container, capacity);
		} catch (ContainerOverflowException | IllegalArgumentException e) {
			logger.error(e.getMessage());
		}
	}

	private void refillCoffeeOption(Container container) {
		System.out.println("Enter Quantity to refill Max" + IngredientsWithMaxCapacity.COFFEE.getMaxCapacity());
		double capacity = scanOperatorInput.nextDouble();
		container.setIngredient(IngredientsWithMaxCapacity.COFFEE);
		try {
			containerService.refillContainer(container, capacity);
		} catch (ContainerOverflowException | IllegalArgumentException e) {
			logger.error(e.getMessage());
		}
	}

	private void refillTeaOption(Container container) {
		System.out.println("Enter Quantity to refill Max" + IngredientsWithMaxCapacity.TEA.getMaxCapacity());
		double capacity = scanOperatorInput.nextDouble();
		container.setIngredient(IngredientsWithMaxCapacity.TEA);
		try {
			containerService.refillContainer(container, capacity);
		} catch (ContainerOverflowException | IllegalArgumentException e) {
			logger.error(e.getMessage());
		}
	}

	private void makeBlackCoffeeOption() {
		Order order = new Order();
		System.out.println("Enter Number of Cups you Want :");
		try {
			int quantity = scanOperatorInput.nextInt();
			if (quantity > 0) {
				order.setQuantity(quantity);
			} else {
				throw new IllegalArgumentException("Input can not be Negative Or Zero");
			}

		} catch (IllegalArgumentException exception) {
			logger.error(exception.getMessage());
			return;
		}
		order.setDrink(Drink.BLACK_COFFEE);
		System.out.println("Making Black Coffee...");
		builder = BlackCoffeeBuilder.getDrinkBuilder();
		prepareDrinkAndUpdateOrders(order);
	}

	private void makeBlackTeaOption() {
		Order order = new Order();
		System.out.println("Enter Number of Cups you Want :");
		try {
			int quantity = scanOperatorInput.nextInt();
			if (quantity > 0) {
				order.setQuantity(quantity);
			} else {
				throw new IllegalArgumentException("Input can not be Negative Or Zero");
			}

		} catch (IllegalArgumentException exception) {
			logger.error(exception.getMessage());
			return;
		}
		order.setDrink(Drink.BLACK_TEA);
		System.out.println("Making Black Tea...");
		builder = BlackTeaBuilder.getDrinkBuilder();
		prepareDrinkAndUpdateOrders(order);

	}

	private void makeCoffeeOption() {
		Order order = new Order();
		System.out.println("Enter Number of Cups you Want :");
		try {
			int quantity = scanOperatorInput.nextInt();
			if (quantity > 0) {
				order.setQuantity(quantity);
			} else {
				throw new IllegalArgumentException("Input can not be Negative Or Zero");
			}

		} catch (IllegalArgumentException exception) {
			logger.error(exception.getMessage());
			return;
		}
		order.setDrink(Drink.COFFEE);
		System.out.println("Making Coffee...");
		builder = CoffeeBuilder.getDrinkBuilder();
		prepareDrinkAndUpdateOrders(order);

	}

	private void makeTeaOption() {
		Order order = new Order();
		System.out.println("Enter Number of Cups you Want :");
		try {
			int quantity = scanOperatorInput.nextInt();
			if (quantity > 0) {
				order.setQuantity(quantity);
			} else {
				throw new IllegalArgumentException("Input can not be Negative Or Zero");
			}

		} catch (IllegalArgumentException exception) {
			logger.error(exception.getMessage());
			return;
		}
		order.setDrink(Drink.TEA);
		System.out.println("Making Tea...");
		builder = TeaBuilder.getDrinkBuilder();
		prepareDrinkAndUpdateOrders(order);

	}

	private void prepareDrinkAndUpdateOrders(Order order) {
		try {
			builder.prepareDrink(order);
		} catch (ContainerUnderflowException exception) {
			logger.error(exception.getMessage());
		}

		try {
			orderService.addOrder(order);
		} catch (FileNotFoundException | EmptyException e) {
			logger.error(e.getMessage());
		}
	}
}
