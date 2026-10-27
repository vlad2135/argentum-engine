package com.wingedsheep.mtg.sets.definitions.dft

import com.wingedsheep.mtg.sets.discovery.CardDiscovery
import com.wingedsheep.sdk.limited.LandSlot
import com.wingedsheep.sdk.model.CardDefinition
import com.wingedsheep.sdk.model.MtgSet
import com.wingedsheep.sdk.model.Printing

/**
 * Aetherdrift (2025)
 *
 * Set Code: DFT
 * Release Date: February 14, 2025
 */
object AetherdriftSet : MtgSet {

    override val code = "DFT"
    override val displayName = "Aetherdrift"
    override val releaseDate = "2025-02-14"

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
     * Paper land slot: a common gain land 50% of the time, otherwise a basic land. Odds from
     * taw/magic-search-engine `data/boosters/dft-play.yaml`, after Wizards' "Collecting" article
     * for the set.
     */
    override val boosterLandSlot: LandSlot = LandSlot(
        nonbasicLands = setOf(
            "Bloodfell Caves",
            "Blossoming Sands",
            "Dismal Backwater",
            "Jungle Hollow",
            "Rugged Highlands",
            "Scoured Barrens",
            "Swiftwater Cliffs",
            "Thornwood Falls",
            "Tranquil Cove",
            "Wind-Scarred Crag",
        ),
        nonbasicChance = 0.5,
    )

    private const val CARDS_PACKAGE = "com.wingedsheep.mtg.sets.definitions.dft.cards"
}
