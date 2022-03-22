package com.example.mtglifetrackerapp

data class PlayerData(val id : Int, var health : Int,
                      var isMonarch : Boolean, var poisonCounters : Int,
                      var energyCounters : Int, var expCounters : Int,
                      var bloodCounters : Int)
//TODO: add data representation for counters, commander damage,