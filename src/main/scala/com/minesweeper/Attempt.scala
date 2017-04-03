package com.minesweeper

sealed trait Attempt

case object SuccessfulAttempt extends Attempt

case object FailureAttempt extends Attempt