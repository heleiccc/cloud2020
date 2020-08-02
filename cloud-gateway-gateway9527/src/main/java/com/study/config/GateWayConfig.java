package com.study.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GateWayConfig {
    /**
     * 配置了一个id为route_baidu的路由规则，
     * 当访问http://localhost:9527/guoji时，会自动转发到http://news.baidu.com/guoji
     * @param routeLocatorBuilder
     * @return
     */
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder){
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        routes.route("route_baidu",
                r -> r.path("/guoji").
                        uri("http://news.baidu.com/guoji")).build();
        return routes.build();
    }
}
