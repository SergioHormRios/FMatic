package net.azarquiel.fmatic.model

data class Request(
    val drivers: List<Drivers>,
    val driverDetail: List<DriverDetail>,
    val hallOfFame: List<HallOfFames>,
    val teams: List<Teams>,
    val teamDetail: List<TeamDetail>,
    val raceCalendar: List<RaceCalendar>
                )

//Driver
data class Drivers(val rank:Int, val points:Int, val firstname: String, val lastname: String )

data class DriverDetail(
    val team:String,
    val country:String,
    val podiums:String,
    val points:String,
    val grandsPrixEntered:String,
    val worldChampionships:String,
    val highestRaceFinish:String,
    val highestGridPosition:String,
    val dateOfBirth:String,
    val placeOfBirth:String,
    val firstname:String,
    val lastname:String
)

data class HallOfFames(val driver: String )

//Team
data class Teams(val rank:Rank, val points:Point, val teamName: String, val drivers: List<Drivers>)
data class Rank(val standing:Int)
data class Point(val pts:Int)
data class TeamDetail(
    val base: String,
    val teamChief:String,
    val technicalChief:String,
    val chassis:String,
    val firstTeamEntry:String,
    val worldChampionships:String,
    val polePositions:String,
    val fastestLaps:String,
    val teamName:String
)

//Race
data class RaceCalendar(
    val status: String,
    val startDate: String,
    val endDate: String,
    val summary: String,
    val location: String,

)