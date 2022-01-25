package com.infy.repository;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import com.infy.entity.RatingRequest;

@SpringBootTest
public class RatingRepositoryTests {
	
	private RatingRepository repo=Mockito.mock(RatingRepository.class);
	private RatingRequest request;

	//@SuppressWarnings("deprecation")
	@Before
	public void requestInitalise() {
		//MockitoAnnotations.initMocks(this);
		request = new RatingRequest();
		request.setName("Tommy");
		request.setRating(5);
		request.setUserId("151");
	}

	@Test
	public void saveTest() throws Exception {

		Mockito.when(repo.save(request)).thenReturn(request);
		RatingRequest result = repo.save(request);
		Assertions.assertEquals(result.getUserId(), request.getUserId());
	}

	@Test
	public void findByUserIdValid() throws Exception {
		repo.save(request);
		Mockito.when(repo.findByUserId(request.getUserId())).thenReturn(request);
		RatingRequest res = repo.findByUserId(request.getUserId());
		Assertions.assertEquals(res.getName(), request.getName());

	}

	@Test
	public void findByUserIdInValid() throws Exception {
		Mockito.when(repo.findByUserId(Mockito.anyString())).thenReturn(null);
		RatingRequest result = repo.findByUserId("empty");
		Assertions.assertNull(result);

	}

	@Test
	public void deleteByUserIdValid() throws Exception {
		Mockito.doNothing().when(repo).deleteByUserId(Mockito.anyString());
		repo.deleteByUserId(request.getUserId());
		Mockito.verify(repo, Mockito.times(1)).deleteByUserId(request.getUserId());

	}

}
