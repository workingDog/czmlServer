# An Akka websocket server and a Cesium client to excersice the ScalaCZML library 

The [ScalaCZML](https://github.com/workingDog/scalaczml) is used here with a sample Akka websocket server to 
send a CZML document to a Cesium client, which then displays the result on the globe.

## How to use
 
Download and unzip this repository. 

Install [Cesium](https://cesiumjs.org/) and look at the [getting started tutorial](https://cesiumjs.org/tutorials/cesium-up-and-running/) 
to setup the web Node server, typically in a terminal: 

    node server.js

This will run the local web server for Cesium.

Put the file [CzmlClient.html](https://github.com/workingDog/scalaczml) in the same Cesium directory as the "HelloWorld.html" example, the "Apps" directory.

In another terminal navigate to the downloaded "akka-server" directory and type: 

    sbt run

This will run the Akka websocket server that will send the CZML document to the "CzmlClient.html" for display.

Once the server is up and running, you should see at the terminal: 

    Akka server online at ws://localhost:3210/
    press RETURN to stop the server...

Do not press RETURN yet.

Point your browser to "http://localhost:8080/Apps/CzmlClient.html" and the globe should be visible.
Rotate the globe to Australia and you should see a TV test pattern image and a text label near Sydney.

That's it. 

     in the "Cesium-1.17" directory
     node server.js
  
     in the "akka-server" directory
     sbt run 
 
     point browser to http://localhost:8080/Apps/CzmlClient.html
     go to Sydney Australia
 
## Dependencies

[Cesium-1.17](https://cesiumjs.org/), [SBT](http://www.scala-sbt.org/), [Node.js](https://nodejs.org/en/) and 
[ScalaCZML](https://github.com/workingDog/scalaczml) (included in the lib directory).

See also the build.sbt file.

