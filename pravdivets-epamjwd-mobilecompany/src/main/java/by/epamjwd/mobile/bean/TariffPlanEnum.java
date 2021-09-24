package by.epamjwd.mobile.bean;

public enum TariffPlanEnum {

	STARTING("Начальный"),
	BASIC("Основной"),
	BUSINESS("Бизнес");
	
	private String name;
	
	private TariffPlanEnum (String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
