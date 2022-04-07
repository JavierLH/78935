package mx.uv.t4is.SaludosBd;

//import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import https.t4is_uv_mx.saludos.BorrarSaludoRequest;
import https.t4is_uv_mx.saludos.BorrarSaludoResponse;
import https.t4is_uv_mx.saludos.BuscarSaludosResponse;
import https.t4is_uv_mx.saludos.ModificarSaludoRequest;
import https.t4is_uv_mx.saludos.ModificarSaludoResponse;
import https.t4is_uv_mx.saludos.SaludarRequest;
import https.t4is_uv_mx.saludos.SaludarResponse;
//import https.t4is_uv_mx.saludos.BuscarSaludosResponse.Saludos;



@Endpoint
public class SaludosEndPoint{
   
    //private int value=1;
    //ArrayList<Saludos> lista = new ArrayList<>();
    @Autowired
    Isaludadores isaludadores;

    @PayloadRoot(localPart = "SaludarRequest", namespace = "https://t4is.uv.mx/saludos")
    @ResponsePayload
    public SaludarResponse Saludar(@RequestPayload SaludarRequest peticion) {
        SaludarResponse respuesta = new SaludarResponse();
        //se agrega a la lista
        mx.uv.t4is.SaludosBd.Saludadores e = new mx.uv.t4is.SaludosBd.Saludadores();
        respuesta.setRespuesta("Hola "+ peticion.getNombre());
        e.setNombre(peticion.getNombre());
        //e.setId(value++);
        isaludadores.save(e);
        //lista.add(e);
        return respuesta;
        
    }

   
    @PayloadRoot(localPart = "BuscarSaludosRequest", namespace = "https://t4is.uv.mx/saludos")
    @ResponsePayload
    public BuscarSaludosResponse buscarSaludos(){
        BuscarSaludosResponse respuesta = new BuscarSaludosResponse();
       Iterable<Saludadores> lista = isaludadores.findAll();
        //recorrer la lista
        //Iterable<>
        for (Saludadores o : lista) {
            //System.out.println(rLista);
            BuscarSaludosResponse.Saludos e = new BuscarSaludosResponse.Saludos();
            e.setNombre(o.getNombre());
            e.setId(o.getId());
            respuesta.getSaludos().add(e);
           
            //e.setNombre(rLista);
           // respuesta.getSaludos().add(rLista);    
        }
        return respuesta;
    }

    @PayloadRoot(localPart = "ModificarSaludoRequest", namespace = "https://t4is.uv.mx/saludos")
    @ResponsePayload
    public ModificarSaludoResponse modificarSaludo(@RequestPayload ModificarSaludoRequest peticion){
        ModificarSaludoResponse respuesta = new ModificarSaludoResponse();
        //recorrer la lista
        Saludadores element = new Saludadores();
        element.setId(peticion.getId());
        element.setNombre(peticion.getNombre());
        isaludadores.save(element);
        //lista.set(peticion.getId()-1,element);
        respuesta.setRespuesta(true);
        
        return respuesta;
    }

    @PayloadRoot(localPart = "BorrarSaludoRequest", namespace = "https://t4is.uv.mx/saludos")
    @ResponsePayload
    public BorrarSaludoResponse borrarSaludo(@RequestPayload BorrarSaludoRequest peticion){
        BorrarSaludoResponse respuesta = new BorrarSaludoResponse();
        //eliminar de la lista
        //lista.remove(peticion.getId()-1);
        //respuesta.setRespuesta(true);

        /*for (Saludos o : lista) {
            if(o.getId()==peticion.getId()){
                lista.remove(o);
                break;
            }
        }*/
        isaludadores.deleteById(peticion.getId());
        respuesta.setRespuesta(true);
        return respuesta;
    }


    
}