package com.yash.tcvm.serviceimpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.yash.tcvm.dao.ContainerDAO;
import com.yash.tcvm.enums.IngredientsWithMaxCapacity;
import com.yash.tcvm.exception.ContainerOverflowException;
import com.yash.tcvm.exception.EmptyException;
import com.yash.tcvm.model.Container;
import com.yash.tcvm.service.ContainerService;

public class ContainerServiceImplTest {

	@Mock
	private ContainerDAO containerDAO;

	@InjectMocks
	private ContainerService containerService = new ContainerServiceImpl();

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getContainerByIngredient_WhenContainerIsEmpty_ShouldReturnNullObject() throws FileNotFoundException, EmptyException {
		Container container = null;
		when(containerDAO.getContainers()).thenReturn(Arrays.asList(new Container()));
		assertEquals(container, containerService.getContainerByIngredient(IngredientsWithMaxCapacity.COFFEE));
	}

	@Test
	public void getContainerByIngredient_WhenIngredientIsPassed_ShouldReturnIngredientContainer() throws FileNotFoundException, EmptyException {
		Container container = new Container(IngredientsWithMaxCapacity.COFFEE,
				IngredientsWithMaxCapacity.COFFEE.getMaxCapacity(), IngredientsWithMaxCapacity.COFFEE.getMaxCapacity());
		when(containerDAO.getContainers()).thenReturn(Arrays.asList(container));
		assertEquals(container, containerService.getContainerByIngredient(IngredientsWithMaxCapacity.COFFEE));
	}

	@Test
	public void getAllContainers_ShouldReturnListOfContainers() throws FileNotFoundException, EmptyException {
		when(containerDAO.getContainers()).thenReturn(Arrays.asList(new Container()));
		assertEquals(1, containerService.getAllContainers().size());
	}

	@Test
	public void updateAllContainers_WhenListOfContainersIsPassed_ShouldReturnTrueWhenUpdated() throws EmptyException {
		List<Container> containers = Arrays.asList(new Container());
		when(containerDAO.updateContainers(containers)).thenReturn(true);
		assertTrue(containerService.updateAllContainers(containers));
	}

	@Test
	public void updateAllContainers_WhenListOfContainersIsPassed_ShouldReturnFalseWhenNotUpdated() throws EmptyException {
		List<Container> containers = Arrays.asList(new Container());
		when(containerDAO.updateContainers(containers)).thenReturn(false);
		assertFalse(containerService.updateAllContainers(containers));
	}

	@Test(expected = ContainerOverflowException.class)
	public void refillContainer_WhenGivenContainerIsAlreadyFull_ThrowContainerOverflowException()
			throws ContainerOverflowException, FileNotFoundException, EmptyException {
		Container container = new Container(IngredientsWithMaxCapacity.COFFEE,
				IngredientsWithMaxCapacity.COFFEE.getMaxCapacity(), 2000.0);
		when(containerDAO.getContainers()).thenReturn(Arrays.asList(container));
		containerService.refillContainer(new Container(IngredientsWithMaxCapacity.COFFEE,
				IngredientsWithMaxCapacity.COFFEE.getMaxCapacity(), IngredientsWithMaxCapacity.COFFEE.getMaxCapacity()),
				2000.0);

	}

	@Test(expected = ContainerOverflowException.class)
	public void refillContainer_WhenGivenQuantityIsMoreThanMaxContainerCapacity_ThrowContainerOverflowException()
			throws ContainerOverflowException, FileNotFoundException, EmptyException {
		Container container = new Container(IngredientsWithMaxCapacity.COFFEE,
				IngredientsWithMaxCapacity.COFFEE.getMaxCapacity(), 1000.0);
		when(containerDAO.getContainers()).thenReturn(Arrays.asList(container));
		containerService.refillContainer(new Container(IngredientsWithMaxCapacity.COFFEE, 0.0, 1000.0), 1500.0);

	}

	@Test(expected = IllegalArgumentException.class)
	public void refillContainer_WhenGivenQuantityIsNegative_ThrowIllegalArgumentException()
			throws ContainerOverflowException, FileNotFoundException, EmptyException {
		Container container = new Container(IngredientsWithMaxCapacity.COFFEE,
				IngredientsWithMaxCapacity.COFFEE.getMaxCapacity(), 1000.0);
		when(containerDAO.getContainers()).thenReturn(Arrays.asList(container));
		containerService.refillContainer(new Container(IngredientsWithMaxCapacity.COFFEE, 0.0, 1000.0), -1000.0);

	}

	@Test(expected = IllegalArgumentException.class)
	public void refillContainer_WhenGivenQuantityIsZero_ThrowIllegalArgumentException()
			throws ContainerOverflowException, FileNotFoundException, EmptyException {
		Container container = new Container(IngredientsWithMaxCapacity.COFFEE,
				IngredientsWithMaxCapacity.COFFEE.getMaxCapacity(), 1000.0);
		when(containerDAO.getContainers()).thenReturn(Arrays.asList(container));
		containerService.refillContainer(new Container(IngredientsWithMaxCapacity.COFFEE, 0.0, 1000.0), 0.0);

	}

	@Test
	public void refillContainer_WhenGivenQuantityValid_ShouldReturnTrue() throws ContainerOverflowException, FileNotFoundException, EmptyException {
		Container container = new Container(IngredientsWithMaxCapacity.COFFEE,
				IngredientsWithMaxCapacity.COFFEE.getMaxCapacity(), 1000.0);
		when(containerDAO.getContainers()).thenReturn(Arrays.asList(container));
		assertTrue(containerService.refillContainer(new Container(IngredientsWithMaxCapacity.COFFEE, 0.0, 1000.0),
				1000.0));

	}

}
