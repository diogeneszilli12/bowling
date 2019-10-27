package com.alenasoft;

import static org.junit.Assert.assertEquals;

import com.alenasoft.strategies.SpareScoreStrategy;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;

public class SpareScoreStrategyTest {

  @Test
  public void testStrikeScoreForFirstFrameJohnCase() {
    int targetFrameIndex = 1;
    List<Frame> frames = Arrays.asList(
        new Frame(targetFrameIndex, new int[] { 3, 7 }),
        new Frame(2, new int[] { 6, 3 }),
        new Frame(3, new int[] { 10 }));

    ScoreStrategy strategy = new SpareScoreStrategy();
    strategy.score(targetFrameIndex, frames);
    assertEquals(16, FrameOrganizer.getByIndex(targetFrameIndex, frames).getScore());
  }

  @Test
  public void testStrikeScoreForEighthFrameJohnCase() {
    int targetFrameIndex = 8;
    Frame previousFrame = new Frame(7, new int[] { 9, 0 });
    previousFrame.setScore(110);

    List<Frame> frames = Arrays.asList(previousFrame,
        new Frame(targetFrameIndex, new int[] { 7, 3 }),
        new Frame(9, new int[] { 4, 4 }));

    ScoreStrategy strategy = new SpareScoreStrategy();
    strategy.score(targetFrameIndex, frames);
    assertEquals(124, FrameOrganizer.getByIndex(targetFrameIndex, frames).getScore());
  }

  @Test
  public void testStrikeScoreForSecondFrameJeffCase() {
    int targetFrameIndex = 2;
    Frame previousFrame = new Frame(1, new int[] { 10 });
    previousFrame.setScore(20);

    List<Frame> frames = Arrays.asList(previousFrame,
        new Frame(targetFrameIndex, new int[] { 7, 3 }),
        new Frame(3, new int[] { 9, 0 }));

    ScoreStrategy strategy = new SpareScoreStrategy();
    strategy.score(targetFrameIndex, frames);
    assertEquals(39, FrameOrganizer.getByIndex(targetFrameIndex, frames).getScore());
  }

  @Test
  public void testStrikeScoreForSixthFrameJeffCase() {
    int targetFrameIndex = 6;
    Frame previousFrame = new Frame(5, new int[] { 0, 8 });
    previousFrame.setScore(74);

    List<Frame> frames = Arrays.asList(previousFrame,
        new Frame(targetFrameIndex, new int[] { 8, 2 }),
        new Frame(7, new int[] { 0, 6 }));

    ScoreStrategy strategy = new SpareScoreStrategy();
    strategy.score(targetFrameIndex, frames);
    assertEquals(84, FrameOrganizer.getByIndex(targetFrameIndex, frames).getScore());
  }
}
