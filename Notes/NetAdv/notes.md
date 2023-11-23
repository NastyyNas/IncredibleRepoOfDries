# MVC

MVC is a ASP.NET Core Application framework

## Model - View - Controller
 - Separation of concerns
 - Loose coupling

=> promotes testability and maintainability

Model
 - the data the user works with
View
 - Displays data to the user
Controller
 - Glue between model and view
 - works with model classes
 - Provides data for the view to display

## MVC pattern - Flow

- http request arrives
- Mthod of a controller is invoked
- view is written to http response

the request arrives at the controller
the controller updates the view and the model
the view gets its data data from the model

## Model
Domain data & logic to manage data
 - Can be in other layers (domain layer, applogic layer, infrastructure layer)
Simple API
 - Hides (encapsulates) the details of managing the data

## Controller
Manager / Traffic agent within the MVC pattern
 - http request is picked up by a controller action method
 - Action method has to determine the response
 - Delegates details to the model

## View
- HTML template
- Contains Razor code
- no logic

## ViewStart

File that contains code that needs to be executed whenever a View is rendered

## ViewImports

File to import directives in multiple Views

# MVC - Routing

## types of routing
ASP.NET MVC = map incoming HTTP request to an action on a controller

2 types of mapping
 - Convention-based
 - Attribute-based

## Convention-based routing
- Define one or more routes
- Route is a URL pattern that is mapped to an endpoint
- URL of incoming HTTP request is compared with the patterns

# Entity Framework

## Entity framework Core
Entity Framework Core is a lightweight, extensible, open source and cross-platform version of the popular Entity Framework data access technology
EF Core can serve as an ORM, which;
- Enables .NET developers to work with a database using .NET objects
- Eliminates the need for most of the data-access code that typically needs to be written.

EF Core began as an ORM
- Object
- Relational
- Mapper

## High level ORM benefits
Developer productivity
Coding consistency


## EF core basic migrations workflow
Define/change Model -> Create a migration file -> Apply migration to DB or Script

EF Core Migrations are source-control friendly

Creating and executing migrations happens at design time

## query workflow
Express and execute query -> EF Core reads model, works with provider to work out SQL -> Sends SQL to database -> Receives tabular results -> Materializes results as objects -> Adds tracking details to DbContext instance

# NUnit

## AAA-Pattern
- Arrange: you instantiate your objects/variables you need to do the test
- Act: You run the method you want to test
- Assert: You check if your result equals the expected result

## Asserts
Asserts tell the test runner wether a test has passed or failed

## qualities of good tests
- Fast
- Repeatable
- Isolated
- Trustworthy
- Valuable

# Test lifecycle
Setup -> Test -> Teardown

