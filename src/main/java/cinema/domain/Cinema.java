/* This code was initialised by Akka Serverless tooling.
 * As long as this file exists it will not be re-generated.
 * You are free to make changes to this file.
 */

package cinema.domain;

import cinema.CinemaApi;
import com.akkaserverless.javasdk.EntityId;
import com.akkaserverless.javasdk.Reply;
import com.akkaserverless.javasdk.eventsourcedentity.*;
import com.google.protobuf.Empty;

/** An event sourced entity. */
@EventSourcedEntity(entityType = "cinema")
public class Cinema extends AbstractCinema {
    @SuppressWarnings("unused")
    private final String entityId;

    public Cinema(@EntityId String entityId) {
        this.entityId = entityId;
    }

    @Override
    public CinemaDomain.CinemaState snapshot() {
        // TODO: produce state snapshot here
        return CinemaDomain.CinemaState.newBuilder().build();
    }

    @Override
    public void handleSnapshot(CinemaDomain.CinemaState snapshot) {
        // TODO: restore state from snapshot here
    }

    @Override
    public Reply<Empty> increase(CinemaApi.CIncreaseValue command, CommandContext ctx) {
        return Reply.failure("The command handler for `Increase` is not implemented, yet");
    }

    @Override
    public Reply<Empty> decrease(CinemaApi.CDecreaseValue command, CommandContext ctx) {
        return Reply.failure("The command handler for `Decrease` is not implemented, yet");
    }

    @Override
    public Reply<Empty> reset(CinemaApi.CResetValue command, CommandContext ctx) {
        return Reply.failure("The command handler for `Reset` is not implemented, yet");
    }

    @Override
    public Reply<CinemaApi.CurrentCinema> getCurrentCinema(CinemaApi.CGetCinema command, CommandContext ctx) {
        return Reply.failure("The command handler for `GetCurrentCinema` is not implemented, yet");
    }


}
