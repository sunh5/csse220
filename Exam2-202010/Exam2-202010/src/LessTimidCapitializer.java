
public class LessTimidCapitializer implements LessTimid{
@Override
	public String getDescription() {
		return "Make string all captials";
	}
	@Override
	public String applyToString(String input) {
		return input.toUpperCase();
	}
	
}
