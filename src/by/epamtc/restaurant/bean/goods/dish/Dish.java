package by.epamtc.restaurant.bean.goods.dish;

import by.epamtc.restaurant.bean.goods.Goods;

public class Dish extends Goods {

	private static final long serialVersionUID = 2973380054614415338L;

	@Override
	public String toString() {
		return "Dish [Id=" + getId() + ", name=" + getName() + ", price=" + getPrice() + ", description()="
				+ getDescription() + "]";
	}

}
