package com.github.fabienrenaud.jjb;

import com.github.fabienrenaud.jjb.data.JsonSource;
import com.github.fabienrenaud.jjb.data.JsonSourceFactory;

/**
 * @author Fabien Renaud
 */
public abstract class JsonBench {

    protected static final JsonSource CLI_JSON_SOURCE = JsonSourceFactory.create();
    public abstract JsonSource JSON_SOURCE();

    public Object gson() throws Exception {
        return null;
    }

    public Object jackson() throws Exception {
        return null;
    }

    public Object jackson_afterburner() throws Exception {
        return null;
    }

    public Object fastjson() throws Exception {
        return null;
    }
}
