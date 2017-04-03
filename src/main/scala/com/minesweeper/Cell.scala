package com.minesweeper

sealed trait Cell

case object Mine extends Cell

case object Flag extends Cell