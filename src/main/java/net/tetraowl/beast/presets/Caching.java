package net.tetraowl.beast.presets;

import net.tetraowl.beast.BeastInstance;
import net.tetraowl.beast.CacheType;
import net.tetraowl.beast.Serializer;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.FileNotFoundException;

public class Caching extends BeastInstance {
    String string;

    public Caching( CacheType cacheType) {
        super(new GSONSerializer(), cacheType);
    }

    @Override
    protected void save(String s) {
        this.string =string;
    }

    @Override
    protected String load() {
        return string;
    }
}
