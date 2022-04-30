package io.github.xanderstuff.leveldatachangedetector;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LevelDataChangeDetector implements ModInitializer {
    // This logger is used to write text to the console and the log file.
    // It is considered best practice to use your mod id as the logger's name.
    // That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger("LevelDataChangeDetector");
    private long oldSeed = -69420; // magic! I love magic!

    @Override
    public void onInitialize() {
//		LOGGER.info("Hello Fabric world!");

        ServerTickEvents.START_SERVER_TICK.register(server -> {
            long newSeed = server.getSaveProperties().getGeneratorOptions().getSeed();
            if (oldSeed == -69420) {
                oldSeed = newSeed;
            } else {
                if (oldSeed != newSeed) {
                    LOGGER.error("ERROR!!!: the seed has been changed from: {}   to: {}", oldSeed, newSeed);
                }
            }

//            server.getWorlds().forEach(serverWorld -> {
//
//            });


//            server.getSaveProperties().getMainWorldProperties().


//            server.session.directory.toFile();
            // get level.dat
//            NbtIo.scanCompressed(file, new NbtCollector());
//            var nbt = exclusiveNbtCollector.getRoot();
        });
    }
}
