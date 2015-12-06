package ch.heigvd.amt.gary.services.dao;

/**
 * Something we copied from the MVCDemo project. We are not sure where and if 
 * this exception is thrown somewhere but there it is anyway.
 */
public class BusinessDomainEntityNotFoundException extends Exception {

  /**
   * Creates a new instance of
   * <code>BusinessDomainEntityNotFoundException</code> without detail message.
   */
  public BusinessDomainEntityNotFoundException() {
  }

  /**
   * Constructs an instance of
   * <code>BusinessDomainEntityNotFoundException</code> with the specified
   * detail message.
   *
   * @param msg the detail message.
   */
  public BusinessDomainEntityNotFoundException(String msg) {
    super(msg);
  }
}