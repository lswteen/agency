package kr.co.within.dubra.response;

public enum ResponseCode {
    SUCCESS(1000, "Ok"),                                                                    //성공
    BAD_REQUEST(4000, "Bad Request"),                                                       //파라메타 정보가 정확하지 않음
    DOSE_NOT_REQUIRED_HEADER(4001, " It does not exist the value of the Required Header"),  //RestKey 정보가 정확하지 않음
    INVALID_REST_KEY(4002, "Invalid RestKey"),                                              //RestKey 정보가 정확하지 않음
    INVALID_AGENCY_CODE(4003, "Invalid Agency Code"),                                       //등록된 agency 정확하지 없음
    NotFound(9005, "Resource not found"),                                                   //리소스를 찾을수 없음
    ACCESS_DENIED_REST_KEY(9006, "Access Denied"),
    ACCESS_DENIED_IP(9007, "Access Denied"),
    INTERNAL_SERVER_ERROR(5000, "Internal server error"),                                   //내부 서버오류
    SERVICE_ERROR(4005, "General service error"),                                           //일반적인 서비스오류
    READ_TIME_OUT(9700, "Asset Read Time out"),
    CONNECT_TO_ASSET_TIME_OUT(9710, "Connect to Asset Time out"),
    CONNECTION_TO_ASSET_REFUSED(9710, "Connection to Asset refused"),
    CONNECTION_COLSED(9720,"Connection Closed"),
    ValidationFail(9004, "Validation Faild");

    private final Integer code;
    private final String message;

    private ResponseCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }

    public static ResponseCode getInstance(int code){
        ResponseCode instance = null;
        for(ResponseCode response : ResponseCode.values()){
            if(response.getCode() == code)
                instance = response;
        }

        if(instance == null){
            return ResponseCode.INTERNAL_SERVER_ERROR;
        }

        return instance;
    }
}
