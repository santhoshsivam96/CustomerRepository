package com.infy.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infy.dto.RatingRequestDTO;
import com.infy.dto.StoreDTO;
import com.infy.entity.RatingRequest;
import com.infy.entity.Store;
import com.infy.exception.StoreException;
import com.infy.repository.StoreRepository;

@Service(value = "storeService")
public class StoreServiceImpl implements StoreService {
	//@Autowired
	private StoreRepository repository=new StoreRepository();

	@Override
	public String addStore(StoreDTO store) throws StoreException {
		Optional<Store> opt = repository.findById(store.getStoreId());
		if (opt.isPresent()) {
			throw new StoreException("Service.STORE_ALREADY_EXISTS");
		}
		Store newStore = new Store();
		newStore.setDescription(store.getDescription());
		newStore.setOpeningHours(store.getOpeningHours());
		newStore.setStoreName(store.getStoreName());
		newStore.setOverallRating(store.getOverallRating());
		newStore.setTotalRating(store.getTotalRating());
		List<RatingRequest> lists = new ArrayList<>();
		for (RatingRequestDTO out : store.getRatingList()) {
			RatingRequest entity = new RatingRequest();
			entity.setName(out.getName());
			entity.setRating(out.getRating());
			entity.setName(out.getName());
			lists.add(entity);
		}
		newStore.setRatingList(lists);
		Store result = repository.save(newStore);
		return result.getStoreId();
	}

	@Override
	public List<StoreDTO> findAllStores() throws StoreException {
		List<Store> lists = (List<Store>) repository.findAll();
		if (lists.isEmpty()) {
			throw new StoreException("Service.DB_EMPTY");
		}
		List<StoreDTO> returnList = new ArrayList<StoreDTO>();
		for (Store s : lists) {
			StoreDTO store = new StoreDTO();
			store.setDescription(s.getDescription());
			store.setOpeningHours(s.getOpeningHours());
			store.setStoreName(s.getStoreName());
			store.setOverallRating(s.getOverallRating());
			store.setStoreId(s.getStoreId());
			store.setTotalRating(s.getTotalRating());
			List<RatingRequestDTO> list = new ArrayList<>();
			for (RatingRequest out : s.getRatingList()) {
				RatingRequestDTO dto = new RatingRequestDTO();
				dto.setName(out.getName());
				dto.setRating(out.getRating());
				dto.setUserId(out.getUserId());
				list.add(dto);
			}
			store.setRatingList(list);
			returnList.add(store);
		}

		return returnList;
	}

	@Override
	public String updateRatingToStore(RatingRequest request, String userId, String storeId) throws StoreException {
		Store store = repository.findByStoreId(storeId);
		if(store==null) throw new StoreException("Service.STORE_DOESN'T_EXISTS");
		List<RatingRequest> list = store.getRatingList();
		Double value = 0.0;
		RatingRequest returned = null;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getUserId().equals(userId)) {
				returned = list.get(i);
				break;
			}
		}
		if (returned == null) {
			throw new StoreException("Service.NO_RATED_USER_FOUND");
		} else {
			value = reduceRating(list,returned.getRating());
			list.remove(returned);
		}
		store.setOverallRating(addRating(list,request.getRating()));
		list.add(request);
		store.setRatingList(list);
		Store result = repository.save(store);
		return result.getStoreId();
	}

	public RatingRequest addNewUserToStore(RatingRequestDTO rate, String storeId) throws StoreException {
		Store result = repository.findByStoreId(storeId);
		if (result == null) {
			throw new StoreException("Service.STORE_DOESN'T_EXISTS");
		}
		List<RatingRequest> lists = result.getRatingList();
		for (RatingRequest out : lists) {
			if (out.getUserId().equals(rate.getUserId())) {
				throw new StoreException("Service.USER_ID_IS_ALREADY_EXISTS");
			}
		}
		RatingRequest request = new RatingRequest();
		request.setName(rate.getName());
		request.setRating(rate.getRating());
		request.setUserId(rate.getUserId());
		lists.add(request);
		result.setRatingList(lists);
		result.setTotalRating(result.getTotalRating() + 1);
		result.setOverallRating(addRating(lists,request.getRating()));
		repository.save(result);
		return request;
	}

	public List<StoreDTO> findAllRatedStoresWithUserID(String userId) throws StoreException {
		List<StoreDTO> output = new ArrayList<>();
		List<StoreDTO> storeList = findAllStores();
		for (StoreDTO out : storeList) {
			StoreDTO temp = out;
			for (RatingRequestDTO request : out.getRatingList()) {
				if (request.getUserId().equals(userId)) {
					temp.setRatingList(null);
					output.add(temp);
				}
			}
		}
		if (output.isEmpty()) {
			throw new StoreException("Service.USER_NOT_FOUND");
		}

		return output;
	}

	public Map<Integer, Integer> mapRating(List<RatingRequest> list) {
		Map<Integer, Integer> map = new HashMap<>();
		map.put(1, 0);
		map.put(2, 0);
		map.put(3, 0);
		map.put(4, 0);
		map.put(5, 0);
		for (int i = 0; i < list.size(); i++) {
			Integer res = list.get(i).getRating();
			map.put(res, map.get(res) + 1);
		}
		return map;
	}
	public Double reduceRating(List<RatingRequest> list, Integer rating) {
			Map<Integer, Integer> map = mapRating(list);
		Integer total = map.get(rating);
		if (total - 1 != -1) {
			map.put(rating, total - 1);
		}
		Double nom = 0.0;
		Double denom = 0.0;
		for (Integer out : map.keySet()) {
			nom = nom + (out * map.get(out));
			denom = denom + map.get(out);
		}
		return Math.round((nom / denom)*100.0)/100.0;
	}

	public double addRating(List<RatingRequest> list, Integer rating) {
		Map<Integer, Integer> map = mapRating(list);
		map.put(rating, map.get(rating)+1);
		Double nom = 0.0;
		Double denom = 0.0;
		for (Integer out : map.keySet()) {
			nom = nom + (out * map.get(out));
			denom = denom + map.get(out);
		}
		return Math.round((nom / denom)*100.0)/100.0;
	}

}
