package com.minesweeper

import com.minesweeper.Alias.MineField
import org.scalatest._

class Player$Test extends FlatSpec with Matchers {
  "Play" should "return failure attempt on touching mine" in {
    val mineField = Alias.MineField(
      Array[Option[Cell]](Some(Mine), None, None, Some(Mine)),
      Array[Option[Cell]](Some(Mine), Some(Mine), Some(Mine), None),
      Array[Option[Cell]](None, Some(Mine), Some(Mine), Some(Mine))
    )

    val (newMineField, attempt) = Player.Play(mineField, (0, 0))

    newMineField shouldEqual mineField
    attempt shouldEqual FailureAttempt
  }

  "Play" should "return success attempt on empty cell" in {
    val mineField = Alias.MineField(
      Array[Option[Cell]](Some(Mine), None, None, Some(Mine)),
      Array[Option[Cell]](Some(Mine), Some(Mine), Some(Mine), None),
      Array[Option[Cell]](None, Some(Mine), Some(Mine), Some(Mine))
    )

    val flaggedMineField = Alias.MineField(
      Array[Option[Cell]](Some(Mine), Some(Flag), None, Some(Mine)),
      Array[Option[Cell]](Some(Mine), Some(Mine), Some(Mine), None),
      Array[Option[Cell]](None, Some(Mine), Some(Mine), Some(Mine))
    )

    val (newMineField, attempt) = Player.Play(mineField, (0, 1))

    newMineField shouldEqual flaggedMineField
    attempt shouldEqual SuccessfulAttempt
  }

  "Flag" should "flag the given cell" in {
    val mineField = Alias.MineField(
      Array[Option[Cell]](Some(Mine), None, None, Some(Mine)),
      Array[Option[Cell]](Some(Mine), Some(Mine), Some(Mine), None),
      Array[Option[Cell]](None, Some(Mine), Some(Mine), Some(Mine))
    )

    val flaggedMineField = Alias.MineField(
      Array[Option[Cell]](Some(Mine), Some(Flag), None, Some(Mine)),
      Array[Option[Cell]](Some(Mine), Some(Mine), Some(Mine), None),
      Array[Option[Cell]](None, Some(Mine), Some(Mine), Some(Mine))
    )

    val newMineField = Player.flag(mineField, (0, 1))

    newMineField shouldEqual flaggedMineField
  }

  "InferResult" should "infer mission failed on failure attempt" in {
    val result = Player.InferResult(MineField(), FailureAttempt)
    result shouldEqual MissionFailed
  }

  "InferResult" should "infer mission accomplished on no mines" in {
    val result = Player.InferResult(MineField(), SuccessfulAttempt)
    result shouldEqual MissionAccomplished
  }

  "InferResult" should "infer mission accomplished on presence of mines" in {
    val mineField = MineField(
      Array[Option[Cell]](Some(Mine), None, Some(Mine), Some(Mine)),
      Array[Option[Cell]](Some(Mine), Some(Mine), Some(Mine), None),
      Array[Option[Cell]](None, Some(Mine), Some(Mine), Some(Mine))
    )

    val result = Player.InferResult(mineField, SuccessfulAttempt)

    result shouldEqual MissionPending
  }
}
