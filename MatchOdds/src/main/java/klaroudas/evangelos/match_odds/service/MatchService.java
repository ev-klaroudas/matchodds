package klaroudas.evangelos.match_odds.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import klaroudas.evangelos.match_odds.model.MatchBuilder;
import klaroudas.evangelos.match_odds.model.MatchEntity;
import klaroudas.evangelos.match_odds.model.MatchRepository;
import klaroudas.evangelos.match_odds.model.dto.MatchDto;
/**
 * Service class to implement CRUD operation for @see MatchOdd class
 * @author baggelis
 *
 */
@Service
public class MatchService {
	
	private final MatchRepository matchRepository;
	
	@Autowired
	public MatchService(MatchRepository matchRepository) {
		this.matchRepository = matchRepository;
	}
	
	/**
	 * Update or Create  MatchEntity
	 * @param matchDto
	 * @return Long matchId
	 */
	public Long  save(MatchDto matchDto) {
		MatchEntity entity = new MatchEntity(matchDto);
		matchRepository.save(entity);
		return entity.getId();
	}
	
	/**
	 * Get all MatchEntities
	 * @return List<MatchDto>
	 */
	public List<MatchDto> getMatches() {
		 List<MatchDto> matches = new ArrayList<>();
		 matchRepository.findAll().forEach((f) -> matches.add(new MatchDto(f)));
		 return matches;
	}
	
	/**
	 * Delete MatchEntity
	 * @param matchId
	 * @return MatchDto
	 */
	public void delete(Long matchId) {
		matchRepository.delete(MatchBuilder.matchWithId(matchId));
	}
	
	/**
	 * get MatchEntity using matchId
	 * @param matchId
	 * @return MatchDto
	 */
	public Optional<MatchDto> findById(Long matchId) {
		Optional<MatchEntity> matchEntity = matchRepository.findById(matchId);
		if(matchEntity.isPresent()) {
			return Optional.of(new MatchDto(matchEntity.get()));
		}
		return Optional.empty();
	}
}
