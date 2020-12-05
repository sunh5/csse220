

import java.util.ArrayList;

public class FoodMain {

	private ArrayList<Object> orders;
	
	public FoodMain() {
		orders = new ArrayList<>();
		setupOrders();
	}

	public void setupOrders() {
		// you should not need to change this function
		orders.add(new FoodPizza("pepperoni"));
		orders.add(new FoodCookies(100));
		orders.add(new FoodPizza("olive"));
		orders.add(new FoodPizza("mushroom"));
		orders.add(new FoodSouffle());
	}
	
	public void makeAllOrders() {
		while(!orders.isEmpty()) {
			Object order = orders.remove(0);
			if(order instanceof FoodPizza) {
				FoodPizza pizzaOrder = (FoodPizza) order;
				pizzaOrder.prepare();
				pizzaOrder.bake();
			}
			if(order instanceof FoodCookies) {
				FoodCookies cookieOrder = (FoodCookies) order;
				cookieOrder.prepare();
				cookieOrder.bake();
			}
			if(order instanceof FoodSouffle) {
				FoodSouffle souffleOrder = (FoodSouffle) order;
				souffleOrder.prepare();
				souffleOrder.bake();
			}
		}
	}
	
	public static void main(String[] args) {
		FoodMain main = new FoodMain();
		main.makeAllOrders();
	}
}
