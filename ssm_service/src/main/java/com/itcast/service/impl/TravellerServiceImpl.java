package com.itcast.service.impl;

import com.itcast.domain.Traveller;
import com.itcast.service.ITravellerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TravellerServiceImpl implements ITravellerService {
    @Override
    public List<Traveller> findAll() {
        return null;
    }
}
