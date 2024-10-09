package main

import ItemController

fun main(args: Array<String>) {

    // Repo teszteles
    val repo = ItemRepository
    val quizItem = repo.randomItem()
    println("Question = $quizItem")
    println("-----------------------------------------------------------")

    // Service teszt
    val service = ItemService(repo)
    val quizItems = service.selectRandomItems(3)
    for (item in quizItems) {
        println("Question = $item")
    }
    println("-----------------------------------------------------------")

    // Controller teszt
    val itemController = ItemController(service)
    itemController.quiz(5)
}