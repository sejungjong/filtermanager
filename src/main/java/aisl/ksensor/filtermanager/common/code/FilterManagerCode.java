package aisl.ksensor.filtermanager.common.code;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

public class FilterManagerCode {

    public static enum ServiceCommonCode {
        @JsonProperty("filtermanager")
        MANAGER("filtermanager"),

        @JsonProperty("filter_engine")
        ENGINE("filter_engine"),

        @JsonProperty(":/serviceZoneFilter")
        ENGINE_MOUNT_DIRECTORY(":/serviceZoneFilter");

        private String code;

        @JsonCreator
        private ServiceCommonCode(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }

        @JsonValue
        public static ServiceCommonCode parseType(String code) {
            for (ServiceCommonCode attributeType : values()) {
                if (attributeType.getCode().equals(code)) {
                    return attributeType;
                }
            }
            return null;
        }
    }

    public static enum ServiceZoneCode {

        @JsonProperty("isp_filter_engine:v1")
        ENGINE_IMAGE_NAME("isp_filter_engine:v1"),
        @JsonProperty("/api/auth")
        SERVICE_ZONE_AUTH_ENDPOINT("/api/auth"),

        @JsonProperty("/api/endpoints/4/docker/containers/create")
        SERVICE_ZONE_CREATE_ENGINE_ENDPOINT("/api/endpoints/4/docker/containers/create"),

        @JsonProperty("/api/endpoints/4/docker/containers/%s/start")
        SERVICE_ZONE_START_ENGINE_ENDPOINT("/api/endpoints/4/docker/containers/%s/start"),

        @JsonProperty("/api/endpoints/4/docker/containers/%s/start")
        SERVICE_ZONE_CONFIRM_CONTAINER_ENDPOINT("/api/endpoints/4/docker/containers/%s/json"),

        @JsonProperty("/api/endpoints/4/docker/containers/%s/json")
        SERVICE_ZONE_CONFIRM_IMAGE_ENDPOINT("/api/endpoints/4/docker/images/%s/json"),

        @JsonProperty("ml-engine")
        ML_ENGINE("ml-engine"),

        @JsonProperty("optimization-engine")
        OPTIMIZATION_ENGINE("optimization-engine"),

        @JsonProperty("filter-engine")
        FILTER_ENGINE("filter-engine"),

        @JsonProperty("sensor-controller")
        SENSOR_CONTROLLER("sensor-controller"),

        @JsonProperty("9101/tcp")
        SERVICE_ZONE_OPTIMIZATIONENGINE_PORT("9101/tcp"),

        @JsonProperty("9102/tcp")
        SERVICE_ZONE_MLENGINE_PORT("9102/tcp"),

        @JsonProperty("9103/tcp")
        SERVICE_ZONE_FILTERENGINE_PORT("9103/tcp"),

        @JsonProperty("1")
        SERVICE_ZONE_MLENGINE_GPU_COUNT("1"),
        @JsonProperty("8g")
        SERVICE_ZONE_MLENGINE_GPU_MEMORY("8g"),

        @JsonProperty("0")
        SERVICE_ZONE_ENGINE_GPU_DEVICES("0"),

        @JsonProperty("0")
        SERVICE_ZONE_ENGINE_GPU_COUNT("0"),
        @JsonProperty("0-1")
        SERVICE_ZONE_ENGINE_CPU_CORE("0-3"),

        @JsonProperty("0g")
        SERVICE_ZONE_ENGINE_GPU_MEMORY("0g"),

        @JsonProperty("4")
        SERVICE_ZONE_ENGINE_ENVIRONMENT_ID("4"),

        @JsonProperty("17179869184")
        SERVICE_ZONE_ENGINE_RAM_MEMORY("17179869184");

        private String code;

        @JsonCreator
        private ServiceZoneCode(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }

        public Long getLongValue() { return Long.valueOf(code); }

        public Integer getIntValue() { return Integer.valueOf(code); }

        @JsonValue
        public static ServiceZoneCode parseType(String code) {
            for (ServiceZoneCode attributeType : values()) {
                if (attributeType.getCode().equals(code)) {
                    return attributeType;
                }
            }
            return null;
        }
    }


    public static enum ErrorCode {
        UNKNOWN_ERROR("C001"),
        NOT_EXIST_ENTITY("C002"),
        SQL_ERROR("C003"),
        MEMORY_QUEUE_INPUT_ERROR("C004"),
        REQUEST_MESSAGE_PARSING_ERROR("C005"),
        RESPONSE_MESSAGE_PARSING_ERROR("C006"),
        INVALID_ENTITY_TYPE("C007"),
        INVALID_ACCEPT_TYPE("C008"),
        INVALID_PARAMETER("C009"),
        INVALID_AUTHORIZATION("C0010"),

        LENGTH_REQUIRED("C0011"),
        ALREADY_EXISTS("C0012"),
        NOT_EXIST_ID("C013"),
        LOAD_ENTITY_SCHEMA_ERROR("C014"),
        CREATE_ENTITY_TABLE_ERROR("C015"),
        NOT_EXISTS_IMAGE("C016"),
        ENGINE_SETUP_ERROR("C017"),
        ENGINE_ALREADY_EXISTS("C018"),
        ENGINE_START_ERROR("C019"),
        MALFORMED_JSON("C020"),
        MALFORMED_URL("C021"),
        HTTP_POST_CLIENT_ERROR("C022"),
        HTTP_GET_CLIENT_ERROR("C023"),

        NOT_SUPPORTED_METHOD("C101"),
        PROVISIONING_ERROR("C114");;

        private String code;

        private ErrorCode(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }
    }
}
