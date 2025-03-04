package cz.mendelu.xzirchuk.project.ui

import cz.mendelu.xzirchuk.project.ui.screens.destinations.ChartStatScreenDestination
import cz.mendelu.xzirchuk.project.ui.screens.destinations.DestinationsListScreenDestination
import cz.mendelu.xzirchuk.project.ui.screens.destinations.DirectionDestination
import cz.mendelu.xzirchuk.project.ui.screens.destinations.MapScreenDestination
import cz.mendelu.xzirchuk.project.ui.screens.destinations.TextRecogntionScreenDestination


sealed class Destinations(val route: DirectionDestination) {
    class DestinationsList:Destinations(route = DestinationsListScreenDestination)
    class Map:Destinations(route = MapScreenDestination )
    class Camera:Destinations(route = TextRecogntionScreenDestination )
    class Dashboard:Destinations(route = ChartStatScreenDestination )



}