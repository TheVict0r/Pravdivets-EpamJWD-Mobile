package by.epamjwd.mobile.service.impl.articleserviceimpl;

import org.junit.Assert;
import org.junit.Test;

import by.epamjwd.mobile.service.ArticleService;
import by.epamjwd.mobile.service.ServiceProvider;

public class IsPreviousIndexZeroTest {
	private final int index1 = 10;
	private final int step1 = 10;
	private final int index2 = 11;
	private final int step2 = 12;
	private final int index3 = 8;
	private final int step3 = 7;
	
	
	private final ArticleService articleService = ServiceProvider.getInstance().getArticleService();
	
	@Test
	public void index1Test() {
		Assert.assertTrue(articleService.isPreviousIndexZero(index1, step1));
	}
	
	@Test
	public void index2Test() {
		Assert.assertFalse(articleService.isPreviousIndexZero(index2, step2));
	}
	
	@Test
	public void index3Test() {
		Assert.assertFalse(articleService.isPreviousIndexZero(index3, step3));
	}
	
}
