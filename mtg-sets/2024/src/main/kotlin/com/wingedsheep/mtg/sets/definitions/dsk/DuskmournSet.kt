package com.wingedsheep.mtg.sets.definitions.dsk

import com.wingedsheep.mtg.sets.discovery.CardDiscovery
import com.wingedsheep.sdk.limited.LandSlot
import com.wingedsheep.sdk.model.CardDefinition
import com.wingedsheep.sdk.model.MtgSet
import com.wingedsheep.sdk.model.Printing

/**
 * Duskmourn: House of Horror (2024)
 *
 * Set Code: DSK
 * Release Date: September 27, 2024
 */
object DuskmournSet : MtgSet {

    override val code = "DSK"
    override val displayName = "Duskmourn: House of Horror"
    override val releaseDate = "2024-09-27"
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
     * Paper land slot: a common dual land 50% of the time, otherwise a basic land. Odds from
     * taw/magic-search-engine `data/boosters/dsk-play.yaml`, after Wizards' "Collecting" article
     * for the set.
     */
    override val boosterLandSlot: LandSlot = LandSlot(
        nonbasicLands = setOf(
            "Abandoned Campground",
            "Bleeding Woods",
            "Etched Cornfield",
            "Lakeside Shack",
            "Murky Sewer",
            "Neglected Manor",
            "Peculiar Lighthouse",
            "Raucous Carnival",
            "Razortrap Gorge",
            "Strangled Cemetery",
        ),
        nonbasicChance = 0.5,
    )

    private const val CARDS_PACKAGE = "com.wingedsheep.mtg.sets.definitions.dsk.cards"
}
