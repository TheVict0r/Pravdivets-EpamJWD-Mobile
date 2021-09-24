package by.epamjwd.mobile.bean;

import java.io.Serializable;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NewsArticle implements Serializable {

	private static final long serialVersionUID = 5492324511728285746L;
	
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
	private final static Logger LOGGER = LogManager.getLogger(NewsArticle.class);
	
	
	private int id;
	private Date date;
	private String title; 
	private String lead;
	private String text;
	
	public NewsArticle() {
		
	}

	public NewsArticle(int id, Date date, String title, String lead, String text) {
		super();
		this.id = id;
		this.date = date;
		this.title = title;
		this.lead = lead;
		this.text = text;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public String getLead() {
		return lead;
	}

	public void setLead(String lead) {
		this.lead = lead;
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
		result = prime * result + id;
		result = prime * result + ((lead == null) ? 0 : lead.hashCode());
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
		NewsArticle other = (NewsArticle) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (id != other.id)
			return false;
		if (lead == null) {
			if (other.lead != null)
				return false;
		} else if (!lead.equals(other.lead))
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
                .append(", Lead=").append(lead)
                .append(", Text=").append(text)
                .append("]")
                .toString();
	}


	
}
