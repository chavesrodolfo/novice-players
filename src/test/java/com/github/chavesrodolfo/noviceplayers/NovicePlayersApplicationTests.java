package com.github.chavesrodolfo.noviceplayers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URL;
import java.util.ArrayList;

import com.github.chavesrodolfo.noviceplayers.controller.PlayerController;
import com.github.chavesrodolfo.noviceplayers.model.MessageResponse;
import com.github.chavesrodolfo.noviceplayers.model.PlayerRepresentation;
import com.github.chavesrodolfo.noviceplayers.model.PlayersRequest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ExtendWith(MockitoExtension.class)
class NovicePlayersApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private PlayerController playerController;

	@Test
	public void testHello() throws Exception {
		ResponseEntity<String> response = restTemplate
				.getForEntity(new URL("http://localhost:" + port + "/api/hello").toString(), String.class);
		assertEquals("Novice Players API", response.getBody());
	}

	@Test
	public void testPostPlayersIntoDB() throws Exception {

		ArrayList<PlayerRepresentation> playerRepresentations = new ArrayList<PlayerRepresentation>();
		playerRepresentations.add(new PlayerRepresentation("Sub zero", "expert"));
		PlayersRequest playersRequest = new PlayersRequest(playerRepresentations);

		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

		MessageResponse messageResponse = playerController.postPlayer(playersRequest);
         
		assertThat(messageResponse.getResult().get(0)).isEqualToIgnoringCase("player Sub zero stored in DB");
	
	}

	@Test
	public void testPostPlayersIntoKafkaTopic() throws Exception {

		ArrayList<PlayerRepresentation> playerRepresentations = new ArrayList<PlayerRepresentation>();
		playerRepresentations.add(new PlayerRepresentation("Scorpion", "novice"));
		PlayersRequest playersRequest = new PlayersRequest(playerRepresentations);

		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

		MessageResponse messageResponse = playerController.postPlayer(playersRequest);
         
		assertThat(messageResponse.getResult().get(0)).isEqualToIgnoringCase("player Scorpion sent to Kafka topic");
	
	}

	@Test
	public void testPostPlayersDidNotFit() throws Exception {

		ArrayList<PlayerRepresentation> playerRepresentations = new ArrayList<PlayerRepresentation>();
		playerRepresentations.add(new PlayerRepresentation("Reptile", "meh"));
		PlayersRequest playersRequest = new PlayersRequest(playerRepresentations);

		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

		MessageResponse messageResponse = playerController.postPlayer(playersRequest);
         
		assertThat(messageResponse.getResult().get(0)).isEqualToIgnoringCase("player Reptile did not fit");
	
	}

	@Test
	public void testPostPlayers3Size() throws Exception {

		ArrayList<PlayerRepresentation> playerRepresentations = new ArrayList<PlayerRepresentation>();
		playerRepresentations.add(new PlayerRepresentation("Sub zero", "expert"));
		playerRepresentations.add(new PlayerRepresentation("Scorpion", "novice"));
		playerRepresentations.add(new PlayerRepresentation("Reptile", "meh"));
		PlayersRequest playersRequest = new PlayersRequest(playerRepresentations);

		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

		MessageResponse messageResponse = playerController.postPlayer(playersRequest);
         
		assertThat(messageResponse.getResult().size()).isEqualTo(3);
	}

}
