package org.raghav.fetch.challenge.pyramid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.HashSet;

/**
 * A service to detect whether a given word is a pyramid word.
 */
@Service
public class PyramidService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PyramidService.class);

  
     */
    public boolean isPyramidWord(String word) {
        // Split the string into 1-character parts, and add to a set (removing duplicates)
        HashSet<String> chars = new HashSet<>(Arrays.asList(word.split("")));

        HashSet<Integer> occurrences = determineCounts(word, chars);

      
        if(chars.size() == occurrences.size()) {
            for (int i = 1; i < chars.size() + 1; i++) {
                if(occurrences.contains(i)) {   // Used HashSet so this lookup is constant-time
                    LOGGER.debug("Letter in {} with {} occurrences found.", word, i);
                } else {
                    LOGGER.debug("NO letter in {} with {} occurrences found.", word, i);
                    return false;
                }
            }
        } else {
            LOGGER.debug("Distinct letter count was different from occurrence counts.  This indicates a duplicate count.");
            return false;
        }

        return true;
    }
     
    private static HashSet<Integer> determineCounts(String word, HashSet<String> chars) {
        HashSet<Integer> occurrences = new HashSet<>();
        chars.forEach((s) -> {
            int count = StringUtils.countOccurrencesOf(word, s);
            LOGGER.debug("Letter in word: {} - occurs {} times", s, count);
            occurrences.add(count);
        });

        return occurrences;
    }
}
