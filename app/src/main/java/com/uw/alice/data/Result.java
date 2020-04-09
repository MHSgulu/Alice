package com.uw.alice.data;

/**
 * A generic class that holds a result success w/ data or an error exception.
 * 一个泛型类，它保存一个带有数据或错误异常的结果成功。
 */
public class Result<T> {
    // hide the private constructor to limit subclass types (Success, Error)
    //隐藏私有构造函数以限制子类类型（成功，错误）
    private Result() {
    }

    @Override
    public String toString() {
        if (this instanceof Result.Success) {
            Result.Success success = (Result.Success) this;
            return "Success[data=" + success.getData().toString() + "]";
        } else if (this instanceof Result.Error) {
            Result.Error error = (Result.Error) this;
            return "Error[exception=" + error.getError().toString() + "]";
        }
        return "";
    }

    // Success sub-class
    //成功子类
    public final static class Success<T> extends Result {
        private T data;

        public Success(T data) {
            this.data = data;
        }

        public T getData() {
            return this.data;
        }
    }

    // Error sub-class
    //错误子类
    public final static class Error extends Result {
        private Throwable throwable;

        public Error(Throwable throwable) {
            this.throwable = throwable;
        }

        public Throwable getError() {
            return this.throwable;
        }
    }
}
