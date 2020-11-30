<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div style="width: 100%;height: 40px">
    公司:&nbsp;&nbsp;<input class="easyui-textbox" id="list_searchCompanyId">
    行业类型:&nbsp;&nbsp;<input class="easyui-textbox" id="list_searchCompanyType">
    <button class="easyui-linkbutton" iconCls="icon-search" onclick="companyListSearch()">搜索</button>
</div>
<table id="company-list" style="width:100%;height:600px"></table>

<div id="editCompany" class="easyui-dialog" data-options="closed:true">
    <form id="editCompanyForm" method="post">
        <table cellpadding="5">
            <input type="hidden" name="id">
            <tr>
                <td>股票代码:</td>
                <td>
                    <input class="easyui-textbox" name="code" style="width: 300px;" data-options="editable:true,required:true"/>
                </td>
            </tr>
            <tr>
                <td>公司名称:</td>
                <td><input class="easyui-textbox" name="name" style="width: 300px;" data-options="editable:true,required:true"/></td>
            </tr>
            <tr>
                <td>行业类型:</td>
                <td>
                    <input class="easyui-textbox" name="type" style="width: 180px;" data-options="editable:true,required:true"/>
                </td>
            </tr>
            <tr>
                <td>归属市场:</td>
                <td>
                    <select class="easyui-combobox" name="location" style="width: 100px;" data-options="editable:true,required:true">
                        <option value="1">沪</option>
                        <option value="2">深</option>
                        <option value="3">港</option>
                        <option value="4">美</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>类型:</td>
                <td>
                    <select class="easyui-combobox" name="category" style="width: 100px;" data-options="editable:true,required:true">
                        <option value="1">股票</option>
                        <option value="2">基金</option>
                        <option value="3">Reits</option>
                        <option value="4">可转债</option>
                        <option value="5">逆回购</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>排序:</td>
                <td><input class="easyui-numberbox" name="sort" value="0" style="width: 100px;" data-options="editable:true,required:true"/></td>
            </tr>
            <tr>
                <td>指标级别:</td>
                <td><input class="easyui-numberbox" name="targetLevel" value="0" style="width: 100px;" data-options="editable:true,required:true"/></td>
            </tr>
            <tr>
                <td>现总股本:</td>
                <td><input class="easyui-numberbox" name="totalEquity" style="width: 300px;" data-options="editable:true,required:true"/></td>
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
    $("#editCompanyForm [name='type']").combogrid({
        panelWidth: 170,
        idField: 'key',
        textField: 'value',
        url: '/company/typeCombogrid',
        mode: 'remote',
        delay: 500,
        columns: [[
            {field: 'key', title: '值', width: 30, align: 'center'},
            {field: 'value', title: '类型', width: 120, align: 'center'},
        ]],
    });

    $("#list_searchCompanyType").combogrid({
        panelWidth: 170,
        idField: 'key',
        textField: 'value',
        url: '/company/typeCombogrid',
        mode: 'remote',
        delay: 500,
        columns: [[
            {field: 'key', title: '值', width: 30, align: 'center'},
            {field: 'value', title: '类型', width: 120, align: 'center'},
        ]],
    });

    $("#list_searchCompanyId").combogrid({
        panelWidth: 260,
        idField: 'id',
        textField: 'name',
        url: '/company/combogrid',
        mode: 'remote',
        delay: 500,
        columns: [[
            {field: 'id', title: '主键', width: 40, align: 'center'},
            {field: 'code', title: '股票代码', width: 100, align: 'center'},
            {field: 'name', title: '公司名称', width: 100, align: 'center'},
        ]],
    });

    $('#company-list').datagrid({
        url: '/company/companyPage',
        title: '公司列表',
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
                addCompany();
            }
        }, '-', {
            text: '编辑',
            iconCls: 'icon-edit',
            handler: function () {
                editCompany();
            }
        }, '-',
            {
                text: '删除',
                iconCls: 'icon-cancel',
                handler: function () {
                    deleteCompany();
                }
            },
        ],
        columns: [[
            {field: 'id', checkbox: true},
            {field: 'code', title: '股票代码', width: 100, align: 'center'},
            {field: 'name', title: '公司名称', width: 150, align: 'center'},
            {field: 'type', title: '行业类型', width: 80, align: 'center'},
            {field: 'location', title: '归属市场', width: 60, align: 'center'},
            {field: 'category', title: '类型', width: 60, align: 'center'},
            {field: 'totalEquity', title: '总股本', width: 100, align: 'right'},
            {field: 'remark', title: '备注', width: 200, align: 'left'},
            {field: 'sort', title: '排序', width: 40, align: 'center'},
            {field: 'targetLevel', title: '指标级别', width: 80, align: 'center'},
            {field: 'createTime', title: '创建时间', width: 150, align: 'center'},
            {field: 'updateTime', title: '修改时间', width: 150, align: 'center'},
        ]],
        onBeforeLoad: function (param) {
            param.pageNo = param.page;
            param.pageSize = param.rows;
            param.companyId = $('#list_searchCompanyId').val();
            param.type = $('#list_searchCompanyType').val();
            return true;
        }
    });

    addCompany = function () {
        $("#editCompany").dialog({
            title: '新增公司详情',
            width: 500,
            height: 500,
            closed: false,
            cache: false,
            modal: true,
            buttons: [{
                text: '保存',
                handler: function () {
                    if(!$("#editCompanyForm").form('validate')) {
                        return false;
                    }
                    var params = $("#editCompanyForm").serialize();
                    $.post("/company/add", params, function (data) {
                        if (data.code == 200) {
                            $.messager.alert('提示', '新增成功!', 'info',
                                function () {
                                    $("#editCompany").dialog('close');
                                    $("#company-list").datagrid("reload");
                                });
                        } else {
                            $.messager.alert('提示', data.msg, 'warning');
                        }
                    });
                }
            }, {
                text: '关闭',
                handler: function () {
                    $("#editCompany").dialog("close");
                }
            }],
            onBeforeClose: function () {
                $("#editCompanyForm").form("clear");
            }
        });
    }

    editCompany = function () {
        var ids = getCompanyListSelectionsIds();
        if (ids.length == 0) {
            $.messager.alert('提示', '必须选择一条数据才能编辑!');
            return;
        }
        if (ids.indexOf(',') > 0) {
            $.messager.alert('提示', '只能选择一条数据!');
            return;
        }
        $("#editCompany").dialog({
            title: '编辑公司详情',
            width: 500,
            height: 500,
            closed: false,
            cache: false,
            modal: true,
            buttons: [{
                text: '保存',
                handler: function () {
                    if(!$("#editCompanyForm").form('validate')) {
                        return false;
                    }
                    var params = $("#editCompanyForm").serialize();
                    $.post("/company/update", params, function (data) {
                        if (data.code == 200) {
                            $.messager.alert('提示', '修改成功!', 'info',
                                function () {
                                    $("#editCompany").dialog('close');
                                    $("#company-list").datagrid("reload");
                                });
                        } else {
                            $.messager.alert('提示', data.msg, 'warning');
                        }
                    });
                }
            }, {
                text: '关闭',
                handler: function () {
                    $("#editCompany").dialog("close");
                }
            }],
            onBeforeClose: function () {
                $("#editCompanyForm").form("clear");
            }
        });
        var data = $("#company-list").datagrid("getSelected");
        $("#editCompanyForm").form("load", data);
    }

    deleteCompany = function () {
        var ids = getCompanyListSelectionsIds();
        if (ids.length == 0) {
            $.messager.alert('提示', '未选中数据!');
            return;
        }
        $.messager.confirm('确认', '确定删除ID为 ' + ids + ' 的公司详情吗？', function (r) {
            if (r) {
                var params = {"id": ids};
                $.post("/company/delete", params, function (data) {
                    if (data.code == 200) {
                        $.messager.alert('提示', '删除成功!', 'info',
                            function () {
                                $("#company-list").datagrid("reload");
                            });
                    }
                });
            }
        });
    }

    companyListSearch = function () {
        $('#company-list').datagrid('load');
    }

    getCompanyListSelectionsIds = function () {
        var itemList = $("#company-list");
        var sels = itemList.datagrid("getSelections");
        var ids = [];
        for (var i in sels) {
            ids.push(sels[i].id);
        }
        ids = ids.join(",");
        return ids;
    }

</script>
