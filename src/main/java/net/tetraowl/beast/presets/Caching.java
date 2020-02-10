package net.tetraowl.beast.presets;

import net.tetraowl.beast.CacheType;
import net.tetraowl.beast.Serializer;
import net.tetraowl.beast.StorageType;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.FileNotFoundException;

public class Caching extends StorageType {
    String string;

    public Caching( CacheType cacheType) {
        super(new GSONSerializer(), cacheType);
    }

    @Override
    protected void save(String s) {
        this.string = s;
    }

    @Override
    protected String load() {
        return string;
    }
}
