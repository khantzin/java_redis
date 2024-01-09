package org.example.datatypes;

import org.example.util.Connection;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.StreamEntryID;
import redis.clients.jedis.params.XAddParams;
import redis.clients.jedis.params.XReadParams;
import redis.clients.jedis.resps.StreamEntry;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>Stream acts like an append-only log but also implements several operations
 * to overcome some of the limits of a typical append-only log.</p>
 * <p>Streams can be used to recorded and simultaneously syndicate events in real time. </p> <br>
 * <b>Examples of stream use cases include: </b>
 * <p><b>Event sourcing - </b> (e.g., tracking user actions, click, etc.)</p>
 * <p><b>Sensor monitoring - </b> (e.g., reading from devices iin the field) </p>
 * <p><b>Notification - </b> (e.g., storing a record of each user's notification in a separate stream)</p>
 * <br>
 * <p>
 *     Redis generate {@code unique ID} for each stream entry. Can use these {@code IDs} to retrieve their
 *     associated entries late or to read and process all subsequent entries in the stream.
 * </p>
 * <p>EntryID - {@code <millisecondsTime>-<sequenceNumber>}</p>
 */
public class TypeStreams extends Connection {

    public final JedisPool pool = getConnectionPool();
    public final Jedis jedis = pool.getResource();

    public void add() {
        var res1 = jedis.xadd("race:france", new HashMap<String, String>() {{
            put("rider", "Castilla");
            put("speed", "30.2");
            put("position", "1");
            put("location_id", "1");
        }}, XAddParams.xAddParams());
        System.out.println(res1);

        var res2 = jedis.xadd("race:france", new HashMap<String, String>() {{
            put("rider", "Norem");
            put("speed", "28.8");
            put("position", "3");
            put("location_id", "1");
        }}, XAddParams.xAddParams());
        System.out.println(res2);

        var res3 = jedis.xadd("race:france", new HashMap<String, String>() {{
            put("rider", "Prickett");
            put("speed", "29.7");
            put("position", "2");
            put("location_id", "1");
        }}, XAddParams.xAddParams());
        System.out.println(res3);
    }

    public void getByEntryId() {
        var res1 = jedis.xrange("race:france", "1702394762003-0", "+", 2);
        System.out.println(res1);
    }

    public void xRead() {
        List<Map.Entry<String, List<StreamEntry>>> res1= jedis.xread(
                XReadParams.xReadParams().block(300).count(100),
                new HashMap<String, StreamEntryID>(){{put("race:france",new StreamEntryID());}}
        );
        System.out.println(res1);
    }
}
