package by.epamjwd.mobile.bean;

import java.io.Serializable;

public class Customer implements Identifiable, Serializable{

	private static final long serialVersionUID = -4256569493787390327L;

	private long id;
	private String passportNumber;
	private String homeAddress;
	private long usersId;

	public Customer() {
	}

	public Customer(long id, String passportNumber, String homeAddress, long usersId) {
		this.id = id;
		this.passportNumber = passportNumber;
		this.homeAddress = homeAddress;
		this.usersId = usersId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPassportNumber() {
		return passportNumber;
	}

	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}

	public String getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	public long getUsersId() {
		return usersId;
	}

	public void setUsersId(long usersId) {
		this.usersId = usersId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((homeAddress == null) ? 0 : homeAddress.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((passportNumber == null) ? 0 : passportNumber.hashCode());
		result = prime * result + (int) (usersId ^ (usersId >>> 32));
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
		Customer other = (Customer) obj;
		if (homeAddress == null) {
			if (other.homeAddress != null)
				return false;
		} else if (!homeAddress.equals(other.homeAddress))
			return false;
		if (id != other.id)
			return false;
		if (passportNumber == null) {
			if (other.passportNumber != null)
				return false;
		} else if (!passportNumber.equals(other.passportNumber))
			return false;
		if (usersId != other.usersId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return new StringBuilder(getClass().getSimpleName())
                .append("[")
                .append("ID=").append(id)
                .append(", passportNumber=").append(passportNumber)
                .append(", homeAddress=").append(homeAddress)
                .append(", usersId=").append(usersId)
                .append("]")
                .toString();
	}

	
	
}
