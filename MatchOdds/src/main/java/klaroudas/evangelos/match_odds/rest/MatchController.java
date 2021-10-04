package klaroudas.evangelos.match_odds.rest;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.ArraySchema;


import klaroudas.evangelos.match_odds.model.dto.MatchDto;
import klaroudas.evangelos.match_odds.service.MatchService;

/**
 * Controller REST API class to expose @see MatchService
 * @author baggelis
 *
 */
@RestController
@RequestMapping("api/v1")
public class MatchController {

	private final MatchService matchService;

	private Logger logger = Logger.getLogger(MatchController.class);

	@Autowired
	public MatchController(MatchService matchService) {
		this.matchService = matchService;
	}
	
	
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "All matches.", 
					content = @Content(array = @ArraySchema(schema = @Schema(implementation = MatchDto.class)))) })
	@Operation(summary = "Get all matches", description = "Get all matches ")
	
	@GetMapping("/matches")
	/**
	 * creates Response for matches list
	 * @return List<MatchDto>
	 */
	public ResponseEntity<List<MatchDto>> getMatches() {
			return ResponseEntity.ok(matchService.getMatches());
	}

	/**
	 * creates Response for match
	 * @return MatchDto
	 */
	
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Return match.", 
					content = @Content(schema = @Schema(implementation = MatchDto.class))),
			@ApiResponse(responseCode = "404", description = "Not found match")})
	@Operation(summary = "Return match", description = "Return match ")
	
	@GetMapping("/matches/{match_id}")
	public ResponseEntity<MatchDto> getMatchEntityById(@PathVariable("match_id") Long matchId) {
		Optional<MatchDto> matchDto = matchService.findById(matchId);
		if (matchDto.isPresent())
			return ResponseEntity.ok(matchDto.get());
		return ResponseEntity.notFound().build();
	}

	/**
	 * Response for creating updating Match
	 * @param matchDto
	 * @param errors
	 * @return
	 */
	
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Update Create Match."), 
			@ApiResponse(responseCode = "400", description = "Error on request")
			})
	@Operation(summary = "Update Create Match", description = "Update Create Match", requestBody = 
		@io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(schema = @Schema(implementation = MatchDto.class))))
	
	@RequestMapping(value = "/matches", method = { RequestMethod.POST, RequestMethod.PUT })
	public ResponseEntity<?> createMatchEntity(@Valid @RequestBody MatchDto matchDto, Errors errors) {
		if (errors.hasErrors()) {
			return ResponseEntity.badRequest().body(ValidationErrorBuilder.fromBindingErrors(errors));
		}
		Long id = matchService.save(matchDto);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")

				.buildAndExpand(id).toUri();
		return ResponseEntity.created(location).build();
	}

	/**
	 * Response for deleting match
	 * @param MatchDto
	 * @return
	 */
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Delete match")
			})
	@Operation(summary = "Delete match", description = "Delete match")
	
	@DeleteMapping("/matches/{match_id}")
	public ResponseEntity<MatchDto> deleteMatchEntity(@PathVariable("match_id") Long matchId) {
		matchService.delete(matchId);
		return ResponseEntity.noContent().build();
	}

	/**
	 * Handles controller's exception
	 * @param exception
	 * @return ValidationError
	 * @see ValidationError
	 */
	
	@ExceptionHandler
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ValidationError handleException(Exception exception) {
		logger.error(exception.getMessage());
		return new ValidationError(exception.getMessage());
	}
}
