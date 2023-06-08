package net.azarquiel.fmatic.model

data class Request(
                    val drivers: List<Drivers>,
                    val constructors: List<Constructors>,
                    val seasons: List<Seasons>,
                    val races: List<Races>
                )

data class Seasons( val season: Int )

data class Constructors( val season:Int, val team_id:Int, val team_name:String )

data class Drivers(
    val rank:Int,
    val points:Int,
    val firstname:String,
    val lastname:String
)

data class Races(
    val country:String,
    val end_date:String,
    val name:String,
    val race_id:Int,
    val season:String,
    val start_date:String,
    val status:String,
    val track:String,
)