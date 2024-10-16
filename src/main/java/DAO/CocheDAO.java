package DAO;

import com.mongodb.ErrorCategory;
import com.mongodb.MongoWriteException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import model.Coche;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class CocheDAO {

    public static MongoCollection<Document> collectionCoches;
    public static MongoCollection<Document> collectionTipos;

    public static String crearCoche(Coche coche) {
        Document doc = new Document();
        String mensaje="";
        doc.append("matricula", coche.getMatricula()).append("marca", coche.getMarca()).append("modelo", coche.getModelo()).append("tipo", coche.getTipo());
        try {
            collectionCoches.insertOne(doc);
            mensaje = "Coche creado correctamente";
        }catch (MongoWriteException mwe) {
            if (mwe.getError().getCategory().equals(ErrorCategory.DUPLICATE_KEY)) {
                mensaje="El documento con esa identificación ya existe";
            }
        }
        return mensaje;
    }//crear coche

    public static List<Coche> listarCoches() {
        List<Coche> listaCoches = new ArrayList<>();
        try (MongoCursor<Document> cursor = collectionCoches.find().iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
               String matricula= (String) doc.get("matricula");
                String marca = (String) doc.get("marca");
                String modelo= (String) doc.get("modelo");
                String tipo = (String) doc.get("tipo");
                Coche c =  new Coche(matricula,marca,modelo,tipo);
                listaCoches.add(c);

            }
        } catch (MongoWriteException mwe) {
            if (mwe.getError().getCategory().equals(ErrorCategory.DUPLICATE_KEY)) {
                mwe.printStackTrace();
            }
        }
        return listaCoches;
    }




}
