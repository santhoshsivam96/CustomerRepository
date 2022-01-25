package com.infy.strngconstant;

public class APIPaths {
	public static final String HOME_PATH="/userAPI";
	public static final String GET_RATING=HOME_PATH+"/rating/{userId}";
	public static final String ALL_STORES=HOME_PATH+"/allstores";
	public static final String ALL_USERS=HOME_PATH+"/allusers";
	public static final String PUT_RATING=HOME_PATH+"/{userId}/{rating}/{storeId}";
	public static final String RATE_STORE1=HOME_PATH+"/ratestore/{storeId}";
	public static final String RATE_STORE2=HOME_PATH+"/{storeId}";
	public static final String ADD_STORE=HOME_PATH+"/storedetails";
}
