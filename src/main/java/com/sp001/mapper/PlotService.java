package com.sp001.mapper;
// PlotService.java
import com.sp001.pojo.PlotResponse;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class PlotService {
    private final RestTemplate restTemplate;
    private final String FASTAPI_URL = "http://localhost:8000/generate";

    // 注入RestTemplate
    public PlotService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public PlotResponse generatePlot(String title, int count) {
        // 构建请求URL
        String url = FASTAPI_URL + "?title=" + title + "&count=" + count;
        try {
            ResponseEntity<PlotResponse> response = restTemplate.getForEntity(url, PlotResponse.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                return response.getBody();
            } else {
                throw new RuntimeException("Python服务返回异常状态码: " + response.getStatusCode());
            }
        } catch (HttpClientErrorException e) {
            // 捕获404等客户端错误
            throw new RuntimeException("调用Python服务失败: " + e.getStatusText(), e);
        } catch (Exception e) {
            // 捕获其他异常（如服务未启动）
            throw new RuntimeException("调用Python服务时发生错误", e);
        }
    }
}