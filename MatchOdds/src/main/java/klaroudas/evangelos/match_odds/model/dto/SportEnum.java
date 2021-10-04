package klaroudas.evangelos.match_odds.model.dto;
/**
 *
 *  <p> Enum for sports <ul>
 * <li>1 Football</li>
 * <li>2 Basketball</li>
 * </ul></p>
 * @author baggelis
 *
 */
public enum SportEnum {
	FOOTBALL("1"), BASKETBALL("2");
	 private final String sport;

    /**
     * @param specifier
     */
    SportEnum(final String sport) {
        this.sport = sport;
    }

    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
