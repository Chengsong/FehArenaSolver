package game;

import java.util.HashMap;
import java.util.Map;

/**
 * Class that represents a Unit in FEH, which means that it has some stats and a set of skills and is able to fight
 * other units.
 */
public class Unit {
	public enum Triangle {
		RED, BLUE, GREEN, COLORLESS;
		
		@Override
		public String toString() {
			return capitalize(this.name().toLowerCase());
		}
	}
	public enum Movement {
		INFANTRY, ARMOR, HORSE, FLIER;
		
		@Override
		public String toString() {
			return capitalize(this.name().toLowerCase());
		}
	}
	
	/**
	 * Helper method that returns the string with its first letter capitalized
	 * @param s the input string
	 * @return the string with its first letter capitalized
	 */
	private static String capitalize(String s) {
		return s.substring(0, 1).toUpperCase() + s.substring(1);
	}
	
	private static final int HP = 0;
	private static final int ATK = 1;
	private static final int DEF = 2;
	private static final int RES = 3;
	private static final int SPD = 4;
	private static final String NONE = "NONE";
	
	private final Map<String, Integer> skills; // <skill name, skill level>
	private final String assist;
	private final int[] stats; // hp, atk, def, res, spd
	private final int[] buffs; // hp, atk, def, res, spd
	private final int range; // range 0 means no weapon
	private final Triangle type;
	private final Movement moveType;
	private final boolean isHealer;
	
	private int currentHP;
	private int skillCharge;
	
	/**
	 * Creates new Unit given skills, stats, and range
	 * @requires range >= 0
	 * @param skills the skills of the unit in the form of (skill_name, skill_level)
	 * @param hp
	 * @param atk
	 * @param def
	 * @param res
	 * @param spd
	 * @param range the range of the unit, usually either 1 or 2. range 0 means unit has no weapon
	 * @param type the weapon triangle type of the unit
	 * @param moveType the movement type of the unit
	 * @param isHealer whether the unit is a healer
	 */
	private Unit(Builder b) {
		this.skills = b.skills;
		this.assist = b.assist;
		this.stats = b.stats;
		buffs = new int[5];
		this.range = b.range;
		this.type = b.type;
		this.moveType = b.moveType;
		this.isHealer = b.isHealer;
		currentHP = stats[HP];
		skillCharge = 0;
	}
	
	/**
	 * Builder for Unit
	 */
	public static class Builder {
		private Map<String, Integer> skills; // <skill name, skill level>
		private int[] stats; // hp, atk, def, res, spd
		private int[] buffs; // hp, atk, def, res, spd
		private int range; // range 0 means no weapon
		private Triangle type;
		private Movement moveType;
		private boolean isHealer;
		private String assist;
		
		/**
		 * Creates a base unit with specified weapon and movement types
		 * @param type weapon type
		 * @param moveType movement type
		 */
		public Builder(Triangle type, Movement moveType) {
			this.type = type;
			this.moveType = moveType;
			this.skills = new HashMap<>();
			this.stats = new int[5];
			assist = NONE;
		}
		
		/**
		 * Set the stats of the unit
		 * @param hp
		 * @param atk
		 * @param def
		 * @param res
		 * @param spd
		 * @return this
		 */
		public Builder stats(int hp, int atk, int def, int res, int spd) {
			stats[HP] = hp;
			stats[ATK] = atk;
			stats[DEF] = def;
			stats[RES] = res;
			stats[SPD] = spd;
			return this;
		}
		
		/**
		 * Adds a skill that does not have a skill level
		 * @param skillName
		 * @return this
		 */
		public Builder skill(String skillName) {
			return skill(skillName, 0);
		}
		
		
		/**
		 * Adds a skill with skill level
		 * @param skillName
		 * @param skillLevel
		 * @return this
		 */
		public Builder skill(String skillName, int skillLevel) {
			skills.put(skillName, skillLevel);
			return this;
		}
		
		/**
		 * Set the assist skill
		 * @param skillName
		 * @return this
		 */
		public Builder assist(String skillName) {
			assist = skillName;
			return this;
		}
		
		/**
		 * Set the range of the unit, default value 0 (no weapon)
		 * @param range Range of the unit, usually between 1-2. 0 range indicates no weapon.
		 * @return this
		 */
		public Builder range(int range) {
			this.range = range;
			return this;
		}
		
		/**
		 * Mark the unit as a healer
		 * @return this
		 */
		public Builder isHealer() {
			isHealer = true;
			return this;
		}
		
		/**
		 * Build the Unit object given the builder configuration.
		 * @return a new Unit object
		 */
		public Unit build() {
			return new Unit(this);
		}
	}
}
