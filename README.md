# kotlin-pokemon

This is a study of the Kotlin language using a SQL database
and integrating with external APIs.

The software was developed using the hexagonal architecture concepts.

The API is very simple, it creates a pokemon trainer with it's
pokemon team. There is an endpoint to find a trainer by id and
an endpoint to update a pokemon team of a trainer, this endpoint
overwrite the pokemon team already saved in the database, maybe
in the future I implement endpoints to add or remove a pokemon 
from a team of a trainer.

## ⚙ Requirements
- Java 17 (JVM 17)
- 🐳 Docker

## 🚀 How to run
- Run `docker-compose up` command inside the project folder
- Start the application in `Application.kt` in the IDE of your preference

## ⚡ Test in Insomnia
[![Run in Insomnia}](https://insomnia.rest/images/run.svg)](https://insomnia.rest/run/?label=Kotlin-Pokemon&uri=https%3A%2F%2Fraw.githubusercontent.com%2Ftocura%2Fkotlin-pokemon%2Fmaster%2Fsrc%2Fmain%2Fresources%2Finsomnia%2FInsomnia_collection.json)

## 📚 References
- [PokeApi](https://pokeapi.co/)
- [Kotlin](https://kotlinlang.org/docs/home.html)
- [Kotlin Coroutines](https://proandroiddev.com/awaiting-multiple-coroutines-the-clean-way-75469f8df160)
- [Kotlin Mutex](https://medium.com/mobile-app-development-publication/mutex-for-coroutines-5f4a4ca60763)
