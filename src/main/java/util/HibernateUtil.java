package util;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            Configuration configuration = new Configuration();

            // JDBC Database connection settings
            Properties settings = new Properties();
            settings.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
            settings.put(Environment.URL, "jdbc:mysql://localhost:3306/DB_BP");
            settings.put(Environment.USER, "root");
            settings.put(Environment.PASS, "");
            settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");

            settings.put(Environment.SHOW_SQL, "true");

            settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

            settings.put(Environment.HBM2DDL_AUTO, "update");

            configuration.setProperties(settings);

            // Mention annotated classes
            configuration.addAnnotatedClass(com.fsac.entities.Client.class);
            configuration.addAnnotatedClass(com.fsac.entities.Compte.class);
            configuration.addAnnotatedClass(com.fsac.entities.Operation.class);
            configuration.addAnnotatedClass(com.fsac.entities.CompteCourant.class);
            configuration.addAnnotatedClass(com.fsac.entities.CompteEpargne.class);
            configuration.addAnnotatedClass(com.fsac.entities.Versement.class);
            configuration.addAnnotatedClass(com.fsac.entities.Retrait.class);

            // Create the SessionFactory from configuration
            return configuration.buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        // Close caches and connection pools
        getSessionFactory().close();
    }
}
