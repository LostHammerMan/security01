package com.demo.security01.repository.community;

import com.demo.security01.entity.lounge.LoungeEntity;
import com.demo.security01.entity.user.User;

import java.util.List;

public interface LoungeRepositoryCustom {

    public List<LoungeEntity> getAllLoungeWithPaging(Long id, int pageSize);

    // lounge id 최대값
    public Long getMaxLoungeIdx();
}
