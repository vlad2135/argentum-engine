package com.wingedsheep.mtg.sets.definitions.tdm

import com.wingedsheep.mtg.sets.discovery.CardDiscovery
import com.wingedsheep.sdk.limited.LandSlot
import com.wingedsheep.sdk.model.CardDefinition
import com.wingedsheep.sdk.model.MtgSet
import com.wingedsheep.sdk.model.Printing

/**
 * Tarkir: Dragonstorm (2025)
 *
 * Set Code: TDM
 * Release Date: April 11, 2025
 */
object TarkirDragonstormSet : MtgSet {

    override val code = "TDM"
    override val displayName = "Tarkir: Dragonstorm"
    override val releaseDate = "2025-04-11"
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
     * Paper land slot: a common gain land 87% of the time (10 duals at 10x the per-card rate of its
     * 15 basics), otherwise a basic land. Odds from taw/magic-search-engine
     * `data/boosters/tdm-play.yaml`, after Wizards' "Collecting" article for the set.
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
        nonbasicChance = 100.0 / 115,
    )

    private const val CARDS_PACKAGE = "com.wingedsheep.mtg.sets.definitions.tdm.cards"
}
