package com.spring_shop.service;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface MainService {
    byte[] getImage(String img) throws IOException;
}