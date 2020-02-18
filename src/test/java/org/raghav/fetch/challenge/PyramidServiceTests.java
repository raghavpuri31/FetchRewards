package org.raghav.fetch.challenge;

import org.junit.Test;
import org.kondrak.fetch.challenge.pyramid.PyramidService;

import static org.junit.Assert.*;

/**
 * A test suite for the service layer of the application, which houses the primary logic for classifying pyramid words.
 */
public class PyramidServiceTests {

    private PyramidService pyramidService = new PyramidService();

    /**
     * Ensure the service behaves correctly when passed an empty string.
     */
    @Test
    public void pyramidServiceShouldFailGracefullyForEmptyString() {
        assertFalse(pyramidService.isPyramidWord(PyramidTestConstants.EMPTY_STRING));
    }

    /**
     * Ensure the service can correctly identify a pyramid word.
     */
    @Test
    public void pyramidServiceShouldReturnTrueForPyramidWord() {
        assertTrue(pyramidService.isPyramidWord(PyramidTestConstants.VALID_PYRAMID_WORD));
    }

    /**
     * Ensure the service can correctly rule out a non-pyramid word due to incorrect counts.
     */
    @Test
    public void pyramidServiceShouldReturnFalseForNonPyramidWordByCount() {
        assertFalse(pyramidService.isPyramidWord(PyramidTestConstants.INVALID_PYRAMID_WORD));
    }

    /**
     * Ensure the service can handle symbols where they match a pyramid word pattern.
     */
    @Test
    public void pyramidServiceShouldReturnTrueForSymbolPyramid() {
        assertTrue(pyramidService.isPyramidWord(PyramidTestConstants.SYMBOL_PYRAMID_WORD));
    }

    /**
     * Ensure the service can handle symbols where they do NOT match a pyramid word pattern.
     */
    @Test
    public void pyramidServiceShouldReturnFalseForSymbolNonPyramid() {
        assertFalse(pyramidService.isPyramidWord(PyramidTestConstants.SYMBOL_NON_PYRAMID_WORD));
    }

    /**
     * Ensure the service can handle sufficiently long pyramid words.
     */
    @Test
    public void pyramidServiceShouldReturnTrueForLongPyramid() {
        assertTrue(pyramidService.isPyramidWord(PyramidTestConstants.REALLY_LONG_PYRAMID_WORD));
    }

    /**
     * Ensure pyramid words can be detected even if shuffled.
     */
    @Test
    public void pyramidServiceShouldReturnTrueForShuffledPyramid() {
        assertTrue(pyramidService.isPyramidWord(PyramidTestConstants.VALID_SHUFFLED_PYRAMID_WORD));
    }

    /**
     * Ensure words containing only a single letter (by count) fail.
     */
    @Test
    public void pyramidServiceShouldReturnFalseForSingleLetterCountNonPyramid() {
        assertFalse(pyramidService.isPyramidWord(PyramidTestConstants.NON_PYRAMID_SINGLE_LETTER_COUNT));
    }

    /**
     * Ensure words containing only a single letter succeed.
     */
    @Test
    public void pyramidServiceShouldReturnTrueForSingleLetterPyramid() {
        assertTrue(pyramidService.isPyramidWord(PyramidTestConstants.NON_PYRAMID_SINGLE_LETTER));
    }
}
