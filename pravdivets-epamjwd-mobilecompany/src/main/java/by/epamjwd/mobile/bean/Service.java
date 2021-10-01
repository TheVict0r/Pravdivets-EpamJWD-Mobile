package by.epamjwd.mobile.bean;

import java.io.Serializable;

public class Service implements Identifiable, Serializable{

	private static final long serialVersionUID = -3179952249585984534L;
	
	private int id;
	private String name;
	private int tarif;
	private String description;
	
	public Service() {
	}

	public Service(int id, String name, int tarif, String description) {
		super();
		this.id = id;
		this.name = name;
		this.tarif = tarif;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTarif() {
		return tarif;
	}

	public void setTarif(int tarif) {
		this.tarif = tarif;
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
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + tarif;
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
		if (tarif != other.tarif)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return new StringBuilder(getClass().getSimpleName())
				.append("[")
				.append("ID=").append(id)
				.append(", Name=").append(name)
				.append(", Tarif=").append(tarif)
				.append(", Description=").append(description)
				.append("]")
				.toString();
	}
	
}
