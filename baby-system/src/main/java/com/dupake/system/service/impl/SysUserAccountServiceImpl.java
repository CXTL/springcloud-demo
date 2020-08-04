package com.dupake.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.dupake.common.message.CommonResult;
import com.dupake.common.pojo.dto.res.finance.AccountDTO;
import com.dupake.common.pojo.dto.res.system.UserAccountDTO;
import com.dupake.system.entity.SysUserAccount;
import com.dupake.system.entity.SysUserRole;
import com.dupake.system.feign.FinanceService;
import com.dupake.system.mapper.SysUserAccountMapper;
import com.dupake.system.service.SysUserAccountService;
import com.dupake.system.utils.RedisUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户帐套关联表  服务实现类
 * </p>
 *
 * @author dupake
 * @since 2020-07-26
 */
@Service
public class SysUserAccountServiceImpl  implements SysUserAccountService {
    
    
    @Resource
    private SysUserAccountMapper sysUserAccountMapper;
    
    @Resource
    private FinanceService financeService;

    /**
     * 分配帐套
     * @param userId
     * @param accountCodes
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public CommonResult allocAccount(Long userId, List<String> accountCodes) {
        //删除原来的用户角色关联关系
        int delete = sysUserAccountMapper.delete(new LambdaUpdateWrapper<SysUserAccount>().eq(SysUserAccount::getUserId, userId));

        if(!CollectionUtils.isEmpty(accountCodes)){
            List<SysUserAccount> sysUserAccounts = accountCodes.stream().map(a -> {
                SysUserAccount sysUserRole = new SysUserAccount();
                sysUserRole.setAccountCode(a);
                sysUserRole.setUserId(userId);
                return sysUserRole;
            }).collect(Collectors.toList());
            sysUserAccountMapper.insertBatch(sysUserAccounts);
        }
        return CommonResult.success();

    }

    /**
     * 查询帐套列表
     * @return
     */
    @Override
    public CommonResult<List<AccountDTO>> listAll() {
        CommonResult<List<AccountDTO>> listCommonResult = financeService.listAll();
        return listCommonResult;
    }

    /**
     * 根据用户ID查询帐号编码
     * @param userId
     * @return
     */
    @Override
    public CommonResult<List<UserAccountDTO>> getAccountByAdmin(Long userId) {
        List<UserAccountDTO> accountDTOS = new ArrayList<>();
        List<SysUserAccount> sysUserAccounts = sysUserAccountMapper.selectList(new LambdaQueryWrapper<SysUserAccount>().eq(SysUserAccount::getUserId, userId));
        if(!CollectionUtils.isEmpty(sysUserAccounts)){
            accountDTOS = sysUserAccounts.stream().map(a -> {
                UserAccountDTO dto = new UserAccountDTO();
                dto.setAccountCode(a.getAccountCode());
                dto.setUserId(a.getUserId());
                return dto;
            }).collect(Collectors.toList());
        }
        return CommonResult.success(accountDTOS);
    }
}
