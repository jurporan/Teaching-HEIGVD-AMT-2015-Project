package ch.heigvd.amt.gary.rest.config;

import ch.heigvd.amt.gary.services.dao.BusinessDomainEntityNotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Jan Purro
 */
@Provider
public class GaryExceptionMapper implements ExceptionMapper<BusinessDomainEntityNotFoundException> {

  @Override
  public Response toResponse(BusinessDomainEntityNotFoundException exception) {
    return Response.status(404).
      entity(exception.getMessage()).
      type("text/plain").
      build();
  }

}
