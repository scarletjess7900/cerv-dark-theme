package dev.scarletjess.cervdarktheme;

import me.fzzyhmstrs.fzzy_config.config.Config;
import me.fzzyhmstrs.fzzy_config.validation.misc.ValidatedBoolean;
import me.fzzyhmstrs.fzzy_config.validation.misc.ValidatedColor;
import me.fzzyhmstrs.fzzy_config.validation.number.ValidatedFloat;
import net.minecraft.resources.ResourceLocation;

public class CDTConfig extends Config {

    public CDTConfig() {
        super(ResourceLocation.fromNamespaceAndPath(CervDarkTheme.MOD_ID, "config"));
    }

    public ValidatedColor backgroundColor = new ValidatedColor(0x14, 0x18, 0x1C, 0xFF);

    public ValidatedColor barColor = new ValidatedColor(0xE2, 0x28, 0x37, 0xFF);

    public ValidatedColor barBackgroundColor = new ValidatedColor(0x14, 0x18, 0x1C, 0xFF);

    public ValidatedColor borderColor = new ValidatedColor(0x30, 0x33, 0x36, 0xFF);

    public ValidatedColor logoColor = new ValidatedColor(0xFF, 0xFF, 0xFF, 0xFF);

    public ValidatedFloat fadeInSeconds = new ValidatedFloat(0.25F, 10.0F, 0.0F);

    public ValidatedFloat fadeOutSeconds = new ValidatedFloat(0.5F, 10.0F, 0.0F);

    public ValidatedBoolean disableEarlyWindow = new ValidatedBoolean(true);
}
