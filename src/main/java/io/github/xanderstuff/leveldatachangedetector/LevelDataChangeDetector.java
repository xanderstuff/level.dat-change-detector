package io.github.xanderstuff.leveldatachangedetector;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.network.MessageType;
import net.minecraft.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.UUID;

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
                    // broadcast and log this atrocity
                    var message = String.format("[LevelDataChangeDetector] OH NO!!! The seed has been changed from: %d   to: %d", oldSeed, newSeed);
                    server.getPlayerManager().broadcast(Text.of(message), MessageType.SYSTEM, UUID.randomUUID());
                    LOGGER.error(message);

                    // find someone to blame
                    var players = server.getPlayerManager().getPlayerNames();
                    if(players.length <= 0) return;
                    var randomPlayer = players[new Random().nextInt(players.length)];
                    server.getPlayerManager().broadcast(Text.of("[LevelDataChangeDetector] Hmmm... I think I'll blame " + randomPlayer), MessageType.SYSTEM, UUID.randomUUID());
                }
            }

//            server.getWorlds().forEach(serverWorld -> {
//
//            });


//            server.getSaveProperties().getMainWorldProperties().


//            server.session.directory.toFile();
//            server.getRunDirectory()
            // get level.dat
//            NbtIo.scanCompressed(file, new NbtCollector());
//            var nbt = exclusiveNbtCollector.getRoot();
        });
    }
}
