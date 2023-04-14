//package com.ggy.gateway;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import com.alibaba.fastjson.serializer.SerializerFeature;
//import com.ggy.gateway.util.JwtUtil;
//import org.springframework.cloud.gateway.filter.GatewayFilterChain;
//import org.springframework.cloud.gateway.filter.GlobalFilter;
//import org.springframework.core.Ordered;
//import org.springframework.core.io.buffer.DataBuffer;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.server.reactive.ServerHttpRequest;
//import org.springframework.http.server.reactive.ServerHttpResponse;
//import org.springframework.stereotype.Component;
//import org.springframework.util.MultiValueMap;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Mono;
//
///***
// *  @title: AuthorizeFilter
// *  @author: GaoGuiYun
// *  @version: 1.0.0
// *  @create: 2023-04-12 15:51
// ***/
//@Component
//public class AuthorizeFilter implements GlobalFilter, Ordered {
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        String requestUrl = exchange.getRequest().getURI().getPath();//获取请求地址
//        System.out.println("开始拦截");
//        System.out.println(requestUrl);
//        MultiValueMap<String,String> params = exchange.getRequest().getQueryParams();/*获取参数列表*/
//
//        String loginUrl = "/user/login";
//            if(!exchange.getRequest().getHeaders().containsKey("Authorization")){
//                JSONObject json = new JSONObject();
//                json.put("desc","无token值");
//                json.put("code","401");
//                return returnJsonResult(exchange,json.toJSONString());
//            }
//            String token = exchange.getRequest().getHeaders().getFirst("Authorization");
//            System.out.println("token="+token);
//            if (null == token || "".equals(token.trim())) {
//                JSONObject json = new JSONObject();
//                json.put("desc","未登录");
//                json.put("code","401");
//                return returnJsonResult(exchange,json.toJSONString());
//            }
//            System.out.println("token1");
//            try{
//                System.out.println("token2");
//                JwtUtil.parseJWT(token);/* 可以正常解析token就放行*/
//                System.out.println("token3");
//                return  chain.filter(exchange);/* 放行 */
//            }catch (Exception ex){
//                System.out.println("token4");
//                JSONObject json = new JSONObject();
//                json.put("desc","token验证失败");
//                json.put("code","401");
//                return returnJsonResult(exchange,json.toJSONString());
//            }
//        }
//
//    /**
//     * 拦截并返回自定义的json字符串
//     */
//    private Mono<Void> returnJsonResult(ServerWebExchange exchange, String msg) {
//        ServerHttpResponse response = exchange.getResponse();
////        response.setStatusCode(HttpStatus.OK);
//        //这里在返回头添加编码，否则中文会乱码
//        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
//        byte[] bytes = JSON.toJSONBytes(msg, SerializerFeature.WriteMapNullValue);
//        DataBuffer buffer = response.bufferFactory().wrap(bytes);
//        return response.writeWith(Mono.just(buffer));
//    }
//
//    @Override
//    public int getOrder() {
//        return -1;
//    }
//}
