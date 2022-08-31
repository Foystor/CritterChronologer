# Critter Chronologer

Real-world project from [Java Web Developer Nanodegree](https://www.udacity.com/course/java-developer-nanodegree--nd035) at Udacity.

Critter Chronologer is a Software as a Service application that provides a scheduling interface for a small business that takes care of animals.

This Spring Boot project will allow users to create pets, owners, and employees, and then schedule events for employees to provide services for pets.

## Summary

- [Demo](#demo)
- [Goals](#goals)
- [Technologies](#technologies)
- [Usage](#usage)
- [Database](#usage)
- [Testing](#testing)
- [Built With](#built-with)
- [License](#license)

## Demo

https://user-images.githubusercontent.com/74621252/187635151-e54ad5b8-da6b-4d18-a121-d2b37c721eae.mov

## Goals

This application will store all the relevant data in a ```MySQL Database``` and allow a predefined controller layer to send and receive updates. We’ll have to design all the data objects on the back end and create the data and service layers to update and retrieve them.

## Technologies

- Define and use data in the application across the layers in `Multitier Architecture`.
- Use the external `MySQL Database` as the primary data source.
- Use an `H2` in-memory database for unit tests.
- Use `Hibernate` to automatically generate appropriate tables to relate the data.
- Use `Spring Data JPA` to handle persistence requests pertaining to the `Entities`.
- Use `Data Transfer Objects(DTO)` to represent the structure of request and response data.

## Usage

A ```Postman``` collection has been provided. Each entry in this collection contains information in its `Body` tab if necessary.

1. Provide the appropriate connection url and credentials in `application.properties` file for `Spring` to configure your external `DB`.
   - _Part of this project involves configuring a Spring application to connect to an external data source. install a database using [this instruction](#database)._
2. Open ```Postman```.
3. Select the `Import` button.
4. Import the file under `src/main/resources/Udacity.postman_collection.json`.
5. Expand the `Udacity` folder in ```Postman``` and send requests.
6. Review the schema in `MySQLWorkbench` or in another tool of your choice.

## Database

 Before running this project, you must install a database to connect to. Here are [instructions for installing MySQL 8](https://dev.mysql.com/doc/refman/8.0/en/installing.html).

You should install the `Server` and `Connector/J`, but it is also convenient to install the `Documentation` and `Workbench`.

Alternately, you may wish to run `MySQL` in a `Docker` container, using [these instructions](https://hub.docker.com/_/mysql/).

After installing the `Server`, you will need to create a user that your application will use to perform operations on the server. You should create a user that has all permissions on localhost using the sql command found [here](https://dev.mysql.com/doc/refman/8.0/en/creating-accounts.html).

Another `SQL` database may be used if desired, but do not use the `H2` in-memory database as your primary datasource.

## Testing

Tests pass under the following conditions:

* `testCreateCustomer` - **UserController.saveCustomer** returns a saved customer matching the request.
* `testCreateEmployee` - **UserController.saveEmployee** returns a saved employee matching the request.
* `testAddPetsToCustomer` - **PetController.getPetsByOwner** returns a saved pet with the same id and name as the one saved with **UserController.savePet** for a given owner.
* `testFindPetsByOwner` - **PetController.getPetsByOwner** returns all pets saved for that owner.
* `testFindOwnerByPet` - **UserController.getOwnerByPet** returns the saved owner used to create the pet.
* `testChangeEmployeeAvailability` - **UserController.getEmployee** returns an employee with the same availability as set for that employee by **UserControler.setAvailability**.
* `testFindEmployeesByServiceAndTime` - **UserController.findEmployeesForService** returns all saved employees that have the requested availability and skills and none that do not.
* `testSchedulePetsForServiceWithEmployee` - **ScheduleController.createSchedule** returns a saved schedule matching the requested activities, pets, employees, and date.
* `testFindScheduleByEntities` - **ScheduleController.getScheduleForEmployee** returns all saved schedules containing that employee. **ScheduleController.getScheduleForPet** returns all saved schedules for that pet. **ScheduleController.getScheduleForCustomer** returns all saved schedules for any pets belonging to that owner.

## Built With

* [Spring Boot](https://spring.io/projects/spring-boot) - Framework providing dependency injection, web framework, data binding, resource management, transaction management, and more.
* [Google Guava](https://github.com/google/guava) - A set of core libraries used in this project for their collections utilities.
* [H2 Database Engine](https://www.h2database.com/html/main.html) - An in-memory database used in this project to run unit tests.
* [MySQL Connector/J](https://www.mysql.com/products/connector/) - JDBC Drivers to allow Java to connect to MySQL Server

## License

[MIT License](LICENSE)
