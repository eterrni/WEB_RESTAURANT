package by.epamtc.restaurant.bean.goods.drink;

import by.epamtc.restaurant.bean.goods.Goods;

public class Drink extends Goods {

	private static final long serialVersionUID = -2660099385328026161L;

	@Override
	public String toString() {
		return "Drink [Id=" + getId() + ", name=" + getName() + ", price=" + getPrice() + ", description()="
				+ getDescription() + "]";
	}
}
