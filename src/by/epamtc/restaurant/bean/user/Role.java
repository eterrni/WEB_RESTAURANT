package by.epamtc.restaurant.bean.user;

public enum Role {

	ADMINISTRATOR(1),
	USER(2);

	private Integer roleId;

	private Role(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getRoleId() {
		return roleId;
	}

}
