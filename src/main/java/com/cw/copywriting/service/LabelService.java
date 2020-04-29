package com.cw.copywriting.service;

import com.alibaba.fastjson.JSONObject;
import com.cw.copywriting.bean.LabelBean;
import com.cw.copywriting.dao.LabelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @auther Liao ziyang
 * @date 2020/3/26
 * @desc
 */
@Service
public class LabelService {

    @Autowired
    private LabelRepository labelRepository;

    public LabelBean save(LabelBean labelBean) {
        LabelBean label = findByLabelName(labelBean);
        if(label == null) {
            return labelRepository.save(labelBean);
        }
        return label;
    }

    public LabelBean findByLabelName(LabelBean labelBean) {
        return labelRepository.findByLabelName(labelBean.getLabelName());
    }

    public List<LabelBean> findByLabelList(int contentId) {
        return labelRepository.findByLabelList(contentId);
    }

}
