package cn.lxg.community.exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode {
    QUESTION_NOT_FOUND("你找的问题不存在！"),
    USER_NOT_LOG_IN("用户未登录");

    String message;

    CustomizeErrorCode(String s) {
        this.message = s;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
