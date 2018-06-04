package com.yash.tcvm.builder;

import java.util.List;
import java.util.Map;

import com.yash.tcvm.configurer.AbstractDrinkConfigurer;
import com.yash.tcvm.configurer.DrinkConfigurer;
import com.yash.tcvm.enums.IngredientsWithMaxCapacity;
import com.yash.tcvm.exception.ContainerUnderflowException;
import com.yash.tcvm.model.Container;
import com.yash.tcvm.model.Order;
import com.yash.tcvm.service.ContainerService;
import com.yash.tcvm.serviceimpl.ContainerServiceImpl;

public abstract class AbstractDrinkBuilder implements DrinkBuilder {

	DrinkConfigurer drinkConfigurer;
	ContainerService containerService = new ContainerServiceImpl();

	public void setDrinkConfigurer(DrinkConfigurer drinkConfigurer) {
		this.drinkConfigurer = drinkConfigurer;
	}

	public void setContainerService(ContainerServiceImpl containerServiceImpl) {
		this.containerService = containerServiceImpl;
	}

	public void prepareDrink(Order order) throws ContainerUnderflowException {
		checkUnderFlow(order);
		updateContainers(order);
		order.setStatus(true);
	}

	private void checkUnderFlow(Order order) throws ContainerUnderflowException{
		AbstractDrinkConfigurer abstractDrinkConfigurer = (AbstractDrinkConfigurer) drinkConfigurer;
		Map<IngredientsWithMaxCapacity, Double> consumption = abstractDrinkConfigurer.getIngredientConsumption();
		Map<IngredientsWithMaxCapacity, Double> wastage = abstractDrinkConfigurer.getIngredientWastage();
		for (Map.Entry<IngredientsWithMaxCapacity, Double> entry : consumption.entrySet()) {
			double qtyWasted = wastage.get(entry.getKey());
			double qtyConsumed = entry.getValue();
			double qtyAvailableInContainer = containerService.getContainerByIngredient(entry.getKey())
					.getAvailableCapacity();
			int noOfCups = order.getQuantity();
			checkForUnderFlowCondition(entry, qtyWasted, qtyConsumed, qtyAvailableInContainer, noOfCups);
		}
	}

	private void checkForUnderFlowCondition(Map.Entry<IngredientsWithMaxCapacity, Double> entry, double qtyWasted,
			double qtyConsumed, double qtyAvailableInContainer, int noOfCups) throws ContainerUnderflowException {
		if (isUnderFlowCondition(qtyWasted, qtyConsumed, qtyAvailableInContainer, noOfCups)) {
			throw new ContainerUnderflowException(entry.getKey() + "Insufficient");
		}
	}

	private boolean isUnderFlowCondition(double qtyWasted, double qtyConsumed, double qtyAvailableInContainer,
			int noOfCups) {
		return (noOfCups * (qtyConsumed + qtyWasted)) > qtyAvailableInContainer;
	}

	public void updateContainers(Order order) {

		List<Container> containers = containerService.getAllContainers();
		AbstractDrinkConfigurer abstractDrinkConfigurer = (AbstractDrinkConfigurer) drinkConfigurer;
		Map<IngredientsWithMaxCapacity, Double> consumption = abstractDrinkConfigurer.getIngredientConsumption();
		Map<IngredientsWithMaxCapacity, Double> wastage = abstractDrinkConfigurer.getIngredientWastage();
		for (Map.Entry<IngredientsWithMaxCapacity, Double> entry : consumption.entrySet()) {
			for (Container container : containers) {
				if (container.getIngredient().toString().equalsIgnoreCase(entry.getKey().toString())) {
					double qtyWasted = wastage.get(entry.getKey());
					double qtyConsumed = entry.getValue();
					double qtyAvailableInContainer = container.getAvailableCapacity();
					int noOfCups = order.getQuantity();
					container.setAvailableCapacity(qtyAvailableInContainer - (noOfCups * (qtyConsumed + qtyWasted)));
					break;
				}
			}
		}
		if(containerService.updateAllContainers(containers)){
			System.out.println("Your "+order.getDrink().toString()+" is Ready" );
			System.out.println("Total Amount = "+(order.getQuantity()*abstractDrinkConfigurer.getDrinkRate()));
		}
	}

}
