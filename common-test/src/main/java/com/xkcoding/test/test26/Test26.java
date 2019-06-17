package com.xkcoding.test.test26;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.Week;
import cn.hutool.core.util.RandomUtil;
import com.google.common.collect.Lists;
import com.taskadapter.redmineapi.*;
import com.taskadapter.redmineapi.bean.CustomField;
import com.taskadapter.redmineapi.bean.Issue;
import com.taskadapter.redmineapi.bean.Project;
import com.taskadapter.redmineapi.internal.ResultsWrapper;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 测试 redmine 的 api
 * </p>
 *
 * @package: com.xkcoding.test.test26
 * @description: 测试 redmine 的 api
 * @author: yangkai.shen
 * @date: Created in 2019-05-09 09:07
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
public class Test26 {
    private final static String URI = "http://bdms.mchz.com.cn:6999";
    /**
     * 替换成自己的 API_ACCESS_KEY，前往 http://bdms.mchz.com.cn:6999/my/account 获取
     */
    private final static String API_ACCESS_KEY = "***********************";

    private final static RedmineManager MGR = RedmineManagerFactory.createWithApiKey(URI, API_ACCESS_KEY);

    private final static String ME = "沈扬凯";

    private final static List<String> RANDOM_CONTENT = Lists.newArrayList("暗数据发现", "暗数据发现功能开发", "主数据管理", "数据管控-主数据管理");

    public static void main(String[] args) throws RedmineException {
        // selectIssues();
        // selectIssue(96094);
        // selectProjects();
        // createIssue();
        autoCreateIssue();
    }

    /**
     * 自动建issue，过滤周六周日，可以设置排除日期，[起始日期，结束日期]，闭区间
     */
    private static void autoCreateIssue() {
        // 开始时间
        String startDate = "2019-05-18";
        // 结束时间
        String endDate = "2019-06-17";
        // 过滤时间
        // List<String> ignoreDate = Lists.newArrayList();
        List<String> ignoreDate = Lists.newArrayList("2019-06-06", "2019-06-07", "2019-06-10", "2019-06-11");

        System.out.println("开始时间：" + startDate);
        System.out.println("结束时间：" + endDate);

        List<DateTime> dateTimeList = DateUtil.rangeToList(DateUtil.parseDate(startDate), DateUtil.parseDate(endDate), DateField.DAY_OF_MONTH);
        List<DateTime> filterDateTime = dateTimeList.stream()
                // 过滤周六周日
                .filter(date -> !Week.SATURDAY.equals(DateUtil.dayOfWeekEnum(date)) && !Week.SUNDAY.equals(DateUtil.dayOfWeekEnum(date)))
                // 过滤 自定义日期
                .filter(date -> !ignoreDate.contains(DateUtil.formatDate(date)))
                // over
                .collect(Collectors.toList());
        System.out.println("创建issue的时间：" + filterDateTime);

        System.out.println("开始批量创建issues");
        filterDateTime.forEach(dateTime -> {
            Issue issue = new Issue(MGR.getTransport(), 147);
            issue.setProjectId(147);
            issue.setSubject(ME + "-" + DateUtil.format(dateTime, "MM.dd"));
            issue.setDescription("<p>" + RandomUtil.randomEle(RANDOM_CONTENT) + "</p>");
            issue.setAssigneeId(350);
            issue.setStartDate(DateUtil.parseDate(DateUtil.formatDate(dateTime)));
            issue.setStatusId(1);
            issue.setPriorityId(2);
            issue.addCustomField(buildCustomField(58, DateUtil.formatDate(dateTime)));
            issue.addCustomField(buildCustomField(51, "2017RD027自行车大数据智能运营平台"));
            try {
                issue.create();
                System.out.println(DateUtil.formatDate(dateTime) + "的issue创建成功");
            } catch (RedmineException e) {
                System.err.println(e);
            }
        });
        System.out.println("批量创建issues成功");

    }

    /**
     * 创建issue
     */
    private static void createIssue() throws RedmineException {
        Issue issue = new Issue(MGR.getTransport(), 147);
        issue.setProjectId(147);
        issue.setSubject("沈扬凯-05.09");
        issue.setDescription("<p>暗数据发现</p>");
        issue.setAssigneeId(350);
        issue.setStartDate(DateUtil.parseDate("2019-05-09"));
        issue.setStatusId(1);
        issue.setPriorityId(2);
        issue.addCustomField(buildCustomField(58, "2019-05-09"));
        issue.addCustomField(buildCustomField(51, "2017RD027自行车大数据智能运营平台"));
        issue.create();
    }

    private static CustomField buildCustomField(Integer id, String value) {
        CustomField customField = new CustomField();
        customField.setId(id);
        customField.setValue(value);
        return customField;
    }

    /**
     * 获取所有项目列表，找到 [报工单] 项目，id为147
     */
    private static void selectProjects() throws RedmineException {
        ProjectManager projectManager = MGR.getProjectManager();
        List<Project> projects = projectManager.getProjects();
        projects.forEach(System.out::println);
    }

    /**
     * 根据 Id 查询 Issue
     *
     * @param issueId issue Id
     */
    private static void selectIssue(Integer issueId) throws RedmineException {
        Issue issue = MGR.getIssueManager().getIssueById(issueId);
        System.out.println("issue = " + issue);
    }

    /**
     * 查询 Issue 列表
     */
    private static void selectIssues() throws RedmineException {
        Params params = new Params().add("set_filter", "1")
                .add("sort", "id:desc")
                .add("f[]", "status_id")
                .add("op[status_id]", "o")
                .add("f[]", "author_id")
                .add("op[author_id]", "=")
                .add("v[author_id]", "me")
                .add("per_page", "25");
        ResultsWrapper<Issue> issues = MGR.getIssueManager().getIssues(params);
        for (Issue issue : issues.getResults()) {
            System.out.println(issue.toString());
        }
    }
}
