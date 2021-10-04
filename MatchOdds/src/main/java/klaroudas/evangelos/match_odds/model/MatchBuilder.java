package klaroudas.evangelos.match_odds.model;

/**
 * Simple Builder for MatchEntity objects
 * @see MatchEntity
 * @author baggelis
 *
 */
public class MatchBuilder {

	private final static MatchBuilder instance = new MatchBuilder();
	

	private MatchBuilder() {
	}

	/**
	 * get singleton Matchbuilder instance
	 * @return MatchBuilder instance
	 */
	public static MatchBuilder create() {
		return instance;
	}

	/**
	 * create new MatchEntity instance 
	 * @param id the id of Entity
	 * @return MatchEntity
	 * @see MatchEntity
	 */
	
	public static MatchEntity matchWithId(Long id) {
		MatchEntity entity = new MatchEntity();
		if (id != null)
			entity.setId(id);
		return entity;
	}

}
