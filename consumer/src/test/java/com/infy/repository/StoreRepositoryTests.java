package com.infy.repository;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.infy.entity.Store;

@SpringBootTest
public class StoreRepositoryTests {

	@InjectMocks
	StoreRepository repo;
	@Mock
	//private StoreRepository repo;//=Mockito.mock(StoreRepository.class);
	private Store store;

	//@SuppressWarnings("deprecation")
	@Before
	public void requestInitalise() {
		//MockitoAnnotations.initMocks(this);
		store = new Store();
		store.setDescription("Jewelery Store");
		store.setOpeningHours(12);
		store.setRatingList(null);
		store.setStoreId("1001");
		store.setOverallRating(4.3);
		store.setTotalRating(5L);
		store.setStoreName("Tanisq");
	}

	@Test
	public void saveTest() throws Exception {

		Mockito.when(repo.save(store)).thenReturn(store);
		Store result = repo.save(store);
		Assertions.assertEquals(store.getStoreId(), result.getStoreId());
	}

	@Test
	public void findByStoreIdValid() throws Exception {
		repo.save(store);
		Mockito.when(repo.findByStoreId(store.getStoreId())).thenReturn(store);
		Store res = repo.findByStoreId(store.getStoreId());
		Assertions.assertEquals(res.getStoreName(), store.getStoreName());

	}

	@Test
	public void findByStoreIdInValid() throws Exception {
		Mockito.when(repo.findByStoreId(Mockito.anyString())).thenReturn(null);
		Store result = repo.findByStoreId("empty");
		Assertions.assertNull(result);

	}

	@Test
	public void deleteByStoreIdValid() throws Exception {
		Mockito.doNothing().when(repo).deleteById(Mockito.anyString());
		repo.deleteById(store.getStoreId());
		Mockito.verify(repo, Mockito.times(1)).deleteById(store.getStoreId());

	}
}
