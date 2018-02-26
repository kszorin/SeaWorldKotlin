package ru.kszorin.seaworldkotlin.models

import ru.kszorin.seaworldkotlin.models.behaviour.Diet
import ru.kszorin.seaworldkotlin.models.behaviour.EnvironsMoving
import ru.kszorin.seaworldkotlin.models.behaviour.PeriodicReproduction

/**
 * Created on 23.02.2018.
 */
class Pinguin(id : Int, pos : Pair<Int, Int>) : Animal(id, pos) {

    companion object {
        private val REPRODUCTION_PERIOD: Byte = 3
        private val ENVIRONS: Byte = 1
    }

    override val species = Species.PENGUIN
    override val environs : Byte = ENVIRONS

    override val eatingBehaviour = Diet()
    override val movingBehaviour = EnvironsMoving()
    override val reproductionBehaviour = PeriodicReproduction()

    override fun lifeStep() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun createBaby() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}