package com.globits.da;

public class Constants {
	public static final String STT = "STT";
	public static final String NAME = "Name";
	public static final String AGE = "Age";
	public static final String CODE = "Code";
	public static final String EMAIL = "Email";
	public static final String PHONE = "Phone";
	public static final int CODE_MIN_LENGTH = 6;
	public static final int CODE_MAX_LENGTH = 10;
	public static final int PHONE_MAX_LENGTH = 11;
	public static final int PHONE_MIN_LENGTH = 10;
	public static final String SHEET_NAME = "Employee";
	public static final String PHONE_REGEX = "^\\s?((\\+[1-9]{1,4}[ \\-]*)|(\\([0-9]{2,3}\\)[ \\-]*)|([0-9]{2,4})[ \\-]*)*?[0-9]{3,4}?[ \\-]*[0-9]{3,4}?\\s?";
	public static final String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	public static enum StaffType {
		Sale(1), // nhân viên bán hàng
		Cashier(2), // nhân viên thu ngân
		Other(3)// khác
		;

		private Integer value;

		private StaffType(Integer value) {
			this.value = value;
		}

		public Integer getValue() {
			return value;
		}
	}

	public static enum ChannelAds {// kenh quang cao
		Webiste(1), // website
		Contextual_Advertiser(2), // khen hquang cao
		Social_Netword(3), // mang xa hoi
		Youtube_channel(4)// youtube
		;

		private Integer value;

		private ChannelAds(Integer value) {
			this.value = value;
		}

		public Integer getValue() {
			return value;
		}
	}
	
	public static enum Social_Netword {// kenh quang cao
		Facebook(1), // website
		Zalo(2), // khen hquang cao
		Tiktok(3), // mang xa hoi
		Other(4)// youtube
		;

		private Integer value;

		private Social_Netword(Integer value) {
			this.value = value;
		}

		public Integer getValue() {
			return value;
		}
	}

}
