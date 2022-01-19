package by.epamjwd.mobile.bean;

/**
 * Subscriber statuses. Used to define their rights and available services.
 *
 */
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
