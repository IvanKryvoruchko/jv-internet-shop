# Internet-shop
# Table of Contents
* [Project purpose](#purpose)
* [Project structure](#structure)
* [How to run](#how-to-run)
* [Design Patterns](#design-patterns)
* [Programming Principles](#programming-principles)
* [Refactoring Techniques](#refactoring-techniquess)
* [Authors](#authors)
# <a name="purpose"></a>Project purpose

This is a template for creating an e-store.
<hr>
It implements typical functions for an online store. 
It has login and registration forms.

Available functions for all users: 
* view menu of the store
* view items of the store
* registration
* log in
* log out
  
Available functions for users with a USER role only: 
* add to user's bucket
* delete from user's bucket
* view all user's orders
* complete order
* view a lists of selected items in user`s bucket

Available functions for users with an ADMIN role only:
* add items to the store
* delete items from the store
* view a list of all users
* delete users from the store

<hr>

# <a name="structure"></a>Project Structure
* Java 11
* Maven 4.0.0
* javax.servlet-api 3.1.0
* jstl 1.2
* log4j 1.2.17
* mysql-connector-java 8.0.18
<hr>

# <a name="how-to-run"></a>How to run
Open the project in your IDE.

Add it as maven project.

Configure Tomcat:
* add artifact
* add sdk 11.0.3

Add sdk 11.0.3 in project struсture.

Use file /jv-internet-shop/src/main/resources/init_db.sql to create schema and all the tables required by this app in MySQL database.

At /jv-internet-shop/src/main/java/com/company/internetshop/factory/DaoAndServiceFactory class use username and password for your DB to create a Connection.

Change a path in /jv-internet-shop/src/main/resources/log4j.properties. It has to reach your logFile.

Run the project.

If you first time launch this project: 
 * Run InjectDefaultUsersController by URL = .../internet_shop_war_exploded/injectDefaultUsers to create default users.

By default there are one user with an USER role (login = "User1", password = "1") 
and one with an ADMIN role (login = "Admin1", password = "1"). 
<hr>

# <a name="design-patterns"></a>Design Patterns
### Factory Method
The [DaoAndServiceFactory](./src/main/java/com/company/internetshop/factory/DaoAndServiceFactory.java) factory class has methods that creates Dao and Service objects.

### Singleton
The project utilizes the Singleton pattern for Dao and Service objects. This ensures only one instance of these classes exists, minimizing memory usage.
Instances of these classes are stored in [AnnotatedClassMap](./src/main/java/com/company/internetshop/lib/AnnotatedClassMap.java) and retrieved by getImplementation method by interface class.

### Chain of Responsibility
There are [AuthenticationFilter](./src/main/java/com/company/internetshop/web/filters/AuthenticationFilter.java) and [AutorizationFilter](./src/main/java/com/company/internetshop/web/filters/AutorizationFilter.java). 
Each of them decides either to process the request or to pass it to the next handler in the chain.

# <a name="programming-principles"></a>Programming Principles
### Single Responsibility
Each class or module has a single responsibility. For example, dao (data access objects) has only one responsibility, they are used for database communication.

### Coding to interfaces, not implementation
Interfaces define what the behavior of the object should be, creating an interface first, defining its methods and then creating the actual class with the implementation.
For example, [BucketService](./src/main/java/com/company/internetshop/web/filters/AuthenticationFilter.java) interface define behaviour and
[BucketServiceImpl](./src/main/java/com/company/internetshop/service/impl/BucketServiceImpl.java) are implemented that behaviour.

### YAGNI
Project implements typical functions for an online store, other functionally will be added on demand.

### KISS
Classes and methods have simple logic that makes it easy to understand for what each method was written.

### Dependency Inversion

High-level class don't depend on low-level classes, they both depend on abstractions.
For example service layer classes depends on dao abstractions not on implementations. If you want to change tha way of communication with database to anouther you only need to create new implementation of dao interface and inject it to service.

# <a name="refactoring-techniquess"></a>Refactoring Techniques

### Rename Method
Methods have understandable names that let the programmer know what the method does.

### Extract Class
Moved logic of hashing password and generating salt to [HashUtil](./src/main/java/com/company/internetshop/util/HashUtil.java) class 
from [UserServiceImpl](./src/main/java/com/company/internetshop/service/impl/UserServiceImpl.java)

### Encapsulate Field
For model classes made the fields private and created access methods for it.

# <a name="authors"></a>Authors
[Ivan Kryvoruchko](https://github.com/IvanKryvoruchko)
