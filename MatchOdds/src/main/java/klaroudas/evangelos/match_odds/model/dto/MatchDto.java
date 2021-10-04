package klaroudas.evangelos.match_odds.model.dto;

import java.time.LocalDate;
import java.time.LocalTime;


import javax.validation.constraints.NotBlank;

import klaroudas.evangelos.match_odds.model.MatchEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Transfer class for @see MatchEntity
 * @author baggelis
 *
 */

@Getter @Setter @NoArgsConstructor 
public class MatchDto {
	
	private Long id;
	@NotBlank
	private String description;
	@NotBlank
	private LocalDate matchDate;

	private LocalTime matchTime;
	@NotBlank
	private String teamA;
	@NotBlank
	private String teamB;
	@NotBlank
	private String sport;
	
	public MatchDto(MatchEntity matchEntity) {
		this.id = matchEntity.getId();
		this.description = matchEntity.getDescription();
		this.teamA = matchEntity.getTeamA();
		this.teamB = matchEntity.getTeamB();
		this.matchDate = matchEntity.getMatchDate();
		this.matchTime = matchEntity.getMatchTime();
		this.sport = matchEntity.getSport();
	}
	
}
