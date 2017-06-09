package game;

import java.util.List;

import utility.Util;

public enum Skill {
	BLADE_TOME, RAVEN_TOME,
	
	FURY, LIFE_AND_DEATH,
	
	DESPERATION, VANTAGE,
	
	HONE_ATK, HONE_SPD;
	
	@Override
	public String toString() {
		return Util.capitalize(this.name());
	}
	
	/**
	 * Returns a list of weapon skills
	 * @return a list of weapon skills
	 */
	public List<Skill> getWeaponSkills() {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * Returns a list of A skills
	 * @return a list of A skills
	 */
	public List<Skill> getASkills() {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * Returns a list of B skills
	 * @return a list of B skills
	 */
	public List<Skill> getBSkills() {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * Returns a list of C skills
	 * @return a list of C skills
	 */
	public List<Skill> getCSkills() {
		throw new UnsupportedOperationException();
	}
}
