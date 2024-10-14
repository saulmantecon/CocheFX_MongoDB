package util;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

import java.util.Properties;

public class DatabaseManager {
    private static MongoClient conexion;
    private static MongoDatabase mongoDatabase;
    public static MongoDatabase getDatabase() {
       /* try {

            final MongoClient conexion = new MongoClient(new MongoClientURI("mongodb://admin:1234@localhost:27017/?authSource=admin"));

            System.out.println("Conectada correctamente a la BD");

            return conexion;
        } catch (Exception e) {
            System.out.println("Conexion Fallida");
            System.out.println(e);
            return null;
        }
        */


        try{
            Properties prop = new Properties();
            prop.load(R.getProperties("database.properties"));
            String host = prop.getProperty("host");
            int port = Integer.parseInt(prop.getProperty("port"));
            String username=prop.getProperty("username");
            String password=prop.getProperty("password");
            String database=prop.getProperty("database");
            conexion = new MongoClient(new MongoClientURI("mongodb://" + username + ":" + password + "@" + host + ":" + port + "/?authSource=admin"));
            mongoDatabase = conexion.getDatabase(database);

        }catch (Exception e){
            System.out.println(e);
        }
        return mongoDatabase;
    }



    public static void desconectar(MongoClient con) {
        con.close();
    }
}
