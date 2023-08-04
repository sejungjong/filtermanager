package aisl.ksensor.filtermanager.common.exception;

import aisl.ksensor.filtermanager.common.code.FilterManagerCode.ErrorCode;

public class EngineException extends BaseException{

    private static final long serialVersionUID = 325647261102179280L;

    public EngineException( ErrorCode errorCode ) {
        super( errorCode );
    }

    public EngineException( ErrorCode errorCode, String msg ) {
        super( errorCode, msg );
        this.errorCode = errorCode;
    }

    public EngineException( ErrorCode errorCode, Throwable throwable ) {
        super( errorCode, throwable );
        this.errorCode = errorCode;
    }

    public EngineException( ErrorCode errorCode, String msg, Throwable throwable ) {
        super( errorCode, msg, throwable );
        this.errorCode = errorCode;
    }

}
