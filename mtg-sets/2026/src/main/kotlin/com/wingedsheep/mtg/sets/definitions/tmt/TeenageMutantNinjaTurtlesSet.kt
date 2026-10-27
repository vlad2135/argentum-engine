package com.wingedsheep.mtg.sets.definitions.tmt

import com.wingedsheep.mtg.sets.discovery.CardDiscovery
import com.wingedsheep.sdk.limited.LandSlot
import com.wingedsheep.sdk.model.CardDefinition
import com.wingedsheep.sdk.model.MtgSet
import com.wingedsheep.sdk.model.Printing

/**
 * Teenage Mutant Ninja Turtles (2026)
 *
 * Set Code: TMT
 * Release Date: March 6, 2026
 */
object TeenageMutantNinjaTurtlesSet : MtgSet {

    override val code = "TMT"
    override val displayName = "Teenage Mutant Ninja Turtles"
    override val releaseDate = "2026-03-06"
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
     * Paper land slot: a common dual land 60% of the time, otherwise a basic land. Odds from
     * taw/magic-search-engine `data/boosters/tmt-play.yaml`, after Wizards' "Collecting" article
     * for the set.
     */
    override val boosterLandSlot: LandSlot = LandSlot(
        nonbasicLands = setOf(
            "Dimension X",
            "Foot Headquarters",
            "Illegitimate Business",
            "Mutant Town",
            "TCRI Building",
        ),
        nonbasicChance = 0.6,
    )

    private const val CARDS_PACKAGE = "com.wingedsheep.mtg.sets.definitions.tmt.cards"
}
