package com.springboot.test.test;

import lombok.Data;

/***
 * Created with IntelliJ IDEA.
 * Description:
 * User: silence
 * Date: 2019-04-18
 * Time: 下午2:26
 */
@Data
public class LoggerEntity {

        private String method;
        private String uri;
        private Object[] args;
        private Object result;
        private String operator;
        private String appName;

        /**
         * 获取当前对象
         *
         * @param method
         * @param uri
         * @param args
         * @param result
         * @return
         */
        public LoggerEntity get(String method, String uri, Object[] args, Object result, String operator, String appName) {
            setMethod(method);
            setUri(uri);
            setArgs(args);
            setResult(result);
            setOperator(operator);
            setAppName(appName);
            return this;
        }


}
