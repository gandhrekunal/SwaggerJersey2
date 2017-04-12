# Jersey2 + Swagger Sample App

## Overview
This is a java project to build a stand-alone server which implements the OpenAPI Spec.  You can find out 
more about both the spec and the framework at http://swagger.io.

### To run (with Maven)
To run the server, run this task:

```
mvn clean package mvn tomcat7:run
```

This will start tomcat embedded on port 9090.

### Testing the server
Once started, you can navigate to http://localhost:9090/api/api-doc/swagger.json to view the Swagger Resource Listing.
This tells you that the server is up and ready to demonstrate Swagger.

### Using the UI
There is an HTML5-based API tool bundled in this sample--you can view it it at [http://localhost:9090](http://localhost:9090). This lets you inspect the API using an interactive UI.  You can access the source of this code from [here](https://github.com/swagger-api/swagger-ui)

