package klaroudas.evangelos.match_odds.rest;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import klaroudas.evangelos.match_odds.model.dto.MatchDto;
import klaroudas.evangelos.match_odds.model.dto.MatchOddDto;
import klaroudas.evangelos.match_odds.service.MatchOddService;

/**
 * Controller REST API class to expose @see MatchOddService
 * @author baggelis
 *
 */

@RestController
@RequestMapping("api/v1")
public class MatchOddController {

	private final MatchOddService matchOddService;
	private Logger logger = Logger.getLogger(MatchOddController.class);
	@Autowired
	public MatchOddController(MatchOddService matchOddService) {
		this.matchOddService = matchOddService;
	}

	
	
	
	/**
	 * Response for matchodd
	 * @param id
	 * @return MatchOddDto
	 */
	

	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Return matchodd.", 
					content = @Content(schema = @Schema(implementation = MatchOddDto.class))),
			@ApiResponse(responseCode = "404", description = "MatchOdd not found.")})
	@Operation(summary = "Return matchodd", description = "Return matchodd ")
	
	@GetMapping("/matchodds/{id}")
	public ResponseEntity<MatchOddDto> getMatchOdd(@PathVariable Long id) {
		Optional<MatchOddDto> matchDto = matchOddService.findById(id);
		if (matchDto.isPresent())
			return ResponseEntity.ok(matchDto.get());
		return ResponseEntity.notFound().build();
	}
	
	
	
	/**
	 * Response for matchodd list of a match
	 * @param matchId
	 * @return List<MatchOddDto>
	 */
	

	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Get All odds for a match.", 
					content = @Content(array = @ArraySchema(schema = @Schema(implementation = MatchOddDto.class)))),
			@ApiResponse(responseCode = "404", description = "Match not found.")})
	@Operation(summary = "Get All odds for a match", description = "Get All odds for a match ")
	
	@GetMapping("/matchodds/match/{match_id}")
	public ResponseEntity<List<MatchOddDto>> getMatchMatchOdds(@PathVariable("match_id") Long matchId) {
		Optional<List<MatchOddDto>> matchDtoList = matchOddService.findMatchOdds(matchId);
		if (matchDtoList.isPresent())
			return ResponseEntity.ok(matchDtoList.get());
		return ResponseEntity.notFound().build();
	}
	
	
	
	
	/**
	 * Response for creating / updating matchodd
	 * @param matchOddDto
	 * @param errors
	 * @return
	 */
	
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Update Create MatchOdd."), 
			@ApiResponse(responseCode = "400", description = "Error on request")
			})
	@Operation(summary = "Update Create MatchOdd", description = "Update Create MatchOdd", requestBody = 
		@io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(schema = @Schema(implementation = MatchOddDto.class))))
	
	@RequestMapping(value = "/matchodds", method = { RequestMethod.POST, RequestMethod.PUT })
	public ResponseEntity<?> saveMatchOdd(@Valid @RequestBody MatchOddDto matchOddDto, Errors errors) {
		if (errors.hasErrors()) {
			return ResponseEntity.badRequest().body(ValidationErrorBuilder.fromBindingErrors(errors));
		}
		
		Optional<Long> id = matchOddService.save(matchOddDto);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")

				.buildAndExpand(id).toUri();
		return ResponseEntity.created(location).build();
	}

	
	
	/**
	 * Response for deleting matchodd
	 * @param id
	 * @return MatchOddDto
	 */
	
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Delete odd")
			})
	@Operation(summary = "Delete odd", description = "Delete odd")
	
	@DeleteMapping("/matchodds/{id}")
	public ResponseEntity<MatchOddDto> deleteMatchOdd(@PathVariable("id") Long id) {
		matchOddService.delete(id);
		return ResponseEntity.noContent().build();
	}

	
	
	
	/**
	 * Response for deleting all matchodds from match
	 * @param matchId
	 * @return
	 */
	
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Delete odds from match")
			})
	@Operation(summary = "Delete odds from match", description = "Delete odds from match")
	
	@DeleteMapping("/matchodds/match/{match_id}")
	public  ResponseEntity<MatchOddDto> deleteAllMatchOdd(@PathVariable("match_id") Long matchId) {
		matchOddService.deleteAllOddsFromMatch(matchId);
		return ResponseEntity.noContent().build();
	}

	@ExceptionHandler
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	/**
	 * Handle exceptions as Bad Requests
	 * @param exception
	 * @return ValidationError
	 */
	public ValidationError handleException(Exception exception) {
		logger.error(exception.getMessage());
		return new ValidationError(exception.getMessage());
	}
	
}
