package toss

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.annotation.PostConstruct
import javax.persistence.Entity
import javax.persistence.Id

val prizes = listOf(
    Prize(1, "Pen", 3), Prize(2, "USB Key", 2), Prize(3, "Bottle", 1)
)

@Entity
data class Prize(@Id val id: Int, val name: String, val amount: Int)

// Define a default fallback, when there's no prize left, so that we don't have to return null
val nothing = Prize(0, "Nothing :(", 0)

@Repository
interface PrizeRepository : JpaRepository<Prize, Int>

@Service
class PrizeService(@Autowired val prizeRepository: PrizeRepository) {
    @PostConstruct
    fun initialize() {
        prizeRepository.saveAll(prizes)
    }

    fun listAvailablePrizes() = prizeRepository.findAll().filter { it.amount > 0 }

    // A more "Bullet-proof" implementation than the one I've shown at the workshop :)
    fun toss(): Prize {
        // By using shorthand ?: "elvis" operator we can exit early and return "nothing"
        val prize = listAvailablePrizes().shuffled().lastOrNull() ?: return nothing

        return prizeRepository.save(prize.copy(amount = prize.amount - 1))
    }
}

@RequestMapping("/prize")
@RestController
class PrizeController(@Autowired val prizeService: PrizeService) {

    @GetMapping
    fun listAvailablePrizes() = prizeService.listAvailablePrizes()

    @GetMapping("/toss")
    fun toss() = prizeService.toss()

}

@SpringBootApplication
class TossApplication

fun main(args: Array<String>) {
    runApplication<TossApplication>(*args)
}
