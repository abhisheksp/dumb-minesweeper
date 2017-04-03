package com.minesweeper

import com.minesweeper.Alias.MineField

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
        row => row.contains(Some(Mine))
      ) match {
        case true => MissionPending
        case false => MissionAccomplished
      }
  }
}
