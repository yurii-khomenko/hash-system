package system.hash

import akka.actor.ActorSystem
import akka.http.scaladsl.model.headers.{BasicHttpCredentials, HttpChallenge}
import system.hash.actor.MetricController
import system.hash.model.MD5
import system.hash.route.Routes

trait Config extends Routes {

  val testSystem = ActorSystem("test-hash-system")
  val metric = testSystem.actorOf(MetricController.props, "metric-controller")

  val responseWWWAuthHeader = HttpChallenge("Basic", Some("hash system"), Map("charset" → "UTF-8"))
  val requiresAuth = "The resource requires authentication, which was not supplied with the request"
  val invalidAuth = "The supplied authentication is invalid"
  val invalidRole = "You're don't have necessary role: "

  val emptyCredentials = BasicHttpCredentials("", "")
  val invalidCredentials = BasicHttpCredentials("Peter", "pan")
  val validClientCredentials = BasicHttpCredentials("test-client", "test-client-password")
  val validMetricCredentials = BasicHttpCredentials("metric-prometeus", "metric-prometeus-password")

  val msisdn = 380672240000L
  val msisdnTooShort = 38067224L
  val msisdnAlphaName = "38067224qweqw"

  val hash = "8801ddf0a8ef82313293d7cf3ab5d46c"
  val incorrectHash = "9801ddf0a8ef82313293d7cf3ab5d46ce"
  val absentHash = "4501ddf0a8ef82313293d7cf3ab5d434"

  msisdns(MD5(hash)) = msisdn
}