# Day 1

On day 1, I wrote my proposal and made sketches of the application. I created this Git repository for future files to be uploaded to.

# Day 2

On day 2, I worked on my design document and finished it with a diagram of classes and API's I would use. I also created the class files in Android Studio and added the menu to each one of them. I made a starting effort in changing the menu items and menu icons.

# Day 3

On day 3, I finished all of my XML files. I hard-coded some strings for illustrative purposes, but I have all fields I want to work with done in my XML. Got the menu items and menu icons appropriately changed and got my intents working.

# Day 4

On day 4, I started writing some Java code in my project. I completed the HTTPRequestHelper file to send queries to my translation API. Google Translate API appeared to be only available for enterprise purposes and was hence an API that required billing. As I did not feel like paying for this API, I did some further research and stumbled upon the "Yandex Dictionary API". I retrieved my API key, set the parameters in my HTTPRequestHelper file and the queries now work for this API.
I also wrote some code in ASyncTask, to eventually execute my queries and parse the JSON that the queries retrieve. For testing purposes, I created the blueprints for this function and let the function "Toast" my JSON. The JSON I retrieve is correct and now needs to be parsed before I can actually do anything with it, but at least the queries work and it returns the desired result in JSON.
I decided to drop the WordsAPI, as the features are identical to the Yandex Dictionary API. This will save me the trouble of querying and parsing two different things. My app is ready for the prototype demo on Friday.

# Day 5

Demo on Friday

# Day 6

Instead of using a Dialog window to add a new table in my database, I decided to go for seperate activities to create word lists. I started a bit with working on my database and created the XML for the activities.

# Day 7

I finished writing my database. I want to add further functions to add and delete words, which need to correspond with my activities. So far I have only built a prototype for the database; it is almost done. Experiencing some issues with the methods I use in my DBHelper file that won't let it correspond with the activity.

# Day 8


