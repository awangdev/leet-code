This document is to document the learning process for **System Design**.
A few concepts to research:
How to split data between servers? How do multiple server communicate? MapReduce?
How does server process heavy calculation in background? Threading? NodeJS threading?

## Good Concepts:
- For any system, consider: how much data does the system handle? how much data does the system need to store?
- Popular hashing algorithm: like 35.      What about md5?
- Scalibility

## Steps:
1. **Scope** the problem: Don't make assumptions; Ask questions; Understand the constraints and use cases.
2. Sketch up an **abstract design** that illustrates the basic components of the system and the relationships between them.
3. Think about the **bottlenecks** these components face when the system scales.
4. Address these bottlenecks by using the fundamentals principles of **scalable** system design.

- Mine Usual steps: might be too much details on scoping but less efforts onto bottlenecks and scalibility issues.
1. Identify the key requirements && constraints
2. Identify the users && Server
3. Identify inputs/ouputs from different users
*** Calculate the bandwidth and storage: the amount of data the server handles, and the amount of data server stores.
4. Based on (1~3), list out the potential features that needs to be implemented
5. Pick technologies that will be able to implement the features above: 
  * backend server
  * user client
  * method of storage
  * method of data trasmissio
6. How does the system scale? What are the limits and trade-off in current design? What improvmenet can be done to design it differently?
  


## Scalibility lecture
- key concepts:
	Vertical scaling: add more cpu,ram,hard-drive
	Horizontal scaling:add more cheaper computer, distribute traffice && storage into these machiens.
	Caching: save some pre-processed data, like sticky session, pre-quried sqls
	Load balancing: determine which web server to hit; determine which db to hit if having multiple db
	Database replication: for faster read; for reduancy safety
	Database partitioning: for faster performance for some contents, or distributed storage
	Using NoSQL instead of scaling a relational database
	Being asynchronous

- Load Balancer?
	load balancer is even good to protect server: now backend server can be on LAN with load balancer, so they are protected; and we just expose load balancer to clients.
	How does load balancer work?
	There are lots of trade-offs: look at 30:00 of the video. That's all the tradeoffs (http://www.hiredintech.com/system-design/scalability-fundamentals/)
		- round robin: the load balancer works as a fancy DNS machine, take turns to assign task/request to machine 1,2,3,4...etc. (if we do `nsloopup google.com` on google.com, we can see google does that)
		- store states/sessions into a dedicated server. but it indroduces server-failure issue. -use RAID, raplication
			RAID0: strip data at two hardrives, little on each
			RAID1: mirror data. store at both places
			RAID10: combination of RAID0 && RAID1.
			RAID5: 3 drives only 1 of them is used for reduendency.
			RAID6: multiple drives. any drive can die, then replace, and no data lost.
		- split by the type of files request: html server, image server; load balancer
		- split based on actual load (less possible), send package to less busy server
	Options:
	Software:
		ELB: elastic load balancer
		HAProxy
		LVS 
	hardware:
		Barracuda
		Cisco
		Citrix: over-priced, $20k 
		F5
	Problem: sticky session (if you revisit the site multiple times seprately, you will still hit the same server)
		- can store serverID in the cookie (one downside: safety, showed the whole world about server IP). So again, can store a random number/hashed value on Load Balancer. It shows which serverID to hit once revisiting. Remember which back-end server to send cookie to.
- Caching:
	file-based caching approach: send .html out.
		down-side: 1. space. 2. old generated files are really hard to change.
	mysqul-query cashed: for identical sql request
	memcached: store in memory of the idetical query. Like a table. store <key, value>.
		next time when checking, try look up key.
		Down-side: run out of ram. Solution: LRUCahe, remove old records(double-linked-list)

- Replication:
	Master && multiple Slaves
		advantage: 1. Same request/query will be paste to all slaves, so master fail, just put up one salve; 2. If READ heavy, they are redundant server to lots readings.
		disadvantage:
	Must have at least load balancer: that is one-point-failure if just one load balancer

- Partitioning:
	based on user informaiton, like name. Put common user (same last name) into certain servers.

- Bottleneck: data traffice and data storage
- 
------Example
- 1:26:00 of the video (http://www.hiredintech.com/system-design/scalability-fundamentals/)
We have backend web server
Sticky session: use cookie and store server ID
Use load balancer that two web server connects:
Use load balancer to link to the two master db:
	Two master db that talks to each other: master-master replication
Now we need 2 load balancer to prevent one-point-failure at the webServer-db connection.
Now we also need 2 load balancers at the (which web server to hit) level, that is another 1-pointer-failure
Switch for the complex connections: 
	Now we have so many connections: we need switches to handle the connections.
	We need at least two ports on each device to: go to the correct switch. Becareful with loop.
- Eventually we will handle: (1:34:00)
	Scalibility
	Redundancy
	High probablity of up time
	Resolution against failure

- Another big issue:
	What is ISP goes down? The whole package mentioned above go down?
		Amazon solution, another ISP, called avalibility zone: like west, asia, south american.
		How to distribute IP on different data center? Load Balancer at the DNS level, global Load Balancer.  (Note, if in one building, could be staying at this building)
			Well, if a building is gone, once your TTL(Time to live) is expired, you will be re-route to another ISP building.

- Before getting into building:
	Firewall, only a few ports are open: like tcp80, 443, 22 (ssh)
	can allow https before first load balancer inside building, and decrypt it after first load balancer
	at db lever, only 3306 is allowed.
Reason to lock, for example, 3306 not allowed at entering building? Because no one needs to inject query to db from outside of building; sql query usually only need to be done within the building, so lock the building up, don't allow bad sql injection : )



## Design UBER
1. Requirements 
   Same requirements between user && driver: GPS tracking, detect pickup/drop off, cost calculation
   Design a web app (or mobile app) for end user to make/cancel car reservation, make payment, rate driver.
   Design a web app (or mobile app) for driver to accept/decline car reservation, accept payment, rate passenger.
   Extra features beyond basics: car pool, split cost, rush-hour rate calculation.

2. Identify the end systems:
   Backend server that handles communication, data tracking, payment trasaction...etc
   Passenger user app
   Driver user app

3. Inputs and outputs of each end system:
   **Server should store every piece of information received or change to database during this process**
   Server-input:
     * Passenger create account, login, log off
     * Driver create account, login, log off
     * Passenger/Driver payment information
     * driver locations
     * passenger locations
     * GPS location data before && during the trip
     * request to pair driver && passenger
     * request to start trip
     * request to end trip
     * request to drop reservation from driver
     * request to drop reservation from passenger
     * request to calculate cost per trip
     * accept rating, comment, report ... 

   Server-output: 
   	 * ack passenger account generation, login, logoff
   	 * ack driver account generation, login, logoff
   	 * ack driver/passenger payment info and store to db
     * broadcast driver locations to passenger
     * broadcast passenger locations to driver
     * response pair confirmation to driver && passenger with contact information of driver&&passenger
     * start a trip between driver && passenger
     * end a trip between driver && passenger
     * calculate recommended route and send to driver&&passenger
     * response reservation dropped from driver with panelty
     * response reservation dropped from passenger with panelty
     * response cost calculated to passenger and intiate payment charge to passenger
     * response cost calculated to driver and initiate payment to driver
     * store rating, comment, report into database. Trigger messages to coresponding party.

   Passenger-input: data that passenger receives
     * ack of login and ready to pick driver
     * status of drivers around current location
     * auto-matched driver and related information about driver, car condition, price rate
     * confirmation of car reservation with driver's info
     * trip route and estimated time
     * GPS tracking of car arriving or GPS tracking during the trip
     * notification of payment
     * notification of canceled trip
     * prompt to rate and comment
     * phone call from driver (extenal)

   Passenger-output: action that passenger takes
     * create account
     * login
     * add payment information
     * put current location and destination location
     * select type of uber car, and start searching(matching)
     * accept/decline auto-matched driver
     * call driver (extenal)
     * cancel reservation
     * put comment, rating, or report to the system
     * automatically pay (be charged) of the trip cost from server

   Driver-input:
     * ack of login and ready to start driving
     * auto-recommended passenger's information: location, passenger rating
     * confirmation of reservation && current waiting location
     * trip route and estimated time
     * GPS tracking of car arriving or GPS tracking during the trip
     * notification of payment
     * notification of trip cancelation from passenger
     * prompt to rate and comment passenger
     * phone call from passenger (extenal)
     * receive payment from server

   Driver-output:
     * create account
     * login and set status avaialbe
     * add payment info
     * accept/decline recommended match
     * call passenger (extenal)
     * cancel reservation
     * put comment && rating of passenger


4. Summarize core features and tools to implement
   * account management: 
   * payment integration: paypal integration? some other credicar processing tools
   * matching algorithm: core problem to solve. Figure out the cheapest match for passenger, and time&&cost efficient match for driver. There might be lots of constraints to think about for the matching algorithm: cost&&time to pick up passenger around multiple driver, based on driver preference to filter type of trip (farest distance to travel)
   * trip process monitoring, notification: RESTful calls between server && driver && passenger. 
   * GPS location tracking and route calculation: Google map?
   * trip cost calculation: calculated based on route length, trip time, rate at specific time, complains filed by passenger
   * rating && comment system: RESTful post from driver/passenger

5. Technologies potentially needed:
   Tools:A server to handle RESTful calls, support live connection between nodes, handle trip matching algorithm, handle cost calculation.
   * Node.js with expressJS: provide RESTful API 
   * Socket.io for concurrent communication between server, user, driver in the same socket room.
   * Data storag: Sql or non-sql? Why? Later on, it seems easy to split data by region, so non-sql db seems okay to use.
   * Algorithm: How does heavy calculation handled on Nodejs? Should it be something to do with multi-threading?

6. How does the system scale? What are the limits and trade-off in current design? What improvmenet can be done to design it differently?
   First, assume everything is running based off one giant server: all conversations and calculation are handled via this server.
   Second, the size of data might be too costly to search, calculate, and it's not practical to store everything on one machine:
     * user/driver account info might be too large
     * concurrent calculation for trip matching
     * number concurrent trip monitoring and conversation
     * concurrent calculation for trip cost
   Therefore, it seems wiser to split onto multiple servers. 
   Third question: in which way to split the data? It seems we need to treat different type of data separately
     * For actively moving driver and user: we can store their location data dynamically by region. Once moved into/out of one regin, poll the information from one server into another server. Here, it's wise to use HashTable to store as <userID, everything about this user>. Calculation algorithm may also happen on this server, because both driver && passenger are on this server, defined by region. 
     * Now we've splitted data into servers by region. However, for same region, it might be too much work for one machine. We need to split data aross different machines for same region. Here, use another Hash lookup table to store <live tripID, server ID> to split data into even smaller sections to fit in different server. Now we can have more **regionServer**.
     * A improvement that can be done: for relative static information account,payment,rating, they are only used once or twice during a trip. It might be okay to store these information on a type of server that specifically handles account look up, let's call it **accountServer**. Again, there might be too much account info to store, we can build lookup tables to store <accountID, serverID>. Double again, do MapReduce to hash the keys so that we can split the look up table if the table is too big to store on one server.
