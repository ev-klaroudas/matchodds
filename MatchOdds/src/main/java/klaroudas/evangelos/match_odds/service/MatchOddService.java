package klaroudas.evangelos.match_odds.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import klaroudas.evangelos.match_odds.model.MatchEntity;
import klaroudas.evangelos.match_odds.model.MatchOddEntity;
import klaroudas.evangelos.match_odds.model.MatchOddRepository;
import klaroudas.evangelos.match_odds.model.MatchRepository;
import klaroudas.evangelos.match_odds.model.dto.MatchOddDto;

/**
 * Service class to implement CRUD operation for @see MatchOddEntity class
 * @author baggelis
 *
 */
@Service
public class MatchOddService {

	private final MatchOddRepository matchOddRepository;
	private final MatchRepository matchRepository;
	
	@Autowired
	public MatchOddService(MatchOddRepository matchOddRepository, MatchRepository matchRepository) {
		this.matchOddRepository = matchOddRepository;
		this.matchRepository = matchRepository;
	}
	
	/**
	 * Returns a MatchEntity
	 * @param id
	 * @return MatchOddDto
	 */
	public Optional<MatchOddDto> findById(Long id) {
		Optional<MatchOddEntity> matchOddEntity = matchOddRepository.findById(id);
		if(matchOddEntity.isPresent()) {
			MatchOddDto matchOddDto = new MatchOddDto(matchOddEntity.get());
			return Optional.of(matchOddDto);
		}
		return Optional.empty();
	}

	/**
	 * Delete a MatchOddEntity
	 * @param id
	 */
	public void delete(Long id) {
		matchOddRepository.deleteById(id);
	}

	/**
	 * Delete All MatchOdds from a MatchEntity
	 * @param matchId
	 */
	public void deleteAllOddsFromMatch(Long matchId) {
		Optional<MatchEntity> match =  matchRepository.findById(matchId);
		if(match.isPresent()) { 
		    List<Long> ids = match.get().getMatchOdds().stream() 
		               .map(MatchOddEntity::getId) 
		               .collect(Collectors.toList());
			matchOddRepository.deleteAllById(ids);
		}
	}

	/**
	 * create or update a MatchOddEntity
	 * @param matchOddDto
	 * @param errors
	 * @return Long
	 */
	public Optional<Long> save( MatchOddDto matchOddDto) {
		Optional<MatchEntity> matchEntity = matchRepository.findById(matchOddDto.getMatchId());
	
		if(matchEntity.isPresent()) {
			MatchOddEntity entity = new MatchOddEntity(matchOddDto, matchEntity.get());
		
			matchOddRepository.save(entity);
		}
		return Optional.empty();
	}

	/**
	 * Returns MatchOddsEntities from a MatchEntity
	 * @param matchId
	 * @return List<MatchOddDto>
	 */
	public Optional<List<MatchOddDto>> findMatchOdds(Long matchId) {

		Optional<MatchEntity> matchEntity = matchRepository.findById(matchId);
		if(matchEntity.isPresent()) {
			List<MatchOddEntity> matchOdds =  matchEntity.get().getMatchOdds();
			

			return Optional.of(matchOdds.stream()
					.map(odd -> new MatchOddDto(odd))
					.collect(Collectors.toList()));
            
		}
		return Optional.empty();
	}

	
}
