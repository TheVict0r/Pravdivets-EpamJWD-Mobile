package by.epamjwd.mobile.bean;

public enum SubscriberStatus {

	ACTIVE("Активный"),
	SEMI_BLOCKED("Частично заблокирован"),
	BLOCKED("Заблокирован"),
	DEACTIVATED("Деактивирован"),
	PAUSED("Пауза");
	
	String statusName;
	
	SubscriberStatus(String statusName) {
		this.statusName = statusName;
	}
	
	public String getStatusName() {
		return statusName;
	}
	
}
