package by.epamjwd.mobile.dao.mapper;

import by.epamjwd.mobile.bean.Subscriber;
import by.epamjwd.mobile.bean.Bill;
import by.epamjwd.mobile.bean.Article;
import by.epamjwd.mobile.bean.Plan;
import by.epamjwd.mobile.bean.Service;
import by.epamjwd.mobile.bean.User;
import by.epamjwd.mobile.dao.mapper.impl.SubscriberRowMapper;
import by.epamjwd.mobile.dao.mapper.impl.BillRowMapper;
import by.epamjwd.mobile.dao.mapper.impl.NewsRowMapper;
import by.epamjwd.mobile.dao.mapper.impl.PlanRowMapper;
import by.epamjwd.mobile.dao.mapper.impl.ServiceRowMapper;
import by.epamjwd.mobile.dao.mapper.impl.UserRowMapper;

public class RowMapperFactory {

	private final RowMapper<Article> newsRowMapper = new NewsRowMapper();
	private final RowMapper<Plan> planRowMapper = new PlanRowMapper();
	private final RowMapper<Service> serviceRowMapper = new ServiceRowMapper();
	private final RowMapper<User> userRowMapper = new UserRowMapper();
	private final RowMapper<Subscriber> abonentRowMapper = new SubscriberRowMapper();
	private final RowMapper<Bill> billRowMapper = new BillRowMapper();
	
	
    public static RowMapperFactory getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        private static final RowMapperFactory INSTANCE = new RowMapperFactory();
    }

	public RowMapper<Article> getNewsRowMapper() {
		return newsRowMapper;
	}

	public RowMapper<Plan> getPlanRowMapper() {
		return planRowMapper;
	}

	public RowMapper<Service> getServiceRowMapper() {
		return serviceRowMapper;
	}

	public RowMapper<User> getUserRowMapper() {
		return userRowMapper;
	}
	
	public RowMapper<Subscriber> getSubscriberRowMapper() {
		return abonentRowMapper;
	}

	public RowMapper<Bill> getBillRowMapper() {
		return billRowMapper;
	}
	
}
