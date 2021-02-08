<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div style="width: 100%;height: 40px">
    名称:&nbsp;&nbsp;<input class="easyui-numberbox" id="listj_searchName">
    <button class="easyui-linkbutton" iconCls="icon-search" onclick="hotCompanyListSearch()">搜索</button>
</div>
<table id="hot-company-list" style="width:100%;height:600px"></table>

<div id="editHotCompany" class="easyui-dialog" data-options="closed:true">
    <form id="editHotCompanyForm" method="post">
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
                <td>热点1:</td>
                <td>
                    <input class="easyui-textbox" name="hotType1" style="width: 180px;" data-options="editable:true,required:true"/>
                </td>
            </tr>
            <tr>
                <td>热点2:</td>
                <td>
                    <input class="easyui-textbox" name="hotType2" style="width: 180px;" data-options="editable:true"/>
                </td>
            </tr>
            <tr>
                <td>热点3:</td>
                <td>
                    <input class="easyui-textbox" name="hotType3" style="width: 180px;" data-options="editable:true"/>
                </td>
            </tr>
            <tr>
                <td>最近连扳次数:</td>
                <td>
                    <input class="easyui-textbox" name="continuityTime" value="0" style="width: 300px;"
                           data-options="editable:true,required:true"/>
                </td>
            </tr>
            <tr>
                <td>首板日期:</td>
                <td>
                    <input class="easyui-datebox" name="firstTime" value="0" style="width: 300px;"
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

  $("#editHotCompanyForm [name='hotType1']").combogrid({
    panelWidth: 200,
    idField: 'id',
    textField: 'name',
    url: '/hotType/combogrid',
    mode: 'remote',
    delay: 500,
    columns: [[
      {field: 'id', title: '主键', width: 40, align: 'center'},
      {field: 'name', title: '名称', width: 100, align: 'center'},
      {field: 'sort', title: '排序', width: 40, align: 'center'},
    ]],
  });

  $("#editHotCompanyForm [name='hotType2']").combogrid({
    panelWidth: 200,
    idField: 'id',
    textField: 'name',
    url: '/hotType/combogrid',
    mode: 'remote',
    delay: 500,
    columns: [[
      {field: 'id', title: '主键', width: 40, align: 'center'},
      {field: 'name', title: '名称', width: 100, align: 'center'},
      {field: 'sort', title: '排序', width: 40, align: 'center'},
    ]],
  });

  $("#editHotCompanyForm [name='hotType3']").combogrid({
    panelWidth: 200,
    idField: 'id',
    textField: 'name',
    url: '/hotType/combogrid',
    mode: 'remote',
    delay: 500,
    columns: [[
      {field: 'id', title: '主键', width: 40, align: 'center'},
      {field: 'name', title: '名称', width: 100, align: 'center'},
      {field: 'sort', title: '排序', width: 40, align: 'center'},
    ]],
  });

    $('#hot-company-list').datagrid({
        url: '/hotCompany/listPage',
        title: '热点类型列表',
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
                addHotCompany();
            }
        }, '-', {
            text: '编辑',
            iconCls: 'icon-edit',
            handler: function () {
                editHotCompany();
            }
        }, '-', {
                text: '删除',
                iconCls: 'icon-cancel',
                handler: function () {
                    deleteHotCompany();
                }
            }
        ],
        columns: [[
            {field: 'id', checkbox: true},
            {field: 'code', title: '股票代码', width: 100, align: 'center'},
            {field: 'name', title: '公司名称', width: 150, align: 'center'},
            {field: 'hotType1', title: '热点1', width: 80, align: 'center'},
            {field: 'hotType2', title: '热点2', width: 80, align: 'center'},
            {field: 'hotType3', title: '热点3', width: 80, align: 'center'},
            {field: 'continuityTime', title: '最近连扳次数', width: 100, align: 'center'},
            {field: 'firstTime', title: '首板日期', width: 100, align: 'center'},
            {field: 'remark', title: '备注', width: 300, align: 'left'},
            {field: 'createTime', title: '创建时间', width: 150, align: 'center'},
            {field: 'updateTime', title: '修改时间', width: 150, align: 'center'},
        ]],
        onBeforeLoad: function (param) {
            param.pageNo = param.page;
            param.pageSize = param.rows;
            param.name = $('#listj_searchName').val();
            return true;
        }
    });

    addHotCompany = function () {
        $("#editHotCompany").dialog({
            title: '新增指标',
            width: 500,
            height: 500,
            top: 60,
            left: 150,
            closed: false,
            cache: false,
            modal: true,
            buttons: [{
                text: '保存',
                handler: function () {
                    if (!$("#editHotCompanyForm").form('validate')) {
                        return false;
                    }
                    var params = $("#editHotCompanyForm").serialize();
                    $.post("/hotCompany/add", params, function (data) {
                        if (data.code == 200) {
                            $.messager.alert('提示', '新增成功!', 'info',
                                function () {
                                    $("#editHotCompany").dialog('close');
                                    $("#hot-company-list").datagrid("reload");
                                });
                        } else {
                            $.messager.alert('提示', data.msg, 'warning');
                        }
                    });
                }
            }, {
                text: '关闭',
                handler: function () {
                    $("#editHotCompany").dialog("close");
                }
            }],
            onBeforeClose: function () {
                $("#editHotCompanyForm").form("clear");
            }
        });
    }

    editHotCompany = function () {
        var ids = getHotCompanyListSelectionsIds();
        if (ids.length == 0) {
            $.messager.alert('提示', '必须选择一条数据才能编辑!');
            return;
        }
        if (ids.indexOf(',') > 0) {
            $.messager.alert('提示', '只能选择一条数据!');
            return;
        }
        $("#editHotCompany").dialog({
            title: '编辑指标',
            width: 500,
            height: 500,
            top: 60,
            left: 150,
            closed: false,
            cache: false,
            modal: true,
            buttons: [{
                text: '保存',
                handler: function () {
                    if (!$("#editHotCompanyForm").form('validate')) {
                        return false;
                    }
                    var params = $("#editHotCompanyForm").serialize();
                    $.post("/hotCompany/update", params, function (data) {
                        if (data.code == 200) {
                            $.messager.alert('提示', '修改成功!', 'info',
                                function () {
                                    $("#editHotCompany").dialog('close');
                                    $("#hot-company-list").datagrid("reload");
                                });
                        } else {
                            $.messager.alert('提示', data.msg, 'warning');
                        }
                    });
                }
            }, {
                text: '关闭',
                handler: function () {
                    $("#editHotCompany").dialog("close");
                }
            }],
            onBeforeClose: function () {
                $("#editHotCompanyForm").form("clear");
            }
        });
        var data = $("#hot-company-list").datagrid("getSelected");
        $("#editHotCompanyForm").form("load", data);
    }

    deleteHotCompany = function () {
        var ids = getHotCompanyListSelectionsIds();
        if (ids.length == 0) {
            $.messager.alert('提示', '未选中数据!');
            return;
        }
        $.messager.confirm('确认', '确定删除ID为 ' + ids + ' 的公司详情吗？', function (r) {
            if (r) {
                var params = {"id": ids};
                $.post("/hotCompany/delete", params, function (data) {
                    if (data.code == 200) {
                        $.messager.alert('提示', '删除成功!', 'info',
                            function () {
                                $("#hot-company-list").datagrid("reload");
                            });
                    }
                });
            }
        });
    }

    hotCompanyListSearch = function () {
        $('#hot-company-list').datagrid('load');
    }

    getHotCompanyListSelectionsIds = function () {
        var itemList = $("#hot-company-list");
        var sels = itemList.datagrid("getSelections");
        var ids = [];
        for (var i in sels) {
            ids.push(sels[i].id);
        }
        ids = ids.join(",");
        return ids;
    }

</script>
