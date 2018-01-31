package brave.http;

import brave.internal.Nullable;
import zipkin2.Endpoint;

public abstract class HttpServerAdapter<Req, Resp> extends HttpAdapter<Req, Resp> {

  /**
   * An expression representing an application endpoint, used to group similar requests together.
   *
   * <p>For example, the template "/products/{key}", would match "/products/1" and "/products/2".
   * There is no format required for the encoding, as it is sometimes application defined. The
   * important part is that the value namespace is low cardinality.
   *
   * <p>Conventionally associated with the key "http.template"
   */
  // TODO: backfill definition of http.template in the api project
  @Nullable public String templateFromRequest(Req request) {
    return null;
  }

  /** Like {@link #templateFromRequest}, used when the template isn't visible until the response. */
  // NOTE: FromResponse suffix is needed as Req and Resp are generic params which equate to Object
  // NOTE: these methods aren't pushed a level up because it is only implemented on the server right now
  @Nullable public String templateFromResponse(Resp response) {
    return null;
  }

  /**
   * Returns true if an IP representing the client was readable. Defaults to parse the
   * "X-Forwarded-For" header.
   */
  public boolean parseClientAddress(Req req, Endpoint.Builder builder) {
    String xForwardedFor = requestHeader(req, "X-Forwarded-For");
    return xForwardedFor != null && builder.parseIp(xForwardedFor);
  }
}
