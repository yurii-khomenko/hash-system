package system.hash

import akka.actor.ActorSystem
import akka.http.scaladsl.server.HttpApp
import system.hash.actor.MetricController
import system.hash.actor.MetricController.MetricsReq
import system.hash.route.Routes
import scala.concurrent.ExecutionContext.Implicits.global

import scala.concurrent.duration._

object App extends HttpApp with Routes {

  val system = ActorSystem("hash-system")
  val metric = system.actorOf(MetricController.props, "metric-controller")

  def main(args: Array[String]): Unit = {

    withTimer("start load hashes", loadHashes())
    startServer("0.0.0.0", 8080, system)
  }
}

// todo add DB credentials support
// todo add test containers for cassandra
// todo add logging with logstash console
// todo add reload users controller