package com.wingedsheep.mtg.sets.definitions.fin

import com.wingedsheep.mtg.sets.discovery.CardDiscovery
import com.wingedsheep.sdk.limited.LandSlot
import com.wingedsheep.sdk.model.CardDefinition
import com.wingedsheep.sdk.model.MtgSet
import com.wingedsheep.sdk.model.Printing

/**
 * Final Fantasy (2025)
 *
 * Set Code: FIN
 * Release Date: June 13, 2025
 */
object FinalFantasySet : MtgSet {

    override val code = "FIN"
    override val displayName = "Final Fantasy"
    override val releaseDate = "2025-06-13"

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
     * Paper land slot: a common dual Town 55% of the time, otherwise a basic land. Odds from
     * taw/magic-search-engine `data/boosters/fin-play.yaml`, after Wizards' "Collecting" article
     * for the set.
     */
    override val boosterLandSlot: LandSlot = LandSlot(
        nonbasicLands = setOf(
            "Baron, Airship Kingdom",
            "Gohn, Town of Ruin",
            "Gongaga, Reactor Town",
            "Guadosalam, Farplane Gateway",
            "Insomnia, Crown City",
            "Rabanastre, Royal City",
            "Sharlayan, Nation of Scholars",
            "Treno, Dark City",
            "Vector, Imperial Capital",
            "Windurst, Federation Center",
        ),
        nonbasicChance = 0.55,
    )

    private const val CARDS_PACKAGE = "com.wingedsheep.mtg.sets.definitions.fin.cards"
}
