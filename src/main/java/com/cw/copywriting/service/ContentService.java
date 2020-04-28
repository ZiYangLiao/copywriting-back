package com.cw.copywriting.service;

import com.cw.copywriting.bean.ContentBean;
import com.cw.copywriting.bean.LabelBean;
import com.cw.copywriting.bean.LabelContentRelBean;
import com.cw.copywriting.common.Response;
import com.cw.copywriting.common.utils.DateUtil;
import com.cw.copywriting.dao.ContentRepository;
import com.cw.copywriting.dto.ContentDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @auther Liao ziyang
 * @date 2020/3/26
 * @desc
 */
@Service
public class ContentService {

    @Autowired
    private ContentRepository contentRepository;
    @Autowired
    private LabelService labelService;
    @Autowired
    private LabelContentRelService labelContentRelService;

    public void addWA(ContentDto content) {
        if (StringUtils.isBlank(content.getContent())) {
            throw new RuntimeException("文案不能为空");
        }
        ContentBean contentBean = new ContentBean();
        contentBean.setContent(content.getContent());
        contentRepository.findOne(Example.of(contentBean)).ifPresent(e -> {
            throw new RuntimeException("文案已重复");
        });
        ContentBean dataObj = new ContentBean();
        BeanUtils.copyProperties(content, dataObj);
        dataObj.setDatetime(DateUtil.thisTime());
        dataObj = contentRepository.save(dataObj);

        if (StringUtils.isNotBlank(content.getLabel())) {
            String[] labels = content.getLabel().trim().split("#");
            for (String label : labels) {
                LabelBean qo = new LabelBean();
                qo.setLabelName(label);
                LabelBean thisLabel = labelService.save(qo);
                LabelContentRelBean rel = new LabelContentRelBean();
                rel.setLabelId(thisLabel.getId());
                rel.setContentId(dataObj.getId());
                labelContentRelService.save(rel);
            }
        }
    }

    public Response<?> list(ContentDto content) {
        if (StringUtils.isBlank(content.getContent())) {
            return Response.fail("搜索内容不能为空");
        }
        LabelBean qo = new LabelBean();
        qo.setLabelName(content.getContent().trim());
        LabelBean labelBean = labelService.findOne(qo);

        PageRequest pageable = PageRequest.of(content.getPageNumber(), content.getPageSize(), Sort.Direction.DESC, "id");
        Page<ContentBean> page = null;
        if (labelBean != null) {
            page = contentRepository.findContentAndLabelId(labelBean.getId(), "%" + content.getContent() + "%", pageable);
        } else {
            page = contentRepository.findByContentLike("%" + content.getContent() + "%", pageable);
        }

        return Response.of(page);
    }
}
