package com.s2p.utility.exceluploader.repository.mongo;

import java.io.IOException;

import com.s2p.utility.exceluploader.logger.Logger;
import de.flapdoodle.embed.mongo.Command;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.*;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.config.IRuntimeConfig;
import de.flapdoodle.embed.process.exceptions.DistributionException;
import de.flapdoodle.embed.process.extract.UserTempNaming;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Configuration
@Profile("embededmongo") // need to pass this argument at run time to pick this profile -Dspring.profiles.active=embededmongo
@Order (value = Ordered.HIGHEST_PRECEDENCE)
public class FlapDoodleDataSourceConfig {
    Logger logger = Logger.getLogger();

    @Value("${spring.data.mongodb.port:27017}")
    private int port;

    @Value("${spring.data.mongodb.host:localhost}")
    private String host;

    @Value("${spring.data.mongodb.databasePath:configDataDB}")
    private String dbPath;

    private MongodProcess process = null;

    @PostConstruct
    public void init() throws Exception {
        try {
            logger.info("Started downloading of the server....");
            initializeAndRunMongoServer();
        } catch (DistributionException de) {
            logger.info("Server was all-ready downloaded, so starting the server....");
            runMongoServer();
        }
        logger.info("Server downloaded and started....");
    }

    private MongodProcess initializeAndRunMongoServer() throws Exception {
        Storage storage = new Storage(dbPath, null, 0);

        IRuntimeConfig runtimeConfig = new RuntimeConfigBuilder()
                .defaults(Command.MongoD)
                .artifactStore(new ExtractedArtifactStoreBuilder()
                        .defaults(Command.MongoD)
                        .download(new DownloadConfigBuilder()
                                .defaultsForCommand(Command.MongoD).build())
                        .executableNaming(new UserTempNaming()))
                .build();

        IMongodConfig mongodConfig = new MongodConfigBuilder()
                .version(Version.Main.PRODUCTION)
                .net(new Net(host, port, false))
                .replication(storage)
                .build();

        MongodStarter runtime = MongodStarter.getInstance(runtimeConfig);
        process = runtime.prepare(mongodConfig).start();
        return process;
    }

    private MongodProcess runMongoServer() throws IOException {
        Storage storage = new Storage(dbPath, null, 0);

        IRuntimeConfig runtimeConfig = new RuntimeConfigBuilder()
                .defaults(Command.MongoD)
                .build();

        IMongodConfig mongodConfig = new MongodConfigBuilder()
                .version(Version.Main.PRODUCTION)
                .net(new Net(host, port, false))
                .replication(storage)
                .build();

        MongodStarter runtime = MongodStarter.getInstance(runtimeConfig);
        process = runtime.prepare(mongodConfig).start();
        return process;
    }

    @PreDestroy
    public void stop(){
        process.stop();
    }
}