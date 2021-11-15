package by.epamjwd.mobile.bean;

import java.io.Serializable;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class Bill implements Identifiable, Serializable{

	private static final long serialVersionUID = -2086131663670245454L;

	private long id;
	private long subscriberID;
	private Date date;
	private int amount;
	
	private final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

	
	public Bill() {
	}

	public Bill(long id, long subscriberID, Date date, int amount) {
		super();
		this.id = id;
		this.subscriberID = subscriberID;
		this.date = date;
		this.amount = amount;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public long getId() {
		return id;
	}

	public long getSubscriberID() {
		return subscriberID;
	}

	public void setSubscriberID(long subscriberID) {
		this.subscriberID = subscriberID;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + amount;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + (int) (subscriberID ^ (subscriberID >>> 32));
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
		Bill other = (Bill) obj;
		if (amount != other.amount)
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (id != other.id)
			return false;
		if (subscriberID != other.subscriberID)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return new StringBuilder(getClass().getSimpleName())
                .append("[")
                .append("ID=").append(id)
				.append(", subscriberID=").append(subscriberID)
				.append(", date=").append(formatter.format(date))
				.append(", amount=").append(amount)
				.append("]")
                .toString();
	}

}
