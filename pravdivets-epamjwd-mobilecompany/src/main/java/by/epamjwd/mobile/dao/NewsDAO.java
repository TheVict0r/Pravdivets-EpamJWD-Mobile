package by.epamjwd.mobile.dao;

import java.util.List;

import by.epamjwd.mobile.bean.NewsArticle;

public interface NewsDAO {

	public List<NewsArticle> getAllNews();
	
	public NewsArticle getArticleByID(int id);

	
}
