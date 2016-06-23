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

Started writing my practise activity and set my adapter to the ListView. DBHelper classes weren't returning the right things yet.

# Day 9

Started fixing my DBHelper functions, spent all day to no avail yet, made small progress, but not quite there yet.

# Day 10

Demo on Friday

# Day 11

Got rid of the dialog interface to add lists and wrote the Java code for my NewList class and AddWords class. I've imported the DBHelper functions here and started working a bit more on my DBHelper functions now that I can actually see the results of my queries.

# Day 12

Worked on my DBHelper functions again. I can now return the list names to my adapter, so they can be displayed. Made an on-click listener on the list names to go to Exercises activity (which is still empty)

# Day 13

Finished my DBHelper functions completely! Adding words to the right lists now works! I can now add lists and add words.

# Day 14

Backtracked a bit to my dictionary activity. Started parsing my JSON. At the moment I only get the English synonym back, but I need to go into another JSONArray to get the translation and word type field.

# Day 15

Finished parsing JSON. My Dictionary activity is now completely done. Started working more on my Practise and Exercises activity. Added a long-click to delete a list from the ListView and the Database. Also implemented error catches in my EditText inputs.

# Day 16

Demo on Friday

# Day 17

Started doing the XML of my Exercises activity and writing some Java. Started a custom adapter names ExercisesAdapter. Currently experiencing some issues with printing the columns.

# Day 18

Found the issue in my custom adapter. I can now print the words and the EditTexts. Added a dialogue on-click to select whether the user wants to practise EN-NL or NL-EN. Words are printed out accordingly in the Exercises activity. Started writing a checkWords function that should compare the user input to the correct answer. Having difficulty retrieving the value from the EditTexts as the XML is not in my Exercises activity.

# Day 19

Googled for a solution and found that an "OnTextChangedListener" exists. Ran this through my custom adapter to retrieve the EditText values. Returning these to my Exercises activity now so I can compare the input to the correct answer. This function now fully works!

# Day 20

Decided to drop the "Word of the Day" class as I feel it does not align with my main app idea. Instead of this class, I created a new class that shows the user their completed word lists, as I had not implemented this yet. Created 2 new activities: One to show the user's lists, using a simple adapter and one to show the user the full words list, printed out in columns next to each other through a custom adapter. App is now fully working. Added an image of a book with a face to all my activities as "App mascotte", which is clickable to get tips about what to do in each activity. There is one bug left which I cannot fix, which is that I can enter words through my computer/emulator keyboard but not through my android software keyboard. Googling this provided literally no results and I am clueless as to how to fix this.

# Day 21

Final app presentation
