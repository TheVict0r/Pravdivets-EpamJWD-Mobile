package by.epamjwd.mobile.bean;

public enum Region {
	BREST_REG("Брестская область"),
	VITEBSK_REG("Витебская область"),
	GOMEL_REG("Гомельская область"),
	GRODNO_REG("Гродненская область"),
	MOGILEV_REG("Могилевская область"),
	MINK_REG("Минская область"),
	MINSK_CITY("г. Минск");
	
	String regionName;
	
	Region(String regionName) {
		this.regionName = regionName;
	}
	
	public String getRegionName() {
		return regionName;
	}
}
