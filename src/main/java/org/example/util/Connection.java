package org.example.util;

import redis.clients.jedis.JedisPool;

public class Connection {

    private String HOST = "localhost";
    private int port = 6379;

    public JedisPool getConnectionPool() {
        return new JedisPool(HOST, port);
    }
}
