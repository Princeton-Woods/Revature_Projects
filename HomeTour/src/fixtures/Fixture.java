package fixtures;

public abstract class Fixture {
	public String name;
	public String shortDescription;
	public String longDescription; 
	public String interactables;
	public String interactablesText;
	
	public Fixture(String name, String shortDescription, String longDescription, String interactables, String interactablesText ) {
		this.name = name;
		this.shortDescription = shortDescription;
		this.longDescription = longDescription;
		this.interactables = interactables;
		this.interactablesText = interactablesText;
	}
	public String getName() {
		return this.name;
	}
	public String GetshortDescription() {
		return this.shortDescription;
	}
	public String GetlongDescription() {
		return this.longDescription;
	}
	public String Getinteractables() {
		return this.interactables;
	}
	public String GetinteractablesText() {
		return this.interactablesText;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public void SetshortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
	public void SetlongDescription(String longDescription) {
		 this.longDescription = longDescription;
	}
	public void Setinteractables(String interactables) {
		this.interactables = interactables;
	}
	public void SetinteractablesText(String interactablesText) {
		 this.interactablesText = interactablesText;
	}
}
