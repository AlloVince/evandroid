package resthttp.execption;

import java.util.List;

/**
 * Created by allovince on 15/8/17.
 */
public class RestErrorMessage {

    /**
     * errors : [{"message":"ERR_AUTH_TOKEN_NOT_INPUT","message_human":"Token未输入","code":10000}]
     */
    private List<ErrorsEntity> errors;

    public void setErrors(List<ErrorsEntity> errors) {
        this.errors = errors;
    }

    public List<ErrorsEntity> getErrors() {
        return errors;
    }

    public static class ErrorsEntity {
        /**
         * message : ERR_AUTH_TOKEN_NOT_INPUT
         * message_human : Token未输入
         * code : 10000
         */
        private String message;
        private String message_human;
        private int code;

        public void setMessage(String message) {
            this.message = message;
        }

        public void setMessageHuman(String message_human) {
            this.message_human = message_human;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public String getMessageHuman() {
            return message_human;
        }

        public int getCode() {
            return code;
        }
    }
}
