package com.davidbevan.challenge13

object LocationLoader {
    fun loadLocations(rawLocations: String): MutableList<Location> {
        var locations = mutableListOf<Location>()
        val locationElementArray = rawLocations.split(",")
        var name: String = ""
        var long: Float = 0.toFloat()
        var lat: Float
        locationElementArray.mapIndexed() { index, element ->
            var elementPointer = index%4
            if(elementPointer == 0) name = element
            if(elementPointer == 2) long = element.toFloat()
            if(elementPointer == 3) {
                lat = element.toFloat()
                locations.add(Location(name, long, lat))
            }
        }
        return locations
    }
}