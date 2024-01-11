# RESTfull API

## disadvantages of serverside rendering

- full page needs to refresh
- more data (HTML) over the wire
- Slower
- 'Data' is not accessible for third-party

## Creating an API

- uses "just" for data
- Data in JSON or XML format
- Open for many types of clients
- Can be built using ASP.NET Core (MVC)

## Creating a RESTful API

- REST = Representational State Transfer
  - Address resources through URLs
- Embraces HTTP standards and follows a few rules
  - the verb (GET, POST, PUT, ...) has a certain meaning
  - Endpoints return a status code and (if relevant) JSON data

## HTTP verbs

GET = give me a resource

POST = Create a new instance of a resource

PUT = Update an existing resource

DELETE = Delete a resource

## JSON

- Compact syntax
- can be consumed by many technologies
- is readable for humans

## Creating an API wit ASP.NET Core

- Controller
- Action methods return data (instead of a view)
  - JsonResult or ObjectResult
- Attribute-based routing (instead of convention-based routing)
- Other concepts are identical

## Configuring the application

Program.cs

- Dependency Injection
  - builder.Services.AddControllers();
    -Not needed when builder.Services.AddControllersWithViews(); is present
- Middleware configuration
  - app.MapControllers();
    - Activates attribute-based routing
    - Not needed when app.MapDefaultControllerRoute(); is present

## API Controller

Name ends with 'Controller'

inherit from ControlerBase

- adds support for access to HttpContext, request
- Same functionality as Controller base class, but without View support

Contains action methods

- Responsible for returning data (json)

## 2 types of routing

ASP.NET MVC -> map incoming HTTP request (based on URL) to an aciton on a controller

2 types of mapping:

- Convention-based
  - matching using route templates
  - Typically used for controllers that return views (html response)
- Attribute-based
  - Matching based on attributes in controllers
  - Typically used for API controllers (json response)

## Attribute-based routing

Controller gets a [Route] attribute

- Requests for urls that start with its value will be handled by one of the controller actions
- [controller] is repaced by the name of the controller
  - E.g. actions of this controller will be accessible via api/[controller]

## HTTP verbs

HTTP berv is used to find the action that needs to execute

E.g. [HttpGet] attribute

- GET request for api/Pie -> GetAll() action is executed

Controller can have multiple actions for the same verb

Verb attribute contain template to distinguish between them

- E.g. [HttpGet("{id}")]
  - Matches for GET requests to api/pie/{id}

## Data

Single object instance or list of object instances

Will be serialized into JSON (default behavior of ASP.NET Core)

## Status code

ControllerBase class has helper methods

- Ok(data) -> Return status 200 and data
- BadRequest() -> Return status 400
- NotFound() -> Return status 404
- NoContent() -> Return status 204

## Consume an API from a browser

Use the fetch api to get data

- Send and load data in the background
  - E.g. send a search term to an API and receive matching pies
- Partial page update
  - E.g. only replace the HTML elements that show the search results

## jQuery & AJAX

jQuery is a commonly used Javascript library

Simplifies JavaScript development

Makes it easy to find elements, handle events and perform operations on the DOM

It can be used to update a website, e.g. after receiving JSON data form a REST API
