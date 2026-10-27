package com.wingedsheep.engine.limited

import com.wingedsheep.sdk.core.CardType
import com.wingedsheep.sdk.core.ManaCost
import com.wingedsheep.sdk.core.TypeLine
import com.wingedsheep.sdk.dsl.basicLand
import com.wingedsheep.sdk.limited.LandSlot
import com.wingedsheep.sdk.limited.PlayBooster
import com.wingedsheep.sdk.limited.StandardBooster
import com.wingedsheep.sdk.model.CardDefinition
import com.wingedsheep.sdk.model.Rarity
import com.wingedsheep.sdk.model.ScryfallMetadata
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.doubles.shouldBeGreaterThan
import io.kotest.matchers.doubles.shouldBeLessThan
import io.kotest.matchers.shouldBe

/**
 * A set whose paper land slot holds common dual lands keeps that slot — basics included — and opens
 * its duals only there; a set with a basics-only slot (no [LandSlot]) drops it.
 */
class BoosterGeneratorLandSlotTest : DescribeSpec({

    fun card(name: String, rarity: Rarity, types: Set<CardType> = setOf(CardType.SORCERY)) = CardDefinition(
        name = name,
        manaCost = ManaCost.ZERO,
        typeLine = TypeLine(cardTypes = types),
        metadata = ScryfallMetadata(rarity = rarity),
    )

    val duals = (1..5).map { card("Dual $it", Rarity.COMMON, setOf(CardType.LAND)) }
    val dualNames = duals.map { it.name }.toSet()
    val spells = (1..40).map { card("Common $it", Rarity.COMMON) } +
        (1..20).map { card("Uncommon $it", Rarity.UNCOMMON) } +
        (1..10).map { card("Rare $it", Rarity.RARE) } +
        (1..3).map { card("Mythic $it", Rarity.MYTHIC) }
    val basics = listOf("Plains", "Island", "Swamp", "Mountain", "Forest").mapIndexed { i, type ->
        basicLand(type) { collectorNumber = "${270 + i}" }.copy(setCode = "TST")
    }
    val basicNames = basics.map { it.name }.toSet()

    fun generator(landSlot: LandSlot?) = BoosterGenerator(
        mapOf(
            "TST" to BoosterGenerator.SetConfig(
                setCode = "TST",
                setName = "Test Set",
                cards = spells + duals,
                basicLands = basics,
                boosterStrategy = PlayBooster(),
                landSlot = landSlot,
            ),
        ),
    )

    val slot = LandSlot(nonbasicLands = dualNames, nonbasicChance = 0.5)

    describe("a set with a land slot") {

        it("adds exactly one land-slot card to the strategy's 13") {
            repeat(200) {
                val pack = generator(slot).generateBooster("TST")
                pack shouldHaveSize 14
                pack.count { it.name in dualNames || it.name in basicNames } shouldBe 1
            }
        }

        it("opens its nonbasic lands only in the land slot") {
            // Duals are commons, so without the slot they would also land in common and wildcard slots.
            repeat(200) {
                val pack = generator(slot).generateBooster("TST")
                pack.dropLast(1).none { it.name in dualNames } shouldBe true
            }
        }

        it("holds a nonbasic at the declared rate and a basic otherwise") {
            val runs = 2000
            val packs = (1..runs).map { generator(slot).generateBooster("TST").last() }
            val dualRate = packs.count { it.name in dualNames }.toDouble() / runs
            dualRate shouldBeGreaterThan 0.45
            dualRate shouldBeLessThan 0.55
            packs.filter { it.name in basicNames }.all { it.setCode == "TST" } shouldBe true
        }

        it("drops the slot when every nonbasic land is banned") {
            repeat(50) {
                val pack = generator(slot).generateBooster("TST", bannedCardNames = dualNames)
                pack shouldHaveSize 13
                pack.none { it.name in basicNames } shouldBe true
            }
        }

        it("keeps it on the combined-pool path for a lone set") {
            repeat(50) {
                generator(slot).generateBooster(listOf("TST"), chaos = true) shouldHaveSize 14
            }
        }

        it("ignores it when the caller overrides the strategy, leaving the lands in the pool") {
            val override = StandardBooster(commons = 45, uncommons = 0, rares = 0)
            repeat(20) {
                val pack = generator(slot).generateBooster("TST", strategy = override)
                pack shouldHaveSize 45
                pack.none { it.name in basicNames } shouldBe true
            }
            val opened = (1..20).flatMap { generator(slot).generateBooster("TST", strategy = override) }
            opened.any { it.name in dualNames } shouldBe true
        }
    }

    describe("a set without a land slot") {

        it("drops it and leaves its common lands in the common slots") {
            repeat(50) {
                val pack = generator(landSlot = null).generateBooster("TST")
                pack shouldHaveSize 13
                pack.none { it.name in basicNames } shouldBe true
            }
            val opened = (1..50).flatMap { generator(landSlot = null).generateBooster("TST") }
            opened.any { it.name in dualNames } shouldBe true
        }
    }
})
