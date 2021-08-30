/* This code was initialised by Akka Serverless tooling.
 * As long as this file exists it will not be re-generated.
 * You are free to make changes to this file.
 */

package cinema.domain;

import cinema.CinemaApi;
import com.akkaserverless.javasdk.eventsourcedentity.CommandContext;
import com.google.protobuf.Empty;
import org.junit.Test;
import org.mockito.*;

import static org.junit.Assert.assertThrows;

public class CinemaTest {
    private String entityId = "entityId1";
    private Cinema entity;
    private CommandContext context = Mockito.mock(CommandContext.class);
    
    @Test
    public void increaseTest() {
        entity = new Cinema(entityId);
        
        // TODO: write your mock here
        // Mockito.when(context.[...]).thenReturn([...]);
        
        // TODO: set fields in command, and update assertions to verify implementation
        // assertEquals([expected],
        //    entity.increase(CinemaApi.CIncreaseValue.newBuilder().build(), context);
        // );
        
        // TODO: if you wish to verify events:
        //    Mockito.verify(context).emit(event);
    }
    
    @Test
    public void decreaseTest() {
        entity = new Cinema(entityId);
        
        // TODO: write your mock here
        // Mockito.when(context.[...]).thenReturn([...]);
        
        // TODO: set fields in command, and update assertions to verify implementation
        // assertEquals([expected],
        //    entity.decrease(CinemaApi.CDecreaseValue.newBuilder().build(), context);
        // );
        
        // TODO: if you wish to verify events:
        //    Mockito.verify(context).emit(event);
    }
    
    @Test
    public void resetTest() {
        entity = new Cinema(entityId);
        
        // TODO: write your mock here
        // Mockito.when(context.[...]).thenReturn([...]);
        
        // TODO: set fields in command, and update assertions to verify implementation
        // assertEquals([expected],
        //    entity.reset(CinemaApi.CResetValue.newBuilder().build(), context);
        // );
        
        // TODO: if you wish to verify events:
        //    Mockito.verify(context).emit(event);
    }
    
    @Test
    public void getCurrentCinemaTest() {
        entity = new Cinema(entityId);
        
        // TODO: write your mock here
        // Mockito.when(context.[...]).thenReturn([...]);
        
        // TODO: set fields in command, and update assertions to verify implementation
        // assertEquals([expected],
        //    entity.getCurrentCinema(CinemaApi.CGetCinema.newBuilder().build(), context);
        // );
        
        // TODO: if you wish to verify events:
        //    Mockito.verify(context).emit(event);
    }
}