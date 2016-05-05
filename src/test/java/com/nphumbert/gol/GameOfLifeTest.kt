package com.nphumbert.gol

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import java.util.*

class GameOfLifeTest {

    @Test
    fun should_die_when_less_than_two_live_neighbours() {
        val grid = HashMap<Position, Cell>()
        grid.put(Position(1, 1), Cell())
        grid.put(Position(2, 1), Cell())

        val gameOfLife = GameOfLife(grid)
        val newGeneration = gameOfLife.nextGeneration()

        val cell = newGeneration.get(Position(1, 1))

        assertThat(cell.alive).isEqualTo(false)
    }

}


