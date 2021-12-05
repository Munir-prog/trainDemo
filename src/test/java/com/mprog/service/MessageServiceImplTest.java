package com.mprog.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MessageServiceImplTest {


    MessageService messageService;

    LocaleServiceImpl localeService;

    @BeforeEach
    void setup(){
        messageService = mock(MessageService.class);
        localeService = mock(LocaleServiceImpl.class);
    }

    @Test
    void localize() {
//        String code = "db.username";
//        Object params = null;
//        when(messageService.localize(code, params))
//                .thenReturn("root");
//
//        messageService.localize(code, params);
//
//        verify(messageService, times(1)).
    }
}