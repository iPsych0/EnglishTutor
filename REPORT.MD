## ProgrammeerProjectDaniel (C) 2016 Daniel Oliemans

#### App description

This app will aid Dutch learners of English as a foreign language in their learning process. Therefore, this app is usable within
educational contexts and usable only for Dutch learners. The app will focus on enriching the vocabulary of the user by means of primarily
exercises. Users may use this app as a complement to the curriculum they are following. Thus, the app is not designed to substitute any
given course, but rather to aid and contribute to a better learning experience. 

![app](https://cloud.githubusercontent.com/assets/18482747/16304751/1396f1dc-3957-11e6-9d6d-78a79608f83c.png)

#### Technical design

##### Hierarchy of app:

###### Activities

**AddWords**: In this activity, the user adds words to the database under the list name where they belong. The helper file DBHelper is used here to manage and store the word lists in a database.

**AllWords**: In this activity, the user is presented with a ListView showing them all their word lists. The lists are retrieved from the DBHelper database. The words are then printed out in neat columns horizontally through the WordListAdapter.

**Dictionary**: In this activity, the user can look up words and get the translation from the Yandex dictionary API. This API is managed by the HTTPRequestHelper and the ASyncTask helper files. They ensure the connection is made with the API endpoint and parse the JSON that is returned.

**Exercises**: In this activity, the user enters the translation of the word on the other side of the EditText. The check-fucntion of this app then compares the user-input to the database answers to see if the user provided the right translation. The word lists are shown and printed through a custom adapter called ExercisesAdapter.

**MainActivity**: MainActivity is mainly an instructional page that is presented to the user each time he or she opens the app.

**NewList**: In this activity, the user can name the list they want their words to be stored in.

**Practise**: In this activity, the user is presented with an overview of their own word lists from the DBHelper file. If they haven't created any, they can create their own list here by pressing the floating action button, which sends them to the NewList activity. 

**WordLists**: In this activity, the user is presented with an overview of their own word lists from the DBHelper file. They can click on a list and see what words are stored in each list, should the user want to study them more.

###### Helper files

**ASyncTask**: ASyncTask executes the query from HTTPRequestHelper and parses the JSON. The JSON that is returned is the translation of the user's translation request. It is used only in the Dictionary activity.

**DBHelper**: DBHelper is a database manager that not only has functions to store words in the database, but also returns certain columns from the database that contain for example Dutch words or English words or list names. These functions are commonly used throughout various activities.

**ExercisesAdapter**: ExercisesAdapter is a custom adapter, made to print out two columns where the horizontal items are a TextView and an EditText. This is necessary in the Exercises activity, because it provides a user-friendly interface for the user to practise their word lists.

**HTTPRequestHelper**: HTTPRequestHelper is a class that handles the API key, the API URL and creates the query string from the user's input. If, for example, the user selects a translation from Dutch to English, the parameters are set to "nl-en". This way, the app adapts to the user's requests. It is used only in the Dictionary activity.

**WordListAdapter**: WordListAdapter is a custom adapter, made to print out two columns where both horizontal items are TextViews showing the using the Dutch and English words in their list, should they want to study them more. This is necessary in the WordLists activity, because it provides a user-friendly interface for the user to study their word lists.

### Challenges during development

There were quite a few challenges throughout this project that I had to overcome. First of all, my database comprehension was very trivial. We had worked with a DBHelper file before, but I had to adapt a lot of things to my own project. This often led to confusion as to what my database results were. Also, while developing the functions that return specific columns, I had some difficulty with the database queries. For example, I needed to select Dutch and/or English words from columns where their parent column was the name of the list. It took me quite some workarounds until I got to the solution. After a lot of googling and some help of my classmates, I managed to get the DBHelper file working. 

Secondly, JSON parsing was somewhat of a challenge for me as well. In a problem set prior to the project, we had already worked with HTTPRequestHelpers and ASyncTasks. This would make one think that it would be easier to implement. While this was true for my HTTPRequestHelper, I had to do some thorough googling on how to get my JSON parsed. In the problem set prior to this project, I received XML from my query, not JSON, so I didn't have to work with JSON parsing. While it didn't take me too long to parse the JSON and understand how it worked, it was challenging nonetheless.

Thirdly, writing my custom adapter for the Exercises activity was quite dramatic (quasi-traumatic). The implementation was not so much the problem, as I had worked with a custom adapter before. Now, the tricky part came in when I had to actually retrieve information from the custom adapter's XML in another activity. Since the XML is not directly linked to the activity I wanted to use it in, I had to look for other ways to still retrieve the information I wanted. Basically, the problem is, and still persists, that I printed out a TextView and an EditText in my custom adapter. This was using its own XML file. Then, in another activity, I wanted to do something with the data stored in those EditTexts. However, the XMLs are not linked, hence I could not directly retrieve the EditText values. After some googling I found a somewhat ambiguous solution, which was to add a TextChangedListener (TextWatcher). This would listen on EditTexts for any changes and then store the character sequence that was added in a string array. I then had to return this string array to my Exercises activity in order to check whether the user input matched the database values that were stored for that list. This was utmost frustrating, as it was not only the last thing I had left to do, but also the thing I struggled most with. In the end, I got to return the string array to my other activity and I could compare user input to the correct answer with some help from lots and lots of Googling.
