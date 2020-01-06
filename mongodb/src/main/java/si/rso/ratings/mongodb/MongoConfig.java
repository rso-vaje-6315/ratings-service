package si.rso.ratings.mongodb;

import com.kumuluz.ee.configuration.cdi.ConfigBundle;
import com.kumuluz.ee.configuration.cdi.ConfigValue;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
@ConfigBundle("mongo")
public class MongoConfig {
    @ConfigValue("username")
    private String username;

    @ConfigValue("password")
    private String password;

    @ConfigValue("port")
    private int port;

    @ConfigValue("database")
    private String database;

    @ConfigValue("default-collection")
    private String defaultCollection;

    @ConfigValue("host")
    private String host;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getDefaultCollection() {
        return defaultCollection;
    }

    public void setDefaultCollection(String defaultCollection) {
        this.defaultCollection = defaultCollection;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }
}
