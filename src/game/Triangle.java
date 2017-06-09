package game;

import utility.Util;

public enum Triangle {
	RED, BLUE, GREEN, COLORLESS;
	
	@Override
	public String toString() {
		return Util.capitalize(this.name());
	}
}
