import main.ItemService

class ItemController(private val itemService: ItemService) {

    fun quiz(numQuestions: Int) {
        val questions = itemService.selectRandomItems(numQuestions)
        var correctAnswers = 0

        for (item in questions) {
            println("Question: ${item.question}")
            for ((index, answer) in item.answers.withIndex()) {
                println("${index + 1}. $answer")
            }

            print("Your answer: ")
            val userAnswer = readLine()?.toIntOrNull()

            if (userAnswer != null && userAnswer - 1 == item.correct) {
                correctAnswers++
                println("Correct!\n")
            } else {
                println("Wrong! The correct answer was: ${item.answers[item.correct]}\n")
            }
        }

        // Show the final result
        println("Quiz finished!")
        println("Correct answers: $correctAnswers / ${questions.size}")
    }
}
