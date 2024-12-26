package com.demo.security01.repository.user;

import com.demo.security01.entity.user.*;
import com.demo.security01.model.dto.admin.AdminUpdateDto;
import com.demo.security01.model.dto.paging.Criteria;
import com.demo.security01.model.dto.user.modifyUser.ModifyUserDto;
import com.demo.security01.model.dto.user.modifyUser.ModifyUserEmailDto;
import com.demo.security01.model.dto.user.modifyUser.ModifyUserProfileDto;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


import static com.demo.security01.entity.user.QUser.user;
import static com.demo.security01.entity.user.QUserAddr.userAddr;
import static com.demo.security01.entity.user.QUserProfile.userProfile;

@Slf4j
@Repository
@RequiredArgsConstructor
public class UserRepositoryCustomImpl implements UserRepositoryCustom {

    private final JPAQueryFactory queryFactory;
//    private final EntityManager em;

   /* @Autowired
    EntityManager em;

    JPAQueryFactory queryFactory = new JPAQueryFactory(em);*/

   /* public UserRepositoryCustomImpl(EntityManager em){
        this.queryFactory = new JPAQueryFactory(em);
    }*/

    @Override
    public List<User> findAllUserList() {
        return queryFactory
                .selectFrom(user)
                .orderBy(user.id.desc())
                .fetch();
    }

   /* @Override
    public List<User> findAllUserList() {

        // Expressions.stringTemplate(MySQL 문법, 변경할 값, 변경할 양식)
        StringTemplate formattedDate = Expressions.stringTemplate(
                "DATE_FORMAT({0}, {1})"
                , user.createDate
                , ConstantImpl.create("%Y-%m-%d")
        );

        return queryFactory
                .select(
                        user.id,
                        user.username,
                        user.password,
                        user.role,
                        user.email,
                        Projections.fields(User.class, formattedDate)
                )
                .orderBy(user.id.desc())
                .fetch();
    }*/

    // 전체 회원 수
    @Override
    public int userListCnt(Criteria cri) {
        return Math.toIntExact(queryFactory
                .select(user.count())
                .from(user)
                .where(searchCondition(cri))
                .fetchFirst());
//        return userListCnt;
    }

    // 회원 목록 + 페이징
    @Override
    public List<User> findAllUser(Criteria cri) {
        // id 파라미터를 첫 페이지에선 사용하지 않기 위한 동적 쿼리
//        BooleanBuilder dynamicUserId = new BooleanBuilder();
        /*if (userId != null){
            dynamicUserId.and(user.id.lt(userId));
        }*/
        List<User> allUser = queryFactory
                .selectFrom(user)
                .where(searchCondition(cri))
//                .limit(cri.getPageStart() & cri.getPerPageNum())
                .orderBy(user.id.desc())
//                .limit(cri.getPageStart() &cri.getPerPageNum())
//                .limit(cri.getPageStart())
                .offset(cri.getPageStart())
                .limit(cri.getPerPageNum())
                .fetch();
//        System.out.println("allUser = " + allUser);
        return allUser;

    }

    // 검색을 위한 조건 BooleanBuilder 로 생성
    @Override
    public BooleanBuilder searchCondition(Criteria cri) {
        BooleanBuilder builder = null;

        if (cri.getType() != null){
            if (!cri.getType().isEmpty() && !cri.getKeyword().isEmpty()){
                switch (cri.getType()){
                    // U, E, R
                    case "U" :
                        builder = new BooleanBuilder(user.username.contains(cri.getKeyword()));
                        break;
                    case "E" :
                        builder = new BooleanBuilder(user.email.contains(cri.getKeyword()));
                        break;
                    case "R" :
                        builder = new BooleanBuilder(user.role.stringValue().contains(cri.getKeyword()));
                        break;

                }
            }
        }
        return builder;
    }

    @Override
    public User findUser(int id) {
        return queryFactory
                .selectFrom(user)
                .where(user.id.eq(id))
                .fetchOne();
    }

    @Override
    public User findUserByUsername(String name) {
        log.info("========== UserRepository - findUserByUsername ============");
        return queryFactory
                .selectFrom(user)
                .where(user.username.eq(name))
                .fetchOne();

    }

    @Override
    public void updateUser(AdminUpdateDto dto) {
        long count = queryFactory
                .update(user)
                .set(user.role, dto.getRole())
                .where(user.id.eq(dto.getId()))
                .execute();

    }

    @Transactional
    @Override
    public void delete(int id) {
        queryFactory
                .delete(user)
                .where(user.id.eq(id))
                .execute();
    }

    @Override
    public void deleteAll(List<Integer> ids) {
//        List<Integer> ids = new ArrayList<>(id);
        queryFactory
                .delete(user)
                .where(user.id.in(ids))
                .execute();
    }

    @Override
    public boolean existByUsername(String username) {
        log.info("===== UserRepository - existByUsername ===========");
        boolean result = queryFactory
                .select(user.username)
                .from(user)
                .where(user.username.eq(username))
                .fetch().size() > 0;
        log.info("result = {}", result);
        return result;


//        return fetchOne != null;
    }

    // 회원비밀번호 조회
    @Override
    public String findPasswordByUsername(String username) {
        return queryFactory
                .select(user.password)
                .from(user)
                .where(user.username.eq(username))
                .fetchOne();
    }

    // 회원비밀번호 수정


    @Override
    public void updateUserPwd(String pwd, String username) {
        long count = queryFactory
                .update(user)
                .set(user.password, pwd)
                .where(user.username.eq(username))
                .execute();
    }

    @Override
    public void addrSave(ModifyUserDto modifyUserDto) {
        long count = queryFactory
                .update(userAddr)
                .set(userAddr.zipCode, modifyUserDto.getZipCode())
                .set(userAddr.postAddr1, modifyUserDto.getPostAddr1())
                .set(userAddr.postAddr2, modifyUserDto.getPostAddr2())
                .where(userAddr.user.id.eq(modifyUserDto.getUserIdx()))
                .execute();
        log.info("addrSave count = {}", count);
    }

    @Override
    public void emailSave(ModifyUserEmailDto modifyUserEmailDto) {
        long count = queryFactory
                .update(user)
                .set(user.email, modifyUserEmailDto.getModifiedEmailAddr())
                .where(user.id.eq(modifyUserEmailDto.getUserIdx()))
                .execute();
    }

    @Override
    public void addrSave2(UserAddr userAddr) {
        if (userAddr == null){
//            em.persist(userAddr);
        }
    }

    @Override
    public void profileSave(ModifyUserProfileDto modifyUserProfileDto) {
        log.info("modifyUserProfileDto = {}", modifyUserProfileDto);
        /*long count3 = queryFactory
                .update(userProfile)
                .set(userProfile.fileName, modifyUserProfileDto.getProfileImgName())
                .set(userProfile.fileUrl, modifyUserProfileDto.getProfileImgPath())
                .set(userProfile.nickName, modifyUserProfileDto.getNickName())
                .where(userProfile.user.id.eq(modifyUserProfileDto.getUserIdx()))
                .execute();
        log.info("profileUpdate = {}", count3);*/
        long count2 = queryFactory
                .insert(userProfile)
                .set(userProfile.fileName, modifyUserProfileDto.getProfileImgName())
                .set(userProfile.fileUrl, modifyUserProfileDto.getProfileImgPath())
                .set(userProfile.user.id, modifyUserProfileDto.getUserIdx())
                .execute();
        log.info("profileInsert = {}", count2);
        /*if (userProfile.fileName != null){
            long count3 = queryFactory
                    .update(userProfile)
                    .set(userProfile.fileName, modifyUserProfileDto.getProfileImgName())
                    .set(userProfile.fileUrl, modifyUserProfileDto.getProfileImgPath())
                    .set(userProfile.nickName, modifyUserProfileDto.getNickName())
                    .where(userProfile.user.id.eq(modifyUserProfileDto.getUserIdx()))
                    .execute();
            log.info("profileUpdate = {}", count3);

        }else {
            long count2 = queryFactory
                    .insert(userProfile)
                    .set(userProfile.fileName, modifyUserProfileDto.getProfileImgName())
                    .set(userProfile.fileUrl, modifyUserProfileDto.getProfileImgPath())
                    .set(userProfile.nickName, modifyUserProfileDto.getNickName())
                    .set(userProfile.user.id, modifyUserProfileDto.getUserIdx())
                    .execute();
            log.info("profileInsert = {}", count2);

        }*/


    }

    // 저장되어 있는 프로필 이미지 파일 가져오기
    public List<UserProfile> getOldProfileFile(){
      /*  BooleanExpression condition =
                userProfile.profileIdx.isNotNull();*/
        return queryFactory
//                .select(userProfile.fileUrl)
//                .from(userProfile)
//                .where(userProfile.profileIdx.eq(profileIdx))
////                .where(userProfile.fileUrl.eq())
//                .fetchOne();
                .selectFrom(userProfile)
//                .where(userProfile.profileIdx.isNotNull())
                .where(userProfile.profileIdx.isNotNull())
//                .where(userProfile.fileUrl.eq())
                .fetch();
    }


    // Date 추출
   /* StringTemplate formattedDateExpression = Expressions.stringTemplate(
            "DATE_FORMAT({0}, '{1s}')", Pro
    )*/


}
