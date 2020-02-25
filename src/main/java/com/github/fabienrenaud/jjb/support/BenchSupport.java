package com.github.fabienrenaud.jjb.support;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by frenaud on 7/24/16.
 */
public enum BenchSupport {
    USERS(
        new Libapi(Library.GSON, Api.DATABIND),
        new Libapi(Library.JACKSON, Api.DATABIND),
        new Libapi(Library.JACKSON_AFTERBURNER, Api.DATABIND),
        new Libapi(Library.FASTJSON, Api.DATABIND)
    ),
    CLIENTS(
            new Libapi(Library.GSON, Api.DATABIND),
            new Libapi(Library.JACKSON, Api.DATABIND),
            new Libapi(Library.JACKSON_AFTERBURNER, Api.DATABIND),
            new Libapi(Library.FASTJSON, Api.DATABIND)
    );

    private final List<Libapi> libapis;

    BenchSupport(Libapi... libapis) {
        this.libapis = Arrays.asList(libapis);
    }

    public List<Libapi> libapis() {
        return libapis;
    }

    public Set<Library> supportedLibs() {
         return libapis.stream()
            .filter(Libapi::active)
            .map(Libapi::lib)
            .collect(Collectors.toSet());
    }
}
