package com.ericdschmid.SecurityBackendApplication.controller;

import com.ericdschmid.SecurityBackendApplication.model.Notice;
import com.ericdschmid.SecurityBackendApplication.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
public class NoticesController {

    @Autowired
    private NoticeRepository noticeRepository;

    @GetMapping("/notices")
    public ResponseEntity<List<Notice>> getNotices() {
        System.out.println("\n*** USER GET request for notices details");
        List<Notice> notices = noticeRepository.findAllActiveNotices();
        if (notices != null) {
            //This says if the user tries to do this call within 60 seconds of load the call won't happen from the UI application, rather it will try to use the notices details it already has in the cache
            return ResponseEntity.ok()
                    .cacheControl(CacheControl.maxAge(60, TimeUnit.SECONDS))
                    .body(notices);
        } else {
            return null;
        }
    }
}
