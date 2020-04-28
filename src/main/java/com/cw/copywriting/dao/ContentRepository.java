package com.cw.copywriting.dao;

import com.cw.copywriting.bean.ContentBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @auther Liao ziyang
 * @date 2018/8/21
 * @desc
 */
@Repository
public interface ContentRepository extends JpaRepository<ContentBean, Long> {

    Page<ContentBean> findByContentLike(String content, Pageable pageable);

    @Query(value = "SELECT DISTINCT c.id,c.content FROM ContentBean c " +
            "where c.id in " +
            "(SELECT lcr.contentId from LabelContentRelBean lcr where lcr.labelId = :labelId) " +
            " or c.content LIKE :content")
    Page<ContentBean> findContentAndLabelId(@Param(value = "labelId") int labelId, @Param(value = "content")String content,  Pageable pageable);
}
