package com.mycompany.videojuego_basex.db;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.basex.api.client.LocalSession;
import org.basex.core.BaseXException;
import org.basex.core.Context;
import org.basex.core.StaticOptions;

public final class ConexionBaseX {

    private static final String DB_NAME = "VideojocsDB";
    private static final String RESOURCE_NAME = "/catalogo.xml";
    private static final Path DB_PATH = Paths.get("target", "basex-data").toAbsolutePath();

    private static Context context;

    private ConexionBaseX() {
    }

    public static synchronized void init() throws IOException {
        if (context != null) {
            return;
        }

        Files.createDirectories(DB_PATH);

        StaticOptions options = new StaticOptions(false);
        options.set(StaticOptions.DBPATH, DB_PATH.toString());
        context = new Context(options);

        try (LocalSession session = new LocalSession(context)) {
            if (!context.databases.list().contains(DB_NAME)) {
                try (InputStream input = ConexionBaseX.class.getResourceAsStream(RESOURCE_NAME)) {
                    if (input == null) {
                        throw new IOException("No se ha encontrado el recurso " + RESOURCE_NAME);
                    }
                    session.create(DB_NAME, input);
                }
            }
        }
    }

    public static LocalSession getSession() throws IOException {
        init();
        LocalSession session = new LocalSession(context);

        try {
            session.execute("OPEN " + DB_NAME);
            return session;
        } catch (BaseXException e) {
            session.close();
            throw e;
        }
    }

    public static synchronized void close() {
        if (context != null) {
            context.close();
            context = null;
        }
    }
}
