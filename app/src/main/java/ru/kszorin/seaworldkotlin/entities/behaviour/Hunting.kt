package ru.kszorin.seaworldkotlin.entities.behaviour

import android.util.Log
import ru.kszorin.seaworldkotlin.BuildConfig
import ru.kszorin.seaworldkotlin.entities.Animal
import ru.kszorin.seaworldkotlin.entities.World

/**
 * Created on 23.02.2018.
 *
 * Implements hunting eating behaviour.
 */
class Hunting: IEatingBehaviour {

    override fun eat(animal: Animal, foundPositionsInEnvirons: List<Pair<Int, Int>>): Boolean {
        var result = false
        val pos = animal.pos

        if (foundPositionsInEnvirons.isNotEmpty()) {
            // select random position
            val bufferRandomNum = (Math.random() * foundPositionsInEnvirons.size).toInt()
            val selectedFreePos = foundPositionsInEnvirons[bufferRandomNum]

            //kill victim and move animal to new position
            val victimId = animal.waterSpace[selectedFreePos.second][selectedFreePos.first]

            if (BuildConfig.DEBUG_LOG) {
                Log.d(TAG, "${animal.creaturesMap[animal.id]?.species?.name} (${animal.id})" +
                        " [${pos.first}, ${pos.second}]: killed ${animal.creaturesMap[victimId]?.species?.name} (${victimId})" +
                        " [${selectedFreePos.first}, ${selectedFreePos.second}]")
            }

            (animal.creaturesMap[victimId] as Animal).isAlive = false
            animal.eatenNumber++

            animal.waterSpace[selectedFreePos.second][selectedFreePos.first] = animal.waterSpace[pos.second][pos.first]
            animal.waterSpace[pos.second][pos.first] = World.FREE_WATER_CODE
            animal.creaturesMap[animal.id]?.pos = selectedFreePos

            if (BuildConfig.DEBUG_LOG) {
                World.logging(TAG, animal.creaturesMap, animal.waterSpace)
            }

            result = true
        }

        return result
    }

    companion object {
        private val TAG = "Hunting"
    }
}