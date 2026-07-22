package dev.scarletjess.cervdarktheme;

import me.fzzyhmstrs.fzzy_config.validation.misc.ValidatedColor;

public final class CDT {

    private static final int DEF_BACKGROUND = 0x14181C;
    private static final int DEF_BAR = 0xE22837;
    private static final int DEF_BAR_BACKGROUND = 0x14181C;
    private static final int DEF_BORDER = 0x303336;
    private static final int DEF_LOGO = 0xFFFFFF;

    private CDT() {
    }

    public static int background(int original) {
        CDTConfig c = config();
        return keepAlpha(original, c == null ? DEF_BACKGROUND : rgb(c.backgroundColor, DEF_BACKGROUND));
    }

    public static int bar(int original) {
        CDTConfig c = config();
        return keepAlpha(original, c == null ? DEF_BAR : rgb(c.barColor, DEF_BAR));
    }

    public static int border(int original) {
        CDTConfig c = config();
        return keepAlpha(original, c == null ? DEF_BORDER : rgb(c.borderColor, DEF_BORDER));
    }

    public static int barBackground(int alpha) {
        CDTConfig c = config();
        int a = Math.max(0, Math.min(255, alpha));
        return (a << 24) | (c == null ? DEF_BAR_BACKGROUND : rgb(c.barBackgroundColor, DEF_BAR_BACKGROUND));
    }

    public static float[] logoRgb() {
        CDTConfig c = config();
        int rgb = c == null ? DEF_LOGO : rgb(c.logoColor, DEF_LOGO);
        return new float[]{
                ((rgb >> 16) & 0xFF) / 255.0F,
                ((rgb >> 8) & 0xFF) / 255.0F,
                (rgb & 0xFF) / 255.0F
        };
    }

    public static float fadeInMs(float original) {
        CDTConfig c = config();
        if (c == null) {
            return original;
        }
        try {
            return c.fadeInSeconds.get() * 1000.0F;
        } catch (Throwable t) {
            return original;
        }
    }

    public static float fadeOutMs(float original) {
        CDTConfig c = config();
        if (c == null) {
            return original;
        }
        try {
            return c.fadeOutSeconds.get() * 1000.0F;
        } catch (Throwable t) {
            return original;
        }
    }

    private static CDTConfig config() {
        try {
            return CervDarkTheme.CONFIG;
        } catch (Throwable t) {
            return null;
        }
    }

    private static int rgb(ValidatedColor color, int fallback) {
        try {
            return ((color.r() & 0xFF) << 16) | ((color.g() & 0xFF) << 8) | (color.b() & 0xFF);
        } catch (Throwable t) {
            return fallback;
        }
    }

    private static int keepAlpha(int original, int rgb) {
        return (original & 0xFF000000) | (rgb & 0xFFFFFF);
    }
}
