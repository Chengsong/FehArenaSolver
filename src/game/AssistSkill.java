package game;

import utility.Util;

public enum AssistSkill {
	SMITE, REPOSITION;
	
	@Override
	public String toString() {
		return Util.capitalize(this.name());
	}
}
