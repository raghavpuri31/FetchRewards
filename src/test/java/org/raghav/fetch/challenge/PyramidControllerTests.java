package org.raghav.fetch.challenge;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * A test suite to cover the /pyramid endpoint(s).
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PyramidControllerTests {

	@Autowired
	private MockMvc mockMvc;

	/**
	 * A test to ensure the /pyramid/{word} endpoint is callable.
	 */
	@Test
	public void pyramidEndpointIsCallable() throws Exception {
		this.mockMvc.perform(get("/pyramid/" + PyramidTestConstants.VALID_PYRAMID_WORD))
				.andExpect(status().isOk());
	}

	/**
	 * A test to ensure the /pyramid/{word} endpoint returns true for a word that is a pyramid word for the provided
	 * example data.
	 */
	@Test
	public void pyramidEndpointShouldReturnTrueForValidPyramidWord() throws Exception {
		this.mockMvc.perform(get("/pyramid/" + PyramidTestConstants.VALID_PYRAMID_WORD))
				.andExpect(status().isOk())
				.andExpect(content().string(String.valueOf(true)));
	}

    /**
     * A test to ensure the /pyramid/{word} endpoint returns false for a word that is NOT a pyramid word for the
     * provided example data.
     */
    @Test
    public void pyramidEndpointShouldReturnFalseForInvalidPyramidWord() throws Exception {
        this.mockMvc.perform(get("/pyramid/" + PyramidTestConstants.INVALID_PYRAMID_WORD))
                .andExpect(status().isOk())
                .andExpect(content().string(String.valueOf(false)));
    }

	/**
	 * A test to ensure the /pyramid/{word} endpoint returns false for words that have multiple letters with the same
	 * counts.
	 */
	@Test
	public void pyramidEndpointShouldReturnFalseForWordsWithDuplicateCounts() throws Exception {
		this.mockMvc.perform(get("/pyramid/" + PyramidTestConstants.NON_PYRAMID_BY_DUPLICATES))
				.andExpect(status().isOk())
				.andExpect(content().string(String.valueOf(false)));
	}

	/**
	 * Ensures the application responds with a 404 when no word is specified.
	 */
	@Test
	public void pyramidEndpointShouldReturn404ForEmptyString() throws Exception {
		this.mockMvc.perform(get("/pyramid/" + PyramidTestConstants.EMPTY_STRING))
				.andExpect(status().isNotFound());
	}
}
