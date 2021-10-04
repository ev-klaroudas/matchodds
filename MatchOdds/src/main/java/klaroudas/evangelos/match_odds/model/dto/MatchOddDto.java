package klaroudas.evangelos.match_odds.model.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import klaroudas.evangelos.match_odds.model.MatchOddEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Transfer class for @see MatchOddEntity
 * @author baggelis
 *
 */

@Getter @Setter @NoArgsConstructor 
public class MatchOddDto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	@NotBlank
	private Long  matchId;
	
	
	@NotBlank
	private String specifier;
	@NotBlank
	private Float odd;
	
	public MatchOddDto (MatchOddEntity entity) {
		this.id = entity.getId() ;
		this.matchId = entity.getMatch().getId();
		this.specifier = entity.getSpecifier();
		this.odd = entity.getOdd() ;
	}
	
}
