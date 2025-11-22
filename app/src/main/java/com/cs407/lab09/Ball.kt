package com.cs407.lab09

/**
 * Represents a ball that can move. (No Android UI imports!)
 *
 * Constructor parameters:
 * - backgroundWidth: the width of the background, of type Float
 * - backgroundHeight: the height of the background, of type Float
 * - ballSize: the width/height of the ball, of type Float
 */
class Ball(
    private val backgroundWidth: Float,
    private val backgroundHeight: Float,
    private val ballSize: Float
) {
    var posX = 0f
    var posY = 0f
    var velocityX = 0f
    var velocityY = 0f
    private var accX = 0f
    private var accY = 0f

    private var isFirstUpdate = true

    init {
        reset()
    }

    /**
     * Updates the ball's position and velocity based on the given acceleration and time step.
     * (See lab handout for physics equations)
     */
    fun updatePositionAndVelocity(xAcc: Float, yAcc: Float, dT: Float) {
        if(isFirstUpdate) {
            isFirstUpdate = false
            accX = xAcc
            accY = yAcc
            return
        }
        val speed = 10
        posX = posX + velocityX * dT + dT * dT * (3*accX + xAcc) / 6f
        posY = posY + velocityY * dT + dT * dT * (3*accY + yAcc) / 6f
        velocityX = velocityX + 0.5f * (xAcc + accX) * dT * speed
        velocityY = velocityY + 0.5f * (yAcc + accY) * dT * speed
        accX = xAcc
        accY = yAcc
        checkBoundaries()

    }

    /**
     * Ensures the ball does not move outside the boundaries.
     * When it collides, velocity and acceleration perpendicular to the
     * boundary should be set to 0.
     */
    fun checkBoundaries() {
        // (Check all 4 walls: left, right, top, bottom)
        if (posX <= 0) { // hitting left wall
            if (velocityX < 0) velocityX = 0f
            if (accX < 0) accX = 0f
        }
        if (posX >= backgroundWidth - ballSize) { // hitting right wall
            if (velocityX > 0) velocityX = 0f
            if (accX > 0) accX = 0f
        }
        if (posY <= 0) { // hitting top wall
            if (velocityY < 0) velocityY = 0f
            if (accY < 0) accY = 0f
        }
        if (posY >= backgroundHeight - ballSize) { // hitting bottom wall
            if (velocityY > 0) velocityY = 0f
            if (accY > 0) accY = 0f
        }

    }

    /**
     * Resets the ball to the center of the screen with zero
     * velocity and acceleration.
     */
    fun reset() {
        // (Reset posX, posY, velocityX, velocityY, accX, accY, isFirstUpdate)
        posX = backgroundWidth / 2f - ballSize / 2f
        posY = backgroundHeight / 2f - ballSize / 2f
        velocityX = 0f
        velocityY = 0f
        accX = 0f
        accY = 0f
        isFirstUpdate = true
    }
}