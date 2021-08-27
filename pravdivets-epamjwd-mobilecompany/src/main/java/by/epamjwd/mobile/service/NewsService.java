package by.epamjwd.mobile.service;

import java.util.List;

import by.epamjwd.mobile.bean.NewsArticle;

public interface NewsService {

	public List<NewsArticle> getAllNews();
	
	public List<NewsArticle> getLastThreeArticles();
	//НАДО ли 3? лучше по количеству статей
	
	public NewsArticle getArticleByID(int id);
	
	
}
