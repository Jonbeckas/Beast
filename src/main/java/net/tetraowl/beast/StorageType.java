package net.tetraowl.beast;

import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.FileNotFoundException;

public interface  StorageType {

      void write(@Nullable File storage,String string) throws FileNotFoundException;
      String read(@Nullable File storage) throws FileNotFoundException;

    boolean needsGivenStorage();

}
