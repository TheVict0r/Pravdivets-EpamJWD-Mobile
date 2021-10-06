package by.epamjwd.mobile.bean;

public enum AbonentStatus {

	ACTIVE("Активный"),
	SEMI_BLOCKED("Частично заблокирован"),
	BLOCKED("Заблокирован"),
	DEACTIVATED("Деактивирован"),
	PAUSED("Пауза");
	
	String statusName;
	
	AbonentStatus(String statusName) {
		this.statusName = statusName;
	}
	
	public String getStatusName() {
		return statusName;
	}
	
}
