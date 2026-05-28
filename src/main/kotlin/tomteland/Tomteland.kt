package tomteland

private val hierarki = mapOf(
    "Tomten" to listOf("Glader", "Butter"),

    "Glader" to listOf("Tröger", "Trötter", "Blyger"),
    "Butter" to listOf("Rådjuret", "Nyckelpigan", "Haren", "Räven"),

    "Trötter" to listOf("Skumtomten"),
    "Skumtomten" to listOf("Dammråttan"),

    "Räven" to listOf("Gråsuggan", "Myran"),
    "Myran" to listOf("Bladlusen"),

    "Tröger" to emptyList(),
    "Blyger" to emptyList(),
    "Rådjuret" to emptyList(),
    "Nyckelpigan" to emptyList(),
    "Haren" to emptyList(),
    "Gråsuggan" to emptyList(),
    "Dammråttan" to emptyList(),
    "Bladlusen" to emptyList()
)

fun hämtaAllaUnderordnade(namn: String): List<String> {
    val underordnade = hierarki[namn].orEmpty()

    return underordnade + underordnade.flatMap {
        hämtaAllaUnderordnade(it)
    }
}

fun körTester() {
    var godkända = 0
    var misslyckade = 0

    fun testa(namn: String, förväntade: List<String>) {
        val resultat = hämtaAllaUnderordnade(namn)

        if (resultat.sorted() == förväntade.sorted()) {
            println("✓ $namn")
            godkända++
        } else {
            println("✗ $namn")
            println("Förväntade: ${förväntade.sorted()}")
            println("Fick: ${resultat.sorted()}")
            misslyckade++
        }
    }

    testa("Bladlusen", emptyList())
    testa("Tröger", emptyList())
    testa("Blyger", emptyList())

    testa("Myran", listOf("Bladlusen"))

    testa(
        "Trötter", listOf("Skumtomten", "Dammråttan")
    )

    testa(
        "Räven", listOf("Gråsuggan", "Myran", "Bladlusen")
    )

    testa(
        "Glader", listOf(
            "Tröger", "Trötter", "Blyger", "Skumtomten", "Dammråttan"
        )
    )

    testa(
        "Tomten", listOf(
            "Glader",
            "Butter",
            "Tröger",
            "Trötter",
            "Blyger",
            "Skumtomten",
            "Dammråttan",
            "Rådjuret",
            "Nyckelpigan",
            "Haren",
            "Räven",
            "Gråsuggan",
            "Myran",
            "Bladlusen"
        )
    )

    println()
    println("Godkända tester: $godkända")
    println("Misslyckade tester: $misslyckade")
}

fun main() {
    körTester()

    println()
    println("Rävens underordnade: ${hämtaAllaUnderordnade("Räven")}")
    println("Tomtens underordnade: ${hämtaAllaUnderordnade("Tomten")}")
}
