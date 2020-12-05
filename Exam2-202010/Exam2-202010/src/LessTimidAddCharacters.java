
public class LessTimidAddCharacters implements LessTimid {

	private String toAdd;
	
	public LessTimidAddCharacters(String toAdd) {
		this.toAdd = toAdd;
	}
	@Override
	public String getDescription() {
		return "Adds '" + toAdd + "'to the end";
	}
	@Override
	public String applyToString(String input) {
		return input + toAdd;
	}
	@Override
	public String applyToString() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
