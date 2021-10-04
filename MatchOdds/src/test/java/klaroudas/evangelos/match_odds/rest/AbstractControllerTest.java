package klaroudas.evangelos.match_odds.rest;



import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.google.gson.Gson;

import klaroudas.evangelos.match_odds.service.MatchOddService;
import klaroudas.evangelos.match_odds.service.MatchService;


@RunWith(SpringRunner.class)
@WebMvcTest
public abstract class AbstractControllerTest {

	private static  Gson gson = new Gson();  
	
	@Autowired
	protected MockMvc mockMvc;

	@MockBean
	protected MatchService matchService;

	@MockBean
	protected  MatchOddService matchOddService;

	@Before
	public void setUp() {
		Mockito.reset(matchService, matchOddService);
	}
	
	public static String toJson (Object obj) {
		 
		 return gson.toJson(obj);
	}

}