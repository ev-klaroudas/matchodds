package klaroudas.evangelos.match_odds.model.dto;

/**
 * Enum which defines valid football's match odd types
 * <p> Supported odd types<ul>
 * <li>Home, Draw, Away</li>
 * <li>Total Goals 0-1 , 2-3 , 4-6 , 7+ </li>
 * <li>Final score 0-1 , 1-0 etc. Note: We simplify the variety
 *  of odds and for full score we define same odd for goals 2 and above (2+)</li>
 * </ul></p>
 * @author baggelis
 *
 */
public enum FootballSpecifierEnum {
	
	HOME("Home"), DRAW("Draw"), AWAY("Away"), GG("GG"), NG("NG"), GOALS_0_2("0-1"), GOALS_2_3("2-3"), GOALS_4_6("4-6"),
	GOALS_7_PLUS("7+"), SCORE_0_0("0-0"), SCORE_1_0("1-0"), SCORE_0_1("0-1") , SCORE_1_1("1-1"),SCORE_2P_0("2+-0"), SCORE_0_2P("0-2+"),
	SCORE_2P_1("2+-1"), SCORE_1_2P("1-2+") ,SCORE_2P_2P("2+-2+");

	private final String specifier;

	/**
	 * FootballSpecifierEnum constructor
	 * @param specifier
	 */
	FootballSpecifierEnum(final String specifier) {
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
