# ProgrammeerProjectDaniel © 2009 Daniel Oliemans

## App Concept

![img-20160530-wa0012](https://cloud.githubusercontent.com/assets/18482747/15654461/bf7b9708-2694-11e6-81f6-33673aaeccbc.jpeg)

## Introduction and problem definition

This app will aid Dutch learners of English as a foreign language in their learning process. Therefore, this app is usable within educational contexts and usable only for Dutch learners.  The app will focus on enriching the vocabulary of the user by means of primarily exercises. Users may use this app as a complement to the curriculum they are following. Thus, the app is not designed to substitute any given course, but rather to aid and contribute to a better learning experience. 

## Features of the app

Users are can look up words in a dictionary. This will aid them throughout their learning process, not exclusively to vocabulary, but also to help understand texts better in case the user does not understand a given word. The Yandex Dictionary API will be used as back-end functionality for this feature.
Another feature of the app will be that the user will be presented with a “word of the day”, where a random word from the dictionary will be selected and presented to the user every 24 hours. While this has no active contribution to the user’s learning process, it contains a surprise element and brings out the user’s curiosity. 
Finally, one of the features that will be available in the app is a program where users can enter the words they need to learn and the correct translation. The app will then test the user’s vocabulary based on the given input. The user will receive a score based on correct performance. 

## Technical description

For this app, the following two API’s will be used:
https://www.wordsapi.com/ and 
https://tech.yandex.com/dictionary/doc/dg/reference/lookup-docpage/ .
Both API’s respond with JSON code which I can extract the information from that I want my users to be presented. The users will then be given the result of the query in a pretty-printed, readable fashion. 

## The app decomposed

For the dictionary function, the user enters the word to be looked up in an EditText box. This search entry will be put into a string, which will then be jammed into the request URL for the API to look up the entered word.
For the vocabulary rehearsal, the user can enter the words they wish to learn (English-Dutch or Dutch-English). An array will be used to keep track of the entered input by the user. This array will then be put into a list (and stored in a database). When the rehearsal game starts, the game will last for the duration of the length of the list. The user will be scored based on his/her performance during the game. Scores will locally be kept track of with unlockable achievements.
The word of the day will simply be presented to the user on the main page of the app, every time the user opens the app.

## Foreseeable complications

Some difficulties that I may encounter, could be with the API and correctly parsing the JSON code. This proved to be difficult during the App Studio module as well, so I will need to do more thorough research on how to read JSON code out and pretty-print it. 

## Analysis of other similar products on the market

Duolingo is a prominent application in the language learning segment. Duolingo was initially web-based and later produced a phone app (cross-platform). Features included in Duolingo are for example audio pronunciation of words as well as full-sentenced grammar. Duolingo has a built-in algorithm that when users provide incorrect answers, will skip a few words and then throw the same (earlier incorrect) word back at the user. This smart technology helps the user memorising their mistakes, so they won’t make them again.
