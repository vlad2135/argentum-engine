package com.wingedsheep.mtg.sets.definitions.tla

import com.wingedsheep.mtg.sets.discovery.CardDiscovery
import com.wingedsheep.sdk.limited.LandSlot
import com.wingedsheep.sdk.model.CardDefinition
import com.wingedsheep.sdk.model.MtgSet
import com.wingedsheep.sdk.model.Printing

/**
 * Avatar: The Last Airbender (2025)
 *
 * Set Code: TLA
 * Release Date: November 21, 2025
 */
object AvatarTheLastAirbenderSet : MtgSet {

    override val code = "TLA"
    override val displayName = "Avatar: The Last Airbender"
    override val releaseDate = "2025-11-21"

    override val cards: List<CardDefinition> by lazy {
        CardDiscovery.findIn(CARDS_PACKAGE)
    }

    override val printings: List<Printing> by lazy {
        CardDiscovery.findPrintingsIn(CARDS_PACKAGE)
    }

    override val basicLands: List<CardDefinition> by lazy {
        CardDiscovery.findBasicLandsIn(CARDS_PACKAGE, code)
    }

    /**
     * Paper land slot: a common dual land 50% of the time, otherwise a basic land. Odds from
     * taw/magic-search-engine `data/boosters/tla-play.yaml`, after Wizards' "Collecting" article
     * for the set.
     */
    override val boosterLandSlot: LandSlot = LandSlot(
        nonbasicLands = setOf(
            "Airship Engine Room",
            "Boiling Rock Prison",
            "Foggy Bottom Swamp",
            "Kyoshi Village",
            "Meditation Pools",
            "Misty Palms Oasis",
            "North Pole Gates",
            "Omashu City",
            "Serpent's Pass",
            "Sun-Blessed Peak",
        ),
        nonbasicChance = 0.5,
    )

    private const val CARDS_PACKAGE = "com.wingedsheep.mtg.sets.definitions.tla.cards"
}
