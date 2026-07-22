package dev.scarletjess.cervdarktheme;

import java.nio.file.Files;
import java.nio.file.Path;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import dev.scarletjess.cervdarktheme.mixin.LoadingOverlayAccessor;
import me.fzzyhmstrs.fzzy_config.api.ConfigApiJava;
import me.fzzyhmstrs.fzzy_config.api.RegisterType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLPaths;

@Mod(value = CervDarkTheme.MOD_ID, dist = Dist.CLIENT)
public final class CervDarkTheme {

    public static final String MOD_ID = "cervdarktheme";

    public static final CDTConfig CONFIG = ConfigApiJava.registerAndLoadConfig(CDTConfig::new, RegisterType.CLIENT);

    private static final Logger LOGGER = LogUtils.getLogger();
    private static final int VANILLA_BACKGROUND = 0xFFEF323D;

    public CervDarkTheme() {
        try {
            LoadingOverlayAccessor.setBrandBackground(() -> CDT.background(VANILLA_BACKGROUND));
        } catch (Throwable t) {
            LOGGER.error("Failed to install loading screen background colour supplier", t);
        }
        disableEarlyWindow();
    }

    private static void disableEarlyWindow() {
        try {
            if (CONFIG == null || !CONFIG.disableEarlyWindow.get()) {
                return;
            }
            Path fmlConfig = FMLPaths.FMLCONFIG.get();
            if (!Files.exists(fmlConfig)) {
                return;
            }
            String text = Files.readString(fmlConfig);
            if (text.contains("earlyWindowControl")) {
                String patched = text.replaceAll("earlyWindowControl\\s*=\\s*true", "earlyWindowControl = false");
                if (!patched.equals(text)) {
                    Files.writeString(fmlConfig, patched);
                    LOGGER.info("Set earlyWindowControl = false in {} (takes effect next launch)", fmlConfig);
                }
            } else {
                Files.writeString(fmlConfig, text + System.lineSeparator() + "earlyWindowControl = false" + System.lineSeparator());
                LOGGER.info("Set earlyWindowControl = false in {} (takes effect next launch)", fmlConfig);
            }
        } catch (Throwable t) {
            LOGGER.error("Failed to update fml.toml", t);
        }
    }
}
