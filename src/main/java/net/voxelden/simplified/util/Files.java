package net.voxelden.simplified.util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Identifier;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class Files {
    public static void copyFromAssets(MinecraftClient client, Identifier source, Path destination) throws IOException {
        copyFromAssets(client, source, destination.toFile());
    }

    public static void copyFromAssets(MinecraftClient client, Identifier source, File destination) throws IOException {
        FileUtils.copyInputStreamToFile(client.getResourceManager().open(source), destination);
    }
}
