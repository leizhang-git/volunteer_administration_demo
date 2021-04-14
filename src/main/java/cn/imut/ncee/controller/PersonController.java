package cn.imut.ncee.controller;

import cn.imut.ncee.entity.pojo.AlgorithmIndex;
import cn.imut.ncee.entity.pojo.Person;
import cn.imut.ncee.entity.vo.MessageBoard;
import cn.imut.ncee.service.PersonService;
import cn.imut.ncee.utils.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 用户(管理员与普通用户)
 * @Author zhanglei
 * @Date 2021/1/29 18:46
 */
@RestController
@RequestMapping("/api/v1/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    /**
     * 登陆
     * @param id 账号（邮箱）
     * @param password 密码
     * @return 是否登陆成功
     */
    @GetMapping("/login")
    public Results<Boolean> login(String id, String password) {
        boolean isSuccess = personService.login(id, password);
        return Results.dataOf(isSuccess);
    }

    /**
     * 注册
     * @param person id、姓名、密码
     * @return 是否成功注册
     */
    @PostMapping("/register")
    public Results<Boolean> register(@RequestBody Person person) {
        boolean isSuccess = personService.register(person);
        return Results.dataOf(isSuccess);
    }

    /**
     * 查询所有用户信息（管理员除外）
     * @param pageNum 页码
     * @param pageSize 每页显示数量
     * @return 用户信息
     */
    @GetMapping("/queryAllPerson")
    public Results<?> queryAll(@RequestParam(defaultValue = "0",required = false) int pageNum,
                                      @RequestParam(defaultValue = "5",required = false) int pageSize) {
        List<Person> person = personService.selectAllPerson(pageNum, pageSize);
        return Results.dataOf(person);
    }

    /**
     * 根据用户Id修改用户信息
     * @param person 新的用户信息
     * @param id 用户Id
     * @return 是否修改成功
     */
    @PatchMapping("/updatePerson")
    public Results<?> queryAll(@RequestBody Person person, @RequestParam String id) {
        boolean isSuccess = personService.updatePerson(person, id);
        return Results.dataOf(isSuccess);
    }

    /**
     * 根据用户Id查询用户信息
     * @param id 用户Id
     * @return 用户信息
     */
    @GetMapping("/queryPerson")
    public Results<?> queryById(String id) {
        Person person = personService.selectByIdPerson(id);
        return Results.dataOf(person);
    }

    /**
     * 管理员根据Id删除用户信息
     * @param id 用户Id
     * @return 是否成功删除
     */
    @GetMapping("/deletePerson")
    public Results<?> deleteById(String id) {
        boolean isSuccess = personService.deletePerson(id);
        return Results.dataOf(isSuccess);
    }

    /**
     * 用户志愿填报推荐
     * @param algorithmIndex 用户输入指标（文理（0理，1文）、地区、分数、具体专业、专业类别(若输入专业，则默认忽略此指标)）
     * @return 返回推荐的列表
     */
    @PostMapping("/voluntaryService")
    public Results<?> voluntaryService(@RequestBody AlgorithmIndex algorithmIndex) {
        Object voluntary = personService.voluntary(algorithmIndex);
        return Results.dataOf(voluntary);
    }

    /**
     * 用户输入留言内容
     * @param messageBoard 留言板
     * @return 是否留言成功
     */
    @PostMapping("/message")
    public Results<?> messageBoard(@RequestBody MessageBoard messageBoard) {
        boolean isSuccess = personService.addMessage(messageBoard);
        return Results.dataOf(isSuccess);
    }
}
