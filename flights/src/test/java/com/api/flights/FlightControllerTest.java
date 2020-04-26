package com.api.flights;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.api.flights.controller.FlightController;
import com.api.flights.exception.ItineraryException;
import com.api.flights.exception.ItineraryNotFoundException;
import com.api.flights.model.Itinerary;
import com.api.flights.model.ItineraryDateTime;
import com.api.flights.model.Passenger;
import com.api.flights.service.FlightService;
import com.fasterxml.jackson.databind.ObjectMapper;

import junit.framework.TestCase;
import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;
import mockit.integration.junit4.JMockit;


@RunWith(JMockit.class)
@SpringBootTest
public class FlightControllerTest extends TestCase {

	private static final String ITIERARY_CREATED_RESPONSE = "{\"itieraryId\":\"5ea4f29b7df6e5106f7ba777\"}";

	private static final String ERROR_ITINERARY_COULD_NOT_BE_CREATED = "{\"error\":\"Itinerary could not be created\"}";

	private static final String CREATED_ITINERARY_ID = "5ea4f29b7df6e5106f7ba777";

	private static final String ITINERARY_ERROR_RESPONSE = "{\"error\":\"Itinerary Error\"}";

	private static final String ITINERARY_NOT_FOUND_RESPONSE = "{\"error\":\"Itinerary not found\"}";

	private static final String ITINERAY_RESPONSE_OK = "{\"itinerary\":{\"id\":\"5ea4f29b7df6e5106f7ba61e\",\"departure\":{\"date\":1587783600000,\"time\":\"12:30\"},\"arrival\":{\"date\":1587783600000,\"time\":\"19:22\"},\"passenger\":{\"name\":\"Agustin Lacomba\",\"age\":35,\"hasLuggage\":true},\"originCity\":\"BUE\",\"destinationCity\":\"MIA\",\"price\":1000}}";

	private static final String ITINERARY_ID = "5ea4f29b7df6e5106f7ba61e";

	@Injectable
	private FlightService flightService; 

	@Tested(availableDuringSetup = true)
	private FlightController flightController;

	private MockMvc mockMvc;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(flightController).build();
	}

	@Test
	public void retrieveItineraryOK() throws Exception {
		Itinerary itinerary = createItineraryFor(ITINERARY_ID);
		new Expectations() {{
			flightService.retrieveFlightItinerary(ITINERARY_ID);
			result = itinerary;
		}};		

		MvcResult result = this.mockMvc.perform(get("/flight/itinerary/5ea4f29b7df6e5106f7ba61e"))
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn();

		String content = result.getResponse().getContentAsString();
		assertEquals(content, ITINERAY_RESPONSE_OK);
	}

	@Test
	public void retrieveItineraryNotFound() throws Exception {
		new Expectations() {{
			flightService.retrieveFlightItinerary(ITINERARY_ID);
			result =  new ItineraryNotFoundException(String.format("Itinerary with id: %s not found", ITINERARY_ID));
		}};		

		MvcResult result = this.mockMvc.perform(get("/flight/itinerary/5ea4f29b7df6e5106f7ba61e"))
				.andDo(print())
				.andExpect(status().isNotFound())
				.andReturn();

		String content = result.getResponse().getContentAsString();
		assertEquals(content, ITINERARY_NOT_FOUND_RESPONSE);
	}

	@Test
	public void retrieveItineraryInternalServerError() throws Exception {
		new Expectations() {{
			flightService.retrieveFlightItinerary(ITINERARY_ID);
			result =  new ItineraryException(String.format("Itinerary with id: %s throwed an error", ITINERARY_ID));
		}};		

		MvcResult result = this.mockMvc.perform(get("/flight/itinerary/5ea4f29b7df6e5106f7ba61e"))
				.andDo(print())
				.andExpect(status().isInternalServerError())
				.andReturn();

		String content = result.getResponse().getContentAsString();
		assertEquals(content, ITINERARY_ERROR_RESPONSE);
	}

	@Test
	public void createItineraryOK() throws Exception {
		Itinerary itinerary = createItinerary();
		new Expectations() {{
			flightService.createFlightItinerary((Itinerary) any);
			result = CREATED_ITINERARY_ID;
		}};		

		String jsonStr = itineraryToJson(itinerary); 

		MvcResult result = this.mockMvc.perform(post("/flight/create").contentType(MediaType.APPLICATION_JSON).content(jsonStr))
				.andExpect(status().isCreated()).andExpect(content()
						.contentType("application/json")).andReturn();

		String content = result.getResponse().getContentAsString();
		assertEquals(content, ITIERARY_CREATED_RESPONSE);
	}

	@Test
	public void createItineraryError() throws Exception {
		Itinerary itinerary = createItinerary();
		new Expectations() {{
			flightService.createFlightItinerary((Itinerary) any);
			result = new ItineraryException("Error Creating itinerary");
		}};		

		String jsonStr = itineraryToJson(itinerary); 

		MvcResult result = this.mockMvc.perform(post("/flight/create").contentType(MediaType.APPLICATION_JSON).content(jsonStr))
				.andExpect(status().isInternalServerError()).andExpect(content()
						.contentType("application/json")).andReturn();

		String content = result.getResponse().getContentAsString();
		assertEquals(content, ERROR_ITINERARY_COULD_NOT_BE_CREATED);
	}

	private String itineraryToJson(Itinerary itinerary) {
		ObjectMapper Obj = new ObjectMapper(); 
		String jsonStr = "";

		try { 
			jsonStr = Obj.writeValueAsString(itinerary); 
		}

		catch (IOException e) { 
			e.printStackTrace(); 
		}
		return jsonStr;
	}


	private Itinerary createItineraryFor(String id) {
		Itinerary itinerary = this.createItinerary();
		itinerary.setId(id);		
		return itinerary;
	}

	private Itinerary createItinerary() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateInString = "2020-04-25";
		Itinerary itinerary = null;
		try {

			Date date = formatter.parse(dateInString);
			ItineraryDateTime arrival = new ItineraryDateTime(date, "19:22");
			ItineraryDateTime departure = new ItineraryDateTime(date, "12:30");
			Passenger passenger = new Passenger("Agustin Lacomba", 35, true);

			itinerary = new Itinerary(departure, arrival, passenger, "BUE", "MIA", new BigDecimal(1000));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		itinerary.setId(null);
		return itinerary;
	}
}