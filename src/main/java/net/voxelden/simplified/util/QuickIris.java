package net.voxelden.simplified.util;

import net.irisshaders.iris.Iris;
import net.voxelden.simplified.Simplified;

import java.io.IOException;

public class QuickIris {
    public static void setShader(String name, boolean enabled) {
        Iris.getIrisConfig().setShaderPackName(name);
        Iris.getIrisConfig().setShadersEnabled(enabled);
        try {
            Iris.getIrisConfig().save();
            Iris.reload();
        } catch (IOException e) {
            Simplified.LOGGER.warn("Exception loading shaders", e);
        }
    }
}
