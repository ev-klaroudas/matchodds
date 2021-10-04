package klaroudas.evangelos.match_odds.model.dto;


/**
 * Enum which defines valid basketballs' match odd types
 * We support some basic odds for simplicity
 * <p>Odds types<ul>
 * <li>Home, Draw, Away</li>
 * <li>Over/Under Total Points (156.5 points)</li>
 * <li>Home, Away Handicap (+5.5 points) </li>
 * </ul></p>
 * @author baggelis
 *
 */
public enum BasketBallSpecifierEnum {
	
	HOME("Home"), DRAW("Draw"), AWAY("Away"), POINTS_OVER("Over 156.5") ,
	POINTS_UNDER("Under 156.5") , HOME_HANDICAP ("Home handicap +5.5"), AWAY_HANDICAP("Away handicap +5.5") ;
	
	private final String specifier;

	/**
	 * BasketBallSpecifierEnum constructor
	 * @param specifier
	 */
	BasketBallSpecifierEnum(final String specifier) {
		this.specifier = specifier;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return specifier;
	}
	
}
