package com.kodekutters.server


import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.ws.{Message, TextMessage}
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.{Flow, Source}

import com.kodekutters.czml.czmlCore._
import com.kodekutters.czml.czmlProperties._
import com.kodekutters.czml.CzmlImplicits._

import play.api.libs.json.Json

import scala.collection.mutable
import scala.io.StdIn


/**
  * an example Akka websocket app, serving a CZML document to a Cesium client
  *
  * The client initiate a request by sending any text message to this server.
  * Upon receiving a request the server will send back a CZML object, specifically,
  * a billboard and a label located near Sydney Australia.
  *
  */
object AkkaServer extends App {

  // setup the akka actor system
  implicit val system = ActorSystem("akkaserver")
  implicit val materializer = ActorMaterializer()
  implicit val ec = system.dispatcher

  // create the czml document object we want to send to the client
  val czml = CZML[CZMLPacket]()
  // add the required first packet to the czml document
  czml.packets += new CZMLPacket(id = "document", version = "1.0")
  // create a positions property ---> Sydney Australia
  val pos = new CzmlPositions(new CzmlPosition(cartographicDegrees = Cartographic[DEGREE](151.18, -33.87, 123.0)))
  // create a billboard property
  val bill = new Billboard(image = "https://upload.wikimedia.org/wikipedia/commons/c/c4/PM5544_with_non-PAL_signals.png", show = true, scale = 0.1)
  // create a label property
  val label = new Label(pixelOffset = CzmlCartesian2(22, 44), text = "some text label", font = "11pt Lucida Console")
  // create a czml packet with all the created properties
  val packet = new CZMLPacket("test packet", mutable.HashSet[CzmlProperty](pos, bill, label))
  // add the packet to the czml document
  czml.packets += packet
  // convert the czml document to json
  val jsczml = Json.toJson(czml)

  // the server is setup on this host and port
  val host = "localhost"
  val port = 3210

  // handle the client request (any text message will do) for some CZML data
  val czmlWebsocketService =
    Flow[Message]
      .collect {
        case tm: TextMessage =>
          println("---> received a client request: " + tm + " \n---> sending some CZML object back...")
          // send the czml document to the client
          TextMessage(Source.single(jsczml.toString()))
      }

  // the point of entry into the server services
  val route =
    path("") {
      get {
        handleWebsocketMessages(czmlWebsocketService)
      }
    }

  // setup the http/ws server
  val bindingFuture = Http().bindAndHandle(route, host, port)

  println(s"Akka server online at ws://" + host + ":" + port + "/\npress RETURN to stop the server...")
  // keep the server alive until a RETURN is pressed
  StdIn.readLine()

  // close everything properly
  bindingFuture
    .flatMap(_.unbind())
    .onComplete(_ => system.terminate())

}

