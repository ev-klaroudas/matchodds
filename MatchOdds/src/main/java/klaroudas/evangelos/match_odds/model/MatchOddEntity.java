package klaroudas.evangelos.match_odds.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import klaroudas.evangelos.match_odds.model.dto.MatchOddDto;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * MatchOddEntity class maps to match_odds database table
 * @author baggelis
 *
 */
@Entity
@Table(name = "match_odds")
@Getter @Setter @NoArgsConstructor @EqualsAndHashCode
public class MatchOddEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6681686661702802449L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "match_id")
    private MatchEntity match;
	
    @NotBlank
	private String specifier;
    
	private float odd;
	
	/**
	 * MatchOddEntity constructor 
	 * @param matchOddDto 
	 * @param match 
	 */
	
	public MatchOddEntity(MatchOddDto matchOddDto, MatchEntity match) {
		this.id = matchOddDto.getId();
		this.match = match;
		this.specifier = matchOddDto.getSpecifier();
		this.odd = matchOddDto.getOdd();
	}
	
}
