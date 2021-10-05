package by.epamjwd.mobile.dao.mapper;

import by.epamjwd.mobile.bean.Abonent;
import by.epamjwd.mobile.bean.NewsArticle;
import by.epamjwd.mobile.bean.Plan;
import by.epamjwd.mobile.bean.Service;
import by.epamjwd.mobile.bean.User;
import by.epamjwd.mobile.dao.mapper.impl.AbonentRowMapper;
import by.epamjwd.mobile.dao.mapper.impl.NewsRowMapper;
import by.epamjwd.mobile.dao.mapper.impl.PlanRowMapper;
import by.epamjwd.mobile.dao.mapper.impl.ServiceRowMapper;
import by.epamjwd.mobile.dao.mapper.impl.UserRowMapper;

public class RowMapperFactory {

	private final RowMapper<NewsArticle> newsRowMapper = new NewsRowMapper();
	private final RowMapper<Plan> planRowMapper = new PlanRowMapper();
	private final RowMapper<Service> serviceRowMapper = new ServiceRowMapper();
	private final RowMapper<User> userRowMapper = new UserRowMapper();
	private final RowMapper<Abonent> abonentRowMapper = new AbonentRowMapper();
	
	
    public static RowMapperFactory getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        private static final RowMapperFactory INSTANCE = new RowMapperFactory();
    }

	public RowMapper<NewsArticle> getNewsRowMapper() {
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
	public RowMapper<Abonent> getAbonentRowMapper() {
		return abonentRowMapper;
	}
    
}
