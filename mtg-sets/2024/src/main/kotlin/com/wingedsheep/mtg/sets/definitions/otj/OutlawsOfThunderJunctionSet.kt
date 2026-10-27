package com.wingedsheep.mtg.sets.definitions.otj

import com.wingedsheep.mtg.sets.discovery.CardDiscovery
import com.wingedsheep.sdk.limited.LandSlot
import com.wingedsheep.sdk.model.CardDefinition
import com.wingedsheep.sdk.model.MtgSet
import com.wingedsheep.sdk.model.Printing

/**
 * Outlaws of Thunder Junction (2024)
 *
 * Set Code: OTJ
 * Release Date: April 19, 2024
 */
object OutlawsOfThunderJunctionSet : MtgSet {

    override val code = "OTJ"
    override val displayName = "Outlaws of Thunder Junction"
    override val releaseDate = "2024-04-19"
    override val sealedSupported = true

    override val cards: List<CardDefinition> by lazy {
        CardDiscovery.findIn(CARDS_PACKAGE)
    }

    override val basicLands: List<CardDefinition> by lazy {
        CardDiscovery.findBasicLandsIn(CARDS_PACKAGE, code)
    }

    override val printings: List<Printing> by lazy {
        CardDiscovery.findPrintingsIn(CARDS_PACKAGE)
    }

    /**
     * Paper land slot: a common dual Desert 50% of the time, otherwise a basic land. Odds from
     * taw/magic-search-engine `data/boosters/otj-play.yaml`, after Wizards' "Collecting" article
     * for the set.
     */
    override val boosterLandSlot: LandSlot = LandSlot(
        nonbasicLands = setOf(
            "Abraded Bluffs",
            "Bristling Backwoods",
            "Creosote Heath",
            "Eroded Canyon",
            "Festering Gulch",
            "Forlorn Flats",
            "Jagged Barrens",
            "Lonely Arroyo",
            "Lush Oasis",
            "Soured Springs",
        ),
        nonbasicChance = 0.5,
    )

    private const val CARDS_PACKAGE = "com.wingedsheep.mtg.sets.definitions.otj.cards"
}
