package com.alenasoft.application.defaults;

import com.alenasoft.application.ScoreParser;
import com.alenasoft.application.exceptions.InvalidInputScoreException;
import com.alenasoft.commons.GameConstants;
import java.util.Objects;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DefaultScoreParser implements ScoreParser {

  private static final Logger log = LogManager.getLogger();

  private final String warningTemplate = "Invalid Score found [%s], this invalidates the PlayerGame";

  @Override
  public int parseToNumericScore(String inputScore)
      throws InvalidInputScoreException {
    if (Objects.isNull(inputScore)) {
      fireInvalidInputException("null");
      return GameConstants.minPinfall;
    }

    final String sanitizedInputScore = inputScore.trim().toUpperCase();
    if (GameConstants.fault.equals(sanitizedInputScore)) {
      return GameConstants.minPinfall;
    }

    try {
      final int score = Integer.parseInt(sanitizedInputScore);
      if (score < GameConstants.minPinfall || score > GameConstants.maxPinfall) {
        fireInvalidInputException(sanitizedInputScore);
      }
      return score;
    } catch (NumberFormatException exception) {
      fireInvalidInputException(sanitizedInputScore);
    }
    return GameConstants.minPinfall;
  }

  private void fireInvalidInputException(String inputScore) throws InvalidInputScoreException {
    log.info(String.format(warningTemplate, inputScore));
    throw new InvalidInputScoreException(String.format(warningTemplate, inputScore));
  }
}
