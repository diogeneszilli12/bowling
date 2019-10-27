package com.alenasoft.strategies;

import static org.junit.Assert.assertEquals;

import com.alenasoft.Frame;
import com.alenasoft.FrameOrganizer;
import com.alenasoft.ScoreStrategy;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

public class StrikeScoreStrategyTest {

  @Test
  public void testStrikeScoreForFirstFrame() {
    int targetFrameIndex = 1;
    List<Frame> frames = Arrays.asList(
        new Frame(targetFrameIndex, new String[] { "10" }),
        new Frame(2, new String[] { "7", "3" }),
        new Frame(3, new String[] { "9", "0" }));

    ScoreStrategy strategy = new StrikeScoreStrategy();
    strategy.score(targetFrameIndex, frames);
    Assert.assertEquals(20, FrameOrganizer.getByIndex(targetFrameIndex, frames).getScore());
  }

  @Test
  public void testStrikeScoreForFourthFrameJeffCase() {
    int targetFrameIndex = 4;
    Frame previousFrame = new Frame(3, new String[] { "9", "0" });
    previousFrame.setScore(48);

    List<Frame> frames = Arrays.asList(previousFrame,
        new Frame(targetFrameIndex, new String[] { "10" }),
        new Frame(5, new String[] { "0", "8" }));

    ScoreStrategy strategy = new StrikeScoreStrategy();
    strategy.score(targetFrameIndex, frames);
    assertEquals(66, FrameOrganizer.getByIndex(targetFrameIndex, frames).getScore());
  }

  @Test
  public void testStrikeScoreForEighthFrameJeffCase() {
    int targetFrameIndex = 8;
    Frame previousFrame = new Frame(7, new String[] { "0", "6" });
    previousFrame.setScore(90);

    List<Frame> frames = Arrays.asList(previousFrame,
        new Frame(targetFrameIndex, new String[] { "10" }),
        new Frame(9, new String[] { "10" }),
        new Frame(10, new String[] { "10", "8", "1" }));

    ScoreStrategy strategy = new StrikeScoreStrategy();
    strategy.score(targetFrameIndex, frames);
    assertEquals(120, FrameOrganizer.getByIndex(targetFrameIndex, frames).getScore());
  }

  @Test
  public void testStrikeScoreForNinethFrameJeffCase() {
    int targetFrameIndex = 9;
    Frame previousFrame = new Frame(8, new String[] { "10" });
    previousFrame.setScore(120);

    List<Frame> frames = Arrays.asList(previousFrame,
        new Frame(targetFrameIndex, new String[] { "10" }),
        new Frame(10, new String[] { "10", "8", "1" }));

    ScoreStrategy strategy = new StrikeScoreStrategy();
    strategy.score(targetFrameIndex, frames);
    assertEquals(148, FrameOrganizer.getByIndex(targetFrameIndex, frames).getScore());
  }

  @Test
  public void testStrikeScoreForThirdFrameJohnCase() {
    int targetFrameIndex = 3;
    Frame previousFrame = new Frame(2, new String[] { "6", "3" });
    previousFrame.setScore(25);

    List<Frame> frames = Arrays.asList(previousFrame,
        new Frame(targetFrameIndex, new String[] { "10" }),
        new Frame(4, new String[] { "8", "1" }));

    ScoreStrategy strategy = new StrikeScoreStrategy();
    strategy.score(targetFrameIndex, frames);
    assertEquals(44, FrameOrganizer.getByIndex(targetFrameIndex, frames).getScore());
  }

  @Test
  public void testStrikeScoreForFifthFrameJohnCase() {
    int targetFrameIndex = 5;
    Frame previousFrame = new Frame(4, new String[] { "8", "1" });
    previousFrame.setScore(53);

    List<Frame> frames = Arrays.asList(previousFrame,
        new Frame(targetFrameIndex, new String[] { "10" }),
        new Frame(6, new String[] { "10" }),
        new Frame(7, new String[] { "9", "0" }));

    ScoreStrategy strategy = new StrikeScoreStrategy();
    strategy.score(targetFrameIndex, frames);
    assertEquals(82, FrameOrganizer.getByIndex(targetFrameIndex, frames).getScore());
  }

  @Test
  public void testStrikeScoreForSixthFrameJohnCase() {
    int targetFrameIndex = 6;
    Frame previousFrame = new Frame(5, new String[] { "10" });
    previousFrame.setScore(82);

    List<Frame> frames = Arrays.asList(previousFrame,
        new Frame(targetFrameIndex, new String[] { "10" }),
        new Frame(7, new String[] { "9", "0" }));

    ScoreStrategy strategy = new StrikeScoreStrategy();
    strategy.score(targetFrameIndex, frames);
    assertEquals(101, FrameOrganizer.getByIndex(targetFrameIndex, frames).getScore());
  }

  @Test
  public void testStrikeScoreForFirstFrameOnPerfectGame() {
    int targetFrameIndex = 1;
    List<Frame> frames = Arrays.asList(
        new Frame(targetFrameIndex, new String[] { "10" }),
        new Frame(2, new String[] { "10" }),
        new Frame(3, new String[] { "10" }));

    ScoreStrategy strategy = new StrikeScoreStrategy();
    strategy.score(targetFrameIndex, frames);
    assertEquals(30, FrameOrganizer.getByIndex(targetFrameIndex, frames).getScore());
  }

  @Test
  public void testStrikeScoreForFifthFrameOnPerfect() {
    int targetFrameIndex = 5;
    Frame previousFrame = new Frame(4, new String[] { "10" });
    previousFrame.setScore(120);

    List<Frame> frames = Arrays.asList(previousFrame,
        new Frame(targetFrameIndex, new String[] { "10" }),
        new Frame(6, new String[] { "10" }),
        new Frame(7, new String[] { "10" }));

    ScoreStrategy strategy = new StrikeScoreStrategy();
    strategy.score(targetFrameIndex, frames);
    assertEquals(150, FrameOrganizer.getByIndex(targetFrameIndex, frames).getScore());
  }

  @Test
  public void testStrikeScoreForNinethFrameOnPerfect() {
    int targetFrameIndex = 9;
    Frame previousFrame = new Frame(8, new String[] { "10" });
    previousFrame.setScore(240);

    List<Frame> frames = Arrays.asList(previousFrame,
        new Frame(targetFrameIndex, new String[] { "10" }),
        new Frame(10, new String[] { "10", "10", "10" }));

    ScoreStrategy strategy = new StrikeScoreStrategy();
    strategy.score(targetFrameIndex, frames);
    assertEquals(270, FrameOrganizer.getByIndex(targetFrameIndex, frames).getScore());
  }
}
