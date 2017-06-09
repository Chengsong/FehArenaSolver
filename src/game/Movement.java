package game;

import utility.Util;

public enum Movement {
	INFANTRY, ARMOR, HORSE, FLIER;
	
	@Override
	public String toString() {
		return Util.capitalize(this.name());
	}
}