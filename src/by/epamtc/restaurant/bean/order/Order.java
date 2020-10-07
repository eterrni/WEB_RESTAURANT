package by.epamtc.restaurant.bean.order;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable {

	private static final long serialVersionUID = 8855300846389844573L;

	private Integer id;
	private String status;
	private Date createDate;
	private Integer orderMark;
	
	public Order() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getOrderMark() {
		return orderMark;
	}

	public void setOrderMark(Integer orderMark) {
		this.orderMark = orderMark;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createDate == null) ? 0 : createDate.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((orderMark == null) ? 0 : orderMark.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		Order other = (Order) obj;
		if (createDate == null) {
			if (other.createDate != null)
				return false;
		} else if (!createDate.equals(other.createDate))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (orderMark == null) {
			if (other.orderMark != null)
				return false;
		} else if (!orderMark.equals(other.orderMark))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", status=" + status + ", createDate=" + createDate + ", orderMark=" + orderMark
				+ "]";
	}
	
	
}
