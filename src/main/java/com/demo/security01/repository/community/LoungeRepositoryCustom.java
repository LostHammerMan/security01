package com.demo.security01.repository.community;

import com.demo.security01.entity.lounge.LoungeEntity;
import com.demo.security01.entity.user.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface LoungeRepositoryCustom {

    public List<LoungeEntity> getAllLoungeWithPaging(Long id, int pageSize);

    public Slice<LoungeEntity> getAllLoungeWithPaging2(Long id, Pageable pageable);

    public Slice<LoungeEntity> checkAndPage(Pageable pageable, List<LoungeEntity> results);

    // lounge id 최대값
    public Long getMaxLoungeIdx();
}
