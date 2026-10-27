package com.wingedsheep.sdk.limited

import com.wingedsheep.sdk.model.CardDefinition
import kotlin.random.Random

/**
 * A booster's land slot, for sets whose paper land slot holds set-specific nonbasic lands
 * (typically a cycle of common dual lands) as well as basics.
 *
 * Sets whose land slot is basics-only declare none: basics are free at deck building, so the slot
 * is dropped and packs are one card shorter. When the slot can hold a nonbasic, the whole slot is
 * kept, basics included, and [nonbasicLands] are removed from every other slot of the pack — in
 * paper they are only ever opened here. This is the same split MTG Arena makes for its digital Play
 * Boosters.
 *
 * The slot is applied by the booster generator on top of the set's [BoosterStrategy], so it works
 * with any strategy and strategies never see [nonbasicLands] in their pool.
 *
 * @param nonbasicLands Names of the nonbasic lands printed in the land slot.
 * @param nonbasicChance Probability that the slot holds one of [nonbasicLands] rather than a basic
 *                       land (foil and non-foil land-slot odds combined).
 */
data class LandSlot(
    val nonbasicLands: Set<String>,
    val nonbasicChance: Double,
) {
    init {
        require(nonbasicChance in 0.0..1.0) { "nonbasicChance must be in [0, 1], was $nonbasicChance" }
    }

    /**
     * The card this slot contributes to one pack: one of [nonbasics] with [nonbasicChance], else one
     * of [basics]. Returns null — dropping the slot — when no [nonbasics] are available (none
     * implemented, or all banned), since a basics-only slot is not opened.
     *
     * @param nonbasics The pool's cards named in [nonbasicLands].
     * @param basics The basic lands the slot may hold, one entry per basic land type.
     */
    fun pick(nonbasics: List<CardDefinition>, basics: List<CardDefinition>, random: Random): CardDefinition? {
        if (nonbasics.isEmpty()) return null
        val rolledNonbasic = random.nextDouble() < nonbasicChance
        return if (rolledNonbasic || basics.isEmpty()) nonbasics.random(random) else basics.random(random)
    }
}
