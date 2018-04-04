package com.github.mielek.mazesolver;

import org.junit.Ignore;
import org.junit.Test;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * MazeLoadingTest
 */
public class MazeLoadingTest {

    @Test
    @Ignore
    public void loadMazeFromCharStream(){
        Stream<Character> charStream = Stream.of('\n');
        Maze maze = MazeLoader.load(charStream);
        assertThat(maze).isNotNull();
    }
    
}