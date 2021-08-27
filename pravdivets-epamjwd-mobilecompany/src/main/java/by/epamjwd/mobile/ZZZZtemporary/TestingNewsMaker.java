package by.epamjwd.mobile.ZZZZtemporary;

import by.epamjwd.mobile.bean.NewsArticle;
import by.epamjwd.mobile.dao.impl.SQLNewsDAO;

//import java.util.List;
//
//import by.epamtc.mobile.entity.NewsArticle;
//import by.epamtc.mobile.service.NewsMaker;

public class TestingNewsMaker {

	public static final String SELECT_FROM_NEWS = "SELECT * FROM NEWS";

	public static void main(String[] args) {
		//NewsMaker newsmaker = new NewsMaker();
		//List<NewsArticle> newsArticles = newsmaker.selectNewsSetFromDatabase(SELECT_FROM_NEWS);
		//System.out.println(newsArticles.get(newsArticles.size()-1));
//		System.out.println();
//		System.out.println(newsArticles.get(newsArticles.size()-2));
//		System.out.println();
//		System.out.println(newsArticles.get(newsArticles.size()-3));
		
		SQLNewsDAO dao = new SQLNewsDAO();
		NewsArticle article = dao.getArticleByID(10);
		
		System.out.println(article);
		
	}

}
