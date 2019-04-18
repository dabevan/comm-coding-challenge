package com.davidbevan.challenge3

abstract class SokobanItem(var x:Int, var y:Int) {

    open val type = "SokobanItem"

    fun moveUp() {
        if (isOkForMeToMove(x, y, x, y - 1, this.type)) {
            Board.getSokobanItemsInLocation(x, y - 1).map { it.performMoveUp()}
            this.performMoveUp()
        }
    }

    fun moveDown() {
        if (isOkForMeToMove(x, y, x, y + 1, this.type)) {
            Board.getSokobanItemsInLocation(x, y + 1).map { it.performMoveDown()}
            this.performMoveDown()
        }
    }

    fun moveLeft() {
        if (isOkForMeToMove(x, y, x - 1, y, this.type)) {
            Board.getSokobanItemsInLocation(x - 1, y).map { it.performMoveLeft()}
            this.performMoveLeft()
        }
    }

    fun moveRight() {
        if (isOkForMeToMove(x, y, x + 1, y, this.type)) {
            Board.getSokobanItemsInLocation(x + 1, y).map { it.performMoveRight()}
            this.performMoveRight()
        }
    }

    open fun performMoveUp() {
        y = y - 1
    }

    open fun performMoveDown() {
        y = y + 1
    }

    open fun performMoveLeft() {
        x = x - 1
    }

    open fun performMoveRight() {
        x = x + 1
    }


    open fun isOkForMeToMove(oldX :Int, oldY :Int, newX :Int, newY :Int, callingType :String): Boolean {
        val displacedX = (2*newX) - oldX
        val displacedY = (2*newY) - oldY
        Board.getSokobanItemsInLocation(newX, newY).map {
            if(!it.isOkForMeToMove(newX,newY,displacedX, displacedY,callingType)) return false
        }
        return true
    }
}

class Player(x: Int, y: Int) : SokobanItem(x, y) {

    override val type = "Player"

}

class Wall(x: Int, y: Int) : SokobanItem(x, y) {

    override val type = "Wall"

    override fun isOkForMeToMove(oldX :Int, oldY :Int, newX :Int, newY :Int, callingType :String): Boolean {
        return false
    }
}

class StorageLocation(x: Int, y: Int) : SokobanItem(x, y) {

    override val type = "StorageLocation"

    override fun isOkForMeToMove(oldX :Int, oldY :Int, newX :Int, newY :Int, callingType :String): Boolean {
        return true
    }

    override fun performMoveUp() { }
    override fun performMoveDown() { }
    override fun performMoveLeft() { }
    override fun performMoveRight() { }

}

class Box(x: Int, y: Int) : SokobanItem(x, y) {

    override val type = "Box"

    override fun isOkForMeToMove(oldX :Int, oldY :Int, newX :Int, newY :Int, callingType :String): Boolean {
        if (callingType != "Player") return false
        return super.isOkForMeToMove(oldX, oldY, newX,newY, type)
    }
}

