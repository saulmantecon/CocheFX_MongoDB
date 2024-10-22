package org.example.DAO;

import com.mongodb.ErrorCategory;
import com.mongodb.MongoWriteException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import org.example.model.Coche;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class CocheDAO {

    public static MongoCollection<Document> collectionCoches;
    public static MongoCollection<Document> collectionTipos;

    public static boolean crearCoche(Coche coche) {
        Document doc = new Document();
        boolean crear=false;
        doc.append("matricula", coche.getMatricula())
                .append("marca", coche.getMarca())
                .append("modelo", coche.getModelo())
                .append("tipo", coche.getTipo());
        try {
            collectionCoches.insertOne(doc);
            crear=true;
        }catch (MongoWriteException mwe) {
            mwe.getError().getCategory();
        }
        return crear;
    }//crear coche

    public static boolean eliminarCoche(String matricula){
        boolean eliminado=false;
        if (collectionCoches.deleteOne(Filters.eq("matricula",matricula)).getDeletedCount()>0){
            eliminado=true;
        }
        return eliminado;
    }//eliminarCoche

    public static boolean actualizarCoche(Coche coche, Coche cocheViejo){
        boolean eliminado=false;
        if (collectionCoches.updateOne(
                new Document("matricula", cocheViejo.getMatricula()),
                new Document("$set", new Document("matricula", coche.getMatricula())
                        .append("marca", coche.getMarca())
                        .append("modelo", coche.getModelo())
                        .append("tipo", coche.getTipo()))
        ).getModifiedCount() > 0) {
            eliminado = true;
        }
        return eliminado;
    }//actualizarCoche

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
    }//listarCoches




}
