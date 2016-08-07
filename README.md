# SharedShopper
A simple app that enables two people to share a shopping list.

Created mainly for me to practice and demonstrate various techniques.

External dependencies used:
  <ul>
    <li>RxJava</li>
    <li>RetroFit</li>
    <li>OkHttp</li>
    <li>Espresso</li>
  </ul>

For the app I have used the model view presenter (MVP) design pattern with elements of Clean architecture.

<h4>Model</h4>
The model/data layer consists of an SQlite database and a RetroFit HTTP client which retrieves data from an SQL Database through the RESTful API I have built in PHP (See 'SharedShopper API' repository). (In clean architecture these are the Entities).

I have used an Interactor layer inbetween the model and presenter layers for further abstraction and to help with future testing, this contains the app specific business logic. 

The Interactor layer uses RxJava to handle the RetroFit calls and the updating of the local database with the retrieved data. It also checks for local changes that have yet to be synced with the server side database. If it finds unsynced changes the changes are then synced.

Finally, i have used repositories as boundary layers between the Interactor and presenter layers. This layer is responsible for subscribing to the RxJava observables created in the Interactor layer and then passing the received information to the Presenter once data has been received.

For testing purposes in the 'Mock' flavour i have mocked the data layer using dependency injection so i can perform Espresso instrumentation tests without having to perform network or database calls.
<h4>View</h4>
The view layer consists of Fragments/Activities, containing all of the Android specific UI. The views are relatively passive with the user inputs being passed to the presenter and the presenter also handling the calling of view methods to update the UI.

Lifecycle events are handled in this layer to ensure that rotating the screen or opening and closing the app doesn't result in a blank screen or excessive loading.
<h4>Presenter</h4>
The Presenter layer receives user input information from the View, calls the neccesary method in the repository layer and then once the needed information is received then updates the View with the new information.

<h3>Things to add/change</h3>
In future i will be adding/changing:
  <ul>
    <li>More tests, both unit tests and instrumentation tests perhaps utilising the Mockito library.</li>
    <li>Retrolambda, to reduce boilerplate code when using RxJava and other anonymous class usage.</li>
    <li>Maybe dagger to further utilise dependency injection.</li>
    <li>Further abstraction if necessary to make testing easier</li>
    <li>Better use of SOLID design principles</li>
  </ul>
  
Please see my 'ShoppingList' repository for a look at the local version of this app.


Articles that have helped me and may be of interest:</br>
http://blog.8thlight.com/uncle-bob/2012/08/13/the-clean-architecture.html </br>
http://blog.danlew.net/2014/09/15/grokking-rxjava-part-1/ (and the rest of the series) </br>
https://medium.com/@andrestaltz/2-minute-introduction-to-rx-24c8ca793877#.p4ge10b4b


