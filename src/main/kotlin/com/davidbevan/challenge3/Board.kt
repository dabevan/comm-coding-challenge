package com.davidbevan.challenge3

class Board {

    companion object {

        var sokobanItems: Array<SokobanItem> = arrayOf()
        var player: SokobanItem = Player(1,1)
        var boardWidth = 0
        var boardHeight = 0


        fun createBoard(boardString: Array<String>) {
            var x: Int
            var y = 0
            sokobanItems = arrayOf()
            boardString.map {
                boardRow ->
                y++
                x=0
                boardHeight = y
                boardRow.map { boardItem ->
                    x++
                    boardWidth = x
                    when (boardItem) {
                     '#' -> sokobanItems = sokobanItems.plus(Wall(x, y))
                     'b' -> sokobanItems = sokobanItems.plus(Box(x, y))
                     'B' -> {
                            sokobanItems = sokobanItems.plus(Box(x, y))
                            sokobanItems = sokobanItems.plus(StorageLocation(x, y))
                            }
                     '*' -> sokobanItems = sokobanItems.plus(StorageLocation(x, y))
                     'p' -> {
                            player = Player(x, y)
                            sokobanItems = sokobanItems.plus(player)
                            }
                     'P' -> {
                            player = Player(x, y)
                            sokobanItems = sokobanItems.plus(player)
                            sokobanItems = sokobanItems.plus(StorageLocation(x, y))
                            }
                     else -> { }
                 }
                }
            }
        }


        fun getSokobanItemsInLocation(x:Int, y:Int): List<SokobanItem> {
            return sokobanItems.filter { it.x == x && it.y == y}
        }


        fun isChallengeComplete(): Boolean {
            val storageLocations = sokobanItems.filter { it.type == "StorageLocation"}
            storageLocations.map {
                val itemsAtStorageLocations = getSokobanItemsInLocation(it.x, it.y)
                if (itemsAtStorageLocations.size == 1) { return false }
                if (itemsAtStorageLocations.size == 2) { if(itemsAtStorageLocations.first().type != "Box" &&
                                                           itemsAtStorageLocations.last().type != "Box") { return false }}
            }
            return true
        }


        fun toStringArray(): Array<String> {
            val outputBoardStringArray = Array(this.boardHeight) { "" }
            var itemsInLocation :List<SokobanItem>
            for (y in 0 until boardHeight) {
                for (x in 0 until boardWidth) {
                    itemsInLocation = getSokobanItemsInLocation(x+1,y+1)
                    if (itemsInLocation.size == 0) { outputBoardStringArray[y] = outputBoardStringArray[y] + " " }
                    if (itemsInLocation.size == 1) { outputBoardStringArray[y] = outputBoardStringArray[y] + (itemsInLocation.map {
                            when (it.type) {
                                "Player" -> { "p" }
                                "Box" -> { "b" }
                                "Wall" -> { "#" }
                                "StorageLocation" -> { "*" }
                                else -> { " " }
                            }
                        }.firstOrNull()
                        )
                    }
                    if (itemsInLocation.size == 2) {
                        if (itemsInLocation.map { it.type }.contains("Player")) { outputBoardStringArray[y] = outputBoardStringArray[y] + "P" }
                        if (itemsInLocation.map { it.type }.contains("Box"))  { outputBoardStringArray[y] = outputBoardStringArray[y] + "B" }
                    }

                }
            }
            return outputBoardStringArray
        }
    }
}

