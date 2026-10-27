package com.wingedsheep.mtg.sets.definitions.msh

import com.wingedsheep.mtg.sets.discovery.CardDiscovery
import com.wingedsheep.sdk.limited.LandSlot
import com.wingedsheep.sdk.model.CardDefinition
import com.wingedsheep.sdk.model.MtgSet
import com.wingedsheep.sdk.model.Printing

/**
 * Marvel Super Heroes (2026)
 *
 * Set Code: MSH
 * Release Date: June 26, 2026
 */
object MarvelSuperHeroesSet : MtgSet {

    override val code = "MSH"
    override val displayName = "Marvel Super Heroes"
    override val releaseDate = "2026-06-26"

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
     * taw/magic-search-engine `data/boosters/msh-play.yaml`, after Wizards' "Collecting" article
     * for the set.
     */
    override val boosterLandSlot: LandSlot = LandSlot(
        nonbasicLands = setOf(
            "A.I.M. Labs",
            "Asgardian Citadel",
            "Avengers Hangar",
            "Birnin Zana Plaza",
            "Fisk Tower",
            "Hell's Kitchen",
            "Los Diablos Missile Base",
            "Pym Technologies",
            "Stark Industries",
            "Subterranean Cavern",
        ),
        nonbasicChance = 0.5,
    )

    private const val CARDS_PACKAGE = "com.wingedsheep.mtg.sets.definitions.msh.cards"
}
