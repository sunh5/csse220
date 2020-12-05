

public class FoodPizza {

	String topping;
	
	public FoodPizza(String topping) {
		this.topping = topping;
	}

	public void prepare() {
		System.out.println("mixing flour and water to make dough");
		System.out.println("adding tomato sauce, cheese, and " + topping);
	}
	
	public void bake() {
		System.out.println("baked 1 " + topping + " pizza!");
	}
}
