
public class LessTimidReplace implements LessTimid{

	private String from;
	private String to;
	
	public LessTimidReplace(String from, String to) {
		this.from = from;
		this.to = to;
	}
	@Override
	public String getDescription() {
		return "Replaces '" + from + "' with '" + to + "'";
	}
	@Override
	public String applyToString(String input) {
		return input.replace(from, to);
	}
	
}

