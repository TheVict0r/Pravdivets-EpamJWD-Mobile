package by.epamjwd.mobile.bean;

import java.io.Serializable;
import java.util.Date;

public class Abonent implements Identifiable, Serializable{

	private static final long serialVersionUID = 5046781397740402140L;
	
	private int id;
	private String login;
	private String password;
	
	private String firstName;
	private String middleName;
	private String lastName;
	private String passportNumber;
	
	private String email;
	private String region;
	private String homeAddress;
	
	private int contractNumber;
	private Date contractDate;

	private int checkingAccountNumber;
	private int checkingAccountAmount;
	
	private int phoneNumber;
	private String tariffPlan;
	private AbonentStatus status;
	
	public Abonent() {
		
	}
	
	public Abonent(int id, String login, String password, String firstName, String middleName, String lastName,
			String passportNumber, String email, String region, String homeAddress, int contractNumber,
			Date contractDate, int checkingAccountNumber, int checkingAccountAmount, int phoneNumber,
			String tariffPlan, AbonentStatus status) {
		
		this.id = id;
		this.login = login;
		this.password = password;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.passportNumber = passportNumber;
		this.email = email;
		this.region = region;
		this.homeAddress = homeAddress;
		this.contractNumber = contractNumber;
		this.contractDate = contractDate;
		this.checkingAccountNumber = checkingAccountNumber;
		this.checkingAccountAmount = checkingAccountAmount;
		this.phoneNumber = phoneNumber;
		this.tariffPlan = tariffPlan;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
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

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	public int getContractNumber() {
		return contractNumber;
	}

	public void setContractNumber(int contractNumber) {
		this.contractNumber = contractNumber;
	}

	public Date getContractDate() {
		return contractDate;
	}

	public void setContractDate(Date contractDate) {
		this.contractDate = contractDate;
	}

	public int getCheckingAccountNumber() {
		return checkingAccountNumber;
	}

	public void setCheckingAccountNumber(int checkingAccountNumber) {
		this.checkingAccountNumber = checkingAccountNumber;
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

	public void setTarifPlan(String tariffPlan) {
		this.tariffPlan = tariffPlan;
	}

	public AbonentStatus getStatus() {
		return status;
	}

	public void setStatus(AbonentStatus status) {
		this.status = status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + checkingAccountAmount;
		result = prime * result + checkingAccountNumber;
		result = prime * result + ((contractDate == null) ? 0 : contractDate.hashCode());
		result = prime * result + contractNumber;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((homeAddress == null) ? 0 : homeAddress.hashCode());
		result = prime * result + id;
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((middleName == null) ? 0 : middleName.hashCode());
		result = prime * result + ((passportNumber == null) ? 0 : passportNumber.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + phoneNumber;
		result = prime * result + ((region == null) ? 0 : region.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((tariffPlan == null) ? 0 : tariffPlan.hashCode());
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
		Abonent other = (Abonent) obj;
		if (checkingAccountAmount != other.checkingAccountAmount)
			return false;
		if (checkingAccountNumber != other.checkingAccountNumber)
			return false;
		if (contractDate == null) {
			if (other.contractDate != null)
				return false;
		} else if (!contractDate.equals(other.contractDate))
			return false;
		if (contractNumber != other.contractNumber)
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
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
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
		if (region != other.region)
			return false;
		if (status != other.status)
			return false;
		if (tariffPlan != other.tariffPlan)
			return false;
		return true;
	}

	@Override
	public String toString() {
		
		return new StringBuilder(getClass().getSimpleName())
                .append("[")
                .append("ID=").append(id)
                .append(", login=").append(login)
                .append(", password=").append(password)
                .append(", firstName=").append(firstName)
                .append(", middleName=").append(middleName)
                .append(", lastName=").append(lastName)
                .append(", passportNumber=").append(passportNumber)
                .append(", email=").append(email)
                .append(", region=").append(region)
                .append(", homeAddress=").append(homeAddress)
                .append(", contractNumber=").append(contractNumber)
                .append(", contractDate=").append(contractDate)
                .append(", checkingAccountNumber=").append(checkingAccountNumber)
                .append(", checkingAccountAmount=").append(checkingAccountAmount)
                .append(", phoneNumber=").append(phoneNumber)
                .append(", tariffPlan=").append(tariffPlan)
                .append(", status=").append(status)
                .append("]")
                .toString();
	}

}
