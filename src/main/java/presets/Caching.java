package presets;

import main.StorageType;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.FileNotFoundException;

public class Caching implements StorageType {
    String string;
    @Override
    public void write(@Nullable File storage, String string) throws FileNotFoundException {
        this.string =string;
    }

    @Override
    public String read(@Nullable File storage) throws FileNotFoundException {
        return string;
    }

    @Override
    public boolean needsGivenStorage() {
        return false;
    }
}
