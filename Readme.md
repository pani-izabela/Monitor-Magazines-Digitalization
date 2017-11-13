##Project Title
Monitor-Magazines-Digitization is an application monitoring magazine’s digitization progress and calculate costs of this process.
It relies on database build with two tables. First one contains magazines to be digitized. Second database include digitization stages. Actually, the application envisions four stages. Each one of them entail different expense. To complete digitization process, all magazine tomes must go through each stage. At the time, digitization of respective toms may take place at alternative stages.

Application simplifies achieving information, how much money is needed at the moment, for digitization of all magazines we have in base. Information about expenses and accomplishment time enables employment planning in the short term. It also facilitate achieving information about digitization costs of one specific magazine title. It’s very useful when, for example, in order to write a founding request supporting the venture. Moreover, the App make it easier to prepare weekly reports about current digitization status.

##Features
- generates reports in csv and pdf format.
- allows to display single magazine as well as all of them
- allows to add magazines to database, to edit and delete it from database 
- calculates and returns each digitization stage expenses. It also shows total amount of stage costs for a specific magazine and for all magazines in database
- calculates and returns number of hours needed for stage III and IV execution and what’s more, it shows time amount for that magazine. It calculates and returns time needed for digitization of all magazines in database. Stages: I and II are executed by external entity, which is covered depending on amount of articles and pages in magazines tome. It also features costs amount of these stages for a specific magazine
- returns information at what digitization stage currently is the magazine. how many toms went through particular stage and how many still must go through, i. e. how many toms have been scanned and how many still awaits in queue. It shows how many toms have been wrought in large or small pdf form and how many are yet to be done
- includes individual tests for services and controllers methods

##Built With
- Gradle
- MySQL
- framework Spring
- Lombok

##Authors
Izabela £ach

##Acknowledgments
The inspiration for creating this app  were my daily duties which are related to project coordination - the database of academic journals of the humanities and social sciences bazhum.muzhp.pl i bazhum.pl