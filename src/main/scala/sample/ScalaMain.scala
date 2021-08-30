package sample

import cinema.CinemaApi
import cinema.domain.CinemaDomain
import com.akkaserverless.javasdk.AkkaServerless
import com.google.protobuf.EmptyProto
import org.slf4j.LoggerFactory
import sample.domain.CounterDomain

object ScalaMain {
  private val LOG = LoggerFactory.getLogger(ScalaMain.getClass)
  val SERVICE: AkkaServerless =
    withComponents(new AkkaServerless())

  @throws[Exception]
  def main(args: Array[String]): Unit = {
    LOG.info("starting the Akka Serverless service with Scala")
    SERVICE.start
  }

  def withComponents(akkaServerless: AkkaServerless): AkkaServerless =
    akkaServerless
      .registerValueEntity(
        classOf[sample.domain.inner.Counter],
        CounterApi.getDescriptor.findServiceByName("CounterService"),
        CounterDomain.getDescriptor,
        EmptyProto.getDescriptor
      )
      .registerEventSourcedEntity(
        classOf[cinema.domain.Cinema],
        CinemaApi.getDescriptor.findServiceByName("CinemaService"),
        CinemaDomain.getDescriptor,
        EmptyProto.getDescriptor
      )
}
