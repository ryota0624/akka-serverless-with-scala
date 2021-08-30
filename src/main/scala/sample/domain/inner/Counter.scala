package sample.domain.inner

import com.akkaserverless.javasdk.valueentity._
import com.akkaserverless.javasdk.{EntityId, Reply}
import com.google.protobuf.Empty
import sample.CounterApi
import sample.domain._

object Counter {
  implicit class CounterStateOps(cs: CounterDomain.CounterState) {
    def modify(
        useBuilder: CounterDomain.CounterState.Builder => CounterDomain.CounterState.Builder
    ): CounterDomain.CounterState = {
      useBuilder(cs.toBuilder).build()
    }

    def toVO: CounterVO = {
      CounterVO(cs.getValue)
    }
  }

  case class CounterVO(value: Int) {
    def increment(count: Int): CounterVO = copy(value = value + count)
    def toState: CounterDomain.CounterState =
      CounterDomain.CounterState.newBuilder().setValue(value).build()
  }

  object CounterVO {
    def initial = CounterVO(0)
  }

  trait Transferable[State, VO] {
    def state2ValueObject(state: State): VO
    def valueObject2State(value: VO): State
  }

  implicit val counterTransferable =
    new Transferable[CounterDomain.CounterState, CounterVO] {
      override def state2ValueObject(
          state: CounterDomain.CounterState
      ): CounterVO = state.toVO

      override def valueObject2State(
          value: CounterVO
      ): CounterDomain.CounterState = value.toState
    }

  implicit class CommandContextOps[State](ctx: CommandContext[State]) {
    def state: Option[State] = {
      ctx.getState.map[Option[State]](Some(_)).orElse(None)
    }

    def update(updateFn: State => State): Unit = {
      state match {
        case Some(value) =>
          ctx.updateState(updateFn(value))
        case None =>
      }
    }

    def update[VO](
        updateFn: VO => VO
    )(implicit transferable: Transferable[State, VO]): Unit = {
      state match {
        case Some(value) =>
          ctx.updateState(
            transferable.valueObject2State(
              updateFn(transferable.state2ValueObject(value))
            )
          )
        case None =>
      }
    }

    def put[VO](
        vo: VO
    )(implicit transferable: Transferable[State, VO]): Unit = {
      ctx.updateState(transferable.valueObject2State(vo))
    }
  }
}

@ValueEntity(entityType = "counter")
class Counter(
    @EntityId private var entityId: String
) extends AbstractCounter {
  import Counter._

  override def increase(
      command: CounterApi.IncreaseValue,
      ctx: CommandContext[CounterDomain.CounterState]
  ): Reply[Empty] = {
    if (ctx.getState.isEmpty) ctx.put(CounterVO.initial)
    else ctx.update[CounterVO](_.increment(command.getValue))

    Reply.message(Empty.getDefaultInstance)
  }

  override def decrease(
      command: CounterApi.DecreaseValue,
      ctx: CommandContext[CounterDomain.CounterState]
  ): Reply[Empty] = {
    Reply.failure(
      "The command handler for `Decrease` is not implemented, yet"
    )
  }

  override def reset(
      command: CounterApi.ResetValue,
      ctx: CommandContext[CounterDomain.CounterState]
  ): Reply[Empty] = {
    Reply.failure(
      "The command handler for `Reset` is not implemented, yet"
    )
  }

  override def getCurrentCounter(
      command: CounterApi.GetCounter,
      ctx: CommandContext[CounterDomain.CounterState]
  ): Reply[CounterApi.CurrentCounter] = {
    Reply
      .message(
        CounterApi.CurrentCounter.getDefaultInstance.toBuilder
          .setValue(ctx.state.fold(0)(_.getValue))
          .build()
      )

  }
}
