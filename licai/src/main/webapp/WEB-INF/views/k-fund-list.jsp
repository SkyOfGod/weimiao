<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div style="width: 100%;height: 40px">
    名称:&nbsp;&nbsp;<input class="easyui-numberbox" id="listk_searchName">
    <button class="easyui-linkbutton" iconCls="icon-search" onclick="fundListSearch()">搜索</button>
</div>
<table id="fund-list" style="width:100%;height:600px"></table>

<div id="editFund" class="easyui-dialog" data-options="closed:true">
    <form id="editFundForm" method="post">
        <table cellpadding="5">
            <input type="hidden" name="id">
            <tr>
                <td>基金代码:</td>
                <td>
                    <input class="easyui-textbox" name="code" style="width: 300px;"
                           data-options="editable:true,required:true"/>
                </td>
            </tr>
            <tr>
                <td>基金名称:</td>
                <td>
                    <input class="easyui-textbox" name="name" style="width: 300px;"
                           data-options="editable:true,required:true"/>
                </td>
            </tr>
            <tr>
                <td>基金创建时价格:</td>
                <td>
                    <input class="easyui-textbox" name="fundCreatePrice" style="width: 300px;"
                           data-options="editable:true,required:true"/>
                </td>
            </tr>
            <tr>
                <td>基金创建时间:</td>
                <td>
                    <input class="easyui-textbox" name="fundCreateTime" style="width: 300px;"
                           data-options="editable:true,required:true"/>
                </td>
            </tr>
            <tr>
                <td>管理基金经理（中间用逗号隔开）:</td>
                <td>
                    <input class="easyui-textbox" name="fundOperator" style="width: 300px;"
                           data-options="editable:true,required:true"/>
                </td>
            </tr>
            <tr>
                <td>基金经理管理时长（中间用逗号隔开）:</td>
                <td>
                    <input class="easyui-textbox" name="fundOperatorLong" style="width: 300px;"
                           data-options="editable:true,required:true"/>
                </td>
            </tr>
            <tr>
                <td>基金资产规模（格式 年月日/规模，中间用逗号隔开）:</td>
                <td>
                    <input class="easyui-textbox" name="fundAssertScale" style="width: 300px;"
                           data-options="editable:true,required:true"/>
                </td>
            </tr>
            <tr>
                <td>基金目前价格:</td>
                <td>
                    <input class="easyui-textbox" name="price" style="width: 300px;"
                           data-options="editable:true,required:true"/>
                </td>
            </tr>
            <tr>
                <td>排序:</td>
                <td>
                    <input class="easyui-textbox" name="sort" value="0" style="width: 300px;"
                           data-options="editable:true,required:true"/>
                </td>
            </tr>
            <tr>
                <td>备注:</td>
                <td><input class="easyui-textbox" name="remark" data-options="multiline:true,validType:'length[0,150]'"
                           style="height:60px;width: 300px;"/></td>
            </tr>
        </table>
    </form>
</div>

<script type="text/javascript" charset="utf-8">

    $('#fund-list').datagrid({
        url: '/fund/listPage',
        title: '基金列表',
        pagePosition: 'top',
        singleSelect: true,
        rownumbers: true,
        collapsible: true,
        pagination: true,
        pageSize: 20,
        pageList: [20, 50, 100],
        toolbar: [{
            text: '新增',
            iconCls: 'icon-add',
            handler: function () {
                addFund();
            }
        }, '-', {
            text: '编辑',
            iconCls: 'icon-edit',
            handler: function () {
                editFund();
            }
        }, '-', {
                text: '删除',
                iconCls: 'icon-cancel',
                handler: function () {
                    deleteFund();
                }
            }
        ],
        columns: [[
            {field: 'id', checkbox: true},
            {field: 'code', title: '基金代码', width: 100, align: 'center'},
            {field: 'name', title: '基金名称', width: 150, align: 'center'},
            {field: 'fundCreatePrice', title: '基金创建时价格', width: 110, align: 'center'},
            {field: 'fundCreateTime', title: '基金创建时间', width: 100, align: 'center'},
            {field: 'fundOperator', title: '管理基金经理', width: 100, align: 'center'},
            {field: 'fundOperatorLong', title: '基金经理管理时长', width: 120, align: 'center'},
            {field: 'fundAssertScale', title: '基金资产规模', width: 150, align: 'center'},
            {field: 'averageIncome', title: '平均年化收益率', width: 100, align: 'center',
                formatter: function (value, row, index) {
                    if (value > 0) {
                        return value/100 + '%';
                    }
                    return value;
                }},
            {field: 'sort', title: '排序', width: 80, align: 'center'},
            {field: 'remark', title: '备注', width: 300, align: 'left'},
            {field: 'createTime', title: '创建时间', width: 150, align: 'center'},
            {field: 'updateTime', title: '修改时间', width: 150, align: 'center'},
        ]],
        onBeforeLoad: function (param) {
            param.pageNo = param.page;
            param.pageSize = param.rows;
            param.name = $('#listk_searchName').val();
            return true;
        }
    });

    addFund = function () {
        $("#editFund").dialog({
            title: '新增基金',
            width: 700,
            height: 700,
            top: 60,
            left: 150,
            closed: false,
            cache: false,
            modal: true,
            buttons: [{
                text: '保存',
                handler: function () {
                    if (!$("#editFundForm").form('validate')) {
                        return false;
                    }
                    var params = $("#editFundForm").serialize();
                    $.post("/fund/add", params, function (data) {
                        if (data.code == 200) {
                            $.messager.alert('提示', '新增成功!', 'info',
                                function () {
                                    $("#editFund").dialog('close');
                                    $("#fund-list").datagrid("reload");
                                });
                        } else {
                            $.messager.alert('提示', data.msg, 'warning');
                        }
                    });
                }
            }, {
                text: '关闭',
                handler: function () {
                    $("#editFund").dialog("close");
                }
            }],
            onBeforeClose: function () {
                $("#editFundForm").form("clear");
            }
        });
    }

    editFund = function () {
        var ids = getFundListSelectionsIds();
        if (ids.length == 0) {
            $.messager.alert('提示', '必须选择一条数据才能编辑!');
            return;
        }
        if (ids.indexOf(',') > 0) {
            $.messager.alert('提示', '只能选择一条数据!');
            return;
        }
        $("#editFund").dialog({
            title: '编辑基金',
            width: 700,
            height: 700,
            top: 60,
            left: 150,
            closed: false,
            cache: false,
            modal: true,
            buttons: [{
                text: '保存',
                handler: function () {
                    if (!$("#editFundForm").form('validate')) {
                        return false;
                    }
                    var params = $("#editFundForm").serialize();
                    $.post("/fund/update", params, function (data) {
                        if (data.code == 200) {
                            $.messager.alert('提示', '修改成功!', 'info',
                                function () {
                                    $("#editFund").dialog('close');
                                    $("#fund-list").datagrid("reload");
                                });
                        } else {
                            $.messager.alert('提示', data.msg, 'warning');
                        }
                    });
                }
            }, {
                text: '关闭',
                handler: function () {
                    $("#editFund").dialog("close");
                }
            }],
            onBeforeClose: function () {
                $("#editFundForm").form("clear");
            }
        });
        var data = $("#fund-list").datagrid("getSelected");
        $("#editFundForm").form("load", data);
    }

    deleteFund = function () {
        var ids = getFundListSelectionsIds();
        if (ids.length == 0) {
            $.messager.alert('提示', '未选中数据!');
            return;
        }
        $.messager.confirm('确认', '确定删除ID为 ' + ids + ' 的数据吗？', function (r) {
            if (r) {
                var params = {"id": ids};
                $.post("/fund/delete", params, function (data) {
                    if (data.code == 200) {
                        $.messager.alert('提示', '删除成功!', 'info',
                            function () {
                                $("#fund-list").datagrid("reload");
                            });
                    }
                });
            }
        });
    }

    fundListSearch = function () {
        $('#fund-list').datagrid('load');
    }

    getFundListSelectionsIds = function () {
        var itemList = $("#fund-list");
        var sels = itemList.datagrid("getSelections");
        var ids = [];
        for (var i in sels) {
            ids.push(sels[i].id);
        }
        ids = ids.join(",");
        return ids;
    }

</script>
