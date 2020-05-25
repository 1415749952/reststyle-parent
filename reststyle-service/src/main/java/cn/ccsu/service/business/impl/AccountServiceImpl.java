package cn.ccsu.service.business.impl;

import cn.ccsu.condition.AccountCondition;
import cn.ccsu.dto.AccountDTO;
import cn.ccsu.mapper.business.AccountMapper;
import cn.ccsu.service.business.AccountService;
import cn.ccsu.domain.table.Account;
import cn.ccsu.utils.requestUtils.PageParam;
import cn.ccsu.utils.uniqueId.SnowFlakeUtil;
import cn.ccsu.vo.AccountVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @version 1.0
 * @author: TheFei
 * @Date: 2020-05-20
 * @Time: 16:26
 */
@Service
public class AccountServiceImpl implements AccountService
{
    @Autowired
    private AccountMapper accountMapper;

    @Override
    public PageInfo<AccountVO> query(AccountCondition accountCondition, PageParam pageParam)
    {
        PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize());
        List<AccountVO> accountVOS = accountMapper.query(accountCondition);
        return new PageInfo<>(accountVOS);
    }


    @Override
    public AccountVO queryAccountById(String id)
    {
        AccountVO accountVO = accountMapper.queryById(id);
        return accountVO;
    }

    @Override
    public Integer creatAccount(AccountDTO accountDTO)
    {
        Account account = new Account();
        BeanUtils.copyProperties(accountDTO, account);
        account.setId(SnowFlakeUtil.getId());
        //account.setPassword(BCrypt.hashpw(account.getPassword(), BCrypt.gensalt()));
        //account.setCreateUser(AccountSecurityUtil.getCurrentUserAuthentication().getId());
        return accountMapper.insert(account);
    }

    @Override
    public Integer updataAccount(AccountDTO accountDTO, String id)
    {
        Account account = new Account();
        BeanUtils.copyProperties(accountDTO, account);
        account.setId(id);
        //account.setPassword(BCrypt.hashpw(account.getPassword(), BCrypt.gensalt()));
        //account.setModifyUser(AccountSecurityUtil.getCurrentUserAuthentication().getId());
        //account.setModifyTime(new Date());
        return accountMapper.updateByPrimaryKeySelective(account);
    }

    @Override
    public Integer delete(String id)
    {
        return accountMapper.deleteById(id);
    }
}