# MissionCrudApplicationRepo
This is my new project repo that i am working on for my first project for QA. 

For my SFIA1 prpject as part of my learning path to become a Technology consultant with QA, I am learning how to create and manage a CRUD application. CRUD stands for the following 
* Create
* Read
* Update
* Delete 

I think that the best way to demonstrate my skills with the technologies I have learnt so far is to make a fun application - namely a 'Mission Planner'! This is not just any old boring to-do list, but this is an application which I intend to be a bit more fun and entertaining. This application can do some simple CRUD actions. Please let me know if you have questions regarding my application by email on lavellej286@gmail.com.

In summary my project is basically a to-do list in its function, using two tables. The backend of the project was made using SpringBoot. The database is a MySQL database hosted on GCP which i unfortunately couldnt get to connect to my front end. I also have a test database using the H2 in memory used in Java.
  * Database Structure - I decided to use two tables in my database in order to push myself to create the best project possible. I did make the database i intended to use wih SQL statements in the Google cloud terminal. My database has been designed with a one to many relationship between the Mission table and the To Do table.
  
 
     Project Tracking and Planning -  
     
I use Jira to plan and track my project and ensure that the different stages of the project were carried out in relatively good time. I split the responsibilities and the use stories into 4 areas - the create, read, update and delete as this is the main purpose of the project. In my project you will see some photos to demonstrate my use of Jira. 
Below are some images of different stages in my jira board.

![Image of jira1](https://i.imgur.com/rmIlC2q.png)
![Image of jira2](https://imgur.com/3x6g7tt.png)
![Image of jira3](https://imgur.com/TeGUIZJ.png)
![Image of jira4](https://imgur.com/xp9Rk62.png)

Risk Assesment - Below you will also find a risk assesment which detisls the potential risks of using my project - no matter how big of small.
My application does connect from back end to a test h2 database, and it also connects to the front end of the application but I can not get any data persist to the database. I did make two integration tests  but i could also not manage to get them to work. 

I have some succesful Mockito Testing, with the missionservice layer namely but If i had more time i would ensure that i got a full coverage of testing in my application. 


 Front End Design - I designed the front end using HTML, CSS and Javascript. I did this using the Visual studio code. 
 
Known Issues - there are unfortunately many issues with my project. Whilst I have all of the component pieces necessary for a successfully built application, I have not been able to connect all of the pieces as well as I could have together. So at the moment my application isn't actually functioning for the following reasons;
- the javascript has some minor errors meaning it is not fully mapping to the information i have writted in Spring.
- I cannot manage to connect the GCP database to the application and have data persist on it. 

Future Improvement - there are many areas I can imrove on with my project, like to following; 
- ensuring that the GCP instance can connect to the application.
- ensure data persists.
- work out the errors in the Java script to make sure the requests are getting handled properly. 
- When I was working on my project, I had to adandon my previous attempt as the errors when building where multiple and the best way to solve this issue was simply by starting again. This sadly left me with less time to fully complete the project. Given more time I am confident that i can provide a fully functioning appliction.
 
Thanks for reading.
 
 
 
 
 










