package cz.mendelu.xzirchuk.project.models

data class APILocation(

     var placeId     : String?           = null,
     var licence     : String?           = null,
     var osmType     : String?           = null,
     var osmId       : String?           = null,
     var lat         : String?           = null,
     var lon         : String?           = null,
     var displayName : String?           = null,
     var address     : Address?          = Address(),
)