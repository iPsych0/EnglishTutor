# ProgrammeerProjectDaniel

## App Concept

![img-20160530-wa0012](https://cloud.githubusercontent.com/assets/18482747/15654461/bf7b9708-2694-11e6-81f6-33673aaeccbc.jpeg)

## Diagram of classes and inheritance

![classdiagram](https://cloud.githubusercontent.com/assets/18482747/15744124/69b7ffea-28ca-11e6-844a-88ab2e3ed61a.png)


## API's and frameworks

For this app, I will work with the Yandex Dictionary API (https://tech.yandex.com/dictionary/) and the WordsAPI (https://www.wordsapi.com/).
As illustrated in the diagram above, the homescreen activity will inheret from the WordsAPI, by returning a random dictionary entry to the user once a day.
The dictionary API inherits from the Yandex Dictionary API. When the user enters a word, the HTTPRequestHelper will execute the query to the API endpoint. ASyncTask is a helper file that will ensure that the request is valid, otherwise it will prompt the user that something went wrong. In case of a valid request, ASyncTask will then parse over the data retrieved from the query and parse over the JSON code that is returned.

Finally, for the practise activity, a SQLite Database will be used. Users can create word lists and save them locally. If the user quits the app, restarts their phone, receives a phone call or any other abruption of the activity, the data will still be stored. This will also allow users to practise their own word lists later and provides a convenient feature for users to practise vocabulary.
When the user has made a word list, they may click on that list to start the Exercises activity that will test their vocabulary respective to the list they clicked on. Hence, the database is important to keep track of per list, so that users can practise their vocabulary with a variety of lists, inherited from the databases they created themself.

The database will consist of the following fields:

* TABLE_ID (Int, Unique table ID)
* EnglishWordColumn (VARCHAR)
* DutchWordColumn (VARCHAR)
* ROW_ID (Int, autoincrement)

User_IDs are not necessary, because the databases are locally stored, thus there will only ever be one user.
