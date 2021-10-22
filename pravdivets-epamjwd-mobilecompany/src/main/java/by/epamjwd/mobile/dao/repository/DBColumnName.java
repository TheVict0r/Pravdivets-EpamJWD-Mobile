package by.epamjwd.mobile.dao.repository;

public class DBColumnName {

    public static final String ROLES_ID = "id";
    public static final String ROLES_ROLE = "role";
	  
    public static final String STATUSES_ID = "id";
    public static final String STATUSES_STATUS = "status";
	  
    public static final String PLANS_ID = "id";
    public static final String PLANS_NAME = "name";
    public static final String PLANS_REGULAR_PAYMENT = "regular_payment";
    public static final String PLANS_UPFRONT_PAYMENT = "upfront_payment";
    public static final String PLANS_WITHIN_NETWORK = "within_network"; 
    public static final String PLANS_OTHER_NETWORKS = "other_networks"; 
    public static final String PLANS_ABROAD = "abroad"; 
    public static final String PLANS_VIDEOCALL = "videocall"; 
    public static final String PLANS_SMS = "sms"; 
    public static final String PLANS_MMS = "mms"; 
    public static final String PLANS_INTERNET = "internet";
    public static final String PLANS_DESCRIPTION = "description";

    
    public static final String USERS_ID = "id";
    public static final String USERS_PASSWORD = "password";
    public static final String USERS_FIRST_NAME = "first_name";
    public static final String USERS_MIDDLE_NAME = "middle_name";
    public static final String USERS_LAST_NAME = "last_name";
    public static final String USERS_PASSPORT = "passport";
    public static final String USERS_EMAIL = "email";
    public static final String USERS_ROLE_ID = "role_id";
	  
    public static final String SUBSCRIBERS_ID = "id";
    public static final String SUBSCRIBERS_CONTRACT_DATE = "contract_date";
    public static final String SUBSCRIBERS_ACCOUNT = "account";
    public static final String SUBSCRIBERS_PHONE = "phone";
    public static final String SUBSCRIBERS_STATUS_ID = "status_id";
    public static final String SUBSCRIBERS_STATUS_DATE = "status_date";
    public static final String SUBSCRIBERS_PLAN_ID = "plan_id";
    public static final String SUBSCRIBERS_USER_ID = "user_id";
	  
    public static final String SERVICES_ID = "id";
    public static final String SERVICES_NAME = "name";
    public static final String SERVICES_TARIF = "tarif";
    public static final String SERVICES_DESCRIPTION = "description";
	  
    public static final String BILLS_ID = "id";
    public static final String BILLS_SUBSCRIBER_ID = "subscriber_id";
    public static final String BILLS_DATE = "date";
    public static final String BILLS_AMOUNT = "amount";
    public static final String BILLS_PAYED = "payed";
	  
    public static final String NEWS_ID = "id";
    public static final String NEWS_DATE = "date";
    public static final String NEWS_TITLE = "title";
    public static final String NEWS_LEAD = "lead";
    public static final String NEWS_TEXT = "text";
	
}
