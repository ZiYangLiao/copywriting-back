package com.cw.copywriting.dao;

import com.cw.copywriting.bean.LabelBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @auther Liao ziyang
 * @date 2018/8/21
 * @desc
 */
@Repository
public interface LabelRepository extends JpaRepository<LabelBean, Long> {

    LabelBean findByLabelName(String labelName);

    @Query(value = "SELECT l.* from label l LEFT JOIN label_content_rel lcr on l.id = lcr.label_id where lcr.content_id =  :contentId", nativeQuery = true)
    List<LabelBean> findByLabelList(@Param("contentId") int contentId);
}
