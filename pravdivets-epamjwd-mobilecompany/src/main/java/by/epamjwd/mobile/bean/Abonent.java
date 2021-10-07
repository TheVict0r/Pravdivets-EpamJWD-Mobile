package by.epamjwd.mobile.bean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Abonent extends User implements Identifiable, Serializable{

	private static final long serialVersionUID = 5046781397740402140L;
	
	private long id;
	private Date contractDate;
	
	private String password;
	
	private String firstName;
	private String middleName;
	private String lastName;
	private String passportNumber;
	
	private String email;
	private String homeAddress;
	
	private int checkingAccountAmount;
	
	private int phoneNumber;
	private String tariffPlan;
	private AbonentStatus status;
	private Date statusDate;
	
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
	
	
	public Abonent() {
		
	}
	
	public Abonent(long id, Date contractDate, String password, String firstName, String middleName, String lastName,
			String passportNumber, String email, String homeAddress, int checkingAccountAmount,
			int phoneNumber, String tariffPlan, AbonentStatus status, Date statusDate) {
		super();
		this.id = id;
		this.contractDate = contractDate;
		this.password = password;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.passportNumber = passportNumber;
		this.email = email;
		this.homeAddress = homeAddress;
		this.checkingAccountAmount = checkingAccountAmount;
		this.phoneNumber = phoneNumber;
		this.tariffPlan = tariffPlan;
		this.status = status;
		this.statusDate = statusDate;
	}





	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassportNumber() {
		return passportNumber;
	}

	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	public Date getContractDate() {
		return contractDate;
	}

	public void setContractDate(Date contractDate) {
		this.contractDate = contractDate;
	}

	public int getCheckingAccountAmount() {
		return checkingAccountAmount;
	}

	public void setCheckingAccountAmount(int checkingAccountAmount) {
		this.checkingAccountAmount = checkingAccountAmount;
	}

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getTariffPlan() {
		return tariffPlan;
	}

	public void setTariffPlan(String tariffPlan) {
		this.tariffPlan = tariffPlan;
	}
	
	public AbonentStatus getStatus() {
		return status;
	}

	public void setStatus(AbonentStatus status) {
		this.status = status;
	}


	public Date getStatusDate() {
		return statusDate;
	}

	public void setStatusDate(Date statusDate) {
		this.statusDate = statusDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + checkingAccountAmount;
		result = prime * result + ((contractDate == null) ? 0 : contractDate.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((formatter == null) ? 0 : formatter.hashCode());
		result = prime * result + ((homeAddress == null) ? 0 : homeAddress.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((middleName == null) ? 0 : middleName.hashCode());
		result = prime * result + ((passportNumber == null) ? 0 : passportNumber.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + phoneNumber;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((statusDate == null) ? 0 : statusDate.hashCode());
		result = prime * result + ((tariffPlan == null) ? 0 : tariffPlan.hashCode());
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
		Abonent other = (Abonent) obj;
		if (checkingAccountAmount != other.checkingAccountAmount)
			return false;
		if (contractDate == null) {
			if (other.contractDate != null)
				return false;
		} else if (!contractDate.equals(other.contractDate))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (formatter == null) {
			if (other.formatter != null)
				return false;
		} else if (!formatter.equals(other.formatter))
			return false;
		if (homeAddress == null) {
			if (other.homeAddress != null)
				return false;
		} else if (!homeAddress.equals(other.homeAddress))
			return false;
		if (id != other.id)
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (middleName == null) {
			if (other.middleName != null)
				return false;
		} else if (!middleName.equals(other.middleName))
			return false;
		if (passportNumber == null) {
			if (other.passportNumber != null)
				return false;
		} else if (!passportNumber.equals(other.passportNumber))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (phoneNumber != other.phoneNumber)
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (statusDate == null) {
			if (other.statusDate != null)
				return false;
		} else if (!statusDate.equals(other.statusDate))
			return false;
		if (tariffPlan == null) {
			if (other.tariffPlan != null)
				return false;
		} else if (!tariffPlan.equals(other.tariffPlan))
			return false;
		return true;
	}

	@Override
	public String toString() {
		
		return new StringBuilder(getClass().getSimpleName())
                .append("[")
                .append("ID=").append(id)
                .append(", contractDate=").append(formatter.format(contractDate))
                .append(", password=").append(password)
                .append(", firstName=").append(firstName)
                .append(", middleName=").append(middleName)
                .append(", lastName=").append(lastName)
                .append(", passportNumber=").append(passportNumber)
                .append(", email=").append(email)
                .append(", homeAddress=").append(homeAddress)
                .append(", checkingAccountAmount=").append(checkingAccountAmount)
                .append(", phoneNumber=").append(phoneNumber)
                .append(", tariffPlan=").append(tariffPlan)
                .append(", status=").append(status.getStatusName())
                .append(", statusDate=").append(formatter.format(statusDate))
                .append("]")
                .toString();
	}

}
