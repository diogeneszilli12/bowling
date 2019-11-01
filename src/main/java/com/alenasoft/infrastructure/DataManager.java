package com.alenasoft.infrastructure;

import com.alenasoft.application.PlayerGame;
import com.alenasoft.commons.GameConstants;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DataManager {

  public static Logger log = LogManager.getLogger();

  private List<PlayerGame> playerGames;

  public DataManager() {
    this.playerGames = new ArrayList<>();
  }

  public void processLine(String nextLine) {
    if (Objects.isNull(nextLine) || nextLine.isEmpty()) {
      log.warn("Empty ROW skipped from input data text file");
      return;
    }

    String sanitized =
        nextLine
            .trim()
            .toUpperCase()
            .replaceAll("\\t", " ") // Replace tabs with space
            .replaceAll("(\\s)+", " "); // Normalize spaces

    String[] values = sanitized.split(" ");
    if (values.length < 1 || values.length > 2) {
      log.error(String.format("Invalid row input: [%s] cannot processed", nextLine));
      System.exit(-1);
    }
    this.addOrUpdate(values[0], values[1]);
  }

  public void processData() {
    this.playerGames.forEach(PlayerGame::calculateScores);
  }

  @Override
  public String toString() {
    return String.join(
        "\n",
        this.frameRowToPrint(),
        this.playerGames.stream().map(PlayerGame::toString).collect(Collectors.joining("\n")));
  }

  private String frameRowToPrint() {
    String headerRow = "Frame\t\t";
    for (int i = 1; i <= GameConstants.maxFramesLength; i++) {
      headerRow = headerRow.concat(String.format("%5d\t\t", i));
    }
    return headerRow;
  }

  private void addOrUpdate(String playerName, String inputPoint) {
    PlayerGame playerGame =
        this.findByPlayerName(playerName)
            .orElseGet(
                () -> {
                  PlayerGame newPlayerGame = new PlayerGame(playerName);
                  this.playerGames.add(newPlayerGame);
                  return newPlayerGame;
                });
    playerGame.getInputScores().add(inputPoint);
  }

  private Optional<PlayerGame> findByPlayerName(String playerName) {
    return this.playerGames.stream().filter(pg -> playerName.equals(pg.getName())).findFirst();
  }
}
