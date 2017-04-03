package com.minesweeper

import com.minesweeper.Alias._

object Player {

  def flag(mineField: MineField, guess: (Int, Int)): MineField = {
    val newMineField = mineField
    newMineField(guess._1).update(guess._2, Some(Flag))
    newMineField
  }

  def Play(mineField: MineField, guess: (Int, Int)): (MineField, Attempt) = mineField(guess._1)(guess._2) match {
    case None => (flag(mineField, guess), SuccessfulAttempt)
    case Some(_) => (mineField, FailureAttempt)
  }

  def InferResult(mineField: MineField, attempt: Attempt): Result = attempt match {
    case FailureAttempt => MissionFailed
    case SuccessfulAttempt =>
      mineField.exists(
        row => row.contains(None)
      ) match {
        case true => MissionPending
        case false => MissionAccomplished
      }
  }

  def GameRunner(mineField: MineField): Unit = {
    println(mineFieldShow(mineField))
    println("Guess!")

    val row = scala.io.StdIn.readInt() - 1
    val col = scala.io.StdIn.readInt() - 1
    val (newGame, attempt) = Play(mineField, (row, col))
    val result: Result = InferResult(mineField, attempt)

    result match {
      case MissionPending => GameRunner(newGame)
      case MissionFailed => println("Mission Failed")
      case MissionAccomplished => println("Mission Accomplished")
    }
  }

  def main(args: Array[String]): Unit = {
    val mineField = Alias.MineField(
      Array[Option[Cell]](Some(Mine), None, None, Some(Mine)),
      Array[Option[Cell]](Some(Mine), None, Some(Mine), Some(Mine)),
      Array[Option[Cell]](Some(Mine), Some(Mine), Some(Mine), None),
      Array[Option[Cell]](None, Some(Mine), Some(Mine), Some(Mine))
    )

    GameRunner(mineField)
  }
}
