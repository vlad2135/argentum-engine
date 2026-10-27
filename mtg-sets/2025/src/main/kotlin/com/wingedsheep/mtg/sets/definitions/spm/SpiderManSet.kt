package com.wingedsheep.mtg.sets.definitions.spm

import com.wingedsheep.mtg.sets.discovery.CardDiscovery
import com.wingedsheep.sdk.limited.LandSlot
import com.wingedsheep.sdk.model.CardDefinition
import com.wingedsheep.sdk.model.MtgSet
import com.wingedsheep.sdk.model.Printing

/**
 * Marvel's Spider-Man (2025)
 *
 * Set Code: SPM
 * Release Date: September 26, 2025
 */
object SpiderManSet : MtgSet {

    override val code = "SPM"
    override val displayName = "Marvel's Spider-Man"
    override val releaseDate = "2025-09-26"

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
     * taw/magic-search-engine `data/boosters/spm-play.yaml`, after Wizards' "Collecting" article
     * for the set.
     */
    override val boosterLandSlot: LandSlot = LandSlot(
        nonbasicLands = setOf(
            "Ominous Asylum",
            "Savage Mansion",
            "Sinister Hideout",
            "Suburban Sanctuary",
            "University Campus",
        ),
        nonbasicChance = 0.5,
    )

    private const val CARDS_PACKAGE = "com.wingedsheep.mtg.sets.definitions.spm.cards"
}
