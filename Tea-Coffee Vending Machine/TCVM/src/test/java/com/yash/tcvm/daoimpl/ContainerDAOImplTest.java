package com.yash.tcvm.daoimpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.yash.tcvm.dao.ContainerDAO;
import com.yash.tcvm.enums.IngredientsWithMaxCapacity;
import com.yash.tcvm.exception.EmptyException;
import com.yash.tcvm.model.Container;
import com.yash.tcvm.util.JSONUtil;

public class ContainerDAOImplTest {

	@Mock
	private JSONUtil jsonUtil;

	@InjectMocks
	private ContainerDAO containerDAO = new ContainerDAOImpl();

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void insert_WhenListOfContainerIsGiven_ShouldInsertAndReturnTrue() throws EmptyException {
		List<Container> containers=new ArrayList<>();
		
		for (IngredientsWithMaxCapacity ingredient : IngredientsWithMaxCapacity.values()) {
			containers.add(new Container(ingredient, ingredient.getMaxCapacity(), ingredient.getMaxCapacity()));
		}
		
		when(jsonUtil.writeObjectInJSONFile(containers)).thenReturn(true);
		assertTrue(containerDAO.insert(containers));
	}
	
	@Test
	public void insert_WhenListOfContainerIsGiven_ShouldNotInsertAndReturnFalse() throws EmptyException {
		List<Container> containers=new ArrayList<>();
		
		for (IngredientsWithMaxCapacity ingredient : IngredientsWithMaxCapacity.values()) {
			containers.add(new Container(ingredient, ingredient.getMaxCapacity(), ingredient.getMaxCapacity()));
		}
		
		when(jsonUtil.writeObjectInJSONFile(containers)).thenReturn(false);
		assertFalse(containerDAO.insert(containers));
	}

	@Test
	public void getContainer_ShouldReturnListOfContainers() throws FileNotFoundException, EmptyException {
		when(jsonUtil.readObjectFromJSONFile()).thenReturn(Arrays.asList(new Container()));
		assertEquals(1, containerDAO.getContainers().size());
	}

	@Test
	public void getContainer_WhenJSONFileIsEmpty_ShouldReturnListOfContainersWithMaxCapacity() throws FileNotFoundException, EmptyException {
		List<Container> containers = Arrays.asList(new Container(), new Container(), new Container(), new Container(),
				new Container());
		when(jsonUtil.readObjectFromJSONFile()).thenReturn(null, containers);
		assertEquals(5, containerDAO.getContainers().size());
	}

	@Test
	public void updateContainers_WhenListOfContainersIsPassed_ShouldReturnTrueWhenUpdated() {
		List<Container> containers = Arrays.asList(new Container());
		when(jsonUtil.writeObjectInJSONFile(containers)).thenReturn(true);
		assertTrue(jsonUtil.writeObjectInJSONFile(containers));
	}

	@Test
	public void updateContainers_WhenListOfContainersIsPassed_ShouldReturnFalseWhenNotUpdated() {
		List<Container> containers = Arrays.asList(new Container());
		when(jsonUtil.writeObjectInJSONFile(containers)).thenReturn(false);
		assertFalse(jsonUtil.writeObjectInJSONFile(containers));
	}
}
