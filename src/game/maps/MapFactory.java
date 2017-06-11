package game.maps;

public class MapFactory {
	/**
	 * Return the map corresponding to map ID, which is in the form "[Rotation] - [Map #]".
	 * The rotation can be found on the FEH wiki, and the map # is the ordered exactly how they are ordered in the
	 * said wiki, starting from 1
	 * @param mapID the id of the map
	 * @return the map
	 * @throws IllegalArgumentException if the mapID cannot be parsed, or rotation or map# does not match the wiki
	 */
	public Map getMap(String mapID) {
		String[] tokens = mapID.split("-");
		int rotation, mapNumber;
		try{
			rotation = Integer.valueOf(tokens[0]);
			mapNumber = Integer.valueOf(tokens[1]);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Unparsable argument");
		}
		
		switch (rotation) {
		case 1:
			switch(mapNumber) {
			case 1: return new Map11();
			default: mapError(mapNumber);
			}
		default: throw new IllegalArgumentException("Illegal rotation number: " + rotation);
			
		}
	}
	
	/**
	 * Helper method that just throws an IllegalArgumentException for wrong map number
	 * @param mapNumber
	 * @throws IllegalArgumentException
	 */
	private void mapError(int mapNumber) {
		throw new IllegalArgumentException("Illegal rotation number: " + mapNumber);
	}
}
