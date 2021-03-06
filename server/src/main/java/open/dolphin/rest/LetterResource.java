package open.dolphin.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import open.dolphin.infomodel.LetterModule;
import open.dolphin.session.LetterServiceBean;

/**
 * LetterResource
 *
 * @author Kazushi Minagawa, Digital Globe, Inc.
 * @author modified by masuda, Masuda Naika
 */
@Path("odletter")
public class LetterResource extends AbstractResource {

    private static final boolean debug = false;
    
    @Inject
    private LetterServiceBean letterServiceBean;

    public LetterResource() {
    }

    @PUT
    @Path("letter")
    @Consumes(MEDIATYPE_JSON_UTF8)
    @Produces(MEDIATYPE_TEXT_UTF8)
    public Response putLetter(String json) {

        LetterModule model = getConverter().fromJson(json, LetterModule.class);

        Long pk = letterServiceBean.saveOrUpdateLetter(model);

        String pkStr = String.valueOf(pk);
        debug(pkStr);

        return Response.ok(pkStr).build();
    }

    @GET
    @Path("list/{karteId}")
    @Produces(MEDIATYPE_JSON_UTF8)
    public Response getLetterList(@PathParam("karteId") Long karteId) {

        List<LetterModule> list = letterServiceBean.getLetterList(karteId);
        
        TypeReference typeRef = new TypeReference<List<LetterModule>>(){};
        StreamingOutput so = getJsonOutStream(list, typeRef);
        
        return Response.ok(so).build();
    }

    @GET
    @Path("letter/{pk}")
    @Produces(MEDIATYPE_JSON_UTF8)
    public Response getLetter(@PathParam("pk") Long pk) {

        LetterModule result = letterServiceBean.getLetter(pk);
        
        StreamingOutput so = getJsonOutStream(result);
        
        return Response.ok(so).build();
    }

    @DELETE
    @Path("letter/{pk}/")
    public void delete(@PathParam("pk") Long pk) {

        letterServiceBean.delete(pk);
    }


    @Override
    protected void debug(String msg) {
        if (debug || DEBUG) {
            super.debug(msg);
        }
    }
}
