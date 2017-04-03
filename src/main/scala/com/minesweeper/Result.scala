package com.minesweeper

sealed trait Result

case object MissionAccomplished extends Result

case object MissionFailed extends Result

case object MissionPending extends Result

