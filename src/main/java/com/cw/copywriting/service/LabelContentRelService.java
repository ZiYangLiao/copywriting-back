package com.cw.copywriting.service;

import com.cw.copywriting.bean.LabelContentRelBean;
import com.cw.copywriting.dao.LabelContentRelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

/**
 * @auther Liao ziyang
 * @date 2020/3/26
 * @desc
 */
@Service
public class LabelContentRelService {

    @Autowired
    private LabelContentRelRepository labelContentRelRepository;

    public LabelContentRelBean save(LabelContentRelBean labelContentRelBean) {
        return labelContentRelRepository.save(labelContentRelBean);
    }

    public LabelContentRelBean findOne(LabelContentRelBean labelContentRelBean) {
        return labelContentRelRepository.findOne(Example.of(labelContentRelBean)).orElseGet(null);
    }
}
