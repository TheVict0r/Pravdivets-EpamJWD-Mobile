package by.epamjwd.mobile.bean;

import java.io.Serializable;

/**
 * Tariff plan of mobile operator. Contains short name, 
 * verbal description and and the cost of services etc.
 * 
 */
public class Plan implements Identifiable, Serializable{

	private static final long serialVersionUID = 1240407715285924386L;
	
	private long id;
	private String name;
	private String description;
	private int regularPayment;
	private int upfrontPayment;
	private int priceWithinNetwork;
	private int priceOtherNetworks;
	private int priceAbroad;
	private int priceVideocall;
	private int priceSMS;
	private int priceMMS;
	private int priceInternet;
	
	public Plan() {
		
	}


	
	public Plan(long id, String name, int regularPayment, int upfrontPayment, String description,
			int priceWithinNetwork, int priceOtherNetworks, int priceAbroad, int priceVideocall, int priceSMS,
			int priceMMS, int priceInternet) {
		super();
		this.id = id;
		this.name = name;
		this.regularPayment = regularPayment;
		this.upfrontPayment = upfrontPayment;
		this.description = description;
		this.priceWithinNetwork = priceWithinNetwork;
		this.priceOtherNetworks = priceOtherNetworks;
		this.priceAbroad = priceAbroad;
		this.priceVideocall = priceVideocall;
		this.priceSMS = priceSMS;
		this.priceMMS = priceMMS;
		this.priceInternet = priceInternet;
	}



	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRegularPayment() {
		return regularPayment;
	}

	public void setRegularPayment(int regularPayment) {
		this.regularPayment = regularPayment;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPriceWithinNetwork() {
		return priceWithinNetwork;
	}

	public void setPriceWithinNetwork(int priceWithinNetwork) {
		this.priceWithinNetwork = priceWithinNetwork;
	}

	public int getPriceOtherNetworks() {
		return priceOtherNetworks;
	}

	public void setPriceOtherNetworks(int priceOtherNetworks) {
		this.priceOtherNetworks = priceOtherNetworks;
	}

	public int getPriceAbroad() {
		return priceAbroad;
	}

	public void setPriceAbroad(int priceAbroad) {
		this.priceAbroad = priceAbroad;
	}

	public int getPriceVideocall() {
		return priceVideocall;
	}

	public void setPriceVideocall(int priceVideocall) {
		this.priceVideocall = priceVideocall;
	}

	public int getPriceSMS() {
		return priceSMS;
	}

	public void setPriceSMS(int priceSMS) {
		this.priceSMS = priceSMS;
	}

	public int getPriceMMS() {
		return priceMMS;
	}

	public void setPriceMMS(int priceMMS) {
		this.priceMMS = priceMMS;
	}

	public int getPriceInternet() {
		return priceInternet;
	}

	public void setPriceInternet(int priceInternet) {
		this.priceInternet = priceInternet;
	}

	
	
	public int getUpfrontPayment() {
		return upfrontPayment;
	}

	public void setUpfrontPayment(int upfrontPayment) {
		this.upfrontPayment = upfrontPayment;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + priceAbroad;
		result = prime * result + priceInternet;
		result = prime * result + priceMMS;
		result = prime * result + priceOtherNetworks;
		result = prime * result + priceSMS;
		result = prime * result + priceVideocall;
		result = prime * result + priceWithinNetwork;
		result = prime * result + regularPayment;
		result = prime * result + upfrontPayment;
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
		Plan other = (Plan) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (priceAbroad != other.priceAbroad)
			return false;
		if (priceInternet != other.priceInternet)
			return false;
		if (priceMMS != other.priceMMS)
			return false;
		if (priceOtherNetworks != other.priceOtherNetworks)
			return false;
		if (priceSMS != other.priceSMS)
			return false;
		if (priceVideocall != other.priceVideocall)
			return false;
		if (priceWithinNetwork != other.priceWithinNetwork)
			return false;
		if (regularPayment != other.regularPayment)
			return false;
		if (upfrontPayment != other.upfrontPayment)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return new StringBuilder(getClass().getSimpleName())
				.append("[")
				.append("ID=").append(id)
				.append(", Name=").append(name)
				.append(", Regular payment=").append(regularPayment)
				.append(", Upfront payment=").append(upfrontPayment)
				.append(", Description=").append(description)
				.append(", Price: within network=").append(priceWithinNetwork)
				.append(", other networks=").append(priceOtherNetworks)
				.append(", abroad=").append(priceAbroad)
				.append(", videocall=").append(priceVideocall)
				.append(", SMS=").append(priceSMS)
				.append(", MMS=").append(priceMMS)
				.append(", internet=").append(priceInternet)
				.append("]")
				.toString();
	}
	
}
