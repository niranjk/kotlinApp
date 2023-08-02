
Task ##1 [Color App]

\------------------Start-------------------\


Our app consist of: 
1. RGB channels of red, green and blue color 
2. Each RGB channel should be added as two hexadecimal characters, where each character can be a
value of 0-9 or A-F. 
3. The value of all 3 channels will be combined to produce a six-character hexadecimal string that
is displayed as a color within the App. 

We create a form of 3 editable text fields in which the user can add 2 hexadecimal values for each color. 

After filling in all 3 fields, the user should click a button that takes the 3 values and 
concatenates them to create a valid hexadecimal color string. 
This should be converted to a color and displayed in the UI of the app. 

\------------------End-------------------\


\------------------Start-------------------\

Task ##2 [Login App]
Our App consist of :
1. Login form with username and password EditText Views and LOGIN BUTTON. 
2. Add a ClickListener interface to the button to react to a button press event. 
3. Validate that the form fields are filled in. 
4. Check the submitted username and password fields against the hardcoded values. 
5. Display a welcome message with the username if succesful and hide the form. 
6. Display an error message if not successful and redirect the user back to the form. 

There are few possible solutions: 
1. Use a singleTop Activity and send an intent to route to the same Activity to validate the credentials. 
2. Use a standard Activity to pass a username and password to another Activity and validate the credentials. 
3. Use registerForActivityResult to carry out the validation in another Activity and then return the result. 

\------------------End-------------------\



\------------------Start-------------------\

Task ##3 [Quiz App]
Our App consist of :
1. We will create a quiz where users have to answer one of three questions on the planets of the solar system. 
2. In the UI, you need to ask the user these three questions:
- what is the largest planet?
- which planet has the most moons?
- which planet spins on its side?
3. Then you need to provide a list of planets so the user can choose the planet as answer. 
4. Once the answer is given then show the user whether they are correct or wrong.

Guidelines:
1. Create a QuizActivity
2. Create a QuestionsFragment, update the layout with the questions, and add interaction with buttons and OnClickListeners(s)
3. Crete an AnswersFragment that displays the relevant answers and details of answer itself. 


\------------------End-------------------\


\------------------Start-------------------\

Task ##4 [Sport App]
Our App consist of :
1. The app consist of 3 or more top-level destinations. 
2. One of the Primary Destinations is called My Sports and should link to one 
or more secondary destinations of Sports. 


Guidelines:
1. Use Bottom Navigation and add the individual secondary sports destinations to the navigation graph
so that it can link to these destinations. 

\------------------End-------------------\

Task ##5 [Recipe App]
Our App consist of :
1. The app consist of Sweet and Savory Recipes. 
2. Each Recipe will have a title, description and a flavor. 
3. User can add new Sweet or Savory Recipes. 
4. Scroll through the list of added recipes - grouped by flavor (Sweet or Savory)
5. Click a recipe to get information about it, and finally delete recipes by swiping them aside.


Guidelines:
1. Create a new Activity. 
2. Add RecyclerView, two EditText fields and two buttons to the main layout. 
3. Add models for the flavor titles and recipes, and an enum for flavor. 
4. Add each layout for the flavor Titles and Recipe Titles 
5. Add ViewHolders for the flavor titles and recipe titles as well as Adapter. 
6. Add click listeners to show a dialog with recipe descriptions. 
7. Update your Activity to construct the new adapter, and hook up the buttons to add new Savory and 
Sweet recipes. Make sure the form is cleared after a recipe is added. 
8. Add a swipe helper to remove items.

\------------------End-------------------\

\------------------Start-------------------\

Task ##6 [Parking App]
Our App consist of :
1. The app helps people to park car and remember the position. 
2. The app will show a pin at the car's location when the user launches the app. 
3. The user can click an "I'm parked here" button to update the pin location to the current location 
every time they park. 

Guidelines:
1. Create a Google Maps Activity app. 
2. Obtain the API key. 
3. Show a button at the bottom with an I'm parked here label. 
4. Include the location service in your app. 
5. Request the user's permission to access their location. 
6. Obtain the user's location and place a pin on the map at that location. 
7. Add a car icon to your project. 
8. Add functionality to move the car icon to the user's current location. 
9. Bonus 1 - You can also store the selected location in SharedPreferences. 
10. Bonus 2 - You can restore any saved location from SharedPreferences. 

\------------------Start-------------------\

Task ##7 [Water App]
Our App Info:
1. The app helps people to regularly drink 2,500 milliliters(ml) of water per day and stay hydrated.
2. This will be done by running a background task, and also we will use the knowledge of communicating with 
a service to update a water dranked in response to user interaction. 

Guidelines:
1. Create a Water Activity. 
2. Add the foreground service and post notifications permissions to your AndroidManifest.xml file. 
3. Create a new service. 
4. Define a variable in your service to track the water level. 
5. Define constants for a notification ID and for an extra Intent data key. 
6. Set up the creation of the notification from the service. 
7. Add functions to request the notification permissions (if required), start the Foreground Service and update the water level. 
8. Set the water level to decrease by 0.144 ml every 5 seconds. 
9. Handle the addition of fluids from outside the service. 
10. Make sure the service cleans up callbacks and messages when destroyed. 
11. Register the service in the Manifest.xml file. 
12. Start the service from your activity when the activity is created, after the notification permission is granted, if needed. 
13. Add a button to the main activity layout with a Drank a Glass of Water label. 
14. When the user clicks the button, notify the service that it needs to increment the water level by 250ml. 

\------------------End-------------------\


\------------------Start-------------------\

Task ##8 [First Compose App]
Our App using Jetpack Compose: which have 3 screens:
1. Insert Rows Screen : title, text field, button where number is inserted 
2. Insert Columns Screen : title, text field where a number is inserted and button : when button clicked then the user navigates to the next screen
3. Grid Screen : display a grid with the number of rows and the number of columns inserted above. Each row will independently scroll using LazyRow 
and for each columns use LazyColumn. Each grid item will display the text "Item [row][column]"

Guidelines: 
1. Create a Compose Activity.
2. Add the navigation libray dependencies 
3. In the ui.theme package, create a new Kotlin file called Elements 
4. In that file, create @Composable functions for the titles used in the application. 
5. In the same file, create @Composable functions for the text fields used in the application. 
6. In the same file, create @Composable functions for the grid items.
7. In the same file, create @Composable functions for the buttons.
8. Create a new file called InsertRowScreen. 
9. Create InsertRowsScreenState, InsertRowsScreenContent, and InsertRowsScreen, which will be responsible for holding the screen sate
and the screen content and for managing the screen state. 
10. Create a new file called InsertColumnsScreen. 
11. Create InsertColumnScreenState, InsertColumnScreenContent, and InsertColumnScreen, which will be responsible for holding the
screen state and the screen content and for managing the screen state. 
12. Create a new file called GridScreen. 
13. Create GridScreenState, GridScreenContent, and GridScreen, which will be responsible for holding the screen state and the screen 
content and for managing the screen state. 
14. In your Activity, create a new function that will set up the navigation between your screens. 
15. In your Activity, modify the setContent method block. 

\------------------End-------------------\



/----------------Test in Android -----------
(Unit Test: Integration Test: UI Test)
(70:20:10)












// Integration Testing Pattern 
// Format 
// 1. Given 
// 2. When
// 3. Then 

Given I am not logged in 
And I open the app

When I enter my credentials
And I click login 

Then I see my Home Page. 


// ---- Espresso ---
1. ViewMatcher - withId and withText
2. ViewActions - typeText, click, scrollTo, clearText, swipeLeft, swipeRight, swipeUp, swipeDown,
closeSoftKeyboard, pressBack, pressKey, doubleClick, longClick
3. ViewAssertions - matches

# Hamcrest Library 
- usually for matching 
- operators : anyOf, both, either, is, isA, hasItem, equalTo, any, instanceOf, not, null and notNull 


# UI TESTS 
- 


# TDD : Test Driven Development 

Usecase : Scenario 
Calculator App : Add, Subtract, Multiply and Divide
Normal Approach 
1. Build your UI and your activity 
2. Create Calculator class 
3. write unit test for your Calculator class
4. Then for your Activity class : UI test 

TDD Approach 
1. Write your UI test with your scenarios first 
2. Write your Calculator class test 
3. Run your test 
4. Test Fails 
5. You Force yourself to implement your code until you pass all your failing tests. 
6. Pass all your Calculator test 
7. Then Pass all your UI tests.

# Android Architecture Components
- Key Components of Jetpack Libraries 
and how it benefits the standard Android Framework

Standard Android frameworks are pre-built components and libraries provided by the Android platform
that developers can use to build Android applications. 
These frameworks provide a wide range of functionality and APIs for various aspects of app development.
Some of the standard Android frameworks include:

1. Activity Framework: Allows developers to create and manage UI screens (activities) 
within an app, handle user interactions, and manage the activity lifecycle.

2. Fragment Framework: Provides a modular approach to UI design, allowing developers
to build reusable UI components that can be combined into a single activity.

3. Layout Framework: Offers a set of XML-based layout files and views
(such as LinearLayout, RelativeLayout, ConstraintLayout) to define and arrange UI elements.

4. Intent Framework: Enables communication between different components of an app 
and between different apps, allowing the launching of activities, passing data, and broadcasting events.

5. Content Providers Framework: Allows apps to share data with other apps by providing 
a consistent interface for accessing and manipulating data stored in databases or other sources.

6. BroadcastReceiver Framework: Enables apps to listen for system or app-level events 
and respond to them, such as receiving notifications, connectivity changes, or battery level updates.

7. Service Framework: Allows developers to create background tasks or long-running processes
that can run independently of the UI, such as downloading files or playing music in the background.

8. Networking Framework (e.g., HttpClient, HttpURLConnection): Provides APIs for making 
network requests, sending and receiving data over HTTP, and handling network-related operations.

9. Notification Framework: Allows developers to show notifications to the user, 
such as text messages, reminders, or updates, providing an interface for creating,
updating, and managing notifications.

10. Storage Framework (e.g., SQLite, SharedPreferences): 
Provides mechanisms for persistent data storage, including SQLite databases for structured data
and SharedPreferences for simple key-value pairs.

These are just a few examples of the standard Android frameworks available for developers.
The Android platform provides many more frameworks and APIs to handle different aspects of
app development, from UI design and data storage to networking and system integration. 
Developers can leverage these frameworks to build robust and feature-rich Android applications.

# ROOM 
- CRUD (Create - Read, Update - Delete)
Abstraction 
- Entities
- DAO (Data Access Object)
- Database 

# Create a Room DB for the - NewsApp 
- App will display a list of articles written by journalists. 
- Each jounalist can write one or more articles. 
- Article : title, content, date 
- Journalist : first name, last name, job title
- Many-to-Many relationship

Task :
1. Create a Room DB 
2. Unit Test the DB 


# Shopping Notes App 
1. UI will be split into two: Top/Bottom in portrait mode : Left/Right in Landscape mode
2. The first half will display the number of notes, a text field, and a button. 
3. Everytime the button is pressed, a note will be added with the text placed in the text field. 
4. The second half will display the list of notes. 
5. For each half, you will have a ViewModel that will hold the relevant data. 
6. You should define a repository that will be used on the top of the Room database to access your data. 
7. You should also define a Room database that will hold your notes. 
8. The note entity will have the following attributes: id and text 

Steps 
1. Start with Room integration by creating the Entity, Dao, and Database methods. For Dao, the @Query annotated methods can 
directly return a LiveData object so that the observers can be directly notified if the data changes. 
2. Define a template of our repository in the form of an interface. 
3. Implement the repository. The repository will have one reference to the Dao object we defined previously. The code for 
inserting the data must be moved to a separate thread.
4. Create the NotesApplication class to provide one instance of the repository that will be used across the application. Make sure 
to update the <application> tag in the AndroidManifest.xml file to add your new application class. 
5. Create our ViewModel.
6. Update the UI by observing the data stream. 
7. Unit test the repository and ViewModels. 

#------------------------------------------------

# Dog Image Downloading App 
- App that displays a list of URL for dog photos. 
- URL : https://dog.ceo/api/breed/hound/images/random/{number}
- user can choose the number of URLs they want to be displayed from the Settings Screen.
- The setting screen will be opened through an option presented on the home screen. 
- When the user clicks on a URL, an image will be downloaded locally in the application's 
external cache path.
- While the image is being downloaded, the user will see an indeterminate progress bar. 
- The list of URLs will be persisted locally using Room. 


We will be using: 
1. Retrofit to retrieve the list of URLs and download files 
2. Room to persist the list of URLs 
3. SharedPreferences to store the number of URLs to retrieve 
4. FileProvider to store the files in the cache
5. Apache IO to write the files 
6. Repository to combine all the data sources 
7. LiveData and ViewModel to handle the logic from the user. 
8. RecyclerView for the list of items. 

{
"message": [
"https://images.dog.ceo/breeds/hound-
afghan/n02088094_4837.jpg",
"https://images.dog.ceo/breeds/hound-
basset/n02088238_13908.jpg",
"https://images.dog.ceo/breeds/hound-
ibizan/n02091244_3939.jpg"
],
"status": "success"
}

Steps : 
Network - Retrofit setup 
1. Create an api package that will contain the network-related classes. 
2. Create a data class that will model the response JSON. 
3. Create a Retrofit Service class that will contain two methods. The first method will represent the API
call to return a list of breeds, and the second method will represent the API call to download the file. 

Room : 
4. Create a storage package, and inside it, create a room package. 
5. Create the Dog entity, which will contain an autogenerated ID and a URL. 
6. Create the Dog Dao, which contains methods to insert a list of dogs, delete all dogs, and query all dogs. The delete method 
is required becoz the API model does not have any unique identifiers. 

Shared Preferences : 
7. Inside the storage package, create a preference package. 
8. Inside the preference package, create a wrapper class around SharedPreferences that will return the number of URLs we need 
to use and set the number. The defulat will be 10. 

FileProvider : 
9. In res/xml, define your folder structure for FileProvider. The files should be saved in the root folder of the 
external-cache-path tag. 
10. Inside the storage package, create a filesystem package. 
11. Inside the filesystem package, define a class that will be responsible for writing InputStream into a file 
in FileProvider, using Context.externalCacheDir.

Repository 
12. Create a repository package 
13. Inside the repository package, create a sealed class that will hold the result of an API call. The subclasses of 
the sealed class will be Success, Error, and Loading 
14. Define a Repository interface that will contain two methods, one to load the list of URLs and the other to download a file. 
15. Define a DogUi model class that will be used in the UI layer of your application and that will be created in your repository. 
16. Define a mapper class that will convert your API models into entities and entities into UI models. 
17. Define a implementation for Repository that will implement the preceding two methods. The repository will hold the 
references to DogDao, the Retrofit Service class, the Preferences wrapper class, the class managing the files, the Dog mapping 
class, and the Executor class for multithreading. When downloading the files, we will use the filename extracted from the URL. 
18. Create a class that will extend Application, which will initialize the repository. 

ViewModel 
19. Define the ViewModel class used by your UI, which will have reference to Repository and call it to load the URL list and 
download the images. 

UI : 
20. Define your UI, which will be composed of two activities. 
- DogActivity which will display the list of URls and will have a click action to start the downloads. 
- This activity will have a progress bar, which will be displayed when the download takes place. 
- The screen will have a Setting option, which will open the SettingsActivity. 
- The DogSettingsActivity, which will display EditText and Button and save the number of URLs. 


# ------------------ DAGGER 2 ---------------------------

1. DI Framework 

Dagger 1 - Square 
Dagger 2 - Google 


Key functionality of Dagger 2 
1. Injection 
2. Dependencies grouped in modules
3. Components used to generate dependency graphs 
4. Qualifiers
5. Scopes 
6. Subcomponents


@Annotations are the key elements 

Main type of annotations 
1. Provider @Module 
2. Consumer @Inject 
3. Connector @Component 

# Hilt ------------------------

- less boiler plate codes 
- build on top of Dagger 



# PostsApp 
- App that connects to a sample API, https: //jsonplaceholder.typicode.com/posts 
- use the Retrofit library 
- retrive a list of posts from the web page, which will then be displayed on the screen. 

We will need:
1. A network component that is responsible for downloading and parsing the JSON file. 
2. A repository that accesses the data from the API layer. 
3. A ViewModel instance that accesses the Repository. 
4. An activity with RecyclerView that displays the data.

Steps:
1. create an activity. 
2. add api package where your api calls are stored. 
3. create a repository package. 
4. define a repository interface with one method, returing LiveData with the list of posts. 
5. Create the implementation for the repository class. 
6. Create a ViewModel instance to call the repository to retrieve the data. 
7. Create an adapter for the rows of the UI. 
8. Create the activity that will render the UI. 
9. set up a Hilt module that will initialize the network-related dependencies. 
10. Create a Hilt module that will be responsible for defining the dependencies required for the activity.


# Activity Transitions - built in support by Android 

1. Explode : this moves views in or out from the center
2. Fade: This view slowly appears or disappears
3. Slide : moves view in or out from the edges. 

































