# User Documentation for t06


## I. Feature List:

a. With our carpooling project, the system administrator can, via a Web frontend:
- gain an overview of the ride sharing net work
- check the status of all active drivers and passengers in the network
- get a list all top drivers and passengers


b. With our carpooling project, the driver user can, via an Android frontend:
- sign up as a driver
- login as a driver
- create a trip
- get information when passenger book their trip
- cancel their trip
- give a score to their passenger

c. With our carpooling project, the passenger user can, via an Android frontend:
- sign up as a passenger
- login as a passenger
- search for a trip
- get a list of possible trip options
- select a trip
- get information of the diver when passenger book a trip
- give a score to their drivers

d. BONUS part
- authentication: user has to login in before create/search for a trip
-authorization: checks to ensure that an authenticateduser is allowed to access a given API endpoint



## II Setup Instructions:


1. Clone the repository (https://github.com/ECSE321-Fall2018/t06.git)




## III. Operations:

Operation #1: Create a new account
- As a passenger / driver
- I can create a new account
- So I can start to use this product
Process (passengers):
1. I open the application on my mobile device or web browser
2. Then I select “sign up”
3. Choose “Passenger”
4. Next I enter my name, and phone number (vehicle information?)
5. I enter my password twice (Receive an OTP?)
6. Finally I am prompted to confirm my account creation
Process (drivers):
1. I open the application on my mobile device or web browser
2. Then I select “sign up”
3. Choose “Driver”
4. Next I enter my name, and phone number
5. Input the driver license ID
6. I enter a password twice (Receive an OTP?)
7. Finally I am prompted to confirm my account creation
Error Cases:
1. Inconsistent password
2. Invalid license ID (issue)(letter)(regular expression)
3. phone number have already been registered



Operation #2: Create a new journey
- As a driver
- I can create a new journey
- So I can advertise the journey to passengers
Process:
1. Given that I have entered the driver interface
2. Select “Create a new journey”
3. Input date and time of departure
4. Input the information of my vehicle
5. State the amount of available seats
6. Enter the locations I want to stop (Up to 5) and corresponding time
7. Input the cost for each person going to each stop (A-B, B-C…)
8. Confirm the creation
Error Cases:
1. Input more than 5 stops
2. Invalid stops
3. Invalid price
4. six boxes for entering locations(1 start point+5 stops) 5 boxes for price between each
locations. six boxes for entering the time the driver will be in the corresponding locations.
If driver doesn’t follow the format (skip one box and enter into the box behind), then,
throw an exception.
5. haven’t end last journey



Operation #3: Select the journey
- As a passenger
- I can search the advertisements by relevant criteria
- So I can select the most suitable journey
Process:
1. Given that I have entered the passenger interface
2. Input my starting location, destination,depart time interval , and search for related
journeys.
3. Sort the advertisements by the preferred criteria (price, car type(alphabetically?))
4. Accept the driver’s offer on the application
Error Cases:
1. Invalid destinations
2. Invalid time interval
3. Haven’t end last journey
Verification (both drivers and passengers)



Operation #4: Confirm arrival
- As a driver
- I can confirm arrival
- So that the information of my journey can get updated
Process:
1. Given that I have entered the driver interface
2. Choose “my journey”
3. Then select the corresponding one
4. Confirm arrival (show the next destination)
5. Rate the passenger(s) after each stop
6. When the driver arrived at the final destination, select “End the Journey”



Operation #5 : Estimated Time of Arrival
- As a passenger
- I want to know my estimated time of arrival
- So that I can be sure that I will reach my destination on time
Process:
1. After I select a journey/advertisements, the estimated time of arrival should appear on
the screen.



Operation #6: Gain an overview of the car sharing network
- As a system administrator
- I would like to gain an overview of the car sharing network
- So that I can check the status of all active drivers and passengers in the network
- As a system administrator
- I would like to see the scores of each driver and passenger
- So that I can list the top driver & passenger on the app
Process (system administrator):
1. There are lists of orders on the website
2. system administrator should be able to search for the order by entering the driver’s
name/ passenger’s name/ driver’s license ID/ destination.



Operation #7: Check history journeys
- As a driver
- I would like to view up to 100 history journeys I have created and the comments left by
the passengers
- As a passenger
- I would like to view up to 100 history journeys I have joined and the comments left by the
drivers

## IV. API Design（Final Version）:

### Carpooling Controller:

1. createTrip
2. getTrip
3. finishTrip
4. getPassengerInPeriod
5. getDriverByTrip

### User Controller

1. login
2. signUpDriver
3. signUpPassenger
4. getUserById
5. getDrivers
6. getPassengers
7. editUser


### Booking Controller
1. creatBooking
2. finishBooking
3. getCitiesInPeriod


## V. Andiod Frontend:
### Driver's app

![2018-11-23 10 25 38](https://user-images.githubusercontent.com/43216920/48964118-2e2e6180-ef6f-11e8-889d-a4359a54743a.png)

Modify Existing Advertisement




![2018-11-23 10 25 49](https://user-images.githubusercontent.com/43216920/48964119-3090bb80-ef6f-11e8-8090-50e600e9d06e.png)

Cancel Existing Advertisement



![2018-11-23 10 25 57](https://user-images.githubusercontent.com/43216920/48964120-325a7f00-ef6f-11e8-9a90-8990701c04d5.png)

Confirmation Page after Successful Advertisement Closure



![2018-11-23 10 26 04](https://user-images.githubusercontent.com/43216920/48964121-34244280-ef6f-11e8-9d02-97f3a97ae35f.png)

Final Design - Users have to select a sorting criteria


![2018-11-23 10 26 15](https://user-images.githubusercontent.com/43216920/48964123-3be3e700-ef6f-11e8-9f3c-e595f50bf166.png)

Initial Design - It is optional for users to use the “Sort” function




![2018-11-23 10 26 29](https://user-images.githubusercontent.com/43216920/48964124-40100480-ef6f-11e8-8d13-649f0d8bc2b6.png)

Login Page for Driver



![2018-11-23 10 26 35](https://user-images.githubusercontent.com/43216920/48964127-42725e80-ef6f-11e8-96d0-759c73914b3d.png)
 
 Sign Up Page for Drivers
 
 
 
![2018-11-23 10 26 45](https://user-images.githubusercontent.com/43216920/48964129-44d4b880-ef6f-11e8-90a6-04a0d17544bb.png)

Confirmation Page after Successful Signup


![2018-11-23 10 26 52](https://user-images.githubusercontent.com/43216920/48964131-4900d600-ef6f-11e8-92ab-4c35fe9d0e06.png)

Main Page of Mobile Application for Driver



![2018-11-23 10 26 59](https://user-images.githubusercontent.com/43216920/48964133-4b633000-ef6f-11e8-8877-ddd4701e4c87.png)

Create Journey Advertisement


![2018-11-23 10 27 06](https://user-images.githubusercontent.com/43216920/48964134-4d2cf380-ef6f-11e8-9889-e86d1a10a59d.png)

Confirmation Page after Successful Journey Creation


![2018-11-23 10 27 14](https://user-images.githubusercontent.com/43216920/48964135-50c07a80-ef6f-11e8-8795-a6e7464d50ba.png)

Display of Current Journey Advertisement


![2018-11-23 10 27 20](https://user-images.githubusercontent.com/43216920/48964136-5322d480-ef6f-11e8-88d2-addd53f360be.png)
 
 Confirmation Page after Successful Cancellation of Journey Advertisement


![2018-11-23 10 27 27](https://user-images.githubusercontent.com/43216920/48964137-561dc500-ef6f-11e8-9f49-2e9c1be60f41.png)

Modify Journey Advertisement

![2018-11-23 10 27 34](https://user-images.githubusercontent.com/43216920/48964139-59b14c00-ef6f-11e8-8c97-b3e3a84148f3.png)

Confirmation Page after Successful Modification of Journey 


![2018-11-23 10 27 43](https://user-images.githubusercontent.com/43216920/48964140-5c13a600-ef6f-11e8-991b-9c5bfc28b711.png)

Confirmation Page after Successful Completion of Journey



### Passenger's app


![2018-11-23 10 27 55](https://user-images.githubusercontent.com/43216920/48964141-5e760000-ef6f-11e8-8029-c4d23eef874f.png)

Main Page of Mobile Application for Passenger


![2018-11-23 10 28 03](https://user-images.githubusercontent.com/43216920/48964143-60d85a00-ef6f-11e8-99d2-1873faa57d32.png)

Search Page



![2018-11-23 10 28 11](https://user-images.githubusercontent.com/43216920/48964146-63d34a80-ef6f-11e8-998a-0b76ac27f4c7.png)

Results Page (Sorted based on price)



![2018-11-23 10 28 17](https://user-images.githubusercontent.com/43216920/48964147-6635a480-ef6f-11e8-828e-c5d653ab52b8.png)

Results Page (Sorted based on driver's ratings)



![2018-11-23 10 28 33](https://user-images.githubusercontent.com/43216920/48964154-6f267600-ef6f-11e8-97a5-53b5b1e0d9e0.png)

Destination Arrival Page



![2018-11-23 10 28 41](https://user-images.githubusercontent.com/43216920/48964155-72b9fd00-ef6f-11e8-8a07-b823b1794bd0.png)

Rating Page




![2018-11-23 10 28 47](https://user-images.githubusercontent.com/43216920/48964157-75b4ed80-ef6f-11e8-9c6b-490a2b6e2172.png)

 Rating Confirmation Page




## VI. Project Management:

### Main responsibilities and leadership roles of each team member:

| Team Member     | Main Responsibilities                              |
| ----------------| ---------------------------------------------------|
| Chang Zhou  | Backend controller&tests&database design |
| Andy Zheng     | Backend controller&tests&database design&Modelling&create database  |
| Xirui Zhang   | Backend Database design&Modelling |
| William Wang | Backend controller&tests |
| ShuFen Pun | Software function design|
| Hai Xu | Backend controller&tests&organization|




### Hourly efforts dedicated by each member to the project:

| Team Member            | Deliverable 1 | Deliverable 2 | Deliverable 3 | Deliverable 4 |
| -----------------------|-------------- |---------------|---------------|---------------|
| **Chang Zhou**     | 15             |  15        |     10    |      -     |
| **Andy Zheng**        | 20              |    15     |   20    |    -      |
| **Xirui Zhang**      |15            |     15    |     10    |    -     |
| **William Wang**    | 15           |  15        |     15      |  -          |
| **ShuFen Pun**    | 10            |   15      |    10        |    -       |
| **Hai Xu**          | 15           |     15      |   20        |     -    |




### Overview of major project iterations and progress:



**Sprint 1: Backend Deliverable**

**1. Documentation**

- README file with features and operations of the application
- Wikipage with a brief summary of tasks accomplished 
- Issue documentation and organization 

**2. Tools setup**

- Springboot
- Maven
- Travis CI
- PostgreSQL
- UML Lab
- RESTFUL web service
- JUnit

**3. Model Building**

- Booking.java - To collect information from passengers to generate booking
- Admin.java - To store information of administrators who are able to monitor data of all drivers and passengers.
- Driver.java - Storing of individual driver’s information and associated trips
- Passenger.java - Storing of individual driver’s information and associated trips
- PersonRole.java - Parent class of Driver and Passenger
- Stop.java - Storing of stop’s information for a particular journey 
- Trip.java - Trip’s information, including number of seats available 


**4. Controller Methods (First Version)**

**(i) Carpooling Controller**
- createTrip
- checkDriver
- checkTrip
- searchTrip
- orderTrip
- finishTripByDriver

**(ii) User Controller**
- login
- signUpDriver
- signUpPassenger

**(iii) Search Controller**



**Sprint 2: Android Frontend Deliverable**

**1. Documentation**

- README file with features and operations of the application
- Wikipage with a brief summary of tasks accomplished 
- Issue documentation and organization 


**2. Tools setup**

- AndroidStudio



**3. Driver App**

**(i) Login Signup page**
- login
- signUp


**(ii) Driver Main Page**
- Create Journey
- CurrentJourney


**3. Passenger App**

**(i) Login Signup page**
- login
- signUp


**(ii) Passenger Main Page**
- Search Journey
- Current Journey
- Create Booking


**Sprint 3: Android Frontend Deliverable**


**1. Controller Methods (Final version)**

**(i) Carpooling Controller**

1. createTrip
2. getTrip
3. finishTrip
4. getPassengerInPeriod
5. getDriverByTrip



**(ii) User Controller**

1. login
2. signUpDriver
3. signUpPassenger
4. getUserById
5. getDrivers
6. getPassengers
7. editUser


**(iii) Booking Controller**

1. creatBooking
2. finishBooking
3. getCitiesInPeriod



**2.Deployment**



**3.Webfrontend layout**
### Web App View #1: Fleet Status Overview (Priority: 3/3)
- Include a means of checking the status of all active drivers and passengers in the network
- Provide a listing of all active routes, all active drivers, and all active passengers.

  - Definition of Active Routes: Journey that has been completed but yet to be completed
    - On the frontend web, there will be a column titled "Whether active"
    - true: completed ride
    - false: uncompleted ride
    
  - Definition of Active Drivers and Passengers: Drivers and passengers who are currently on a ride (i.e. in the midst of the journey)
    - On the frontend web, there will be a column titled "At trip"
    - true: Driver or passenger are currently on a ride (ie. in the midst of a journey)
    - false: Driver or passenger are currently not on a ride

- Able to filter any one of these overviews by typing text in a search box associated with each category (routes, drivers, passengers)
The filter functionality should support partial matching.
  - For example, if the administrator types “mon” in the search box of the all active routes category, active routes to “Montréal”,       “Moncton”, and “Edmonton” should appear.

#### Process:

1. Web administrator will login to the web application using the registered username and password. 

![login](https://user-images.githubusercontent.com/43051936/48961593-e0e8ca80-ef43-11e8-9036-706f6cc71784.png)

2. Directed to the landing page

![overview](https://user-images.githubusercontent.com/43051936/48961607-ff4ec600-ef43-11e8-8507-576f30b3e58b.png)

3. Select “View Fleet Status Overview” and be directed to overview page. (Figure 3)

![status overview](https://user-images.githubusercontent.com/43051936/48961618-1392c300-ef44-11e8-8840-ed6734b26fae.png)

4. Select any of the category and be directed to the specific page. 

![routes](https://user-images.githubusercontent.com/43051936/48961626-24433900-ef44-11e8-9ef0-4f4ba9a84438.png)

![drivers](https://user-images.githubusercontent.com/43051936/48961630-29a08380-ef44-11e8-99bd-3ada0919c48f.png)

![passengers](https://user-images.githubusercontent.com/43051936/48961633-2c9b7400-ef44-11e8-9cb5-571b95496b92.png)

5. To filter, web administrator can enter the keyword in the search box and click “Search”. Filtered results will be displayed. 

### Web App View #2: Ranking Overview (Priority: 3/3)
- Allow administrators to identify top performing drivers, most loyal passengers, and most popular routes.

  - Ranking of Top Performing Drivers: Drivers will be ranked by the number of journeys they have created. The greater the number of      journey created, the better the performance.
  - Ranking of Most Loyal Passengers: Passengers will be ranked by the number of trips completed. The greater the number of trips         completed, the greater the loyalty.
  - Ranking of Most Popular Routes: Cities will be ranked by its inclusion in trips. The higher the frequency of the city being           included in journeys, the greater its popularity.

- A ranking view based on completed trips will be used. The filtering of results to contain only completed trips will be done at the backend.
- Able to filter by selecting a timeframe with a start date and an end date.
- In that filtered view, only completed trips within the timeframe will be used to compute the top drivers, passengers, and routes.
  - Eg. Entering a timeframe between January 2017 and December 2017 will produce different results compared to the entering December        2017 and October 2018 as timeframe.


#### Process:

1. Web administrator will login to the web application using the registered username and password. 

![login](https://user-images.githubusercontent.com/43051936/48961593-e0e8ca80-ef43-11e8-9036-706f6cc71784.png)

2. Directed to the landing page 

![overview](https://user-images.githubusercontent.com/43051936/48961607-ff4ec600-ef43-11e8-8507-576f30b3e58b.png)

3. Select “View Ranking Overview” and be directed to the ranking overview page. 

![ranking overview](https://user-images.githubusercontent.com/43051936/48961637-38873600-ef44-11e8-87d0-4a2fa8abe0f9.png)

4. Select any of the category and be directed to the specific page. The default result is the ranking of all trips. 

![driverranking](https://user-images.githubusercontent.com/43051936/48961648-42109e00-ef44-11e8-8e0e-23e02fdd5e4c.png)

![passengerranking](https://user-images.githubusercontent.com/43051936/48961652-46d55200-ef44-11e8-963c-2dc1fc881b3b.png)

![cityrank](https://user-images.githubusercontent.com/43051936/48961655-4b9a0600-ef44-11e8-9829-5f321dc4f8f3.png)


5. To filter, web administrator can enter the start date and end date in the search boxes and click “Search”. Filtered results will be displayed. 
