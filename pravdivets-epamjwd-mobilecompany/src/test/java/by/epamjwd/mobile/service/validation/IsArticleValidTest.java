package by.epamjwd.mobile.service.validation;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import by.epamjwd.mobile.bean.Article;

public class IsArticleValidTest {

	private final Article goodArticle1 = new Article(0, new Date(), "Заголовок", "Вводная часть", "Текст текст текст");
	private final Article badArticle1 = new Article(-1, new Date(), "Заголовок", "Вводная часть", "Текст текст текст");
	private final Article badArticle2 = new Article(0, null, "Заголовок", "Вводная часть", "Текст текст текст");
	private final Article badArticle3 = new Article(0, new Date(), null, "Вводная часть", "Текст текст текст");
	private final Article badArticle4 = new Article(0, new Date(), " ", "Вводная часть", "Текст текст текст");
	private final Article badArticle5 = new Article(0, new Date(), "Заголовок", null, "Текст текст текст");
	private final Article badArticle6 = new Article(0, new Date(), "Заголовок", " ", "Текст текст текст");
	private final Article badArticle7 = new Article(0, new Date(), "Заголовок", "Вводная часть", null);
	private final Article badArticle8 = new Article(0, new Date(), "Заголовок", "Вводная часть", " ");
	private final Article badArticle9 = new Article();
	
	
	@Test
	public void goodArticle1Test() {
		Assert.assertTrue(InputDataValidator.isArticleValid(goodArticle1));
	}
	
	@Test
	public void badArticle1Test() {
		Assert.assertFalse(InputDataValidator.isArticleValid(badArticle1));
	}
	
	@Test
	public void badArticle2Test() {
		Assert.assertFalse(InputDataValidator.isArticleValid(badArticle2));
	}
	
	@Test
	public void badArticle3Test() {
		Assert.assertFalse(InputDataValidator.isArticleValid(badArticle3));
	}
	
	@Test
	public void badArticle4Test() {
		Assert.assertFalse(InputDataValidator.isArticleValid(badArticle4));
	}
	
	@Test
	public void badArticle5Test() {
		Assert.assertFalse(InputDataValidator.isArticleValid(badArticle5));
	}
	
	@Test
	public void badArticle6Test() {
		Assert.assertFalse(InputDataValidator.isArticleValid(badArticle6));
	}
	
	@Test
	public void badArticle7Test() {
		Assert.assertFalse(InputDataValidator.isArticleValid(badArticle7));
	}
	
	@Test
	public void badArticle8Test() {
		Assert.assertFalse(InputDataValidator.isArticleValid(badArticle8));
	}
	
	@Test
	public void badArticle9Test() {
		Assert.assertFalse(InputDataValidator.isArticleValid(badArticle9));
	}
	
	
}
