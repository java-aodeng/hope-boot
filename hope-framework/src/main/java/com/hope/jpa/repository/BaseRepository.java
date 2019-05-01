package com.hope.jpa.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.lang.NonNull;

import java.util.List;

/**
 * BaseRepository
 * @program:hope-boot
 * @author johnniang 借鉴https://github.com/halo-dev/halo/blob/v1/src/main/java/run/halo/app/repository/base/BaseRepository.java
 * @author:aodeng
 * @blog:低调小熊猫(https://aodeng.cc)
 * @微信公众号:低调小熊猫
 * @create:2019-03-05 17:31
 **/
@NoRepositoryBean
public interface BaseRepository<DOMAIN,ID> extends JpaRepository<DOMAIN,ID> {

    /**
     * Finds all domain by id list and the specified sort.
     *
     * @param ids  id list of domain must not be null
     * @param sort the specified sort must not be null
     * @return a list of domains
     */
    @NonNull
    List<DOMAIN> findAllByIdIn(@NonNull Iterable<ID> ids, @NonNull Sort sort);

    /**
     * Deletes by id list.
     *
     * @param ids id list of domain must not be null
     * @return number of rows affected
     */
    long deleteByIdIn(@NonNull Iterable<ID> ids);
}
