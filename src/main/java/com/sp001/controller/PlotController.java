package com.sp001.controller;

// PlotController.java
import com.sp001.mapper.PlotService;
import com.sp001.pojo.PlotResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PlotController {
    private final PlotService plotService;

    public PlotController(PlotService plotService) {
        this.plotService = plotService;
    }

    @GetMapping("/plot")
    public String showPlot(
            @RequestParam(required = false, defaultValue = "我的图表") String title,
            @RequestParam(required = false, defaultValue = "20") int count,
            Model model) {

        // 调用Python绘图服务
        PlotResponse response = plotService.generatePlot(title, count);

        // 将Base64图像数据传递给前端
        model.addAttribute("imageBase64", response.getImageBase64());
        model.addAttribute("title", title);

        return "plot";  // 返回Thymeleaf模板
    }
}