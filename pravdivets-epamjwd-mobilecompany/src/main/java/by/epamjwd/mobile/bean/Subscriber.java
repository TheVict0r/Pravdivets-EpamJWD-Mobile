package by.epamjwd.mobile.bean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Subscriber implements Identifiable, Serializable{

	private static final long serialVersionUID = 5046781397740402140L;
	
	private long id;
	private Date contractDate;
	private int account;
	private int phone;
	private Date statusDate;
	private SubscriberStatus status;
	private long planId;
	private long userId;

	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
	
	
	public Subscriber() {
		
	}
	
	public Subscriber(long id, Date contractDate, int account, int phone, Date statusDate, SubscriberStatus status,
			long planId, long userId) {
		super();
		this.id = id;
		this.contractDate = contractDate;
		this.account = account;
		this.phone = phone;
		this.statusDate = statusDate;
		this.status = status;
		this.planId = planId;
		this.userId = userId;
	}






	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getContractDate() {
		return contractDate;
	}

	public void setContractDate(Date contractDate) {
		this.contractDate = contractDate;
	}

	public int getAccount() {
		return account;
	}

	public void setAccount(int account) {
		this.account = account;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public Date getStatusDate() {
		return statusDate;
	}

	public void setStatusDate(Date statusDate) {
		this.statusDate = statusDate;
	}

	public SubscriberStatus getStatus() {
		return status;
	}

	public void setStatus(SubscriberStatus status) {
		this.status = status;
	}

	public long getPlanId() {
		return planId;
	}

	public void setPlanId(long planId) {
		this.planId = planId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + account;
		result = prime * result + ((contractDate == null) ? 0 : contractDate.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + phone;
		result = prime * result + (int) (planId ^ (planId >>> 32));
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((statusDate == null) ? 0 : statusDate.hashCode());
		result = prime * result + (int) (userId ^ (userId >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Subscriber other = (Subscriber) obj;
		if (account != other.account)
			return false;
		if (contractDate == null) {
			if (other.contractDate != null)
				return false;
		} else if (!contractDate.equals(other.contractDate))
			return false;
		if (id != other.id)
			return false;
		if (phone != other.phone)
			return false;
		if (planId != other.planId)
			return false;
		if (status != other.status)
			return false;
		if (statusDate == null) {
			if (other.statusDate != null)
				return false;
		} else if (!statusDate.equals(other.statusDate))
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		
		return new StringBuilder(getClass().getSimpleName())
                .append("[")
                .append("ID=").append(id)
                .append(", contractDate=").append(formatter.format(contractDate))
                .append(", account=").append(account)
                .append(", phone=").append(phone)
                .append(", planId=").append(planId)
                .append(", status=").append(status.getStatusName())
                .append(", statusDate=").append(formatter.format(statusDate))
                .append(", userId=").append(userId)
                .append("]")
                .toString();
	}

}