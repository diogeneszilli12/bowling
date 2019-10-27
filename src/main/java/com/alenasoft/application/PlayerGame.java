package com.alenasoft.application;

import com.alenasoft.application.exceptions.InvalidInputScoreException;
import com.alenasoft.application.strategies.ScoreStrategyProvider;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PlayerGame {
  final private String name;
  final private List<String> inputScores;
  private List<Frame> frames;

  public PlayerGame(String name) {
    this(name, new ArrayList<>());
  }

  public PlayerGame(String name, List<String> inputScores) {
    this.name = name;
    this.inputScores = inputScores;
    this.calculateScores();
  }

  public void calculateScores() {
    try {
      this.frames = FrameOrganizer.organize(this.inputScores);
    } catch (InvalidInputScoreException e) {
      this.frames.forEach(f -> f.setScore(0));
    }

    this.computeScore();
  }

  public List<String> getInputScores() {
    return this.inputScores;
  }

  public String getName() {
    return this.name;
  }

  private String pinfallsRowToPrint() {
    String rowName = "Pinfalls";
    String framePoints =
        this.frames.stream().map(f -> {
          try {
            return f.pointsToPrint();
          } catch (InvalidInputScoreException e) {
            return "Invalid points detected";
          }
        }).collect(Collectors.joining("\t\t"));

    return String.join("\t", rowName, framePoints);
  }

  private String scoresRowToPrint() {
    String rowName = "Score";
    String frameScores =
        this.frames
            .stream()
            .map(f -> String.format("%5d", f.getScore()))
            .collect(Collectors.joining("\t\t"));

    return String.join("\t\t", rowName, frameScores);
  }

  @Override
  public String toString() {
    String nameWithTag = this.name.concat(!this.isValid() ? " [Invalid Game]" : "");
    return String.join("\n", nameWithTag,
        this.pinfallsRowToPrint(), this.scoresRowToPrint());
  }

  public boolean isValid() {
    return this.frames.size() == 10;
  }

  public void computeScore() {
    if (!this.isValid()) {
      this.frames.forEach(f -> f.setScore(0));
      return;
    }

    this.frames.forEach(f -> ScoreStrategyProvider.provideFor(f)
          .score(f.getIndex(), this.frames));
  }
}