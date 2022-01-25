package com.infy.repository;

import org.springframework.data.repository.CrudRepository;

import com.infy.entity.Store;

public interface StoreRepository extends CrudRepository<Store,String> {

	public Store findByStoreId(String storeId);


}
