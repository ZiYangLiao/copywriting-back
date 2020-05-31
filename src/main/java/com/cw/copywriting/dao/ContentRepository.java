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

    ContentBean findByContent(String content);

    Page<ContentBean> findByContentLike(String content, Pageable pageable);

    @Query(value = "SELECT DISTINCT c.* FROM content as c " +
            "where c.id in " +
            "(SELECT lcr.content_id from label_content_rel as lcr where lcr.label_id = :labelId) " +
            " or c.content LIKE :content",
            countQuery = "SELECT count(DISTINCT c.id) FROM content as c " +
                    "where c.id in " +
                    "(SELECT lcr.content_id from label_content_rel as lcr where lcr.label_id = :labelId) " +
                    " or c.content LIKE :content",
            nativeQuery = true)
    Page<ContentBean> findByContentAndLabelId(@Param(value = "labelId") int labelId, @Param(value = "content")String content,  Pageable pageable);
}
