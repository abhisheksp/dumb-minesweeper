package com.minesweeper

object Alias {
  type MineField = Array[Array[Option[Cell]]]

  def MineField(xs: Array[Option[Cell]]*) = Array(xs: _*)

  def mineFieldShow(mineField: MineField): String = {
    mineField.map(row => row.map {
      case None => "*"
      case Some(Mine) => "*"
      case Some(Flag) => "F"
    }.mkString(" ")
    ).mkString("\n")
  }
}