package com.cw.copywriting.service;

import com.alibaba.fastjson.JSONObject;
import com.cw.copywriting.bean.ContentBean;
import com.cw.copywriting.bean.LabelBean;
import com.cw.copywriting.bean.LabelContentRelBean;
import com.cw.copywriting.common.Response;
import com.cw.copywriting.common.utils.DateUtil;
import com.cw.copywriting.common.utils.Html2TextUtil;
import com.cw.copywriting.dao.ContentRepository;
import com.cw.copywriting.dto.ContentDto;
import com.cw.copywriting.vo.ContentVO;
import com.cw.copywriting.vo.PageVO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther Liao ziyang
 * @date 2020/3/26
 * @desc
 */
@Service
public class ContentService {

    static Logger logger = LoggerFactory.getLogger(ContentService.class);

    @Autowired
    private ContentRepository contentRepository;
    @Autowired
    private LabelService labelService;
    @Autowired
    private LabelContentRelService labelContentRelService;

    public void addWA(ContentDto content) {

        if (StringUtils.isBlank(content.getContent())) {
            throw new RuntimeException("文案不能为空");
        } else {
            content.setContent(HtmlUtils.htmlEscape(content.getContent()));
            if (StringUtils.isBlank(content.getContent())) {
                throw new RuntimeException("文案去除HTML标签后不能为空");
            }
        }
        logger.info("addWA.content:" + JSONObject.toJSONString(content));
        ContentBean contentBean = contentRepository.findByContent(content.getContent().trim());
        if (contentBean != null) {
            throw new RuntimeException("文案已重复");
        }
        ContentBean dataObj = new ContentBean();
        BeanUtils.copyProperties(content, dataObj);
        dataObj.setDatetime(DateUtil.thisTime());
        dataObj = contentRepository.save(dataObj);

        if (StringUtils.isNotBlank(content.getLabel())) {
            String[] labels = content.getLabel().trim().split("#");
            for (String label : labels) {
                if (StringUtils.isBlank(label)) {
                    continue;
                }
                LabelBean qo = new LabelBean();
                qo.setLabelName(label.trim());
                LabelBean thisLabel = labelService.save(qo);
                LabelContentRelBean rel = new LabelContentRelBean();
                rel.setLabelId(thisLabel.getId());
                rel.setContentId(dataObj.getId());
                labelContentRelService.save(rel);
            }
        }
    }

    public Response<?> list(ContentDto content) {
        Page<ContentBean> page = null;
        PageRequest pageable = PageRequest.of(content.getPageNumber(), content.getPageSize(), Sort.Direction.DESC, "id");
        if (StringUtils.isNotBlank(content.getContent())) {
            LabelBean qo = new LabelBean();
            qo.setLabelName(content.getContent().trim());
            LabelBean labelBean = labelService.findByLabelName(qo);

            if (labelBean != null) {
                page = contentRepository.findByContentAndLabelId(labelBean.getId(), "%" + content.getContent() + "%", pageable);
            } else {
                page = contentRepository.findByContentLike("%" + content.getContent() + "%", pageable);
            }
        } else {
            page = contentRepository.findByContentLike("%" + content.getContent() + "%", pageable);
        }

        System.out.println(JSONObject.toJSONString(page));
        logger.info("list.page:" + JSONObject.toJSONString(page));
        List<ContentVO> contentVOList  = new ArrayList<>();
        List<ContentBean> contentBeanList  = page.getContent();
        for (ContentBean bean: contentBeanList) {
            ContentVO vo = new ContentVO();
            BeanUtils.copyProperties(bean, vo);
            vo.setLabelBeanList(labelService.findByLabelList(vo.getId()));
            contentVOList.add(vo);
        }

        PageVO<ContentVO> pageVO = new PageVO<>();
        pageVO.setPageNumber(page.getNumber());
        pageVO.setPageSize(page.getSize());
        pageVO.setTotal(Integer.parseInt(page.getTotalElements() + ""));
        pageVO.setData(contentVOList);
        return Response.of(pageVO);
    }
}
