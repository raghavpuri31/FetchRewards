package org.raghav.fetch.challenge.pyramid;

import org.apache.tomcat.jni.Local;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

/**
 * A controller to handle routes for pyramid word tests.
 */
@RestController
@RequestMapping("/pyramid")
public class PyramidController {
    Logger LOGGER = LoggerFactory.getLogger(PyramidController.class);

    private PyramidService pyramidService;

    public PyramidController(PyramidService pyramidService) {
        this.pyramidService = pyramidService;
    }

    @RequestMapping("/{word}")
    public boolean testWord(@PathVariable String word) {
        // log time prior to starting processing (could use Spring AOP for this, but that's kind of overkill for this)
        LocalTime start = LocalTime.now();
        LOGGER.info("Processing: {} - {}", word, start);

        boolean isPyramid = pyramidService.isPyramidWord(word);

        // log time, and duration following processing so performance of runs can be compared
        LocalTime end = LocalTime.now();
        long minutes = start.until( end, ChronoUnit.MINUTES);
        long seconds = start.until( end, ChronoUnit.SECONDS);
        long nanos = start.until( end, ChronoUnit.NANOS);
        LOGGER.info("End processing! - at {} - {}m {}s {}n", end, minutes, seconds, nanos);

        return isPyramid;
    }
}
