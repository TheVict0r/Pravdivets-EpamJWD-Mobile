package by.epamjwd.mobile.bean;

import java.io.Serializable;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Article implements Identifiable, Serializable {

	private static final long serialVersionUID = 5492324511728285746L;
	
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
	private final static Logger LOGGER = LogManager.getLogger(Article.class);
	
	
	private long id;
	private Date date;
	private String title; 
	private String intro;
	private String text;
	
	public Article() {
		
	}

	public Article(long id, Date date, String title, String lead, String text) {
		super();
		this.id = id;
		this.date = date;
		this.title = title;
		this.intro = lead;
		this.text = text;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public String getDateAsString() {
		return formatter.format(date).toString();
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setDateAsString(String date) {
		try {
			this.date = (Date) formatter.parse(date);
		} catch (ParseException e) {
			LOGGER.error("Can't parse String data to Date for the news article" + e.getMessage());
		}
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((formatter == null) ? 0 : formatter.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((intro == null) ? 0 : intro.hashCode());
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Article other = (Article) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (formatter == null) {
			if (other.formatter != null)
				return false;
		} else if (!formatter.equals(other.formatter))
			return false;
		if (id != other.id)
			return false;
		if (intro == null) {
			if (other.intro != null)
				return false;
		} else if (!intro.equals(other.intro))
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
		
	
	@Override
	public String toString() {
		return new StringBuilder(getClass().getSimpleName())
                .append("[")
                .append("ID=").append(id)
                .append(", Date=").append(formatter.format(date))
                .append(", Title=").append(title)
                .append(", Intro=").append(intro)
                .append(", Text=").append(text)
                .append("]")
                .toString();
	}


	
}
