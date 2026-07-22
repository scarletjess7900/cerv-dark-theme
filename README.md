# CervDarkTheme

Turns the red Mojang loading screen dark — and lets you customise every part of it.

Client-side mod for **Minecraft 1.21.1** on **NeoForge**.

## Features

- Dark background by default (`#14181C` instead of vanilla red `#EF323D`)
- Full colour customisation with an in-game colour picker:
  - Background
  - Progress bar fill
  - Bar background (unfilled part)
  - Bar border
  - Mojang Studios logo tint
- Adjustable fade-in / fade-out durations
- Automatically disables NeoForge's red early loading window (configurable)
- Config GUI powered by [Fzzy Config](https://modrinth.com/mod/fzzy-config): **Mods → CervDarkTheme → Config**
- Config file: `config/cervdarktheme/config.toml`

## Dependencies

- [Fzzy Config](https://modrinth.com/mod/fzzy-config) (required)
- [Kotlin for Forge](https://modrinth.com/mod/kotlin-for-forge) (required by Fzzy Config)

## The early loading window

NeoForge by default replaces the vanilla Mojang loading overlay with its own red-themed
early loading window (`earlyWindowControl = true` in `config/fml.toml`). In that mode only
the background colour of the final overlay can be changed by a mod — the progress bars you
see belong to the loader itself.

CervDarkTheme therefore sets `earlyWindowControl = false` in `config/fml.toml`
automatically. Because the loader reads that file before mods load, the change takes
effect **from the second launch onward** — the very first launch with the mod installed
still shows the early window.

This behaviour can be turned off with the **Disable Early Loading Window** config option,
and `earlyWindowControl` can always be edited manually in `config/fml.toml`.

## Configuration

In-game: **Esc → Mods → CervDarkTheme → Config**, or edit
`config/cervdarktheme/config.toml`.

| Setting | Default | Description |
|---|---|---|
| Background Color | `#14181C` | Loading screen background |
| Bar Color | `#E22837` | Progress bar fill |
| Bar Background Color | `#14181C` | Unfilled part of the bar |
| Border Color | `#303336` | Progress bar border |
| Logo Color | `#FFFFFF` | Mojang Studios logo tint |
| Fade In Duration | `0.25` | Seconds (vanilla: 0.5) |
| Fade Out Duration | `0.5` | Seconds (vanilla: 1.0) |
| Disable Early Loading Window | `true` | Sets `earlyWindowControl = false` in `config/fml.toml` (applies next launch) |

## Building

Requires JDK 21 and Gradle 8.8+.

```bash
gradle build
```

The mod JAR will be at `build/libs/cervdarktheme-1.1.0.jar`.

To test in a development client:

```bash
gradle runClient
```

## License

MIT © scarletjess
