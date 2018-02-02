package xin.com.funtrek.http;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by D-H-F on 2018/01/26.
 */

class HttpInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();

        String s = request.url().toString();

        Request request1 = request.newBuilder().url(s + (s.contains("?") ? "&" : "?")
                + "source=android&appVersion=101").build();

        return chain.proceed(request1);
    }
}
