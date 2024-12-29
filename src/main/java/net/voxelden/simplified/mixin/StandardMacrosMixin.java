package net.voxelden.simplified.mixin;

import com.google.common.collect.ImmutableList;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.irisshaders.iris.gl.shader.StandardMacros;
import net.irisshaders.iris.helpers.StringPair;
import net.voxelden.simplified.client.CustomShaderUniforms;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Collection;

@Mixin(StandardMacros.class)
public class StandardMacrosMixin {
    @WrapOperation(method = "createStandardEnvironmentDefines", at = @At(value = "INVOKE", target = "Lcom/google/common/collect/ImmutableList;copyOf(Ljava/util/Collection;)Lcom/google/common/collect/ImmutableList;"), remap = false)
    private static ImmutableList<StringPair> addCustomShaderDefines(Collection<StringPair> list, Operation<ImmutableList<StringPair>> original) {
        list.addAll(CustomShaderUniforms.getDefines());
        return original.call(list);
    }
}
