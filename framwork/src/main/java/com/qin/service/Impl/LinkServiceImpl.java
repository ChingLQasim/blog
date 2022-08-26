package com.qin.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qin.Utils.BeanCopyUtils;
import com.qin.constants.SystemConstants;
import com.qin.domain.ResponseResult;
import com.qin.domain.entity.Link;
import com.qin.domain.vo.LinkVo;
import com.qin.mapper.LinkMapper;
import org.springframework.stereotype.Service;
import com.qin.service.LinkService;

import java.util.List;

/**
 * 友链(Link)表服务实现类
 *
 * @author qinli
 * @since 2022-08-19 14:23:18
 */
@Service("linkService")
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements LinkService {

    @Override
    public ResponseResult getAllLink() {
        //查询所有审核通过的Links
        LambdaQueryWrapper<Link> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Link::getStatus, SystemConstants.LINK_STATUS_NORMAL);
        List<Link> links = list(queryWrapper);
        //转化成Vo
        List<LinkVo> linkVos = BeanCopyUtils.copyBeanList(links, LinkVo.class);
        return ResponseResult.okResult(linkVos);
    }
}
