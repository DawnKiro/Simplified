package net.voxelden.simplified.util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Identifier;
import net.voxelden.simplified.Simplified;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class Files {
    public static void copyFromAssets(MinecraftClient client, Identifier source, Path destination) {
        copyFromAssets(client, source, destination.toFile());
    }

    public static void copyFromAssets(MinecraftClient client, Identifier source, File destination) {
        try {
            FileUtils.copyInputStreamToFile(client.getResourceManager().open(source), destination);
        } catch (IOException e) {
            Simplified.LOGGER.warn("Error copying file", e);
        }
    }

    public static void delete(Path destination) {
        delete(destination.toFile());
    }

    public static void delete(File destination) {
        try {
            FileUtils.delete(destination);
        } catch (IOException e) {
            Simplified.LOGGER.warn("Error deleting file", e);
        }
    }
}
