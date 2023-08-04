package aisl.ksensor.filtermanager.common.handler;

import aisl.ksensor.filtermanager.common.code.FilterManagerCode;
import aisl.ksensor.filtermanager.common.exception.EngineException;
import reactor.core.publisher.Mono;

public class EngineExceptionHandler {

    // Handle authEngine error
    public Mono<? extends String> handleAuthEngineError(String jwt) {
        return Mono.just(jwt)
                .onErrorResume(error -> {
                    if (error instanceof EngineException && ((EngineException) error).getErrorCode().equals(FilterManagerCode.ErrorCode.INVALID_AUTHORIZATION.getCode())) {
                        System.out.println(error.getMessage());
                    }
                    return Mono.error(error);
                });
    }

    // Handle confirmImage error
    public Mono<? extends String> handleConfirmImageError(String image) {
        return Mono.just(image)
                .onErrorResume(error -> {
                    if (error instanceof EngineException && ((EngineException) error).getErrorCode().equals(FilterManagerCode.ErrorCode.NOT_EXISTS_IMAGE.getCode())) {
                        System.out.println(error.getMessage());
                    }
                    return Mono.error(error);
                });
    }

    // Handle createEngine error
    public Mono<? extends String> handleCreateEngineError(String createdEngine) {
        return Mono.just(createdEngine)
                .onErrorResume(error -> {
                    if (error instanceof EngineException && ((EngineException) error).getErrorCode().equals(FilterManagerCode.ErrorCode.ENGINE_SETUP_ERROR.getCode())) {
                        System.out.println(error.getMessage());
                    }
                    return Mono.error(error);
                });
    }
}
