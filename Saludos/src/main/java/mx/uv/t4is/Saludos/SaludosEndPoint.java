package mx.uv.t4is.Saludos;

import java.util.ArrayList;

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
import https.t4is_uv_mx.saludos.BuscarSaludosResponse.Saludos;



@Endpoint
public class SaludosEndPoint{
   
    private int value=1;
    ArrayList<Saludos> lista = new ArrayList<>();

    @PayloadRoot(localPart = "SaludarRequest", namespace = "https://t4is.uv.mx/saludos")
    @ResponsePayload
    public SaludarResponse Saludar(@RequestPayload SaludarRequest peticion) {
        SaludarResponse respuesta = new SaludarResponse();
        //se agrega a la lista
        Saludos e = new Saludos();
        respuesta.setRespuesta("Hola "+ peticion.getNombre());
        e.setNombre(peticion.getNombre());
        e.setId(value++);
        lista.add(e);
        return respuesta;
        
    }

   
    @PayloadRoot(localPart = "BuscarSaludosRequest", namespace = "https://t4is.uv.mx/saludos")
    @ResponsePayload
    public BuscarSaludosResponse buscarSaludos(){
        BuscarSaludosResponse respuesta = new BuscarSaludosResponse();
        //recorrer la lista
        for (Saludos rLista : lista) {
            //System.out.println(rLista);
           
            //e.setNombre(rLista);
            respuesta.getSaludos().add(rLista);    
        }
        return respuesta;
    }

    @PayloadRoot(localPart = "ModificarSaludoRequest", namespace = "https://t4is.uv.mx/saludos")
    @ResponsePayload
    public ModificarSaludoResponse modificarSaludo(@RequestPayload ModificarSaludoRequest peticion){
        ModificarSaludoResponse respuesta = new ModificarSaludoResponse();
        //recorrer la lista
        Saludos element = new Saludos();
        element.setId(peticion.getId());
        element.setNombre(peticion.getNombre());
        lista.set(peticion.getId()-1,element);
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

        for (Saludos o : lista) {
            if(o.getId()==peticion.getId()){
                lista.remove(o);
                break;
            }
        }
        respuesta.setRespuesta(true);
        return respuesta;
    }


    
}