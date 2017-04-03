package com.minesweeper

object Alias {
  type MineField = Array[Array[Option[Cell]]]
  def MineField(xs: Array[Option[Cell]]*) = Array(xs: _*)
}