package by.epamjwd.mobile.bean;

import java.io.Serializable;

/**
 * Additional service. Not included in the tariff plans.
 *
 */
public class Service implements Identifiable, Serializable{

	private static final long serialVersionUID = -3179952249585984534L;
	
	private long id;
	private String name;
	private int tariff;
	private String description;
	
	public Service() {
	}

	public Service(long id, String name, int tariff, String description) {
		this.id = id;
		this.name = name;
		this.tariff = tariff;
		this.description = description;
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

	public int getTariff() {
		return tariff;
	}

	public void setTarif(int tarif) {
		this.tariff = tarif;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + tariff;
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
		Service other = (Service) obj;
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
		if (tariff != other.tariff)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return new StringBuilder(getClass().getSimpleName())
				.append("[")
				.append("ID=").append(id)
				.append(", Name=").append(name)
				.append(", Tarif=").append(tariff)
				.append(", Description=").append(description)
				.append("]")
				.toString();
	}
	
}
