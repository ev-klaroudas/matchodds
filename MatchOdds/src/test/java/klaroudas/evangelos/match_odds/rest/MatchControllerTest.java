package klaroudas.evangelos.match_odds.rest;

import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.Test;
import org.springframework.http.MediaType;

import klaroudas.evangelos.match_odds.model.dto.MatchDto;



public class MatchControllerTest extends AbstractControllerTest {

	@Test
	public void newMatch() throws Exception {

		// given
		MatchDto matchDto = createTestMatchDto("OSFP", "PAOK");
		
		// when
		when(matchService.save(matchDto)).thenReturn(1L);

		// then
		mockMvc.perform(post("/api/v1/matches")
				.content(toJson(matchDto))
				.contentType(APPLICATION_JSON_UTF8)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
	}

	private MatchDto createTestMatchDto(String teamA, String teamB) {
		MatchDto matchDto = new MatchDto();
		matchDto.setDescription(teamA+"-"+teamB);
		matchDto.setMatchDate(LocalDate.now());
		matchDto.setMatchTime(LocalTime.now());
		matchDto.setSport("1");
		matchDto.setTeamA(teamA);
		matchDto.setTeamB(teamB);
		return matchDto;
	}
	
	
}
