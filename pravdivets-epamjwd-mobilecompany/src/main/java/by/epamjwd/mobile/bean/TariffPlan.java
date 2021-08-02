package by.epamjwd.mobile.bean;

public enum TariffPlan {

	STARTING("Начальный"),
	BASIC("Основной"),
	BUSINESS("Бизнес");
	
	private String name;
	
	private TariffPlan (String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
