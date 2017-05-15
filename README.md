Bus Route Challenge
The solution to the bus route challenge.

REST API

Your micro service has to implement a REST-API supporting a single URL and only GET requests. It has to serve http://localhost:8088/api/direct?dep_sid={}&arr_sid={}. The parameters dep_sid (departure) and arr_sid (arrival) are two station ids (sid) represented by 32 bit integers.

The response has to be a single JSON Object:
```
{
  "$schema": "http://json-schema.org/draft-04/schema#",
  "type": "object",
  "properties": {
    "dep_sid": {
      "type": "integer"
    },
    "arr_sid": {
      "type": "integer"
    },
    "direct_bus_route": {
      "type": "boolean"
    }
  },
  "required": [
    "dep_sid",
    "arr_sid",
    "direct_bus_route"
  ]
}```
The direct_bus_route field has to be set to true if there exists a bus route in the input data that connects the stations represented by dep_sid and arr_sid. Otherwise direct_bus_route must be set to false.

Example Data
```
Bus Routes Data File:

3
0 0 1 2 3 4
1 3 1 6 5
2 0 6 4
```
Query:
```
http://localhost:8088/api/direct?dep_sid=3&arr_sid=6
```
Response:
```
{
    "dep_sid": 3,
    "arr_sid": 6,
    "direct_bus_route": true
}```

Note:
```
1) Due to other commitments and lack of time could not add unit test cases.
2) Alternative to Route Constants I could have used property file to make it more configurable.
3) I could have also used the docker plugin in the pom.xml to make the application dockerised.
```
Please let me know If I have to update the project with unit test cases, and other changes. Happy to do so.
