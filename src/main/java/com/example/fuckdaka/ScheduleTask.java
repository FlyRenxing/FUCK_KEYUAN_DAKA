package com.example.fuckdaka;

import cc.renxing.MessageTools;
import com.example.fuckdaka.dao.CardDao;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;

import static cc.renxing.model.MsgMeta.MSG_TYPE_QQ;

@Component
public class ScheduleTask {
    @Autowired
    CardDao cardDao;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    MessageTools messageTools;
    @Value("${app.admin-qq}")
    String adminQQ;

    @Scheduled(cron = "${app.daka-task.cron}")
    public void dakaTask() throws JsonProcessingException {
        messageTools.send("十分钟后开始打卡", MSG_TYPE_QQ, adminQQ);
        try {
            Thread.sleep(1000 * 60 * 10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        daka();
    }

    public void daka() {
        //当前时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = sdf.format(System.currentTimeMillis());
        //请求头
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        //jackson
        ObjectMapper objectMapper = new ObjectMapper();

        cardDao.findAll().parallelStream().forEach(card -> {
//            在600秒内随机休眠
            try {
                Thread.sleep((long) (Math.random() * 600 * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            card.setTbrq(time);

            MultiValueMap<String, Object> valueMap = new LinkedMultiValueMap<>();
            valueMap.setAll(objectMapper.convertValue(card, new TypeReference<>() {
            }));
            HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(valueMap, httpHeaders);

            ResponseEntity<String> response = restTemplate.postForEntity("http://cas.hnkjxy.net.cn/mp-czzx/save", requestEntity, String.class);
            String msg = "请求内容：" + requestEntity
                    + "\r\n打卡响应码：" + response.getStatusCodeValue()
                    + "\r\n结果：" + response.getBody();
            try {
                messageTools.send(msg, MSG_TYPE_QQ, String.valueOf(card.getQq()));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        });
    }
}

