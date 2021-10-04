package klaroudas.evangelos.match_odds.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;



import klaroudas.evangelos.match_odds.model.dto.MatchDto;
import klaroudas.evangelos.match_odds.model.dto.SportEnum;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * MatchEntity class maps to matches database table
 * @author baggelis
 *
 */

@Entity
@Table(name = "matches")

@Getter @Setter @NoArgsConstructor  @EqualsAndHashCode
public class MatchEntity implements Serializable {
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6169105132852496345L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String description;
	
	@Column(name="match_date")
	@NotBlank
	private LocalDate matchDate; 
	
	@NotBlank
	@Column(name="match_time")
	private LocalTime matchTime; 
	
	@NotBlank
	@Column(name="team_a")
	private String teamA;
	
	@NotBlank
	@Column(name="team_b")
	private String teamB;
	
	@NotBlank
	private String sport; 
	
	@OneToMany(mappedBy = "match", cascade = { CascadeType.ALL}, orphanRemoval = true)
    private List<MatchOddEntity> matchOdds;
	
	/**
	 * MatchEntity constructor 
	 * @param matchDto 
	 */
	public MatchEntity(MatchDto matchDto) {
		this.id = matchDto.getId();
		this.description = matchDto.getDescription();
		this.matchDate =  matchDto.getMatchDate();
		this.matchTime = matchDto.getMatchTime();
		this.teamA = matchDto.getTeamA();
		this.teamB = matchDto.getTeamB();
		this.sport = matchDto.getSport();
		
	}

}
