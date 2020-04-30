package com.davidbevan.challenge26

val rotateWholeCubeBy90DegMappings = mapOf(1 to 7, 2 to 4, 3 to 1, 4 to 8, 6 to 2, 7 to 9, 8 to 6, 9 to 3, //Front
                                                      10 to 12, 11 to 15, 12 to 18, 13 to 11, 15 to 17, 16 to 10, 17 to 13, 18 to 16, //Back
                                                      19 to 52, 20 to 49, 21 to 46, 22 to 53, 23 to 50, 24 to 47, 25 to 54, 26 to 51, 27 to 48, //Left
                                                      28 to 43, 29 to 40, 30 to 37, 31 to 44, 32 to 41, 33 to 38, 34 to 45, 35 to 42, 36 to 39, //Right
                                                      37 to 25, 38 to 22, 39 to 19, 40 to 26, 41 to 23, 42 to 20, 43 to 27, 44 to 24, 45 to 21, //Top
                                                      46 to 34, 47 to 31, 48 to 28, 49 to 35, 50 to 32, 51 to 29, 52 to 36, 53 to 33, 54 to 30)  //Bottom


val rotateTopClockwiseMappings = mapOf(1 to 28, 2 to 29, 3 to 30, //Front
                                                  10 to 19, 11 to 20, 12 to 21, //Back
                                                  19 to 1, 20 to 2, 21 to 3, //Left
                                                  28 to 10, 29 to 11, 30 to 12, //Right
                                                  37 to 43, 38 to 40, 39 to 37, 40 to 44, 42 to 38, 43 to 45, 44 to 42, 45 to 39) //Top
                                                  //No Change to Bottom

fun rotateCube(faces: List<String>, layer: String, direction: String): List<String> {
    var flattenedFaced = faces.joinToString(separator = "", prefix = "", postfix = "") { it }.toCharArray()
    val rotateTimes = when (layer) {
        "Top" -> 0
        "Bottom" -> 2
        "Left" -> 1
        "Right" -> 3
        else -> 0
    }
    var rotatedFaces = rotateWholeCubeBy90Deg(flattenedFaced, rotateTimes)
    if (direction == "CW") rotatedFaces = rotateTopRowClockwise(rotatedFaces)
    if (direction == "CCW") rotatedFaces = rotateTopRowClockwise(rotatedFaces, 3)
    //return rotateWholeCubeBy90Deg(rotatedFaces, 4 - rotateTimes).toFaces()
    return rotateWholeCubeBy90Deg(rotatedFaces, 0).toFaces()
}

fun rotateWholeCubeBy90Deg(faces: CharArray, times: Int = 1) = moveSquares(faces, rotateWholeCubeBy90DegMappings, times)

fun rotateTopRowClockwise(faces: CharArray, times: Int = 1) = moveSquares(faces, rotateTopClockwiseMappings, times)

fun moveSquares(faces: CharArray, mappings: Map<Int,Int>, times: Int = 1): CharArray {
    var newFaces = faces.copyOf()
    var countDown = times
    while (countDown-- > 0) {
        var oldFaces = newFaces.copyOf()
        mappings.forEach { new, old ->
            newFaces[new-1] = oldFaces[old-1]
        }
    }
    return newFaces
}


fun CharArray.toFaces(): List<String> {

    var faces = listOf<String>()
    var face = ""
    this.mapIndexed{ index, c ->
        if ((index + 1).rem(9) == 0) {
            face += c
            faces = faces.plus(face)
            face = ""
        } else {
            face += c
        }
    }
    return faces
}
