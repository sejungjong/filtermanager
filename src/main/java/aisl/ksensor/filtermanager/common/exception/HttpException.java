package aisl.ksensor.filtermanager.common.exception;

import aisl.ksensor.filtermanager.common.code.FilterManagerCode;

public class HttpException extends BaseException{
    private static final long serialVersionUID = 15156165165165165L;

    public HttpException( FilterManagerCode.ErrorCode errorCode ) {
        super( errorCode );
    }

    public HttpException(FilterManagerCode.ErrorCode errorCode, String msg ) {
        super( errorCode, msg );
        this.errorCode = errorCode;
    }

    public HttpException(FilterManagerCode.ErrorCode errorCode, Throwable throwable ) {
        super( errorCode, throwable );
        this.errorCode = errorCode;
    }

    public HttpException(FilterManagerCode.ErrorCode errorCode, String msg, Throwable throwable ) {
        super( errorCode, msg, throwable );
        this.errorCode = errorCode;
    }
}
