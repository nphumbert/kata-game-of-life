package com.nphumbert.gol

import java.util.*
import java.util.Arrays.asList

class GameOfLife {

    private val grid: MutableMap<Position, Cell>
    private val dimension: Dimension

    constructor(grid: Map<Position, Cell>, dimension: Dimension) {
        this.grid = HashMap<Position, Cell>(grid)
        this.dimension = dimension

        completeGridWithDeadCells(dimension, grid)
    }

    private fun completeGridWithDeadCells(dimension: Dimension, grid: Map<Position, Cell>) {
        for (i in 1..dimension.width) {
            for (j in 1..dimension.height) {
                if (!grid.containsKey(Position(i, j))) {
                    this.grid.put(Position(i, j), Cell(false))
                }
            }
        }
    }

    fun nextGeneration(): GameOfLife =
            GameOfLife(
                    grid.mapValues {
                        val numberOfLiveNeighbours = numberOfLiveNeighbours(it.key)
                        if (it.value.alive && (numberOfLiveNeighbours < 2 || numberOfLiveNeighbours > 3)) {
                            Cell(false)
                        } else if (!it.value.alive && numberOfLiveNeighbours == 3) {
                            Cell(true)
                        } else {
                            it.value
                        }
                    },
                    dimension
            )

    private fun numberOfLiveNeighbours(position: Position): Int = position.neighbours().filter { get(it).alive }.count()

    fun get(position: Position): Cell = grid.getOrElse(position, { Cell(false) })

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

data class Dimension(val width: Int, val height: Int) {

}

