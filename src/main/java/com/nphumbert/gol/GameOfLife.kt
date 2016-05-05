package com.nphumbert.gol

import java.util.Arrays.asList

class GameOfLife(val grid: Map<Position, Cell>) {

    fun nextGeneration(): GameOfLife =
            GameOfLife(
                    grid.mapValues {
                        if (numberOfLiveNeighbours(it.key) < 2) {
                            Cell(false)
                        } else {
                            it.value
                        }
                    }
            )

    fun get(position: Position): Cell = grid.getOrElse(position, { Cell(false) })

    private fun numberOfLiveNeighbours(position: Position): Int = position.neighbours().filter { get(it).alive }.count()

}

class Cell(val alive: Boolean = true) {

}

data class Position(val abs: Int, val ord: Int) {

    fun neighbours(): List<Position> =
            asList(
                    Position(abs.minus(1), ord.minus(1)),
                    Position(abs, ord.minus(1)),
                    Position(abs.plus(1), ord.minus(1)),
                    Position(abs.plus(1), ord),
                    Position(abs.plus(1), ord.plus(1)),
                    Position(abs, ord.plus(1)),
                    Position(abs.minus(1), ord.plus(1)),
                    Position(abs.minus(1), ord)
            )
}

