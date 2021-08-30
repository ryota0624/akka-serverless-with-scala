package sample.domain.inner

import cinema.CinemaApi
import cinema.domain.CinemaDomain.AudienceAdded
import cinema.domain.{AbstractCinema, CinemaDomain}
import com.akkaserverless.javasdk.Reply
import com.akkaserverless.javasdk.eventsourcedentity._
import com.google.protobuf.Empty

@EventSourcedEntity(entityType = "cinema")
class Cinema(private var entityId: String) extends AbstractCinema {
  override def snapshot(): CinemaDomain.CinemaState =
    return CinemaDomain.CinemaState.newBuilder.build

  override def handleSnapshot(snapshot: CinemaDomain.CinemaState): Unit = {}

  override def increase(
      command: CinemaApi.CIncreaseValue,
      ctx: CommandContext
  ): Reply[Empty] = {
    ctx.emit(AudienceAdded.newBuilder().setId(???).build())
    Reply.message(Empty.getDefaultInstance)
  }

  override def decrease(
      command: CinemaApi.CDecreaseValue,
      ctx: CommandContext
  ): Reply[Empty] = ???

  override def reset(
      command: CinemaApi.CResetValue,
      ctx: CommandContext
  ): Reply[Empty] = ???

  override def getCurrentCinema(
      command: CinemaApi.CGetCinema,
      ctx: CommandContext
  ): Reply[CinemaApi.CurrentCinema] = ???
}
