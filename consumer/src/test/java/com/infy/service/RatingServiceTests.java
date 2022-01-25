package com.infy.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.infy.entity.RatingRequest;
import com.infy.entity.Store;
import com.infy.exception.StoreException;
import com.infy.exception.UserException;
import com.infy.repository.RatingRepository;
import com.infy.repository.StoreRepository;

@SpringBootTest
public class RatingServiceTests {
	@Mock
	private RatingRepository repo = Mockito.mock(RatingRepository.class);
	@Mock
	private StoreRepository storeRepo = Mockito.mock(StoreRepository.class);
	@Mock
	StoreServiceImpl ssi;
	@InjectMocks
	private RatingServiceImpl service = new RatingServiceImpl();
	@InjectMocks
	private StoreServiceImpl storeService = new StoreServiceImpl();
	private Store store;
	private RatingRequest request;

//@SuppressWarnings("deprecation")
	@Before
	public void setUp() {
		// MockitoAnnotations.initMocks(this);
		request = new RatingRequest();
		request.setName("Thomas");
		request.setRating(4);
		request.setUserId("121");
		store = new Store();
		store.setDescription("Jewelery Store");
		store.setOpeningHours(12);
		List<RatingRequest> list = new ArrayList<>();
		list.add(request);
		store.setRatingList(list);
		store.setStoreId("1001");
		store.setOverallRating(4.3);
		store.setTotalRating(5L);
		store.setStoreName("Tanisq");
		repo.save(request);
		storeRepo.save(store);

	}

	@Test
	public void modifyRatingValid() throws UserException, StoreException {
		Mockito.when(repo.findByUserId(Mockito.anyString())).thenReturn(request);
		Mockito.when(storeRepo.findByStoreId(Mockito.anyString())).thenReturn(store);
		ssi = Mockito.spy(new StoreServiceImpl());
		Mockito.when(ssi.updateRatingToStore(Mockito.any(), Mockito.anyString(), Mockito.anyString()))
			.thenReturn(store.getStoreId());
		String result = service.modifyRating(request.getUserId(), request.getRating(), store.getStoreId());
		Assertions.assertEquals(store.getStoreId(), result);
	}

}
