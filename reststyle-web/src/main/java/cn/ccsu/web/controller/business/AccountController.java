package cn.ccsu.controller.business;

import cn.ccsu.commom.annotation.OperationLogDetail;
import cn.ccsu.commom.constraint.group.Update;
import cn.ccsu.commom.constraint.validator.ValidList;
import cn.ccsu.commom.enums.OperationType;
import cn.ccsu.commom.enums.OperationUnit;
import cn.ccsu.condition.AccountCondition;
import cn.ccsu.dto.AccountDTO;
import cn.ccsu.utils.requestUtils.PageParam;
import cn.ccsu.utils.responseUtils.RestResult;
import cn.ccsu.utils.responseUtils.ResultUtil;
import cn.ccsu.service.business.AccountService;
import cn.ccsu.vo.AccountVO;
import com.fasterxml.jackson.annotation.JsonView;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @version 1.0
 * @author: TheFei
 * @Date: 2020-05-20
 * @Time: 16:23
 */
@Slf4j
@RestController
@RequestMapping("/account")
public class AccountController
{
    @Autowired
    private AccountService accountService;

    /**
     * 获取多个用户
     *
     * @param accountCondition 查询条件
     * @return
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @JsonView(AccountVO.AccountListView.class)
    @OperationLogDetail(detail = "查询用户list", level = 3, operationType = OperationType.SELECT, operationUnit = OperationUnit.USER)
    public RestResult query(AccountCondition accountCondition, PageParam pageParam)
    {
        PageInfo<AccountVO> query = accountService.query(accountCondition, pageParam);
        return ResultUtil.success(query);
    }


    /**
     * 获取一个用户
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @JsonView(AccountVO.AccountDetailView.class)
    public RestResult getInfo(@PathVariable String id)
    {
        AccountVO query = accountService.queryAccountById(id);
        return ResultUtil.success(query);
    }

    /**
     * 新增用户
     *
     * @param accountDTO
     * @return
     */
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public RestResult create(@Validated @RequestBody AccountDTO accountDTO)
    {
        Integer accountId = accountService.creatAccount(accountDTO);
        return ResultUtil.success();
    }


    /**
     * 新增用户
     *
     * @param accountDTOs
     * @return
     */
    @PostMapping("/addList")
    @ResponseStatus(HttpStatus.OK)
    public RestResult createMany(@Valid @RequestBody ValidList<AccountDTO> accountDTOs)
    {
        log.info("{}", accountDTOs);
        return ResultUtil.success();
    }


    /**
     * 修改一个用户
     *
     * @param id
     * @return
     */
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RestResult update(@PathVariable String id, @Validated @RequestBody AccountDTO accountDTO)
    {
        Integer integer = accountService.updataAccount(accountDTO, id);
        return ResultUtil.success();
    }

    /**
     * 删除一个用户
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RestResult delete(@PathVariable String id)
    {
        Integer integer = accountService.delete(id);
        return ResultUtil.success();
    }

}