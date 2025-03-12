package vn.hdoan.exception;/*
 * @project backend-service
 * @author Huy
 */

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String message){
        super(message);
    }

}
